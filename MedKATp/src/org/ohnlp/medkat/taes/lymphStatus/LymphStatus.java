// *********************************************************************
// * IBM Confidential *
// * OCO Source Materials *
// * Package: com.ibm.uima.cas *
// * (C) Copyright IBM Corp. 2003 *
// * The source code for this program is not published or *
// * otherwise divested of its trade secrets, irrespective of *
// * what has been deposited with the US Copyright Office. *
// *********************************************************************
package org.ohnlp.medkat.taes.lymphStatus;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import org.apache.uima.analysis_engine.ResultSpecification;
import org.apache.uima.analysis_engine.annotator.AnnotatorConfigurationException;
import org.apache.uima.analysis_engine.annotator.AnnotatorContext;
import org.apache.uima.analysis_engine.annotator.AnnotatorInitializationException;
import org.apache.uima.analysis_engine.annotator.AnnotatorProcessException;
import org.apache.uima.analysis_engine.annotator.JTextAnnotator_ImplBase;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Feature;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.TypeSystem;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.ohnlp.medkat.common.FeatureConstrainedIterator;
import org.ohnlp.medkat.common.NumericStringUtils;
import org.ohnlp.medkat.common.UIMAAnnotationOffsetComparator;
import org.ohnlp.medkat.common.UIMAAnnotationUtils;
import org.ohnlp.medkat.taes.conceptMapper.DictTerm;
import org.ohnlp.medkat.taes.conceptMapper.DictTermMarkers;
import org.ohnlp.medkat.taes.npMerger.NPCombinedAnnotation;
import org.ohnlp.medkat.taes.sectionFinder.DiagnosisAnnotation;
import org.ohnlp.medkat.taes.sectionFinder.SectionAnnotation;
import org.ohnlp.medkat.taes.sizeLocationRegExAnnotator.NumberExpression;
import org.ohnlp.medkat.taes.subsectionDetector.SubHeading;
import org.ohnlp.medkat.taes.support.dateFinder.DateAnnotation;

/**
 * @author Anni Coden (<a href="mailto:anni@us.ibm.com">anni@us.ibm.com</a>)
 */

public class LymphStatus
        extends JTextAnnotator_ImplBase
