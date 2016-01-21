package org.ohnlp.medkat.taes.lymphNodes;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;


import org.apache.uima.analysis_engine.ResultSpecification;
import org.apache.uima.analysis_engine.annotator.AnnotatorConfigurationException;
import org.apache.uima.analysis_engine.annotator.AnnotatorContext;
import org.apache.uima.analysis_engine.annotator.AnnotatorInitializationException;
import org.apache.uima.analysis_engine.annotator.AnnotatorProcessException;
import org.apache.uima.analysis_engine.annotator.JTextAnnotator_ImplBase;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.jcas.JFSIndexRepository;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.ohnlp.medkat.common.FeatureConstrainedIterator;
import org.ohnlp.medkat.common.UIMAAnnotationUtils;
import org.ohnlp.medkat.logger.Logger;
import org.ohnlp.medkat.scr.types.SCRLymphNodes;
import org.ohnlp.medkat.scr.types.SCRLymphNodesReading;
import org.ohnlp.medkat.taes.conceptMapper.DictTerm;
import org.ohnlp.medkat.taes.conceptMapper.DictTermMarkers;
import org.ohnlp.medkat.taes.coreferencer.Coreferencer;
import org.ohnlp.medkat.taes.sectionFinder.DiagnosisAnnotation;
import org.ohnlp.medkat.taes.sizeLocationRegExAnnotator.LymphLevelExpression;
import org.ohnlp.medkat.taes.subsectionDetector.SubHeading;
import org.ohnlp.medkat.taes.syntacticUnitFinder.SyntacticUnit;


