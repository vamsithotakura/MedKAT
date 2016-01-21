package org.ohnlp.medkat.taes.bulletList;

import org.apache.uima.analysis_engine.ResultSpecification;
import org.apache.uima.analysis_engine.annotator.AnnotatorConfigurationException;
import org.apache.uima.analysis_engine.annotator.AnnotatorContext;
import org.apache.uima.analysis_engine.annotator.AnnotatorInitializationException;
import org.apache.uima.analysis_engine.annotator.AnnotatorProcessException;
import org.apache.uima.analysis_engine.annotator.Annotator_ImplBase;
import org.apache.uima.analysis_engine.annotator.JTextAnnotator;
import org.apache.uima.jcas.JCas;

public class BulletListAnnotator extends Annotator_ImplBase implements
		JTextAnnotator {
	private int start, end;

	public void process(JCas jcas, ResultSpecification rspec)
			throws AnnotatorProcessException {
		String buffer = jcas.getDocumentText();
		LineGetter lngt = new LineGetter(buffer);
		String line = lngt.nextLine();
		boolean found = false;
		while (line != null) {
			if (line.trim().startsWith("-") && found) {
				BulletListAnnotation annot = new BulletListAnnotation(jcas);
				annot.setBegin(start);
				annot.setEnd(end);
				annot.addToIndexes();
				line = lngt.nextLine() ;	//get another line
				found = false;
			}
			if (line.trim().startsWith("-")) { // could be a bullet list
				start = lngt.getStart() + line.indexOf("-");
				end = start + line.length()-line.indexOf( "-");
				found = true;
			}
			line = lngt.nextLine();
			if (line != null && found) {

				// bullet list ends when new bullet starts or at blank line
				if (line.trim().startsWith("-") || (line.trim().length() == 0)
						&& found) {
					BulletListAnnotation annot = new BulletListAnnotation(jcas);
					annot.setBegin(start);
					annot.setEnd(end);
					annot.addToIndexes();
					//line = lngt.nextLine() ;	//get another line
					found = false;
				}
				//if the line starts with text and no bullet
				//it is a continuation of the same bullet
				if(found && ! line.trim().startsWith( "-")) {
					end = lngt.getStart() +line.length() ;
				}
			}
		}
	}

	public void initialize(AnnotatorContext context)
			throws AnnotatorInitializationException,
			AnnotatorConfigurationException {
		super.initialize(context);
	}

}
