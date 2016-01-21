
package org.ohnlp.medkat.POSTaggerAdaptorBase;

import org.apache.uima.analysis_engine.annotator.AnnotatorProcessException;
import org.apache.uima.cas.Feature;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.TypeSystem;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;

abstract public class POSAdapterBase
{
    protected String  m_tokenAnnotationName;
    protected String  m_tokenPOSFeatureName;

    protected Type    m_tokenType;
    protected Feature m_tokenPOSFeature;
    
    POSAdapterBase (String tokenAnnotationName,
                    String tokenPOSFeatureName)
    {
        m_tokenAnnotationName = tokenAnnotationName;
        m_tokenPOSFeatureName = tokenPOSFeatureName;
    }

    public void typeSystemInit(TypeSystem typeSystem)
    {
        m_tokenType = typeSystem.getType(m_tokenAnnotationName);
        m_tokenPOSFeature = m_tokenType.getFeatureByBaseName(m_tokenPOSFeatureName);
    }
    
    abstract protected boolean isCorrectPOS (Annotation ann);
    abstract void setCorrectPOS (JCas jcas, Annotation ann);
    abstract void processJCas (JCas jcas) throws AnnotatorProcessException;
}
