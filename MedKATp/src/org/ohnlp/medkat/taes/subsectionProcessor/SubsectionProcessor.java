/*
 * $Id$
 */

package org.ohnlp.medkat.taes.subsectionProcessor;


import java.util.HashSet;
import java.util.Set;

import org.apache.uima.analysis_engine.ResultSpecification;
import org.apache.uima.analysis_engine.annotator.AnnotatorConfigurationException;
import org.apache.uima.analysis_engine.annotator.AnnotatorContext;
import org.apache.uima.analysis_engine.annotator.AnnotatorContextException;
import org.apache.uima.analysis_engine.annotator.AnnotatorInitializationException;
import org.apache.uima.analysis_engine.annotator.AnnotatorProcessException;
import org.apache.uima.analysis_engine.annotator.Annotator_ImplBase;
import org.apache.uima.analysis_engine.annotator.TextAnnotator;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.ConstraintFactory;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.cas.FSIntConstraint;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.FSMatchConstraint;
import org.apache.uima.cas.Feature;
import org.apache.uima.cas.FeaturePath;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.TypeSystem;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.cas.CAS;
import org.apache.uima.jcas.JFSIndexRepository;
import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.ohnlp.medkat.logger.Logger;
import org.ohnlp.medkat.taes.sectionFinder.SectionAnnotation;
import org.ohnlp.medkat.taes.subSubsectionDetector.SubSubsection;
import org.ohnlp.medkat.taes.subsectionDetector.SubHeading;

/**
 * @version $Revision$
 *
 * @author Michael Tanenblatt &lt;mtan@us.ibm.com&gt;
 * @author (c) Copyright 2006 IBM
 * @author All Rights Reserved
 */

