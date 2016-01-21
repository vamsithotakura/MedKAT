package org.ohnlp.medkat.taes.diagnosisTypeDetector;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;


import org.apache.uima.analysis_engine.annotator.AnnotatorConfigurationException;
import org.apache.uima.analysis_engine.annotator.AnnotatorContext;
import org.apache.uima.analysis_engine.annotator.AnnotatorInitializationException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Feature;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.TypeSystem;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.cas.CAS;
import org.apache.uima.jcas.JFSIndexRepository;
import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.ohnlp.medkat.common.DocumentURI;
import org.ohnlp.medkat.common.FeatureConstrainedIterator;
import org.ohnlp.medkat.common.ParameterProcessor;
import org.ohnlp.medkat.common.UIMAAnnotationOffsetComparator;
import org.ohnlp.medkat.common.UIMAAnnotationUtils;
import org.ohnlp.medkat.common.annotationMapper;
import org.ohnlp.medkat.scr.typeConversion.MedKATTypeConverter;
import org.ohnlp.medkat.scr.types.SCRCoreference;
import org.ohnlp.medkat.scr.types.SCRGradeValue;
import org.ohnlp.medkat.scr.types.SCRMetastaticTumor;
import org.ohnlp.medkat.scr.types.SCRMetastaticTumorReading;
import org.ohnlp.medkat.scr.types.SCRNamedEntity;
import org.ohnlp.medkat.scr.types.SCRPrimaryTumor;
import org.ohnlp.medkat.scr.types.SCRPrimaryTumorReading;
import org.ohnlp.medkat.scr.types.SCRSize;
import org.ohnlp.medkat.taes.conceptMapper.OriginTerm;
import org.ohnlp.medkat.taes.dimensionAnnotator.RangeAnnotation;
import org.ohnlp.medkat.taes.disambiguator.TumorSizeAnnotation;
import org.ohnlp.medkat.taes.gradeDetector.GradeAnnotation;
import org.ohnlp.medkat.taes.subsectionDetector.SubHeading;
import org.ohnlp.medkat.taes.subsectionProcessor.BasicSubsectionProcessor;

public class TumorModelAnnotator extends BasicSubsectionProcessor
{

    
    public class AnnotationBeginComparator implements Comparator<Annotation>
    {

        public int compare (Annotation left, Annotation right)
        {
            
            if (left.getBegin() < right.getBegin())
            {
                return -1;
            }
            else if (left.getBegin() > right.getBegin())
            {
                return 1;
            }
            return 0;
        }

    }

    
    public class SizeDimensionsComparator implements Comparator<Annotation>
    {
        public int numDimensions (FSArray dims)
        {
            if (dims.get(2) != null)
            {
                return 3;
            }
            else if (dims.get(1) != null)
            {
                return 2;
            }
            else
            {
                return 1;
            }
        }
        // sort Range anotation by the number of dimensions in their largest entry
        public int compare (Annotation leftAnnotation, Annotation rightAnnotation)
        {
            TumorSizeAnnotation leftRange = (TumorSizeAnnotation) leftAnnotation;
            TumorSizeAnnotation rightRange = (TumorSizeAnnotation) rightAnnotation;

            int leftSize = numDimensions (leftRange.getRange().getHigh().getDimensions());
            int rightSize = numDimensions (rightRange.getRange().getHigh().getDimensions());
            
            if (leftSize > rightSize)
            {
                return -1;
            }
            else if (leftSize < rightSize)
            {
                return 1;
            }
            return 0;
        }

    }


	private static final String ENTITY_SEMCLASS_LYMPH = "Lymph";
    private static final String ENTITY_SEMCLASS_INVASIVE = "Invasive";
    private static final String ENTITY_SEMCLASS_METASTATIC = "Metastatic";



    private static class DiagnosisAnnotationAccessor
    {
        private static Type diagnosisType;
        private static Class<? extends Annotation> diagnosisClass;
        private static Feature terminologyFeature;
        private static Feature codeFeature;
        private static Feature corefsFeature;
        private static Feature negationFeature;
        
        public static void init (TypeSystem typeSystem, String diagnosisTypeName, String terminologyFeatureName, String codeFeatureName, String corefsFeatureName, String negationFeatureName)
            throws AnnotatorInitializationException, ClassNotFoundException
        {
            if ((diagnosisTypeName == null) || diagnosisTypeName.equals("") ||
                (terminologyFeatureName == null) || terminologyFeatureName.equals("") ||
                (codeFeatureName == null) || codeFeatureName.equals("") ||
                (corefsFeatureName == null) || corefsFeatureName.equals("") ||
                (negationFeatureName == null) || negationFeatureName.equals(""))
            {
                throw new AnnotatorInitializationException ();
            }
            diagnosisType = typeSystem.getType (diagnosisTypeName);
            diagnosisClass = UIMAAnnotationUtils.forName (diagnosisTypeName);
            terminologyFeature = diagnosisType.getFeatureByBaseName(terminologyFeatureName);
            codeFeature = diagnosisType.getFeatureByBaseName(codeFeatureName);
            corefsFeature = diagnosisType.getFeatureByBaseName(corefsFeatureName);
            negationFeature = diagnosisType.getFeatureByBaseName(negationFeatureName);
        }
        
        public static String getTerminology (Annotation diagnosis)
        {
            return diagnosis.getStringValue(terminologyFeature);
        }
        
        public static String getCodeValue (Annotation diagnosis)
        {
            return diagnosis.getStringValue(codeFeature);
        }
        
        public static FSArray getCorefs (Annotation diagnosis)
        {
            return (FSArray)diagnosis.getFeatureValue(corefsFeature);
        }
        
        public static int getNegation (Annotation diagnosis)
        {
            return diagnosis.getIntValue(negationFeature);
        }
        
        public static boolean isNegated (Annotation diagnosis)
        {
            return (1 == getNegation (diagnosis));
        }
        
        public static Class<? extends Annotation> getDiagnosisClass ()
        {
            return diagnosisClass;
        }
        
    }
    
    
    private static class SiteAnnotationAccessor
    {
        private static Type siteType;
        private static Class<? extends Annotation> siteClass;
        private static Feature terminologyFeature;
        private static Feature codeFeature;
        private static Feature corefsFeature;
        private static Feature negationFeature;
        
        public static void init (TypeSystem typeSystem, String siteTypeName, String terminologyFeatureName, String codeFeatureName, String corefsFeatureName, String negationFeatureName)
        throws AnnotatorInitializationException, ClassNotFoundException
        {
            if ((siteTypeName == null) || siteTypeName.equals("") ||
                (terminologyFeatureName == null) || terminologyFeatureName.equals("") ||
                (codeFeatureName == null) || codeFeatureName.equals("") ||
                (corefsFeatureName == null) || corefsFeatureName.equals(""))
            {
                throw new AnnotatorInitializationException ();
            }
            siteType = typeSystem.getType (siteTypeName);
            siteClass = UIMAAnnotationUtils.forName (siteTypeName);
            terminologyFeature = siteType.getFeatureByBaseName(terminologyFeatureName);
            codeFeature = siteType.getFeatureByBaseName(codeFeatureName);
            corefsFeature = siteType.getFeatureByBaseName(corefsFeatureName);
            negationFeature = siteType.getFeatureByBaseName(negationFeatureName);

        }
        
        public static String getTerminology (Annotation site)
        {
            return site.getStringValue(terminologyFeature);
        }
        
        public static String getCodeValue (Annotation site)
        {
            return site.getStringValue(codeFeature);
        }
        
        public static FSArray getCorefs (Annotation site)
        {
            return (FSArray)site.getFeatureValue(corefsFeature);
        }
        
        public static int getNegation (Annotation site)
        {
            return site.getIntValue(negationFeature);
        }
        
        public static boolean isNegated (Annotation site)
        {
            return (1 == getNegation(site));
        }
        

        public static Class<? extends Annotation> getSiteClass ()
        {
            return siteClass;
        }

    }

    private class TumorModelParts
    {
        private Collection<Annotation> diagnoses;
        private Collection<Annotation> sites;
        private Collection<Annotation> grades;
        private Collection<Annotation> sizes;
        
        public TumorModelParts (Collection<Annotation> diagnoses, Collection<Annotation> sites, Collection<Annotation> grades, Collection<Annotation> sizes)
        {
            super();
            this.diagnoses = diagnoses;
            this.sites = sites;
            this.grades = grades;
            this.sizes = sizes;
        }

        public Collection<Annotation> getDiagnoses ()
        {
            return diagnoses;
        }

        public void setDiagnoses (Collection<Annotation> diagnoses)
        {
            this.diagnoses = diagnoses;
        }

        public Collection<Annotation> getGrades ()
        {
            return grades;
        }

        public void setGrades (Collection<Annotation> grades)
        {
            this.grades = grades;
        }

        public Collection<Annotation> getSites ()
        {
            return sites;
        }

        public void setSites (Collection<Annotation> sites)
        {
            this.sites = sites;
        }

        public Collection<Annotation> getSizes ()
        {
            return sizes;
        }

        public void setSizes (Collection<Annotation> sizes)
        {
            this.sizes = sizes;
        }
        
    }


    private JCas jcas = null;
    
    private List<Annotation> sitesInSubsection;
    
    private static final String METASTATIC_SUFFIX = "/6";
    private static final String BENIGN_SUFFIX = "/0";
    private static final String BENIGN_DIAGNOSIS_KEY = "Benign";
    private static final String METASTATIC_DIAGNOSIS_KEY = ENTITY_SEMCLASS_METASTATIC;
    private static final String LYMPH_DIAGNOSIS_KEY = ENTITY_SEMCLASS_LYMPH;
    private static final String PRIMARY_DIAGNOSIS_KEY = "Primary";
    private static final String PARAM_EXCLUDING_PREPS = "ExcludingPrepositions";
    
    private List<Annotation> diagnosesInSubsection;
    private List<Annotation> gradesInSubsection;
    private List<Annotation> sizesInSubsection;


    //private static final String PARAM_SENTENCE_TYPE = "SentenceAnnotationType";
    private String sentenceAnnotationName = "uima.tt.SentenceAnnotation";

    //private static final String PARAM_NPCOMBINED_TYPE = "NPCombinedAnnotationType";
    private String NPCombinedAnnotationName = "org.ohnlp.medkat.taes.npMerger.NPCombinedAnnotation";

    //private static final String PARAM_NP_TYPE = "NPAnnotationType";
    private String NPAnnotationName = "uima.tt.NPAnnotation";

    //private static final String PARAM_NP_LIST_TYPE = "NPListAnnotationType";
    private String NPListAnnotationName = "uima.tt.NPListAnnotation";

    //private static final String PARAM_PP_TYPE = "PPAnnotationType";
    private String PPAnnotationName = "uima.tt.PPAnnotation";

    //private static final String PARAM_TOKEN_TYPE = "TokenAnnotationType";
    private String tokenAnnotationName = "uima.tt.TokenAnnotation";

    private String gradeAnnotationName = "org.ohnlp.medkat.taes.gradeDetector.GradeAnnotation";

    private Set<String> exclusionPreps;
    
    private Type subsectionType;
    private String subsectionAnnotationName = "org.ohnlp.medkat.taes.subsectionDetector.SubHeading";
    private Feature subBeginFeat;
    private Feature subEndFeat;
    
    private AnnotationIndex sentenceAnnotationIndex;
    private Type sentenceType;

