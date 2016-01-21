package org.ohnlp.medkat.taes.textdocparser.subannots;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.uima.analysis_engine.ResultSpecification;
import org.apache.uima.analysis_engine.annotator.AnnotatorConfigurationException;
import org.apache.uima.analysis_engine.annotator.AnnotatorContext;
import org.apache.uima.analysis_engine.annotator.AnnotatorContextException;
import org.apache.uima.analysis_engine.annotator.AnnotatorInitializationException;
import org.apache.uima.analysis_engine.annotator.AnnotatorProcessException;
import org.apache.uima.analysis_engine.annotator.Annotator_ImplBase;
import org.apache.uima.analysis_engine.annotator.TextAnnotator;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.TypeSystem;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.cas.CAS;
import org.apache.uima.jcas.JFSIndexRepository;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.ohnlp.medkat.taes.syntacticUnitFinder.SyntacticUnit;


/**
 * This annotator combines any text within parentheses into a singl sentence
 * This is designed for use with Mayo pathology documents where short phrases
 * which may include periods are parenthesized. It combines them. It will not do
 * what you want if there are two actual sentences within the parentheses.
 * 
 * @author J W Cooper
 * 
 */
public class ParenSentenceCombiner extends Annotator_ImplBase implements
		TextAnnotator {
    private static final String PARAM_SENTENCETYPENAME = "SentenceType";
    private String m_sentenceTypeName;;
    private Type m_sentenceType;
    
	public void initialize (AnnotatorContext cont) throws AnnotatorInitializationException, AnnotatorConfigurationException
    {
        super.initialize(cont);
        try {
            m_sentenceTypeName = (String) getContext().getConfigParameterValue(PARAM_SENTENCETYPENAME);
        }
        catch (AnnotatorContextException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void typeSystemInit(TypeSystem typeSystem)
    throws AnnotatorInitializationException,
    AnnotatorConfigurationException {

        m_sentenceType = typeSystem.getType(m_sentenceTypeName);

}
    public void process (CAS arg0, ResultSpecification arg1) throws AnnotatorProcessException
    {
        // TODO Auto-generated method stub
        
    }
    public void process(JCas jcas, ResultSpecification rspec)
			throws AnnotatorProcessException {

		JFSIndexRepository indexes = jcas.getJFSIndexRepository();
		// TypeSystem typeSystem = jcas.getTypeSystem();
		//sentenceType = typeSystem.getType();

		FSIterator parenIter = indexes.getAnnotationIndex(SyntacticUnit.type).iterator();
		// go through parens and create a subiterator of
		// sentences. If more than one sentence is found combine them.
		while (parenIter.hasNext()) {

		    SyntacticUnit pAnnot = (SyntacticUnit) parenIter.next();
			AnnotationIndex ix = jcas.getAnnotationIndex(m_sentenceType);
			FSIterator itx = ix.iterator();
            Annotation sannot = null;
            if (itx.hasNext()) {
                sannot = (Annotation)itx.next();
            }
			//while (sannot.getBegin() <pAnnot.getBegin() && itx.hasNext ()) {
				//sannot = (SentenceAnnotation)itx.next();
		//	}
			List<Annotation> sArray = new ArrayList<Annotation>();
			
			int count = 0;
			int begin = 100000; 
			int end = 0;
			//count the number of sentences inside the parens
			while(itx.hasNext() ) {
                sannot = (Annotation)itx.next();
                if ((pAnnot.getBegin() <= sannot.getBegin()) && (pAnnot.getEnd() >= sannot.getBegin()) ||
                    (pAnnot.getBegin() <= sannot.getEnd()) && (pAnnot.getEnd() >= sannot.getEnd())) {
                    sArray.add(sannot);
                    if(sannot.getEnd () > end) {
                        end = sannot.getEnd();
                    }
                    if(sannot.getBegin ()< begin) {
                        begin = sannot.getBegin ();
                    }
				count++;
                }
			}
			//if there is more than one, remove them all
			if(count > 0 ){
				Iterator<Annotation> iter = sArray.iterator();
				while(iter.hasNext()) {
					sannot = iter.next();
					sannot.removeFromIndexes();
				}
				//replace with a single annotation
				sannot = (Annotation)jcas.getCas().createAnnotation(m_sentenceType, begin, end);
				sannot.addToIndexes();
			}

		}
        removeTails (indexes, jcas);
	}
    //removes any sentences that end or begin within
    // a parenthesis and go on outside it
    private void removeTails(JFSIndexRepository indexes, JCas jcas) {
        
        TypeSystem typeSystem = jcas.getTypeSystem();
        CAS tcas = (CAS) jcas.getCas();
        Type sentType = typeSystem.getType(Annotation.class.getName());
        AnnotationIndex ix = (AnnotationIndex) (tcas).getAnnotationIndex(sentType);
        FSIterator itx = ix.iterator();
        List<Annotation> sentences = new ArrayList<Annotation>();
        while(itx.hasNext ()) {
            sentences.add ((Annotation)itx.next ());
        }
        //looks like we may have to sort these.
        for(int i=0; i< sentences.size (); i++) {
            for (int j=i+1; j< sentences.size(); j++) {
                Annotation si = sentences.get (i);
                Annotation sj = sentences.get (j);
                if(sj.getBegin () < si.getBegin ()) {
                    sentences.set (i, sj);
                    sentences.set (j, si);
                }
            }
        }
        FSIterator parenIter = indexes.getAnnotationIndex(SyntacticUnit.type).iterator();
        
        while(parenIter.hasNext ()) {
           SyntacticUnit paren = (SyntacticUnit)parenIter.next ();
           Iterator<Annotation> iter = sentences.iterator ();
           boolean joinLast = false;
           Annotation lastSent = null;
           while(iter.hasNext ()) {
               Annotation sent = iter.next ();
               if(joinLast)  {
                   lastSent.setEnd (sent.getEnd ());
                   sent.removeFromIndexes ();
                   joinLast = false;
               }
               if ((paren.getBegin() <= sent.getEnd ()) && (paren.getEnd() >= sent.getEnd ())) {
                   joinLast = true;
                   lastSent = sent;
               }
           }
        }
        
    }
}
