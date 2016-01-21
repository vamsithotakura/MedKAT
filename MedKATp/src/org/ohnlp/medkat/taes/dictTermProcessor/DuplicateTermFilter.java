package org.ohnlp.medkat.taes.dictTermProcessor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


import org.apache.uima.analysis_engine.annotator.AnnotatorProcessException;
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
 

public class DuplicateTermFilter
        extends DictTermProcessorBase
{

    protected void processSemclass (Annotation span,
                                    String semClass,
                                    ArrayList<DictTerm> terms)
        throws AnnotatorProcessException
    {
        
        Map<String, DictTerm> uniqueTerms = new HashMap<String, DictTerm> ();

        Iterator<DictTerm> termIter = terms.iterator ();
        
        while (termIter.hasNext ())
        {
            DictTerm term = termIter.next ();
            String key = generateKey (term);
            if (uniqueTerms.containsKey (key))
            {
                term.setMarked (term.getMarked () | DictTermMarkers.DUPLICATE_INDICATOR);
                //System.err.println("DUPLICATE TERM: '" + term.getCoveredText () + "'");
            }
            else
            {
                uniqueTerms.put (key, term);
            }
        }

    }


    private String generateKey (DictTerm term)
    {
        return "" + term.getBegin () + "-" + term.getEnd () + term.getAttributeValue ();
    }

}
