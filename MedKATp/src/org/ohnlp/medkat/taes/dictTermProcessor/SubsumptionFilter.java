package org.ohnlp.medkat.taes.dictTermProcessor;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


import org.apache.uima.analysis_engine.annotator.AnnotatorConfigurationException;
import org.apache.uima.analysis_engine.annotator.AnnotatorContext;
import org.apache.uima.analysis_engine.annotator.AnnotatorContextException;
import org.apache.uima.analysis_engine.annotator.AnnotatorInitializationException;
import org.apache.uima.analysis_engine.annotator.AnnotatorProcessException;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.tcas.Annotation;
import org.ohnlp.medkat.common.ParameterProcessor;
import org.ohnlp.medkat.taes.conceptMapper.DictTerm;
import org.ohnlp.medkat.taes.conceptMapper.DictTermMarkers;

/**
 * @author mtan
 * 
 * iterate through spans (e.g. sentences) and remove all annotations of type "DictTerm" that are subsumed by others with the SemClass:
 *
 * Remove duplicates (duplicates are defined as having equal begin, end and semantic class)
 * Subsumption processing:
 *    Diagnosis only:
 *      if term is one of the set of potential co-referring diagnoses, specified by the parameter "GenericTermCodes"
 *      and wholly contains another diagnosis, the co-referring diagnosis is removed
 * 
 *    If "comma overrides subsumption" parameter is not set, or text containing term does not contain a comma:
 *      if term is wholly contained within another term, remove the contained term
 */  
 
