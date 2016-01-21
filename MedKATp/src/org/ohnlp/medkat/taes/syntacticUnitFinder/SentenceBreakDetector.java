package org.ohnlp.medkat.taes.syntacticUnitFinder;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

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
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.cas.CAS;

/**
 * This annotator detects sentences that cross section boundaries , and breaks //
 * those sentences // into smaller ones within each section. It deletes the
 * original annotation and // leaves // only the new ones. // uses only
 * annotations specified in the descriptor file.
 */

public class SentenceBreakDetector extends JTextAnnotator_ImplBase
{
	// private JCas jcas;


    private Type sentenceType;
    private Type[] sectTypes;
    private Type subSectionType;

    private String sentClassName;
	private String[] sectClassName;
	private String subSectClassName;

	private static final String SENTENCE_CLASS = "SentenceClass";
	private static final String SECTION_CLASSES = "SectionClasses";
	private static final String SUBSECTION_CLASS = "SubsectionClass";


	private boolean overHang;

	private Annotation annot;

	/** gets the list of annotations to break on */
    @Override
    public void initialize(AnnotatorContext arg0)
            throws AnnotatorInitializationException,
            AnnotatorConfigurationException {
        super.initialize(arg0);

        overHang = false;
        try {
            sentClassName = (String) getContext().getConfigParameterValue(
                    SENTENCE_CLASS);
            sectClassName = (String[]) getContext().getConfigParameterValue(
                    SECTION_CLASSES);
            subSectClassName = (String) getContext().getConfigParameterValue(
                    SUBSECTION_CLASS);
        }
        catch (AnnotatorContextException e) {
            e.printStackTrace();
        }
    }

	public void typeSystemInit (TypeSystem typeSystem)
	throws AnnotatorInitializationException, AnnotatorConfigurationException
	{
		sentenceType = typeSystem.getType(sentClassName);
		subSectionType = typeSystem.getType(subSectClassName);
        sectTypes = new Type[sectClassName.length];
		for (int i = 0; i < sectTypes.length; i++) {
			sectTypes[i] = typeSystem.getType(sectClassName[i]);
		}
	}

	/**
	 * entry point for each document. Splits each sentence if it overlaps a
	 * section or subsection
	 */
    public void process (JCas jcas, ResultSpecification resultSpec)
    throws AnnotatorProcessException
    {
		// printAnnotations(tcas, sentenceType );
		for (int j = 0; j < sectTypes.length; j++) {
			if (sectTypes[j] != null) {
				FSIterator sectIterator = jcas.getAnnotationIndex(sectTypes[j]).iterator();
				// printAnnotations(tcas, sectTypes[j]);
				if (sectIterator.hasNext()) {
					Annotation annot = (Annotation) sectIterator.next();

					FSIterator sentIterator = jcas.getAnnotationIndex(sentenceType).iterator();

					Vector<Annotation> sList = new Vector<Annotation>();
					while (sentIterator.hasNext()) {
						Annotation sent = (Annotation) sentIterator.next();
						// String s = sent.getCoveredText();
						sList.addElement(sent);
					}
					Iterator<Annotation> sIter = sList.iterator();

					while (sIter.hasNext()) {

						Annotation sentence = sIter.next();
						if (overHang) {
							if (annot.getEnd() < sentence.getEnd()) {
								overHang = false;
							}
						}
						if (!overHang) {
							splitSentence(sentence, sectTypes[j], jcas);
						}
						else {

						}
					}
				}
			}
		}
		chopSubsections(jcas);
	}

	// private void printAnnotations(CAS tcas, Type typ) {
	// int i;
	// Iterator sentIterator;
	// System.out.println("---annotations---" + typ.toString());
	// print out current sentences
	// sentIterator = tcas.getAnnotationIndex(typ).iterator();
	// i = 0;

