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

package org.ohnlp.medkat.taes.conceptMapper;


import org.apache.uima.UimaContext;
import org.apache.uima.analysis_engine.ResultSpecification;
import org.apache.uima.analysis_engine.annotator.AnnotatorConfigurationException;
import org.apache.uima.analysis_engine.annotator.AnnotatorContext;
import org.apache.uima.analysis_engine.annotator.AnnotatorInitializationException;
import org.apache.uima.analysis_engine.annotator.AnnotatorProcessException;
import org.apache.uima.analysis_engine.annotator.JTextAnnotator_ImplBase;
import org.apache.uima.cas.Type;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;

/**
 * Simple Annotator to detect sentences and create Sentence annotations in the
 * CAS. Uses the OpenNLP MaxEnt Sentence Detector.
 * 
 */
public class DictionarySentenceCreator extends JTextAnnotator_ImplBase
{

    public static final String                          PARAM_SENTENCETYPENAME = "SentenceType";

    String                                              m_sentenceTypeName;

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
            m_sentenceTypeName = ctx.getConfigParameterValue(PARAM_SENTENCETYPENAME).toString();
    }
        catch (Exception e) {
            throw new AnnotatorInitializationException(e);
        }
    }

    /**
     * Process a CAS.
     * 
     * @see JTextAnnotator_ImplBase#process(JCas, ResultSpecification)
     */
    public void process (JCas jcas, ResultSpecification rs)
    throws AnnotatorProcessException
    {
        Type sentenceType = jcas.getTypeSystem().getType(m_sentenceTypeName);
        String docText = jcas.getDocumentText();

        Annotation sentence = (Annotation)jcas.getCas().createAnnotation(sentenceType, 0, docText.length());
        sentence.addToIndexes();
    }
}
