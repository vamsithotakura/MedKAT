package org.ohnlp.medkat.taes.dictTermProcessor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;


import org.apache.uima.analysis_engine.annotator.AnnotatorConfigurationException;
import org.apache.uima.analysis_engine.annotator.AnnotatorContext;
import org.apache.uima.analysis_engine.annotator.AnnotatorInitializationException;
import org.apache.uima.analysis_engine.annotator.AnnotatorProcessException;
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
 

public class IgnoredTermFilter
        extends DictTermProcessorBase
{
    private static final String PARAM_TERMCODES = "IgnoredTermCodes";
    private Set<String> termCodes;

    public void initialize (AnnotatorContext annotatorContext) throws AnnotatorInitializationException, AnnotatorConfigurationException
    {
        super.initialize(annotatorContext);
        // Process configration parameters
        try
        {
            termCodes = ParameterProcessor.paramArrayToSet(annotatorContext, PARAM_TERMCODES, false);
        }
        catch (Exception e)
        {
            throw new AnnotatorConfigurationException (e);
        }
    }


    protected void processSemclass (Annotation span,
                                    String semClass,
                                    ArrayList<DictTerm> terms)
        throws AnnotatorProcessException
    {
        Iterator<DictTerm> termIter = terms.iterator ();
        
        while (termIter.hasNext ())
        {
            DictTerm term = termIter.next ();

            if ((hasMultipleCommas (term.getCoveredText ())) ||
                (term.getAttributeValue () != null) && (termCodes.contains (term.getAttributeValue ().toLowerCase ())))
            {
                term.setMarked (term.getMarked () | DictTermMarkers.IGNORED_INDICATOR);
            }

        }

    }


    
    private boolean hasMultipleCommas (String coveredText)
    {
        int commaPos = coveredText.indexOf (',');
        return ((commaPos != -1) && (coveredText.lastIndexOf (',') != commaPos));
    }

}
