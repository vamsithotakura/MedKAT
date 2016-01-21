
package org.ohnlp.medkat.POSTaggerAdaptorBase;

import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.uima.analysis_engine.annotator.AnnotatorProcessException;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;

public class LymphNodesPOSAdapterBase extends POSAdapterBase
{
    private final static Pattern m_lnPattern = Pattern.compile("lymph\\s+(nodes)", Pattern.CASE_INSENSITIVE);
    
    public LymphNodesPOSAdapterBase (String tokenAnnotationName,
                                     String tokenPOSFeatureName)
    {
        super(tokenAnnotationName, tokenPOSFeatureName);
    }

    protected boolean isCorrectPOS (Annotation ann)
    {
        return !ann.getStringValue(m_tokenPOSFeature).equals("VBZ");
    }
    
    protected void setCorrectPOS (JCas jcas, Annotation ann)
    {
        ann.setStringValue(m_tokenPOSFeature, "NNS");
    }
    
    public void processJCas (JCas jcas)
    throws AnnotatorProcessException
	{
        try {
            int tokenTypeId = Class.forName(m_tokenAnnotationName).getField("typeIndexID").getInt(null);
            AnnotationIndex ta_ind = (AnnotationIndex)jcas.getJFSIndexRepository().getAnnotationIndex(tokenTypeId);
            
            String docText = jcas.getDocumentText();
            Matcher m = m_lnPattern.matcher(docText);
            int pos = 0;
            while (pos < docText.length() && m.find(pos)) {
                Annotation dummy = new Annotation(jcas, m.start(1), m.end(1));
                pos = m.end(1);
                dummy.addToIndexes();
                Iterator<?> it = ta_ind.subiterator(dummy);
                dummy.removeFromIndexes();
                if (!it.hasNext()) {
                    continue;
                }
                Annotation tok_ann = (Annotation)it.next();
                if (!isCorrectPOS(tok_ann)) {
                    setCorrectPOS(jcas, tok_ann);
                }
            }
        }
        catch (Exception e) {
            throw new AnnotatorProcessException(e);
        }
	}
}
