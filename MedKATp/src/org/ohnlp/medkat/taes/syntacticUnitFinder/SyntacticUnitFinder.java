/*
 * $Id$
 */

package org.ohnlp.medkat.taes.syntacticUnitFinder;

import java.util.ArrayList;
import java.util.Iterator;

import org.apache.uima.analysis_engine.ResultSpecification;
import org.apache.uima.analysis_engine.annotator.AnnotatorConfigurationException;
import org.apache.uima.analysis_engine.annotator.AnnotatorContext;
import org.apache.uima.analysis_engine.annotator.AnnotatorInitializationException;
import org.apache.uima.analysis_engine.annotator.AnnotatorProcessException;
import org.apache.uima.analysis_engine.annotator.JTextAnnotator_ImplBase;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.TypeSystem;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;


/*
 * @version $Revision$
 *
 * @author Michael Tanenblatt <mtan@us.ibm.com>
 * @author (c) Copyright 2005 IBM
 * @author All Rights Reserved
 */
public class SyntacticUnitFinder
    extends JTextAnnotator_ImplBase
{
    
    /** Configuration parameter giving type of tokens */
    public static final String PARAM_TOKENANNOTATION = "TokenAnnotation";
    private String tokenAnnotationName;
    /** The type of token annotations to consider */
    protected Type tokenType;

    private int m_currentParenType;

    /**
     * type of annotation that defines a block for processing, e.g. a sentence
     */
    private static final String PARAM_DATA_BLOCK_FS = "DataBlockFeatureStructure";
    private String dataBlockFeatureStructureName;
    private Type dataBlockFeatureStructureType;

    private static final String[] LEFT_PAREN    = {"(", "[", "{"};
    private static final String[] RIGHT_PAREN   = {")", "]", "}"};
    
    /**
     * Initialize the annotator, which includes compilation of regular
     * expressions, fetching configuration parameters from XML descriptor file,
     * and loading of the dictionary file.
     */
    public void initialize(AnnotatorContext ac)
        throws AnnotatorInitializationException, AnnotatorConfigurationException
    {
        super.initialize(ac);
        // Process configration parameters
        try {

            dataBlockFeatureStructureName = (String)getContext().getConfigParameterValue(PARAM_DATA_BLOCK_FS);

            tokenAnnotationName = (String)getContext().getConfigParameterValue(PARAM_TOKENANNOTATION);
        }
        catch (Exception e) {
            throw new AnnotatorConfigurationException(e);
        }
    }

    /**
     * Perform local type system initialization.
     * 
     * @param ts
     *            the current type system.
     * @see org.apache.uima.analysis_engine.annotator.TextAnnotator#typeSystemInit(TypeSystem)
     */
    public void typeSystemInit (TypeSystem ts)
        throws AnnotatorConfigurationException, AnnotatorInitializationException
    {
        tokenType = ts.getType(tokenAnnotationName);
        
        dataBlockFeatureStructureType = ts.getType(dataBlockFeatureStructureName);
    }

    
    public void process (JCas jcas, ResultSpecification resultSpec)
            throws AnnotatorProcessException
    {
        Annotation token;
        for (m_currentParenType = 0; m_currentParenType < LEFT_PAREN.length; ++m_currentParenType) {

            try {
                AnnotationIndex dbIndex = jcas.getAnnotationIndex(dataBlockFeatureStructureType);
                AnnotationIndex tokenIndex = jcas.getAnnotationIndex(tokenType);
    
                for (FSIterator dataBlockIterator = dbIndex.iterator(); dataBlockIterator.hasNext();) {
                    ArrayList<Annotation> punctuation = new ArrayList<Annotation>(2048);
    
                    AnnotationFS dataBlockAnnotation = (AnnotationFS)dataBlockIterator.next();
    
                    // get all tokens for the specified block
                    for (FSIterator tokenIter = tokenIndex.subiterator(dataBlockAnnotation); tokenIter.hasNext();) {
                        token = (Annotation)tokenIter.next();
                        if (isScopePunctuation(token)) {
                            punctuation.add(token);
                        }
                    }
                    processPunct(jcas, dataBlockAnnotation, punctuation);
                }
            }
            catch (Exception e) {
                throw new AnnotatorProcessException(e);
            }
        }
    }
    
    
    private boolean isScopePunctuation (AnnotationFS token)
    {
        String text = token.getCoveredText();
        return (text.equals(LEFT_PAREN[m_currentParenType]) || text.equals(RIGHT_PAREN[m_currentParenType]));
    }
    
    
    private void processPunct (JCas jcas, AnnotationFS dataBlockAnnotation, ArrayList<Annotation> punctuations)
    {
        // find left punctuation mark
        for (Iterator<Annotation> leftPunctIter = punctuations.iterator(); leftPunctIter.hasNext();) {
            Annotation leftPunct = leftPunctIter.next();
            if (isLeftPunctMark(leftPunct)) {
                processPunctDataBlock(jcas, leftPunct, leftPunctIter, dataBlockAnnotation, 1);
            }
        }
    }
    
    
    private void processPunctDataBlock (JCas jcas, Annotation leftPunct, Iterator<Annotation> nextPunctIter, AnnotationFS dataBlockAnnotation, int level)
    {
        // if the sentence has no other punctuation other than a left paren, close scope at end of sentence
        if (!nextPunctIter.hasNext()) {
            makeScopeAnnotation(jcas, leftPunct, dataBlockAnnotation.getEnd(), level);
        }
        // otherwise process other punctuation
        else {
            while (nextPunctIter.hasNext()) {
                Annotation nextPunct = (Annotation)nextPunctIter.next(); 
    
                // if matching closing punctuation found, use it as syntactic unit end
                if (isRightPunctMark(nextPunct)) {
                    makeScopeAnnotation(jcas, leftPunct, nextPunct.getEnd(), level);
                    return;
                }
    
                // otherwise, if another left punct mark, recurse
                else if (isLeftPunctMark(nextPunct)) {
                    processPunctDataBlock(jcas, nextPunct, nextPunctIter, dataBlockAnnotation, level + 1);
                }
            }
        }
        return;
    }
    
    private void makeScopeAnnotation (JCas jcas, Annotation leftPunct, int rightEnd, int level)
    {
        SyntacticUnit annotation = new SyntacticUnit(jcas);
        annotation.setBegin(leftPunct.getBegin());
        annotation.setEnd(rightEnd);
        annotation.setScope(level);
        annotation.addToIndexes();
        
        //System.err.println("****New annotation, begin: " + annotation.getBegin() + ", end: " + annotation.getEnd() + ", level: " + annotation.getScope());
    }
    
    
    private boolean isLeftPunctMark (Annotation punct)
    {
        return (punct.getCoveredText().equals(LEFT_PAREN[m_currentParenType]));
    }
    
    private boolean isRightPunctMark(Annotation punct)
    {
        return (punct.getCoveredText().equals(RIGHT_PAREN[m_currentParenType]));
    }
}
