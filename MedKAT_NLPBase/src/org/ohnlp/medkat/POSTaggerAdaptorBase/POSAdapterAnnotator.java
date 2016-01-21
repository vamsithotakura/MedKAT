
package org.ohnlp.medkat.POSTaggerAdaptorBase;

import java.util.ArrayList;
import java.util.List;

import org.apache.uima.analysis_engine.ResultSpecification;
import org.apache.uima.analysis_engine.annotator.AnnotatorConfigurationException;
import org.apache.uima.analysis_engine.annotator.AnnotatorContext;
import org.apache.uima.analysis_engine.annotator.AnnotatorContextException;
import org.apache.uima.analysis_engine.annotator.AnnotatorInitializationException;
import org.apache.uima.analysis_engine.annotator.AnnotatorProcessException;
import org.apache.uima.analysis_engine.annotator.JTextAnnotator_ImplBase;
import org.apache.uima.cas.TypeSystem;
import org.apache.uima.jcas.JCas;

public class POSAdapterAnnotator extends JTextAnnotator_ImplBase
{
    final public static String PARAM_POSADAPTERCLASSNAMES = "POSAdapterClassNames";  
    List<POSAdapterBase> m_adapters;
    
    @Override
    public void initialize (AnnotatorContext ac)
    throws AnnotatorInitializationException, AnnotatorConfigurationException
    {
        super.initialize(ac);
        
        m_adapters = new ArrayList<POSAdapterBase>();
        
        try {
            String[] adapterClassNames = (String[])ac.getConfigParameterValue(PARAM_POSADAPTERCLASSNAMES);
            for (String adapterClassName : adapterClassNames) {
                Class<?> cls = Class.forName(adapterClassName);
                Object adapter = cls.newInstance();
                m_adapters.add((POSAdapterBase)adapter);
            }
        }
        catch (AnnotatorContextException e) {
            throw new AnnotatorConfigurationException(e);
        }
        catch (Exception e) {
            throw new AnnotatorInitializationException(e);
        }
    }

    @Override
    public void typeSystemInit (TypeSystem typeSystem)
    throws AnnotatorInitializationException, AnnotatorConfigurationException
    {
        super.typeSystemInit(typeSystem);
        
        for (POSAdapterBase adapter : m_adapters) {
            adapter.typeSystemInit(typeSystem);
        }
    }
    
    public void process (JCas jcas, ResultSpecification resultSpec)
    throws AnnotatorProcessException
    {
        for (POSAdapterBase adapter : m_adapters) {
            adapter.processJCas(jcas);
        }
    }
}
