package org.ohnlp.medkat.taes.syntacticUnitFinder;


import org.apache.uima.analysis_engine.ResultSpecification;
import org.apache.uima.analysis_engine.annotator.AnnotatorConfigurationException;
import org.apache.uima.analysis_engine.annotator.AnnotatorContext;
import org.apache.uima.analysis_engine.annotator.AnnotatorContextException;
import org.apache.uima.analysis_engine.annotator.AnnotatorInitializationException;
import org.apache.uima.analysis_engine.annotator.AnnotatorProcessException;
import org.apache.uima.analysis_engine.annotator.JTextAnnotator_ImplBase;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.TypeSystem;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;


public class SentenceShortener extends JTextAnnotator_ImplBase
{
    private String m_sentClass;
    private  Type m_sentenceType;
    private final static String PARAM_SENTENCETYPE = "SentenceType";
    
    @Override
    public void initialize (AnnotatorContext context)
            throws AnnotatorInitializationException,
            AnnotatorConfigurationException
    {
        super.initialize(context);
        try {
            m_sentClass = context.getConfigParameterValue(PARAM_SENTENCETYPE).toString();
        }
        catch (AnnotatorContextException e) {
            throw new AnnotatorConfigurationException(e);  
        }
    }

    @Override
    public void typeSystemInit (TypeSystem typeSystem)
        throws AnnotatorInitializationException, AnnotatorConfigurationException 
    {
        m_sentenceType = typeSystem.getType(m_sentClass);
    }

    public void process (JCas cas, ResultSpecification arg1)
            throws AnnotatorProcessException
    {
        for (FSIterator iter = cas.getAnnotationIndex(m_sentenceType).iterator(); iter.hasNext();) {
            Annotation sent = (Annotation)iter.next();
            int begin = sent.getBegin();
            String text = sent.getCoveredText();
            String newText = text.trim();
            if (newText.length() < text.length()) {
                sent.setEnd(begin + newText.length());
            }
        }
    }
}
