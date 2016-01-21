package org.ohnlp.medkat.taes.diagnosisTypeDetector;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import uima.tt.SentenceAnnotation;


import org.apache.uima.analysis_engine.ResultSpecification;
import org.apache.uima.analysis_engine.annotator.AnnotatorConfigurationException;
import org.apache.uima.analysis_engine.annotator.AnnotatorInitializationException;
import org.apache.uima.analysis_engine.annotator.AnnotatorProcessException;
import org.apache.uima.analysis_engine.annotator.JTextAnnotator_ImplBase;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Feature;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.TypeSystem;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.jcas.JFSIndexRepository;
import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.ohnlp.medkat.taes.conceptMapper.DictTerm;
import org.ohnlp.medkat.taes.conceptMapper.DictTermMarkers;

public class DiagnosisTypeDetector
        extends JTextAnnotator_ImplBase
{
    
    
    public class AnnotationPosComparator
            implements Comparator<Annotation>
    {

        public int compare (Annotation left, Annotation right)
        {
            int leftBegin = left.getBegin ();
            int rightBegin = right.getBegin ();
            
            if (leftBegin < rightBegin)
            {
                return -1;
            }
            else if (leftBegin > rightBegin)
            {
                return 1;
            }
            return 0;
        }

    }


	private static final String lymphDiagnosisTypeDictTermFeatureName = "term";
    private Feature lymphDiagnosisTypeDictTermFeature;
    private Type lymphDiagnosisType;

    private static final String metastaticDiagnosisTypeDictTermFeatureName = "term";
    private Feature metastaticDiagnosisTypeDictTermFeature;
    private Type metastaticDiagnosisType;

    private static final String primaryDiagnosisTypeDictTermFeatureName = "term";
    private Feature primaryDiagnosisTypeDictTermFeature;
    private Type primaryDiagnosisType;

    private static final String otherDiagnosisTypeDictTermFeatureName = "term";
    private Feature otherDiagnosisTypeDictTermFeature;
    private Type otherDiagnosisType;

    private AnnotationIndex dictTermAnnotationIndex;
    //private Feature dictTermBeginFeature;
    //private Feature dictTermEndFeature;
    

    public void typeSystemInit (TypeSystem typeSystem) throws AnnotatorInitializationException, AnnotatorConfigurationException
    {
        super.typeSystemInit (typeSystem);

        lymphDiagnosisType = typeSystem.getType (LymphDiagnosis.class.getName ());
        lymphDiagnosisTypeDictTermFeature = lymphDiagnosisType.getFeatureByBaseName (lymphDiagnosisTypeDictTermFeatureName);

        metastaticDiagnosisType = typeSystem.getType (MetastaticDiagnosis.class.getName ());
        metastaticDiagnosisTypeDictTermFeature = metastaticDiagnosisType.getFeatureByBaseName (metastaticDiagnosisTypeDictTermFeatureName);

        primaryDiagnosisType = typeSystem.getType (PrimaryDiagnosis.class.getName ());
        primaryDiagnosisTypeDictTermFeature = primaryDiagnosisType.getFeatureByBaseName (primaryDiagnosisTypeDictTermFeatureName);

        otherDiagnosisType = typeSystem.getType (OtherDiagnosis.class.getName ());
        otherDiagnosisTypeDictTermFeature = otherDiagnosisType.getFeatureByBaseName (otherDiagnosisTypeDictTermFeatureName);
    }


    public void process (JCas jcas, ResultSpecification arg1)
        throws AnnotatorProcessException
    {
        JFSIndexRepository indexes = jcas.getJFSIndexRepository ();

        Map<Annotation, Annotation> previousSentenceMap = findPreviousSentences ((AnnotationIndex) indexes.getAnnotationIndex (SentenceAnnotation.typeIndexID));
        //dictTermBeginFeature = jcas.getCasType (DictTerm.type).getFeatureByBaseName ("begin");
        //dictTermEndFeature = jcas.getCasType (DictTerm.type).getFeatureByBaseName ("end");
        dictTermAnnotationIndex = (AnnotationIndex) indexes.getAnnotationIndex (DictTerm.typeIndexID);
        
        FSIterator DictTermIterator = dictTermAnnotationIndex.iterator ();
        while (DictTermIterator.hasNext ())
        {
            DictTerm dictTerm = (DictTerm) DictTermIterator.next ();
            if (dictTerm.getSemClass ().equals ("Diagnosis") && isOKMark (dictTerm.getMarked ()))
            {
                Annotation previousSentence = previousSentenceMap.get (dictTerm.getEnclosingSpan ());
                //System.err.println("Previous Sentence, b: " + previousSentence.getBegin () + ", e: " + previousSentence.getEnd () + ", '" + previousSentence.getCoveredText ());
                //System.err.println("Sentence, b: " + dictTerm.getEnclosingSpan ().getBegin() + ", e: " + dictTerm.getEnclosingSpan ().getEnd() + ", '" + dictTerm.getEnclosingSpan ().getCoveredText ());
                createDiagnosis (jcas, dictTerm, previousSentence, dictTermAnnotationIndex);
            }
        }

    }

    private Map<Annotation, Annotation> findPreviousSentences (AnnotationIndex sentenceIndex)
    {
        Map<Annotation, Annotation> previousSentences = new HashMap<Annotation, Annotation> ();
        
        ArrayList<Annotation> sentences = new ArrayList<Annotation> ();
        FSIterator sentenceIter = sentenceIndex.iterator ();
        while (sentenceIter.hasNext ())
        {
            sentences.add ((Annotation)sentenceIter.next ());
        }
        Collections.sort (sentences, new AnnotationPosComparator());
        
        Iterator<Annotation> sentenceArrayIter = sentences.iterator ();
        Annotation prevSentence = null;
        while (sentenceArrayIter.hasNext ())
        {
            Annotation sentence = (Annotation) sentenceArrayIter.next ();
            previousSentences.put (sentence, prevSentence);
            prevSentence = sentence;
        }
        return previousSentences;
    }


    private void createDiagnosis (JCas jcas, DictTerm dictTerm, Annotation previousSpan, AnnotationIndex dictTermIndex)
    {
        ArrayList<DictTerm> sites = getSitesFor (jcas, dictTerm, previousSpan, dictTermIndex);

        //System.err.println ("Diagnosis: " + dictTerm.getCoveredText ());
        FSArray sitesArray = null;
        if (sites.isEmpty ())
        {
            // do nothing
        }
        else
        {
            sitesArray = new FSArray(jcas, sites.size ());
            for (int i = 0; i < sites.size (); i++)
            {
                sitesArray.set (i, sites.get (i));
                //System.err.println ("\tSite: " + ((DictTerm) sites.get (i)).getDictCanon ());
            }
        }
        //System.err.println("sites FSArray length: " + ((sitesArray == null) ? "null" : "" + sitesArray.size ()));
        if (DictTermMarkers.isOnlyMarkedAsMetastatic (dictTerm))
        {
            if (lymphNodeSentence (jcas, dictTerm.getEnclosingSpan ()))
            {
                //System.err.println("Create LymphDiagnosis, marked = " + dictTerm.getMarked () + ", text: " + dictTerm.getCoveredText ());
                LymphDiagnosis ld = new LymphDiagnosis (jcas);
                ld.setBegin (dictTerm.getBegin ());
                ld.setEnd (dictTerm.getEnd ());
                ld.setSites (sitesArray);
                ld.setFeatureValue (lymphDiagnosisTypeDictTermFeature, dictTerm);
                ld.addToIndexes();
            }
            else
            {
                //System.err.println("Create MetastaticDiagnosis, marked = " + dictTerm.getMarked () + ", text: " + dictTerm.getCoveredText ());
                MetastaticDiagnosis md = new MetastaticDiagnosis (jcas);
                md.setBegin (dictTerm.getBegin ());
                md.setEnd (dictTerm.getEnd ());
                md.setSites (sitesArray);
                md.setFeatureValue (metastaticDiagnosisTypeDictTermFeature, dictTerm);
                md.addToIndexes();
            }
        }
        else if ((! DictTermMarkers.isMarkedAsNegated (dictTerm)) &&
                 (! DictTermMarkers.isMarkedAsMetastatic (dictTerm)))
        {
            //System.err.println("Create Non-MetastaticDiagnosis, marked = " + dictTerm.getMarked () + ", text: " + dictTerm.getCoveredText ());

            if (DictTermMarkers.isMarkedAsSuperfluous (dictTerm))
            {
                //System.err.println("\tCreate OtherDiagnosis");
    
                OtherDiagnosis od = new OtherDiagnosis (jcas);
                od.setBegin (dictTerm.getBegin ());
                od.setEnd (dictTerm.getEnd ());
                od.setSites (sitesArray);
                od.setFeatureValue (otherDiagnosisTypeDictTermFeature, dictTerm);
                od.addToIndexes();
            }
            else
            {
                //System.err.println("\tCreate PrimaryDiagnosis");
    
                PrimaryDiagnosis pd = new PrimaryDiagnosis (jcas);
                pd.setBegin (dictTerm.getBegin ());
                pd.setEnd (dictTerm.getEnd ());
                pd.setSites (sitesArray);
                pd.setFeatureValue (primaryDiagnosisTypeDictTermFeature, dictTerm);
                pd.addToIndexes();
            }
        }
    }


    private ArrayList<DictTerm> getSitesFor (JCas jcas, DictTerm dictTerm, Annotation previousSpan, AnnotationIndex dictTermIndex)
    {
        ArrayList<DictTerm> sites = new ArrayList<DictTerm> ();
        /**/FSIterator dictTermIterator = dictTermAnnotationIndex.subiterator (dictTerm.getEnclosingSpan ());
        //FSIterator baseDictTermIterator = dictTermAnnotationIndex.iterator ();
        //FSIterator dictTermIterator = findBeginEndContraintIterator (jcas, baseDictTermIterator, dictTerm.getEnclosingSpan().getBegin(), dictTerm.getEnclosingSpan().getEnd());
        //System.err.println("Checking sentence: " + dictTerm.getEnclosingSpan().getCoveredText ());
        while (dictTermIterator.hasNext ())
        {
            DictTerm term = (DictTerm) dictTermIterator.next ();
            if (isOKMark (term.getMarked()) && (term.getSemClass ().equals ("Site")))
            {
                sites.add (term);
            }
        }
        if ((previousSpan != null) && (sites.isEmpty ()))
        {
            /**/dictTermIterator = dictTermAnnotationIndex.subiterator (previousSpan);
            //baseDictTermIterator = dictTermAnnotationIndex.iterator ();
            //dictTermIterator = findBeginEndContraintIterator (jcas, baseDictTermIterator, previousSpan.getBegin(), previousSpan.getEnd());
            //System.err.println("Checking sentence: " + previousSpan.getCoveredText ());

            while (dictTermIterator.hasNext ())
            {
                DictTerm term = (DictTerm) dictTermIterator.next ();
                if (isOKMark (term.getMarked()) && (term.getSemClass ().equals ("Site")))
                {
                    sites.add (term);
                }
            }

        }
        return sites;
    }


    private boolean isOKMark (int marked)
    {
        return ((marked == 0) || ((marked & DictTermMarkers.METASTATIC_INDICATOR) != 0));
    }


    private boolean lymphNodeSentence (JCas jcas, Annotation enclosingSpan)
    {
        FSIterator terms = dictTermAnnotationIndex.subiterator (enclosingSpan);
        while (terms.hasNext ())
        {
            DictTerm term = (DictTerm) terms.next ();
            if (term.getSemClass ().equals ("Lymph"))
            {
                return true;
            }
        }
        return false;
    }

    

    /*
     
    private FSIterator findBeginEndContraintIterator (JCas jcas, FSIterator baseIter, int begin, int end)
    {        
        ConstraintFactory cf = jcas.getConstraintFactory(); 
        
        FeaturePath beginPath = jcas.createFeaturePath(); 
        beginPath.addFeature(dictTermBeginFeature); 
        FSIntConstraint beginConstraint = cf.createIntConstraint(); 
        beginConstraint.geq(begin);         
        FSMatchConstraint okBegin = cf.embedConstraint(beginPath, beginConstraint); 
        
        FeaturePath endPath = jcas.createFeaturePath(); 
        endPath.addFeature(dictTermEndFeature); 
        FSIntConstraint endConstraint = cf.createIntConstraint(); 
        endConstraint.leq(end);         
        FSMatchConstraint okEnd = cf.embedConstraint(endPath, endConstraint); 
        
        FSMatchConstraint okBounds = cf.and(okBegin, okEnd);
            
        return jcas.createFilteredIterator (baseIter, okBounds); 
        
    }
    */

}