	// while (sentIterator.hasNext()) {
	// Annotation sannot = (Annotation) sentIterator.next();
	// System.out.println(i++ + "::" + sannot.getBegin() + " "
	// + sannot.getEnd() + "::" + sannot.getCoveredText());
	// }
	// }
	/** Splits sentence as described below */
	private void splitSentence(Annotation sentence, Type type, JCas jcas) {
		// int k;
	
		 //String s1 = sentence.getCoveredText() ;
		HashMap<Integer, Annotation> annotMap = new HashMap<Integer, Annotation>(); // prevents duplicate annotations
		FSIterator sectIterator = jcas.getAnnotationIndex(type).iterator();
		boolean found = false;
		Vector<Annotation> annots = new Vector<Annotation>();
		annot = null;
		while (sectIterator.hasNext() && !found) {
			annot = (Annotation) sectIterator.next();
			//System.out.println( annot.getBegin()+" "+annot.getEnd());
	//		String s2= annot.getCoveredText();
	//		System.out.println(s2);
			found = annot.getBegin() >= sentence.getBegin()
					&& annot.getBegin() <= sentence.getEnd();
		}
		if (found) {
			Integer begin = new Integer(annot.getBegin());
			if (!annotMap.containsKey(begin)) {
				annotMap.put(begin, annot);
				annots.addElement(annot);
			}

		}

		boolean past = false;
		// add them into list until they pass the end of the sentence
		while (sectIterator.hasNext() && !past) {
			annot = (Annotation) sectIterator.next();
			past = annot.getEnd() > sentence.getEnd();
			if (!past || annot.getBegin() < sentence.getEnd()) {
				Integer begin = new Integer(annot.getBegin());
				if (!annotMap.containsKey(begin)) {
					annotMap.put(begin, annot);
					annots.addElement(annot);
				}
			}
		}

		// now split the sentence up

		Iterator<Annotation> iter = annots.iterator();
		while (iter.hasNext()) {
			annot = iter.next();
			//String a = annot.getCoveredText();

			// change annotation bound if it hangs over a sentence because
			// of line break detection issues
			// if (i > 0)
			// {
			// while ((i > 0) && (Character.isWhitespace(a.charAt(i))))
			// {
			// i -= 1;
			// }
			// a = a.substring(0, i + 1);
			// annot.setEnd(annot.getBegin() + a.length());
			// System.err.println("annot.setEnd(" + annot.getBegin() + "+" +
			// a.length() + " = " + (annot.getBegin() + a.length()));
			// }

			/**
			 * For each annotation and sentence pair, there are 4 cases 1.
			 * Annotation starts within sentence hangs off right end of
			 * sentence. 2. Annotation is entirely within sentence (or identical
			 * to it 3. Annotation begins before sentence and finishes in middle
			 * 4. Annotation is larger than sentence
			 */

			boolean done = false; // so only 1 case is handled
			// Case 4 - annotation larger than sentence
			// do nothing at all
			if (annot.getBegin() <= sentence.getBegin()
					&& sentence.getEnd() <= annot.getEnd()) {
				done = true;
			}

			// Case 1- Annotation starts within sentence and is longer than the
			// sentence
			if (sentence.getBegin() <= annot.getBegin()
					&& annot.getEnd() > sentence.getEnd() && !done) {
				// create sentence if any part before the subhead
				
				int bound = annot.getBegin()-1;
				//a=annot.getCoveredText();
				if(annot.getCoveredText().startsWith("\n") || annot.getCoveredText().startsWith("\r") ) {
					bound++;
				}
				
				if(annot.getCoveredText().trim().length()>0) {
					createSentence(jcas.getCas(), sentence.getBegin(), bound);
				

				// make current sentence shorter to start at annotation
				shortenSentence(sentence, bound+1);
				done = true; // case complete
				}
			}
			// Case 2 - Annotation completely within sentence
			// including identical
			if (sentence.getBegin() <= annot.getBegin()
					&& sentence.getEnd() >= annot.getEnd() && !done) {
				// create new sentence to the left of the annotation if they are
				// not equal
				if (sentence.getBegin() < annot.getBegin()) {
					createSentence(jcas.getCas(), sentence.getBegin(),
							annot.getBegin() - 1);

				}
				// create a sentence equal to the annotation
				createSentence(jcas.getCas(), annot.getBegin(), annot.getEnd());

				// revise existing sentence to start after annotation
				// or delete it if now of zero length
				if (annot.getEnd() <= sentence.getEnd()) {
					int start = annot.getEnd();// + 1;
					int end = sentence.getEnd();
					if (start < end) {
						shortenSentence(sentence, start);
					} else {
						sentence.removeFromIndexes();
					}
				}
				done = true;
			}
			// case 3. Annotation begins before sentence and ends within
			// sentence
			if (annot.getBegin() < sentence.getBegin()
					&& annot.getEnd() < sentence.getEnd() && !done) {
				// create new sentence that starts where old one does
				// but ends at end of annotation

				createSentence(jcas.getCas(), sentence.getBegin(), annot.getEnd());

				// move old sentence start after annotation
				shortenSentence(sentence, annot.getEnd() + 1);
				done = true;
			}

		}

	}