// extends Annotator_ImplBase implements TextAnnotator
{
    public static final String MESSAGE_DIGEST = "tae.FilterAnnotator_Messages";

    String                      m_LymphLevelExpressionName;
    private Type                m_LymphLevelExpressionType;
    private Feature             m_lymphStatus;
    private Feature             m_numPositive;
    private Feature             m_numTotal;
    private Feature             m_nodeExp;
    Class<? extends Annotation> m_sentenceClass;
    

    static final int m_undefinedNodeCount = -1;

    
    final static String PARAM_LYMPHLEVELEXPRESSIONNAME = "LymphLevelExpressionName";
    final static String PARAM_SENTENCECLASS = "SentenceClass";
    
//    private static final Pattern numberPattern = Pattern.compile ("[^(]+\\((\\d+).+");

    String leadChars = "[\\s\\(]"; 
    private static final String termChars = "(?:\\s|\\)|\\p{Punct}&&[^-])"; 
    
    private static final String  m_numericStringGroupPattern = "(\\d+|" + NumericStringUtils.getNumericStringGroupPattern(30) + ")";
    private static final Pattern nodeExprPattern = Pattern.compile ("\\(?\\D*" + m_numericStringGroupPattern + "\\s*\\(?of\\s*" + m_numericStringGroupPattern + termChars,Pattern.CASE_INSENSITIVE);
    // private static final Pattern nodeExprPattern2 = Pattern.compile (m_numericStringGroupPattern + "\\s*\\(?of\\s*(\\d+)\\)?",Pattern.CASE_INSENSITIVE);
    private static final Pattern nodeExprPattern3 = Pattern.compile ("\\(\\D*\\s*" + m_numericStringGroupPattern + "\\s*\\D*\\s*and\\s*\\D*\\s*" + m_numericStringGroupPattern + "\\s*\\D*\\)");

    /**
     * Performs any startup tasks required by this annotator. This
     * implementation reads the configuration parmaeters and compiles the
     * regular expressions.
     * 
     * @see org.apache.uima.analysis_engine.annotator.TextAnnotator#initialize(AnnotatorContext)
     */
    public void initialize (AnnotatorContext ac)
    throws AnnotatorConfigurationException, AnnotatorInitializationException
    {
        super.initialize(ac);
        try {
            // Retrieve configuration parameters
            m_LymphLevelExpressionName = (String)ac.getConfigParameterValue(PARAM_LYMPHLEVELEXPRESSIONNAME);
            m_sentenceClass = UIMAAnnotationUtils.forName((String)ac.getConfigParameterValue(PARAM_SENTENCECLASS)); 
        }
        catch (Exception e) {
            throw new AnnotatorInitializationException(e);
        }
    }

    /**
     * Acquires references to CAS Type and Feature objects that are later used
     * during the {@link #process(CAS,ResultSpecification)} method.
     * 
     * @see org.apache.uima.analysis_engine.annotator.TextAnnotator#typeSystemInit(TypeSystem)
     */
    public void typeSystemInit (TypeSystem aTypeSystem)
        throws AnnotatorInitializationException
    {
        m_LymphLevelExpressionType = aTypeSystem.getType (m_LymphLevelExpressionName);
        m_lymphStatus = m_LymphLevelExpressionType.getFeatureByBaseName ("status");
        m_nodeExp = m_LymphLevelExpressionType.getFeatureByBaseName ("nodeExpression");
        m_numPositive = m_LymphLevelExpressionType.getFeatureByBaseName ("numPositive");
        m_numTotal = m_LymphLevelExpressionType.getFeatureByBaseName ("numTotal");
    }
    

    public void process (JCas jcas, ResultSpecification aResultSpec)
        throws AnnotatorProcessException
    {
        try {
            
            // System.out.println(CommonFeatureMatcher.getDocumentId(jcas, null));

            List<Annotation> dateMap = new ArrayList<Annotation>();
            for (Iterator<?> NPChunkIterator = FeatureConstrainedIterator.getEnclosedIterator(
                    jcas, NPCombinedAnnotation.class, null); NPChunkIterator.hasNext ();) {
                Annotation NPChunk = (Annotation) NPChunkIterator.next ();
    
                List<Annotation> lymphMap = getAnnotationsForSpanLymph (jcas, NPChunk);
                List<Annotation> numberMap = getAnnotationsForSpan (jcas, NumberExpression.class, NPChunk);
                dateMap.addAll(getAnnotationsForSpan (jcas, DateAnnotation.class, NPChunk));
    
                if (!lymphMap.isEmpty ())
                {
                    int count = 0;
    
                    if ( !numberMap.isEmpty ()) {
    
                        TreeSet<Annotation> numberSorted =
                            new TreeSet<Annotation>(new UIMAAnnotationOffsetComparator());
                        numberSorted.addAll (numberMap);
    
                        int begin = 0;
                        int end = 0;
                        
                        Iterator<Annotation> numberIterator = numberSorted.iterator ();
                        Integer number1 = null;
                        Integer number2 = null;
    
                        boolean done = false;
                        while (numberIterator.hasNext () && !done) {
                            NumberExpression numExp = (NumberExpression) numberIterator.next ();
                            if (count == 0) {
                                number1 = NumericStringUtils.getIntegerFromNumericString(numExp.getCoveredText());
                                numExp.setNumeric (number1.toString());
                                begin = numExp.getBegin ();
                                end = numExp.getEnd ();
                                count = 1;
                            }
                            else {
                                number2 = NumericStringUtils.getIntegerFromNumericString(numExp.getCoveredText());
                                begin = Math.min (begin, numExp.getBegin ());
                                end = Math.max (end, numExp.getEnd ());
                                numExp.setNumeric (number2.toString());
                                count = 2;
                                done = true;
                            }
                        }
    
                        if (count == 1) {
                            if (isStatusPositive(jcas, lymphMap.toArray(), FeatureConstrainedIterator.getEnclosingAnnotation(
                                    jcas, m_sentenceClass, NPChunk))) {
                                number2 = new Integer(m_undefinedNodeCount);
                            }
                            else {
                                number2 = number1; 
                                number1 = new Integer(0);
                            }
                        }
                        // System.out.println("creatng lle from chunk: " + begin + "|" + end + "|" + new Annotation(jcas, begin, end).getCoveredText());
                        createLymphLevelExpression(jcas, begin, end, number1.intValue(), number2.intValue());
                    }
                }
            }
    
            for (FSIterator dateIter = FeatureConstrainedIterator.getEnclosedIterator(
                    jcas, DateAnnotation.class, null); dateIter.hasNext();) {
                Annotation date = (Annotation) dateIter.next ();
                for (FSIterator lymphExprIter = FeatureConstrainedIterator.getEnclosedIterator(
                        jcas, m_LymphLevelExpressionName, date); lymphExprIter.hasNext();) {
                    ((Annotation)lymphExprIter.next()).removeFromIndexes();
                }
            }
    
            for (FSIterator lymphIter = FeatureConstrainedIterator.getEnclosedIterator(
                    jcas, m_LymphLevelExpressionName, null); lymphIter.hasNext();) {
                Annotation annot = (Annotation) lymphIter.next();
                String s1 = annot.getStringValue(m_nodeExp);
                if (null == s1) {
                    s1 = annot.getCoveredText ();
                }
                if (null != s1) {
                    setStatusValuesFromString (jcas, annot, s1);
                }
            }
            
            for (Iterator<?> sectionIt = FeatureConstrainedIterator.getEnclosedIterator(
                    jcas, SectionAnnotation.class, null); sectionIt.hasNext();) {
                SectionAnnotation section = (SectionAnnotation)sectionIt.next();
                if (section instanceof DiagnosisAnnotation) {
                    List<Annotation> subSectionDiagnosisMap = getAnnotationsForSpan (jcas, SubHeading.class, section);
                    if ( !subSectionDiagnosisMap.isEmpty ())
                    {
    
                        TreeSet<Annotation> subSectionSorted =
                            new TreeSet<Annotation>(new UIMAAnnotationOffsetComparator());
                        subSectionSorted.addAll (subSectionDiagnosisMap);
    
                        Iterator<Annotation> subSecIterator = subSectionSorted.iterator ();
                        while (subSecIterator.hasNext ())
                        {
                            SubHeading subHead = (SubHeading) subSecIterator.next ();
                            // Check is subSection subHead has a lymph node which is
                            // not negated and a diagnosis which is not negated in
                            // one
                            // sentence
                            List<Annotation> lymphMap1 = getAnnotationsForSpanLymph(jcas, subHead);
                            if ( !lymphMap1.isEmpty ())
                            {
                                TreeSet<Annotation> lymphMap1Sorted =
                                    new TreeSet<Annotation>(new UIMAAnnotationOffsetComparator());
                                lymphMap1Sorted.addAll (lymphMap1);
                                Iterator<Annotation> lymphMap1Iterator = lymphMap1Sorted.iterator ();
                                while (lymphMap1Iterator.hasNext ())
                                {
                                    // check if lymph expression and diagnosis are
                                    // in the same
                                    // sentence
                                    DictTerm dTL = (DictTerm) lymphMap1Iterator.next ();
                                    if (!anyContains (dateMap, dTL)) {
                                        findPosNodeExpression(jcas, dTL, dTL.getEnclosingSpan ());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        catch (Exception e) {
            throw new AnnotatorProcessException(e);
        }
    }

    private void setStatusValuesFromString (JCas jcas, Annotation annot, String s1)
    {
        // assume input string of form: "2/24" where first num is number of positive nodes and second is total number of nodes
        int index;
        String positiveStr = null;
        String totalStr = null;
        index = s1.indexOf ("/");
        if (-1 == index) {
            index = s1.indexOf (":");
        }
        if (-1 != index) {
            positiveStr = s1.substring (0, index);
            totalStr = s1.substring (index + 1, s1.length ());
        }
        
        int positive = (positiveStr != null) ? Integer.parseInt (positiveStr) : 0;
        int total = (totalStr != null) ? Integer.parseInt (totalStr) : m_undefinedNodeCount;
        
        setLymphLevelExpressionValues(jcas, annot, positive, total);
    }


    private void findPosNodeExpression (JCas jcas, DictTerm term, Annotation enclosingSpan)
    throws IllegalArgumentException, SecurityException, ClassNotFoundException, IllegalAccessException, NoSuchFieldException
    {
        //System.err.println ("findPosNodeExpression, input: " + enclosingSpan.getCoveredText ());
        boolean status_positive = isStatusPositive(jcas, new Object[] {term}, enclosingSpan);
        
        Matcher m = nodeExprPattern.matcher(enclosingSpan.getCoveredText());
        if (m.find()) {
            //System.err.println("findPosNodeExpression, result1: " + m.group(1) + "/" + m.group(2));
            // String status_nodes = m.group(1);
            Integer status_nodes = NumericStringUtils.getIntegerFromNumericString(m.group(1));
            Integer total_nodes = NumericStringUtils.getIntegerFromNumericString(m.group(2));
            int begin = enclosingSpan.getBegin() + Math.min(m.start(1), m.start(2));
            int end = enclosingSpan.getBegin() + Math.max(m.end(1), m.end(2));
            int positive = status_positive ? ((null == status_nodes) ? 0 : status_nodes.intValue()) : 0;
            // int total = status_positive ? ((null == total_nodes) ? 0 : total_nodes.intValue()) : 0;
            int total = (null == total_nodes) ? m_undefinedNodeCount : total_nodes.intValue();
            // System.out.println("creatng lle from nodeExprPattern: " + begin + "|" + end + "|" + new Annotation(jcas, begin, end).getCoveredText());
            createLymphLevelExpression(jcas, begin, end, positive, total);            
        }
        else {
//            m = nodeExprPattern2.matcher(enclosingSpan.getCoveredText());
//            if (m.find()) {
//                //System.err.println("findPosNodeExpression, result2: " + m.group(1) + "/" + m.group(2));
//                Integer status_nodes = NumericStringUtils.getIntegerFromNumericString(m.group(1));
//                String total_nodes = m.group(2);
//                int begin = enclosingSpan.getBegin() + Math.min(m.start(1), m.start(2));
//                int end = enclosingSpan.getBegin() + Math.max(m.end(1), m.end(2));
//                int positive = status_positive ? ((null == status_nodes) ? 0 : status_nodes.intValue()) : 0;
//                int total = Integer.parseInt (total_nodes);
//                createLymphLevelExpression(jcas, begin, end, positive, total);            
//            }
//            else {
                m = nodeExprPattern3.matcher(enclosingSpan.getCoveredText());
                if (m.find()) {
                    //System.err.println("findPosNodeExpression, result3: " + m.group(1) + " and " + m.group(2);
                    Integer status_nodes = NumericStringUtils.getIntegerFromNumericString(m.group(1));
                    int begin = enclosingSpan.getBegin() + m.start(1);
                    int end = enclosingSpan.getBegin() + m.end(1);
                    int total = ((null != status_nodes) && !status_positive) ? status_nodes.intValue() : m_undefinedNodeCount;
                    int positive = ((null != status_nodes) && status_positive) ? status_nodes.intValue() : 0;
                    // System.out.println("creatng lle 1 from nodeExprPattern3: " + begin + "|" + end + "|" + new Annotation(jcas, begin, end).getCoveredText());
                    createLymphLevelExpression(jcas, begin, end, positive, total);
                    status_nodes = NumericStringUtils.getIntegerFromNumericString(m.group(2));;
                    begin = enclosingSpan.getBegin() + m.start(2);
                    end = enclosingSpan.getBegin() + m.end(2);
                    total = ((null != status_nodes) && !status_positive) ? status_nodes.intValue() : m_undefinedNodeCount;
                    positive = ((null != status_nodes) && status_positive) ? status_nodes.intValue() : 0;
                    // System.out.println("creatng lle 2 from nodeExprPattern3: " + begin + "|" + end + "|" + new Annotation(jcas, begin, end).getCoveredText());
                    createLymphLevelExpression(jcas, begin, end, positive, total);            
                }
//            }
        }
    }

    
    private void setLymphLevelExpressionValues (JCas jcas, Annotation ann, int positive, int total)
    {
        ann.setStringValue(m_nodeExp, positive + "/" + total);
        ann.setIntValue(m_lymphStatus, positive > 0 ? 1 : 0);
        ann.setIntValue(m_numPositive, positive);
        ann.setIntValue(m_numTotal, total);
    }
    
    private void createLymphLevelExpression (JCas jcas, int begin, int end, int positive, int total)
    throws IllegalArgumentException, SecurityException, ClassNotFoundException, IllegalAccessException, NoSuchFieldException
    {
        //System.err.println ("findPosNodeExpression, result3: " + positive + "/" + total);
        Iterator<?> it = FeatureConstrainedIterator.getEnclosedIterator(
                jcas, UIMAAnnotationUtils.forName(m_LymphLevelExpressionName), begin, end);

        Annotation ann = null;        
        if (it.hasNext()) {
            ann = (Annotation)it.next();
            
            if ((ann.getBegin() >= begin) && (ann.getEnd() <= end)) {
                ann.removeFromIndexes();
                ann.setBegin(begin);
                ann.setEnd(end);
                ann.addToIndexes();
            }
        }
        else {
            ann = (Annotation)jcas.getCas().getCurrentView().createAnnotation(m_LymphLevelExpressionType, begin, end);
            ann.addToIndexes();
        }
        setLymphLevelExpressionValues(jcas, ann, positive, total);
    }
    
    boolean isStatusPositive(JCas jcas, Object[] ln_terms, Annotation enclosingSpan)
    throws IllegalArgumentException, SecurityException, ClassNotFoundException, IllegalAccessException, NoSuchFieldException
    {
        // System.out.print("if status positive for \"" + term.getCoveredText() + "\" in \"" + enclosingSpan.getCoveredText() + "... ");
        ;
        for (int i = 0; i < ln_terms.length; ++i) {
            if (DictTermMarkers.isMarkedAsNegated((DictTerm)ln_terms[i])) {
                return false;
            }
        }
        return isStatusPositiveFromDx(jcas, enclosingSpan);
    }
    
    boolean isStatusPositiveFromDx(JCas jcas, Annotation enclosingSpan)
    throws IllegalArgumentException, SecurityException, ClassNotFoundException, IllegalAccessException, NoSuchFieldException
    {
        // System.out.print("if status positive for \"" + term.getCoveredText() + "\" in \"" + enclosingSpan.getCoveredText() + "... ");
        DictTerm dtDx = getDiagnosis(jcas, enclosingSpan);
        if ((null != dtDx) && DictTermMarkers.isMarkedAsNegated(dtDx)) {
            // System.out.println("NO");
            return false;
        }
        // System.out.println("YES");
        return true;
    }

    private DictTerm getDiagnosis (JCas jcas, Annotation enclosingSpan)
    throws IllegalArgumentException, SecurityException, ClassNotFoundException, IllegalAccessException, NoSuchFieldException
    {
        int dictTermMarksToIgnore = (DictTermMarkers.IGNORED_INDICATOR     |
                                     DictTermMarkers.DUPLICATE_INDICATOR   |
                                     DictTermMarkers.SUBSUMED_INDICATOR    |
                                     DictTermMarkers.SUPERFLUOUS_INDICATOR |
                                     DictTermMarkers.MODIFIER_INDICATOR    |
                                     DictTermMarkers.CONTAINSDISALLOWEDTERM_INDICATOR);

        for (Iterator<?> it = FeatureConstrainedIterator.getEnclosedIterator(
                jcas, DictTerm.class, enclosingSpan, "SemClass", new String[]{"Diagnosis"}); it.hasNext();) {
            DictTerm dt = (DictTerm)it.next();
            if (!DictTermMarkers.isAnyMarkedAs(dt, dictTermMarksToIgnore)) {
                return dt; 
            }           
        }
        return null;
    }
    

    private List<Annotation> getAnnotationsForSpan (JCas jcas, Class<? extends Annotation> ann_class, Annotation enclosing)
    throws IllegalArgumentException, SecurityException, ClassNotFoundException, IllegalAccessException, NoSuchFieldException
    {
        List<Annotation> result = new ArrayList<Annotation> ();
        for (Iterator<?> it = FeatureConstrainedIterator.getEnclosedIterator(
                jcas, ann_class, enclosing); it.hasNext();) {
            result.add((Annotation)it.next());
        }
        return result;
    }


    private List<Annotation> getAnnotationsForSpanLymph (JCas jcas, Annotation enclosingSpan)
    throws IllegalArgumentException, SecurityException, ClassNotFoundException, IllegalAccessException, NoSuchFieldException
    {
        List<Annotation> result = new ArrayList<Annotation>();
        for (Iterator<?> it = FeatureConstrainedIterator.getEnclosedIterator(
                jcas, DictTerm.class, enclosingSpan, "SemClass", new String[] {"Lymph"}); it.hasNext();) {
            result.add((Annotation)it.next());
        }
        return result;
    }


    private boolean anyContains (Collection<? extends Annotation>   potentialContainers, 
                                 Annotation                         test_ann)
    {
        //System.err.println("Checking containment within: " + begin + ", end: " + end);
        for (Iterator<? extends Annotation> it = potentialContainers.iterator(); it.hasNext (); ) { 
            Annotation ann = it.next();
            if (contains(ann, test_ann)) {
                return true;
            }
        }
        return false;
    }

    private boolean contains (Annotation container, Annotation contained)
    {
        //System.err.println("\tChecking containment of: " + left.getBegin() + ", end: " + left.getEnd() + " => " + ((left.getBegin() <= begin) && (left.getEnd () >= end)));
        return ((container.getBegin() <= contained.getBegin()) && (container.getEnd () >= contained.getEnd()));
    }
}