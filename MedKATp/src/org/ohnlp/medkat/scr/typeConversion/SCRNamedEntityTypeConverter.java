package org.ohnlp.medkat.scr.typeConversion;

import java.util.Iterator;


import org.apache.uima.analysis_engine.ResultSpecification;
import org.apache.uima.analysis_engine.annotator.AnnotatorProcessException;
import org.apache.uima.analysis_engine.annotator.JTextAnnotator_ImplBase;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.jcas.JFSIndexRepository;
import org.apache.uima.jcas.JCas;
import org.ohnlp.medkat.taes.conceptMapper.DictTerm;
import org.ohnlp.medkat.taes.coreferencer.CoreferringDiagnoses;
import org.ohnlp.medkat.taes.coreferencer.CoreferringSites;

public class SCRNamedEntityTypeConverter extends JTextAnnotator_ImplBase
{

    public void process (JCas jcas, ResultSpecification resultSpecification)
            throws AnnotatorProcessException
    {
        JFSIndexRepository indexes = jcas.getJFSIndexRepository();

        AnnotationIndex ind =
            (AnnotationIndex)indexes.getAnnotationIndex(CoreferringSites.typeIndexID);
        for (Iterator<?> it = ind.iterator(); it.hasNext();) {
            MedKATTypeConverter.convert(jcas, (CoreferringSites)it.next());
        }
        
        ind = (AnnotationIndex)indexes.getAnnotationIndex(CoreferringDiagnoses.typeIndexID);
        for (Iterator<?> it = ind.iterator(); it.hasNext();) {
            MedKATTypeConverter.convert(jcas, (CoreferringDiagnoses)it.next());
        }
        
        ind = (AnnotationIndex)indexes.getAnnotationIndex(DictTerm.typeIndexID);
        for (Iterator<?> it = ind.iterator(); it.hasNext();) {
            MedKATTypeConverter.convertNE(jcas, (DictTerm)it.next());
        }
        
    }
}