    private String PARAM_SITETYPENAME = "siteTypeName";
    private String siteTypeName;
    private String PARAM_SITETERMINOLOGYFEATURENAME = "siteTerminologyFeatureName";
    private String siteTerminologyFeatureName;
    private String PARAM_SITECODEFEATURENAME = "siteCodeFeatureName";
    private String siteCodeFeatureName;
    private String PARAM_SITECOREFSFEATURENAME = "siteCorefsFeatureName";
    private String siteCorefsFeatureName;
    private String PARAM_SITENEGATIONFEATURENAME = "siteNegationFeatureName";
    private String siteNegationFeatureName;
    
    private String PARAM_DIAGNOSISTYPENAME = "diagnosisTypeName";
    private String diagnosisTypeName;
    private String PARAM_DIAGNOSISTERMINOLOGYFEATURENAME = "diagnosisTerminologyFeatureName";
    private String diagnosisTerminologyFeatureName;
    private String PARAM_DIAGNOSISCODEFEATURENAME = "diagnosisCodeFeatureName";
    private String diagnosisCodeFeatureName;
    private String PARAM_DIAGNOSISCOREFSFEATURENAME = "diagnosisCorefsFeatureName";
    private String diagnosisCorefsFeatureName;
    private String PARAM_DIAGNOSISNEGATIONFEATURENAME = "diagnosisNegationFeatureName";
    private String diagnosisNegationFeatureName;

    
    private String benignSuffix = BENIGN_SUFFIX;
    private String metastaticSuffix = METASTATIC_SUFFIX;

    private String PARAM_SIZETYPENAME = "tumorSizeTypeName";
    private String tumorSizeAnnotationName;

    private Collection<?> grossDescriptionSites;

    private Map<Annotation, Collection<Annotation>> pp2tokenMap = null;
    private Map<Annotation, Collection<Annotation>> sentence2ppMap = null;
    
    private Map<Annotation, Annotation> diagnosis2sentenceMap = null;
    private Map<Annotation, Annotation> diagnosis2npMap = null;
    private Map<Annotation, Annotation> diagnosis2npListMap = null;
    private Map<Annotation, Annotation> diagnosis2npCombinedMap = null;

    private Map<Annotation, Annotation> site2sentenceMap = null;
    private Map<Annotation, Annotation> site2npMap = null;
    private Map<Annotation, Annotation> site2npListMap = null;
    private Map<Annotation, Annotation> site2npCombinedMap = null;
    private Map<Annotation, Annotation> site2ppMap = null;

    private Map<Annotation, Annotation> grade2sentenceMap = null;
    private Map<Annotation, Annotation> grade2npMap = null;
    private Map<Annotation, Annotation> grade2npListMap = null;
    private Map<Annotation, Annotation> grade2npCombinedMap = null;
    private Map<Annotation, Annotation> grade2ppMap = null;
    
    private Map<Annotation, Annotation> size2sentenceMap = null;
    private Map<Annotation, Annotation> size2npMap = null;
    private Map<Annotation, Annotation> size2npListMap = null;
    private Map<Annotation, Annotation> size2npCombinedMap = null;
    private Map<Annotation, Annotation> size2ppMap = null;
    private Map<Annotation, Set<Annotation>> metastaticContainerMap = null; // igor bug fix
    
    private Set<Annotation> originNPs;
    
    private Class<? extends Annotation> sentenceClass = null;
    private Class<? extends Annotation> gradeAnnotationClass = null;
    private Class<? extends Annotation> tokenAnnotationClass = null;
    private Class<? extends Annotation> ppAnnotationClass = null;
    private Class<? extends Annotation> npAnnotationClass = null;
    private Class<? extends Annotation> npListAnnotationClass = null;
    private Class<? extends Annotation> npCombinedAnnotationClass = null;
    private Class<? extends Annotation> namedEntityAnnotationClass = null;
    private Class<? extends Annotation> originTermAnnotationClass = null;
    private Class<? extends Annotation> tumorSizeAnnotationClass = null;
    private Collection<Annotation> sentences;
    private Annotation firstSentence = null;
    private ArrayList<TumorModelParts> metastaticDiagnoses;
    private ArrayList<TumorModelParts> primaryDiagnoses;
    private ArrayList<TumorModelParts> otherDiagnoses;


    
    public void initialize (AnnotatorContext annotatorContext) throws AnnotatorInitializationException, AnnotatorConfigurationException
    {
        super.initialize(annotatorContext);
        try
        {
            diagnosisTypeName = (String) getContext ().getConfigParameterValue (PARAM_DIAGNOSISTYPENAME);
            diagnosisTerminologyFeatureName = (String) getContext ().getConfigParameterValue (PARAM_DIAGNOSISTERMINOLOGYFEATURENAME);
            diagnosisCodeFeatureName = (String) getContext ().getConfigParameterValue (PARAM_DIAGNOSISCODEFEATURENAME);
            diagnosisCorefsFeatureName = (String) getContext ().getConfigParameterValue (PARAM_DIAGNOSISCOREFSFEATURENAME);
            diagnosisNegationFeatureName = (String) getContext ().getConfigParameterValue (PARAM_DIAGNOSISNEGATIONFEATURENAME);
            siteTypeName = (String) getContext ().getConfigParameterValue (PARAM_SITETYPENAME);
            siteTerminologyFeatureName = (String) getContext ().getConfigParameterValue (PARAM_SITETERMINOLOGYFEATURENAME);
            siteCodeFeatureName = (String) getContext ().getConfigParameterValue (PARAM_SITECODEFEATURENAME);
            siteCorefsFeatureName = (String) getContext ().getConfigParameterValue (PARAM_SITECOREFSFEATURENAME);
            siteNegationFeatureName = (String) getContext ().getConfigParameterValue (PARAM_SITENEGATIONFEATURENAME);
            
            tumorSizeAnnotationName = (String) getContext ().getConfigParameterValue (PARAM_SIZETYPENAME);
            
            exclusionPreps = ParameterProcessor.paramArrayToSet(annotatorContext, PARAM_EXCLUDING_PREPS, true);

        }
        catch (Exception e)
        {
            throw new AnnotatorConfigurationException (e);
        }
    }



    public void typeSystemInit (TypeSystem typeSystem) throws AnnotatorInitializationException, AnnotatorConfigurationException
    {
        super.typeSystemInit(typeSystem);
        try
        {
            DiagnosisAnnotationAccessor.init (typeSystem, diagnosisTypeName, diagnosisTerminologyFeatureName, diagnosisCodeFeatureName, diagnosisCorefsFeatureName, diagnosisNegationFeatureName);
            SiteAnnotationAccessor.init (typeSystem, siteTypeName, siteTerminologyFeatureName, siteCodeFeatureName, siteCorefsFeatureName, siteNegationFeatureName);
        }
        catch (ClassNotFoundException cnfe)
        {
            throw new AnnotatorInitializationException ();
        }
        sentenceType = typeSystem.getType (sentenceAnnotationName);        
        subsectionType = typeSystem.getType (subsectionAnnotationName);
        subBeginFeat = subsectionType.getFeatureByBaseName("begin");
        subEndFeat = subsectionType.getFeatureByBaseName("end");
        
        try {
            sentenceClass = UIMAAnnotationUtils.forName(sentenceAnnotationName);
            gradeAnnotationClass = UIMAAnnotationUtils.forName(gradeAnnotationName);
            tokenAnnotationClass = UIMAAnnotationUtils.forName(tokenAnnotationName);
            ppAnnotationClass = UIMAAnnotationUtils.forName(PPAnnotationName);
            npAnnotationClass = UIMAAnnotationUtils.forName(NPAnnotationName);
            npListAnnotationClass = UIMAAnnotationUtils.forName(NPListAnnotationName);
            npCombinedAnnotationClass = UIMAAnnotationUtils.forName(NPCombinedAnnotationName);
            namedEntityAnnotationClass = UIMAAnnotationUtils.forName(SCRNamedEntity.class.getName());
            originTermAnnotationClass = UIMAAnnotationUtils.forName(OriginTerm.class.getName());
            tumorSizeAnnotationClass = UIMAAnnotationUtils.forName(tumorSizeAnnotationName);
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new AnnotatorInitializationException ();
        }

    }

    


    protected void initializeProcess (CAS tcas, JCas jCas, JFSIndexRepository jcasIndexes)
    {
        super.initializeProcess(tcas, jCas, jcasIndexes);
        jcas = jCas;
        sentenceAnnotationIndex = (AnnotationIndex)tcas.getAnnotationIndex (sentenceType);
        //System.err.println ("===> PROCESSING DOCUMENT: '" + DocumentURI.get (jcas) + "'");
        
        metastaticContainerMap = new HashMap<Annotation, Set<Annotation>> (); // igor bug fix 

    }



