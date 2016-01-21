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

//import org.ohnlp.medkat.taes.coreferencer.CoreferringDiagnoses;
//import org.ohnlp.medkat.taes.coreferencer.CoreferringSites;
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
import org.ohnlp.medkat.common.DocumentURI;
import org.ohnlp.medkat.common.FeatureConstrainedIterator;
import org.ohnlp.medkat.common.ParameterProcessor;
import org.ohnlp.medkat.common.UIMAAnnotationOffsetComparator;
import org.ohnlp.medkat.common.UIMAAnnotationUtils;
import org.ohnlp.medkat.taes.conceptMapper.DictTerm;
import org.ohnlp.medkat.taes.conceptMapper.DictTermMarkers;
import org.ohnlp.medkat.taes.conceptMapper.OriginTerm;
import org.ohnlp.medkat.taes.coreferencer.CorefAnnotation;
import org.ohnlp.medkat.taes.coreferencer.CoreferringDiagnoses;
import org.ohnlp.medkat.taes.subsectionDetector.SubHeading;
import org.ohnlp.medkat.taes.subsectionProcessor.BasicSubsectionProcessor;

public class DiagnosisTypeAnnotator extends BasicSubsectionProcessor
{

    
    private static final String SIZE_FEATURE = "SIZE";
    private static final String METASTATIC_FEATURE = "META";
    private static final String LYMPH_FEATURE = "LYMPH";

    public class ObjectMaps extends ArrayList<TreeNode>
    {
        /**
         * 
         */
        private static final long serialVersionUID = 926018425287278111L;
        private Map<Annotation, Annotation> token2ppMap;
        private Map<Annotation, Annotation> term2npMap;
        private Map<Annotation, Annotation> term2ppMap;
        private Map<Annotation, Annotation> np2npCombinedMap;
        private Map<Annotation, Annotation> npList2npCombinedMap;
        private Map<Annotation, Annotation> npCombined2sentenceMap;
        private Map<Annotation, Integer> token2sentenceMap;
        private Map<Annotation, Integer> pp2sentenceMap;
        private Map<Annotation, Collection<Annotation>> sentenceSizeMap;
        private Map<Annotation, Collection<DictTerm>> sentenceMetastaticMap;
        private Map<Annotation, Collection<DictTerm>> sentenceLymphMap;
        
        public ObjectMaps ()
        {
            token2ppMap = new HashMap<Annotation, Annotation> ();
            term2npMap = new HashMap<Annotation, Annotation> ();
            term2ppMap = new HashMap<Annotation, Annotation> ();
            np2npCombinedMap = new HashMap<Annotation, Annotation> ();
            npList2npCombinedMap = new HashMap<Annotation, Annotation> ();
            npCombined2sentenceMap = new HashMap<Annotation, Annotation> ();
            token2sentenceMap = new HashMap<Annotation, Integer> ();
            pp2sentenceMap = new HashMap<Annotation, Integer> ();
            sentenceSizeMap = new HashMap<Annotation, Collection<Annotation>> ();
            sentenceMetastaticMap = new HashMap<Annotation, Collection<DictTerm>> ();
            sentenceLymphMap = new HashMap<Annotation, Collection<DictTerm>> ();
            
        }
        
        
        public Map<Annotation, Integer> getPp2sentenceMap ()
        {
            return pp2sentenceMap;
        }
        public void setPp2sentenceMap (Map<Annotation, Integer> pp2sentenceMap)
        {
            this.pp2sentenceMap = pp2sentenceMap;
        }
        


        public void addListToMap (Collection<Annotation> list, Annotation npList, Map<Annotation, Annotation> map)
        {
            Iterator<Annotation> listIter = list.iterator();
            while (listIter.hasNext())
            {
                map.put(listIter.next(), npList);
            }
        }

        
        public Map<Annotation, Annotation> getNP2npCombinedMap ()
        {
            return np2npCombinedMap;
        }


        public void setNP2npCombinedMap (Map<Annotation, Annotation> np2npCombinedMap)
        {
            this.np2npCombinedMap = np2npCombinedMap;
        }


        public Map<Annotation, Annotation> getNPCombined2sentenceMap ()
        {
            return npCombined2sentenceMap;
        }


        public void setNPCombined2sentenceMap (Map<Annotation, Annotation> npCombined2sentenceMap)
        {
            this.npCombined2sentenceMap = npCombined2sentenceMap;
        }



        public void addNPCombined2sentenceMap (Collection<Annotation> npCombineds, Annotation sentence)
        {
            addListToMap(npCombineds, sentence, npCombined2sentenceMap);
        }


        public Map<Annotation, Annotation> getNPList2npCombinedMap ()
        {
            return npList2npCombinedMap;
        }


        public void setNPList2npCombinedMap (Map<Annotation, Annotation> npList2npCombinedMap)
        {
            this.npList2npCombinedMap = npList2npCombinedMap;
        }



        public void addNPList2npCombinedMap (Collection<Annotation> npLists, Annotation npCombined)
        {
            addListToMap(npLists, npCombined, npList2npCombinedMap);
        }


        public void addNPs2npCombinedMap (Collection<Annotation> nps, Annotation npCombined)
        {
            addListToMap(nps, npCombined, np2npCombinedMap);
        }


        public Map<Annotation, Annotation> getTerm2npMap ()
        {
            return term2npMap;
        }


        public void setTerm2npMap (Map<Annotation, Annotation> term2npMap)
        {
            this.term2npMap = term2npMap;
        }



        public void addTerms2npMap (Collection<Annotation> tokens, Annotation np)
        {
            addListToMap(tokens, np, term2npMap);
        }


        public Map<Annotation, Annotation> getToken2ppMap ()
        {
            return token2ppMap;
        }
        public void setToken2ppMap (Map<Annotation, Annotation> token2ppMap)
        {
            this.token2ppMap = token2ppMap;
        }
        public Map<Annotation, Integer> getToken2sentenceMap ()
        {
            return token2sentenceMap;
        }
        public void setToken2sentenceMap (Map<Annotation, Integer> token2sentenceMap)
        {
            this.token2sentenceMap = token2sentenceMap;
        }


        public void addToSentencePPMaps (Integer sentenceNumber, Annotation pp, Annotation token)
        {
            //System.err.println("Adding token: '" + token.getCoveredText() + "', obj: " + token.getAddress() + ", to sentenceNumber: " + sentenceNumber.intValue() + " and pp: " + ((pp == null) ? "null" : pp.getCoveredText()));
            token2ppMap.put (token, pp);
            token2sentenceMap.put(token, sentenceNumber);
            if (pp != null)
            {
                pp2sentenceMap.put(pp, sentenceNumber);
            }
        }



        public void addToFeatureMaps (Annotation sentence, Collection<Annotation> sizes, Collection<DictTerm> metastatic, Collection<DictTerm> lymph)
        {
            sentenceSizeMap.put (sentence, sizes);
            sentenceMetastaticMap.put (sentence, metastatic);
            sentenceLymphMap.put (sentence, lymph);
        }



        public Map<Annotation, Annotation> getTerm2ppMap ()
        {
            return term2ppMap;
        }


        public void setTerm2ppMap (Map<Annotation, Annotation> term2ppMap)
        {
            this.term2ppMap = term2ppMap;
        }



        public void addTerm2ppMap (Collection<Annotation> tokens, Annotation pp)
        {
            addListToMap(tokens, pp, term2ppMap);
        }

    }

    public class TreeNodeComparator implements Comparator<TreeNode>
    {

        public int compare (TreeNode leftObj, TreeNode rightObj)
        {
            int left = leftObj.getAnnotation().getBegin();
            int right = rightObj.getAnnotation().getBegin();
            if (left < right)
            {
                return -1;
            }
            else if (left > right)
            {
                return 1;
            }
            return 0;
        }

    }

    private class TreeNode
    {
        private Annotation annotation;
        private Collection<TreeNode> children;
        private Set<String> features;


        public TreeNode (Annotation annotation)
        {
            super();
            this.annotation = annotation;
            features = new HashSet<String>();
        }

        public Annotation getAnnotation ()
        {
            return annotation;
        }

        public void setAnnotation (Annotation annotation)
        {
            this.annotation = annotation;
        }

        public Collection<TreeNode> getChildren ()
        {
            return children;
        }

        public void setChildren (Collection<TreeNode> children)
        {
            this.children = children;
        }

        public Set<String> getFeatures ()
        {
            return features;
        }

        public void setFeatures (Set<String> features)
        {
            this.features = features;
        }

        public String toString ()
        {
            if ((getChildren() == null) || (getChildren().isEmpty()))
            {
                return getAnnotation().getCoveredText();
            }
            else
            {
                StringBuffer result = new StringBuffer ("{ ");
                Iterator<TreeNode> iter = getChildren().iterator();
                while (iter.hasNext())
                {
                    TreeNode child = iter.next();
                    result.append(child.toString());
                    result.append(" ");
                }
                return result.append("}").toString();
            }
        }

        public void setFeature (String feature)
        {
            features.add(feature);
        }

        public boolean hasFeature (String feature)
        {
            return features.contains(feature);
        }
        
    }


    private JCas jcas = null;
    
    private static final String PARAM_COREF_SITE_TYPE = "CorefererringSitesAnnotationType";
    private static final String PARAM_COREF_SITE_FEATURE = "CorefererringSitesAnnotationFeature";
    private String corefererringSitesName;
    private Type corefererringSitesType;
    private List<CorefAnnotation> corefSites;
    
    private static final String PARAM_COREF_DIAGNOSES_TYPE = "CorefererringDiagnosesAnnotationType";
    private static final String PARAM_COREF_DIAGNOSES_FEATURE = "CorefererringDiagnosesAnnotationFeature";
    private static final String METASTATIC_SUFFIX = "/6";
    private static final String BENIGN_SUFFIX = "/0";
    private static final String BENIGN_DIAGNOSIS_KEY = "Benign";
    private static final String METASTATIC_DIAGNOSIS_KEY = "Metastatic";
    private static final String LYMPH_DIAGNOSIS_KEY = "Lymph";
    //private static final String OTHER_DIAGNOSIS_KEY = "Other";
    private static final String PRIMARY_DIAGNOSIS_KEY = "Primary";
    private static final String PARAM_EXCLUDING_PREPS = "ExcludingPrepositions";
    
