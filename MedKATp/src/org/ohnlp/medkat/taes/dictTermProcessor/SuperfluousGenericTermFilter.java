package org.ohnlp.medkat.taes.dictTermProcessor;
    
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
    

import org.apache.uima.analysis_engine.annotator.AnnotatorConfigurationException;
import org.apache.uima.analysis_engine.annotator.AnnotatorContext;
import org.apache.uima.analysis_engine.annotator.AnnotatorInitializationException;
import org.apache.uima.analysis_engine.annotator.AnnotatorProcessException;
import org.apache.uima.jcas.tcas.Annotation;
import org.ohnlp.medkat.common.ParameterProcessor;
import org.ohnlp.medkat.taes.conceptMapper.DictTerm;
import org.ohnlp.medkat.taes.conceptMapper.DictTermMarkers;

public class SuperfluousGenericTermFilter
        extends DictTermProcessorBase
{

    private static final String PARAM_TERMCODES = "GenericTermCodes";
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


    protected void processSemclass (Annotation span, String semClass, ArrayList<DictTerm> terms)
    throws AnnotatorProcessException
    {
        if (semClass.equals ("Diagnosis"))
        {
            Collections.sort (terms, new NumTokensComparator ());
            removeGenericTerms (terms);
        }
    }


    private void removeGenericTerms (ArrayList<DictTerm> terms)
    throws AnnotatorProcessException
    {
        for (int i = 0; i < terms.size (); i++)
        {
            DictTerm current = terms.get (i);
            
            if ((current != null) && (! isMeaningfulDiagnosis (current)))
            {
                //System.err.println("! isMeaningfulDiagnosis: " + current.getDictCanon ());
                int currentBegin = current.getBegin ();
                int currentEnd = current.getEnd ();

                boolean containsMeaningfulDiagnoses = false;
                    
                for (int j = i + 1; j < terms.size (); j++)
                {
                    DictTerm toCompare = terms.get (j);
                    if ((toCompare != null) &&
                        subsumes (currentBegin, currentEnd, toCompare.getBegin (), toCompare.getEnd ()) &&
                        isMeaningfulDiagnosis (toCompare))
                    {
                        //System.err.println("containsMeaningfulDiagnoses: " + toCompare.getDictCanon ());
                        containsMeaningfulDiagnoses = true;
                        break;
                    }
                }
                if (containsMeaningfulDiagnoses)
                {
                    //System.err.println ("containsMeaningfulDiagnoses term: " + current.getCoveredText ());
                    current.setMarked (current.getMarked () | DictTermMarkers.SUPERFLUOUS_INDICATOR);
                }
            }
        }
    }

    private boolean isMeaningfulDiagnosis (DictTerm toCompare)
    {       
        if (termCodes.contains (toCompare.getAttributeValue ()))
        {
            return false;
        }
        return true;
    }

}