    private void fillMaps (JCas jcas, SubHeading subsection)
    {
        sentences = getSubsectionSentenceAnnotations(jcas, subsection);

        Iterator<Annotation> sentenceIterator = sentences.iterator();
        if (sentenceIterator.hasNext())
        {
            Annotation sentence = sentenceIterator.next();
            firstSentence  = sentence;
            //System.err.println ("FIRST Sentence:" + sentence.getCoveredText());
        }
        
        /*sentenceIterator = sentences.iterator();
        int sentenceNumber = 0;
        while (sentenceIterator.hasNext())
        {
            Annotation sentence = (Annotation) sentenceIterator.next();
            System.err.println ("Sentence[" + sentenceNumber + "]: " + sentence.getCoveredText());
            if (sentenceNumber == 0)
            {
                System.err.println ("FIRST Sentence:" + sentence.getCoveredText());
            }
            sentenceNumber += 1;
        }*/

        /*diagnosis2sentenceMap = new TreeMap(new UIMAAnnotationOffsetComparator ());
        sentenceIterator = sentences.iterator();
        int sentenceNumber = 0;
        while (spanIter.hasNext())
        {
            Annotation sentence = (Annotation) spanIter.next();
            //System.err.println ("Sentence[" + sentenceNumber + "]: " + sentence.getCoveredText());
            if (sentenceNumber == 0)
            {
                firstSentence  = sentence;
                //System.err.println ("FIRST Sentence:" + sentence.getCoveredText());
            }
            sentenceNumber += 1;
            diagnosis2sentenceMap = annotationMapper.addItemsForSpanMap (jcas, diagnosis2sentenceMap, DiagnosisAnnotationAccessor.getDiagnosisClass(), sentence);            
        }*/
        //System.err.println("diagnosis2sentenceMap:");
        diagnosis2sentenceMap = annotationMapper.fillMap (jcas, subsection, sentenceClass, DiagnosisAnnotationAccessor.getDiagnosisClass());
        //System.err.println("DONE diagnosis2sentenceMap!");
        diagnosis2npMap = annotationMapper.fillMap (jcas, subsection, npAnnotationClass, DiagnosisAnnotationAccessor.getDiagnosisClass());
        diagnosis2npListMap = annotationMapper.fillMap (jcas, subsection, npListAnnotationClass, DiagnosisAnnotationAccessor.getDiagnosisClass());
        diagnosis2npCombinedMap = annotationMapper.fillMap (jcas, subsection, npCombinedAnnotationClass, DiagnosisAnnotationAccessor.getDiagnosisClass());
        //diagnosis2ppMap = annotationMapper.fillMap (jcas, subsection, ppAnnotationClass, DiagnosisAnnotationAccessor.getDiagnosisClass());
        
        site2sentenceMap = annotationMapper.fillMap (jcas, subsection, sentenceClass, SiteAnnotationAccessor.getSiteClass());
        site2npMap = annotationMapper.fillMap (jcas, subsection, npAnnotationClass, SiteAnnotationAccessor.getSiteClass());
        site2npListMap = annotationMapper.fillMap (jcas, subsection, npListAnnotationClass, SiteAnnotationAccessor.getSiteClass());
        site2npCombinedMap = annotationMapper.fillMap (jcas, subsection, npCombinedAnnotationClass, SiteAnnotationAccessor.getSiteClass());
        site2ppMap = annotationMapper.fillMap (jcas, subsection, ppAnnotationClass, SiteAnnotationAccessor.getSiteClass());


        
        //System.err.println("grade2sentenceMap:");
        grade2sentenceMap = annotationMapper.fillMap (jcas, subsection, sentenceClass, gradeAnnotationClass);
        //System.err.println("grade2npMap:");
        grade2npMap = annotationMapper.fillMap (jcas, subsection, npAnnotationClass, gradeAnnotationClass);
        //System.err.println("grade2npListMap:");
        grade2npListMap = annotationMapper.fillMap (jcas, subsection, npListAnnotationClass, gradeAnnotationClass);
        //System.err.println("grade2npCombinedMap:");
        grade2npCombinedMap = annotationMapper.fillMap (jcas, subsection, npCombinedAnnotationClass, gradeAnnotationClass);
        //System.err.println("grade2ppMap:");
        grade2ppMap = annotationMapper.fillMap (jcas, subsection, ppAnnotationClass, gradeAnnotationClass);
        
        //System.err.println("size2sentenceMap:");
        size2sentenceMap = annotationMapper.fillMap (jcas, subsection, sentenceClass, tumorSizeAnnotationClass);
        //System.err.println("size2npMap:");
        size2npMap = annotationMapper.fillMap (jcas, subsection, npAnnotationClass, tumorSizeAnnotationClass);
        //System.err.println("size2npListMap:");
        size2npListMap = annotationMapper.fillMap (jcas, subsection, npListAnnotationClass, tumorSizeAnnotationClass);
        //System.err.println("size2npCombinedMap:");
        size2npCombinedMap = annotationMapper.fillMap (jcas, subsection, npCombinedAnnotationClass, tumorSizeAnnotationClass);
        //System.err.println("size2ppMap:");
        size2ppMap = annotationMapper.fillMap (jcas, subsection, ppAnnotationClass, tumorSizeAnnotationClass);
        
        Map<Annotation, Collection<Annotation>> np2originMap = annotationMapper.fillOneToManyMap (jcas, subsection, npAnnotationClass, originTermAnnotationClass);
        originNPs = new HashSet<Annotation> ();
        
        Iterator<Annotation> np2oIter = np2originMap.keySet().iterator();
        //System.err.println("np2originMap: ");
        while (np2oIter.hasNext())
        {
            Annotation np = np2oIter.next();
            Collection<Annotation> origins = np2originMap.get(np);
            //System.err.println("\tNP: " + np.getCoveredText());
            Iterator<Annotation> oIter = origins.iterator();
            if (oIter.hasNext())
            {
                //Annotation origin = (Annotation) oIter.next();
                //System.err.println("\tOrigin np: " + np.getCoveredText());
                originNPs.add(np);
            }
        }
        //System.err.println("np2originMap DONE");
        
        //System.err.println("pp2tokenMap:");
        pp2tokenMap = annotationMapper.fillOneToManyMap (jcas, subsection, ppAnnotationClass, tokenAnnotationClass);
        sentence2ppMap = annotationMapper.fillOneToManyMap (jcas, subsection, sentenceClass, ppAnnotationClass);
        
        grossDescriptionSites = findAllGrossDescriptionTerms (subsection, SiteAnnotationAccessor.getSiteClass());

    }


    
    protected void processSubSection (CAS tcas,
                                      JCas jcas,
                                      SubHeading subsection,
                                      int begin,
                                      int end)
    {
        fillMaps (jcas, subsection);

        sitesInSubsection = annotationMapper.getSubAnnotations (jcas, SiteAnnotationAccessor.getSiteClass(), subsection);
        diagnosesInSubsection = annotationMapper.getSubAnnotations (jcas, DiagnosisAnnotationAccessor.getDiagnosisClass(), subsection);
        Iterator<Annotation> diagnosisIter = diagnosesInSubsection.iterator();
        Set<Annotation> diagsToRemove = new HashSet<Annotation>();
        while (diagnosisIter.hasNext())
        {
            Annotation diagnosisAnnot = (Annotation) diagnosisIter.next();
            //System.err.println ("Diagnosis, " + diagnosisAnnot.getCoveredText() + ", code: " + DiagnosisAnnotationAccessor.getCodeValue(diagnosisAnnot) + ", begin: " + diagnosisAnnot.getBegin() + ", end: " + diagnosisAnnot.getEnd());                
            //System.err.println ("diagnosis2sentenceMap.get(diagnosisAnnot): " + diagnosis2sentenceMap.get(diagnosisAnnot) + ", firstSentence: " + firstSentence + ", (diagnosis2sentenceMap.get(diagnosisAnnot) == firstSentence): " + (diagnosis2sentenceMap.get(diagnosisAnnot) == firstSentence));                
            if (DiagnosisAnnotationAccessor.isNegated(diagnosisAnnot) || (diagnosis2sentenceMap.get(diagnosisAnnot) == firstSentence))
            {
                //System.err.println ("Removing negated diagnosis, " + diagnosisAnnot.getCoveredText() + ", code: " + DiagnosisAnnotationAccessor.getCodeValue(diagnosisAnnot) + ", begin: " + diagnosisAnnot.getBegin() + ", end: " + diagnosisAnnot.getEnd());                
                diagsToRemove.add (diagnosisAnnot);
            }
            else if (isMetastaticCode(DiagnosisAnnotationAccessor.getCodeValue(diagnosisAnnot)))
            {
                metastaticContainerMap.put (diagnosisAnnot, new HashSet<Annotation> ());
            }
        }
        
        diagnosisIter = diagnosesInSubsection.iterator();
        while (diagnosisIter.hasNext())
        {
            Annotation diagnosisAnnot = diagnosisIter.next();
            Annotation containedBy = findContainer (diagnosisAnnot, metastaticContainerMap.keySet());
            
            if ((containedBy != null) && (! containedBy.equals(diagnosisAnnot)))
            {
                Set<Annotation> contained = metastaticContainerMap.get(containedBy);
                //diagsToRemove.add(diagnosisAnnot);
                contained.add (diagnosisAnnot);
            }
        }
        
        diagnosesInSubsection.removeAll(diagsToRemove);        
        
        gradesInSubsection = annotationMapper.getSubAnnotations (jcas, gradeAnnotationClass, subsection);
        Iterator<?> gradesIter = gradesInSubsection.iterator();
        Set<GradeAnnotation> gradesToRemove = new HashSet<GradeAnnotation>();
        while (gradesIter.hasNext())
        {
            GradeAnnotation gradeAnnot = (GradeAnnotation) gradesIter.next();
            if (gradeAnnot.getGradeType().equals ("BasicGrade"))
            {
                //System.err.println ("Removing BasicGrade, " + gradeAnnot.getCoveredText() + ", begin: " + gradeAnnot.getBegin() + ", end: " + gradeAnnot.getEnd());                
                gradesToRemove.add (gradeAnnot);
            }
        }
        gradesInSubsection.removeAll(gradesToRemove);        
        
        
        sizesInSubsection = annotationMapper.getSubAnnotations (jcas, tumorSizeAnnotationClass, subsection);
        //
        //System.err.println ("PROCESSING SUBSECTION: " + subsection.getSubSectionNumber());
        //System.err.println ("SITES IN SUBSECTION:");
        //Iterator<Annotation> siteIter = sitesInSubsection.iterator();
        //while (siteIter.hasNext())
        //{
        //    Annotation siteAnnot = siteIter.next();
        //    System.err.println ("Site, " + siteAnnot.getCoveredText() + ", code: " + SiteAnnotationAccessor.getCodeValue(siteAnnot) + ", begin: " + siteAnnot.getBegin() + ", end: " + siteAnnot.getEnd());                
        //    //FSArray sites = SiteAnnotationAccessor.getCorefs(siteAnnot);
        //    for (int i = 0; i < sites.size(); i ++)
        //    {
        //        Annotation coref = (Annotation) sites.get(i);
        //        System.err.println ("\t " + coref.getCoveredText() + ", code: " + SiteAnnotationAccessor.getCodeValue(siteAnnot) + ", begin: " + coref.getBegin() + ", end: " + coref.getEnd());                
        //    }//
        //}
        //
        
        Set<Annotation> originSites = findOriginSites (sitesInSubsection, site2npMap, originNPs);

        processDiagnoses (jcas, sitesInSubsection, originSites, diagnosesInSubsection, gradesInSubsection, sizesInSubsection, metastaticContainerMap);
    }


    private Annotation findContainer (Annotation diagnosis, Set<Annotation> containers)
    {   
        Annotation result = null;
        
        Iterator<Annotation> containerIter = containers.iterator();
        while (containerIter.hasNext())
        {
            Annotation potentialContainer = containerIter.next();
            if ((potentialContainer.getBegin() <= diagnosis.getBegin()) && (potentialContainer.getEnd() >= diagnosis.getEnd()))
            {
                //System.err.println ("CONTAINER: " + potentialContainer.getCoveredText() + ", contains: " + diagnosis.getCoveredText());
                return potentialContainer;
            }
        }
        return result;
    }



    private Set<Annotation> findOriginSites (List<Annotation> sitesInSubsection, Map<Annotation, Annotation> site2npMap, Set<Annotation> originNPs)
    {
        Set<Annotation> originSites = new HashSet<Annotation> ();

        Iterator<Annotation> siteIter = sitesInSubsection.iterator();
        while (siteIter.hasNext())
        {
            Annotation site = siteIter.next();
            Annotation np = site2npMap.get(site);
            if (np != null)
            {
                //System.err.println("findOriginSites, NP: " + ((Annotation) np).getCoveredText());
                if ((originNPs.contains(np)))
                {
                    originSites.add(site);
                    //System.err.println("Origin site: " + ((Annotation) site).getCoveredText());
                }
            }
        }
        
        return originSites;
    }



