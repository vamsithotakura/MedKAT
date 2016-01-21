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


import opennlp.tools.util.Span;

import org.apache.uima.analysis_engine.ResultSpecification;
import org.apache.uima.analysis_engine.annotator.AnnotatorConfigurationException;
import org.apache.uima.analysis_engine.annotator.AnnotatorContext;
import org.apache.uima.analysis_engine.annotator.AnnotatorInitializationException;
import org.apache.uima.analysis_engine.annotator.AnnotatorProcessException;
import org.apache.uima.analysis_engine.annotator.JTextAnnotator_ImplBase;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.ohnlp.medkat.common.UIMAAnnotationUtils;


/**
 * UIMA Analysis Engine that invokes the OpenNLP Tokenizer. The OpenNLP
 * Tokenizer generates a PennTreeBank style tokenization. This annotator assumes
 * that sentences have already been annotated in the CAS with Sentence
 * annotations. We iterate over sentences and invoke the OpenNLP Tokenizer on
 * each sentence. For each token, a Token annotation is created in the CAS. The
 * model file for the OpenNLP Tokenizer is specified as a parameter
 * (MODEL_FILE_PARAM).
 * 
 */
public class TokenizerAnnotator extends JTextAnnotator_ImplBase
{

    public static final String                   PARAM_MODELFILE        = "ModelFile";
    public static final String                   PARAM_TOKENTYPENAME    = "TokenType";
    public static final String                   PARAM_SENTENCETYPENAME = "SentenceType";

    private opennlp.tools.lang.english.Tokenizer m_tokenizer;

    String                                       m_tokenTypeName;
    String                                       m_sentenceTypeName;
    
    private void createTokens(CAS       cas,
                              Type      tokenType,
                              int       spanBegin,
                              int       spanEnd,
                              String    spanText)
    {
        int tokBegin = -1;
        boolean inTok = false;

        for (int i = 0; i < spanText.length(); ++i) {
            if (Character.isMirrored(spanText.charAt(i))) {
                if (inTok) {
                    Annotation token = (Annotation)cas.createAnnotation(
                            tokenType, tokBegin, spanBegin + i);
                    token.addToIndexes();
                }
                Annotation token = (Annotation)cas.createAnnotation(tokenType,
                        spanBegin + i, spanBegin + i + 1);
                token.addToIndexes();
                inTok = false;
                tokBegin = -1;
            }
            else {
                if (!inTok) {
                    tokBegin = spanBegin + i;
                    inTok = true;
                }
            }
        }
        if (inTok) {
            Annotation token = (Annotation)cas.createAnnotation(
                    tokenType, tokBegin, spanEnd);
            token.addToIndexes();
        }
    }

    /**
     * Initialize the Annotator.
     * 
     * @see JTextAnnotator_ImplBase#initialize(AnnotatorContext)
     */
    public void initialize (AnnotatorContext ctx)
    throws AnnotatorInitializationException,
            AnnotatorConfigurationException
    {
        super.initialize(ctx);


        try {
            String buf = ctx.getConfigParameterValue(PARAM_MODELFILE).toString();
            m_tokenizer = new opennlp.tools.lang.english.Tokenizer(buf);
            m_tokenTypeName = ctx.getConfigParameterValue(PARAM_TOKENTYPENAME).toString();
            m_sentenceTypeName = ctx.getConfigParameterValue(PARAM_SENTENCETYPENAME).toString();
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
            Type tokenType = jcas.getTypeSystem().getType(m_tokenTypeName);
            AnnotationIndex sentenceIndex = jcas.getAnnotationIndex(UIMAAnnotationUtils.getTypeIndexId(m_sentenceTypeName));
    
            // iterate over Sentences
            for (FSIterator sentenceIterator = sentenceIndex.iterator(); sentenceIterator.hasNext();) {
                Annotation sent = (Annotation)sentenceIterator.next();
    
                String text = sent.getCoveredText();
                Span[] spans = m_tokenizer.tokenizePos(text);
                for (Span span : spans) {
                    int spanBegin = sent.getBegin() + span.getStart();
                    int spanEnd = sent.getBegin() + span.getEnd();
                    String spanText = text.substring(span.getStart(), span.getEnd());
                    
                    createTokens(jcas.getCas(), tokenType, spanBegin, spanEnd, spanText);
                }
            }
        }
        catch (Exception e) {
            throw new AnnotatorProcessException(e);
        }
    }
}
