///////////////////////////////////////////////////////////////////////////////
//Copyright (C) 2003 Thomas Morton
//
//This library is free software; you can redistribute it and/or
//modify it under the terms of the GNU Lesser General Public
//License as published by the Free Software Foundation; either
//version 2.1 of the License, or (at your option) any later version.
//
//This library is distributed in the hope that it will be useful,
//but WITHOUT ANY WARRANTY; without even the implied warranty of
//MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//GNU General Public License for more details.
//
//You should have received a copy of the GNU Lesser General Public
//License along with this program; if not, write to the Free Software
//Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
//////////////////////////////////////////////////////////////////////////////   
package org.ohnlp.medkat.opennlp.annotators;


import java.util.HashMap;
import java.util.Map;

import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.ohnlp.medkat.common.UIMAAnnotationUtils;

import opennlp.maxent.MaxentModel;
import opennlp.tools.lang.english.HeadRules;
import opennlp.tools.lang.english.ParserChunker;
import opennlp.tools.parser.Parse;
import opennlp.tools.parser.chunking.Parser;
import opennlp.tools.util.Span;

/**
 * Class for a shift reduce style parser based on Adwait Ratnaparkhi's 1998 thesis. 
 * 
 */
public class MedKATParser extends Parser
{
    private JCas m_jcas;
    private int m_start;
    
    private Feature m_posFeature;
    private Class<? extends Annotation> m_tokenClass;

    /**
     * Creates a new parser using the specified models and head rules.
     * 
     * @param buildModel    * The model to assign constituent labels.
     * @param checkModel    * The model to determine a constituent is complete.
     * @param chunker       * The model to assign flat constituent labels.
     * @param headRules     * The head rules for head word perculation.
     */
    public MedKATParser (MaxentModel                         buildModel,
                        MaxentModel                         checkModel,
                        ParserChunker                       chunker,
                        HeadRules                           headRules,
                        Map<String, Map<String, String>>    posTagReplacements)
    {
        this(buildModel, checkModel, chunker, headRules, defaultBeamSize, defaultAdvancePercentage, posTagReplacements);
    }

    /**
     * Creates a new parser using the specified models and head rules using the
     * specified beam size and advance percentage.
     * 
     * @param buildModel        * The model to assign constituent labels.
     * @param checkModel        * The model to determine a constituent is complete.
     * @param chunker           * The model to assign flat constituent labels.
     * @param headRules         * The head rules for head word perculation.
     * @param beamSize          * The number of different parses kept during parsing.
     * @param advancePercentage * The minimal amount of probability mass which advanced outcomes
     *                          * must represent. Only outcomes which contribute to the top
     *                          * "advancePercentage" will be explored.
     */
    public MedKATParser (MaxentModel                         buildModel,
                        MaxentModel                         checkModel,
                        ParserChunker                       chunker,
                        HeadRules                           headRules,
                        int                                 beamSize,
                        double                              advancePercentage,
                        Map<String, Map<String, String>>    posTagReplacements)
    {
        super(buildModel, checkModel, null, chunker, headRules, beamSize, advancePercentage);
        m_posTagReplacements = posTagReplacements;
    }
    
    public Parse parse (JCas jcas,
                        int start,
                        Parse tokens,
                        Class<? extends Annotation> tokenClass,
                        Feature posFeature)
    {
        m_jcas = jcas;
        m_tokenClass = tokenClass;
        m_posFeature = posFeature;
        m_start = start;
        return parse(tokens);
    }
    