    private void processDiagnoses (JCas jcas, List<Annotation> sitesInSubsection, Set<Annotation> originSites, List<Annotation> diagnosesInSubsection, List<Annotation> gradesInSubsection, List<Annotation> sizesInSubsection, Map<Annotation, Set<Annotation>> metastaticContainerMap)
    {
        //printRuleName("processDiagnoses");
        System.err.println ("Document: " + DocumentURI.get(jcas));
        Set<Annotation> sitesRemaining = new HashSet<Annotation> (sitesInSubsection); // all sites
        Set<Annotation> gradesRemaining = new HashSet<Annotation> (gradesInSubsection); // all sites
        Set<Annotation> sizesRemaining = new HashSet<Annotation> (sizesInSubsection); // all sites

        Map<?, ?> [] diagnosisSpanMaps  = { diagnosis2npMap, diagnosis2npListMap, diagnosis2npCombinedMap, diagnosis2sentenceMap };
        //String [] diagnosisSpanMapNames  = { "diagnosis2npMap", "diagnosis2npListMap", "diagnosis2npCombinedMap", "diagnosis2sentenceMap" };
        Map<?, ?> [] siteSpanMaps  = { site2npMap, site2npListMap, site2npCombinedMap, site2sentenceMap };
        //String [] siteSpanMapNames  = { "site2npMap", "site2npListMap", "site2npCombinedMap", "site2sentenceMap" };
        Map<?, ?> [] gradeSpanMaps  = { grade2npMap, grade2npListMap, grade2npCombinedMap, grade2sentenceMap };
        //String [] gradeSpanMapNames  = { "grade2npMap", "grade2npListMap", "grade2npCombinedMap", "grade2sentenceMap" };
        Map<?, ?> [] sizeSpanMaps  = { size2npMap, size2npListMap, size2npCombinedMap, size2sentenceMap };
        //String [] sizeSpanMapNames  = { "size2npMap", "size2npListMap", "size2npCombinedMap", "size2sentenceMap" };

        Map<Annotation, Collection<Annotation>> siteResults = new TreeMap<Annotation, Collection<Annotation>> (new UIMAAnnotationOffsetComparator ());
        Map<Annotation, Collection<Annotation>> gradeResults = new HashMap<Annotation, Collection<Annotation>> ();
        Map<Annotation, Collection<Annotation>> sizeResults = new HashMap<Annotation, Collection<Annotation>> ();
        Map<Annotation, Collection<Annotation>> originResults = new HashMap<Annotation, Collection<Annotation>> ();
        int spanMapIndex = 0;
        
        do 
        {
            //System.err.println ("spanMapName: " + diagnosisSpanMapNames[spanMapIndex]);
            processAllSpans(diagnosesInSubsection, spanMapIndex, diagnosisSpanMaps, siteSpanMaps, gradeSpanMaps, sizeSpanMaps, sitesRemaining, siteResults, gradesRemaining, gradeResults, sizesRemaining, sizeResults, originSites, originResults);

            spanMapIndex += 1;
        }
        while ((spanMapIndex < diagnosisSpanMaps.length) && ((sitesRemaining.size() > 0) || (gradesRemaining.size() > 0) || (sizesRemaining.size() > 0)));

        // if multiple corefs for diagnosis have different site mentions, merge them
        mergeSitesFromCorefDiagnoses (siteResults, gradeResults, sizeResults, diagnosesInSubsection);

        
        // if no site assigned to diagnosis, then assign sites from Gross description
        Collection<Annotation> grossDescriptionSites = null;
        Iterator<?> diagnosisIter = diagnosesInSubsection.iterator();
        while (diagnosisIter.hasNext())
        {
            Annotation diagnosis = (Annotation) diagnosisIter.next();
            if (siteResults.get(diagnosis) == null)
            {
                if (grossDescriptionSites == null)
                {
                    grossDescriptionSites = findGrossDescriptionSites ();
                }
                //System.err.println("Using Gross Description sites for diagnosis: " + diagnosis.getCoveredText());
                siteResults.put(diagnosis, grossDescriptionSites);
            }
        }
        
        //print them out
        /*
        Set<Annotation> diags = siteResults.keySet();
        Iterator<Annotation> diagIter = diags.iterator();
        System.err.println ("======= RESULTS =======");
        while (diagIter.hasNext())
        {
            Annotation diag = diagIter.next();
            System.err.println ("Diagnosis: " + diag.getCoveredText() + ": '" + DiagnosisAnnotationAccessor.getCodeValue(diag) + "'");
            Collection<Annotation> sites = siteResults.get(diag);
            Iterator<Annotation> siteIter = sites.iterator();
            while (siteIter.hasNext())
            {
                Annotation site = siteIter.next();
                System.err.println ("\tSite: " + site.getCoveredText() + ", code: " + SiteAnnotationAccessor.getCodeValue(site));
            }
            Collection<Annotation> grades = gradeResults.get(diag);
            if (grades == null)
            {
                System.err.println ("NO GRADES FOR DIAGNOSIS: " + DiagnosisAnnotationAccessor.getCodeValue(diag));
            }
            else
            {
                Iterator<Annotation> gradeIter = grades.iterator();
                while (gradeIter.hasNext())
                {
                    Annotation grade = gradeIter.next();
                    System.err.println ("\tGrade: " + grade.getCoveredText());
                }
            }
        }
        System.err.println ("======= +++++++ =======");
        */
        trimSizeResults (sizeResults);
        metastaticDiagnoses = new ArrayList<TumorModelParts> ();
        primaryDiagnoses = new ArrayList<TumorModelParts> ();
        otherDiagnoses = new ArrayList<TumorModelParts> ();
        categorizeAndMapAll(diagnosesInSubsection, siteResults, gradeResults, sizeResults, categorizeDiagnosesNew (siteResults.keySet()));
        removeMetastaticSitesFromPrimaries (metastaticDiagnoses, primaryDiagnoses);
        //makeResultingAnnotations (jcas, diagnosesInSubsection, siteResults, gradeResults, sizeResults, originResults, metastaticContainerMap, categorizeDiagnosesNew (siteResults.keySet()));
        makePrimaryAnnotations (jcas, primaryDiagnoses, gradeResults);
        makeMetastaticAnnotations (jcas, metastaticDiagnoses, gradeResults, originResults, metastaticContainerMap);
        
    }

    private void makePrimaryAnnotations (JCas jcas, ArrayList<TumorModelParts> primaryDiagnoses, Map<Annotation, Collection<Annotation>> gradeResults)
    {
        Set<Annotation> primariesDone = new HashSet<Annotation> ();   

        Iterator<TumorModelParts> diagnosesIter = primaryDiagnoses.iterator();
        while (diagnosesIter.hasNext())
        {
            
            TumorModelParts tumorModelParts = diagnosesIter.next();
            Collection<Annotation> diagnoses = tumorModelParts.getDiagnoses();
            //System.err.println ("makeResultingAnnotations, diagnoses: " + diagnoses);
                
            Collection<Annotation> sitesForDiagnosis = tumorModelParts.getSites();
            Collection<Annotation> gradesForDiagnosis = tumorModelParts.getGrades();
            Collection<Annotation> sizesForDiagnosis = tumorModelParts.getSizes();

            if (! primariesDone.containsAll(diagnoses))
            {
                Iterator<?> sizeIter = sizesForDiagnosis.iterator();
                do
                {
                    TumorSizeAnnotation size = null;
                    //System.err.println ("Creating new PrimaryDiagnosis");
                    if (sizeIter.hasNext())
                    {
                        size = (TumorSizeAnnotation) sizeIter.next();
                        //System.err.println ("PrimaryDiagnosis size: " + size.getCoveredText());
                    }
                    createPrimaryDiagnosis (jcas, diagnoses, sitesForDiagnosis, gradesForDiagnosis, size);
                }
                while (sizeIter.hasNext());
                primariesDone = addToFinishedSet (primariesDone, diagnoses, gradeResults);
            }
        }
    }


    private void makeMetastaticAnnotations (JCas jcas, ArrayList<TumorModelParts> metastaticDiagnoses, Map<Annotation, Collection<Annotation>> gradeResults, Map<Annotation, Collection<Annotation>> originResults, Map<Annotation, Set<Annotation>> metastaticContainerMap)
    {
        Set<Annotation> metastaticsDone = new HashSet<Annotation> ();   

        Iterator<TumorModelParts> diagnosesIter = metastaticDiagnoses.iterator();
        while (diagnosesIter.hasNext())
        {
            
            TumorModelParts tumorModelParts = diagnosesIter.next();
            Collection<Annotation> diagnoses = tumorModelParts.getDiagnoses();
            //System.err.println ("makeResultingAnnotations, diagnoses: " + diagnoses);
                
            Collection<Annotation> sitesForDiagnosis = tumorModelParts.getSites();
            Collection<Annotation> gradesForDiagnosis = tumorModelParts.getGrades();
            Collection<Annotation> sizesForDiagnosis = tumorModelParts.getSizes();
                
            //System.err.println ("makeResultingAnnotations, done adding sites, grades, sizes");
            if (! metastaticsDone.containsAll(diagnoses))
            {
                Iterator<Annotation> sizeIter = sizesForDiagnosis.iterator();
                do
                {
                    TumorSizeAnnotation size = null;
                    if (sizeIter.hasNext())
                    {
                        size = (TumorSizeAnnotation) sizeIter.next();
                    }
                    createMetastaticDiagnosis (jcas, diagnoses, sitesForDiagnosis, gradesForDiagnosis, size, originResults, metastaticContainerMap);
                } 
                while (sizeIter.hasNext());
                
                metastaticsDone = addToFinishedSet (metastaticsDone, diagnoses, gradeResults);
                //System.err.println ("Creating new MetastaticDiagnosis");
            }
        }
    }


    private void removeMetastaticSitesFromPrimaries (Collection<TumorModelParts> metastaticDiagnoses, Collection<TumorModelParts> primaryDiagnoses)
    {
        Set<String> metastaticSites = new HashSet<String> ();
        Iterator<TumorModelParts> metastaticIterator = metastaticDiagnoses.iterator();
        while (metastaticIterator.hasNext())
        {
            TumorModelParts parts = metastaticIterator.next();
            Collection<?> sites = parts.getSites();
            if (sites != null)
            {
                Iterator<?> siteIterator = sites.iterator();
                while (siteIterator.hasNext())
                {
                    Annotation site = (Annotation)siteIterator.next();
                    String siteCode = SiteAnnotationAccessor.getCodeValue(site);
                    metastaticSites.add(siteCode);
                }
            }
        }

        Iterator<TumorModelParts> primaryIterator = primaryDiagnoses.iterator();
        while (primaryIterator.hasNext())
        {
            TumorModelParts parts = primaryIterator.next();
            Collection<?> sites = parts.getSites();
            if (sites != null)
            {
                ArrayList<Annotation> toRemove = new ArrayList<Annotation> ();
                Iterator<?> siteIterator = sites.iterator();
                while (siteIterator.hasNext())
                {
                    Annotation site = (Annotation)siteIterator.next();
                    String siteCode = SiteAnnotationAccessor.getCodeValue(site);
                    if (metastaticSites.contains(siteCode))
                    {
                        toRemove.add(site);
                        //System.err.println("REMOVING METASTATIC SITE: " + ((Annotation) site).getCoveredText());
                    }
                }
                // if all should be removed, something is wrong!
                if (sites.size() > toRemove.size())
                {
                    sites.removeAll(toRemove);
                }
                //else
                //{
                //    System.err.println("ABORTED REMOVING METASTATIC SITES");
                //}
            }
        }

    }



    // if multiple proposed sizes for tumor, and some are of lower dimensionality, remove them
    private void trimSizeResults (Map<Annotation, Collection<Annotation>> sizeResults)
    {
        SizeDimensionsComparator comp = new SizeDimensionsComparator ();
        
        Iterator<Annotation> sizeResultsIter = sizeResults.keySet().iterator();
        while (sizeResultsIter.hasNext())
        {
            Annotation key = sizeResultsIter.next();
            Collection<Annotation> rangesAsCollection = sizeResults.get(key);
            List<Annotation> ranges = new ArrayList<Annotation> (rangesAsCollection);
            
            if (ranges.size() > 1)
            {
                Collections.sort(ranges, new SizeDimensionsComparator ());
                int numDims = comp.numDimensions(((TumorSizeAnnotation)ranges.get(0)).getRange().getHigh().getDimensions());
                int removeFrom = 0;
                int i = 1;
                while (i < ranges.size())
                {
                    int nextNumDims = comp.numDimensions(((TumorSizeAnnotation)ranges.get(i)).getRange().getHigh().getDimensions());
                    //System.err.println("numDims: " + numDims + " nextNumDims: " + nextNumDims);

                    if (nextNumDims < numDims)
                    {
                        removeFrom = nextNumDims;
                        break;
                    }
                    i += 1;
                }
                if (removeFrom != 0)
                {
                    for (int itemToRemove = ranges.size() - 1; itemToRemove >= removeFrom; itemToRemove--)
                    {
                        //System.err.println("REMOVING RANGE, dims: " + numDims + " vs. " + comp.numDimensions(((TumorSizeAnnotation)ranges.get(itemToRemove)).getRange().getHigh().getDimensions()));
                        rangesAsCollection.remove(ranges.get(itemToRemove));
                    }
                    //System.err.println("Size results now contains: " + ranges.size() + " elements");
                }
            }
            
        }
    }



