package org.ohnlp.medkat.taes.dictTermProcessor;


import java.util.ArrayList;
import java.util.Collections;


import org.apache.uima.analysis_engine.annotator.AnnotatorProcessException;
import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.tcas.Annotation;
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
 
public class SimpleSubsumptionFilter
extends DictTermProcessorBase
{


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
            DictTerm current = (DictTerm) terms.get (i);
            //System.err.println("checking terms[" + i + "]: " + current.getCoveredText ());

            if ((current != null) && (current.getCoveredText ().indexOf (',') == -1))
            {
                FSArray currentMatchedTokens = current.getMatchedTokens ();
                int currentBegin = ((Annotation) currentMatchedTokens.get (0)).getBegin ();
                int currentEnd = ((Annotation) currentMatchedTokens.get (currentMatchedTokens.size ()-1)).getEnd ();

                for (int j = i + 1; j < terms.size (); j++)
                {
                    DictTerm toCompare = (DictTerm) terms.get (j);
                    //System.err.println("against[" + j + "]: " + toCompare.getCoveredText ());
                    
                    if (toCompare != null)
                    {
                        FSArray toCompareTokens = toCompare.getMatchedTokens ();
                        
                        if (currentMatchedTokens.size () < toCompareTokens.size ())
                        {
                            System.err.println("ERROR, DictTermFilter: terms out of order");
                            getLogger ().logError ("ERROR, DictTermFilter: terms out of order");
                            throw new AnnotatorProcessException ();
                        }
                        else if (currentMatchedTokens.size () == toCompareTokens.size ())
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
                                /*
                                for (int k = 0; k < toCompareTokens.size (); k++)
                                {
                                    System.err.print (((Annotation) toCompareTokens.get (k)).getCoveredText () + " ");
                                }
                                System.err.println ("");
                                System.err.println("\tis subsumed by: ");
                                for (int k = 0; k < currentMatchedTokens.size (); k++)
                                {
                                    System.err.print (((Annotation) currentMatchedTokens.get (k)).getCoveredText () + " ");
                                }
                                System.err.println ("");

                                System.err.println("\tcurrentBegin: " + currentBegin + ", currentEnd: " + currentEnd + ", toCompareBegin: " + toCompareBegin + ", toCompareEnd: " + toCompareEnd);
                                */
                                toCompare.setMarked (toCompare.getMarked () | DictTermMarkers.SUBSUMED_INDICATOR);
                            }
                        }
                    }
                }
            }
        }
    }

}
