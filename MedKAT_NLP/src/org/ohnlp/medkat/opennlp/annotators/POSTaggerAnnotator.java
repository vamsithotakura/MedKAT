/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.ohnlp.medkat.opennlp.annotators;

import java.util.ArrayList;
import java.util.List;

import opennlp.tools.lang.english.PosTagger;
import opennlp.tools.dictionary.Dictionary;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_engine.ResultSpecification;
import org.apache.uima.analysis_engine.annotator.AnnotatorConfigurationException;
import org.apache.uima.analysis_engine.annotator.AnnotatorContext;
import org.apache.uima.analysis_engine.annotator.AnnotatorInitializationException;
import org.apache.uima.analysis_engine.annotator.AnnotatorProcessException;
import org.apache.uima.analysis_engine.annotator.JTextAnnotator_ImplBase;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Feature;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.ohnlp.medkat.common.UIMAAnnotationUtils;

/**
 * UIMA Analysis Engine that invokes the OpenNLP POS tagger. The OpenNLP POS
 * tagger generates a PennTreeBank style POS tags. This annotator assumes that
 * sentences and tokens have already been annotated in the CAS with Sentence and
 * Token annotations, respectively. We iterate over sentences, then iterate over
 * tokens in the current sentece to accumlate a list of words, then invoke the
 * OpenNLP POS tagger on the list of words. For each Token we then update the
 * posTag field with the POS tag. The model file for the OpenNLP POS tagger is
 * specified as a parameter (MODEL_FILE_PARAM).
 * 
 */
public class POSTaggerAnnotator extends JTextAnnotator_ImplBase
{

    public static final String PARAM_MODELFILE        = "ModelFile";
    public static final String PARAM_TOKENTYPENAME    = "TokenType";
    public static final String PARAM_SENTENCETYPENAME = "SentenceType";
    public static final String PARAM_POSFEATURENAME   = "POSFeature";

    String                     m_tokenTypeName;
    String                     m_sentenceTypeName;
    String                     m_POSFeatureName;

    private PosTagger          m_tagger;

    /**
     * Initialize the Annotator.
     * 
     * @see JTextAnnotator_ImplBase#initialize(UimaContext)
     */
    public void initialize (AnnotatorContext ctx)
    throws AnnotatorInitializationException,
            AnnotatorConfigurationException
    {
        super.initialize(ctx);

        try {
            // Get configuration parameter values
          String modelFile = ctx.getConfigParameterValue(PARAM_MODELFILE).toString();
          m_tagger = new PosTagger(modelFile, (Dictionary) null);

          m_tokenTypeName = ctx.getConfigParameterValue(PARAM_TOKENTYPENAME).toString();
          m_sentenceTypeName = ctx.getConfigParameterValue(PARAM_SENTENCETYPENAME).toString();
          m_POSFeatureName = ctx.getConfigParameterValue(PARAM_POSFEATURENAME).toString();
        }
        catch (Exception e) {
            throw new AnnotatorInitializationException(e);
        }
    }

    /**
     * Process a CAS.
     * 
     * @see JTextAnnotator_ImplBase#process(JCas)
     */
    public void process (JCas jcas, ResultSpecification aResultSpec)
    throws AnnotatorProcessException
    {

        try {
            List<Annotation> tokenList = new ArrayList<Annotation>();
            List<String> wordList = new ArrayList<String>();
            
            Type sentenceType = jcas.getTypeSystem().getType(m_sentenceTypeName);
            Type tokenType = jcas.getTypeSystem().getType(m_tokenTypeName);
            Feature posFeature = jcas.getRequiredFeature(tokenType, m_POSFeatureName);
        
            AnnotationIndex sentenceIndex = jcas.getAnnotationIndex(UIMAAnnotationUtils.getTypeIndexId(sentenceType));
            AnnotationIndex tokenIndex = jcas.getAnnotationIndex(UIMAAnnotationUtils.getTypeIndexId(tokenType));
        
            // iterate over Sentences
            FSIterator sentenceIterator = sentenceIndex.iterator();
            while (sentenceIterator.hasNext()) {
                Annotation sentence = (Annotation)sentenceIterator.next();
                
                tokenList.clear();
                wordList.clear();
        
                // iterate over Tokens
                FSIterator tokenIterator = tokenIndex.subiterator(sentence);
                while (tokenIterator.hasNext()) {
                    Annotation token = (Annotation) tokenIterator.next();
                    tokenList.add(token);
                    wordList.add(token.getCoveredText());
                }
        
                List<?> wordTagList = m_tagger.tag(wordList);
                
                try {
                    for (int i = 0; i < tokenList.size(); i++) {
                        Annotation token = tokenList.get(i);
                        String posTag = (String)wordTagList.get(i);
                        token.setStringValue(posFeature, posTag);
                    }
                }
                catch (IndexOutOfBoundsException e) {
                    System.err.println("POS tagger error - list of tags shorter than list of words");
                }
            }
        }
        catch (Exception e) {
            throw new AnnotatorProcessException(e);
        }
    }
}