    private void mergeSitesFromCorefDiagnoses (Map<Annotation, Collection<Annotation>> siteResults, Map<Annotation, Collection<Annotation>> gradeResults, Map<Annotation, Collection<Annotation>> sizeResults, List<Annotation> diagnosesInSubsection)
    {
        Set<Annotation> toRemove = new HashSet<Annotation> ();
        List<Annotation> keys = new ArrayList<Annotation> (diagnosesInSubsection);
        Collections.sort(keys, new AnnotationBeginComparator ());
        
        Iterator<?> diagIter = keys.iterator();
        while (diagIter.hasNext())
        {
            Annotation diagnosis = (Annotation) diagIter.next();
            if (! toRemove.contains(diagnosis))
            {
                //System.err.println ("mergeSitesFromCorefDiagnoses, diagnosis: " + diagnosis.getCoveredText());
    
                FSArray corefs = DiagnosisAnnotationAccessor.getCorefs(diagnosis);
                for (int corefIndex = 0; corefIndex < corefs.size(); corefIndex++)
                {
                    SCRCoreference coref = (SCRCoreference) corefs.get(corefIndex);
                    if (coref != null)
                    {
                        FSArray elements = coref.getElements();
                        for (int elementIndex = 0; elementIndex < elements.size(); elementIndex++)
                        {
                            Annotation corefDiag = (Annotation) elements.get(elementIndex);
                            if ((corefDiag != diagnosis) && (! toRemove.contains(corefDiag) && sameTypeOfDiagnosis (diagnosis, corefDiag) && sameGrades (diagnosis, corefDiag, gradeResults)))
                            //if ((corefDiag != diagnosis) && (! toRemove.contains(corefDiag)))
                            {
                                //System.err.println ("mergeSitesFromCorefDiagnoses, corefDiag: " + corefDiag.getCoveredText());
                                if (siteResults.containsKey(corefDiag))
                                {
                                    Collection<Annotation> sites = siteResults.get(corefDiag);
                                    Collection<Annotation> currentSites = siteResults.get(diagnosis);
                                    if (currentSites == null)
                                    {
                                        currentSites = sites;
                                    }
                                    else
                                    {
                                        currentSites.addAll(sites);
                                    }
                                    
                                    siteResults.put(diagnosis, currentSites);
                                    //System.err.println ("Added to diagnosis: " + diagnosis.getCoveredText() + ", sites: " + sites + ", from diagnosis " + corefDiag.getCoveredText());
                                }
                                if (gradeResults.containsKey(corefDiag))
                                {
                                    Collection<Annotation> grades = gradeResults.get(corefDiag);
                                    Collection<Annotation> currentGrades = gradeResults.get(diagnosis);
                                    if (currentGrades == null)
                                    {
                                        currentGrades = grades;
                                    }
                                    else
                                    {
                                        currentGrades.addAll(grades);
                                    }
                                    gradeResults.put(diagnosis, currentGrades);
                                    //System.err.println ("Added to diagnosis: " + diagnosis.getCoveredText() + ", Grades: " + grades + ", from diagnosis " + corefDiag.getCoveredText());
                                }
                                if (sizeResults.containsKey(corefDiag))
                                {
                                    Collection<Annotation> sizes = sizeResults.get(corefDiag);
                                    Collection<Annotation> currentSizes = sizeResults.get(diagnosis);
                                    if (currentSizes == null)
                                    {
                                        currentSizes = sizes;
                                    }
                                    else
                                    {
                                        currentSizes.addAll(sizes);
                                    }
                                    sizeResults.put(diagnosis, currentSizes);
                                    //System.err.println ("Added to diagnosis: " + diagnosis.getCoveredText() + ", Sizes: " + sizes + ", from diagnosis " + corefDiag.getCoveredText());
                                }
                                toRemove.add (corefDiag);
                                //System.err.println ("toRemove diagnosis: " + corefDiag.getCoveredText() + ", begin: " + corefDiag.getBegin());
                            }
                        }
                    }
                }
            }
        }
        Iterator<Annotation> removeIter = toRemove.iterator();
        while (removeIter.hasNext())
        {
            Object obj = removeIter.next();
            siteResults.remove(obj);
            gradeResults.remove(obj);
            sizeResults.remove(obj);
        }
        
        diagnosesInSubsection.removeAll(toRemove);
        
    }



    private boolean sameGrades (Annotation diagnosis, Annotation corefDiag, Map<Annotation, Collection<Annotation>> gradeResults)
    {
        if (gradeResults.containsKey(diagnosis) && gradeResults.containsKey(corefDiag))
        {
            Collection<Annotation> diagnosisGrades = gradeResults.get(diagnosis);
            Collection<Annotation> corefGrades = gradeResults.get(corefDiag);
            if ((! corefGrades.containsAll(diagnosisGrades)) ||
                (! diagnosisGrades.containsAll(corefGrades)))
            {
                //System.err.println ("sameGrades = false");
                //System.err.println ("diagnosisGrades: " + diagnosisGrades);
                //System.err.println ("corefGrades: " + corefGrades);
                return false;
            }
        }
        return true;
    }



    private boolean sameTypeOfDiagnosis (Annotation diagnosis1, Annotation diagnosis2)
    {
        String diagnosis1type = categorize1Diagnosis(diagnosis1);
        String diagnosis2type = categorize1Diagnosis(diagnosis2);
        //System.err.println ("diagnosis1: " + diagnosis1 + ", diagnosis1type: " + diagnosis1type);
        //System.err.println ("diagnosis2: " + diagnosis2 + ", diagnosis2type: " + diagnosis2type);
        if ((diagnosis1type == null) || (diagnosis2type == null))
        {
            //System.err.println ("diagnoses NOT EQUAL");
            return false;
        }
        else if (diagnosis1type.equals(diagnosis2type) || ((! diagnosis1type.equals (LYMPH_DIAGNOSIS_KEY)) && (! diagnosis2type.equals (LYMPH_DIAGNOSIS_KEY))))
        {
            //System.err.println ("diagnoses EQUAL");
            return true;
        }
        //System.err.println ("diagnoses NOT EQUAL");
        return false;
    }



    private void processAllSpans (List<Annotation> diagnosesInSubsection, int spanMapIndex, Map<?, ?> [] diagnosisSpanMaps, Map<?, ?> [] siteSpanMaps, Map<?, ?> [] gradeSpanMaps, Map<?, ?> [] sizeSpanMaps, Set<Annotation> sitesRemaining, Map<Annotation, Collection<Annotation>> siteResults, Set<Annotation> gradesRemaining, Map<Annotation, Collection<Annotation>> gradeResults, Set<Annotation> sizesRemaining, Map<Annotation, Collection<Annotation>> sizeResults, Set<Annotation> originSites, Map<Annotation, Collection<Annotation>> originResults)
    {
        Map<?, ?> diagnosisSpanMap = diagnosisSpanMaps[spanMapIndex];
        //System.err.println ("diagnosisSpanMap: " + diagnosisSpanMap);
        //System.err.println ("diagnosisSpanMap size: " + ((diagnosisSpanMap == null) ? 0 : diagnosisSpanMap.size()));
        
        Iterator<?> diagnosesIter = diagnosesInSubsection.iterator();
        while (diagnosesIter.hasNext())
        {
            Annotation diagnosis = (Annotation) diagnosesIter.next();
            
            //System.err.println ("Processing diagnosis: " + diagnosis.getCoveredText() + ", begin: " + diagnosis.getBegin() + ", negated? " + DiagnosisAnnotationAccessor.isNegated (diagnosis));
            if ((! DiagnosisAnnotationAccessor.isNegated (diagnosis)) && (diagnosisSpanMap != null))
            {
                if (! siteResults.containsKey(diagnosis))
                {
                    processSpanForSite (sitesRemaining, diagnosis, (Annotation) diagnosisSpanMap.get(diagnosis), siteSpanMaps[spanMapIndex], site2ppMap, siteResults, originSites, originResults);
                }
                if (! gradeResults.containsKey(diagnosis))
                {
                    processSpanForFeature (gradesRemaining, diagnosis, (Annotation) diagnosisSpanMap.get(diagnosis), gradeSpanMaps[spanMapIndex], grade2ppMap, grade2sentenceMap, gradeResults);
                }
                if (! sizeResults.containsKey(diagnosis))
                {
                    processSpanForFeature (sizesRemaining, diagnosis, (Annotation) diagnosisSpanMap.get(diagnosis), sizeSpanMaps[spanMapIndex], size2ppMap, size2sentenceMap, sizeResults);
                }
            }
        }
        //return sitesRemaining;
    }

    private void processSpanForSite (Set<Annotation> sitesRemaining, Annotation diagnosis, Annotation diagnosisSpan, Map<?, ?> site2SpanMap, Map<Annotation, Annotation> site2ppMap, Map<Annotation, Collection<Annotation>> results, Collection<Annotation> originSites, Map<Annotation, Collection<Annotation>> originResults)
    {
        //System.err.println ("processSpanForSite: BEGIN");
        //System.err.println ("Diagnosis: " + diagnosis.getCoveredText());
        //System.err.println ("Diagnosis Span: " + diagnosisSpan);

        // now go through all sites to see how they relate to this diagnosis
        Iterator<?> siteIter = sitesRemaining.iterator();
        Collection<Annotation> toRemove = new ArrayList<Annotation> ();
        
        while (siteIter.hasNext())
        {
            Annotation site = (Annotation) siteIter.next();

            //System.err.println ("Site: " + site.getCoveredText() + ", begin: " + site.getBegin());
            if (SiteAnnotationAccessor.isNegated (site))
            {
                //System.err.println ("SKIPPING NEGATED SITE: " + site.getCoveredText());
                toRemove.add (site);
            }
            else
            {
                Annotation siteSpan = (Annotation) site2SpanMap.get(site);

                //System.err.println ("Site Sentence: " + siteSpan);
                if ((siteSpan != null) && (siteSpan.equals(diagnosisSpan)))
                {
                    //System.err.println ("Site span == Diagnosis span: " + siteSpan);
                    // the site is in the same span as the diagnosis!!
                    if (originSites.contains(site) || anyMetastaticContainersContain (site, metastaticContainerMap) || betweenMetastaticAndDiagnosis (site, diagnosis))
                    {
                        //System.err.println ("originSites.contains(site) || anyMetastaticContainersContain (site, metastaticContainerMap) || betweenMetastaticAndDiagnosis (site, diagnosis): " + site);
                        Collection<Annotation> sites = originResults.get(diagnosis);
                        if (sites == null)
                        {
                            sites = new HashSet<Annotation> ();
                        }
                        sites.add(site);
                        toRemove.add(site);
                        originResults.put(diagnosis, sites);
                    }
                    //else if ((invasionSite (diagnosis, site, siteSpan)) || isLymph(site) || (followsExclusionaryPP (site, site2sentenceMap)))
                    else if ((invasionSite (diagnosis, site, siteSpan)) || (followsExclusionaryPP (site, site2sentenceMap)))
                    {
                        //System.err.println ("invasionSite (diagnosis, site, siteSpan)) || isLymph(site) || (followsExclusionaryPP (site): " + site);
                        toRemove.add(site);
                    }
                    else  
                    {
                        //System.err.println ("adding site to diagnosis, site: " + site);
                        Collection<Annotation> sites = results.get(diagnosis);
                        if (sites == null)
                        {
                            sites = new HashSet<Annotation> ();
                        }
                        sites.add(site);
                        toRemove.add(site);
                        results.put(diagnosis, sites);
                    }
                }
            }
        }
        sitesRemaining.removeAll(toRemove);
        //System.err.println ("processSpanForSite: END");
    }

    
    