    private Map<String, String> m_punctuationTags = new HashMap<String, String>();
    final private Map<String, Map<String, String>> m_posTagReplacements;
    /**
     * Advances the parse by assigning it POS tags and returns multiple tag
     * sequences.
     * 
     * @param p     * The parse to be tagged.
     * @return Parses with different POS-tag sequence assignments.
     */
    protected Parse[] advanceTags (final Parse p)
    {
        if (m_punctuationTags.isEmpty()) {
            m_punctuationTags.put("#", "#");
            m_punctuationTags.put("$", "$");
            m_punctuationTags.put(".", ".");
            m_punctuationTags.put("!", ".");
            m_punctuationTags.put("?", ".");
            m_punctuationTags.put(",", ",");
            m_punctuationTags.put("\"", "''");
            m_punctuationTags.put("''", "''");
            m_punctuationTags.put("(", "-LRB-");
            m_punctuationTags.put("{", "-LRB-");
            m_punctuationTags.put(")", "-RRB-");
            m_punctuationTags.put("}", "-RRB-");
            m_punctuationTags.put("...", ":");
            m_punctuationTags.put("--", ":");
            m_punctuationTags.put(":", ":");
            m_punctuationTags.put(";", ":");
            m_punctuationTags.put("-", ":");
            m_punctuationTags.put("``", "``");
            m_punctuationTags.put("'", "'");
        }

        Parse[] children = p.getChildren();

        Parse[] newParses = new Parse[1];
        newParses[0] = (Parse)p.clone();
        
        for (int i = 0; i < children.length; i++) {
            Parse token = children[i];
            Span span = token.getSpan(); 
            Annotation ta = UIMAAnnotationUtils.getSameOffsetAnnotation(
                    m_jcas, m_tokenClass, m_start + span.getStart(), m_start + span.getEnd());
            double prob = 1.0;
            String posTag = ta.getStringValue(m_posFeature);
            if (null == posTag) {
                String tokenText = ta.getCoveredText().toLowerCase();
                if (m_punctuationTags.containsKey(tokenText)) {
                    posTag = m_punctuationTags.get(tokenText);
                }
                else {
                    posTag = tokenText;
                }
            }
            else {
                if (m_posTagReplacements.containsKey(posTag)) {
                    Map<String, String> tok2tag = m_posTagReplacements.get(posTag);
                    String tokString = ta.getCoveredText().toLowerCase();
                    if (tok2tag.containsKey(tokString)) {
                        posTag = tok2tag.get(tokString);
                    }
                    else {
                        if (tok2tag.containsKey("")) {
                            posTag = tok2tag.get("");
                        }
                        else {
                            System.err.printf("No replacement found for external POS tag %s for token %s\n",
                                    posTag, tokString);
                        }
                    }
                }
            }
            
//            System.err.printf(
//                    "%s: %s\n", 
//                    token.getText().substring(token.getSpan().getStart(), token.getSpan().getEnd()),
//                    posTag);
            newParses[0].insert(new Parse(token.getText(), token.getSpan(), posTag, prob, 0));
            newParses[0].addProb(Math.log(prob));
        }
        return newParses;
    }
    
    
/*    
    protected Parse[] advanceTags_orig (final Parse p)
    {
        Parse[] children = p.getChildren();
        String[] words = new String[children.length];
        double[] probs = new double[words.length];
        for (int i = 0, il = children.length; i < il; i++) {
            words[i] = children[i].toString();
        }
        Sequence[] ts = tagger.topKSequences(words);
        if (ts.length == 0) {
            System.err.println("no tag sequence");
        }
        Parse[] newParses = new Parse[ts.length];
        for (int i = 0; i < ts.length; i++) {
            String[] tags = (String[])ts[i].getOutcomes().toArray(
                    new String[words.length]);
            ts[i].getProbs(probs);
            newParses[i] = (Parse)p.clone(); // copies top level
            if (createDerivationString)
                newParses[i].getDerivation().append(i).append(".");
            for (int j = 0; j < words.length; j++) {
                Parse word = children[j];
                // System.err.println("inserting tag "+tags[j]);
                double prob = probs[j];
                newParses[i].insert(new Parse(word.getText(), word.getSpan(),
                        tags[j], prob));
                newParses[i].addProb(Math.log(prob));
                // newParses[i].show();
            }
        }
        return newParses;
    }
*/    
}