public class SubsumptionFilter
extends DictTermProcessorBase
{
    /**
     * name of parameter that indicates list of SemClasses where processing should ignore subsumption checking if the spanned text contains a comma (default = true)
     */
    private static final String PARAM_COMMAOVERRIDESSUBSUMPTION = "CommaOverridesSubsumption";
    private Set<String> commaOverridesSubsumption;

    /**
     * name of parameter that indicates whether any or all tokens need to match to qualify for subsumption. Possible values are:
     * - NoMatchingTokensRequired (default)
     * - AtLeastOneTokenRequiredToMatch
     * - AllTokensRequiredToMatch
     */
    private static final String PARAM_TOKENMATCHCRITERION = "TokensMatchCriterion";
    private static final String NoMatchingTokensRequired = "nomatchingtokensrequired";
    private boolean noMatchingTokensRequired = true;
    private static final String AtLeastOneTokenRequiredToMatch = "atleastonetokenrequiredtomatch";
    private boolean anyMatchingTokensRequired = false;
    private static final String AllTokensRequiredToMatch = "alltokensrequiredtomatch";
    private boolean allMatchingTokensRequired = false;

    
    private int disallowedContainedMarkers  =  (DictTermMarkers.IGNORED_INDICATOR     |
            DictTermMarkers.DUPLICATE_INDICATOR   |
            DictTermMarkers.SUBSUMED_INDICATOR    |
            DictTermMarkers.SUPERFLUOUS_INDICATOR |
            DictTermMarkers.MODIFIER_INDICATOR    |
            DictTermMarkers.CONTAINSDISALLOWEDTERM_INDICATOR);


    public void initialize (AnnotatorContext annotatorContext) throws AnnotatorInitializationException, AnnotatorConfigurationException
    {
        super.initialize(annotatorContext);
        // Process configration parameters
        try
        {
            commaOverridesSubsumption = ParameterProcessor.paramArrayToSet(annotatorContext, PARAM_COMMAOVERRIDESSUBSUMPTION, false);
            setMatchCriterion ();
        }
        catch (Exception e)
        {
            throw new AnnotatorConfigurationException (e);
        }
    }


    private void setMatchCriterion () throws AnnotatorContextException
    {
        String criterion = (String) getContext ().getConfigParameterValue (PARAM_TOKENMATCHCRITERION);

        if (criterion != null)
        {
            criterion = criterion.toLowerCase();
            noMatchingTokensRequired = false;
            anyMatchingTokensRequired = false;
            allMatchingTokensRequired = false;
            
            /**
             * name of parameter that indicates whether any or all tokens need to match to qualify for subsumption. Possible values are:
             * - NoMatchingTokensRequired (default)
             * - AtLeastOneTokenRequiredToMatch
             * - AllTokensRequiredToMatch
             */
            //System.err.println ("match criterion: " + criterion);
            if (criterion.equals(NoMatchingTokensRequired))
            {
                noMatchingTokensRequired = true;
            }
            else if (criterion.equals(AtLeastOneTokenRequiredToMatch))
            {
                anyMatchingTokensRequired = true;
            }
            else if (criterion.equals(AllTokensRequiredToMatch))
            {
                allMatchingTokensRequired = true;
            }
            else
            {
                System.err.println ("invalid match criterion: " + criterion + ". Should be either: " + NoMatchingTokensRequired + ", " + AtLeastOneTokenRequiredToMatch + ", or " + AllTokensRequiredToMatch);
                getLogger ().logError ("invalid match criterion: " + criterion + ". Should be either: " + NoMatchingTokensRequired + ", " + AtLeastOneTokenRequiredToMatch + ", or " + AllTokensRequiredToMatch);
            }
        }
    }


    protected void processSemclass (Annotation span, String semClass, ArrayList<DictTerm> terms)
        throws AnnotatorProcessException
    {
        Collections.sort (terms, new NumTokensComparator ());
        markSubsumedTerms (terms);
    }



    private void markSubsumedTerms (ArrayList<DictTerm> terms)
    throws AnnotatorProcessException
    {
        //for (int i = 0; i < terms.size (); i++)
        //{
        //    DictTerm term = (DictTerm)terms.get (i);
        //    System.err.println("terms[" + i + "]: " + term.getCoveredText () + ", matched Length: " + term.getMatchedTokens ().size ());
        //}
        for (int i = 0; i < terms.size (); i++)
        {
            DictTerm current = terms.get (i);
            //System.err.println("checking terms[" + i + "]: " + current.getCoveredText ());            
            //System.err.println("commaOverridesSubsumption, semClass: " + current.getSemClass () + ": " + commaOverridesSubsumption.contains (current.getSemClass ()));

            if (current != null)
            //if ((current != null) && (! DictTermMarkers.isMarkedAsSuperfluous (current)))
            {
                FSArray currentMatchedTokens = current.getMatchedTokens ();
                int currentBegin = ((Annotation) currentMatchedTokens.get (0)).getBegin ();
                int currentEnd = ((Annotation) currentMatchedTokens.get (currentMatchedTokens.size ()-1)).getEnd ();

                for (int j = i + 1; j < terms.size (); j++)
                {
                    DictTerm toCompare = terms.get (j);
                    
                    if (toCompare != null)
                    {
                        //System.err.println("against[" + j + "]: " + toCompare.getCoveredText ());
                        FSArray toCompareTokens = toCompare.getMatchedTokens ();
                        
                        if (currentMatchedTokens.size () < toCompareTokens.size ())
                        {
                            System.err.println("ERROR, DictTermFilter: terms out of order");
                            getLogger ().logError ("ERROR, DictTermFilter: terms out of order");
                            throw new AnnotatorProcessException ();
                        }
                        else if (! DictTermMarkers.isAnyMarkedAs (toCompare, disallowedContainedMarkers))
                        {
                            if (currentMatchedTokens.size () == toCompareTokens.size ())
                            {
                                // do nothing
                                //System.err.println("SAME TOKEN LENGTH: " + current.getCoveredText ());
                            }
                            
                            else
                            {
                                int toCompareBegin = ((Annotation) toCompareTokens.get (0)).getBegin ();
                                int toCompareEnd = ((Annotation) toCompareTokens.get (toCompareTokens.size ()-1)).getEnd ();
                                if (subsumes (currentBegin, currentEnd, toCompareBegin, toCompareEnd))
                                {
                                    if ((commaOverridesSubsumption.contains (current.getSemClass ())) && (current.getCoveredText().indexOf (',') != -1))
                                    {
                                        // make sure subsumed is not same term
                                        //System.err.println("commaOverridesSubsumption: " + current.getCoveredText ());
                                        if (current.getDictCanon ().equals (toCompare.getDictCanon ()))
                                        {
                                            toCompare.setMarked (toCompare.getMarked () | DictTermMarkers.DUPLICATE_INDICATOR);
                                            //System.err.println("ignore commaOverridesSubsumption, DUPLICATE TERM: '" + toCompare.getCoveredText () + "'");
                                        }
                                    }
                                    else
                                    {
        
                                        //System.err.println("\tcurrentBegin: " + currentBegin + ", currentEnd: " + currentEnd + ", toCompareBegin: " + toCompareBegin + ", toCompareEnd: " + toCompareEnd);
                                        //System.err.println("\tnoMatchingTokensRequired: " + noMatchingTokensRequired + ", anyMatchingTokensRequired: " + anyMatchingTokensRequired + ", allMatchingTokensRequired: " + allMatchingTokensRequired);
                                        //
                                        if ((noMatchingTokensRequired) ||
                                            ((anyMatchingTokensRequired && anyMatchingTokens (current, toCompare))) ||
                                            ((allMatchingTokensRequired && allTokensMatch (current, toCompare))))
                                        {
                                            //
                                            //for (int k = 0; k < toCompareTokens.size (); k++)
                                            //{
                                            //    System.err.print (((Annotation) toCompareTokens.get (k)).getCoveredText () + " ");
                                            //}
                                            //System.err.println ("");
                                            //System.err.println("\tis subsumed by: ");
                                            //for (int k = 0; k < currentMatchedTokens.size (); k++)
                                            //{
                                            //    System.err.print (((Annotation) currentMatchedTokens.get (k)).getCoveredText () + " ");
                                            //}
                                            //System.err.println ("");
                                            toCompare.setMarked (toCompare.getMarked () | DictTermMarkers.SUBSUMED_INDICATOR);
                                        }
                                        //else
                                        //{
                                            //for (int k = 0; k < toCompareTokens.size (); k++)
                                            //{
                                            //    System.err.print (((Annotation) toCompareTokens.get (k)).getCoveredText () + " ");
                                            //}
                                            //System.err.println ("");
                                            //System.err.println("\tis NOT subsumed by: ");
                                            //for (int k = 0; k < currentMatchedTokens.size (); k++)
                                            //{
                                            //    System.err.print (((Annotation) currentMatchedTokens.get (k)).getCoveredText () + " ");
                                            //}
                                            //System.err.println ("");
                                        //}
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }


    private boolean allTokensMatch (DictTerm current, DictTerm toCompare)
    {
        boolean result = false;
        //System.err.println ("allTokensMatch");

        FSArray currentMatchedTokens = current.getMatchedTokens ();
        FSArray toCompareMatchedTokens = current.getMatchedTokens ();
        if (currentMatchedTokens.size() == toCompareMatchedTokens.size())
        {
            Set<FeatureStructure> currentSet = new HashSet<FeatureStructure> ();
            Set<FeatureStructure> toCompareSet = new HashSet<FeatureStructure> ();
            for (int i = 0; i < currentMatchedTokens.size(); i++)
            {
                currentSet.add(currentMatchedTokens.get (i));
                toCompareSet.add(toCompareMatchedTokens.get (i));
                //System.err.println ("currentMatchedTokens[" + i + "]: " + ((Annotation) currentMatchedTokens.get (i)).getCoveredText());
                //System.err.println ("toCompareMatchedTokens[" + i + "]: " + ((Annotation) toCompareMatchedTokens.get (i)).getCoveredText());
            }
            if (currentSet.containsAll(toCompareSet))
            {
                //System.err.println ("currentSet.containsAll(toCompareSet)");
                result =  true;
            }

        }
        //System.err.println ("allTokensMatch returns: " + result);
        return result;
    }


    private boolean anyMatchingTokens (DictTerm current, DictTerm toCompare)
    {
        //System.err.println ("anyMatchingTokens");

        FSArray currentMatchedTokens = current.getMatchedTokens ();
        FSArray toCompareMatchedTokens = toCompare.getMatchedTokens ();

        Set<FeatureStructure> currentSet = new HashSet<FeatureStructure> ();
        for (int i = 0; i < currentMatchedTokens.size(); i++)
        {
            currentSet.add(currentMatchedTokens.get (i));
            //System.err.println ("currentMatchedTokens[" + i + "]: " + ((Annotation) currentMatchedTokens.get (i)).getCoveredText());
        }
        for (int i = 0; i < toCompareMatchedTokens.size(); i++)
        {
            if (currentSet.contains(toCompareMatchedTokens.get(i)))
            {
                //System.err.println ("currentSet contains toCompareMatchedTokens[" + i + "]: " + ((Annotation) toCompareMatchedTokens.get (i)).getCoveredText());
                return true;
            }
        }
        //System.err.println ("anyMatchingTokens returns: false");

        return false;
    }

}