    private boolean betweenMetastaticAndDiagnosis (Annotation site, Annotation diagnosis)
    {
        Annotation sentence = (Annotation) site2sentenceMap.get(site);
        Collection<SCRNamedEntity> metastatics = findMetastatic(sentence);
        if (metastatics != null)
        {
            Iterator<SCRNamedEntity> metaIter = metastatics.iterator();
            while (metaIter.hasNext())
            {
                Annotation metastatic = metaIter.next ();
                if (metastatic.getBegin() < diagnosis.getBegin())
                {
                    if ((metastatic.getBegin() < site.getBegin ()) && (site.getEnd() < diagnosis.getEnd()))
                    {
                        return true;
                    }
                }
            }
        }
        
        return false;
    }



    private boolean anyMetastaticContainersContain (Annotation site, Map<Annotation, Set<Annotation>> metastaticContainerMap)
    {
        if (metastaticContainerMap != null)
        {
            Set<Annotation> metastatics = metastaticContainerMap.keySet();
            Iterator<Annotation> metaIter = metastatics.iterator();
            while (metaIter.hasNext())
            {
                Annotation metastaticDiagnosis = metaIter.next();
                if ((metastaticDiagnosis.getBegin() <= site.getBegin()) && (metastaticDiagnosis.getEnd() >= site.getEnd()))
                {
                    //System.err.println ("METASTATIC DIAGNOSIS: "  + metastaticDiagnosis.getCoveredText() + " CONTAINS SITE: " + site.getCoveredText());
                    return true;
                }
            }
        }
        return false;
    }



    private void processSpanForFeature (Set<Annotation> featuresRemaining, Annotation diagnosis, Annotation diagnosisSpan, Map<?, ?> feature2SpanMap, Map<Annotation, Annotation> feature2ppMap, Map<Annotation, Annotation> feature2sentenceMap, Map<Annotation, Collection<Annotation>> results)
    {
        //System.err.println ("processSpanForGrade: BEGIN");
        //System.err.println ("Diagnosis: " + diagnosis.getCoveredText());
        //System.err.println ("Diagnosis Span: " + diagnosisSpan);
        
        // now go through all grades to see how they relate to this diagnosis
        Iterator<Annotation> featureIter = featuresRemaining.iterator();
        Collection<Annotation> toRemove = new ArrayList<Annotation> ();
        
        while (featureIter.hasNext())
        {
            Annotation feature = featureIter.next();

            //System.err.println ("Feature: " + feature.getCoveredText());
            Annotation featureSpan = (Annotation) feature2SpanMap.get(feature);

            //System.err.println ("Feature Sentence: " + featureSpan);
            if ((featureSpan != null) && (featureSpan.equals(diagnosisSpan)))
            {
                //System.err.println ("Feature span == Diagnosis span: " + featureSpan);
                //System.err.println ("checking PP, feature: " + feature.getCoveredText());

                // the feature is in the same span as the diagnosis!!
                if (followsExclusionaryPP (feature, feature2sentenceMap))
                {
                    toRemove.add(feature);
                }
                else
                {
                    //System.err.println("(! inclusivePP (pp), diagnosis: '" + diagnosis.getCoveredText() + "', begin: " + diagnosis.getBegin() + ", feature: " + feature.getCoveredText() + ", begin: " + feature.getBegin());
                    Collection<Annotation> features = results.get(diagnosis);
                    if (features == null)
                    {
                        features = new HashSet<Annotation> ();
                    }
                    features.add(feature);
                    toRemove.add(feature);
                    results.put(diagnosis, features);
                }
            }
        }
        featuresRemaining.removeAll(toRemove);
        //System.err.println ("processSpanForFeature: END");
    }


    
    protected boolean isOK_Marker (SCRNamedEntity term)
    {
        return ((term.getNegation() == 0) && (term.getModifier() == 0));
    }

    
    private Collection<SCRNamedEntity> findEntitiesWithSemClass (Annotation span, String semClass)
    {
        Collection<?> terms = annotationMapper.getSubAnnotations (jcas, namedEntityAnnotationClass, span);
        Collection<SCRNamedEntity> results = new ArrayList<SCRNamedEntity> ();
        Iterator<?> iter = terms.iterator();
        while (iter.hasNext())
        {
            SCRNamedEntity term = (SCRNamedEntity) iter.next();
            if ((isOK_Marker (term)) && term.getSemanticClass().equals(semClass))
            {
                results.add(term);
            }
        }
        return results;
    }


    private Collection<SCRNamedEntity> findMetastatic (Annotation span)
    {
        return findEntitiesWithSemClass (span, ENTITY_SEMCLASS_METASTATIC);
    }
    
    private Collection<?> findOrigin (Annotation span)
    {
        Collection<?> results = annotationMapper.getSubAnnotations (jcas, originTermAnnotationClass, span);
        return results;
    }

    private Collection<SCRNamedEntity> findInvasion (Annotation span)
    {
        return findEntitiesWithSemClass (span, ENTITY_SEMCLASS_INVASIVE);
    }


    private Collection<SCRNamedEntity> findLymph (Annotation span)
    {
        return findEntitiesWithSemClass (span, ENTITY_SEMCLASS_LYMPH);
    }



    /*
    private static void printRuleName (String ruleName)
    {
        System.err.println ("** Applying rule: " + ruleName);
    }
    */
    
    private boolean isLymph (Annotation site)
    {
        //TODO: this is a lymph entry--generalize this
        String code = SiteAnnotationAccessor.getCodeValue(site);
        return code.matches("^[Cc]77\\..*");
    }



    private boolean invasionSite (Annotation diagnosis, Annotation site, Annotation span)
    {
        //Annotation sentence = (Annotation)terms2sentenceMap.get(site);
        Collection<SCRNamedEntity> invasions = findInvasion (span);
        Iterator<SCRNamedEntity> invIter = invasions.iterator();
        while (invIter.hasNext())
        {
            Annotation invasion = invIter.next();
            if ((diagnosis.getBegin() < invasion.getBegin()) && (site.getBegin() > invasion.getBegin()))
            {
                //System.err.println("****>> invasion site: " + site.getCoveredText());
                return true;
            }
        }
        return false;
    }

    


    private String findDiagnosesCategory (Collection<Annotation> diagnoses, Map<Annotation, String> categorizedDiagnoses)
    {
        //System.err.println("findDiagnosesCategory: BEGIN");      
        String result = BENIGN_DIAGNOSIS_KEY;

        Iterator<?> diagnosisIter = diagnoses.iterator();
        while (diagnosisIter.hasNext())
        {
            Annotation diagnosis = (Annotation) diagnosisIter.next();
            //System.err.println("findDiagnosesCategory, diagnosis: " + diagnosis.getCoveredText());      
            String cat = (String) categorizedDiagnoses.get(diagnosis);
            //System.err.println("findDiagnosesCategory, cat: " + cat);      
            // metastatic always wins
            if (cat.equals(METASTATIC_DIAGNOSIS_KEY))
            {
                //System.err.println("findDiagnosesCategory: METASTATIC_DIAGNOSIS_KEY");      
                return METASTATIC_DIAGNOSIS_KEY;
            }
            // primary beats benign
            else if (! cat.equals(BENIGN_DIAGNOSIS_KEY))
            {
                result = cat;
            }
        }
        //System.err.println("findDiagnosesCategory: " + result);      
        return result;
    }


    private void categorizeAndMapAll (List<Annotation> diagnosesInSubsection, Map<Annotation, Collection<Annotation>> diagnosis2sites, Map<Annotation, Collection<Annotation>> gradeResults, Map<Annotation, Collection<Annotation>> sizeResults, Map<Annotation, String> categorizedDiagnoses)
    {
        //System.err.println ("categorizeAndMapAll, BEGIN");
        Collection<Collection<Annotation>> overlappingDiagnoses = findOverlappingDiagnoses (diagnosesInSubsection);
        Map<Annotation, Set<Annotation>> diagnosesWithSameSites = findDiagnosesWithSameSites (diagnosesInSubsection, diagnosis2sites);
        
        Collection<Collection<Annotation>> allDiagnoses = mergeDiagnoses (overlappingDiagnoses, diagnosesWithSameSites);
        
        Iterator<Collection<Annotation>> allDiagnosesIter = allDiagnoses.iterator();
        while (allDiagnosesIter.hasNext())
        {
            Collection<Annotation> diagnoses = allDiagnosesIter.next();
            //System.err.println ("categorizeAndMapAll, diagnoses: " + diagnoses);
            
            Collection<Annotation> sitesForDiagnosis = new HashSet<Annotation> ();
            Collection<Annotation> gradesForDiagnosis = new HashSet<Annotation> ();
            Collection<Annotation> sizesForDiagnosis = new HashSet<Annotation> ();

            Iterator<Annotation> thisDiagnosisIter = diagnoses.iterator();
            while (thisDiagnosisIter.hasNext())
            {
                Annotation diagnosis = (Annotation) thisDiagnosisIter.next();
                //System.err.println ("categorizeAndMapAll, diagnosis: " + diagnosis.getCoveredText() + " at: " + diagnosis.getBegin());

                //System.err.println ("categorizeAndMapAll, adding sites");
                Collection<Annotation> sites = diagnosis2sites.get (diagnosis);
                if (sites != null)
                {
                    //System.err.println ("categorizeAndMapAll, sites: " + sites);
                    sitesForDiagnosis.addAll(sites);
                }
            
                //System.err.println ("categorizeAndMapAll, adding grades");
                Collection<Annotation> grades = gradeResults.get (diagnosis);
                if (grades != null)
                {
                    gradesForDiagnosis.addAll(grades);
                }
            
                //System.err.println ("categorizeAndMapAll, adding sizes");
                Collection<Annotation> sizes = sizeResults.get (diagnosis);
                if (sizes != null)
                {
                    sizesForDiagnosis.addAll(sizes);
                }
            }
            
            //System.err.println ("categorizeAndMapAll, done adding sites, grades, sizes");

            TumorModelParts modelParts = new TumorModelParts (diagnoses, sitesForDiagnosis, gradesForDiagnosis, sizesForDiagnosis);
            String type = findDiagnosesCategory (diagnoses, categorizedDiagnoses);
            if (type.equals(METASTATIC_DIAGNOSIS_KEY))
            {
                //System.err.println ("categorizeAndMapAll, adding to metastaticDiagnoses: " + modelParts);
                metastaticDiagnoses.add (modelParts);
            }
            else if (type.equals(PRIMARY_DIAGNOSIS_KEY))
            {
                primaryDiagnoses.add (modelParts);
                //System.err.println ("categorizeAndMapAll, adding to primaryDiagnoses: " + modelParts);
            }
            else if (type.equals(LYMPH_DIAGNOSIS_KEY))
            {
                //System.err.println ("categorizeAndMapAll, adding to LYMPH_DIAGNOSIS, doing nothing");
                // do nothing
            }
            else
            {
                //System.err.println ("categorizeAndMapAll, adding to otherDiagnoses: " + modelParts);
                otherDiagnoses.add (modelParts);
            }
        }

    }