	/** Chop DignosisSubSubsection into sentences by line */
	private void chopSubsections(JCas jcas) {
		
		String text = jcas.getDocumentText();
		int index = text.indexOf("Accession");
		if (index >0 ){
		text = text.substring(index);
		index = text.indexOf("\n");
		text = text.substring(0, index);
		//System.out.println(text);
		}
		FSIterator iter = jcas.getAnnotationIndex(subSectionType ).iterator();
		// look for subsub sections that are made up of one-line sentences
		// occurs in synoptic reports
		while(iter.hasNext()) {
			Annotation dbannot = (Annotation) iter.next();
		//	System.out.println("annot:"+dbannot.getCoveredText());
			//look for sentences within that span
			//there will usually only be one
			FSIterator siter = jcas.getAnnotationIndex(sentenceType).iterator();
			boolean found = false;
			Annotation sent = null;
			while (siter.hasNext() && !found) {
				sent = (Annotation) siter.next();
				found = sent.getBegin() >= dbannot.getBegin()
						&& sent.getEnd() <= dbannot.getEnd();
			}
			// if we found a sentence within a subSubsection, cut it into pieces
			if (found && sent != null) {
				String fullSent = sent.getCoveredText();
				//System.out.println("full:"+fullSent);
				int offset = sent.getBegin();
				int start = 0;
				int nl = fullSent.indexOf("\n");
				while (nl > 0) {
					if (nl > start) {
						// String newSent = fullSent.substring(start, nl);
						
						if (start == 0) {
							sent.setEnd(nl + offset);
						} else {
							createSentence(jcas.getCas(), start + offset, nl + offset);
						}
					}
					start = nl + 1;
					nl = fullSent.indexOf("\n", start);
				}
				if (start < fullSent.length()&& start >0) {
					int st = start + offset;
					int end = offset + fullSent.length();
					
					createSentence(jcas.getCas(), st, end);
		
				}
			}
		}
//		//now print them out
//		FSIterator siter = tcas.getAnnotationIndex(sentenceType).iterator();
//		int count = 0;
//		while(siter.hasNext()) {
//			Annotation annot = (Annotation)siter.next();
//			//System.out.println(count++ +":"+annot.getBegin()+" "+annot.getEnd()+ " "+ annot.getCoveredText());
//		}
	}
	

	/** creates a new smaller sentence */
	private void createSentence(CAS cas, int start, int end) {
		if (start < end) {
			AnnotationFS fs1 = cas.createAnnotation(sentenceType, start, end);
			String s = fs1.getCoveredText();
			if (s.trim().length() > 0) {
			    cas.getIndexRepository().addFS(fs1);
			}
		}
		else {
			// System.out.println("illegal sentence");
		}
	}

	/** shortens an existing sentence */
	private void shortenSentence(Annotation sent, int newStart) {
		// String s = sent.getCoveredText();
		if (newStart < sent.getEnd()) {
			sent.setBegin(newStart);
			// s = sent.getCoveredText();
		}
		else {
			sent.removeFromIndexes();// *****trial
			// System.out.println("illegal sentence bound");
		}
	}

	public SentenceBreakDetector() {
		super();

	}

}
