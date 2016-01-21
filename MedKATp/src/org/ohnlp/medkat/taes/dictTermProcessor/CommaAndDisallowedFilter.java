
package org.ohnlp.medkat.taes.dictTermProcessor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;
//import java.util.Iterator;
import java.util.List;


import org.apache.uima.analysis_engine.ResultSpecification;
import org.apache.uima.analysis_engine.annotator.AnnotatorConfigurationException;
import org.apache.uima.analysis_engine.annotator.AnnotatorContext;
import org.apache.uima.analysis_engine.annotator.AnnotatorContextException;
import org.apache.uima.analysis_engine.annotator.AnnotatorInitializationException;
import org.apache.uima.analysis_engine.annotator.AnnotatorProcessException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.TypeSystem;
//import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.cas.CAS;
import org.apache.uima.jcas.tcas.Annotation;
import org.ohnlp.medkat.taes.conceptMapper.DictTerm;
import org.ohnlp.medkat.taes.conceptMapper.DictTermMarkers;


public class CommaAndDisallowedFilter
extends DictTermProcessorBase
{

    public class BeginComparator
            implements Comparator<Annotation>
    {

        public int compare (Annotation leftObj, Annotation rightObj)
        {
            int leftBegin = ((Annotation) leftObj).getBegin ();
            int rightBegin = ((Annotation) rightObj).getBegin ();
            if (leftBegin < rightBegin)
            {
                return -1;
            }
            else if (leftBegin > rightBegin)
            {
                return 1;
            }
            return 0;
        }

    }


	private static final String PARAM_TOKEN_CLASS = "TokenClass";
    
    private int commaRuleDisallowedContainedMarkers  =  (DictTermMarkers.IGNORED_INDICATOR     |
            DictTermMarkers.DUPLICATE_INDICATOR   |
            DictTermMarkers.SUPERFLUOUS_INDICATOR |
            DictTermMarkers.MODIFIER_INDICATOR    |
            DictTermMarkers.CONTAINSDISALLOWEDTERM_INDICATOR);
    
    private int disallowedContainedMarkers  =  (DictTermMarkers.IGNORED_INDICATOR     |
            DictTermMarkers.DUPLICATE_INDICATOR   |
            DictTermMarkers.SUBSUMED_INDICATOR    |
            DictTermMarkers.SUPERFLUOUS_INDICATOR |
            DictTermMarkers.MODIFIER_INDICATOR    |
            DictTermMarkers.CONTAINSDISALLOWEDTERM_INDICATOR);

    private String tokenTypeName;

    private Type tokenType;
    private AnnotationIndex tokenIndex;




    protected AnnotationIndex getTokenIndex ()
    {
        return tokenIndex;
    }



    protected void setTokenIndex (AnnotationIndex tokenIndex)
    {
        this.tokenIndex = tokenIndex;
    }


    public void initialize (AnnotatorContext annotatorContext) throws AnnotatorInitializationException, AnnotatorConfigurationException
    {
        super.initialize(annotatorContext);
        try
        {
            tokenTypeName = (String) getContext ().getConfigParameterValue (PARAM_TOKEN_CLASS);
        }
        catch (AnnotatorContextException e)
        {
            e.printStackTrace ();
            throw new AnnotatorConfigurationException ();
        }

    }
    
    

    public void typeSystemInit (TypeSystem typeSystem) throws AnnotatorInitializationException, AnnotatorConfigurationException
    {
        super.typeSystemInit(typeSystem);
        tokenType = typeSystem.getType (tokenTypeName);
    }




    public void process (CAS tcas, ResultSpecification resultSpecification) throws AnnotatorProcessException
    {
        setTokenIndex ((AnnotationIndex) tcas.getAnnotationIndex(tokenType));
        super.process(tcas, resultSpecification);
    }

    
    /**
     * removes terms that contain a disallowed term that is:
     * 1) a "modifier" (unless the term is a complete term unto itself, e.g., "sigmoid colon")
     * 2) or one from a set of disallowed term types and also contains a comma
     */
    protected void processSemclass (Annotation span,
                                    String semClass,
                                    ArrayList<DictTerm> terms)
        throws AnnotatorProcessException
    {
        TreeSet<DictTerm> orderedTerms = new TreeSet<DictTerm> (new BeginComparator ());
        for (int i = 0; i < terms.size (); i++)
        {
            DictTerm current = terms.get (i);
            if (current != null)
            {
                //System.err.println ("Processing current: " + current.getCoveredText () + ", begin: " + current.getBegin () + ", end: " + current.getEnd ());
                //System.err.println ("Current tokens: ");
                //for (int tt = 0; tt < current.getMatchedTokens().size(); tt++)
                //{
                //    System.err.println ("\tToken[" + tt + "]: " + ((Annotation)(current.getMatchedTokens().get(tt))).getCoveredText());                    
                //}
                int containedMarkers = containsDisallowedTerm (getAllowMask(), current, getDictTermIndex());
                boolean currentContainsExtraneousTokens = containsExtraneousTokens (current);
                //System.err.println ("DictTermMarkers.isMarkedAsModifer (containedMarkers): " + DictTermMarkers.isMarkedAsModifer (containedMarkers) + ", (DictTermMarkers.isAnyMarkedAs (containedMarkers, disallowedContainedMarkers): " + DictTermMarkers.isAnyMarkedAs (containedMarkers, disallowedContainedMarkers) + ", currentContainsExtraneousTokens: " + currentContainsExtraneousTokens);
                //System.err.println ("DictTermMarkers.isAnyMarkedAs (containedMarkers, disallowedContainedMarkers): " + DictTermMarkers.isAnyMarkedAs (containedMarkers, disallowedContainedMarkers) + ", containsComma (current): " + containsComma (current));
                if ((DictTermMarkers.isMarkedAsModifer (containedMarkers) && currentContainsExtraneousTokens) ||
                     (DictTermMarkers.isAnyMarkedAs (containedMarkers, commaRuleDisallowedContainedMarkers) && (containsComma (current))))
                {
                    //System.err.println ("Current: " + current.getCoveredText () + ", begin: " + current.getBegin () + ", end: " + current.getEnd ());
                    //System.err.println (">>>>DictTermMarkers.isMarkedAsModifer (containedMarkers): " + DictTermMarkers.isMarkedAsModifer (containedMarkers) + ", (DictTermMarkers.isAnyMarkedAs (containedMarkers, disallowedContainedMarkers): " + DictTermMarkers.isAnyMarkedAs (containedMarkers, disallowedContainedMarkers) + ", currentContainsExtraneousTokens: " + currentContainsExtraneousTokens);
                    //System.err.println (">>>>DictTermMarkers.isAnyMarkedAs (containedMarkers, disallowedContainedMarkers): " + DictTermMarkers.isAnyMarkedAs (containedMarkers, disallowedContainedMarkers) + ", containsComma (current): " + containsComma (current));
                    current.setMarked (current.getMarked () | DictTermMarkers.CONTAINSDISALLOWEDTERM_INDICATOR);
                }
                // collect all unmarked terms that contain more than one token for further processing below
                if ((! DictTermMarkers.isAnyMarkedAs (current.getMarked (), disallowedContainedMarkers)) &&
                    ((current.getMatchedTokens ().size () > 1)))
                {
                    orderedTerms.add (current);
                }
            }
        }
        
        // iterate left-to-right through unmarked terms that contain more than one token and do not contain extraneous tokens, 
        // and remove terms that start within another term
        Iterator<DictTerm> iter = orderedTerms.iterator ();
        DictTerm prev = null;
        while (iter.hasNext ())
        {
            DictTerm current = (DictTerm) iter.next ();
            //System.err.println ("Current: " + current.getCoveredText () + ", begin: " + current.getBegin () + ", end: " + current.getEnd ());
            if (prev != null)
            {
                //System.err.println ("Prev: " + prev.getCoveredText () + ", begin: " + prev.getBegin () + ", end: " + prev.getEnd ());
                if (((current.getBegin () > prev.getBegin ()) && (current.getBegin () < prev.getEnd ())) &&
                     (containsComma (current)))
                {
                    current.setMarked (current.getMarked () | DictTermMarkers.CONTAINSDISALLOWEDTERM_INDICATOR);
                    //System.err.println ("Marking: '" + current.getCoveredText () + "', it starts inside '" + prev.getCoveredText () + "'");
                }
            }
            if (! DictTermMarkers.isAnyMarkedAs (current.getMarked (), disallowedContainedMarkers))
            {
                prev = current;
            }
        }
    }



    private boolean containsComma (DictTerm current)
    {
        return current.getCoveredText().indexOf (',') != -1;
    }
    
    /**
     * return true if some unused tokens within dictTerm (i.e., if span is "A B C" and dicTerm is "A C", then "B" is unused)
     * @param dictTerm
     * @param tokenIndex
     * @return
     */
    private boolean containsExtraneousTokens (DictTerm dictTerm)
    {
        FSIterator subTokenIter = getTokenIndex().subiterator (dictTerm);
        int numSpannedTokens = 0;
        int numMatchedTokens = dictTerm.getMatchedTokens ().size ();
        while (subTokenIter.hasNext ())
        {
            subTokenIter.next ();
            numSpannedTokens += 1;
            if (numSpannedTokens > numMatchedTokens)
            {
                return true;
            }
            
        }
        return false;
    }


    // return true if term contains another term that has been marked. All tokens of disallowed term must be part of dictTerm, otherwise return false
    private int containsDisallowedTerm (int allowMask, DictTerm dictTerm, AnnotationIndex dictTermIndex)
    {
        int returnValue = 0;
        
        //System.err.println("containsDisallowedTerm, testing: " + dictTerm.getDictCanon ());
        FSIterator subTermIter = dictTermIndex.subiterator (dictTerm);

        while (subTermIter.hasNext ())
        {
            DictTerm subsumedTerm = (DictTerm) subTermIter.next ();
            if ((!isAllowed (allowMask, subsumedTerm)) &&
                (subsumes (dictTerm.getBegin (), dictTerm.getEnd (), subsumedTerm.getBegin (), subsumedTerm.getEnd ())))
            {
                //System.err.println("containsDisallowedTerm, testing against: " + subsumedTerm.getDictCanon ());
                List<FeatureStructure> dictTermTokens  = Arrays.asList (dictTerm.getMatchedTokens ().toArray ());

                //Iterator junkIter = dictTermTokens.iterator ();
                //System.err.println ("dictTermTokens");
                //while (junkIter.hasNext ())
                //{
                //    Annotation annot = (Annotation) junkIter.next ();
                //    System.err.println ("\t" + annot.getCoveredText ());
                //}
                List<FeatureStructure> subsumedTermTokens  = Arrays.asList (subsumedTerm.getMatchedTokens ().toArray ());
                //Iterator junkIter2 = subsumedTermTokens.iterator ();
                //System.err.println ("subsumedTermTokens");
                //while (junkIter2.hasNext ())
                //{
                //    Annotation annot = (Annotation) junkIter2.next ();
                //    System.err.println ("\t" + annot.getCoveredText ());
                //}
                if (dictTermTokens.containsAll (subsumedTermTokens))
                {
                //    System.err.println("dictTermTokens.containsAll (subsumedTermTokens) --> containsDisallowedTerm!!! " + dictTerm.getCoveredText ());
                    returnValue |= subsumedTerm.getMarked ();
                }
            }
            else if (! subsumes (dictTerm.getBegin (), dictTerm.getEnd (), subsumedTerm.getBegin (), subsumedTerm.getEnd ()))
            {
                System.err.println ("****SUBITERATOR PROBLEM!");
                System.err.println ("dictTerm.getBegin (): " + dictTerm.getBegin () + ", dictTerm.getEnd (): " + dictTerm.getEnd ());
                System.err.println ("subsumedTerm.getBegin (): " + subsumedTerm.getBegin () + ", subsumedTerm.getEnd (): " + subsumedTerm.getEnd ());
            }
        }

        return returnValue;
    }



}