public abstract class SubsectionProcessor
        extends Annotator_ImplBase
        implements TextAnnotator
{
    
    /**
     * name of param in TAE descriptor that specifies the list of primary section annotation types' subsections to process
     */
    private static final String PARAM_PRIMARY_SECTION_ANNOTATIONS = "PrimarySectionAnnotations";

    /**
     * name of param in TAE descriptor that specifies the list of secondary section annotation types' subsections to process
     */
    private static final String PARAM_SECONDARY_SECTION_ANNOTATIONS = "SecondarySectionAnnotations";


    /**
     * name of param in TAE descriptor that specifies the list of subsubsection annotation types to exclude from processing
     * i.e., subsubsections to not look within, e.g.: "NOTES:"
     */
    private static final String PARAM_EXCLUDED_SUB_SUBSECTIONS = "ExcludedSubSubsections";

    /*
     * names of the section annotations, as specified using the parameter "PARAM_PRIMARY_SECTION_ANNOTATIONS"
     */
    private String [] primaryDocumentSectionAnnotationNames;

    /*
     * names of the section annotations, as specified using the parameter "PARAM_SECONDARY_SECTION_ANNOTATIONS"
     */
    private String [] secondaryDocumentSectionAnnotationNames;

    /*
     * the actual CAS types derived from the names listed in 'primaryDocumentSectionAnnotationNames' (and kept in parallel with it)
     */
    private Type [] primaryDocumentSectionAnnotations;

    /*
     * the actual CAS types derived from the names listed in 'secondaryDocumentSectionAnnotationNames' (and kept in parallel with it)
     */
    private Type [] secondaryDocumentSectionAnnotations;

    /*
     * maximum number of subsections in the document
     */
    private int numSubsections;

    /*
     * array of indices for the primaryDocumentSectionAnnotations
     */
    private FSIndex [] primaryDocumentSectionAnnotationIndexes;
    private FSIndex [] secondaryDocumentSectionAnnotationIndexes;


    private String [] excludedSubSubsectionNames;

    private Set<Type> excludedSubSubsections;


    private Type subSectionType;

    private Logger logger;


    /**
     * OPTIONAL type of annotation to require in subsection to allow processing of the subsection.
     * For example, it might be necessary to have a diagnosis within a subsection for any other processing of that subsection to be valid
     * 
     * The feature must be an integer, and it must indicate the subsection number, as described below. 
     * 
     * The way these things work:
     * The specified feature for each annotations is considered, and if it contains a value that matches a particular subsectionID,
     * then that subsection will be considered as meeting the constraint. For example, if there is an annotation "Diagnosis" and it has a
     * feature "specimenNumber" = 2, then all the subsections within a document that have the id = 2 would be considered to meet the constraint.
     * In other words, the idea is, for example, to provide a simple way to only process parts of a report that require a diagnosis,
     * such as the largest tumor size, when there is actually an associated diagnosis. 
     */
    private static final String PARAM_CONSTRAINT_ANNOTATIONS = "ConstrainingAnnotations";
    private String [] constraintAnnotationNames;
    private Type [] constraintAnnotations;
    private static final String PARAM_CONSTRAINT_ANNOTATION_FEATURES = "ConstrainingAnnotationFeatures";
    private String [] constraintAnnotationFeatureNames;
    private Feature [] constraintAnnotationFeatures;

    private static final String PARAM_ANNOTATOR_NAME = "AnnotatorName";

    private String annotatorName;


    /**
     * @return Returns the annotatorName.
     */
    protected String getAnnotatorName ()
    {
        return annotatorName;
    }



    /**
     * @param annotatorName The annotatorName to set.
     */
    protected void setAnnotatorName (String annotatorName)
    {
        this.annotatorName = annotatorName;
    }


    /**
     * @return Returns the maximum number of Subsections across all given document sections.
     */
    public int getNumSubsections ()
    {
        return numSubsections;
    }


    /**
     * @param numSubsections The numSubsections to set.
     */
    public void setNumSubsections (int numSubsections)
    {
        this.numSubsections = numSubsections;
    }


    /**
     * @return Returns the primaryDocumentSectionAnnotations.
     */
    protected Type [] getPrimaryDocumentSectionAnnotations ()
    {
        return primaryDocumentSectionAnnotations;
    }


    /**
     * @param primaryDocumentSectionAnnotations The primaryDocumentSectionAnnotations to set.
     */
    protected void setPrimaryDocumentSectionAnnotations (Type [] documentSectionAnnotations)
    {
        this.primaryDocumentSectionAnnotations = documentSectionAnnotations;
    }


    /**
     * @return Returns the primaryDocumentSectionAnnotationIndexes.
     */
    protected FSIndex [] getPrimaryDocumentSectionAnnotationIndexes ()
    {
        return primaryDocumentSectionAnnotationIndexes;
    }


    /**
     * @param primaryDocumentSectionAnnotationIndexes The primaryDocumentSectionAnnotationIndexes to set.
     */
    protected void setPrimaryDocumentSectionAnnotationIndexes (FSIndex [] documentSectionAnnotationIndexes)
    {
        this.primaryDocumentSectionAnnotationIndexes = documentSectionAnnotationIndexes;
    }


    /**
     * @return Returns the secondaryDocumentSectionAnnotationIndexes.
     */
    protected FSIndex [] getSecondaryDocumentSectionAnnotationIndexes ()
    {
        return secondaryDocumentSectionAnnotationIndexes;
    }


    /**
     * @param secondaryDocumentSectionAnnotationIndexes The secondaryDocumentSectionAnnotationIndexes to set.
     */
    protected void setSecondaryDocumentSectionAnnotationIndexes (FSIndex [] secondaryDocumentSectionAnnotationIndexes)
    {
        this.secondaryDocumentSectionAnnotationIndexes = secondaryDocumentSectionAnnotationIndexes;
    }


    /**
     * @return Returns the secondaryDocumentSectionAnnotations.
     */
    protected Type [] getSecondaryDocumentSectionAnnotations ()
    {
        return secondaryDocumentSectionAnnotations;
    }


    /**
     * @param secondaryDocumentSectionAnnotations The secondaryDocumentSectionAnnotations to set.
     */
    protected void setSecondaryDocumentSectionAnnotations (Type [] secondaryDocumentSectionAnnotations)
    {
        this.secondaryDocumentSectionAnnotations = secondaryDocumentSectionAnnotations;
    }


    /**
     * @return Returns the excludedSubSubsections.
     */
    public Set<Type> getExcludedSubSubsections ()
    {
        return excludedSubSubsections;
    }


    /**
     * @param excludedSubSubsections The excludedSubSubsections to set.
     */
    public void setExcludedSubSubsections (Set<Type> excludedSubSubsections)
    {
        this.excludedSubSubsections = excludedSubSubsections;
    }


    /**
     * @return Returns the subSectionType.
     */
    public Type getSubSectionType ()
    {
        return subSectionType;
    }


    /**
     * @param subSectionType The subSectionType to set.
     */
    public void setSubSectionType (Type subSectionType)
    {
        this.subSectionType = subSectionType;
    }

    protected boolean [] constraints;

    
    /**
     * @return Returns the constraintAnnotations.
     */
    protected Type [] getConstraintAnnotations ()
    {
        return constraintAnnotations;
    }


    /**
     * @param constraintAnnotations The constraintAnnotations to set.
     */
    protected void setConstraintAnnotations (Type [] constraintAnnotations)
    {
        this.constraintAnnotations = constraintAnnotations;
    }

    
    /**
     * @return Returns the constraintAnnotations.
     */
    protected Type getConstraintAnnotation (int index)
    {
        return constraintAnnotations[index];
    }


    /**
     * @param constraintAnnotations The constraintAnnotations to set.
     */
    protected void setConstraintAnnotation (int index, Type constraintAnnotation)
    {
        this.constraintAnnotations[index] = constraintAnnotation;
    }


    /**
     * @return Returns the constraintAnnotationFeature.
     */
    protected Feature getConstraintAnnotationFeature (int index)
    {
        return constraintAnnotationFeatures[index];
    }


    /**
     * @param constraintAnnotationFeature The constraintAnnotationFeature to set.
     */
    protected void setConstraintAnnotationFeature (int index, Feature constraintAnnotationFeature)
    {
        this.constraintAnnotationFeatures[index] = constraintAnnotationFeature;
    }


    /**
     * @return Returns the constraintAnnotationFeature.
     */
    protected Feature [] getConstraintAnnotationFeatures ()
    {
        return constraintAnnotationFeatures;
    }


    /**
     * @param constraintAnnotationFeature The constraintAnnotationFeature to set.
     */
    protected void setConstraintAnnotationFeatures (Feature [] constraintAnnotationFeatures)
    {
        this.constraintAnnotationFeatures = constraintAnnotationFeatures;
    }




    /**
     * @return Returns the constraints.
     */
    protected boolean [] getConstraints ()
    {
        return constraints;
    }


    /**
     * @param constraints The constraints to set.
     */
    protected void setConstraints (boolean [] constraints)
    {
        this.constraints = constraints;
    }


    /* (non-Javadoc)
     * @see org.apache.uima.analysis_engine.annotator.Annotator_ImplBase#initialize(org.apache.uima.analysis_engine.annotator.AnnotatorContext)
     * 
     * in addition to default processing, loads arrays from parameter settings:
     *  - primaryDocumentSectionAnnotationNames:   names of the section annotations to process
     *  - excludedSubSubsectionNames:       names of subsections within the section annotations to exclude from processing (e.g., "NOTE:")
     *
     *  In addition, there are two OPTIONAL parameters, containing type of annotation to require in subsection to allow processing of the subsection
     *  as well as associated Features.
     *  For example, it might be necessary to have a diagnosis within a subsection for any other processing of that subsection to be valid
     *  
     *  - constraintAnnotationNames:        names of constraining annotations
     *  - constraintAnnotationFeatureNames: names of features of constraining annotations
     * 
     */
    public void initialize (AnnotatorContext annotatorContext) throws AnnotatorInitializationException, AnnotatorConfigurationException
    {
        super.initialize (annotatorContext);
        
        try
        {
            logger = new Logger (annotatorContext.getLogger ());

            primaryDocumentSectionAnnotationNames = (String []) getContext ().getConfigParameterValue (PARAM_PRIMARY_SECTION_ANNOTATIONS);
            secondaryDocumentSectionAnnotationNames = (String []) getContext ().getConfigParameterValue (PARAM_SECONDARY_SECTION_ANNOTATIONS);
            excludedSubSubsectionNames =  (String []) getContext ().getConfigParameterValue (PARAM_EXCLUDED_SUB_SUBSECTIONS);
            constraintAnnotationNames = (String []) getContext ().getConfigParameterValue (PARAM_CONSTRAINT_ANNOTATIONS);
            constraintAnnotationFeatureNames = (String []) getContext ().getConfigParameterValue (PARAM_CONSTRAINT_ANNOTATION_FEATURES);
            setAnnotatorName ((String) getContext().getConfigParameterValue(PARAM_ANNOTATOR_NAME));

        }
        catch (AnnotatorContextException e)
        {
            e.printStackTrace();
            throw new AnnotatorConfigurationException ();
        }

    }


    /* (non-Javadoc)
     * @see org.apache.uima.analysis_engine.annotator.Annotator_ImplBase#typeSystemInit(org.apache.uima.cas.TypeSystem)
     */
    public void typeSystemInit (TypeSystem typeSystem) throws AnnotatorInitializationException, AnnotatorConfigurationException
    {
        super.typeSystemInit (typeSystem);
        
        primaryDocumentSectionAnnotations = new Type[primaryDocumentSectionAnnotationNames.length];

        for (int i = 0; i < primaryDocumentSectionAnnotationNames.length; i++)
        {
            primaryDocumentSectionAnnotations [i] = typeSystem.getType (primaryDocumentSectionAnnotationNames[i]);
        }
        
        secondaryDocumentSectionAnnotations = null;
        if (secondaryDocumentSectionAnnotationNames != null)
        {
            secondaryDocumentSectionAnnotations = new Type[secondaryDocumentSectionAnnotationNames.length];
    
            for (int i = 0; i < secondaryDocumentSectionAnnotationNames.length; i++)
            {
                secondaryDocumentSectionAnnotations [i] = typeSystem.getType (secondaryDocumentSectionAnnotationNames[i]);
            }
        }
        
        excludedSubSubsections = new HashSet<Type> ();
        
        if (excludedSubSubsectionNames != null)
        {
            for (int i = 0; i < excludedSubSubsectionNames.length; i++)
            {
                excludedSubSubsections.add (typeSystem.getType (excludedSubSubsectionNames[i]));
            }
        }        
        if (constraintAnnotationNames != null)
        {
            if (constraintAnnotationNames.length != constraintAnnotationFeatureNames.length)
            {
                logger.logError ("Length of param arrays '" + PARAM_CONSTRAINT_ANNOTATIONS + "' and '" + PARAM_CONSTRAINT_ANNOTATION_FEATURES + "' must be same length");
                throw new AnnotatorConfigurationException ();
            }
            setConstraintAnnotations (new Type [constraintAnnotationNames.length]);
            for (int i = 0; i < constraintAnnotationNames.length; i++)
            {
                setConstraintAnnotation (i, typeSystem.getType (constraintAnnotationNames[i]));
                if (getConstraintAnnotation (i) == null)
                {
                    logger.logError ("Could not find annotation type: '" + constraintAnnotationNames[i] + "' in typeSystem");
                    throw new AnnotatorConfigurationException ();
                }
                else
                {
                    //System.err.println("adding annotation[" + i + "] '" + getConstraintAnnotation (i).getName() + "'");
                }
            }
            if (constraintAnnotationFeatureNames == null)
            {
                logger.logError ("Must specify feature name for annotation types specified in parameter: '" + PARAM_CONSTRAINT_ANNOTATIONS + "'");
                throw new AnnotatorConfigurationException ();
            }
            setConstraintAnnotationFeatures (new Feature [constraintAnnotationFeatureNames.length]);
            for (int i = 0; i < constraintAnnotationFeatureNames.length; i++)
            {
                setConstraintAnnotationFeature (i, getConstraintAnnotation (i).getFeatureByBaseName (constraintAnnotationFeatureNames[i]));
            
                if (getConstraintAnnotationFeature (i) == null)
                {
                    logger.logError ("Could not find feature '" + constraintAnnotationFeatureNames[i] + "' for annotation type: '" + constraintAnnotationNames[i] + "' in typeSystem");
                    throw new AnnotatorConfigurationException ();
                }
                else
                {
                    //System.err.println("adding feature[" + i + "] '" + getConstraintAnnotationFeature (i).getName() + "'");
                }
            }
            
            //for (int i = 0; i < getConstraintAnnotations().length; i++)
            //{
            //    System.err.println("Type[" + i + "] '" + getConstraintAnnotation (i).getName() + "'");
            //    System.err.println("Feature[" + i + "] '" + getConstraintAnnotationFeature (i).getName() + "'");
            //}
        }

    }


    /* (non-Javadoc)
     * @see org.apache.uima.analysis_engine.annotator.TextAnnotator#process(org.apache.uima.cas.CAS, org.apache.uima.analysis_engine.ResultSpecification)
     * 
     * Processing is as follows:
     * 
     * 1) indices for all specified document sections are found
     * 2) the max number of subsections across all specified sections is computed
     * 3) next, the initializeProcess() method is called
     * 4) then, for each section, execute the processSubSection() method for each of the section's subsections
     * 5) finally, run the postProcess() method
     * 
     */
    public void process (CAS tcas, ResultSpecification resultSpecification)
        throws AnnotatorProcessException
    {

 		logger.setupDocument (tcas);

        primaryDocumentSectionAnnotationIndexes = new FSIndex [primaryDocumentSectionAnnotations.length];
        for (int i = 0; i < primaryDocumentSectionAnnotations.length; i++)
        {
            primaryDocumentSectionAnnotationIndexes[i] = tcas.getAnnotationIndex (primaryDocumentSectionAnnotations[i]);
        }
        
        secondaryDocumentSectionAnnotationIndexes = null;
        if (secondaryDocumentSectionAnnotations != null)
        {
            secondaryDocumentSectionAnnotationIndexes = new FSIndex [secondaryDocumentSectionAnnotations.length];
            for (int i = 0; i < secondaryDocumentSectionAnnotations.length; i++)
            {
                secondaryDocumentSectionAnnotationIndexes[i] = tcas.getAnnotationIndex (secondaryDocumentSectionAnnotations[i]);
            }
        }
        
        JCas jcas;
        try
        {
            
            jcas = tcas.getJCas ();
            JFSIndexRepository jcasIndexes = jcas.getJFSIndexRepository ();
            AnnotationIndex subSectionIndex = (AnnotationIndex) jcasIndexes.getAnnotationIndex (SubHeading.typeIndexID);

            setNumSubsections (findNumOfSubsections (primaryDocumentSectionAnnotationIndexes, subSectionIndex));

            initializeProcess (tcas, jcas, jcasIndexes);
            
            processDocumentSections (tcas, jcas, primaryDocumentSectionAnnotationIndexes, subSectionIndex, null);
            
            if (secondaryDocumentSectionAnnotationIndexes != null)
            {
                //System.err.println("Num subsections: " + getNumSubsections());
                boolean [] anyResultsForSubsection = new boolean [getNumSubsections () + 1];
                processDocumentSections (tcas, jcas, secondaryDocumentSectionAnnotationIndexes, subSectionIndex, anyResultsForSubsection);
            }
            postProcess (tcas, jcas, jcasIndexes);
            
        }
        catch (CASException e)
        {
            e.printStackTrace ();
            throw new AnnotatorProcessException ();
        }
                    
    }


    /**
     * @param tcas
     * @param jcas
     * @param documentSectionAnnotationIndexes 
     * @param subSectionIndex
     * @param anyResultsForSubsection if null or false, no results previously computed for subsection, so overwrite with new results. If true, allow results to be merged with prev results
     */
    private void processDocumentSections (CAS tcas, JCas jcas, FSIndex [] documentSectionAnnotationIndexes, AnnotationIndex subSectionIndex, boolean [] anyResultsForSubsection)
    {
        for (int i = 0; i < documentSectionAnnotationIndexes.length; i++)
        {
            FSIndex docSectionIndex = documentSectionAnnotationIndexes [i];
            FSIterator docSectionIter = docSectionIndex.iterator ();

            // go through all document sections, looking for DictTerms with
            // matching SemClass features
            while (docSectionIter.hasNext ())
            {
                SectionAnnotation docSectionAnnotation = (SectionAnnotation) docSectionIter.next ();
                FSIterator subSectionIter = subSectionIndex.subiterator (docSectionAnnotation);

                //System.err.println("Section: " + docSectionAnnotation.getHeaderText());
                // go through subsections of this section
                while (subSectionIter.hasNext ())
                {
                    SubHeading subsection = (SubHeading) subSectionIter.next();
                    //System.err.println("SubSection contentBegin: " + subsection.getContentBegin() + ", prefix: " + subsection.getPrefix() + ", SubsectionNumber: " + subsection.getSubSectionNumber());
                    if (anyResultsForSubsection == null)
                    {
                        //if ((getAnnotatorName () != null) && getAnnotatorName ().equalsIgnoreCase("LymphSelector"))
                        //{
                        //    System.err.println("PROCESS WITHOUT MERGE, content only. subsection: " + subsection.getSubSectionNumber() + ", content " + subsection.getContent());
                        //}
                        processSubSection (tcas, jcas, subsection, false, subsection.getContentBegin(), subsection.getEnd());
                    }
                    else if ((subsection.getSubSectionNumber() < anyResultsForSubsection.length) && (! anyResultsForSubsection[subsection.getSubSectionNumber()]))
                    {
                        //if ((getAnnotatorName () != null) && getAnnotatorName ().equalsIgnoreCase("LymphSelector"))
                        //{
                        //    System.err.println("PROCESS WITHOUT MERGE, NOT content only. subsection: " + subsection.getSubSectionNumber() + ", covered text " + subsection.getCoveredText());
                        //}
                        processSubSection (tcas, jcas, subsection, false, subsection.getBegin(), subsection.getEnd());
                    }
                    else
                    {
                        //if ((getAnnotatorName () != null) && getAnnotatorName ().equalsIgnoreCase("LymphSelector"))
                        //{
                        //    System.err.println("PROCESS WITH MERGE. subsection: " + subsection.getSubSectionNumber() + ", covered text " + subsection.getCoveredText());
                        //}
                        processSubSection (tcas, jcas, subsection, true, subsection.getBegin(), subsection.getEnd());
                    }
                }
            }
        }
    }


    
    
    /**
     * @param subsectionIndex
     * 
     * tests if subsection with specified index meets constraints
     */
    protected boolean testAgainstConstraint (int subsectionIndex)
    {        
        //System.err.println("Testing constraint[" + subsectionIndex + "] = " + ((getConstraints() == null) || (getConstraints()[subsectionIndex])));
        boolean [] constraints = getConstraints();
        return ((constraints == null) || ((subsectionIndex < constraints.length) && constraints[subsectionIndex]));
    }
    

    
    /**
     * @param tcas
     * @param jcas
     * @param jcasIndexes
     * 
     * set up constraints array, if specified
     * 
     */
    protected void initializeProcess (CAS tcas, JCas jcas, JFSIndexRepository jcasIndexes)
    {
        subSectionType = jcas.getCasType(SubHeading.type);

        if (getConstraintAnnotations() == null)
        {
            setConstraints(null);
        }
        else
        {
            // allocate boolean "constraints" array, one entry per subsection
            setConstraints (new boolean [getNumSubsections()]);
            for (int i = 0; i < getConstraintAnnotations().length; i++)
            {
                FSIndex constrainingAnnotationIndex = tcas.getAnnotationIndex(getConstraintAnnotation(i));
                //System.err.println("Target TYPE[" + i + "]: " + getConstraintAnnotation(i).getName());
                Feature feature = getConstraintAnnotationFeature(i);
                //System.err.println("Target FEATURE[" + i + "]: " + getConstraintAnnotationFeature(i));
                //System.err.println("Target FEATURENAME[" + i + "]: " + getConstraintAnnotationFeature(i).getName());
                FSIterator constrainingAnnotationIter = constrainingAnnotationIndex.iterator();
                while (constrainingAnnotationIter.hasNext())
                {
                    FeatureStructure constrainer = (FeatureStructure) constrainingAnnotationIter.next();
                    //System.err.println("annotation type: '" + constrainer.getType().getName());
                    int index = constrainer.getIntValue(feature);
                    constraints[index] = true;
                }
            }
        }
        //if (constraints != null)
        //{
        //    for (int i = 0; i < constraints.length; i++)
        //    {
        //        System.err.println("Constraint[" + i + "] = " + constraints[i]);
        //    }
        //}

        return;
    }
    
    
    /**
     * @param jcas 
     * @param subsection
     * 
     * implement this to do any processing of a subsection within the process() method. Called once per subsection.
     */
    protected abstract void processSubSection (CAS tcas, JCas jcas, SubHeading subsection, boolean mergeResults, int begin, int end);
    
    
    /**
     * @param tcas
     * @param jcas
     * @param jcasIndexes
     * 
     * override this to do any postprocessing at the end of the process() method (e.g., generate annotations). Default method does nothing.
     */
    protected void postProcess (CAS tcas, JCas jcas, JFSIndexRepository jcasIndexes)
    {
        return;
    }


    /**
     * 
     * Find the highest number of subsection annotations, looking at all of the sections provided
     * 
     * @param primaryDocumentSectionAnnotationIndexes
     * @param subSectionIndex
     * @return max number of subsections across all section annotations
     */
    private int findNumOfSubsections (FSIndex [] documentSectionAnnotationIndexes, AnnotationIndex subSectionIndex)
    {
        int result = 0;
        
        for (int i = 0; i < documentSectionAnnotationIndexes.length; i++)
        {
            FSIndex index = documentSectionAnnotationIndexes[i];
            FSIterator docSectionIter = index.iterator ();

            while (docSectionIter.hasNext ())
            {
                SectionAnnotation docSectionAnnotation = (SectionAnnotation) docSectionIter.next ();
                FSIterator subSectionIter = subSectionIndex.subiterator (docSectionAnnotation);

                while (subSectionIter.hasNext ())
                {
                    SubHeading subsection = (SubHeading) subSectionIter.next();
                    //System.err.println ("PRIOR MAX SUBSECTION: " + result + ", candidate: " + subsection.getSubSectionNumber());
                    result = Math.max(result, subsection.getSubSectionNumber ());
                    //System.err.println ("NEW MAX SUBSECTION: " + result);
                }
            }
        }
        return result + 1;
    }


    /**
     * @param annotation the annotation to check
     * @param subsection the subsection to look in
     * @param excludedSubSubsections the set of subsubsections of the subsection to exclude annotations
     * @return true if annotation falls within an excluded subsubsection's bounds
     */
    protected boolean inExcludedSubSubsection (Annotation annotation, SubHeading subsection, Set<Type> excludedSubSubsections)
    {
        FSArray subsubs = subsection.getSubSubsections();
        if (subsubs != null)
        {
            for (int i = 0; i < subsubs.size (); i++)
            {
                SubSubsection subsub = (SubSubsection) subsubs.get(i);
                if (excludedSubSubsections.contains(subsub.getType ()))
                {
                    //System.err.println("subsub type: " + subsub.getType () + ", begin: " + subsub.getBegin() + ", end: " + subsub.getEnd());
                    //System.err.println("\tannotation begin: " + annotation.getBegin() + ", end: " + annotation.getEnd());
                    if ((annotation.getBegin() >= subsub.getBegin()) &&
                            (annotation.getEnd() <= subsub.getEnd()))
                    {
                        //System.err.println("\treturning TRUE!");
                        return true;
                    }
                }
            }
        }
        //System.err.println("\treturning FALSE!");
        return false;
    }


    /**
     * This is to workaround a bug(?) in UIMA where a subiterator of a subiterator does not always work as expected.
     * 
     * @param subsection
     * @param subsub
     * @return
     */
    protected FSIterator getBeginEndConstrainedIter (JCas jcas, FSIterator baseIter, Feature beginFeature, Feature endFeature, int begin, int end)
    {
        ConstraintFactory cf = jcas.getConstraintFactory(); 

        FeaturePath beginPath = jcas.createFeaturePath(); 
        beginPath.addFeature(beginFeature); 
        FSIntConstraint beginConstraint = cf.createIntConstraint(); 
        beginConstraint.geq(begin);         
        FSMatchConstraint okBegin = cf.embedConstraint(beginPath, beginConstraint); 

        FeaturePath endPath = jcas.createFeaturePath(); 
        endPath.addFeature(endFeature); 
        FSIntConstraint endConstraint = cf.createIntConstraint(); 
        endConstraint.leq(end);         
        FSMatchConstraint okEnd = cf.embedConstraint(endPath, endConstraint); 

        FSMatchConstraint okBounds = cf.and(okBegin, okEnd);
   
        return jcas.createFilteredIterator(baseIter, okBounds); 
   
    }

}

