/*
 * $Id$
 */

package org.ohnlp.medkat.taes.subSubsectionDetector;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import org.apache.uima.analysis_engine.annotator.AnnotatorConfigurationException;
import org.apache.uima.analysis_engine.annotator.AnnotatorContext;
import org.apache.uima.analysis_engine.annotator.AnnotatorContextException;
import org.apache.uima.analysis_engine.annotator.AnnotatorInitializationException;
import org.apache.uima.cas.FSIndexRepository;
import org.apache.uima.cas.Feature;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.TypeSystem;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.cas.CAS;
import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.ohnlp.medkat.logger.Logger;
import org.ohnlp.medkat.taes.subsectionDetector.SubHeading;
import org.ohnlp.medkat.taes.subsectionProcessor.SubsectionProcessor;

/**
 * @version $Revision$
 *
 * @author Michael Tanenblatt &lt;mtan@us.ibm.com&gt;
 * @author (c) Copyright 2006 IBM
 * @author All Rights Reserved
 */

public class SubSubsectionDetector
        extends SubsectionProcessor
{

    public class SubSubSectionInfo
    {
        private int begin;
        private int end;
        private String label;
        private String concept;
        private Type annotationType;
        private Annotation parent;
        
        
        /**
         * @return Returns the parent.
         */
        protected Annotation getParent ()
        {
            return parent;
        }
        /**
         * @param parent The parent to set.
         */
        protected void setParent (Annotation parent)
        {
            this.parent = parent;
        }
        /**
         * @return Returns the label.
         */
        protected String getLabel ()
        {
            return label;
        }
        /**
         * @param label The label to set.
         */
        protected void setLabel (String label)
        {
            this.label = label;
        }
        /**
         * @return Returns the concept.
         */
        protected String getConcept ()
        {
            return concept;
        }
        /**
         * @param concept The concept to set.
         */
        protected void setConcept (String concept)
        {
            this.concept = concept;
        }
        /**
         * @return Returns the annotationType.
         */
        protected Type getAnnotationType ()
        {
            return annotationType;
        }
        /**
         * @param annotationType The annotationType to set.
         */
        protected void setAnnotationType (Type annotationType)
        {
            this.annotationType = annotationType;
        }
        /**
         * @return Returns the begin.
         */
        protected int getBegin ()
        {
            return begin;
        }
        /**
         * @param begin The begin to set.
         */
        protected void setBegin (int begin)
        {
            this.begin = begin;
        }
        /**
         * @return Returns the end.
         */
        protected int getEnd ()
        {
            return end;
        }
        /**
         * @param end The end to set.
         */
        protected void setEnd (int end)
        {
            this.end = end;
        }
        
        
    }
    public class SubSubSectionComparator
        //J5.0 implements Comparator<SubSubSectionInfo>
        implements Comparator<SubSubSectionInfo>
    {
    
        /* (non-Javadoc)
         * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
         */
        //J5.0 public int compare (SubSubSectionInfo leftObj, SubSubSectionInfo rightObj)
        public int compare (SubSubSectionInfo left, SubSubSectionInfo right)
        {
            if (left.begin < right.begin)
            {
                return -1;
            }
            else if (left.begin > right.begin)
            {
                return 1;
            }
            else
            {
                return 0;
            }
        }
    }


    private static final String PARAM_SUB_SUB_SECTION_ANNOTATIONS = "subSubSectionAnnotations";
    private static final String PARAM_SUB_SUB_SECTION_ANNOTATION_LABELS = "subSubSectionAnnotationLabels";
    private static final String PARAM_SUB_SUB_SECTION_CONCEPTS = "subSubSectionConcepts";
    private String [] subSubSectionAnnotationNames;
    private Type [] subSubSectionAnnotations;
    private String [] subSubSectionAnnotationLabels;
    private Pattern [] subSubSectionAnnotationLabelPatterns;
    private String [] subSubSectionConcepts;
    private Feature labelFeature;
    private Feature parentFeature;
    private Feature conceptFeature;
    
    private Logger logger;

    /* (non-Javadoc)
     * @see org.ohnlp.medkat.taes.subsectionProcessor.SubsectionProcessor#initialize(org.apache.uima.analysis_engine.annotator.AnnotatorContext)
     */
    public void initialize (AnnotatorContext annotatorContext) throws AnnotatorInitializationException, AnnotatorConfigurationException
    {
        super.initialize (annotatorContext);
        try
        {
            logger = new Logger (annotatorContext.getLogger ());

            subSubSectionAnnotationNames = (String []) getContext ().getConfigParameterValue (PARAM_SUB_SUB_SECTION_ANNOTATIONS);
            subSubSectionAnnotationLabels = (String []) getContext ().getConfigParameterValue (PARAM_SUB_SUB_SECTION_ANNOTATION_LABELS);
            subSubSectionConcepts = (String []) getContext ().getConfigParameterValue (PARAM_SUB_SUB_SECTION_CONCEPTS);
            
            if (subSubSectionAnnotationLabels.length != subSubSectionAnnotationNames.length)
            {
                logger.logError ("Parameter array length mismatch. Number of entries in array specified by '" + PARAM_SUB_SUB_SECTION_ANNOTATIONS + "' = " + subSubSectionAnnotationNames.length);
                logger.logError ("But number of entries in array specified by '" + PARAM_SUB_SUB_SECTION_ANNOTATION_LABELS + "' = " + subSubSectionAnnotationLabels.length);
                throw new AnnotatorContextException ();
            }

            subSubSectionAnnotationLabelPatterns = new Pattern [subSubSectionAnnotationLabels.length];
            for (int i = 0; i < subSubSectionAnnotationLabels.length; i++)
            {
                subSubSectionAnnotationLabelPatterns[i] = Pattern.compile("^\\p{Blank}*" + subSubSectionAnnotationLabels[i], Pattern.MULTILINE + Pattern.CASE_INSENSITIVE);
            }
        }
        catch (AnnotatorContextException e)
        {
            e.printStackTrace();
            throw new AnnotatorConfigurationException ();
        }

    }

    /* (non-Javadoc)
     * @see org.ohnlp.medkat.taes.subsectionProcessor.SubsectionProcessor#typeSystemInit(org.apache.uima.cas.TypeSystem)
     */
    public void typeSystemInit (TypeSystem typeSystem) throws AnnotatorInitializationException, AnnotatorConfigurationException
    {
        super.typeSystemInit (typeSystem);
        
        subSubSectionAnnotations = new Type [subSubSectionAnnotationNames.length];
        for (int i = 0; i < subSubSectionAnnotations.length; i++)
        {
            subSubSectionAnnotations [i] = typeSystem.getType (subSubSectionAnnotationNames[i]);
            //System.err.println ("***Subsubsection label: '" + subSubSectionAnnotationLabels[i] + "', name: '" + subSubSectionAnnotationNames[i] + "', class: " + subSubSectionAnnotations [i]);
            if (subSubSectionAnnotations[i] == null)
            {
                logger.logError ("Type for '" + subSubSectionAnnotationNames[i] + "' not found");
                throw new AnnotatorInitializationException ();
            }
        }
        
        //TODO: un-hardcode these constants
        labelFeature = subSubSectionAnnotations[0].getFeatureByBaseName("label");
        parentFeature = subSubSectionAnnotations[0].getFeatureByBaseName("parent");
        conceptFeature = subSubSectionAnnotations[0].getFeatureByBaseName("concept");
    }

    /* (non-Javadoc)
     * @see org.ohnlp.medkat.taes.subsectionProcessor.SubsectionProcessor#processSubSection(org.ohnlp.medkat.taes.subsectionDetector.SubHeading)
     */
    protected void processSubSection (CAS tcas, JCas jcas, SubHeading subsection, boolean mergeResults, int begin, int end)
    {
        Set<SubSubSectionInfo> results = new TreeSet<SubSubSectionInfo> (new SubSubSectionComparator ());
        String text = subsection.getCoveredText();
        
        // find the beginnings
        for (int i = 0; i < subSubSectionAnnotationLabels.length; i++)
        {
            //System.err.println(">>>>> Searching for subsubsection: '" + subSubSectionAnnotationLabels[i] + "'");
            int start = -1;
            Matcher matcher = subSubSectionAnnotationLabelPatterns[i].matcher(text);
            
            while (matcher.find (start + 1))
            {
                start = matcher.start ();
                SubSubSectionInfo item = new SubSubSectionInfo ();
                item.setBegin(start + subsection.getBegin());
                item.setLabel(matcher.group());
                item.setParent(subsection);
                item.setAnnotationType(subSubSectionAnnotations[i]);
                item.setConcept(subSubSectionConcepts[i]);
                
                //System.err.println("NEW SUBSUBSECTION, label: '" + subSubSectionAnnotationLabels[i]+ "', begin: " + item.getBegin() + ", type: " + item.getAnnotationType() + ", concept: '" + item.getConcept() + "'");
                results.add(item);
            }
        }
        
        if (! results.isEmpty())
        {
            // find the ends
            Iterator<SubSubSectionInfo> itemIterator = results.iterator();
            SubSubSectionInfo prev = null;
            SubSubSectionInfo item = null;
            while (itemIterator.hasNext())
            {
                item = itemIterator.next();
                if (prev != null)
                {
                    prev.setEnd(item.getBegin());
                }
                prev = item;
            }
            // the final one ends at the end of the subsection
            item.setEnd(subsection.getEnd());
            
            // create annotations and attach to subsection
            subsection.setSubSubsections(makeSubSubSectionAnnotations (tcas, jcas, results));
        }
    }

    /**
     * @param tcas
     * @param jcas
     * @param subsubsections
     * @return
     */
    private FSArray makeSubSubSectionAnnotations (CAS tcas, JCas jcas, Set<SubSubSectionInfo> subsubsections)
    {
        FSArray results = new FSArray (jcas, subsubsections.size());
        int i = 0;
        FSIndexRepository indices = tcas.getIndexRepository();
        Iterator<SubSubSectionInfo> itemIterator = subsubsections.iterator();
        while (itemIterator.hasNext())
        {
            SubSubSectionInfo item = itemIterator.next();
            AnnotationFS fs = tcas.createAnnotation(item.getAnnotationType(), item.getBegin(), item.getEnd());
            fs.setStringValue(labelFeature, item.getLabel());
            fs.setStringValue(conceptFeature, item.getConcept());
            fs.setFeatureValue(parentFeature, item.getParent());
            indices.addFS(fs);
            results.set(i, fs);
            i += 1;
        }
        return results;
    }

}

