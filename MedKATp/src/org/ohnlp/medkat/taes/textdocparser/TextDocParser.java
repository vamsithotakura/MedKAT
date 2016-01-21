package org.ohnlp.medkat.taes.textdocparser;

import java.util.ArrayList;
import java.util.List;


import org.apache.uima.analysis_engine.ResultSpecification;
import org.apache.uima.analysis_engine.annotator.AnnotatorConfigurationException;
import org.apache.uima.analysis_engine.annotator.AnnotatorContext;
import org.apache.uima.analysis_engine.annotator.AnnotatorContextException;
import org.apache.uima.analysis_engine.annotator.AnnotatorInitializationException;
import org.apache.uima.analysis_engine.annotator.AnnotatorProcessException;
import org.apache.uima.analysis_engine.annotator.Annotator_ImplBase;
import org.apache.uima.analysis_engine.annotator.JTextAnnotator;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.TypeSystem;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.cas.CAS;
import org.apache.uima.jcas.JFSIndexRepository;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.ohnlp.medkat.taes.bulletList.BulletListAnnotation;
import org.ohnlp.medkat.taes.sectionFinder.DiagnosisAnnotation;
import org.ohnlp.medkat.taes.sectionFinder.GrossDescriptionAnnotation;
import org.ohnlp.medkat.taes.subsectionDetector.MaxSubsectionIndicator;
import org.ohnlp.medkat.taes.subsectionDetector.SubHeading;
import org.ohnlp.medkat.taes.syntacticUnitFinder.SyntacticUnit;
import org.ohnlp.medkat.taes.textdocparser.subannots.NewlineSentenceAnnotation;

/**
 * This annotator takes the subheading annotation under the Diagnosis annotation
 * and splits it at the colon into a subheading and a bulletlist annotation
 * 
 * @author J W Cooper
 * 
 */
