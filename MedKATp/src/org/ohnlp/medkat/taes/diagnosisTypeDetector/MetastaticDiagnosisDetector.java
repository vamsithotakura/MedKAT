package org.ohnlp.medkat.taes.diagnosisTypeDetector;


import org.apache.uima.jcas.JCas;
import org.ohnlp.medkat.taes.conceptMapper.DictTerm;
import org.ohnlp.medkat.taes.conceptMapper.DictTermMarkers;
import org.ohnlp.medkat.taes.negationDetector.NegationDetector;

public class MetastaticDiagnosisDetector
        extends NegationDetector
{
    protected void negateTerm (JCas jcas, DictTerm dT)
    {
        //System.err.println("****Create MetastaticDiagnosis, marked = " + dT.getMarked () + ", text: " + dT.getCoveredText ());
        dT.setMarked (dT.getMarked () | DictTermMarkers.METASTATIC_INDICATOR);
        
        
    }


}
