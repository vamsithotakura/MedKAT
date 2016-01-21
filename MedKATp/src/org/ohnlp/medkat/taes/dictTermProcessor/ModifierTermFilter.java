package org.ohnlp.medkat.taes.dictTermProcessor;

import java.util.ArrayList;


import org.apache.uima.analysis_engine.annotator.AnnotatorProcessException;
import org.apache.uima.jcas.tcas.Annotation;
import org.ohnlp.medkat.taes.conceptMapper.DictTerm;
import org.ohnlp.medkat.taes.conceptMapper.DictTermMarkers;

 
public class ModifierTermFilter
extends DictTermProcessorBase
{

    protected void processSemclass (Annotation span,
                                    String semClass,
                                    ArrayList<DictTerm> terms)
        throws AnnotatorProcessException
    {
        
        // if more than one term in span, and the span has no commas, mark all but last one as a modifier
        if ((terms.size () > 1) && (span.getCoveredText ().indexOf (",") == -1))
        {
            int penultimateTermIndex = terms.size () - 1;
            DictTerm penultimateTerm = (DictTerm) terms.get (penultimateTermIndex);
            //System.err.println ("==> MODIFIED  term: " + penultimateTerm.getCoveredText() + ", marker: " + penultimateTerm.getMarked());
            for (int termNum = 0; termNum < penultimateTermIndex; termNum++)
            {
                DictTerm term = (DictTerm) terms.get (termNum);
                if ((term.getDictCanon().equals(penultimateTerm.getDictCanon())) && (term.getBegin () == penultimateTerm.getBegin()) && (term.getEnd () == penultimateTerm.getEnd()))
                {
                    // do nothing if term and penultimate term are the same
                }
                else
                {
                    //System.err.println ("Marking as MODIFIER, term: " + term.getCoveredText() + ", marker: " + term.getMarked());
                    term.setMarked (term.getMarked () | DictTermMarkers.MODIFIER_INDICATOR);
                }
            }
        }                
    }        
    
}
