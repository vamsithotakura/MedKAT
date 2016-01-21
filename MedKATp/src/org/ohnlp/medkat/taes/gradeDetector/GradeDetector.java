/*
 * $Id$
 */

package org.ohnlp.medkat.taes.gradeDetector;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.uima.analysis_engine.annotator.AnnotatorConfigurationException;
import org.apache.uima.analysis_engine.annotator.AnnotatorContext;
import org.apache.uima.analysis_engine.annotator.AnnotatorContextException;
import org.apache.uima.analysis_engine.annotator.AnnotatorInitializationException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Feature;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.TypeSystem;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.cas.CAS;
import org.apache.uima.jcas.JFSIndexRepository;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.ohnlp.medkat.scr.typeConversion.MedKATTypeConverter;
import org.ohnlp.medkat.taes.conceptMapper.DictTerm;
import org.ohnlp.medkat.taes.subsectionDetector.SubHeading;
import org.ohnlp.medkat.taes.subsectionProcessor.SubsectionProcessor;

/**
 * @version $Revision$
 * 
 * @author Michael Tanenblatt &lt;mtan@us.ibm.com&gt;
 * @author (c) Copyright 2006 IBM
 * @author All Rights Reserved
 */

public class GradeDetector
        extends SubsectionProcessor
{

    public class GradeOrTokenAnnotationComparator
    //J5.0 implements Comparator<GradeOrTokenAnnotation>
    implements Comparator<GradeOrTokenAnnotation>
    {
    
        /* (non-Javadoc)
         * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
         */
        //J5.0 public int compare (GradeOrTokenAnnotation leftObj, GradeOrTokenAnnotation rightObj)
        public int compare (GradeOrTokenAnnotation left, GradeOrTokenAnnotation right)
        {
            if (left.getBegin () < right.getBegin ())
            {
                return -1;
            }
            else if (left.getBegin () > right.getBegin ())
            {
                return 1;
            }
            else
            {
                return 0;
            }
        }
    }
      
    private class GradeOrTokenAnnotation
    {
        static final int GRADE_ANNOTATION = 1;
        static final int TOKEN_ANNOTATION = 2;
        
        private int type;
        private Annotation annotation;
        
        //private GradeOrTokenAnnotation ()
       // {
        //}        
        /**
         * @param type
         * @param annotation
         */
        protected GradeOrTokenAnnotation (int type, Annotation annotation)
        {
            super ();
            this.type = type;
            this.annotation = annotation;
        }
        /**
         * @return Returns the annotation.
         */
        protected Annotation getAnnotation ()
        {
            return annotation;
        }
        /**
         * @param annotation The annotation to set.
         */
        protected void setAnnotation (Annotation annotation)
        {
            this.annotation = annotation;
        }
        protected boolean isToken ()
        {
            return (type == TOKEN_ANNOTATION);
        }
        protected boolean isGrade ()
        {
            return (type == GRADE_ANNOTATION);
        }
        
        protected int getBegin ()
        {
            return getAnnotation ().getBegin();
        }
        
        protected int getEnd ()
        {
            return getAnnotation ().getEnd();
        }
        /* (non-Javadoc)
         * @see java.lang.Object#toString()
         */
        public String toString ()
        {
            StringBuffer result = new StringBuffer ("");
            
            result.append (isToken () ? "Type: TOKEN\n" : "Type: GRADE\n");
            result.append ("\ttext: '" + getAnnotation().getCoveredText() + "'\n");
            result.append ("\tbegin: " + getBegin() + "\n");
            result.append ("\tend: " + getEnd() + "\n");
            return result.toString ();
        }
        public String toCompactString ()
        {
            return ("'" +getAnnotation().getCoveredText() + "'/" + (isToken () ? "token" : "grade"));
        }
        
        
    }

    public class Grade
    {

        private String gradeType;
        private String value;
        private String maxValue;
        private int begin;
        private int end;
        private int subsection;

        /**
         * @return Returns the begin.
         */
        public int getBegin ()
        {
            return begin;
        }

        /**
         * @param begin The begin to set.
         */
        public void setBegin (int begin)
        {
            this.begin = begin;
        }

        /**
         * @return Returns the end.
         */
        public int getEnd ()
        {
            return end;
        }

        /**
         * @param end The end to set.
         */
        public void setEnd (int end)
        {
            this.end = end;
        }

        /**
         * @param gradeType
         */
        public void setGradeType (String gradeType)
        {
            this.gradeType = gradeType;
        }

        /**
         * @param string
         */
        public void setValue (String value)
        {
            this.value = value;
        }

        /**
         * @return Returns the maxValue.
         */
        public String getMaxValue ()
        {
            return maxValue;
        }

        /**
         * @param maxValue The maxValue to set.
         */
        public void setMaxValue (String maxValue)
        {
            this.maxValue = maxValue;
        }

        /**
         * @return Returns the gradeType.
         */
        public String getGradeType ()
        {
            return gradeType;
        }

        /**
         * @return Returns the value.
         */
        public String getValue ()
        {
            return value;
        }

        /**
         * @return Returns the subsection.
         */
        public int getSubsection ()
        {
            return subsection;
        }

        /**
         * @param subsection The subsection to set.
         */
        public void setSubsection (int subsection)
        {
            this.subsection = subsection;
        }

    }

    
    
    private static final String PARAM_SENTENCE_CLASS = "SentenceAnnotationType";
    private static final String PARAM_TOKEN_CLASS = "TokenAnnotationType";
    private static final String PARAM_BASE_SUBSUBSECTION_CLASS = "BaseSubSubsectionType";

    //private static final String PARAM_GRADES_FOR_SITES = "gradesForSites";

    //private static final String PARAM_SITES_FOR_GRADES = "sitesForGrades";
    
    private static final String PARAM_TOTAL_GLEASON_GRADE = "GleasonsGrade";

    private static final String PARAM_PRIMARY_GLEASON_GRADE = "PrimaryGleasonsGrade";

    private static final String PARAM_SECONDARY_GLEASON_GRADE = "SecondaryGleasonsGrade";

    private static final String PARAM_MAX_TO_LOOK_BEYOND = "MaxToLookBeyond";


    private static final String GRADE_SEMCLASS = "Grade";

    private static final String GLEASON_TEXT = "Gleason";
//These are now declared in Grades.java as public static
   // private static final int OTHER_GRADE = 1;
    //private static final int GLEASON_GRADE = 2;
    //private static final int PRIMARY_GLEASON_GRADE = 3;
    //private static final int SECONDARY_GLEASON_GRADE = 4;

    //private static final String GRADE_LEVEL_INDICATOR = "GradeLevel";

    private static final String PARAM_UNKNOWN_MAX_VALUE_INDICATOR = "UnknownMaxValueIndicator";
    private static final String DEFAULT_UNKNOWN_MAX_VALUE = "0";
    private String unknownMaxValueIndicator = DEFAULT_UNKNOWN_MAX_VALUE;
    
    private static final String GLEASON_MAX_TOTAL_VALUE = "10";
    private static final String GLEASON_MAX_SINGLE_VALUE = "5";

    private static final String BASIC_GRADE = "BasicGrade";
    private static final String GRADE_LEVEL = "GradeLevel";

    private AnnotationIndex tokenIndex;
    private AnnotationIndex dictTermIndex;
    private AnnotationIndex sentenceIndex;
    //private AnnotationIndex siteIndex;
    
    //private String [] gradesForSites;
    //private String [] sitesForGrades;

    //private Map siteGradeMap;

    private List<Grade> results;

    //private List [] sitesInSections;

    private Type sentenceType;
    private String sentenceTypeName;
    private Type tokenType;
    private String tokenTypeName;

    private Feature tokenTypeFeature;

    private Pattern zeroToTenPattern = Pattern.compile ("[0-9]|10");
    private Pattern zeroToTenOverZeroToTenPattern = Pattern.compile ("([0-9]|10)/([0-9]|10)");

    //Moved to grades.java so they can be accessed from MedCASconsumer
   // private String primaryGleasonGradeLabel;
    //private String secondaryGleasonGradeLabel;
    //private String totalGleasonGradeLabel;

    private String gradeLevelValueBasicPatternString = "(([1-5]|(i{1,3}|iv|v))(a|b)?)";
    private Pattern gradeLevelValuePattern = Pattern.compile (gradeLevelValueBasicPatternString + "(-" + gradeLevelValueBasicPatternString + ")?", Pattern.CASE_INSENSITIVE);

    private Pattern compoundGradeValue = Pattern.compile ("[^\\-0-9]*-?((?:[123IV]|II|III|IV)(?:[AB])?)$", Pattern.CASE_INSENSITIVE);
    private int maxToLookBeyond;

    private AnnotationIndex subsubIndex;
    private Type subsubType;
    private String subsubTypeName;
   
    private Feature subBeginFeat;
    private Feature subEndFeat;
   
    /*
     * (non-Javadoc)
     * 
     * @see org.ohnlp.medkat.taes.subsectionProcessor.SubsectionProcessor#initialize(org.apache.uima.analysis_engine.annotator.AnnotatorContext)
     */
    public void initialize (AnnotatorContext annotatorContext)
        throws AnnotatorInitializationException,
            AnnotatorConfigurationException
    {
        super.initialize (annotatorContext);

        try
        {
            
            sentenceTypeName = (String) getContext ().getConfigParameterValue (PARAM_SENTENCE_CLASS);
            tokenTypeName = (String) getContext ().getConfigParameterValue (PARAM_TOKEN_CLASS);
            subsubTypeName = (String) getContext ().getConfigParameterValue (PARAM_BASE_SUBSUBSECTION_CLASS);
            maxToLookBeyond = ((Integer) getContext ().getConfigParameterValue (PARAM_MAX_TO_LOOK_BEYOND)).intValue();
            unknownMaxValueIndicator = (String) getContext ().getConfigParameterValue (PARAM_UNKNOWN_MAX_VALUE_INDICATOR);
            
            Grades.primaryGleasonGradeLabel = (String) getContext ().getConfigParameterValue (PARAM_PRIMARY_GLEASON_GRADE);
            Grades.secondaryGleasonGradeLabel = (String) getContext ().getConfigParameterValue (PARAM_SECONDARY_GLEASON_GRADE);
            Grades.totalGleasonGradeLabel = (String) getContext ().getConfigParameterValue (PARAM_TOTAL_GLEASON_GRADE);
            //gradesForSites = (String []) getContext ().getConfigParameterValue (PARAM_GRADES_FOR_SITES);
            //sitesForGrades = (String []) getContext ().getConfigParameterValue (PARAM_SITES_FOR_GRADES);

            //if (gradesForSites.length == sitesForGrades.length)
            //{
            //    siteGradeMap = buildSiteGradeMap (sitesForGrades,
            //                                      gradesForSites);
            //}
            //else
            //{
            //    System.err.println ("Arrays 'gradesForSites' and 'sitesForGrades' must be the same length.");
            //    throw new AnnotatorConfigurationException ();
            //}

        }
        catch (AnnotatorContextException e)
        {
            e.printStackTrace ();
            throw new AnnotatorConfigurationException ();
        }

    }

    /**
     * @param sitesForGrades
     *            array: { breast, breast, prostate, ... }
     * @param gradesForSites
     *            array: { HistologicGrade, NuclearGrade, GleasonsGrade, ... }
     * @return map of grades to sites, e.g.:
     * 
     *  breast: { HistologicGrade, NuclearGrade }
     *  prostate: { GleasonsGrade }
     *  ...
     *  
     */
    //private Map buildSiteGradeMap (String [] sitesForGrades,
    //                               String [] gradesForSites)
    //{
    //    Map result = new HashMap ();

    //    for (int i = 0; i < gradesForSites.length; i++ )
    //    {
    //        Map values;
    //        if (result.containsKey (gradesForSites [i]))
    //        {
    //            values = (Map) result.get (gradesForSites [i]);
                //System.err.println ("grade exists: '" + gradesForSites [i] + "'");
    //        }
    //        else
    //        {
    //            values = new HashMap ();
    //            //System.err.println ("grade doesn't exist: '" + gradesForSites [i] + "'");
    //        }
            
            //values.put (gradesForSites [i], sitesForGrades [i]);
    //        values.put (sitesForGrades [i], null);
    //        result.put (gradesForSites [i], values);
            //System.err.println ("adding site: '" + sitesForGrades [i] + "'");
    //    }

    //    return result;
    //}

    /*
     * (non-Javadoc)
     * 
     * @see org.ohnlp.medkat.taes.subsectionProcessor.SubsectionProcessor#typeSystemInit(org.apache.uima.cas.TypeSystem)
     */
    public void typeSystemInit (TypeSystem typeSystem)
        throws AnnotatorInitializationException,
            AnnotatorConfigurationException
    {
        super.typeSystemInit (typeSystem);
        tokenType = typeSystem.getType (tokenTypeName);
        tokenTypeFeature = tokenType.getFeatureByBaseName ("frost_TokenType");
        sentenceType = typeSystem.getType (sentenceTypeName);
        subsubType = typeSystem.getType (subsubTypeName);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.ohnlp.medkat.taes.subsectionProcessor.SubsectionProcessor#initializeProcess(org.apache.uima.cas.CAS,
     *      org.apache.uima.jcas.JCas, org.apache.uima.jcas.JFSIndexRepository)
     */
    protected void initializeProcess (CAS tcas,
                                      JCas jcas,
                                      JFSIndexRepository jcasIndexes)
    {
        super.initializeProcess (tcas, jcas, jcasIndexes);
        tokenIndex = (AnnotationIndex) tcas.getAnnotationIndex (tokenType);
        sentenceIndex = (AnnotationIndex) tcas.getAnnotationIndex (sentenceType);
        subsubIndex = (AnnotationIndex) tcas.getAnnotationIndex (subsubType);

        dictTermIndex = (AnnotationIndex) jcasIndexes.getAnnotationIndex (DictTerm.typeIndexID);
        
        results = new ArrayList<Grade> ();

        // these need to be here instead of in typeSystemInit() since the subSectionType is not defined until super.initializeProcess() is run
        subBeginFeat = getSubSectionType ().getFeatureByBaseName("begin");
        subEndFeat = getSubSectionType ().getFeatureByBaseName("end");
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.ohnlp.medkat.taes.subsectionProcessor.SubsectionProcessor#postProcess(org.apache.uima.cas.CAS,
     *      org.apache.uima.jcas.JCas, org.apache.uima.jcas.JFSIndexRepository)
     */
    protected void postProcess (CAS tcas,
                                JCas jcas,
                                JFSIndexRepository jcasIndexes)
    {
        super.postProcess (tcas, jcas, jcasIndexes);
        
        // now create annotations for all items found
        if (results != null)
        {
            for (int i = 0; i < results.size(); i++)
            {
                if (results.get(i) != null)
                {
                    Grade grade = results.get(i);
                    GradeAnnotation annotation = new GradeAnnotation (jcas, grade.getBegin (), grade.getEnd ());
                    annotation.setSubsection(grade.getSubsection());
                    annotation.setGradeType(grade.getGradeType());
                    annotation.setValue(grade.getValue());
                    annotation.setMaxValue(grade.getMaxValue());
                    annotation.addToIndexes();
                    MedKATTypeConverter.convert(jcas, annotation);
                    //System.err.println("CREATING GRADE ANNOTATION, GradeType:" + grade.getGradeType() + ", value: " + grade.getValue());                        
                }
            }            
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.ohnlp.medkat.taes.subsectionProcessor.SubsectionProcessor#processSubSection(org.ohnlp.medkat.taes.subsectionDetector.SubHeading)
     */
    protected void processSubSection (CAS tcas, JCas jcas, SubHeading subsection, boolean unused, int begin, int end)
    {
        if (!isUsingSubSubsections (subsubIndex))
        {
            //FSIterator sentenceIter = sentenceIndex.subiterator (subsection);
            FSIterator sentenceIter = getBeginEndConstrainedIter (jcas, sentenceIndex.iterator (), subBeginFeat, subEndFeat, subsection.getBegin(), subsection.getEnd());
            processAllTargetSentences (sentenceIter, subsection);
        }
    }

    private boolean isUsingSubSubsections (AnnotationIndex  subsubIndex)
    {
        FSIterator subsubIter = subsubIndex.iterator ();
        return subsubIter.hasNext();
    }
    
    /**
     * @param sentenceIter
     */
    private void processAllTargetSentences (FSIterator sentenceIter, SubHeading subsection)
    {
        //if (! sentenceIter.hasNext ())
        //{
        //    System.err.println("NO Sentences!");
        //}
        while (sentenceIter.hasNext ())
        {
            Annotation sentence = (Annotation) sentenceIter.next ();

            //System.err.println("Sentence:"  + sentence.getCoveredText());
            
            if (! inExcludedSubSubsection (sentence, subsection, getExcludedSubSubsections ()))
            {
                GradeOrTokenAnnotation [] orderedAnnotations = findGradesAndTokens (sentence);

                // print it
                //for (int i = 0; i < orderedAnnotations.length; i++)
                //{
                //    System.err.println (orderedAnnotations[i].toString ());
                //}
                
                results = processGrades (orderedAnnotations, subsection);
            }
        }
    }

    private GradeOrTokenAnnotation [] findGradesAndTokens (Annotation sentence)
    {
        FSIterator dictTermIter = dictTermIndex.subiterator (sentence);
        FSIterator tokenIter = tokenIndex.subiterator (sentence);
        TreeSet<GradeOrTokenAnnotation> orderedAnnotationMap = new TreeSet<GradeOrTokenAnnotation> (new GradeOrTokenAnnotationComparator ());
        
        List<DictTerm> grades = new ArrayList<DictTerm> ();
        while (dictTermIter.hasNext ())
        {
            DictTerm dictTerm = (DictTerm) dictTermIter.next ();
            if ((dictTerm.getMarked () == 0) && (dictTerm.getSemClass ().equals (GRADE_SEMCLASS)))
            {
                grades.add (dictTerm);
                orderedAnnotationMap.add(new GradeOrTokenAnnotation (GradeOrTokenAnnotation.GRADE_ANNOTATION, dictTerm));
            }
        }

        
        //Iterator gradeTempIterator = grades.iterator();
        //System.err.println("Grades: ");
        //while (gradeTempIterator.hasNext ())
        //{
        //    DictTerm grade = (DictTerm) gradeTempIterator.next ();
        //    System.err.println("grade: " + grade.getCoveredText () + ", begin: " + grade.getBegin () + ", end: " + grade.getEnd ());
        //}
        // add all tokens that do not occur within a grade term
        Iterator<DictTerm> gradeIterator = grades.iterator();
        while (gradeIterator.hasNext ())
        {
            DictTerm grade = gradeIterator.next ();
            //System.err.println("grade: " + grade.getCoveredText () + ", begin: " + grade.getBegin () + ", end: " + grade.getEnd ());

            Annotation token = null;
            
            // go through all tokens that occur up to the end of the current grade term,
            // and add only tokens that occur prior to the current term
            while (tokenIter.hasNext () &&
                    ((token = (Annotation) tokenIter.next()) != null) &&
                    //dumpToken(token, grade) &&
                    (token.getEnd() < grade.getEnd()))
             {
                 if (token.getEnd() < grade.getBegin())
                 {
                     orderedAnnotationMap.add(new GradeOrTokenAnnotation (GradeOrTokenAnnotation.TOKEN_ANNOTATION, token));
                 }
             }
        }
        // add any tokens that occur subsequent to the final grade term
        while (tokenIter.hasNext ())
        {
            Annotation token = (Annotation) tokenIter.next ();
            orderedAnnotationMap.add (new GradeOrTokenAnnotation (GradeOrTokenAnnotation.TOKEN_ANNOTATION, token));
        }
        
        return orderedAnnotationMap.toArray(new GradeOrTokenAnnotation [orderedAnnotationMap.size()]);
    }
    
    //private boolean dumpToken (Annotation token, Annotation grade)
    //{
    //    System.err.println("token: '" + token.getCoveredText () + "', begin: " + token.getBegin () + ", end: " + token.getEnd () + ", grade begin: " + grade.getBegin () + ", end: " + grade.getEnd () + ", token.getEnd() < grade.getEnd():" + (token.getEnd() < grade.getEnd()));
    //    return true;
    //}

    /**
     * @param orderedAnnotations
     * @param grades 
     * @param subsection
     * @return
     */
    private List<Grade> processGrades (GradeOrTokenAnnotation [] orderedAnnotations, SubHeading subsection)
    {
        int currentItem = 0;
        
        
        //while (currentItem < orderedAnnotations.length)
        //{
        //    System.err.print (orderedAnnotations[currentItem].toCompactString() + " ");
        //    currentItem += 1;
        //}
        //System.err.println ();
        //currentItem = 0;
        
        
        while (currentItem < orderedAnnotations.length)
        {
            if (orderedAnnotations[currentItem].isGrade())
            {
                DictTerm grade = (DictTerm) orderedAnnotations[currentItem].getAnnotation();
                
                //System.err.println("GRADE TERM: '" + grade.getAttributeValue() + "'");
                
                //if (isBasicGrade (grade) ||
                //    (OKGradeForSites (grade.getAttributeValue (), sitesInSections[subsection.getSubSectionNumber()])))
                //{
                    //System.err.println("GRADE TYPE: '" + gradeTypeOf (grade.getAttributeValue()) + "'");
                    
                    switch (gradeTypeOf (grade.getAttributeValue()))
                    {
                        
                        case Grades.PRIMARY_GLEASON_GRADE :
                            currentItem = findSoloGleasonGradeValue (results, orderedAnnotations, currentItem, grade, subsection, Grades.primaryGleasonGradeLabel);
                            break;
                        case Grades.SECONDARY_GLEASON_GRADE:
                            currentItem = findSoloGleasonGradeValue (results, orderedAnnotations, currentItem, grade, subsection, Grades.secondaryGleasonGradeLabel);
                            break;
                        case Grades.GLEASON_GRADE:
                            currentItem = findGleasonGradeValues (results, orderedAnnotations, currentItem, grade, subsection);
                            break;
                        case Grades.OTHER_GRADE:
                            currentItem = findOtherGradeValues (results, orderedAnnotations, currentItem, grade, subsection);
                            break;
                        default:
                            break;
                    }
                //}
            }
            currentItem++;
        }
        return results;
    }

    /**
     * detect grade type. If some special handling of grade needed (e.g., Gleason's grade),
     * return value indicating this. Otherwise return default value.
     * 
     * @param gradeType
     * @return indicator of grade type
     */
    private int gradeTypeOf (String gradeType)
    {
        if (gradeType.equals(Grades.primaryGleasonGradeLabel))
        {
            return Grades.PRIMARY_GLEASON_GRADE;
        }
        else if (gradeType.equals(Grades.secondaryGleasonGradeLabel))
        {
            return Grades.SECONDARY_GLEASON_GRADE;
        }
        else if (gradeType.indexOf(GLEASON_TEXT) != -1)
        {
            return Grades.GLEASON_GRADE;
        }
        return Grades.OTHER_GRADE;
    }

    /**
     * @param results
     * @param gradeTerm
     * @param subsection
     */
    private int findOtherGradeValues (List<Grade> results, GradeOrTokenAnnotation [] annotations, int currentAnnotation, DictTerm otherGradeTerm, SubHeading subsection)
    {
        int toLookBeyond = 1;
        int toLookAt = toLookBeyond - currentAnnotation;
        
        //System.err.println("processing other grade term: " + otherGradeTerm);

        String  compoundGradeValue = compoundGradeEntry (otherGradeTerm);
        if (compoundGradeValue != null)
        {
            results.add (createNewGrade (otherGradeTerm.getAttributeValue(), compoundGradeValue, unknownMaxValueIndicator, otherGradeTerm.getBegin(), otherGradeTerm.getEnd(), subsection.getSubSectionNumber()));            
            return currentAnnotation;
        }
        
        // look at current item
        if (isBasicGradeOrLevel (otherGradeTerm))
        {
            // check to see if next term defines the type of grade level
            if ((currentAnnotation + 1 < annotations.length) &&
                (annotations[currentAnnotation + 1].isGrade()))
            {
                DictTerm gradeAnnotation = (DictTerm) annotations[currentAnnotation + 1].getAnnotation();
                if (! isBasicGradeOrLevel (gradeAnnotation))
                {
                    //System.err.println("case 1a");
                    results.add (createNewGrade (gradeAnnotation.getAttributeValue(), otherGradeTerm.getDictCanon(), unknownMaxValueIndicator, otherGradeTerm.getBegin(), otherGradeTerm.getEnd(), subsection.getSubSectionNumber()));            
                    // do not skip ahead a token, since e.g., "high gleason grade (4+5=9)" would not be handled correctly
                    return currentAnnotation;
                }
            }
            //System.err.println("case 1b");
            results.add (createNewGrade (otherGradeTerm.getAttributeValue(), otherGradeTerm.getDictCanon(), unknownMaxValueIndicator, otherGradeTerm.getBegin(), otherGradeTerm.getEnd(), subsection.getSubSectionNumber()));
            return currentAnnotation;
        }
        
        // otherwise, look backwards
        while ((toLookAt >= 0) && 
               (toLookBeyond < maxToLookBeyond))
        {
            
            //System.err.println("Backwards, ANNOTATIONS[" + toLookAt + "]: " + annotations[toLookAt]);

            if (annotations[toLookAt].isGrade())
            {
                DictTerm gradeAnnotation = (DictTerm) annotations[toLookAt].getAnnotation();
                if (isBasicGradeOrLevel (gradeAnnotation))
                {
                    //System.err.println("case 2");

                    results.add (createNewGrade (otherGradeTerm.getAttributeValue(), gradeAnnotation.getDictCanon(), unknownMaxValueIndicator, gradeAnnotation.getBegin(), gradeAnnotation.getEnd(), subsection.getSubSectionNumber()));
                    return currentAnnotation;
                }
            }
            toLookBeyond += 1;
            toLookAt = currentAnnotation - toLookBeyond;
        }
        
        
        // otherwise, look forwards
        toLookBeyond = 1;
        toLookAt = toLookBeyond + currentAnnotation;
        
        while ((toLookAt < annotations.length) && 
               (toLookBeyond < maxToLookBeyond))
        {
            
            //System.err.println("toLookAt: " + toLookAt + ", toLookBeyond: " + toLookBeyond);
            //System.err.println("Forwards, ANNOTATIONS[" + toLookAt + "]: " + annotations[toLookAt]);

            if (annotations[toLookAt].isGrade())
            {
                DictTerm gradeAnnotation = (DictTerm) annotations[toLookAt].getAnnotation();
                if (isBasicGradeOrLevel (gradeAnnotation))
                {
                    //System.err.println("case 3");

                    results.add (createNewGrade (otherGradeTerm.getAttributeValue(), gradeAnnotation.getDictCanon(), unknownMaxValueIndicator, gradeAnnotation.getBegin(), gradeAnnotation.getEnd(), subsection.getSubSectionNumber()));
                    return toLookAt;
                }
            }
            else
            {
                Annotation token = annotations[toLookAt].getAnnotation();
                if (isGradeLevelValue (token.getCoveredText()))
                {
                    //System.err.println("toLookAt: " + toLookAt + ", annotations.length: " + annotations.length);
                    //for (int xx = 0; xx < annotations.length; xx++)
                    //{
                    //    System.err.println("annotations[" + xx + "]: " + annotations[xx]);
                    //}
                    // pattern: 2 (of 4)
                    if (((toLookAt + 3) < annotations.length) &&
                             annotations[toLookAt+1].getAnnotation().getCoveredText().equals("(") &&
                             annotations[toLookAt+2].getAnnotation().getCoveredText().equalsIgnoreCase("of") &&
                             isGradeLevelValue (annotations[toLookAt + 3].getAnnotation().getCoveredText()))
                    {
                        //String maxValue = findMaxIfRange (token.getCoveredText());
                        //results.add (createNewGrade (otherGradeTerm.getAttributeValue(), maxValue, annotations[toLookAt + 3].getAnnotation().getCoveredText(), token.getBegin(), annotations[toLookAt + 3].getAnnotation().getEnd(), subsection.getSubSectionNumber()));
                        String value = token.getCoveredText();
                        results.add (createNewGrade (otherGradeTerm.getAttributeValue(), value, annotations[toLookAt + 3].getAnnotation().getCoveredText(), token.getBegin(), annotations[toLookAt + 3].getAnnotation().getEnd(), subsection.getSubSectionNumber()));
                        //System.err.println("case 4");
                        //System.out.println ("createNewGrade: " + maxValue + ", " + annotations[toLookAt + 3].getAnnotation().getCoveredText());
                        return toLookAt + 3;
                    }
                    // pattern: 2 of 4
                    else if (((toLookAt + 2) < annotations.length) &&
                             annotations[toLookAt+1].getAnnotation().getCoveredText().equalsIgnoreCase("of") &&
                             isGradeLevelValue (annotations[toLookAt + 2].getAnnotation().getCoveredText()))
                    {
                        //String maxValue = findMaxIfRange (token.getCoveredText());
                        //results.add (createNewGrade (otherGradeTerm.getAttributeValue(), maxValue, annotations[toLookAt + 2].getAnnotation().getCoveredText(), token.getBegin(), annotations[toLookAt + 2].getAnnotation().getEnd(), subsection.getSubSectionNumber()));
                        String value = token.getCoveredText();
                        results.add (createNewGrade (otherGradeTerm.getAttributeValue(), value, annotations[toLookAt + 2].getAnnotation().getCoveredText(), token.getBegin(), annotations[toLookAt + 2].getAnnotation().getEnd(), subsection.getSubSectionNumber()));
                        //System.err.println("case 4");
                        //System.out.println ("createNewGrade: " + maxValue + ", " + annotations[toLookAt + 3].getAnnotation().getCoveredText());
                        return toLookAt + 2;
                    }
                    // pattern: II/IV or II-III/IV
                    else if (((toLookAt + 2) < annotations.length) &&
                            (isPunct(annotations[toLookAt+1]) && annotations[toLookAt+1].getAnnotation().getCoveredText().equals("/")) &&
                            isGradeLevelValue (annotations[toLookAt + 2].getAnnotation().getCoveredText()))
                    {
                        //String maxValue = findMaxIfRange (token.getCoveredText());
                        //results.add (createNewGrade (otherGradeTerm.getAttributeValue(), maxValue, annotations[toLookAt + 2].getAnnotation().getCoveredText(), token.getBegin(), annotations[toLookAt + 2].getAnnotation().getEnd(), subsection.getSubSectionNumber()));
                        String value = token.getCoveredText();
                        results.add (createNewGrade (otherGradeTerm.getAttributeValue(), value, annotations[toLookAt + 2].getAnnotation().getCoveredText(), token.getBegin(), annotations[toLookAt + 2].getAnnotation().getEnd(), subsection.getSubSectionNumber()));
                        //System.err.println("case 5");
                        return toLookAt + 2;
                    }
                    // pattern: II-III or II
                    else 
                    {
                        //String maxValue = findMaxIfRange (token.getCoveredText());
                        //results.add (createNewGrade (otherGradeTerm.getAttributeValue(), maxValue, UNKNOWN_MAX_VALUE, token.getBegin(), token.getEnd(), subsection.getSubSectionNumber()));
                        String value = token.getCoveredText();
                        results.add (createNewGrade (otherGradeTerm.getAttributeValue(), value, unknownMaxValueIndicator, token.getBegin(), token.getEnd(), subsection.getSubSectionNumber()));
                        //System.err.println("case 6");
                        return toLookAt;
                    }
                }
            }
            toLookBeyond += 1;
            toLookAt = toLookBeyond + currentAnnotation;
        }
        //System.err.println("case 0");

        return toLookAt;
    }

    /**
     * @param otherGradeTerm
     * @return
     */
    private String compoundGradeEntry (DictTerm otherGradeTerm)
    {
        String result = null;
        
        //System.err.println("COMPOUND ENTRY: " + otherGradeTerm.getCoveredText ());
        Matcher matcher = compoundGradeValue.matcher (otherGradeTerm.getCoveredText ());
        if (matcher.matches ())
        {
            //for (int i = 0; i <=  matcher.groupCount (); i++)
            //{
            //    System.err.println("Match["+ i + "]: " + matcher.group (i));
            //}
            result = matcher.group (1);
            //System.err.println("MATCHES: "+ otherGradeTerm.getCoveredText ());
        }
        return result;
    }

    /**
     * @param coveredText
     * @return
     */
    private boolean isGradeLevelValue (String text)
    {
        Matcher matcher = gradeLevelValuePattern.matcher (text);
        //System.err.println("isGradeLevelValue: " + text + " = " + matcher.matches());
        return matcher.matches();
    }

    /*
    private String findMaxIfRange (String value)
    {
        String [] gradeRange = value.split("-");
        if (gradeRange.length < 1)
        {
            return "";
        }
        return gradeRange[gradeRange.length - 1];
    }
    */
    
    private int findSoloGleasonGradeValue (List<Grade> results, GradeOrTokenAnnotation [] annotations, int currentAnnotation, DictTerm gleasonGradeTerm, SubHeading subsection, String label)
    {        

        int toLookAhead = 1;
        int toLookAt = toLookAhead + currentAnnotation;
        
        while (((toLookAt) < annotations.length) && 
                (toLookAhead < maxToLookBeyond) &&
                (isPunct(annotations[toLookAt]) || (!isZeroToTen (annotations[toLookAt].getAnnotation().getCoveredText()))))
        {
            toLookAhead += 1;
            toLookAt = toLookAhead + currentAnnotation;
        }
        
        if ((toLookAhead < maxToLookBeyond) &&
            (toLookAt < annotations.length) &&
            (isZeroToTen (annotations[toLookAt].getAnnotation().getCoveredText())))
        {
            Annotation gleason = annotations[toLookAt].getAnnotation();
            results.add (createNewGrade (label, gleason.getCoveredText(), (label.equals(Grades.totalGleasonGradeLabel) ? GLEASON_MAX_TOTAL_VALUE : GLEASON_MAX_SINGLE_VALUE), gleason.getBegin(), gleason.getEnd(), subsection.getSubSectionNumber()));

        }
        return toLookAt;
    }
    /**
     * patterns:
     *  x
     *  x+y
     *  x+y=z
     *  z (x+y)
     *  
     * @param results
     * @param currentAnnotation 
     * @param annotations 
     * @param gleasonGradeTerm
     * @param subsection
     */
    private int findGleasonGradeValues (List<Grade> results, GradeOrTokenAnnotation [] annotations, int currentAnnotation, DictTerm gleasonGradeTerm, SubHeading subsection)
    {
        int toLookAhead = 1;
        int toLookAt = toLookAhead + currentAnnotation;
        
        // look ahead, if possible
        while (((toLookAt) < annotations.length) && 
                (toLookAhead < maxToLookBeyond) &&
                (isPunct(annotations[toLookAt]) || (!isZeroToTen (annotations[toLookAt].getAnnotation().getCoveredText()))))
        {
            // if hit another grade descriptor, done
            if (annotations[toLookAt].isGrade())
            {
                // want to look at this descriptor again
                return toLookAt - 1;
            }
            //System.err.println("Skipping: '" + annotations[toLookAt].getAnnotation().getCoveredText() + "'");
            toLookAhead += 1;
            toLookAt = toLookAhead + currentAnnotation;
        }
        
        //System.err.println("Starting with: '" + annotations[toLookAt].getAnnotation().getCoveredText() + "'");
        if ((toLookAhead < maxToLookBeyond) &&
            (toLookAt < annotations.length) &&
            (isZeroToTen (annotations[toLookAt].getAnnotation().getCoveredText())))
        {
            Annotation gleason1 = annotations[toLookAt].getAnnotation();
            Annotation gleason2 = null;
                
            /**
             * patterns:
             *  x+y
             *  x+y=z
             *  
             */
            toLookAt += 1;
            if ((toLookAt + 1 < annotations.length) &&
                annotations[toLookAt].getAnnotation().getCoveredText().equals("+") &&
                isZeroToTen (annotations[toLookAt + 1].getAnnotation().getCoveredText()))
            {
                toLookAt += 1;
                gleason2 = annotations[toLookAt].getAnnotation();

                results.add (createNewGrade (Grades.primaryGleasonGradeLabel, gleason1.getCoveredText(), GLEASON_MAX_SINGLE_VALUE, gleason1.getBegin(), gleason1.getEnd(), subsection.getSubSectionNumber()));
                
                results.add (createNewGrade (Grades.secondaryGleasonGradeLabel, gleason2.getCoveredText(), GLEASON_MAX_SINGLE_VALUE, gleason2.getBegin(), gleason2.getEnd(), subsection.getSubSectionNumber()));
                    
                toLookAt += 1;
                
                if ((toLookAt + 1 < annotations.length) &&
                    annotations[toLookAt].getAnnotation().getCoveredText().equals("=") &&
                    (isZeroToTen (annotations[toLookAt + 1].getAnnotation().getCoveredText()) ||
                     isZeroToTenOverZeroToTen (annotations[toLookAt + 1].getAnnotation().getCoveredText())))
                {
                    Annotation totalGleason;
                    String totalGleasonValue;
                    if (isZeroToTen (annotations[toLookAt + 1].getAnnotation().getCoveredText()))
                    {
                        toLookAt += 1;
                        totalGleason = annotations[toLookAt].getAnnotation();
                        totalGleasonValue = totalGleason.getCoveredText();
                    }
                    else
                    {
                        toLookAt += 1;
                        totalGleason = annotations[toLookAt].getAnnotation();
                        int slash = totalGleason.getCoveredText().indexOf('/');
                        totalGleasonValue = totalGleason.getCoveredText().substring(0,slash);
                    }
                    results.add (createNewGrade (Grades.totalGleasonGradeLabel, totalGleasonValue, GLEASON_MAX_TOTAL_VALUE, totalGleason.getBegin(), totalGleason.getEnd(), subsection.getSubSectionNumber()));
                }
                else
                {
                    int total = Integer.parseInt (gleason1.getCoveredText()) + Integer.parseInt (gleason2.getCoveredText());
                    results.add (createNewGrade (Grades.totalGleasonGradeLabel, "" + total, GLEASON_MAX_TOTAL_VALUE, gleason1.getBegin(), gleason2.getEnd(), subsection.getSubSectionNumber()));
                }
            }
            /**
             * pattern:
             *  z (x+y)
             */  
            else if ((toLookAt + 3 < annotations.length) &&
                    annotations[toLookAt].getAnnotation().getCoveredText().equals("(") &&
                    isZeroToTen (annotations[toLookAt + 1].getAnnotation().getCoveredText()) &&
                    annotations[toLookAt + 2].getAnnotation().getCoveredText().equals("+") &&
                    isZeroToTen (annotations[toLookAt + 3].getAnnotation().getCoveredText()))
            {
                Annotation totalGleason = gleason1;
                gleason1 = annotations[toLookAt + 1].getAnnotation();
                gleason2 = annotations[toLookAt + 3].getAnnotation();
                results.add (createNewGrade (Grades.totalGleasonGradeLabel, totalGleason.getCoveredText(), GLEASON_MAX_TOTAL_VALUE, totalGleason.getBegin(), totalGleason.getEnd(), subsection.getSubSectionNumber()));
                results.add (createNewGrade (Grades.primaryGleasonGradeLabel, gleason1.getCoveredText(), GLEASON_MAX_SINGLE_VALUE, gleason1.getBegin(), gleason1.getEnd(), subsection.getSubSectionNumber()));
                results.add (createNewGrade (Grades.secondaryGleasonGradeLabel, gleason2.getCoveredText(), GLEASON_MAX_SINGLE_VALUE, gleason2.getBegin(), gleason2.getEnd(), subsection.getSubSectionNumber()));
                toLookAt += 3;
            }
            /**
             * pattern:
             *  x
             */  
            else
            {
                results.add (createNewGrade (Grades.totalGleasonGradeLabel, gleason1.getCoveredText(), GLEASON_MAX_TOTAL_VALUE, gleason1.getBegin(), gleason1.getEnd(), subsection.getSubSectionNumber()));
            }
        }
        
        // otherwise look back
        //toLookAt = currentAnnotation - 1;
        
        return toLookAt;
    }

    
    /**
     * @param coveredText
     * @return
     */
    private boolean isZeroToTenOverZeroToTen (String text)
    {
        Matcher numericMatcher = zeroToTenOverZeroToTenPattern.matcher (text);
        return numericMatcher.matches();
    }

    /**
     * @param secondary_gleason_grade2
     * @param coveredText
     * @param begin
     * @param end
     * @param subsection 
     * @return
     */
    private Grade createNewGrade (String gradeName, String gradeValue, String maxValue, int begin, int end, int subsection)
    {
        Grade newGrade = new Grade ();
        newGrade.setGradeType(gradeName);
        // make sure there is not too much whitespace in the value
        newGrade.setValue(gradeValue.replaceAll("([\n\r\t]+ *)| ( )+", " "));
        newGrade.setMaxValue(maxValue);
        newGrade.setBegin(begin);
        newGrade.setEnd(end);
        newGrade.setSubsection (subsection);
        
        //System.err.println("***** Creating new GRADE: '" + gradeName + "', value: " + gradeValue);
        return newGrade;
    }

    /**
     * @param text
     * @return
     */
    private boolean isZeroToTen (String text)
    {
        Matcher numericMatcher = zeroToTenPattern.matcher (text);
        return numericMatcher.matches();
    }

    /**
     * @param annotation
     * @return
     */
    private boolean isPunct (GradeOrTokenAnnotation annotation)
    {
        //System.err.print("isPunct(): ");
        if (! annotation.isGrade())
        {
            AnnotationFS token = annotation.getAnnotation();
            
            if ((tokenTypeFeature != null) && (token.getIntValue (tokenTypeFeature) == 1))
            {
                //System.err.println("TRUE, text:'" + token.getCoveredText() + "'");
                return true;
            }
        }
        //System.err.println("FALSE");
        return false;
    }

    private boolean isGradeLevel (DictTerm gradeAnnotation)
    {
        return gradeAnnotation.getAttributeValue().equals(GRADE_LEVEL);
    }

    private boolean isBasicGrade (DictTerm gradeAnnotation)
    {
        return gradeAnnotation.getAttributeValue().equals(BASIC_GRADE);
    }
    
    private boolean isBasicGradeOrLevel (DictTerm gradeAnnotation)
    {
        return isGradeLevel (gradeAnnotation) || isBasicGrade (gradeAnnotation);
    }
}