public class TextDocParser extends Annotator_ImplBase implements JTextAnnotator {
	public void initialize (AnnotatorContext context) throws AnnotatorInitializationException, AnnotatorConfigurationException
    {
        try
        {
            starters = (String[])context.getConfigParameterValue ("subheadLeaders");
        }
        catch (AnnotatorContextException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static final String COLON = ":";

	private JCas jcas;

	private CAS tcas;

	private List<SyntacticUnit> m_parens;

	private TypeSystem typeSystem;

	//private String fullContent;

	private int subHeadCount;
    private String[] starters={"A.", "B.", "C.","D.", "E.","F."};

	public void process (JCas jcas, ResultSpecification rspec)
            throws AnnotatorProcessException
    {
        this.jcas = jcas;

        // fullContent = jcas.getDocumentText();
        JFSIndexRepository idxs = jcas.getJFSIndexRepository();
        tcas = (CAS)jcas.getCas();
        typeSystem = tcas.getTypeSystem();
        // Type diagType =
        // typeSystem.getType(DiagnosisAnnotation.class.getName());
        m_parens = new ArrayList<SyntacticUnit>();
        AnnotationIndex su_indx = idxs.getAnnotationIndex(SyntacticUnit.type);
        for (FSIterator su_it = su_indx.iterator(); su_it.hasNext();) {
            m_parens.add((SyntacticUnit)su_it.next());
        }
        makeSubheads(jcas, null, idxs, DiagnosisAnnotation.type, false);
        int subMax = subHeadCount;
        makeSubheads(jcas, null, idxs, GrossDescriptionAnnotation.type, true);
        if (subMax < subHeadCount) {
            subMax = subHeadCount;
        }

        FSIndex msi_idx = idxs.getAnnotationIndex(MaxSubsectionIndicator.type);
        FSIterator msi_it = msi_idx.iterator();
        if (msi_it.hasNext()) {
            MaxSubsectionIndicator mxs = (MaxSubsectionIndicator)msi_it.next();
            mxs.setValue(subMax);
        }
        // mxs.setBegin (0);
        // mxs.setEnd (jcas.getDocumentText ().length ());
        // mxs.addToIndexes ();
    }

    private void makeSubheads (JCas jcas, Annotation dAnnot, JFSIndexRepository indexes, int dType, boolean skipBullets)
    {
        subHeadCount = 1; 
        FSIterator iter = indexes.getAnnotationIndex(dType).iterator();
		// get the outer annotation blcok
		if (iter != null && iter.hasNext()) {
			dAnnot = (Annotation) iter.next();
			Type subheadType = typeSystem.getType(SubHeading.class.getName());
			AnnotationIndex ix = (AnnotationIndex) (tcas).getAnnotationIndex(subheadType);
			FSIterator itx = ix.subiterator(dAnnot);
			// look for sub headings within it.
			if (itx != null) {
				if (itx.hasNext()) {
					SubHeading subh = (SubHeading) itx.next();
					String s = subh.getCoveredText();
					s = s.trim();
					// sentenceIteration(subh);
					subh.removeFromIndexes();
				}
			}
			Type sentType = typeSystem.getType(NewlineSentenceAnnotation.class.getName());

			AnnotationIndex sx = (AnnotationIndex) (tcas).getAnnotationIndex(sentType);
			FSIterator isx = sx.subiterator(dAnnot);
			// skip the header line "Final Diagnosis" or the like
			NewlineSentenceAnnotation nls = null;
			if (isx != null) {
				nls = (NewlineSentenceAnnotation) isx.next();
			}
			// look for breaks to make multiple subheads
			int begin = 0;
			int end = 0;
			while (isx != null && isx.hasNext()) {
				nls = (NewlineSentenceAnnotation) isx.next();
				if (begin == 0) {
					begin = nls.getBegin();
				}

				String s = nls.getCoveredText();
				if (s.trim().length() < 1) {
					end = nls.getBegin() - 1;
					SubHeading subh = new SubHeading(jcas);
					subh.setBegin(begin);
					subh.setEnd(end);

					if (end >= begin) {
		                //System.err.println ("TEXTDOCPARSER1: Making subSection, #:" + subHeadCount + ", begin: " + begin + ", end: " + end);
						subh.setSubSectionNumber(subHeadCount++);
						subh.addToIndexes();
					}
					begin = 0; // flag to catch next one
					end = 0;
				}

			}
			if (begin > 0 && end == 0) {
				end = nls.getEnd();
				SubHeading subh = new SubHeading(jcas);
				subh.setBegin(begin);
				subh.setEnd(end);
                //System.err.println ("TEXTDOCPARSER2: Making subSection, #:" + subHeadCount + ", begin: " + begin + ", end: " + end);

				subh.setSubSectionNumber(subHeadCount++);
				subh.addToIndexes();
			}
		}
		Type subheadType = typeSystem.getType(SubHeading.class.getName());
		AnnotationIndex ix = (AnnotationIndex) (tcas)
				.getAnnotationIndex(subheadType);
        if (dAnnot != null){
            FSIterator itx = ix.subiterator(dAnnot);
            // look for sub headings within it, but not in GrossDescriptions
            if(! skipBullets) {
            while (itx != null && itx.hasNext()) {
                SubHeading subh = (SubHeading) itx.next();
                sentenceIteration(subh);
            }
            }
        }
        //try also to check for sections starting with A., B. etc.
        /* this should really be done in subsectionDetector, but for some reason, it is done here */
        if(subHeadCount == 2) {
            checkAlphs(dAnnot);
        }/* */
        
        
    }
    
    
    protected void checkAlphs(Annotation dAnnot) {
    
        Type sentType = typeSystem.getType(NewlineSentenceAnnotation.class
            .getName());

        AnnotationIndex sx = (AnnotationIndex) (tcas)
            .getAnnotationIndex(sentType);
        FSIterator isx = sx.subiterator(dAnnot);
//      skip the header line "Final Diagnosis" or the like
        NewlineSentenceAnnotation nls = null;
        if (isx != null) {
            nls = (NewlineSentenceAnnotation) isx.next();
        }
        int sIndex=0;
        boolean found = true;
        while (isx.hasNext ()) {
            nls = (NewlineSentenceAnnotation) isx.next();
            String s = nls.getCoveredText ().trim (); 
            if(s.length () >0) {
                found = found && s.startsWith (starters[sIndex++]);
            }
        }
        if(found && sIndex > 1) {
            Type subheadType = typeSystem.getType(SubHeading.class.getName());
            AnnotationIndex ix = (AnnotationIndex) (tcas).getAnnotationIndex(subheadType);
            FSIterator itx = ix.subiterator(dAnnot);
            // look for sub headings within it.
            if (itx != null) {
                if (itx.hasNext()) {
                    SubHeading subh = (SubHeading) itx.next();
                    subh.removeFromIndexes();
                }
            }
            isx = sx.subiterator(dAnnot);
//          skip the header line "Final Diagnosis" or the like
            nls = null;
            if (isx != null) {
                nls = (NewlineSentenceAnnotation) isx.next();
            }
            
            int subCount = 1;
            while (isx.hasNext ()) {
                nls = (NewlineSentenceAnnotation) isx.next();
                SubHeading subh = new SubHeading(jcas);
                subh.setBegin (nls.getBegin ());
                subh.setEnd (nls.getEnd ());
                //System.err.println ("TEXTDOCPARSER3: Making subSection, #:" + subHeadCount + ", begin: " + subh.getBegin () + ", end: " + subh.getEnd ());
                subh.setSubSectionNumber (subCount++);
                subh.addToIndexes ();
            }
        }
    }
    
    
	private void sentenceIteration(SubHeading subh) {
		Type sentenceType = typeSystem.getType(NewlineSentenceAnnotation.class
				.getName());
		AnnotationIndex sIx = (AnnotationIndex) (tcas)
				.getAnnotationIndex(sentenceType);
		FSIterator itx = sIx.subiterator(subh);

		boolean found = false;
		if (itx != null) {
			while (itx.hasNext() && !found) {
				NewlineSentenceAnnotation sent = (NewlineSentenceAnnotation) itx
						.next();
				String content = sent.getCoveredText();
				int subStart = sent.getBegin();
				int index = content.indexOf(COLON);

				while (!found && index >= 0) {
					found = (!inParens(index + subStart));
					if (!found) {
						index = content.indexOf(COLON, ++index);
					}
				}
				if (index > 0 && !content.startsWith("\t")) {
					subh.setPrefix(content.substring(0, index + 1));
					subh.setContent(content.substring(index + 1));
					String bull = content.substring(index + 1);
					bull = bull.trim();
					if (bull.length() > 0) {
						int start = content.indexOf(bull) + sent.getBegin();
						BulletListAnnotation ba = new BulletListAnnotation(jcas);
						ba.setBegin(start);
						ba.setEnd(bull.length() + start);
						ba.addToIndexes();

					}
				}
				if (content.startsWith("\t")) {
					BulletListAnnotation ba = new BulletListAnnotation(jcas);
					ba.setBegin(subStart);
					ba.setEnd(content.length() + subStart);
					ba.addToIndexes();
				}
			}
			if (itx != null && itx.hasNext()) {
				found = false;
			}
		}
		if (itx != null) {
			while (itx.hasNext()) {
				NewlineSentenceAnnotation sent = (NewlineSentenceAnnotation) itx
						.next();
				BulletListAnnotation ba = new BulletListAnnotation(jcas);
				ba.setBegin(sent.getBegin());
				ba.setEnd(sent.getEnd());
				ba.addToIndexes();
			}
		}

	}

	private boolean inParens (int posn)
	{
		for (SyntacticUnit su_ann : m_parens) {
		    if (su_ann.getBegin() <= posn && posn < su_ann.getEnd()) {
		        return true;
		    }
		}
		return false;
	}
	/**
	 * private int getBullets(int begin, int sBegin, String sbCover) { boolean
	 * found = false; int end = 0; end = sbCover.indexOf("\n", begin + 1); while
	 * (!found && end > 0) { String sentence = sbCover.substring(begin, end);
	 * found = sentence.indexOf(COLON) > 0 && !sentence.startsWith("\t"); if
	 * (!found) { sentence = sentence.trim(); if (sentence.length() > 0) {
	 * BulletListAnnotation bull = new BulletListAnnotation(jcas);
	 * bull.setBegin(begin + sBegin); end = sbCover.indexOf("\n", begin + 1);
	 * bull.setEnd(end + sBegin); bull.addToIndexes(); begin = end + 1; end =
	 * sbCover.indexOf("\n", end + 1); } else { begin = end + 1; } end =
	 * sbCover.indexOf("\n", begin); } } if (!found) { return begin - 1; } else {
	 * return begin; } }
	 */
}