    private String corefererringDiagnosesName;
    private Type corefererringDiagnosesType;
    private List<CorefAnnotation> corefDiagnoses;

    //private static final String PARAM_SENTENCE_TYPE = "SentenceAnnotationType";
    private String sentenceAnnotationName = "uima.tt.SentenceAnnotation";

    //private static final String PARAM_NPCOMBINED_TYPE = "NPCombinedAnnotationType";
    private String NPCombinedAnnotationName = "org.ohnlp.medkat.taes.npMerger.NPCombinedAnnotation";
    //private int npCombinedAnnotationTypeID;
    //private Map npCombinedToSentenceMap = new HashMap ();

    //private static final String PARAM_NP_TYPE = "NPAnnotationType";
    private String NPAnnotationName = "uima.tt.NPAnnotation";
    //private int npAnnotationTypeID;
    //private Map npToNPListMap = new HashMap ();

    //private static final String PARAM_NP_LIST_TYPE = "NPListAnnotationType";
    private String NPListAnnotationName = "uima.tt.NPListAnnotation";
    //private int npListAnnotationTypeID;
    //private Map npListToNPCombinedeMap = new HashMap ();

    //private static final String PARAM_PP_TYPE = "PPAnnotationType";
    private String PPAnnotationName = "uima.tt.PPAnnotation";
    //private int ppAnnotationTypeID;
    //private Map ppToSentenceMap = new HashMap ();

    //private static final String PARAM_TOKEN_TYPE = "TokenAnnotationType";
    private String tokenAnnotationName = "uima.tt.TokenAnnotation";
    //private int tokenAnnotationTypeID;

    //private static final String PARAM_DICTTERM_TYPE = "DictTermAnnotationType";
    //private String dictTermAnnotationName = "org.ohnlp.medkat.taes.conceptMapper.DictTerm";
    //private int dictTermAnnotationTypeID;

    private Set<String> exclusionPreps;
    
    
    //private AnnotationIndex sentenceAnnotationIndex;
    //private Type sentenceType;
    //private AnnotationIndex npAnnotationIndex;
    //private Type npAnnotationType;
    //private AnnotationIndex npListAnnotationIndex;
    //private Type npListAnnotationType;
    //private AnnotationIndex npCombinedAnnotationIndex;
    //private Type npCombinedAnnotationType;
    //private AnnotationIndex ppAnnotationIndex;
    //private Type ppAnnotationType;
    //private AnnotationIndex tokenAnnotationIndex;
    private Type tokenAnnotationType;
    private Feature tokenTypeFeature;
    private AnnotationIndex corefSitesIndex;
    private AnnotationIndex corefDiagnosesIndex;
    private String corefererringDiagnosesFeatureName;
    private String corefererringSitesFeatureName;
    private Feature corefererringSitesFeature;
    private Feature corefererringDiagnosesFeature;
    
    private String benignSuffix = BENIGN_SUFFIX;
    private String metastaticSuffix = METASTATIC_SUFFIX;
    //private Type sizeAnnotationType;
    private String sizeAnnotationName;

    private Type dictTermAnnotationType;
    //private AnnotationIndex sizeAnnotationIndex;
    private AnnotationIndex dictTermAnnotationIndex;

    private Collection<Annotation> grossDescriptionTerms;
    //private Map<FeatureStructure, Annotation> diagnosisToCoref;
    private Map<FeatureStructure, Annotation> sitesToCoref = null;
    private Map<Annotation, Annotation> terms2sentenceMap = null;
    private Map<Annotation, Annotation> term2npMap = null;
    private Map<Annotation, Annotation> term2npListMap = null;
    private Map<Annotation, Annotation> term2npCombinedMap = null;
    private Map<Annotation, Annotation> np2npCombinedMap = null;
    private Map<Annotation, Annotation> npList2npCombinedMap = null;
    private Map<Annotation, Annotation> term2ppMap = null;
    private Map<Annotation, Annotation> npCombined2sentenceMap = null;
    private Map<Annotation, ArrayList<Annotation>> pp2tokenMap = null;
    private Class<? extends Annotation> sentenceClass = null;
    //private Class corefererringSitesClass = null;
    //private Class corefererringDiagnosesClass = null;
    private Class<? extends Annotation> tokenAnnotationClass = null;
    private Class<? extends Annotation> ppAnnotationClass = null;
    private Class<? extends Annotation> npAnnotationClass = null;
    private Class<? extends Annotation> npListAnnotationClass = null;
    private Class<? extends Annotation> npCombinedAnnotationClass = null;
    private Class<? extends Annotation> dictTermAnnotationClass = null;
    private Class<? extends Annotation> originTermAnnotationClass = null;
    private Class<? extends Annotation> sizeAnnotationClass = null;

    