public class LymphNodesAnnotator
        extends JTextAnnotator_ImplBase
{
    int [] m_site_typeIndexIDs;
    int [] m_diagnosis_typeIndexIDs;
    private int m_undefinedNodeCount;
    Class<? extends Annotation>  m_sentenceClass;

    final static String PARAM_SITETYPES = "SiteTypes";
    final static String PARAM_DIAGNOSISTYPES = "DiagnosisTypes";
    final static String PARAM_UNDEFINEDNODECOUNT = "UndefinedNodeCount";
    final static String PARAM_SENTENCECLASS= "SentenceClass";

    public void initialize(AnnotatorContext ac)
    throws AnnotatorInitializationException, AnnotatorConfigurationException
    {
        Logger.getDefaultLogger().logInfo("initializing");
        
        super.initialize(ac);
        
        try {
            m_undefinedNodeCount = ((Integer)ac.getConfigParameterValue(PARAM_UNDEFINEDNODECOUNT)).intValue();
            String[] site_types = (String[])ac.getConfigParameterValue(PARAM_SITETYPES);
            String[] daignosis_types = (String[])ac.getConfigParameterValue(PARAM_DIAGNOSISTYPES);
            m_site_typeIndexIDs = new int[site_types.length]; 
            m_diagnosis_typeIndexIDs = new int[daignosis_types.length];
            m_sentenceClass = UIMAAnnotationUtils.forName((String)ac.getConfigParameterValue(PARAM_SENTENCECLASS)); 

            
            for (int i = 0; i < site_types.length; ++i) {
                m_site_typeIndexIDs[i] = UIMAAnnotationUtils.getTypeIndexId(site_types[i]);
            }
            for (int i = 0; i < daignosis_types.length; ++i) {
                m_diagnosis_typeIndexIDs[i] = UIMAAnnotationUtils.getTypeIndexId(daignosis_types[i]);
            }
        }
        catch (Exception e) {
            throw new AnnotatorInitializationException(e); 
        }
    }
    
    List<Annotation> findEnclosedSites (JCas jcas, Annotation enclosing, Annotation ln_ann)
    throws IllegalArgumentException,
           SecurityException,
           ClassNotFoundException,
           IllegalAccessException,
           NoSuchFieldException
    {
        List<Annotation> sites = new ArrayList<Annotation>();

        for (Iterator<?> it = FeatureConstrainedIterator.getEnclosedIterator(jcas, m_site_typeIndexIDs, enclosing) ; it.hasNext();) {
            Annotation site = (Annotation)it.next();
            
            if ((null == ln_ann) || ((ln_ann.getBegin() >= site.getBegin()) && (ln_ann.getEnd() <= site.getEnd()))) {
                sites.add(site);
                if (null != ln_ann) {
                    break;
                }
            }
        }
        
        return sites;
    }

    List<DictTerm> findEnclosedDictTerms (JCas jcas, Annotation enclosing, String semClass)
    throws IllegalArgumentException,
           SecurityException,
           ClassNotFoundException,
           IllegalAccessException,
           NoSuchFieldException
    {
        List<DictTerm> dts = new ArrayList<DictTerm>();
        
        if (null == semClass) {
            for (FSIterator dt_it = FeatureConstrainedIterator.getEnclosedIterator(
                    jcas, DictTerm.class, enclosing); dt_it.hasNext();) {
                dts.add((DictTerm)dt_it.next());
            }
        }
        else {
            for (FSIterator dt_it = FeatureConstrainedIterator.getEnclosedIterator(
                    jcas, DictTerm.class, enclosing, "SemClass", new String[] {semClass}); dt_it.hasNext();) {
                dts.add((DictTerm)dt_it.next());
            }
        }
        return dts;
    }
    

    List<Annotation> findSites (JCas jcas, Annotation sentence, Annotation section, Annotation ln_ann)
    throws IllegalArgumentException,
           SecurityException,
           ClassNotFoundException,
           IllegalAccessException,
           NoSuchFieldException
    {
        List<Annotation> sites = findEnclosedSites(jcas, sentence, ln_ann);
        if (sites.isEmpty()) {
            for (FSIterator it = FeatureConstrainedIterator.getLeftIterator(                    
                    jcas, m_sentenceClass, section, sentence); it.isValid();) {
                sites = findEnclosedSites(jcas, (Annotation)it.get(), null);
                if (!sites.isEmpty()) {
                    break;
                }
                it.moveToPrevious();
            }
        }
        return sites;
    }
                
    List<Annotation> findEnclosedDiagnoses (JCas jcas, Annotation enclosing)
    throws IllegalArgumentException,
           SecurityException,
           ClassNotFoundException,
           IllegalAccessException,
           NoSuchFieldException
    {
        List<Annotation> diagnoses = new ArrayList<Annotation>();
        for (Iterator<?> it = FeatureConstrainedIterator.getEnclosedIterator(jcas, m_diagnosis_typeIndexIDs, enclosing); it.hasNext();) {
            diagnoses.add((Annotation)it.next());
        }
        
        return diagnoses;
    }

    Annotation getNextSentence (JCas jcas, Annotation subsection, Annotation sentence)
    throws IllegalArgumentException,
           SecurityException,
           ClassNotFoundException,
           IllegalAccessException,
           NoSuchFieldException
    {
        for (FSIterator it = FeatureConstrainedIterator.getRightIterator(
                jcas, m_sentenceClass, subsection, sentence); it.isValid();) {
            return (Annotation)it.get();
        }
        return null;
    }
        
    
    
    List<Annotation> findDiagnosesFromSameSubsection (JCas jcas, Annotation subsection, Annotation sentence, boolean all)
    throws IllegalArgumentException,
           SecurityException,
           ClassNotFoundException,
           IllegalAccessException,
           NoSuchFieldException
    {
        for (FSIterator it = FeatureConstrainedIterator.getLeftIterator(
                jcas, m_sentenceClass, subsection, sentence); it.isValid();) {
            Annotation sa = (Annotation)it.get();
            List<Annotation> diagnoses = findEnclosedDiagnoses(jcas, sa);
            if (!diagnoses.isEmpty() && !all) {
                // System.out.println(CommonFeatureMatcher.getDocumentId(jcas, null) + ": diagnoses found in preceding sentences");
                return diagnoses; 
            }
            it.moveToPrevious();
        }
        
        for (FSIterator it = FeatureConstrainedIterator.getRightIterator(
                jcas, m_sentenceClass, subsection, sentence); it.isValid();) {
            Annotation sa = (Annotation)it.get();
            List<Annotation> diagnoses = findEnclosedDiagnoses(jcas, sa);
            if (!diagnoses.isEmpty() && all) {
                // System.out.println(CommonFeatureMatcher.getDocumentId(jcas, null) + ": diagnoses found in following sentences");
                return diagnoses; 
            }
            it.moveToNext();
        }
        return new ArrayList<Annotation>();
    }
    
    List<LymphLevelExpression> findLymphLevelExpressions (JCas jcas, Annotation enclosing)
    throws IllegalArgumentException,
           SecurityException,
           ClassNotFoundException,
           IllegalAccessException,
           NoSuchFieldException
    {
        List<LymphLevelExpression> result = new ArrayList<LymphLevelExpression>();
        
        LymphLevelExpression prevLLE = null;
        
        for (Iterator<?> it = FeatureConstrainedIterator.getEnclosedIterator(
                jcas, LymphLevelExpression.class, enclosing); it.hasNext();) {
            LymphLevelExpression thisLLE = (LymphLevelExpression)it.next();  
            if (null != prevLLE) {
                Annotation prevPar = FeatureConstrainedIterator.getEnclosingAnnotation(jcas, SyntacticUnit.class, prevLLE);
                Annotation thisPar = FeatureConstrainedIterator.getEnclosingAnnotation(jcas, SyntacticUnit.class, thisLLE);
                if (prevPar != thisPar) {
                    // if the expressions come from different parenteses
                    if (null == prevPar) {
                        result.remove(prevLLE);
                        prevLLE = null;
                        result.add(thisLLE);
                        continue;
                    }
                }
            }
            result.add(thisLLE);
            prevLLE = thisLLE;
        }
        return result;
    }
    
    int collectLNFeatures(JCas jcas, Annotation subsection, Annotation sentence, Annotation ln_ann)
    throws IllegalArgumentException,
           SecurityException,
           ClassNotFoundException,
           IllegalAccessException,
           NoSuchFieldException,
           InvocationTargetException,
           NoSuchMethodException
    {
        List<LymphLevelExpression> llexprs = findLymphLevelExpressions(jcas, sentence);
        List<Annotation> diagnoses = findEnclosedDiagnoses(jcas, sentence);
        List<Annotation> sites = findSites(jcas, sentence, subsection, ln_ann);
        
        if (diagnoses.isEmpty()) {
            if (llexprs.isEmpty()) {
                // if neither diagnoses nor LymphLevelExpression present in the sentence -
                // skip this sentence
                return 0;
            }
            else {
                int negative_Dx = 0;
                for (Iterator<LymphLevelExpression> it = llexprs.iterator(); it.hasNext();) {
                    LymphLevelExpression lle = it.next();
                    if (0 == lle.getStatus()) {
                        // when negative lymph nodes and no particular diagnosis is mentioned
                        // in the same sentence - do not look for diagnosis in the rest of the section
                        negative_Dx = 1;
                        break;
                    }
                }
                Annotation nextSentence = getNextSentence(jcas, subsection, sentence);
                if (null != nextSentence) {
                    List<DictTerm> dictTerms = findEnclosedDictTerms(jcas, nextSentence, "Margin");
                    dictTerms.addAll(findEnclosedDictTerms(jcas, nextSentence, "Invasion"));
                    dictTerms.addAll(findEnclosedDictTerms(jcas, nextSentence, "Site"));
                    if (dictTerms.isEmpty()) {
                        List<LymphLevelExpression> nsLLExprs = findLymphLevelExpressions(jcas, nextSentence);
                        if (nsLLExprs.isEmpty()) {
                            List<Annotation> nsDiagnoses = findEnclosedDiagnoses(jcas, nextSentence);
                            for (Iterator<Annotation> it = nsDiagnoses.iterator(); it.hasNext();) {
                                Annotation nsHDx = it.next();
                                if (negative_Dx != nsHDx.getIntValue(nsHDx.getType().getFeatureByBaseName("Negation"))) {
                                    nsDiagnoses.clear();
                                    break;
                                }
                            }
                            diagnoses = nsDiagnoses;
                            if (!diagnoses.isEmpty()) {
                                // System.out.println(CommonFeatureMatcher.getDocumentId(jcas, null) + ": diagnoses found in next sentence");
                            }
                        }
                    }
                }
                if (diagnoses.isEmpty() && (0 == negative_Dx)) {
                    diagnoses = findDiagnosesFromSameSubsection(jcas, subsection, sentence, false);
                    if (diagnoses.isEmpty()) {
                        return 0;
                    }
                }
            }
        }
        
        return createLNModel(jcas, sites, diagnoses, llexprs, false);
    }
    
    public int createLNModel (JCas jcas,
                              List<Annotation>              sites, 
                              List<Annotation>              diagnoses,
                              List<LymphLevelExpression>    llexprs,
                              boolean                       infered)
    {
            int created_anns = 0;
        
        while (!llexprs.isEmpty()) {
            ++created_anns;
            LymphLevelExpression lle = llexprs.remove(0);
    
            SCRLymphNodesReading lnr = new SCRLymphNodesReading(jcas);
            lnr.setPositiveNodes(lle.getNumPositive());
            lnr.setTotalNodes(lle.getNumTotal() < 0 ? m_undefinedNodeCount : lle.getNumTotal());
            UIMAAnnotationUtils.updateSpan(lnr, lle);
    
            if (!sites.isEmpty()) {
                UIMAAnnotationUtils.addAnnotationsToFSArray(jcas, lnr, "Sites", sites, true);
            }
            if (!diagnoses.isEmpty()) {
                UIMAAnnotationUtils.addAnnotationsToFSArray(jcas, lnr, "Diagnoses", diagnoses, true);
            }
            if (infered) {
                lnr.setInference(1);
            }
            lnr.addToIndexes();
            SCRLymphNodes ln = new SCRLymphNodes(jcas);
            UIMAAnnotationUtils.addAnnotationsToFSArray(jcas, ln, "Readings", Arrays.asList(new Annotation[]{lnr}), true);
            ln.addToIndexes();
        }
    
        return created_anns;
    }
    
    
    
    int collectInferedLNFeatures(JCas jcas, Annotation subsection)
    throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException,
           NoSuchMethodException, ClassNotFoundException, NoSuchFieldException
    {
        // preconditions: no other ln models have been created for the scetion 
        List<LymphLevelExpression> llexprs = findLymphLevelExpressions(jcas, subsection);
        if (!llexprs.isEmpty()) {
            // should be handled before as LLE always assosiated with ln site by LymphStatus module
            // although it is a bad practive to depend on the logic that is implemented by other module
            return 0; 
        }

        List<DictTerm> lnDictTerms = findEnclosedDictTerms(jcas, subsection, "Lymph");
        
        List<Annotation> lnSites = new ArrayList<Annotation>();
        boolean negated = false;
        
        Annotation arbitrary_site = null;
        for (Iterator<DictTerm> it = lnDictTerms.iterator(); it.hasNext();) {
            DictTerm dtLN = it.next();
            Iterator<?> as_it = FeatureConstrainedIterator.getEnclosedIterator(jcas, this.m_site_typeIndexIDs, dtLN);
            if (as_it.hasNext()) {
                Annotation site = (Annotation)as_it.next();
                if (lnSites.isEmpty() || Coreferencer.coreferencedAnnotations(lnSites, site, "Coreferences", "Elements")) {
                    lnSites.add(site);
                    arbitrary_site = site;
                }
                else {
                    // some other lymph nodes are mentioned. cannot assume that the diagnoses would apply
                    // to all of them
                    return 0;
                }
            }
            if (DictTermMarkers.isMarkedAsNegated(dtLN)) {
                negated = true;
            }
        }
        if (lnSites.isEmpty()) {
            // no site - no dict term - no model
            return 0;
        }
        
        List<Annotation> diagnoses = findEnclosedDiagnoses(jcas, subsection);
        if (diagnoses.isEmpty()) {
            negated = true;
        }
        else {
            // System.out.println(CommonFeatureMatcher.getDocumentId(jcas, null) + ": diagnoses found in the same section");
        }
        /*        
        if (!diagnoses.isEmpty()) {
            if (!Coreferencer.coreferencedAnnotations(diagnoses, "CoreferredDiagnoses", "Elements")) {
                // ambigous, cannot resolve which diagnosis is assiciated with LN
                return 0;
            }
        }
         */
        int positives = negated ? 0 : 1;
        int total = m_undefinedNodeCount; // do it explicitly now 
        // create artificial LLE when no lle information present
        LymphLevelExpression lle = new LymphLevelExpression(jcas, arbitrary_site.getBegin(), arbitrary_site.getEnd());
        lle.setNodeExpression(positives + "/" + total);
        lle.setNumPositive(positives);
        lle.setNumTotal(total);
        lle.setStatus(positives > 0 ? 1 : 0);
        // do not add it to indixes yet
        llexprs.add(lle);
        
        return createLNModel(jcas, lnSites, diagnoses, llexprs, true);
    }
    
    
    void processSubsection (JCas jcas, Annotation subsection)
    throws IllegalArgumentException,
           SecurityException,
           ClassNotFoundException,
           IllegalAccessException,
           NoSuchFieldException,
           InvocationTargetException,
           NoSuchMethodException
    {
        int created_anns = 0;
        List<DictTerm> lnSites = findEnclosedDictTerms(jcas, subsection, "Lymph");
        Annotation prevSentence = null;
        for (Iterator<DictTerm> it = lnSites.iterator(); it.hasNext();) {
            Annotation ann = it.next();
            Annotation sentence = FeatureConstrainedIterator.getEnclosingAnnotation (jcas, m_sentenceClass, ann);
            if (prevSentence != sentence) {
                created_anns += collectLNFeatures(jcas, subsection, sentence, ann);
            }
            prevSentence = sentence;
        }
        if (0 == created_anns) {
            created_anns = collectInferedLNFeatures(jcas, subsection);
        }
    }
    
    public void process (JCas jcas, ResultSpecification arg1)
    throws AnnotatorProcessException
    {
        Logger.getDefaultLogger().logInfo("processing");
        
        try {
            JFSIndexRepository indxs = jcas.getJFSIndexRepository ();
            AnnotationIndex sec_ind = (AnnotationIndex)indxs.getAnnotationIndex(DiagnosisAnnotation.typeIndexID);
            AnnotationIndex subsec_ind = (AnnotationIndex)indxs.getAnnotationIndex(SubHeading.typeIndexID);
            
            for (Iterator<?> sec_it = sec_ind.iterator(); sec_it.hasNext();) {
                Annotation section = (DiagnosisAnnotation)sec_it.next();
                for (Iterator<?> subsec_it = subsec_ind.subiterator(section); subsec_it.hasNext();) {
                    Annotation subsection = (Annotation)subsec_it.next();
                    processSubsection(jcas, subsection);
                }
            }
        }
        catch (Exception e) {
            throw new AnnotatorProcessException(e);
        }
    }
}
