package org.ohnlp.medkat.taes.textdocparser.subannots;


import org.apache.uima.analysis_engine.ResultSpecification;
import org.apache.uima.analysis_engine.annotator.AnnotatorProcessException;
import org.apache.uima.analysis_engine.annotator.Annotator_ImplBase;
import org.apache.uima.analysis_engine.annotator.JTextAnnotator;
import org.apache.uima.jcas.JCas;
import org.ohnlp.medkat.taes.textdocparser.subannots.NewlineSentenceAnnotation;

public class NewLineSentenceAnnotator extends Annotator_ImplBase implements
		JTextAnnotator {

	public void process(JCas jcas, ResultSpecification rSpec)
			throws AnnotatorProcessException {
	int begin =0 ;
	String contents = jcas.getDocumentText();
	int index = contents.indexOf("\n");
	while(index >= 0) {
//		if(index-begin>1) {
			NewlineSentenceAnnotation nla = new NewlineSentenceAnnotation(jcas);
			nla.setBegin(begin);
			nla.setEnd(index);
			nla.addToIndexes();
	//	}
		begin = index+1;
		index = contents.indexOf("\n", begin);		
	}

	}

}
