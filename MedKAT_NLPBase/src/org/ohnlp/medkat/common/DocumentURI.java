package org.ohnlp.medkat.common;

import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.examples.SourceDocumentInformation;
import org.apache.uima.jcas.JCas;

public class DocumentURI
{

    /**
     * returns the URI of the current document. 
     */
    public static String get (JCas jcas)
    {
        AnnotationIndex sdIndex = (AnnotationIndex) jcas.getJFSIndexRepository().getAnnotationIndex(SourceDocumentInformation.type);
        FSIterator sdIter = sdIndex.iterator();
        if (sdIter.hasNext())
        {
            SourceDocumentInformation doc = (SourceDocumentInformation) sdIter.next();
            return doc.getUri();
        }
        return "";
    }


}