    private Collection<Collection<Annotation>> mergeDiagnoses (Collection<Collection<Annotation>> overlappingDiagnoses, Map<Annotation, Set<Annotation>> diagnosesWithSameSites)
    {
        Set<Collection<Annotation>> undone = new HashSet<Collection<Annotation>> ();
        Set<Annotation> doneSoFar = new HashSet<Annotation> ();
        
        Iterator<Collection<Annotation>> overlappingIterator = overlappingDiagnoses.iterator();
        while (overlappingIterator.hasNext())
        {
            Collection<Annotation> diagnoses = overlappingIterator.next();
            //System.err.println ("mergeDiagnoses, overlapping: " + diagnoses);
            if (diagnoses.size() == 1)
            {
                undone.add(diagnoses);
            }
            else
            {
                mergeOneDiagnosisSet(diagnosesWithSameSites, diagnoses);
                doneSoFar.addAll(diagnoses);
            }
        }

        //undone.removeAll(doneSoFar);
        Iterator<Collection<Annotation>> undoneIterator = undone.iterator();
        while (undoneIterator.hasNext())
        {
            Collection<Annotation> diagnoses = undoneIterator.next();
            //System.err.println ("UNDONE: " + diagnoses);
            //System.err.println ("TO REMOVE: " + doneSoFar);
            diagnoses.removeAll(doneSoFar);
            //System.err.println ("AFTER REMOVE: " + diagnoses);
            mergeOneDiagnosisSet(diagnosesWithSameSites, diagnoses);
        }
        
        return overlappingDiagnoses;
    }



    private void mergeOneDiagnosisSet (Map<Annotation, Set<Annotation>> diagnosesWithSameSites, Collection<Annotation> diagnoses)
    {
        Iterator<Annotation> diagIter = diagnoses.iterator();
        Collection<Annotation> allToAdd = new HashSet<Annotation> ();
        while (diagIter.hasNext())
        {
            Collection<Annotation> itemToAdd = diagnosesWithSameSites.get(diagIter.next());
            if (itemToAdd != null)
            {
                allToAdd.addAll(itemToAdd);
            }
        }
        if (! allToAdd.isEmpty())
        {
            diagnoses.addAll(allToAdd);
        }
    }



    // return map from diagnoses to sets of diagnoses that apply to the exact same sites
    private Map<Annotation, Set<Annotation>> findDiagnosesWithSameSites (List<Annotation> diagnosesInSubsection, Map<Annotation, Collection<Annotation>> diagnosis2sites)
    {
        Map<Annotation, Set<Annotation>> result = new HashMap<Annotation, Set<Annotation>> ();

        for (int i = 0; i < diagnosesInSubsection.size(); i++)
        {
            Annotation currentDiagnosis = (Annotation) diagnosesInSubsection.get(i);
            //System.err.println ("findDiagnosesWithSameSites, processing diagnosis[" + i + "]: "  + currentDiagnosis.getCoveredText());
            Set<Annotation> currentDiagosisSites = new HashSet<Annotation> (diagnosis2sites.get(currentDiagnosis));
            Set<Annotation> sameSites = null;
            
            if (currentDiagosisSites != null)
            {
                for (int j = i + 1; j < diagnosesInSubsection.size(); j++)
                {
                    Annotation diagnosisToCompare = (Annotation) diagnosesInSubsection.get(j);
                    Set<?> sitesToCompare = new HashSet<Object> (diagnosis2sites.get(diagnosisToCompare));
                    if (currentDiagosisSites.equals(sitesToCompare))
                    {
                        sameSites = addToSameSites (currentDiagnosis, diagnosisToCompare, sameSites);
                    }
                }
                if (sameSites != null)
                {
                    Iterator<Annotation> iter = sameSites.iterator();
                    while (iter.hasNext())
                    {
                        result.put(iter.next(), sameSites);
                    }
                }
            }
        }
        
        return result;
    }



    private Set<Annotation> addToSameSites (Annotation currentDiagnosis, Annotation diagnosisToCompare, Set<Annotation> sameSites)
    {
        if (sameSites == null)
        {
            sameSites = new HashSet<Annotation> ();
            sameSites.add(currentDiagnosis);
        }

        sameSites.add(diagnosisToCompare);
        return sameSites;
    }



    private Collection<Collection<Annotation>> findOverlappingDiagnoses (List<Annotation> diagnosesInSubsection)
    {
        Collection<Collection<Annotation>> result = new ArrayList<Collection<Annotation>> ();
        Set<Annotation> sortedDiagnoses = new TreeSet<Annotation> (new UIMAAnnotationOffsetComparator ());
        sortedDiagnoses.addAll(diagnosesInSubsection);
        
        Annotation currentDiagnosis = null;
        Collection<Annotation> currentResult = null;
        int currentBegin = 0;
        int currentEnd = 0;
        Iterator<Annotation> diagnosisIter = sortedDiagnoses.iterator();
        while (diagnosisIter.hasNext())
        {
            currentDiagnosis = (Annotation) diagnosisIter.next();
            //System.err.println ("findOverlappingDiagnoses, processing diagnosis: "  + currentDiagnosis.getCoveredText());
            if (! DiagnosisAnnotationAccessor.isNegated(currentDiagnosis))
            {
                if (currentResult != null)
                {
                    if ((currentDiagnosis.getBegin() >= currentBegin) && (currentDiagnosis.getBegin() <= currentEnd))
                    {
                        currentEnd = Math.max(currentEnd, currentDiagnosis.getEnd());
                        currentResult.add(currentDiagnosis);
                    }
                    else
                    {
                        result.add(currentResult);
                        currentResult = null;
                    }
                    
                }
                if (currentResult == null)
                {
                    currentResult = new HashSet<Annotation> ();
                    currentBegin = currentDiagnosis.getBegin();
                    currentEnd = currentDiagnosis.getEnd();
                    currentResult.add(currentDiagnosis);
                }
            }        
        }
        if (currentResult != null)
        {
            result.add(currentResult);
        }
        
        //Iterator it = result.iterator();
        //while (it.hasNext())
        //{
        //    Collection diags = (Collection) it.next();
        //    System.err.println ("New grouping:");
        //    Iterator diagIt = diags.iterator();
        //    while (diagIt.hasNext())
        //    {
        //        Annotation d = (Annotation) diagIt.next();
        //        System.err.println ("\t" + d.getCoveredText());
        //    }
        //}
        
        return result;
    }



    private Set<Annotation> addToFinishedSet (Set<Annotation> itemsDone, Collection<?> diagnoses, Map<Annotation, Collection<Annotation>> gradeResults)
    {
        Iterator<?> diagnosesIter = diagnoses.iterator();
        while (diagnosesIter.hasNext())
        {
            Annotation diagnosis = (Annotation) diagnosesIter.next();
            FSArray corefs = DiagnosisAnnotationAccessor.getCorefs(diagnosis);
            itemsDone.add(diagnosis);
            //System.err.println("ADDING DIAGNOSIS TO DONE: " + diagnosis.getCoveredText());
            if (corefs != null)
            {
                for (int corefIndex = 0; corefIndex < corefs.size(); corefIndex++)
                {
                    SCRCoreference coref = (SCRCoreference)corefs.get(corefIndex);
                    if (coref != null)
                    {
                        FSArray elements = coref.getElements();
                        if (elements != null)
                        {
                            for (int elementIndex = 0; elementIndex < elements.size(); elementIndex++)
                            {
                                //System.err.println("ADDING COREF TO DONE: " + ((Annotation)elements.get(elementIndex)).getCoveredText());
                                Annotation corefDiagnosis = (Annotation) elements.get(elementIndex);
                                if (sameGrades(diagnosis, corefDiagnosis, gradeResults))
                                {
                                    itemsDone.add(corefDiagnosis);
                                }
                            }
                        }
                    }
                }

            }
        }
        return itemsDone;
    }



    private Collection<Annotation> findGrossDescriptionSites ()
    {
        Collection<Annotation> result = new ArrayList<Annotation> ();
        
        Iterator<?> gdIter = grossDescriptionSites.iterator();
        while (gdIter.hasNext())
        {
            Annotation term = (Annotation) gdIter.next();
            if (! isLymph(term))
            {
                result.add(term);
            }
        }
        return result;
    }


    private boolean followsExclusionaryPP (Annotation feature, Map<Annotation, Annotation> feature2sentenceMap)
    {
        Annotation sentence = (Annotation) feature2sentenceMap.get (feature);
        Collection<Annotation> pps = (Collection<Annotation>) sentence2ppMap.get (sentence);
        if (pps != null)
        {
            Iterator<Annotation> ppIter = pps.iterator();
            while (ppIter.hasNext())
            {
                Annotation pp = ppIter.next();
                if ((! inclusivePP(pp)) && (feature.getBegin() > pp.getBegin()))
                {
                    return true;
                }
            }
        }
        return false;
    }
    
    
    private boolean inclusivePP (Annotation pp)
    {
        //System.err.println("inclusivePP?");
        if (pp != null)
        {
            List<Annotation> tokens = (List<Annotation>)pp2tokenMap.get(pp);
            
            if (tokens != null)
            {
                String token = (tokens.get(0)).getCoveredText().toLowerCase();
                //System.err.println("\tpreposition: '" + token + "'");
            
                if (exclusionPreps.contains(token))
                {
                    //System.err.println("\t==> EXCLUSION");
                    return false;
                }
            }
            //else
            //{
            //    System.err.println("\ttokens == null");
            //}
        }
        //else
        //{
        //    System.err.println("\tpp == null");
        //}
        //System.err.println("\t==> INCLUSION");
        return true;
    }

    

    private SCRPrimaryTumor createPrimaryDiagnosis (JCas jcas, Collection<Annotation> diagnoses, Collection<Annotation> sites, Collection<Annotation> grades, TumorSizeAnnotation sizeAnnotation)
    {
        SCRPrimaryTumor primary = null;
        
        if (sites.isEmpty() && grades.isEmpty())
        {
            // do nothing
        }
        else
        {
            SCRPrimaryTumorReading diagnosis = new SCRPrimaryTumorReading (jcas);
            setTumorBounds (diagnosis, diagnoses);
            diagnosis.setPrimarySites(createFSArray (jcas, sites));
            diagnosis.setDiagnoses(createFSArray (jcas, diagnoses));
            if ((grades != null) && (grades.size() > 0))
            {
                diagnosis.setGradeValues(makeGradesFSArray (grades));
            }
            diagnosis.setSize (findSCRSizeForTumorSize (sizeAnnotation));
    
            primary = new SCRPrimaryTumor (jcas);
            primary.setBegin(diagnosis.getBegin());
            primary.setEnd(diagnosis.getEnd());
            FSArray diagnosisCoref = new FSArray (jcas, 1);
            diagnosisCoref.set(0, diagnosis);
            primary.setReadings(diagnosisCoref);
            diagnosis.addToIndexes();
            primary.addToIndexes();
        }
        return primary;
    }



