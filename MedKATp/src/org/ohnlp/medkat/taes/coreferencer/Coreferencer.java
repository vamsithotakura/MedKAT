package org.ohnlp.medkat.taes.coreferencer;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;


import org.apache.uima.analysis_engine.annotator.AnnotatorConfigurationException;
import org.apache.uima.analysis_engine.annotator.AnnotatorContext;
import org.apache.uima.analysis_engine.annotator.AnnotatorInitializationException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Feature;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.TypeSystem;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.cas.CAS;
import org.apache.uima.jcas.JFSIndexRepository;
import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.ohnlp.medkat.common.UIMAAnnotationOffsetComparator;
import org.ohnlp.medkat.common.UIMAAnnotationUtils;
import org.ohnlp.medkat.taes.conceptMapper.DictTerm;
import org.ohnlp.medkat.taes.conceptMapper.DictTermMarkers;
import org.ohnlp.medkat.taes.subsectionDetector.SubHeading;
import org.ohnlp.medkat.taes.subsectionProcessor.BasicSubsectionProcessor;

public abstract class Coreferencer
        extends BasicSubsectionProcessor
{
    

	private static final Object ICDO_STRING = "ICDO";
    private static final String PARAM_GENERICTERMCODES = "GenericTermCodes";
    // contains a Map for each section (part). The map maps from a key that uniquely identifies coreference object (e.g., the portion of an ICD-O code before the dot) to a list of coreferring terms
    protected Map<String, Collection<DictTerm>> [] corefsForSection;
    protected AnnotationIndex dictTermIndex;
    private Feature dictTermBeginFeature;
    private Feature dictTermEndFeature;
    protected Set<String> genericTermCodes;

    private static final String PARAM_DICTTERMSEMCLASS = "SemClass";
    private String dictTermSemClass;

    private static final String PARAM_NEWANNOTATIONNAME = "NewAnnotationName";
    private String newAnnotationName;
    private Type newAnnotation;

    private static final String PARAM_NEWANNOTATIONFEATURENAME = "NewAnnotationFeatureName";
    private String newAnnotationFeatureName;
    private Feature newAnnotationFeature;

    private static final String PARAM_NEWANNOTATIONSECTIONFEATURENAME = "NewAnnotationSectionNumberFeatureName";
    private String newAnnotationSectionFeatureName;
    private Feature newAnnotationSectionFeature;

    

    /**
     * get the associated Type/Feature for each of the TAE descriptor parameters
     * 
     * @see org.apache.uima.analysis_engine.annotator.Annotator_ImplBase#typeSystemInit(org.apache.uima.cas.TypeSystem)
     */
    public void typeSystemInit (TypeSystem typeSystem) throws AnnotatorInitializationException, AnnotatorConfigurationException
    {
        super.typeSystemInit (typeSystem);
                
        newAnnotation = typeSystem.getType (newAnnotationName);
        if (newAnnotation == null)
        {
            //logger.logError ("Could not find annotation type: '" + newAnnotationName + "' in typeSystem");
            throw new AnnotatorConfigurationException ();
        }
        newAnnotationFeature = newAnnotation.getFeatureByBaseName (newAnnotationFeatureName);
        if (newAnnotationFeature == null)
        {
            //logger.logError ("Could not find feature '" + newAnnotationFeatureName + "' for annotation type: '" + newAnnotationName + "' in typeSystem");
            throw new AnnotatorConfigurationException ();
        }
        newAnnotationSectionFeature = newAnnotation.getFeatureByBaseName (newAnnotationSectionFeatureName);
        if (newAnnotationSectionFeature == null)
        {
            //logger.logError ("Could not find feature '" + newAnnotationFeatureName + "' for annotation type: '" + newAnnotationName + "' in typeSystem");
            throw new AnnotatorConfigurationException ();
        }
    }

    public void initialize (AnnotatorContext annotatorContext) throws AnnotatorInitializationException, AnnotatorConfigurationException
    {
        super.initialize(annotatorContext);

        genericTermCodes = new HashSet<String> ();
        try
        {
            String [] genericTermCodesArray = ((String []) getContext ().getConfigParameterValue (PARAM_GENERICTERMCODES));
            if (genericTermCodesArray != null)
            {
                genericTermCodes.addAll (Arrays.asList (genericTermCodesArray));
            }

            newAnnotationName = (String) getContext ().getConfigParameterValue (PARAM_NEWANNOTATIONNAME);
            newAnnotationFeatureName = (String) getContext ().getConfigParameterValue (PARAM_NEWANNOTATIONFEATURENAME);
            newAnnotationSectionFeatureName = (String) getContext ().getConfigParameterValue (PARAM_NEWANNOTATIONSECTIONFEATURENAME);
            dictTermSemClass = ((String) getContext ().getConfigParameterValue (PARAM_DICTTERMSEMCLASS));
        }
        catch (Exception e)
        {
            throw new AnnotatorConfigurationException (e);
        }


    }

    @SuppressWarnings("unchecked")
    protected void initializeProcess (CAS tcas, JCas jcas, JFSIndexRepository jcasIndexes)
    {
        super.initializeProcess(tcas, jcas, jcasIndexes);
        corefsForSection = new Map [getNumSubsections () + 1]; // add 1 since not zero based
        for (int i = 0; i < corefsForSection.length; i++)
        {
            corefsForSection[i] = new HashMap<String, Collection<DictTerm>> ();
        }
        dictTermIndex = (AnnotationIndex) jcasIndexes.getAnnotationIndex (DictTerm.typeIndexID);
        
        Type dictTermType = jcas.getCasType (DictTerm.type);
        dictTermBeginFeature = dictTermType.getFeatureByBaseName ("begin");
        dictTermEndFeature = dictTermType.getFeatureByBaseName ("end");

    }

    protected void processSubSection (CAS tcas,
                                      JCas jcas,
                                      SubHeading subsection,
                                      int begin,
                                      int end)
    {
        List<DictTerm> terms = new ArrayList<DictTerm> ();

        FSIterator baseDictTermIter = getBeginEndConstrainedIter (jcas, dictTermIndex.iterator (), dictTermBeginFeature, dictTermEndFeature, begin, end);
        while (baseDictTermIter.hasNext ())
        {
            DictTerm term = (DictTerm) baseDictTermIter.next ();
            if ((term.getSemClass ().equals (dictTermSemClass)) && (isOK_Marker (term)))
            {
                terms.add(term);
            }
        }

        Collections.sort(terms, new UIMAAnnotationOffsetComparator ());
        collectCorefsForSection(subsection, terms);
    }

    protected boolean isOK_Marker (DictTerm dictTerm)
    {
        int dictTermMarksToIgnore = (DictTermMarkers.IGNORED_INDICATOR     |
                                     DictTermMarkers.DUPLICATE_INDICATOR   |
                                     DictTermMarkers.SUBSUMED_INDICATOR    |
                                     DictTermMarkers.SUPERFLUOUS_INDICATOR |
                                     DictTermMarkers.MODIFIER_INDICATOR    |
                                     DictTermMarkers.CONTAINSDISALLOWEDTERM_INDICATOR);
        return (! DictTermMarkers.isAnyMarkedAs(dictTerm, dictTermMarksToIgnore));
    }


    abstract void collectCorefsForSection (SubHeading subsection, List<DictTerm> sortedTerms);

    
    protected boolean isICDO (String attributeType)
    {
        return attributeType.equals (ICDO_STRING);
    }
    
    static public boolean coreferencedAnnotations (List<?>     annotations,
                                                   String   coreferenceFeatureName,
                                                   String   elementsFeatureName)
    throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException
    {
        // must not be empty
        FSArray fsCorefObjects = null; 
        for (Iterator<?> it = annotations.iterator(); it.hasNext();) {
            Annotation curr = (Annotation)it.next();
            if (null == fsCorefObjects) {
                Feature f = curr.getType().getFeatureByBaseName(coreferenceFeatureName);
                if (null == f) {
                    return false;
                }
                FeatureStructure coreference = curr.getFeatureValue(f);
                f = coreference.getType().getFeatureByBaseName(elementsFeatureName);
                if (null == f) {
                    return false;
                }
                fsCorefObjects = (FSArray)coreference.getFeatureValue(f);
                if (null == fsCorefObjects) {
                    return false;
                }
            }
            if (!UIMAAnnotationUtils.containsAnnotationBySpan(fsCorefObjects.toArray(), curr)) {
                return false;
            }
        }
        return true;
    }

    static public boolean coreferencedAnnotations (List<?>         annotation,
                                                   Annotation   test,
                                                   String       coreferenceFeatureName,
                                                   String       elementsFeatureName)
    throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException
    {
        // returns true if "test" is coreference with any of the "annotations"
        Feature f = test.getType().getFeatureByBaseName(coreferenceFeatureName);
        if (null == f) {
            throw new NoSuchMethodException(test.getClass().getName() + "." + coreferenceFeatureName);
        }
        // array of corerence objects
        FSArray coreferences = (FSArray)test.getFeatureValue(f);
        for (int i = 0; i < coreferences.size(); ++i) {
            FeatureStructure coreference = coreferences.get(i); 
            f = coreference.getType().getFeatureByBaseName(elementsFeatureName);
            if (null == f) {
                throw new NoSuchMethodException(coreference.getClass().getName() + "." + elementsFeatureName);
            }
            FSArray fsTestCorefs = (FSArray)coreference.getFeatureValue(f);
            if (null == fsTestCorefs) {
                return false;
            }
            for (Iterator<?> it = annotation.iterator(); it.hasNext();) {
                Annotation ann = (Annotation)it.next();
                if (UIMAAnnotationUtils.containsAnnotationBySpan(fsTestCorefs.toArray(), ann)) {
                    return true;
                }
            }
        }
        return false;
    }

    protected void postProcess (CAS tcas, JCas jcas, JFSIndexRepository jcasIndexes)
    {
        super.postProcess(tcas, jcas, jcasIndexes);
        processCorefs (tcas, jcas);
    }

    protected void processCorefs (CAS tcas, JCas jcas)
    {
        //System.err.println ("Co-referring annotations:");
        for (int i = 0; i < corefsForSection.length; i++)
        {
            Map<?, ?> corefsMap = corefsForSection[i];
            if ((corefsMap != null) && (!corefsMap.isEmpty ()))
            {
                FSArray corefs = null;
                
                //System.err.println ("Section #" + i);
                
                //ArrayList genericEntries = (ArrayList) corefsMap.get (genericKey ());
                //int genericEntriesSize = (genericEntries == null) ? 0 : genericEntries.size ();

                Set<?> corefsKeys = corefsMap.keySet ();
                //boolean justGeneric = ((genericEntriesSize != 0) && (corefsKeys.size () == 1));

                Iterator<?> keyIter = corefsKeys.iterator ();                
                while (keyIter.hasNext ())
                {
                    String key = (String) keyIter.next ();
                    ArrayList<?> entries = (ArrayList<?>) corefsMap.get (key);
                    
                    //System.err.println ("\tkey: " + key);
                    int entriesSize = (entries == null) ? 0 : entries.size ();
                        
                    if (entriesSize > 0)
                    {
                        corefs = new FSArray (jcas, entriesSize);
                        int entryNumber = 0;
                        
                        Annotation annotation = (Annotation) tcas.createAnnotation (newAnnotation, 0, 0);

                        if (entries != null)
                        {
                            entryNumber = addCorefsToAnnotation (annotation, corefs, entries, entryNumber);
                        }
                        //System.err.println("-> Creating annotation, begin: " + annotation.getBegin() + ", end: " + annotation.getEnd() + ", num sites:" + corefs.size ());
                        annotation.setFeatureValue (newAnnotationFeature, corefs);
                        annotation.setIntValue(newAnnotationSectionFeature, i);

                        tcas.getIndexRepository ().addFS (annotation);
                    }
                }
            }
        }
    }

    private int addCorefsToAnnotation (Annotation annotation, FSArray corefs, ArrayList<?> entries, int entryNumber)
    {
        int begin = annotation.getBegin ();
        int end = annotation.getEnd ();
        Iterator<?> entryIter = entries.iterator ();
        while (entryIter.hasNext ())
        {
            DictTerm term = (DictTerm) entryIter.next ();
            if (entryNumber == 0)
            {
                begin = term.getBegin ();
                end = term.getEnd ();
            }
            else
            {
                begin = Math.min (begin, term.getBegin ());
                end = Math.max (end, term.getEnd ());
            }
            corefs.set (entryNumber, term);
            //System.err.println ("\tterm[" + entryNumber + "], begin: " + term.getBegin () + ", end: " + term.getEnd () + ", code: " + term.getAttributeValue () + ", text: " + term.getCoveredText ());
            entryNumber += 1;
        }
        annotation.setBegin (begin);
        annotation.setEnd (end);
        
        return entryNumber;
    }
    
}
