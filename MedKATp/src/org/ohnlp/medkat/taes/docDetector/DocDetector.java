package org.ohnlp.medkat.taes.docDetector;



import org.apache.uima.analysis_engine.ResultSpecification;
import org.apache.uima.analysis_engine.annotator.AnnotatorConfigurationException;
import org.apache.uima.analysis_engine.annotator.AnnotatorContext;
import org.apache.uima.analysis_engine.annotator.AnnotatorContextException;
import org.apache.uima.analysis_engine.annotator.AnnotatorInitializationException;
import org.apache.uima.analysis_engine.annotator.AnnotatorProcessException;
import org.apache.uima.analysis_engine.annotator.Annotator_ImplBase;
import org.apache.uima.analysis_engine.annotator.JTextAnnotator;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.jcas.JFSIndexRepository;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.ohnlp.medkat.taes.sectionFinder.PhysicianAnnotation;

public class DocDetector extends Annotator_ImplBase implements JTextAnnotator {
	static public String DOC_NAMES = "docNames";

	private int pBegin, pEnd;

	private String[] docs;

	public void initialize(AnnotatorContext context)
			throws AnnotatorInitializationException,
			AnnotatorConfigurationException {
		try {
			docs = (String[]) context.getConfigParameterValue(DOC_NAMES);
		} catch (AnnotatorContextException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void process(JCas jcas, ResultSpecification rspec)
			throws AnnotatorProcessException {
		JFSIndexRepository indexes = jcas.getJFSIndexRepository();
		// A doc already annotated as the physician is not the pathologist
		FSIterator phys = indexes.getAnnotationIndex(PhysicianAnnotation.type).iterator();
		if (phys.hasNext()) {
			Annotation p = (Annotation) phys.next();
			pBegin = p.getBegin();
			pEnd = p.getEnd();
		}
		String contents = jcas.getDocumentText();
		for (int i = 0; i < docs.length; i++) {
			String doc = docs[i];
			int index = contents.indexOf(doc);
			while (index > 0) {
				if (index > pEnd || index < pBegin && index > 0) {
					// find beginning of line
					int start = index - 1;
					int crindex = 100000; // just a large number
					while (crindex > index) {
						crindex = contents.indexOf("\n", start);
						start--;
					}
					start++;
					int end = index + doc.length();
					String mdString = contents.substring(start, end).trim();
					int newstart = contents.indexOf(mdString);
					end = start + mdString.length();
					while(newstart < start) {
						newstart = contents.indexOf(mdString, newstart+1);
					}
					start = newstart;
					end = newstart + mdString.length();
					if (mdString.startsWith("(")) {
						start++;
					}
					DocAnnotation dannot = new DocAnnotation(jcas);
					dannot.setBegin(start);
					dannot.setEnd(end);
					dannot.addToIndexes();

				}
				index = contents.indexOf(doc, index + doc.length());
			}

		}
	}

}