    private SCRSize findSCRSizeForTumorSize (TumorSizeAnnotation sizeAnnotation)
    {
        SCRSize scrSize = null;
        
        if (sizeAnnotation != null)
        {
            RangeAnnotation range = sizeAnnotation.getRange();
            if ((range != null) && (range.getHigh() != null))
            {
                try
                {
                    FSIterator it = FeatureConstrainedIterator.getSameOffsetIterator (jcas, SCRSize.class, range.getHigh());
                    if (it.hasNext())
                    {
                        scrSize = (SCRSize) it.next();
                    }
                    if (scrSize == null)
                    {
                        //System.err.println ("findSCRSizeForTumorSize could not find SCRSize annotation for: " + sizeAnnotation + "!!!");
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return scrSize;
    }



    private FSArray makeGradesFSArray (Collection<Annotation> grades)
    {
        FSArray gradeArray = new FSArray (jcas, grades.size());

        Iterator<Annotation> gradeIter = grades.iterator();
        int i = 0;
        while (gradeIter.hasNext())
        {
            GradeAnnotation grade = (GradeAnnotation) gradeIter.next();
            SCRGradeValue scrGrade = MedKATTypeConverter.convert(jcas, grade);
            gradeArray.set(i, scrGrade);
            i += 1;
        }
        
        return gradeArray;
    }


    private SCRMetastaticTumor createMetastaticDiagnosis (JCas jcas, Collection<Annotation> diagnoses, Collection<Annotation> sites, Collection<Annotation> grades, TumorSizeAnnotation sizeAnnotation, Map<Annotation, Collection<Annotation>> originResults, Map<Annotation, Set<Annotation>> metastaticContainerMap)
    {
        SCRMetastaticTumor metastatic = null;
        
        if (sites.isEmpty() && grades.isEmpty())
        {
            // do nothing
        }
        else
        {
            //if (metastaticContainerMap != null)
            //{
            //    if (diagnoses != null)
            //    {
            //        Iterator diagIter = diagnoses.iterator();
            //        if (diagIter.hasNext())
            //        {
            //            Annotation diagnosisAnnot = (Annotation) diagIter.next();
            //            Collection contained = (Collection) metastaticContainerMap.get(diagnosisAnnot);
            //            if (contained != null)
            //            {
            //                diagnoses.addAll(contained);
            //            }
            //        }
            //    }
            //}
            SCRMetastaticTumorReading diagnosis = new SCRMetastaticTumorReading (jcas);
            setTumorBounds (diagnosis, diagnoses);
            diagnosis.setPrimarySites(createFSArray (jcas, sites));
            diagnosis.setDiagnoses(createFSArray (jcas, diagnoses));
            Annotation origin = getOriginFromMap (originResults, diagnoses);
            diagnosis.setOriginatingSite(origin);
            if ((grades != null) && (grades.size() > 0))
            {
                diagnosis.setGradeValues(makeGradesFSArray (grades));
            }
            diagnosis.setSize (findSCRSizeForTumorSize (sizeAnnotation));
    
            metastatic = new SCRMetastaticTumor (jcas);
            metastatic.setBegin(diagnosis.getBegin());
            metastatic.setEnd(diagnosis.getEnd());
            FSArray diagnosisCoref = new FSArray (jcas, 1);
            diagnosisCoref.set(0, diagnosis);
            metastatic.setReadings(diagnosisCoref);
            diagnosis.addToIndexes();
            metastatic.addToIndexes();
        }
            return metastatic;
    }




    private Annotation getOriginFromMap (Map<Annotation, Collection<Annotation>> diagnosis2origins, Collection<Annotation> diagnoses)
    {
        Annotation origin = null;
        
        Iterator<Annotation> diagnosisIter = diagnoses.iterator();
        while (diagnosisIter.hasNext())
        {
            Object diagnosis = diagnosisIter.next();
            Collection<Annotation> origins = diagnosis2origins.get (diagnosis);
            
            if ((origins != null) && (! origins.isEmpty()))
            {
                Iterator<Annotation> iter = origins.iterator();
                if (iter.hasNext())
                {
                    Annotation newOrigin = iter.next();
                    if (origin == null)
                    {
                        origin = newOrigin;
                    }
                    else if (! origin.equals(newOrigin))
                    {
                        //System.err.println ("Origin site CLASH: " + origin.getCoveredText() + ", " + newOrigin.getCoveredText());
                        logger.logWarning("Document: " + DocumentURI.get(jcas) + "Origin site CLASH: " + origin.getCoveredText() + ", " + newOrigin.getCoveredText());
                    }
                    //System.err.println ("Origin site: " + origin.getCoveredText());
                }
            }
        }
        return origin;
    }



    private void setTumorBounds (Annotation tumor, Collection<Annotation> diagnoses)
    {
        int begin = -1;
        int end = -1;
        
        if ((diagnoses == null) || (diagnoses.size() == 0))
        {
            begin = 0;
            end = 1;
        }
        else
        {
            Iterator<Annotation> iter = diagnoses.iterator();
            while (iter.hasNext())
            {
                Annotation diagnosis = iter.next();
                if (begin == -1)
                {
                    begin = diagnosis.getBegin();
                    end = diagnosis.getEnd();                    
                }
                else 
                {
                    begin = Math.min(begin, diagnosis.getBegin());
                    end = Math.max(end, diagnosis.getEnd());
                }
            }
        }
        tumor.setBegin(begin);
        tumor.setEnd(end);

    }



    private FSArray createFSArray (JCas jcas, Collection<Annotation> collection)
    {
        FSArray fsArray = null;
        
        if ((collection == null) || (collection.isEmpty()))
        {
            // do nothing
        }
        else
        {
            fsArray = new FSArray (jcas, collection.size());
            Iterator<Annotation> iter = collection.iterator();
            int i = 0;
            while (iter.hasNext())
            {
                fsArray.set(i, iter.next ());
                i += 1;
            }
            
        }
        return fsArray;
    }

    private Map<Annotation, String> categorizeDiagnosesNew (Collection<Annotation> diagnoses)
    {
        Map<Annotation, String> results = new HashMap<Annotation, String> ();
        
        Iterator<Annotation> diagnosisIter = diagnoses.iterator();
        
        while (diagnosisIter.hasNext())
        {
            Annotation diagnosis = diagnosisIter.next();
            String category = categorize1Diagnosis(diagnosis);
            if (category != null)
            {
                results.put(diagnosis, category);
            }
        }
        //adjustCategorizationForMetastasis (results);
        return results;
    }

    /*private void adjustCategorizationForMetastasis (Map results)
    {
        Iterator iter = results.keySet().iterator();
        Set toCheck = new HashSet ();
        while (iter.hasNext())
        {
            Object diagnosis = iter.next();
            String category = (String) results.get (diagnosis);
            if (category.equals(METASTATIC_DIAGNOSIS_KEY))
            {
                toCheck.add(diagnosis);
            }
        }
        Iterator metastaticIter = toCheck.iterator();
        while (metastaticIter.hasNext())
        {
            Annotation diagnosis = (Annotation) metastaticIter.next();
            FSArray corefs = DiagnosisAnnotationAccessor.getCorefs(diagnosis);
            for (int i = 0; i < corefs.size(); i++)
            {
                Annotation corefDiagnosis = (Annotation) corefs.get(i);
                //if (results.containsKey(corefDiagnosis))
                //{
                    results.put(corefDiagnosis, METASTATIC_DIAGNOSIS_KEY);
                //}
            }
        }
    }*/


    private boolean isMetastasicCoref (Annotation diagnosis, Annotation sentence)
    {
        FSArray corefs = DiagnosisAnnotationAccessor.getCorefs(diagnosis);
        for (int corefIndex = 0; corefIndex < corefs.size(); corefIndex++)
        {
            SCRCoreference coref = (SCRCoreference) corefs.get(corefIndex);
            if (coref != null)
            {
                FSArray elements = coref.getElements();
                for (int elementIndex = 0; elementIndex < elements.size(); elementIndex++)
                {
                    Annotation corefDiagnosis = (Annotation) elements.get(elementIndex);
                    {
                        if (! diagnosis.equals(corefDiagnosis))
                        {
                            if (isMetastaticDiagnosis (corefDiagnosis, sentence))
                            {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }


    private String categorize1Diagnosis (Annotation diagnosis)
    {
        //System.err.println ("categorize1Diagnosis, term: " + diagnosis.getCoveredText() + ", begin: " + diagnosis.getBegin());
        /*if (isMetastaticCode (diagnosis.getAttributeValue()))
        {
            System.err.println (" categorize1Diagnosis, category: " + METASTATIC_DIAGNOSIS_KEY);
            return METASTATIC_DIAGNOSIS_KEY;
        }
        else*/
        if (isBenignCode (DiagnosisAnnotationAccessor.getCodeValue(diagnosis)))
        {
            //System.err.println (" categorize1Diagnosis, category: " + BENIGN_DIAGNOSIS_KEY);
            return BENIGN_DIAGNOSIS_KEY;
        }
        else
        {
            Annotation sentence = (Annotation) diagnosis2sentenceMap.get(diagnosis);

            // do not process if from another doc section (sentence==null)
            if (sentence != null)
            {
                if (findLymph (sentence).size() > 0)
                {
                    //System.err.println (" categorize1Diagnosis, category: " + LYMPH_DIAGNOSIS_KEY);
                    return LYMPH_DIAGNOSIS_KEY;
                }
                //else if ((isMetastaticCode (DiagnosisAnnotationAccessor.getCodeValue(diagnosis))) || (findMetastatic (sentence).size() > 0) || (findOrigin (sentence).size() > 0))
                else if (isMetastaticDiagnosis (diagnosis, sentence) || isMetastasicCoref(diagnosis, sentence))
                {
                    //System.err.println (" categorize1Diagnosis, category: " + METASTATIC_DIAGNOSIS_KEY);
                    return METASTATIC_DIAGNOSIS_KEY;
                }
                else //if (sentenceNode.hasFeature(SIZE_FEATURE))
                {
                    //System.err.println (" categorize1Diagnosis, category: " + PRIMARY_DIAGNOSIS_KEY);
                    return PRIMARY_DIAGNOSIS_KEY;
                }
            }
            else 
            {
                //System.err.println (" categorizeDiagnoses, category: NONE (sentence==null)");
                //System.err.println ("categorize1Diagnosis, term: " + diagnosis.getCoveredText() + ", begin: " + diagnosis.getBegin());
                //return BENIGN_DIAGNOSIS_KEY;
                return null;
            }
        }

    }

    private boolean isMetastaticDiagnosis (Annotation diagnosis, Annotation sentence)
    {
        return (isMetastaticCode (DiagnosisAnnotationAccessor.getCodeValue(diagnosis))) || (findMetastatic (sentence).size() > 0) || (findOrigin (sentence).size() > 0);
    }
    



    private boolean isMetastaticCode (String diagnosisCode)
    {
        return (diagnosisCode.endsWith(metastaticSuffix));
    }



    private boolean isBenignCode (String diagnosisCode)
    {
        return (diagnosisCode.endsWith(benignSuffix));
    }





    private Collection<Annotation> findAllGrossDescriptionTerms (SubHeading subsection, Class<? extends Annotation> classOfTerms)
    {
        Collection<Annotation> grossDescriptionTerms = new ArrayList<Annotation> ();
        
        Iterator<Annotation> sentenceIter = sentences.iterator();
        while (sentenceIter.hasNext())
        {
            Annotation sentence = sentenceIter.next();
            //System.err.println ("Sentence[" + sentenceNumber++ + "]: "  + sentence.getCoveredText());
            
            if (grossDescriptionTerms.size() == 0)
            {
                Collection<Annotation> terms = annotationMapper.getSubAnnotations(jcas, classOfTerms, sentence);
                if (terms.size() == 0)
                {
                    //System.err.println ("NO GROSS DESCRIPTION TERMS IN SENTENCE! " + sentence.getCoveredText());
                }
                grossDescriptionTerms.addAll (terms);
                //Iterator gdIter = grossDescriptionTerms.iterator();
                //while (gdIter.hasNext())
                //{
                //    Annotation term = (Annotation) gdIter.next();
                    //System.err.println ("GROSS DESCRIPTION TERM: "  + term.getCoveredText());
                //}
            }
        }
        return grossDescriptionTerms;
    }




    private ArrayList<Annotation> getSubsectionSentenceAnnotations (JCas jcas, Annotation subsection)
    {
        FSIterator sentenceIter = getBeginEndConstrainedIter (jcas, sentenceAnnotationIndex.iterator (), subBeginFeat, subEndFeat, subsection.getBegin(), subsection.getEnd());
        return annotationMapper.getSubAnnotationsForIterator(sentenceIter, new UIMAAnnotationOffsetComparator ());
    }




}