    /*protected int getTypeIndexID (String typeName) throws IllegalAccessException, NoSuchFieldException, ClassNotFoundException
    {
        return Class.forName(typeName).getField("typeIndexID").getInt(null);
    }*/

    
    public void initialize (AnnotatorContext annotatorContext) throws AnnotatorInitializationException, AnnotatorConfigurationException
    {
        super.initialize(annotatorContext);
        try
        {
            corefererringSitesName = (String) getContext ().getConfigParameterValue (PARAM_COREF_SITE_TYPE);
            corefererringSitesFeatureName = (String) getContext ().getConfigParameterValue (PARAM_COREF_SITE_FEATURE);
            corefererringDiagnosesName = (String) getContext ().getConfigParameterValue (PARAM_COREF_DIAGNOSES_TYPE);
            corefererringDiagnosesFeatureName = (String) getContext ().getConfigParameterValue (PARAM_COREF_DIAGNOSES_FEATURE);
            sizeAnnotationName = "org.ohnlp.medkat.taes.disambiguator.SizeDimensionAnnotation";
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

        corefererringSitesType = typeSystem.getType (corefererringSitesName);
        corefererringSitesFeature = corefererringSitesType.getFeatureByBaseName(corefererringSitesFeatureName);
        
        corefererringDiagnosesType = typeSystem.getType (corefererringDiagnosesName);
        corefererringDiagnosesFeature = corefererringDiagnosesType.getFeatureByBaseName(corefererringDiagnosesFeatureName);
        System.err.println ("****corefererringDiagnosesFeatureName: " + corefererringDiagnosesFeatureName + ", feature: " + corefererringDiagnosesFeature);

        
        tokenAnnotationType = typeSystem.getType (tokenAnnotationName);
        //ppAnnotationType = typeSystem.getType (PPAnnotationName);
        //npAnnotationType = typeSystem.getType (NPAnnotationName);
        //npListAnnotationType = typeSystem.getType (NPListAnnotationName);
        //npCombinedAnnotationType = typeSystem.getType (NPCombinedAnnotationName);
        //sentenceType = typeSystem.getType (sentenceAnnotationName);
        try {
            sentenceClass = UIMAAnnotationUtils.forName(sentenceAnnotationName);
            //corefererringSitesClass = Class.forName(corefererringSitesName);
            //corefererringDiagnosesClass = Class.forName(corefererringDiagnosesName);
            tokenAnnotationClass = UIMAAnnotationUtils.forName(tokenAnnotationName);
            ppAnnotationClass = UIMAAnnotationUtils.forName(PPAnnotationName);
            npAnnotationClass = UIMAAnnotationUtils.forName(NPAnnotationName);
            npListAnnotationClass = UIMAAnnotationUtils.forName(NPListAnnotationName);
            npCombinedAnnotationClass = UIMAAnnotationUtils.forName(NPCombinedAnnotationName);
            dictTermAnnotationClass = UIMAAnnotationUtils.forName(DictTerm.class.getName());
            originTermAnnotationClass = UIMAAnnotationUtils.forName(OriginTerm.class.getName());
            sizeAnnotationClass = UIMAAnnotationUtils.forName(sizeAnnotationName);
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new AnnotatorInitializationException ();
        }

        //sizeAnnotationType = typeSystem.getType (sizeAnnotationName);
        dictTermAnnotationType = typeSystem.getType (DictTerm.class.getName());

    }

    


    protected void initializeProcess (CAS tcas, JCas jCas, JFSIndexRepository jcasIndexes)
    {
        super.initializeProcess(tcas, jCas, jcasIndexes);
        
        jcas = jCas;

        corefSitesIndex = (AnnotationIndex)tcas.getAnnotationIndex (corefererringSitesType);
        corefDiagnosesIndex = (AnnotationIndex)tcas.getAnnotationIndex (corefererringDiagnosesType);
        //sentenceAnnotationIndex = (AnnotationIndex)tcas.getAnnotationIndex (sentenceType);
        //npAnnotationIndex = (AnnotationIndex) tcas.getAnnotationIndex(npAnnotationType);
        //npListAnnotationIndex = (AnnotationIndex) tcas.getAnnotationIndex(npListAnnotationType);
        //npCombinedAnnotationIndex = (AnnotationIndex) tcas.getAnnotationIndex(npCombinedAnnotationType);
        //ppAnnotationIndex = (AnnotationIndex) tcas.getAnnotationIndex(ppAnnotationType);
        //tokenAnnotationIndex = (AnnotationIndex) tcas.getAnnotationIndex(tokenAnnotationType);
        //sizeAnnotationIndex = (AnnotationIndex) tcas.getAnnotationIndex(sizeAnnotationType);
        dictTermAnnotationIndex = (AnnotationIndex) tcas.getAnnotationIndex(dictTermAnnotationType);
        
        System.err.println ("===> PROCESSING DOCUMENT: '" + DocumentURI.get (jcas) + "'");

        //TODO: this needs to be a param--just works with JFrost now
        tokenTypeFeature = tokenAnnotationType.getFeatureByBaseName("frost_TokenType");
        if (tokenTypeFeature == null)
        {
            System.err.println ("!!!!!! tokenTypeFeature == null");
        }
        sitesToCoref = null;
        term2npMap = null;
        term2npListMap = null;
        term2npCombinedMap = null;
        np2npCombinedMap = null;
        npList2npCombinedMap = null;
        term2ppMap = null;
        npCombined2sentenceMap = null;
        pp2tokenMap = null;
        

        terms2sentenceMap = new TreeMap<Annotation, Annotation>(new UIMAAnnotationOffsetComparator ());
        FSIterator dtIter = dictTermAnnotationIndex.iterator();
        while (dtIter.hasNext())
        {
            DictTerm term = (DictTerm)dtIter.next();
            terms2sentenceMap.put(term, term.getEnclosingSpan());
        }


}




    protected void processSubSection (CAS tcas,
                                      JCas jcas,
                                      SubHeading subsection,
                                      int begin,
                                      int end)
    {
        //corefSites = getSubAnnotations(subsection, corefSitesIndex);
        corefSites = getCorefAnnotations(subsection.getSubSectionNumber(), corefSitesIndex);
        //corefSites = getSubAnnotations(jcas, corefererringSitesClass, subsection);
        //corefDiagnoses = getSubAnnotations(subsection, corefDiagnosesIndex);
        corefDiagnoses = getCorefAnnotations(subsection.getSubSectionNumber(), corefDiagnosesIndex);
        //corefDiagnoses = getSubAnnotations(jcas, corefererringDiagnosesClass, subsection);
        
        /**/
        System.err.println ("PROCESSING SUBSECTION: " + subsection.getSubSectionNumber());
        System.err.println ("COREF SITES:");
        Iterator<CorefAnnotation> siteIter = corefSites.iterator();
        while (siteIter.hasNext())
        {
            Annotation corefSiteAnnot = siteIter.next();
            FSArray sites = (FSArray) corefSiteAnnot.getFeatureValue(corefererringSitesFeature);
            for (int i = 0; i < sites.size(); i ++)
            {
                DictTerm term = (DictTerm) sites.get(i);
                System.err.println ("\t" + term.getDictCanon() + ", begin: " + term.getBegin() + ", end: " + term.getEnd());                
            }
        }
        /**/
        
        
        sitesToCoref = mapSitesToCorefs (corefSites);
        //diagnosisToCoref = mapDiagnosesToCorefs (corefDiagnoses);
        
        grossDescriptionTerms = new ArrayList<Annotation> ();
        mapSubsectionAnnotations (subsection);

        ObjectMaps objectMaps = mapSubsectionObjects (subsection);
        //processObjects (jcas, corefSites, corefDiagnoses, objectMaps);
        /**/processCorefs (jcas, corefSites, corefDiagnoses, objectMaps);
    }


    private void processCorefs (JCas jcas, List<CorefAnnotation> corefSites, List<CorefAnnotation> corefDiagnoses, ObjectMaps objectMaps)
    {
        printRuleName("processCorefs");
        setObjectMapFeatures (objectMaps);

        Set<FeatureStructure> sitesRemaining = new HashSet<FeatureStructure> (sitesToCoref.keySet()); // all sites
        sitesRemaining = removeMarkedSites (sitesRemaining);

        Map<?, ?> [] spanMaps  = { term2npMap, term2npListMap, term2npCombinedMap, terms2sentenceMap };
        //Map [] spanMaps  = { terms2sentenceMap };
        String [] spanMapNames  = { "term2npMap", "term2npListMap", "term2npCombinedMap", "terms2sentenceMap" };
        //String [] spanMapNames  = { "terms2sentenceMap" };
        Map<DictTerm, Collection<DictTerm>> results = new HashMap<DictTerm, Collection<DictTerm>> ();
        int spanMapIndex = 0;
        
        do 
        {
            /**/System.err.println ("spanMapName: " + spanMapNames[spanMapIndex]);
            sitesRemaining = processAllSpans(corefDiagnoses, objectMaps, spanMaps[spanMapIndex], sitesRemaining, results);

            spanMapIndex += 1;
        }
        while ((spanMapIndex < spanMaps.length) && (sitesRemaining.size() > 0)); //(sitesRemainingLength != sitesRemaining.size()));
        
        //print them out
        Set<DictTerm> diags = results.keySet();
        Iterator<DictTerm> diagIter = diags.iterator();
        System.err.println ("======= RESULTS =======");
        while (diagIter.hasNext())
        {
            DictTerm diag = diagIter.next();
            System.err.println ("Diagnosis: " + diag.getCoveredText() + ": '" + diag.getDictCanon() + "'");
            Collection<DictTerm> sites = results.get(diag);
            Iterator<DictTerm> siteIter = sites.iterator();
            while (siteIter.hasNext())
            {
                DictTerm site = siteIter.next();
                System.err.println ("\tSite: " + site.getDictCanon());
            }
        }
        System.err.println ("======= +++++++ =======");
        makeResultingAnnotations (jcas, corefDiagnoses, results, categorizeDiagnosesNew (results.keySet()));

        
    }



    private Set<FeatureStructure> removeMarkedSites (Set<FeatureStructure> sitesRemaining)
    {
        Iterator<FeatureStructure> sIter = sitesRemaining.iterator();
        Collection<DictTerm> toRemove = new ArrayList<DictTerm> ();
        while (sIter.hasNext())
        {
            DictTerm site = (DictTerm) sIter.next();
            if (! isOK_Marker(site))
            {
                toRemove.add (site);
            }
        }
        sitesRemaining.removeAll (toRemove);
        return sitesRemaining;
    }



    private Set<FeatureStructure> processAllSpans (List<CorefAnnotation> corefDiagnoses, ObjectMaps objectMaps, Map<?, ?> spanMap, Set<FeatureStructure> sitesRemaining, Map<DictTerm, Collection<DictTerm>> results)
    {
        //System.err.println ("spanMap: " + spanMap);
        System.err.println ("spanMap size: " + ((spanMap == null) ? 0 : spanMap.size()));
        
        Iterator<CorefAnnotation> coreferringDiagnosesIter = corefDiagnoses.iterator();
        while (coreferringDiagnosesIter.hasNext())
        {
            Annotation diagnosisCorefs = coreferringDiagnosesIter.next();
            FSArray diagnosisCorefArray = (FSArray)diagnosisCorefs.getFeatureValue(corefererringDiagnosesFeature);
            
            for (int i = 0; i < diagnosisCorefArray.size(); i++)
            {
                DictTerm diagnosis = (DictTerm) diagnosisCorefArray.get(i);
                if (isOK_Marker(diagnosis) && (! results.containsKey(diagnosis)) && (spanMap != null))
                {
                    sitesRemaining = processSpan (sitesRemaining, diagnosis, (Annotation) spanMap.get(diagnosis), spanMap, objectMaps.getTerm2ppMap(), results);
                }
                
            }
        }
        return sitesRemaining;
    }

    private Set<FeatureStructure> processSpan (Set<FeatureStructure> sitesRemaining, DictTerm diagnosis, Annotation diagnosisSpan, Map<?, ?> site2SpanMap, Map<Annotation, Annotation> term2ppMap, Map<DictTerm, Collection<DictTerm>> results)
    {
        /**/System.err.println ("Diagnosis: " + diagnosis.getDictCanon());
        /**/System.err.println ("Diagnosis Span: " + diagnosisSpan);
        // now go through all sites to see how they relate to this diagnosis
        Iterator<FeatureStructure> siteIter = sitesRemaining.iterator();
        Collection<DictTerm> toRemove = new ArrayList<DictTerm> ();
        
        //int numOtherSites = 0;
        
        while (siteIter.hasNext())
        {
            DictTerm site = (DictTerm) siteIter.next();

            /**/System.err.println ("Site: " + site.getDictCanon());
            /*if (isLymph (site))
            {
                //System.err.println ("SKIPPING LYMPH SITE: " + site.getAttributeValue());
                toRemove.add (site);
                //numLymphSites += 1;
            }
            else
            {*/
                Annotation siteSpan = (Annotation) site2SpanMap.get(site);

                //System.err.println ("Site Sentence: " + siteSentence);
                if ((siteSpan != null) && (siteSpan.equals(diagnosisSpan)))
                {
                    System.err.println ("Site span == Diagnosis span: " + siteSpan);
                    // the site is in the same span as the diagnosis!!
                    System.err.println ("checking PP, site: " + site.getCoveredText());
                    Annotation pp = term2ppMap.get (site);
                    if ((invasionSite (diagnosis, site, siteSpan)) || isLymph(site) || (! inclusivePP (pp)))
                    {
                        toRemove.add(site);
                    }
                    else //    if ((! invasionSite (diagnosis, site, siteSpan)) && ((pp == null) || (inclusivePP (pp))))
                    {
                        printRuleName("(! invasionSite (site)) && (pp == null) || (inclusivePP (pp), diagnosis: '" + diagnosis.getDictCanon() + "', begin: " + diagnosis.getBegin() + ", site: " + site.getDictCanon() + ", begin: " + site.getBegin());
                        Collection<DictTerm> sites = results.get(diagnosis);
                        if (sites == null)
                        {
                            sites = new ArrayList<DictTerm> ();
                        }
                        sites.add(site);
                        toRemove.add(site);
                        results.put(diagnosis, sites);
                    }
                }
            //}
        }
        sitesRemaining.removeAll(toRemove);
        return sitesRemaining;
    }


    private Map<FeatureStructure, Annotation> mapSitesToCorefs (List<CorefAnnotation> corefSites)
    {
        Map<FeatureStructure, Annotation> result = new HashMap<FeatureStructure, Annotation> ();
        
        Iterator<CorefAnnotation> corefSitesIter = corefSites.iterator();
        while (corefSitesIter.hasNext())
        {
            Annotation corefAnnotation = corefSitesIter.next();
            FSArray corefs = (FSArray) corefAnnotation.getFeatureValue(corefererringSitesFeature);
            int corefsSize = corefs.size();
            for (int i = 0; i < corefsSize; i++)
            {
                /**/DictTerm corefSite = (DictTerm) corefs.get(i);
                /**/System.err.println ("Mapping site: " + corefSite.getDictCanon() + "(" + corefSite.getBegin() + "," + corefSite.getEnd() + ")");
                result.put(corefs.get(i), corefAnnotation);
            }
        }
        return result;
    }

    /*
    private Map<FeatureStructure, Annotation> mapDiagnosesToCorefs (List<CorefAnnotation> corefDiagnoses)
    {
        Map<FeatureStructure, Annotation> result = new HashMap<FeatureStructure, Annotation> ();
        
        Iterator<CorefAnnotation> corefDiagnosesIter = corefDiagnoses.iterator();
        while (corefDiagnosesIter.hasNext())
        {
            Annotation corefAnnotation = corefDiagnosesIter.next();
            FSArray corefs = (FSArray)corefAnnotation.getFeatureValue(corefererringDiagnosesFeature);
            int corefsSize = corefs.size();
            for (int i = 0; i < corefsSize; i++)
            {
                result.put(corefs.get(i), corefAnnotation);
            }
        }
        return result;
    }*/



    public Map<Annotation, Annotation> addListToMap (Collection<Annotation> list, Annotation annot, Map<Annotation, Annotation> map)
    {
        if (map == null)
        {
            map = new TreeMap<Annotation, Annotation>(new UIMAAnnotationOffsetComparator ());
        }
        Iterator<Annotation> listIter = list.iterator();
        while (listIter.hasNext())
        {
            map.put(listIter.next(), annot);
        }
        return map;
    }

    /*
    private void addToTerms2SentenceMap (ArrayList<Annotation> terms, Annotation sentence)
    {
        terms2sentenceMap = addListToMap(terms, sentence, terms2sentenceMap);
    }
     */

    private void addTerms2npMap (ArrayList<Annotation> terms, Annotation np)
    {
        term2npMap = addListToMap(terms, np, term2npMap);
    }


    private void addTerms2npListMap (ArrayList<Annotation> terms, Annotation npList)
    {
        term2npListMap = addListToMap(terms, npList, term2npListMap);
    }

    
    private void addTerms2npCombinedMap (ArrayList<Annotation> terms, Annotation npCombined)
    {
        term2npCombinedMap = addListToMap(terms, npCombined, term2npCombinedMap);
    }

    private void addNPs2npCombinedMap (ArrayList<Annotation> nps, Annotation npc)
    {
        np2npCombinedMap = addListToMap(nps, npc, np2npCombinedMap);
    }



    private void addNPList2npCombinedMap (ArrayList<Annotation> npLists, Annotation npc)
    {
        npList2npCombinedMap= addListToMap(npLists, npc, npList2npCombinedMap);
    }



    private void addTerm2ppMap (ArrayList<Annotation> terms, Annotation pp)
    {
        term2ppMap = addListToMap(terms, pp, term2ppMap);
    }



    private void addNPCombined2sentenceMap (ArrayList<Annotation> npCombineds, Annotation sentence)
    {
        npCombined2sentenceMap = addListToMap(npCombineds, sentence, npCombined2sentenceMap);
    }






    private void setObjectMapFeatures (ObjectMaps objectMaps)
    {
        Iterator<TreeNode> iter = objectMaps.iterator();
        while (iter.hasNext())
        {
            TreeNode node = iter.next();
            Annotation sentence = node.getAnnotation();
            Collection<Annotation> sizes = findSizes (sentence);
            Collection<DictTerm> metastatic = findMetastatic (sentence);
            Collection<DictTerm> lymph = findLymph (sentence);
            objectMaps.addToFeatureMaps (sentence, sizes, metastatic, lymph);
            
            if (!sizes.isEmpty())
            {
                node.setFeature(SIZE_FEATURE);
            }
            if (!metastatic.isEmpty())
            {
                node.setFeature(METASTATIC_FEATURE);
            }
            if (!lymph.isEmpty())
            {
                node.setFeature(LYMPH_FEATURE);
            }
        }

    }

    private ArrayList<Annotation> findSizes (Annotation sentence)
    {
        //return getSubAnnotations (sentence, sizeAnnotationIndex);
        return getSubAnnotations (jcas, sizeAnnotationClass, sentence);
    }


    protected boolean isOK_Marker (DictTerm dictTerm)
    {
        int dictTermMarksToIgnore = (DictTermMarkers.NEGATED_INDICATOR     |
                                     DictTermMarkers.IGNORED_INDICATOR     |
                                     DictTermMarkers.DUPLICATE_INDICATOR   |
                                     DictTermMarkers.SUBSUMED_INDICATOR    |
                                     DictTermMarkers.SUPERFLUOUS_INDICATOR |
                                     DictTermMarkers.MODIFIER_INDICATOR    |
                                     DictTermMarkers.CONTAINSDISALLOWEDTERM_INDICATOR);
        return (! DictTermMarkers.isAnyMarkedAs(dictTerm, dictTermMarksToIgnore));
    }


    private Collection<DictTerm> findDictTermsWithSemClass (Annotation span, String semClass)
    {
        //Collection terms = getSubAnnotations (sentence, dictTermAnnotationIndex);
        Collection<Annotation> terms = getSubAnnotations (jcas, dictTermAnnotationClass, span);
        Collection<DictTerm> results = new ArrayList<DictTerm> ();
        Iterator<Annotation> iter = terms.iterator();
        while (iter.hasNext())
        {
            DictTerm term = (DictTerm) iter.next();
            if ((isOK_Marker (term)) && term.getSemClass().equals(semClass))
            {
                results.add(term);
            }
        }
        return results;
    }


    private Collection<DictTerm> findMetastatic (Annotation span)
    {
        return findDictTermsWithSemClass (span, "Metastatic");
    }
    
    private Collection<Annotation> findOrigin (Annotation span)
    {
        Collection<Annotation> results = getSubAnnotations (jcas, originTermAnnotationClass, span);
        return results;
    }

    private Collection<DictTerm> findInvasion (Annotation span)
    {
        return findDictTermsWithSemClass (span, "Invasive");
    }


    private Collection<DictTerm> findLymph (Annotation span)
    {
        return findDictTermsWithSemClass (span, "Lymph");
    }




    private static void printRuleName (String ruleName)
    {
        System.err.println ("** Applying rule: " + ruleName);
    }

    /*
    private void processObjects (JCas jcas, List<Annotation> corefSites, List<CorefAnnotation> corefDiagnoses, ObjectMaps objectMaps)
    {
        //tokenLookupAndPrint(corefDiagnoses, corefererringDiagnosesFeature, objectMaps);
        //tokenLookupAndPrint(corefSites, corefererringSitesFeature, objectMaps);

        setObjectMapFeatures (objectMaps);
        
        Map<DictTerm, Object> categorizedDiagnoses = categorizeDiagnoses (corefDiagnoses, corefererringDiagnosesFeature, objectMaps);
        
        printCategorizedDiagnoses(categorizedDiagnoses);
        if (sitesOnlyInGrossDescription (corefSites, objectMaps))
        {
            printRuleName("sitesOnlyInGrossDescription");
            
            Iterator<CorefAnnotation> coreferringDiagnosesIter = corefDiagnoses.iterator();
            while (coreferringDiagnosesIter.hasNext())
            {
                Annotation corefs = coreferringDiagnosesIter.next();
                FSArray diagCorefArray = (FSArray) corefs.getFeatureValue(corefererringDiagnosesFeature);
                int diagnosesLength = diagCorefArray.size();
                
                int lymphCount = 0;
                int metastaticCount = 0;
                int primaryCount = 0;
                
                for (int i = 0; i < diagnosesLength; i++)
                {
                    //Object type = categorizedDiagnoses.get(diagCorefArray.get(i));
                    Object type = categorize1Diagnosis ((DictTerm)diagCorefArray.get(i));
                    System.err.println ("coref[" + i + "]: " + diagCorefArray.get(i) + ", type: " + type);
                    if (type != null)
                    {
                        if (type.equals(LYMPH_DIAGNOSIS_KEY))
                        {
                            lymphCount += 1;
                        }
                        else if (type.equals(METASTATIC_DIAGNOSIS_KEY))
                        {
                            metastaticCount += 1;
                        }
                        else if (type.equals(PRIMARY_DIAGNOSIS_KEY))
                        {
                            primaryCount += 1;
                        }
                    }
                } 
                
                // make a lymph diagnosis, if necessary, and wither a metasatic or primary
                /*if (lymphCount > 0)
                {
                    createLymphDiagnosis(jcas, corefs, corefSites);
                }*//*
                if (metastaticCount > 0)
                {
                    createMetastaticDiagnosis(jcas, corefs, corefSites);
                }                        
                else if (primaryCount > 0)
                {
                    createPrimaryDiagnosis(jcas, corefs, corefSites);
                }                        

            }
        }
        else 
        {
            processDiagnosesAndSites (jcas, categorizedDiagnoses, corefDiagnoses, corefSites, objectMaps);
        }
    }
    */

    /*
    // assumption: coreferring diagnoses are ordered such that non-generics are listed 
    private void processDiagnosesAndSites (JCas jcas, Map<DictTerm, Object> categorizedDiagnoses, List<CorefAnnotation> corefDiagnoses, List<Annotation> corefSites, ObjectMaps objectMaps)
    {
        
        // go through each diagnosis of each corefDiagnoses annot
        // progresively check each broader NP category for site from each 
        printRuleName("processDiagnosesAndSites");
        
        Set<FeatureStructure> sitesRemaining = sitesToCoref.keySet(); // all sites
        Map<DictTerm, Collection<DictTerm>> results = new HashMap<DictTerm, Collection<DictTerm>> ();
        
        Iterator<CorefAnnotation> coreferringDiagnosesIter = corefDiagnoses.iterator();
        while (coreferringDiagnosesIter.hasNext())
        {
            Annotation corefs = coreferringDiagnosesIter.next();
            FSArray corefArray = (FSArray)corefs.getFeatureValue(corefererringDiagnosesFeature);
            int numSites = 0;
            int numLymphSites = 0;
            
            for (int i = 0; i < corefArray.size(); i++)
            {
                DictTerm diagnosis = (DictTerm) corefArray.get(i);
                if (isOK_Marker(diagnosis))
                {
                    Object diagnosisSentence = terms2sentenceMap.get(diagnosis);
    
                    //System.err.println ("Diagnosis: " + diagnosis.getDictCanon());
                    //System.err.println ("Diagnosis Sentence: " + diagnosisSentence);
                    //Object diagnosisNP = objectMaps.getTerm2npMap().get(diagnosis);
                    // now go through all sites to see how they relate to this diagnosis
                    Iterator<FeatureStructure> siteIter = sitesRemaining.iterator();
                    //int numOtherSites = 0;
                    
                    while (siteIter.hasNext())
                    {
                        DictTerm site = (DictTerm) siteIter.next();

                        numSites += 1;

                        //TODO: this is a lymph entry--generalize this
                        if (isLymph (site))
                        {
                            System.err.println ("SKIPPING LYMPH SITE: " + site.getAttributeValue());
                            numLymphSites += 1;
                        }
                        else
                        {
                            //System.err.println ("Site: " + site.getDictCanon());
                            Annotation siteSentence = terms2sentenceMap.get(site);

                            //System.err.println ("Site Sentence: " + siteSentence);
                            if ((siteSentence != null) && (siteSentence.equals(diagnosisSentence)))
                            {
                                System.err.println ("Site Sentence == Diagnosis Sentence");
                                // the site is in the same sentence as the diagnosis!!
                                /*if (inSameNP (site, diagnosis))
                                {
                                    
                                }
                                else if (inSameNPList(site, diagnosis))
                                {
                                    
                                }
                                else if (inSameNPCombined (site, diagnosis))
                                {
                                    System.err.println ("inSameNPCombined, checking PP");*//*
                                System.err.println ("checking PP, site: " + site.getCoveredText());
                                Annotation pp = objectMaps.getTerm2ppMap().get (site);
                                if ((! invasionSite (diagnosis, site, siteSentence)) && (inclusivePP (pp)))
                                {
                                    printRuleName("(! invasionSite (site)) && (pp == null) || (inclusivePP (pp), diagnosis: '" + diagnosis.getDictCanon() + "', begin: " + diagnosis.getBegin() + ", site: " + site.getDictCanon() + ", begin: " + site.getBegin());
                                    Collection<DictTerm> sites = results.get(diagnosis);
                                    if (sites == null)
                                    {
                                        sites = new ArrayList<DictTerm> ();
                                    }
                                    sites.add(site);
                                    //numOtherSites +=1 ;
                                    results.put(diagnosis, sites);
                                }
                            }
                        }
                    }
                    /*if ((numLymphSites > 0) && (numOtherSites == 0))
                    {
                        results.remove(diagnosis);
                        numCorefsRemoved += 1;
                        System.err.println("SHOULD REMOVE DIAGNOSIS: " + diagnosis.getAttributeValue() + ", numCorefsRemoved: " + numCorefsRemoved);
                    }*//*
                }
                
                // remove sites already found
                sitesRemaining.removeAll(results.entrySet());
            }
            System.err.println("numLymphSites: " + numLymphSites + ", corefArray.size(): " + corefArray.size());
            if (numLymphSites == numSites)
            {
                for (int j = 0; j < corefArray.size(); j++)
                {
                    DictTerm toRemove = (DictTerm) corefArray.get(j);
                    System.err.println("REMOVING DIAGNOSIS: " + toRemove.getAttributeValue());
                    //categorizedDiagnoses.remove(toRemove);
                }
            }

        }
        

        
        
        //print them out
        Set<DictTerm> diags = results.keySet();
        Iterator<DictTerm> diagIter = diags.iterator();
        System.err.println ("======= RESULTS =======");
        while (diagIter.hasNext())
        {
            DictTerm diag = diagIter.next();
            System.err.println ("Diagnosis: " + diag.getCoveredText() + ": '" + diag.getDictCanon() + "'");
            Collection<DictTerm> sites = results.get(diag);
            Iterator<DictTerm> siteIter = sites.iterator();
            while (siteIter.hasNext())
            {
                DictTerm site = siteIter.next();
                System.err.println ("\tSite: " + site.getDictCanon());
            }
        }
        System.err.println ("======= +++++++ =======");
        makeResultingAnnotations (jcas, corefDiagnoses, results, categorizedDiagnoses);
    }*/
    
    private boolean isLymph (DictTerm site)
    {
        //TODO: this is a lymph entry--generalize this
        return site.getAttributeValue().matches("^[Cc]77\\..*");
    }



    private boolean invasionSite (DictTerm diagnosis, DictTerm site, Annotation span)
    {
        //Annotation sentence = (Annotation)terms2sentenceMap.get(site);
        Collection<DictTerm> invasions = findInvasion (span);
        Iterator<DictTerm> invIter = invasions.iterator();
        while (invIter.hasNext())
        {
            Annotation invasion = invIter.next();
            if ((diagnosis.getBegin() < invasion.getBegin()) && (site.getBegin() > invasion.getBegin()))
            {
                System.err.println("****>> invasion site: " + site.getCoveredText());
                return true;
            }
        }
        return false;
    }



    private void makeResultingAnnotations (JCas jcas, List<CorefAnnotation> corefDiagnoses, Map<DictTerm, Collection<DictTerm>> results, Map<DictTerm, Object> categorizedDiagnoses)
    {
        Set<Annotation> primariesDone = new HashSet<Annotation> ();
        Set<Annotation> metastaticsDone = new HashSet<Annotation> ();
        //Set lymphsDone = new HashSet ();
        Set<Annotation> othersDone = new HashSet<Annotation> ();

        Iterator<CorefAnnotation> coreferringDiagnosesIter = corefDiagnoses.iterator();
        while (coreferringDiagnosesIter.hasNext())
        {
            Annotation diagCoref = coreferringDiagnosesIter.next();
            FSArray corefsArray = (FSArray) diagCoref.getFeatureValue(corefererringDiagnosesFeature);

            // collect all sites for all diagnoses in this coref
            Collection<DictTerm> sitesForCorefDiagnosis = new ArrayList<DictTerm> ();
            for (int i = 0; i < corefsArray.size(); i++)
            {
                DictTerm diagnosis = (DictTerm) corefsArray.get(i);
                Collection<DictTerm> sites = results.get (diagnosis);
                if (sites != null)
                {
                    sitesForCorefDiagnosis.addAll(sites);
                }
            }
                
            /**/CoreferringDiagnoses cd = (CoreferringDiagnoses) diagCoref;
            /**/for (int i = 0; i < cd.getElements().size(); i++)
            /**/{
            /**/    System.err.println ("coref[" + i + "]: " + ((Annotation)cd.getElements().get(i)).getCoveredText());
            /**/}

            for (int coref = 0; coref < corefsArray.size(); coref++)
            {
                DictTerm diagnosis = (DictTerm) corefsArray.get(coref);
                /**/System.err.println ("makeResultingAnnotations, diagnosis: " + diagnosis.getCoveredText() + ": '" + diagnosis.getDictCanon() + "'");
            
            
                DiagnosisBase newDiagnosisAnnotation = null;
            
                Object type = categorizedDiagnoses.get(diagnosis);
                if ((type == null) && isOK_Marker(diagnosis))
                {
                    type = categorize1Diagnosis (diagnosis);
                    /*if (isMetastaticCode(diagnosis.getAttributeValue()))
                    {
                        type = METASTATIC_DIAGNOSIS_KEY;
                    }
                    else if (isBenignCode(diagnosis.getAttributeValue()))
                    {
                        type = BENIGN_DIAGNOSIS_KEY;
                    }
                    else
                    {
                        type = PRIMARY_DIAGNOSIS_KEY;//continue;
                    }*/
                }
                if (type != null)
                {
                    if (type.equals(LYMPH_DIAGNOSIS_KEY))
                    {
                        /*if (! lymphsDone.contains(diagCoref))
                        {
                            newDiagnosisAnnotation = new LymphDiagnosis (jcas);
                            lymphsDone.add(diagCoref);
                            System.err.println ("Creating new LymphDiagnosis");
                        }*/
                    }
                    else if (type.equals(METASTATIC_DIAGNOSIS_KEY))
                    {
                        if (! metastaticsDone.contains(diagCoref))
                        {
                            newDiagnosisAnnotation = new MetastaticDiagnosis (jcas);
                            metastaticsDone.add(diagCoref);
                            System.err.println ("Creating new MetastaticDiagnosis");
                        }
                    }
                    else if (type.equals(PRIMARY_DIAGNOSIS_KEY))
                    {
                        if (! primariesDone.contains(diagCoref))
                        {
                            newDiagnosisAnnotation = new PrimaryDiagnosis (jcas);
                            primariesDone.add(diagCoref);
                            System.err.println ("Creating new PrimaryDiagnosis");
                        }
                    }
                    else
                    {
                        if (! othersDone.contains(diagCoref))
                        {
                            newDiagnosisAnnotation = new OtherDiagnosis (jcas);
                            othersDone.add(diagCoref);
                            System.err.println ("Creating new OtherDiagnosis");
                        }
                    }
                }
                
                if (newDiagnosisAnnotation != null)
                {
                    newDiagnosisAnnotation.setTerm(diagCoref);
                    newDiagnosisAnnotation.setBegin(diagCoref.getBegin());
                    newDiagnosisAnnotation.setEnd(diagCoref.getEnd());
                    
                    Set<Annotation> corefSites = new HashSet<Annotation> ();
                    System.err.println ("sitesForCorefDiagnosis.size = " + sitesForCorefDiagnosis.size());
                    
                    if (sitesForCorefDiagnosis.size() == 0)
                    {
                        sitesForCorefDiagnosis = findGrossDescriptionSites ();
                        System.err.println ("Using Gross Description Sites, sitesForCorefDiagnosis.size = " + sitesForCorefDiagnosis.size());
                    }
                    
                    Iterator<DictTerm> siteIter = sitesForCorefDiagnosis.iterator();
                    while (siteIter.hasNext())
                    {
                        DictTerm site = siteIter.next();
                        if (isLymph(site))
                        {
                            // do nothing
                        }
                        else 
                        {
                            System.err.println ("attempting to add site: " + site.getCoveredText() + " to new diagnosis");
                            Annotation siteCoref = sitesToCoref.get(site);
                            if (siteCoref == null)
                            {
                                System.err.println ("adding site failed: siteCoref == null");
                            }
                            else if (! corefSites.contains(siteCoref))
                            {
                                System.err.println ("adding site: " + site.getCoveredText() + " to new diagnosis");
                                corefSites.add(siteCoref);
                            }
                        }
                    }
                    FSArray siteCorefsArray = new FSArray (jcas, corefSites.size());
                    Iterator<Annotation> it = corefSites.iterator();
                    int i = 0;
                    while (it.hasNext())
                    {
                        siteCorefsArray.set(i, it.next());
                        i += 1;
                    }
                    newDiagnosisAnnotation.setSites(siteCorefsArray);
                    newDiagnosisAnnotation.addToIndexes();
                }
            }
        }
    }



    private Collection<DictTerm> findGrossDescriptionSites ()
    {
        Collection<DictTerm> result = new ArrayList<DictTerm> ();
        
        Iterator<Annotation> gdIter = grossDescriptionTerms.iterator();
        while (gdIter.hasNext())
        {
            DictTerm term = (DictTerm) gdIter.next();
            if (term.getSemClass().equals("Site"))
            {
                result.add(term);
            }
        }
        return result;
    }



    private boolean inclusivePP (Annotation pp)
    {
        System.err.println("inclusivePP?");
        if (pp != null)
        {
            List<Annotation> tokens = pp2tokenMap.get(pp);
            
            if (tokens != null)
            {
                String token = tokens.get(0).getCoveredText().toLowerCase();
                System.err.println("\tpreposition: '" + token + "'");
            
                if (exclusionPreps.contains(token))
                {
                    System.err.println("\t==> EXCLUSION");
                    return false;
                }
            }
        }
        System.err.println("\t==> INCLUSION");
        return true;
    }

    
    /*
    private boolean inSameLocation (Map map, DictTerm site, DictTerm diagnosis)
    {
        Object siteLocation = map.get(site);
        Object diagLocation = map.get(diagnosis);
        return (siteLocation != null) && (siteLocation.equals(diagLocation));
    }*/

    /*
    private boolean inSameNP (DictTerm site, DictTerm diagnosis)
    {
        return inSameLocation (term2npMap, site, diagnosis);
    }

    private boolean inSameNPList (DictTerm site, DictTerm diagnosis)
    {
        return inSameLocation (term2npListMap, site, diagnosis);
    }

    private boolean inSameNPCombined (DictTerm site, DictTerm diagnosis)
    {
        return inSameLocation (term2npCombinedMap, site, diagnosis);
    }

    private boolean inSamePP (DictTerm site, DictTerm diagnosis)
    {
        return inSameLocation (term2ppMap, site, diagnosis);
    }
    */


    /*private void processCategorizedDiagnoses (Collection diagnoses)
    {
        Iterator diagnosesIter = diagnoses.iterator();
        while (diagnosesIter.hasNext())
        {
            Annotation corefAnnot = (Annotation) diagnosesIter.next();
            FSArray diagnosesCorefs = (FSArray) corefAnnot.getFeatureValue(corefererringDiagnosesFeature);
            for (int item = 0; item < diagnosesCorefs.size(); item++)
            {
                DictTerm dictTerm = (DictTerm) diagnosesCorefs.get(item);
                System.err.println ("\t" + dictTerm.getAttributeValue() + ", '" + dictTerm.getCoveredText() + "'");
              
            }
        }
    }*/





    /*private void createMetastaticDiagnoses (JCas jcas, Collection diagnoses, List corefSites)
    {
        Iterator iter = diagnoses.iterator();
        while (iter.hasNext())
        {
            createMetastaticDiagnosis(jcas, (Annotation)iter.next(), corefSites);
        }
    }*/
    /*
    private MetastaticDiagnosis createMetastaticDiagnosis (JCas jcas, Annotation corefDiagnoses, List<Annotation> corefSites)
    {
        MetastaticDiagnosis diagnosis = new MetastaticDiagnosis (jcas);
        FSArray sites = setDiagnosis (jcas, diagnosis, corefDiagnoses, corefSites);
        diagnosis.setSites(sites);
        diagnosis.setTerm(corefDiagnoses);
        diagnosis.addToIndexes();
        return diagnosis;
    }*/




    /*private void createPrimaryDiagnoses (JCas jcas, Collection diagnoses, List corefSites)
    {
        Iterator iter = diagnoses.iterator();
        while (iter.hasNext())
        {
            createPrimaryDiagnosis(jcas, (Annotation)iter.next(), corefSites);
        }
    }*/
    /*
    private PrimaryDiagnosis createPrimaryDiagnosis (JCas jcas, Annotation corefDiagnoses, List<Annotation> corefSites)
    {
        PrimaryDiagnosis diagnosis = new PrimaryDiagnosis (jcas);
        FSArray sites = setDiagnosis (jcas, diagnosis, corefDiagnoses, corefSites);
        diagnosis.setSites(sites);
        diagnosis.setTerm(corefDiagnoses);
        diagnosis.addToIndexes();
        return diagnosis;
    }*/





    /*private void createLymphDiagnoses (JCas jcas, Collection diagnoses, List corefSites)
    {
        Iterator iter = diagnoses.iterator();
        while (iter.hasNext())
        {
            createLymphDiagnosis(jcas, (Annotation)iter.next(), corefSites);
        }
    }*/


    /*
    private LymphDiagnosis createLymphDiagnosis (JCas jcas, Annotation corefDiagnoses, List<Annotation> corefSites)
    {
        LymphDiagnosis diagnosis = new LymphDiagnosis (jcas);
        FSArray sites = setDiagnosis (jcas, diagnosis, corefDiagnoses, corefSites);
        diagnosis.setSites(sites);
        diagnosis.setTerm(corefDiagnoses);
        diagnosis.addToIndexes();
        return diagnosis;
    }
    */


    /*
    private FSArray setDiagnosis (JCas jcas, Annotation newDiagnosis, Annotation coref, List<Annotation> corefSites)
    {
        FSArray sites = new FSArray (jcas, corefSites.size());
        int begin = coref.getBegin();
        int end = coref.getEnd();
        for (int i = 0; i < corefSites.size(); i++)
        {
            Annotation site = corefSites.get(i);
            sites.set(i, site);
            begin = Math.min(begin, site.getBegin());
            end = Math.max(end, site.getEnd());
        }
        newDiagnosis.setBegin(begin);
        newDiagnosis.setEnd(end);
        return sites;
    }


    private void printCategorizedDiagnoses (Map<DictTerm, Object> categorizedDiagnoses)
    {
        Iterator<DictTerm> keyIter = categorizedDiagnoses.keySet().iterator();
        while (keyIter.hasNext())
        {
            DictTerm key = keyIter.next();
            //String diagnosesOfType = (String) categorizedDiagnoses.get(key);
            String diagnosisType = (String) categorizedDiagnoses.get(key);
            System.err.println ("DIAGNOSIS: " + key.getDictCanon() + ", type: " + diagnosisType);
            /*Iterator diagnosesIter = diagnosesOfType.iterator();
            while (diagnosesIter.hasNext())
            {
                Annotation corefAnnot = (Annotation) diagnosesIter.next();
                FSArray diagnoses = (FSArray) corefAnnot.getFeatureValue(corefererringDiagnosesFeature);
                for (int item = 0; item < diagnoses.size(); item++)
                {
                    DictTerm dictTerm = (DictTerm) diagnoses.get(item);
                    System.err.println ("\t" + dictTerm.getAttributeValue() + ", '" + dictTerm.getCoveredText() + "'");
                }
            }*//*
        }
    }

    */
    /*
    private Map<DictTerm, Object> categorizeDiagnoses (Collection<CorefAnnotation> corefDiagnoses, Feature corefererringDiagnosesFeature, ObjectMaps objectMaps)
    {
        Map<DictTerm, Object> results = new HashMap<DictTerm, Object> ();
        
        Iterator<CorefAnnotation> itemIter = corefDiagnoses.iterator();
        
        while (itemIter.hasNext())
        {
            Annotation corefAnnot = itemIter.next();
            FSArray diagnoses = (FSArray) corefAnnot.getFeatureValue(corefererringDiagnosesFeature);

            for (int item = 0; item < diagnoses.size(); item++)
            {
                DictTerm dictTerm = (DictTerm) diagnoses.get(item);
                System.err.println ("categorizeDiagnoses, term: " + dictTerm.getCoveredText());
                if (isMetastaticCode (dictTerm.getAttributeValue()))
                {
                    System.err.println (" categorizeDiagnoses, category: " + METASTATIC_DIAGNOSIS_KEY);
                    results.put(dictTerm, METASTATIC_DIAGNOSIS_KEY);
                }
                else if (isBenignCode (dictTerm.getAttributeValue()))
                {
                    System.err.println (" categorizeDiagnoses, category: " + BENIGN_DIAGNOSIS_KEY);
                    results.put(dictTerm, BENIGN_DIAGNOSIS_KEY);
                }
                else
                {
                    FSArray tokens = dictTerm.getMatchedTokens();
                    Annotation token = (Annotation) tokens.get(0);
                    Integer sentence = objectMaps.getToken2sentenceMap().get(token);
                    
                    // do not process if from another doc section (sentence==null)
                    if (sentence != null)
                    {
                        TreeNode sentenceNode = (TreeNode)objectMaps.get(sentence.intValue());
                        if (sentenceNode.hasFeature(LYMPH_FEATURE))
                        {
                            System.err.println (" categorizeDiagnoses, category: " + LYMPH_DIAGNOSIS_KEY);
                            results.put(dictTerm, LYMPH_DIAGNOSIS_KEY);
                        }
                        else if (sentenceNode.hasFeature(METASTATIC_FEATURE))
                        {
                            System.err.println (" categorizeDiagnoses, category: " + METASTATIC_DIAGNOSIS_KEY);
                            results.put(dictTerm, METASTATIC_DIAGNOSIS_KEY);
                        }
                        else //if (sentenceNode.hasFeature(SIZE_FEATURE))
                        {
                            System.err.println (" categorizeDiagnoses, category: " + PRIMARY_DIAGNOSIS_KEY);
                            results.put(dictTerm, PRIMARY_DIAGNOSIS_KEY);
                        }
                    }
                    else 
                    {
                        System.err.println (" categorizeDiagnoses, category: NONE (sentence==null)");
                    }
                }
            }
        }
        return results;
    }*/

    private Map<DictTerm, Object> categorizeDiagnosesNew (Collection<DictTerm> diagnoses)
    {
        Map<DictTerm, Object> results = new HashMap<DictTerm, Object> ();
        
        Iterator<DictTerm> diagnosisIter = diagnoses.iterator();
        
        while (diagnosisIter.hasNext())
        {
            DictTerm dictTerm = diagnosisIter.next();
            Object category = categorize1Diagnosis(dictTerm);
            if (category != null)
            {
                results.put(dictTerm, category);
            }
        }
        return results;
    }

    private String categorize1Diagnosis (DictTerm diagnosis)
    {
        System.err.println ("categorize1Diagnosis, term: " + diagnosis.getCoveredText());
        /*if (isMetastaticCode (diagnosis.getAttributeValue()))
        {
            System.err.println (" categorize1Diagnosis, category: " + METASTATIC_DIAGNOSIS_KEY);
            return METASTATIC_DIAGNOSIS_KEY;
        }
        else*/ if (isBenignCode (diagnosis.getAttributeValue()))
        {
            System.err.println (" categorize1Diagnosis, category: " + BENIGN_DIAGNOSIS_KEY);
            return BENIGN_DIAGNOSIS_KEY;
        }
        else
        {
            Annotation sentence = terms2sentenceMap.get(diagnosis);

            // do not process if from another doc section (sentence==null)
            if (sentence != null)
            {
                if (findLymph (sentence).size() > 0)
                {
                    System.err.println (" categorize1Diagnosis, category: " + LYMPH_DIAGNOSIS_KEY);
                    return LYMPH_DIAGNOSIS_KEY;
                }
                else if ((isMetastaticCode (diagnosis.getAttributeValue())) || (findMetastatic (sentence).size() > 0) || (findOrigin (sentence).size() > 0))
                {
                    System.err.println (" categorize1Diagnosis, category: " + METASTATIC_DIAGNOSIS_KEY);
                    return METASTATIC_DIAGNOSIS_KEY;
                }
                else //if (sentenceNode.hasFeature(SIZE_FEATURE))
                {
                    System.err.println (" categorize1Diagnosis, category: " + PRIMARY_DIAGNOSIS_KEY);
                    return PRIMARY_DIAGNOSIS_KEY;
                }
            }
            else 
            {
                System.err.println (" categorizeDiagnoses, category: NONE (sentence==null)");
                return null;
            }
        }

    }

    private boolean isMetastaticCode (String diagnosisCode)
    {
        return (diagnosisCode.endsWith(metastaticSuffix));
    }



    private boolean isBenignCode (String diagnosisCode)
    {
        return (diagnosisCode.endsWith(benignSuffix));
    }


    /*
    private boolean sitesOnlyInGrossDescription (Collection<Annotation> corefSites, ObjectMaps objectMaps)
    {
        Iterator<Annotation> itemIter = corefSites.iterator();
        
        while (itemIter.hasNext())
        {
            Annotation corefAnnot = itemIter.next();
            FSArray annots = (FSArray) corefAnnot.getFeatureValue(corefererringSitesFeature);
            for (int item = 0; item < annots.size(); item++)
            {
                DictTerm dictTerm = (DictTerm) annots.get(item);
                //System.err.println ("SITE["+item+"]: " + dictTerm.getAttributeValue());
                FSArray tokens = dictTerm.getMatchedTokens();
                Annotation token = (Annotation) tokens.get(0);
                Integer sentence = objectMaps.getToken2sentenceMap().get(token);
                if (sentence != null)
                {
                    //System.err.println ("Site in sentence number: " + sentence.intValue());
                }
                
                if ((sentence != null) && (sentence.intValue() != 0))
                {
                    return false;
                }
            }
        }
        return true;
    }
     */

    /*
    private void tokenLookupAndPrint (Collection corefAnnotation, Feature feature, ObjectMaps objectMaps)
    {
        Iterator itemIter = corefAnnotation.iterator();
        
        while (itemIter.hasNext())
        {
            Annotation corefAnnot = (Annotation) itemIter.next();
            FSArray annots = (FSArray) corefAnnot.getFeatureValue(feature);
            for (int item = 0; item < annots.size(); item++)
            {
                DictTerm dictTerm = (DictTerm) annots.get(item);
                //System.err.println ("ITEM["+item+"]: " + dictTerm.getAttributeValue());
                FSArray tokens = dictTerm.getMatchedTokens();
                for (int j = 0; j < tokens.size(); j++)
                {
                    Annotation token = (Annotation) tokens.get(j);
                    //System.err.println ("TOKEN: '"  + token.getCoveredText() + "', obj: " + token.getAddress() + ", is in");
                    Integer sentence = (Integer) objectMaps.getToken2sentenceMap().get(token);
                    
                    // might be coreference to another section, in which case it should be ignored
                    if (sentence != null)
                    {
                        //System.err.println ("\tsentence#==null!!!!");
                        //TreeNode sentenceNode = (TreeNode)objectMaps.get(sentence.intValue());
                        //System.err.println ("\tsentence[" + sentence.intValue() + "]:" + sentenceNode.toString());
                        Annotation pp = (Annotation) objectMaps.getToken2ppMap().get(token);
                        if (pp != null)
                        {
                            //System.err.println ("\tand in pp: '" + pp.getCoveredText() + "'");
                        }
                    }
                }
            }
        }
    }*/



    private ObjectMaps mapSubsectionObjects (SubHeading subsection)
    {
        ObjectMaps result = new ObjectMaps ();
        ArrayList<TreeNode> sentences = getSubAnnotationsAsNodes (jcas, sentenceClass, subsection);
        //ArrayList sentences = getSubAnnotationsAsNodes (subsection, sentenceAnnotationIndex);
        
        Iterator<TreeNode> sentenceIter = sentences.iterator();
        int sentenceNumber = 0;
        while (sentenceIter.hasNext())
        {
            TreeNode sentence = sentenceIter.next();
            //ArrayList tokens = getSubAnnotations (sentence.getAnnotation(), tokenAnnotationIndex);
            ArrayList<Annotation> tokens = getSubAnnotations (jcas, tokenAnnotationClass, sentence.getAnnotation());
            //ArrayList terms = getSubAnnotations (sentence.getAnnotation(), dictTermAnnotationIndex);
            //ArrayList<Annotation> terms = getSubAnnotations (jcas, dictTermAnnotationClass, sentence.getAnnotation());
            
            //addToTerms2SentenceMap (terms, sentence.getAnnotation());

            //ArrayList pps = getSubAnnotations (sentence.getAnnotation(), ppAnnotationIndex);
            ArrayList<Annotation> pps = getSubAnnotations (jcas, ppAnnotationClass, sentence.getAnnotation());
            setNPMaps (result, sentence.getAnnotation());
            sentence.setChildren(orderAnnotations (result, new Integer (sentenceNumber), pps, tokens));
            result.add(sentence);
            sentenceNumber += 1;
        }
        for (int i = 0; i < result.size(); i++)
        {
            TreeNode sentence = result.get(i);
            System.err.println("SENTENCE: " + sentence.toString());
        }
        return result;
    }


    private void mapSubsectionAnnotations (SubHeading subsection)
    {
        //ArrayList sentences = getSubAnnotations (subsection, sentenceAnnotationIndex);
        ArrayList<Annotation> sentences = getSubAnnotations (jcas, sentenceClass, subsection);
        Iterator<Annotation> sentenceIter = sentences.iterator();
        int sentenceNumber = 0;
        while (sentenceIter.hasNext())
        {
            Annotation sentence = sentenceIter.next();
            /**/System.err.println ("Adding sentence[" + sentenceNumber + "]: "  + sentence.getCoveredText());
            //ArrayList terms = getSubAnnotations (sentence, dictTermAnnotationIndex);
            ArrayList<Annotation> terms = getSubAnnotations (jcas, dictTermAnnotationClass, sentence);
            
            if (grossDescriptionTerms.size() == 0)
            //if (sentenceNumber == 0)
            {
                if (terms.size() == 0)
                {
                    System.err.println ("NO GROSS DESCRIPTION TERMS IN SENTENCE! " + sentence.getCoveredText());
                }
                grossDescriptionTerms.addAll (terms);
                Iterator<Annotation> gdIter = grossDescriptionTerms.iterator();
                while (gdIter.hasNext())
                {
                    DictTerm dt = (DictTerm) gdIter.next();
                    System.err.println ("GROSS DESCRIPTION TERM: "  + dt.getAttributeValue());
                }
            }

            //addToTerms2SentenceMap (terms, sentence);
            setAnnotationMaps (sentence);
            sentenceNumber += 1;
        }
    }



    private void setNPMaps (ObjectMaps result, Annotation sentence)
    {
        //ArrayList npCombineds = getSubAnnotations (sentence, npCombinedAnnotationIndex);
        ArrayList<Annotation> npCombineds = getSubAnnotations (jcas, npCombinedAnnotationClass, sentence);
        result.addNPCombined2sentenceMap (npCombineds, sentence);

        //ArrayList pps = getSubAnnotations(sentence, ppAnnotationIndex);
        ArrayList<Annotation> pps = getSubAnnotations(jcas, ppAnnotationClass, sentence);
        Iterator<Annotation> ppIter = pps.iterator();
        while (ppIter.hasNext())
        {
            Annotation pp = ppIter.next();
            //ArrayList terms = getSubAnnotations(pp, dictTermAnnotationIndex);
            ArrayList<Annotation> terms = getSubAnnotations(jcas, dictTermAnnotationClass, pp);
            result.addTerm2ppMap(terms, pp);
        }
        
        Iterator<Annotation> npCombinedsIter = npCombineds.iterator();
        while (npCombinedsIter.hasNext())
        {
            Annotation npc = npCombinedsIter.next();
            //ArrayList npLists = getSubAnnotations (npc, npListAnnotationIndex);
            ArrayList<Annotation> npLists = getSubAnnotations (jcas, npListAnnotationClass, npc);
            result.addNPList2npCombinedMap(npLists, npc);
            ArrayList<Annotation> nps = getSubAnnotations (jcas, npAnnotationClass, npc);
            result.addNPs2npCombinedMap(nps, npc);
            
            Iterator<Annotation> npIter = nps.iterator();
            while (npIter.hasNext())
            {
                Annotation np = npIter.next();
                //ArrayList terms = getSubAnnotations(np, dictTermAnnotationIndex);
                ArrayList<Annotation> terms = getSubAnnotations(jcas, dictTermAnnotationClass, np);
                result.addTerms2npMap(terms, np);
            }
        }        
    }



    private void setAnnotationMaps (Annotation sentence)
    {

        //ArrayList npCombineds = getSubAnnotations (sentence, npCombinedAnnotationIndex);
        ArrayList<Annotation> npCombineds = getSubAnnotations (jcas, npCombinedAnnotationClass, sentence);
        addNPCombined2sentenceMap (npCombineds, sentence);

        //ArrayList pps = getSubAnnotations(sentence, ppAnnotationIndex);
        ArrayList<Annotation> pps = getSubAnnotations(jcas, ppAnnotationClass, sentence);
        Iterator<Annotation> ppIter = pps.iterator();
        while (ppIter.hasNext())
        {
            Annotation pp = ppIter.next();
            //ArrayList terms = getSubAnnotations(pp, dictTermAnnotationIndex);
            ArrayList<Annotation> terms = getSubAnnotations(jcas, dictTermAnnotationClass, pp);
            addTerm2ppMap(terms, pp);
            //ArrayList tokens = getSubAnnotations(pp, tokenAnnotationIndex);
            ArrayList<Annotation> tokens = getSubAnnotations(jcas, tokenAnnotationClass, pp);
            addPP2TokenMap(pp, tokens);
            
        }
        
        Iterator<Annotation> npCombinedsIter = npCombineds.iterator();
        while (npCombinedsIter.hasNext())
        {
            Annotation npc = npCombinedsIter.next();
            //ArrayList termsInNPCombined = getSubAnnotations(npc, dictTermAnnotationIndex);
            ArrayList<Annotation> termsInNPCombined = getSubAnnotations(jcas, dictTermAnnotationClass, npc);
            addTerms2npCombinedMap(termsInNPCombined, npc);
            //ArrayList npLists = getSubAnnotations (npc, npListAnnotationIndex);
            ArrayList<Annotation> npLists = getSubAnnotations (jcas, npListAnnotationClass, npc);
            Iterator<Annotation> npListsIter = npLists.iterator();
            while (npListsIter.hasNext())
            {
                Annotation npl = npListsIter.next();
                //ArrayList termsInNPList = getSubAnnotations(npl, dictTermAnnotationIndex);
                ArrayList<Annotation> termsInNPList = getSubAnnotations(jcas, dictTermAnnotationClass, npl);
               addTerms2npListMap(termsInNPList, npl);
            }
            addNPList2npCombinedMap(npLists, npc);
            //ArrayList nps = getSubAnnotations (npc, npAnnotationIndex);
            ArrayList<Annotation> nps = getSubAnnotations (jcas, npAnnotationClass, npc);
            addNPs2npCombinedMap(nps, npc);
            
            Iterator<Annotation> npIter = nps.iterator();
            while (npIter.hasNext())
            {
                Annotation np = npIter.next();
                //ArrayList termsInNP = getSubAnnotations(np, dictTermAnnotationIndex);
                ArrayList<Annotation> termsInNP = getSubAnnotations(jcas, dictTermAnnotationClass, np);
                addTerms2npMap(termsInNP, np);
            }
        }        
    }

    private void addPP2TokenMap (Annotation pp, ArrayList<Annotation> tokens)
    {
        if (pp2tokenMap == null)
        {
            pp2tokenMap = new TreeMap<Annotation, ArrayList<Annotation>> (new UIMAAnnotationOffsetComparator ());
        }
        pp2tokenMap.put(pp, tokens);
    }



    private boolean anyPPsRemain (ArrayList<Annotation> pps, int ppIndex)
    {
        return (pps != null) && (ppIndex < pps.size());
    }
    
    private Collection<TreeNode> orderAnnotations (ObjectMaps objectMaps, Integer sentenceNumber, ArrayList<Annotation> pps, ArrayList<Annotation> tokens)
    {
        Collection<TreeNode> orderedAnnotations = new ArrayList<TreeNode> ();
        
        int ppIndex = 0;
        int tokenIndex = 0;
        Annotation token = null;

        while (tokenIndex < tokens.size())
        {
            if (token == null)
            {
                token = tokens.get(tokenIndex);
            }
            Annotation nextPP = null;
            if (anyPPsRemain(pps, ppIndex))
            {
                nextPP = pps.get(ppIndex);
            }
            while (((nextPP != null) && (token.getEnd() < nextPP.getBegin())) ||
                    ((nextPP == null) && (token != null)))
            {
                orderedAnnotations.add(new TreeNode(token));
                objectMaps.addToSentencePPMaps (sentenceNumber, null, token);
                do
                {
                    tokenIndex += 1;
                    token = nextToken (tokens, tokenIndex);
                } while (shouldSkipToken (token));
            }
            if (anyPPsRemain(pps, ppIndex))
            {
                Annotation pp = nextPP;
                TreeNode ppNode = new TreeNode (pp);
                orderedAnnotations.add(ppNode);
                ArrayList<TreeNode> children = new ArrayList<TreeNode> ();
                while ((token != null) && (token.getEnd() <= pp.getEnd()))
                {
                    children.add(new TreeNode (token));
                    objectMaps.addToSentencePPMaps (sentenceNumber, pp, token);
                    do
                    {
                        tokenIndex += 1;
                        token = nextToken (tokens, tokenIndex);
                    } while (shouldSkipToken (token));
                }
                ppNode.setChildren(children);
                ppIndex += 1;                
            }
        }
        
        return orderedAnnotations;
    }

    
    private boolean shouldSkipToken (Annotation token)
    {   
        // TODO: this is JFrost specific
        //int tokenType = token.getIntValue (tokenTypeFeature);
        
        if ((token != null) && (token.getIntValue (tokenTypeFeature) == 1))
        {
            //System.err.println ("IGNORING TOKEN: '" + token.getCoveredText() + "'");
            return true;
        }
        return false;
    }



    private Annotation nextToken (ArrayList<Annotation> tokens, int tokenIndex)
    {

        if (tokenIndex < tokens.size())
        {
            return tokens.get(tokenIndex);
        }
        else
        {
            return null;
        }
    }
    /*
    private ArrayList getSubAnnotationsAsNodes (Annotation span, AnnotationIndex annotIndex)
    {
        FSIterator annotIter = getSubiterator (span, annotIndex);
        return getSubAnnotationsAsNodesForIterator(annotIter, new TreeNodeComparator ());
    }
    */

    private ArrayList<TreeNode> getSubAnnotationsAsNodes (JCas jcas, Class<? extends Annotation> enclosed, Annotation enclosing)
    {
        FSIterator annotIter = getSubiterator (jcas, enclosed, enclosing);
        return getSubAnnotationsAsNodesForIterator(annotIter, new TreeNodeComparator ());
    }



    private ArrayList<TreeNode> getSubAnnotationsAsNodesForIterator (FSIterator annotIter, Comparator<TreeNode> comparator)
    {
        ArrayList<TreeNode> result = new ArrayList<TreeNode> ();
        while (annotIter.hasNext ())
        {
            Annotation annot = (Annotation) annotIter.next ();
            result.add (new TreeNode (annot));
        }
        Collections.sort(result, comparator);
        return result;
    }

    /*
    private ArrayList getSubAnnotations (Annotation span, AnnotationIndex annotIndex)
    {
        FSIterator annotIter = getSubiterator (span, annotIndex);
        return getSubAnnotationsForIterator(annotIter, null);
    }
    */

    private ArrayList<Annotation> getSubAnnotations (JCas jcas, Class<? extends Annotation> enclosed, Annotation enclosing)
    {
        FSIterator annotIter = getSubiterator (jcas, enclosed, enclosing);
        return getSubAnnotationsForIterator(annotIter, new UIMAAnnotationOffsetComparator ());
    }


    private ArrayList<Annotation> getSubAnnotationsForIterator (FSIterator annotIter, Comparator<Annotation> comparator)
    {
        ArrayList<Annotation> result = new ArrayList<Annotation> ();
        while (annotIter.hasNext ())
        {
            Annotation annot = (Annotation) annotIter.next ();
            //System.err.println ("Adding sub annot: '" + annot.getCoveredText() + "'");
            result.add (annot);
        }
        if (comparator != null)
        {
            Collections.sort(result, comparator);
        }
        return result;
    }
    
    
    
    FSIterator getSubiterator (Annotation span, AnnotationIndex annotIndex)
    {
        return annotIndex.subiterator (span);
    }
    
    FSIterator getSubiterator (JCas jcas, Class<? extends Annotation> enclosed, Annotation enclosing)
    {
        try 
        {  
            return FeatureConstrainedIterator.getEnclosedIterator(jcas, enclosed, enclosing);        
        }
        catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (NoSuchFieldException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    private ArrayList<CorefAnnotation> getCorefAnnotations (int subsectionNumber, AnnotationIndex index)
    {
        ArrayList<CorefAnnotation> result = new ArrayList<CorefAnnotation> ();

        FSIterator annotIter = index.iterator();
        while (annotIter.hasNext ())
        {
            CorefAnnotation annot = (CorefAnnotation) annotIter.next ();
            if (annot.getSubsectionNumber() == subsectionNumber)
            {
                result.add (annot);
            }
        }
        return result;
    }


}
