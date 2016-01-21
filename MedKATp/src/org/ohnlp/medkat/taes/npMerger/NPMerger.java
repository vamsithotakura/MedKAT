package org.ohnlp.medkat.taes.npMerger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import uima.tt.NPAnnotation;
import uima.tt.NPListAnnotation;
import uima.tt.NPPAnnotation;
import uima.tt.SentenceAnnotation;
import uima.tt.TokenAnnotation;

import org.apache.uima.analysis_engine.ResultSpecification;
import org.apache.uima.analysis_engine.annotator.AnnotatorProcessException;
import org.apache.uima.analysis_engine.annotator.JTextAnnotator_ImplBase;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.jcas.JFSIndexRepository;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.ohnlp.medkat.common.UIMAAnnotationOffsetComparator;


// merge overlapping NPAnnotation, NPListAnnotation and NPPAnnotation
public class NPMerger
        extends JTextAnnotator_ImplBase
{

    public void process (JCas jcas, ResultSpecification resultSpecification) throws AnnotatorProcessException
    {
        createNPConjunctedAnnotations(jcas);        
        createMissingNPPAnnotations(jcas);
        
        JFSIndexRepository indexes = jcas.getJFSIndexRepository ();
        

        AnnotationIndex NPListAnnotationIndex = (AnnotationIndex) indexes.getAnnotationIndex (NPListAnnotation.typeIndexID);
        AnnotationIndex NPPAnnotationIndex = (AnnotationIndex) indexes.getAnnotationIndex (NPPAnnotation.typeIndexID);
        AnnotationIndex NPAnnotationIndex = (AnnotationIndex) indexes.getAnnotationIndex (NPAnnotation.typeIndexID);

        Set<FeatureStructure> superceded = new HashSet<FeatureStructure>();
        
        //NPList > NPP > NP
        FSIterator NPListAnnotationIterator = NPListAnnotationIndex.iterator ();
        while (NPListAnnotationIterator.hasNext ())
        {
            NPListAnnotation npList = (NPListAnnotation) NPListAnnotationIterator.next ();
            makeNewNP (jcas, npList.getBegin (), npList.getEnd ());
            
            FSIterator subNPPIter = NPPAnnotationIndex.subiterator (npList);
            while (subNPPIter.hasNext ())
            {
                superceded.add ((FeatureStructure)subNPPIter.next ());
            }
            FSIterator subNPIter = NPAnnotationIndex.subiterator (npList);
            while (subNPIter.hasNext ())
            {
                superceded.add ((FeatureStructure)subNPIter.next ());
            }
        }

        FSIterator NPPAnnotationIterator = NPPAnnotationIndex.iterator ();
        while (NPPAnnotationIterator.hasNext ())
        {
            NPPAnnotation npp = (NPPAnnotation) NPPAnnotationIterator.next ();
            
            if (! superceded.contains (npp))
            {
                makeNewNP (jcas, npp.getBegin (), npp.getEnd ());
                
                FSIterator subNPIter = NPAnnotationIndex.subiterator (npp);
                while (subNPIter.hasNext ())
                {
                    superceded.add ((FeatureStructure)subNPIter.next ());
                }
            }
        }

        FSIterator NPAnnotationIterator = NPAnnotationIndex.iterator ();
        while (NPAnnotationIterator.hasNext ())
        {
            NPAnnotation np = (NPAnnotation) NPAnnotationIterator.next ();
            
            if (! superceded.contains (np))
            {
                makeNewNP (jcas, np.getBegin (), np.getEnd ());                
            }
        }


    }

    private void makeNewNP (JCas jcas, int begin, int end)
    {
        NPCombinedAnnotation np = new NPCombinedAnnotation (jcas);
        np.setBegin (begin);
        np.setEnd (end);
        np.addToIndexes();
    }
    
    private void createNPConjunctedAnnotations(JCas jcas)
    {
        JFSIndexRepository indxs = jcas.getJFSIndexRepository ();

        AnnotationIndex sa_idx = (AnnotationIndex)indxs.getAnnotationIndex (SentenceAnnotation.typeIndexID);
        
        for (FSIterator it = sa_idx.iterator(); it.hasNext();) {
            createNPConjunctedAnnotations(jcas, (SentenceAnnotation)it.next());
        }
    }

    private void createNPConjunctedAnnotations(JCas jcas, SentenceAnnotation enclosing)
    {
        JFSIndexRepository indxs = jcas.getJFSIndexRepository ();

        AnnotationIndex ann_idx = (AnnotationIndex)indxs.getAnnotationIndex (Annotation.typeIndexID);
        List<Annotation> newNPCs = new ArrayList<Annotation>();
        
        NPAnnotation curr_np = null;
        Annotation curr_enclosingNP = null;
        String pattern = "";
        Set<Annotation> patternAnnotations = new TreeSet<Annotation>(new UIMAAnnotationOffsetComparator());
        for (FSIterator ann_it = ann_idx.subiterator(enclosing); ann_it.hasNext();) {
            Annotation ann = (Annotation)ann_it.next();
            if (ann instanceof NPAnnotation) {
                curr_np = (NPAnnotation)ann;
            }
            else if (ann instanceof NPPAnnotation || ann instanceof NPListAnnotation) {
                if ((null == curr_enclosingNP) || !belongs(ann, curr_enclosingNP)) {
                    curr_enclosingNP = ann;
                    continue;
                }
            }
            else if (!(ann instanceof TokenAnnotation)) {
                continue;
            }
            
            if (null == curr_np) {
                continue;
            }
            if ((null != curr_enclosingNP) && belongs(ann, curr_enclosingNP)) {
                continue;
            }
            if ((null != curr_np) && (ann != curr_np) && belongs(ann, curr_np)) {
                continue;
            }
            if (ann instanceof NPAnnotation) {
                pattern += "_NP_";
                patternAnnotations.add(ann);
                
            }
            else if (ann instanceof TokenAnnotation) {
                String txt = ann.getCoveredText().toLowerCase();
                pattern += txt;
                if (!txt.equals(".")) {
                    patternAnnotations.add(ann);
                }
            }
            else {
                continue;
            }
            if (pattern.endsWith("_NP_and_NP_")) {
                Annotation new_NPC = make_newNPC(jcas, patternAnnotations); 
                newNPCs.add(new_NPC);
                // System.out.println(CommonFeatureMatcher.getDocumentId(jcas, null) + ": created new NPConjunctedAnnotation: " + new_NPC.getBegin() + "|" + new_NPC.getEnd());
                curr_np = null;
                pattern = "";
                patternAnnotations.clear();
            }
            else if (!pattern.endsWith("_NP_and") && !pattern.endsWith("_NP_")) {
                curr_np = null;
                pattern = "";
                patternAnnotations.clear();
            }
        }
        
        for (Iterator<Annotation> npp_it = newNPCs.iterator(); npp_it.hasNext();) {
            Annotation ann = npp_it.next();
            ann.addToIndexes();
        }
    }
    
    
    private void createMissingNPPAnnotations(JCas jcas)
    {
        JFSIndexRepository indxs = jcas.getJFSIndexRepository ();

        AnnotationIndex sa_idx = (AnnotationIndex)indxs.getAnnotationIndex (SentenceAnnotation.typeIndexID);
        
        for (FSIterator it = sa_idx.iterator(); it.hasNext();) {
            createMissingNPPAnnotations(jcas, (SentenceAnnotation)it.next());
        }
    }
    
    private boolean belongs (Annotation ann1, Annotation ann2)
    {
        return (ann1.getBegin() >= ann2.getBegin()) && (ann1.getEnd() <= ann2.getEnd()); 
    }

    private void createMissingNPPAnnotations(JCas jcas, SentenceAnnotation enclosing)
    {
        JFSIndexRepository indxs = jcas.getJFSIndexRepository ();

        AnnotationIndex ann_idx = (AnnotationIndex)indxs.getAnnotationIndex (Annotation.typeIndexID);
        List<NPPAnnotation> newNPPs = new ArrayList<NPPAnnotation>();
        
        Annotation curr_np = null;
        Annotation curr_enclosingNP = null;
        String pattern = "";
        Set<Annotation> patternAnnotations = new TreeSet<Annotation>(new UIMAAnnotationOffsetComparator());
        for (FSIterator ann_it = ann_idx.subiterator(enclosing); ann_it.hasNext();) {
            Annotation ann = (Annotation)ann_it.next();
            if ((ann instanceof NPAnnotation) || (ann instanceof NPConjunctedAnnotation)) {
                curr_np = (Annotation)ann;
            }
            else if ((ann instanceof NPPAnnotation)) {
                curr_enclosingNP = (NPPAnnotation)ann;
            }
            
            if (null == curr_np) {
                continue;
            }
            if ((null != curr_enclosingNP) && belongs(ann, curr_enclosingNP)) {
                continue;
            }
            if ((null != curr_np) && (ann != curr_np) && belongs(ann, curr_np)) {
                continue;
            }
            if ((ann instanceof NPAnnotation) || (ann instanceof NPConjunctedAnnotation)) {
                pattern += "_NP_";
                patternAnnotations.add(ann);
                
            }
            else if (ann instanceof TokenAnnotation) {
                String txt = ann.getCoveredText().toLowerCase();
                pattern += txt;
            }
            else {
                continue;
            }
            if (pattern.equalsIgnoreCase("_NP_of_NP_")) {
                newNPPs.add(make_newNPP(jcas, patternAnnotations));
                pattern = "";
                patternAnnotations.clear();
                
            }
            else if (!pattern.equalsIgnoreCase("_NP_") && !pattern.equalsIgnoreCase("_NP_of")) {
                pattern = "";
                patternAnnotations.clear();
            }
        }
        
        for (Iterator<NPPAnnotation> npp_it = newNPPs.iterator(); npp_it.hasNext();) {
            Annotation ann = npp_it.next();
            ann.addToIndexes();
        }
    }
    
    NPPAnnotation make_newNPP(JCas jcas, Collection<Annotation> components)
    {
        int b = Integer.MAX_VALUE, e = 0;
        for (Iterator<Annotation> it = components.iterator(); it.hasNext();) {
            Annotation ann = it.next();
            if (ann.getBegin() < b) {
                b = ann.getBegin();
            }
            if (ann.getEnd() > e) {
                e = ann.getEnd();
            }
        }
        // System.out.println("Making new NPP: " + b + "|" + e);
        return new NPPAnnotation(jcas, b, e);
    }
    
    NPConjunctedAnnotation make_newNPC(JCas jcas, Collection<Annotation> components)
    {
        int b = Integer.MAX_VALUE, e = 0;
        for (Iterator<Annotation> it = components.iterator(); it.hasNext();) {
            Annotation ann = it.next();
            if (ann.getBegin() < b) {
                b = ann.getBegin();
            }
            if (ann.getEnd() > e) {
                e = ann.getEnd();
            }
        }
        return new NPConjunctedAnnotation(jcas, b, e);
    }
}
