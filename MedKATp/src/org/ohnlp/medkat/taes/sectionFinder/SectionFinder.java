/*
 * $Id$
 */

package org.ohnlp.medkat.taes.sectionFinder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

import org.apache.uima.analysis_engine.ResultSpecification;
import org.apache.uima.analysis_engine.annotator.AnnotatorConfigurationException;
import org.apache.uima.analysis_engine.annotator.AnnotatorContext;
import org.apache.uima.analysis_engine.annotator.AnnotatorContextException;
import org.apache.uima.analysis_engine.annotator.AnnotatorInitializationException;
import org.apache.uima.analysis_engine.annotator.AnnotatorProcessException;
import org.apache.uima.analysis_engine.annotator.Annotator_ImplBase;
import org.apache.uima.analysis_engine.annotator.TextAnnotator;
import org.apache.uima.cas.Feature;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.TypeSystem;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.cas.CAS;

/**
 * @version $Revision$
 *
 * @author Michael Tanenblatt &lt;mtan@us.ibm.com&gt;
 * @author (c) Copyright 2006 IBM
 * @author All Rights Reserved
 */

public class SectionFinder
    extends Annotator_ImplBase
    implements TextAnnotator
{

    public class SectionComparator
        //J5.0 implements Comparator<Section>
        implements Comparator<Section>
    {

        /* (non-Javadoc)
         * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
         */
        //J5.0 public int compare (Section leftObj, Section rightObj)
        public int compare (Section left, Section right)
        {
            if (left.start < right.start)
            {
                return -1;
            }
            else if (left.start > right.start)
            {
                return 1;
            }
            else
            {
                return 0;
            }
        }

    }

    protected class Section
    {
        private String sectionHeading;
        private int start;
        private int end;

        /**
         * @param sectionHeading
         * @param start
         * @param end
         */
        public Section (String sectionHeading, int start, int end)
        {
            super ();
            this.sectionHeading = sectionHeading;
            this.start = start;
            this.end = end;
        }
    }
    
    protected class SectionMappingInfo
    {
        private String headingLabel;
        private String type;
        private Integer id;
        
        
        /**
         * @param headingLabel
         * @param type
         * @param id
         */
        public SectionMappingInfo (String headingLabel, String type, Integer id)
        {
            super ();
            this.headingLabel = headingLabel;
            this.type = type;
            this.id = id;
        }
        /**
         * @return Returns the headingLabel.
         */
        public String getHeadingLabel ()
        {
            return headingLabel;
        }
        /**
         * @param headingLabel The headingLabel to set.
         */
        public void setHeadingLabel (String headingLabel)
        {
            this.headingLabel = headingLabel;
        }
        /**
         * @return Returns the id.
         */
        public Integer getId ()
        {
            return id;
        }
        /**
         * @param id The id to set.
         */
        public void setId (Integer id)
        {
            this.id = id;
        }
        /**
         * @return Returns the type.
         */
        public String getType ()
        {
            return type;
        }
        /**
         * @param type The type to set.
         */
        public void setType (String type)
        {
            this.type = type;
        }
        
    }


            
    private String [] sectionHeadingStrings;
    //J5.0 private TreeSet<Section> sections;
    private TreeSet<Section> sections;

    private final String HEADERTEXT_FEATURE_NAME = "headerText";
    
    // list of headings that might be included within larger headings, e.g.:
    // "Items:" might be part of "Important Items:" or it might be its own section. 
    // Use this list to know which headings need to be checked for this situation.
    //J5.0 private Hashtable<String, String> subsumed;
    private Hashtable<String, String> subsumed;
    
    //private Hashtable sectionAnnotationTypes;
    private String [] sectionAnnotationNameArray;
    
    //J5.0 private Hashtable<String, Type> sectionAnnotations;
    private Hashtable<String, Type> sectionAnnotations;

    //J5.0 private Hashtable<Type, Feature> features;
    private Hashtable<Type, Feature> features;

    /**
     * Performs any startup tasks required by this annotator. This
     * implementation reads the configuration parmaeters and compiles the
     * regular expressions.
     * 
     * @see org.apache.uima.analysis_engine.annotator.JTextAnnotator#initialize(AnnotatorContext)
     */
    public void initialize (AnnotatorContext aContext)
        throws AnnotatorConfigurationException,
            AnnotatorInitializationException
    {
        super.initialize (aContext);
        //String [] sectionAnnotationTypeArray;

        try
        {
            //Retrieve configuration parameters
            sectionHeadingStrings = (String []) getContext ().getConfigParameterValue ("sectionHeadingStrings");
            
            sectionAnnotationNameArray = (String []) getContext ().getConfigParameterValue ("sectionAnnotations");
            
            //J5.0 subsumed = new Hashtable<String, String> ();
            subsumed = new Hashtable<String, String> ();

            for (int i = 0; i < sectionHeadingStrings.length; i++)
            {
                for (int j = i+1; j < sectionHeadingStrings.length; j++)
                {
                    if (sectionHeadingStrings[i].indexOf (sectionHeadingStrings[j]) != -1)
                    {
                        subsumed.put (sectionHeadingStrings[j], "");
                    }
                    else if (sectionHeadingStrings[j].indexOf (sectionHeadingStrings[i]) != -1)
                    {
                        subsumed.put (sectionHeadingStrings[i], "");
                    }
                }
            }
            
            
            //for (Enumeration e = subsumed.keys(); e.hasMoreElements(); )
            //{
            //    System.err.println ("Subsumed: " + (String) e.nextElement());
            //}
        }
        catch (AnnotatorContextException e)
        {
            throw new AnnotatorInitializationException (e);
        }
    }
    
    
    

    /* (non-Javadoc)
     * @see org.apache.uima.analysis_engine.annotator.Annotator_ImplBase#typeSystemInit(org.apache.uima.cas.TypeSystem)
     */
    public void typeSystemInit (TypeSystem typeSystem) throws AnnotatorInitializationException, AnnotatorConfigurationException
    {
        super.typeSystemInit (typeSystem);
        
        //J5.0 sectionAnnotations = new Hashtable<String, Type> ();
        sectionAnnotations = new Hashtable<String, Type> ();
        
        for (int i = 0; i < sectionHeadingStrings.length; i++)
        {
            sectionAnnotations.put (sectionHeadingStrings[i], typeSystem.getType (sectionAnnotationNameArray[i]));
        }

        //J5.0 features = new Hashtable<Type, Feature> ();
        features = new Hashtable<Type, Feature> ();
        //J5.0 Collection<Type> types = sectionAnnotations.values();
        Collection<Type> types = sectionAnnotations.values();
        //J5.0 Iterator<Type> typeIterator = types.iterator();
        Iterator<Type> typeIterator = types.iterator();

        while (typeIterator.hasNext())
        {
            Type type = typeIterator.next();
            features.put(type, type.getFeatureByBaseName (HEADERTEXT_FEATURE_NAME));
        }
    }




    /**
     * Invokes this annotator's analysis logic.
     * 
     * @param aJCas
     *            the JCas to process
     * @param aResultSpec
     *            A list of outputs that this annotator should produce. This is
     *            ignored in the current implementation.
     * 
     * @throws AnnotatorProcessException
     *             if a failure occurs during processing.
     * 
     * @see org.apache.uima.analysis_engine.annotator.JTextAnnotator#process(org.apache.uima.jcas.JCas, org.apache.uima.analysis_engine.ResultSpecification)
     */
    public void process (CAS tcas, ResultSpecification aResultSpec)
        throws AnnotatorProcessException
    {
        int i = 0;
        //J5.0 sections = new TreeSet<Section>(new SectionComparator ());
        sections = new TreeSet<Section> (new SectionComparator ());

        try
        {
            String docText = tcas.getDocumentText ();

            // identify the section heading strings in the text
            for (i = 0; i < sectionHeadingStrings.length; i++ )
            {
                int start = -1;
                while (-1 != (start = docText.indexOf (sectionHeadingStrings [i], start + 1)))
                {
                    sections.add (new Section (sectionHeadingStrings[i], start, -1));
                }
            }
            
            // remove any subsumed sections
            //J5.0 List<Section> toRemove = new ArrayList<Section> ();
            List<Section> toRemove = new ArrayList<Section> ();
            for (Iterator<Section> sectionIter = sections.iterator(); sectionIter.hasNext(); )
            {
                Section section = sectionIter.next ();
                if (isSubsumed (sections, section))
                {
                    toRemove.add(section);
                }
            }
            for (Iterator<Section> removeIter = toRemove.iterator(); removeIter.hasNext();)
            {
                sections.remove(removeIter.next ());
            }
            
            if (!sections.isEmpty())
            {
                //J5.0 Iterator<Section> sectionIterator = sections.iterator ();
                Iterator<Section> sectionIterator = sections.iterator ();
                Section prevSection = null;
                Section section = null;
            
                while (sectionIterator.hasNext ())
                {
                    prevSection = section;
                    section = sectionIterator.next ();
                    if (prevSection != null)
                    {
                        prevSection.end = section.start;
                        makeSectionAnnotation (tcas, prevSection);
                    }
                }
                section = sections.last ();
                section.end = docText.length ();
                makeSectionAnnotation (tcas, section);
            } 
        }
        catch (Exception e)
        {
            throw new AnnotatorProcessException (e);
        }
    }
    private void makeSectionAnnotation (CAS tcas, Section section)
    {
        //J5.0 Type type = sectionAnnotations.get (section.sectionHeading);
        Type type = sectionAnnotations.get (section.sectionHeading);
        //J5.0 Feature headingFeature = features.get (type);
        Feature headingFeature = features.get (type);
        AnnotationFS fs = tcas.createAnnotation(type, section.start, section.end);
        fs.setStringValue(headingFeature,section.sectionHeading);
        tcas.getIndexRepository().addFS(fs);


    }
    
    private boolean isSubsumed (TreeSet<Section> sections, Section sectionToCheck)
    {
        if (subsumed.containsKey(sectionToCheck.sectionHeading))
        {
            for (Iterator<Section> sectionIter = sections.iterator(); sectionIter.hasNext();)
            {
                Section s = sectionIter.next();
                if (sectionToCheck != s)
                {
                    //int length = s.sectionHeading.length();
                    if ((sectionToCheck.start >= s.start) && (sectionToCheck.start <= (s.start + s.sectionHeading.length())))
                    {
                        //System.err.println ("SUBSUMED! " + sectionToCheck.sectionHeading + " by " + s.sectionHeading);
                        return true;
                    }
                }
            }
        }
        
        return false;

    }

}

