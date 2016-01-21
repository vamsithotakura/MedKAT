
package org.ohnlp.medkat.POSTaggerAdaptorBase;

import org.apache.uima.analysis_engine.annotator.AnnotatorProcessException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;

public class LeftRightPOSAdapterBase extends POSAdapterBase
{
    public LeftRightPOSAdapterBase (String tokenAnnotationName,
                                 String tokenPOSFeatureName)
    {
        super(tokenAnnotationName, tokenPOSFeatureName);
    }

    protected boolean isCorrectPOS (Annotation ann)
    {
        return ann.getStringValue(m_tokenPOSFeature).equals("JJ");
    }
    
    protected void setCorrectPOS (JCas jcas, Annotation ann)
    {
        ann.setStringValue(m_tokenPOSFeature, "JJ");
    }

	public void processJCas (JCas jcas)
    throws AnnotatorProcessException
	{
        AnnotationIndex tok_ind = (AnnotationIndex)jcas.getAnnotationIndex (m_tokenType);

        for (FSIterator tok_it = tok_ind.iterator (); tok_it.hasNext();) {
        	Annotation tok_ann = (Annotation) tok_it.next();
        	if ((tok_ann.getCoveredText().equalsIgnoreCase("left")) || (tok_ann.getCoveredText().equalsIgnoreCase("right")) ||
                (tok_ann.getCoveredText().equalsIgnoreCase("ascending")) || (tok_ann.getCoveredText().equalsIgnoreCase("descending"))) {
        		//System.err.println("LeftRightPOSAdapter, changing POS tag of TOKEN '" + token.getCoveredText() + "' from: " + token.getStringValue(tokenPOSFeature));
                if (!isCorrectPOS(tok_ann)) {
                    setCorrectPOS(jcas, tok_ann);
                }
        	}
        }
	}
}
