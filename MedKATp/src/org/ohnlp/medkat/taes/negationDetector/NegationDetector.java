/*
 * $Id$
 */

package org.ohnlp.medkat.taes.negationDetector;


import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;


import org.apache.uima.analysis_engine.ResultSpecification;
import org.apache.uima.analysis_engine.annotator.AnnotatorConfigurationException;
import org.apache.uima.analysis_engine.annotator.AnnotatorContext;
import org.apache.uima.analysis_engine.annotator.AnnotatorContextException;
import org.apache.uima.analysis_engine.annotator.AnnotatorInitializationException;
import org.apache.uima.analysis_engine.annotator.AnnotatorProcessException;
import org.apache.uima.analysis_engine.annotator.Annotator_ImplBase;
import org.apache.uima.analysis_engine.annotator.TextAnnotator;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Feature;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.TypeSystem;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.cas.CAS;
import org.apache.uima.jcas.JFSIndexRepository;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.ohnlp.medkat.taes.conceptMapper.DictTerm;
import org.ohnlp.medkat.taes.conceptMapper.DictTermMarkers;
import org.ohnlp.medkat.taes.npMerger.NPCombinedAnnotation;

/**
 * @version $Revision$
 *
 * @author Anni Coden &lt;mtan@us.ibm.com&gt;
 * @author (c) Copyright 2005 IBM
 * @author All Rights Reserved
 */

public class NegationDetector
    extends Annotator_ImplBase
    implements TextAnnotator
{

    private static final String PARAM_SEMCLASSESTOAPPLY = "SemanticClassesToApplyNegation";
    
    private Set<String> semClassesToApplyNegation = null;
    
    private static final String PARAM_NO_TERM_TYPE = "NoTermType";
    private String NoTermTypeName;
    private Type noTermType;
    
    private static final String PARAM_NO_TERM_ENCLOSING_SPAN_FEATURE = "NoTermEnclosingSpanFeature";
    private String NoTermEnclosingSpanFeatureName;

    private Feature enclosingSpanFeature;
    
    /* (non-Javadoc)
     * @see org.apache.uima.analysis_engine.annotator.Annotator_ImplBase#initialize(org.apache.uima.analysis_engine.annotator.AnnotatorContext)
     */
    public void initialize (AnnotatorContext annotatorContext) throws AnnotatorInitializationException, AnnotatorConfigurationException
    {
        super.initialize (annotatorContext);
        try
        {
            String [] semClassesStrings = (String[]) getContext().getConfigParameterValue(PARAM_SEMCLASSESTOAPPLY);
            semClassesToApplyNegation = new HashSet<String> ();
            for (int i = 0; i < semClassesStrings.length; i++)
            {
                semClassesToApplyNegation.add(semClassesStrings[i]);
            }
            
            NoTermTypeName = (String) getContext ().getConfigParameterValue (PARAM_NO_TERM_TYPE);
            NoTermEnclosingSpanFeatureName = (String) getContext ().getConfigParameterValue (PARAM_NO_TERM_ENCLOSING_SPAN_FEATURE);

        }
        catch (AnnotatorContextException e)
        {
            e.printStackTrace();
            throw new AnnotatorInitializationException ();
        }

    }


    /* (non-Javadoc)
     * @see org.apache.uima.analysis_engine.annotator.Annotator_ImplBase#typeSystemInit(org.apache.uima.cas.TypeSystem)
     */
    public void typeSystemInit (TypeSystem typeSystem) throws AnnotatorInitializationException, AnnotatorConfigurationException
    {
        super.typeSystemInit (typeSystem);
        noTermType = typeSystem.getType (NoTermTypeName);
        enclosingSpanFeature = noTermType.getFeatureByBaseName (NoTermEnclosingSpanFeatureName);
    }


    /* (non-Javadoc)
     * @see org.apache.uima.analysis_engine.annotator.TextAnnotator#process(org.apache.uima.cas.CAS, org.apache.uima.analysis_engine.ResultSpecification)
     */
    public void process (CAS tcas, ResultSpecification resultSpecification)
        throws AnnotatorProcessException
    {
        try
        {
            JCas jcas = tcas.getJCas ();
            JFSIndexRepository indexes = jcas.getJFSIndexRepository ();
            // AnnotationIndex syntacticUnitIndex = (AnnotationIndex) indexes.getAnnotationIndex (SyntacticUnit.typeIndexID);
            // AnnotationIndex noTermIndex = (AnnotationIndex) indexes.getAnnotationIndex (NoTerm.typeIndexID);
            AnnotationIndex dictTermIndex = (AnnotationIndex) indexes.getAnnotationIndex (DictTerm.typeIndexID);
            AnnotationIndex NPChunkIndex = (AnnotationIndex) indexes.getAnnotationIndex(NPCombinedAnnotation.typeIndexID);
    
            AnnotationIndex noTermIndex = (AnnotationIndex) tcas.getAnnotationIndex (noTermType);
    
            FSIterator noTermIterator = noTermIndex.iterator ();
            Annotation prevSpan = null;
            
            // go through all spans that contain negation
            while (noTermIterator.hasNext())
            {
                Annotation noTerm = (Annotation) noTermIterator.next();
                Annotation enclosingSpan = (Annotation) noTerm.getFeatureValue (enclosingSpanFeature);
                
                // only process a sentence once
                if (! enclosingSpan.equals(prevSpan))
                {
                    Set<Annotation> negations = getAnnotationsForSpan (noTermIndex, enclosingSpan);
                    TreeSet<Annotation> negationsSorted = new TreeSet<Annotation>(new AnnotComparator ());
                    negationsSorted.addAll(negations);
                    Set<Annotation> dictTerms = getAnnotationsForSpanFromSet (dictTermIndex, enclosingSpan);
                    Set<Annotation> dictTermsSorted = new TreeSet<Annotation>(new AnnotComparator ());
                    dictTermsSorted.addAll(dictTerms);
                    Set<Annotation> dictTermsLymph = getAnnotationsForSpanLymph (dictTermIndex, enclosingSpan);
                    TreeSet<Annotation> dictTermsLymphSorted = new TreeSet<Annotation>(new AnnotComparator ());
                    dictTermsLymphSorted.addAll(dictTermsLymph);
                    // HashMap syntacticUnits = getAnnotationsForSpan (syntacticUnitIndex, enclosingSpan);
                    //System.err.println("Getting NP Chunks for span, begin: " + enclosingSpan.getStart () + ", end: " + enclosingSpan.getEnd () + ", text: '" + enclosingSpan.getCoveredText () + "'");
                    Set<Annotation> NPChunkUnits = getAnnotationsForSpan (NPChunkIndex, enclosingSpan);
                    TreeSet<Annotation> NPChunksSorted = new TreeSet<Annotation>(new AnnotComparator ());
                    NPChunksSorted.addAll(NPChunkUnits);
                    
                    // FSIterator syntacticUnitIterator = syntacticUnitIndex.subiterator(enclosingSpan);
                    // processSyntacticUnits (syntacticUnitIndex, syntacticUnitIterator, noTermIndex, dictTermIndex, negations, dictTerms, syntacticUnits);
                    if (negations.size() > 0 && dictTerms.size() > 0)
                    {
                    	// there are some negations and Diagnosis 
                        //  processDictTerms (dictTerms, syntacticUnits);
                        processDictTerms (jcas, dictTermsSorted, negationsSorted, NPChunksSorted);
                   //     processDictTermsLymph (jcas, dictTermIndex, dictTermsLymphSorted, negationsSorted, NPChunksSorted);
                    }
                    prevSpan = enclosingSpan;
                }
            }
        }
        catch (CASException ce)
        {
            System.err.println ("Cannot get JCas");
        }
    }


    /**
     * @param dictTerms
     * @param syntacticUnits
     */
    private void processDictTerms (JCas jcas, Set<Annotation> dictTermsSorted, Set<Annotation> negationsSorted, Set<Annotation> NPChunksSorted)
    {
    	int startNoTerm;//, endNoTerm;
        int done = 0;
    	
    	Iterator<Annotation> noTermIterator = negationsSorted.iterator();
    	Iterator<Annotation> NPChunkIterator = NPChunksSorted.iterator();
       
        Annotation noTerm = null;
        Annotation currentNP_Chunk = null;
        if (noTermIterator.hasNext())
        {
            noTerm = noTermIterator.next();
        }
        
    	while (noTerm != null)
        {
            Annotation nextNoTerm = null;
         //   System.out.println("nextNoTerm is set to null");
            if (noTermIterator.hasNext ())
            {
                nextNoTerm = noTermIterator.next();
          //      System.out.println("nextNoTerm = " + nextNoTerm.getCoveredText ());
            }
          //  System.out.println ("****Processing no term: "  + noTerm.getCoveredText ());
            //if (nextNoTerm == null)
            //    System.out.println("nextNoTerm has been set to null");
            // find the first NPChunk which either contains
    	    // contains the noTerm or is the next NP chunk
    		startNoTerm = noTerm.getBegin();
           // System.out.println ("!!!\n!!!> No Term = " + noTerm.getCoveredText () + ", startNo: "  + startNoTerm + ", endNo: " + noTerm.getEnd());

            boolean termFound = false;
           
            Stack<Annotation> prevChunks = new Stack<Annotation> ();
            
            // find chunks prior-to and containing/after NoTerm
    		while ((NPChunkIterator.hasNext() && done !=2) && (! termFound))
            {
                
                currentNP_Chunk = NPChunkIterator.next();
          //      System.out.println("currentNP_Chunk1 = " + currentNP_Chunk.getCoveredText ());
                
                if (startNoTerm >= currentNP_Chunk.getEnd ())
                {
                    prevChunks.push (currentNP_Chunk);
                    //System.err.println("startNoTerm: " + startNoTerm + ", currentNP_Chunk.getEnd ()" + currentNP_Chunk.getEnd ());
              //     System.out.println("PUSHING CHUNK: " + currentNP_Chunk.getCoveredText ());
                }
                else
                {
                    termFound = true;
             //       System.out.println("termfound = true");
                }
            }
            
            //System.err.println ("Searching current/subsequent chunks...");
            // at this point, currentNP_Chunk either contains or follows NoTerm (termFound==true) OR no chunks left (termFound==false)
          //  boolean done = false;
            
            if (termFound || done == 2)
            {
               // done = false;
                done = 0;
            //    System.out.println("step1");
              //  while ((! done) && (currentNP_Chunk != null))
                while ((done == 0) && (currentNP_Chunk != null))
                {
                   // System.out.println("calling FindAndMark");
                    done = findAndMarkTerms (jcas, currentNP_Chunk, dictTermsSorted, nextNoTerm);
                    // if don't find any DictTerms, move to next NPChunk
                //    System.out.println("done1 = " + done);
                   
                //    if (! done)
                    if (done == 0)
                    {
                   //     System.out.println ("get next chunk...");
                        if (NPChunkIterator.hasNext())
                        {
                            currentNP_Chunk = NPChunkIterator.next();   
                    //        System.out.println("currentNP_Chunk3 =  " + currentNP_Chunk.getCoveredText ())
;                        }
                        else
                        {
                            currentNP_Chunk = null;
                        }
                    }
                }
            }
            //else
            //{
            //    System.err.println("TERM NOT FOUND");
            //}
            
            // if no terms negated, look at previous NPChunks
          //  if (!done)
          //  if (done != 1)  CHANGE ARC JULY15
            if (!termFound)
            {
              //  System.out.println ("Searching prior chunks...");
              //  while ((! done) && (! prevChunks.empty ()))
                while ((done != 1) && (! prevChunks.empty ()))
                {
                    currentNP_Chunk = prevChunks.pop ();
                //    System.out.println("calling FindAndMark2");
                    done = findAndMarkTerms (jcas, currentNP_Chunk, dictTermsSorted, null);
                 //   System.out.println("done2 = " + done);
                }
            }
            
            noTerm = nextNoTerm;
            
    	}
        
    }


    /**
     * @param currentNP_Chunk
     * @param dictTermsSorted
     * @return
     */
    private int findAndMarkTerms (JCas jcas, Annotation currentNP_Chunk, Set<Annotation> dictTermsSorted, Annotation nextNoTerm)
    {
       // boolean done = false;
        int done = 0;
        int startNPChunk = currentNP_Chunk.getBegin();
        int end;
        
       // System.out.println("in findAndMark");
        // don't go past next NoTerm, if any
        if (nextNoTerm == null)
        {
            end = currentNP_Chunk.getEnd();
        }
        else
        {
            end = Math.min (currentNP_Chunk.getEnd (), nextNoTerm.getBegin ());
            if (end == nextNoTerm.getBegin ())
            {
                //System.err.println("STOPPING BEFORE NO TERM: " + nextNoTerm.getCoveredText ());
                done = 2;
            }
        }
        
        //System.err.println ("NP Chunk, startNPChunk: " + startNPChunk + ", endNPChunk: " + end);

        // check whether the chunk contains one or more appropriate DictTerms, and if so, mark them, then done
        Iterator<Annotation> dTIterator = dictTermsSorted.iterator();
        int lastBegin = -1;
        while (dTIterator.hasNext())
        {
            Annotation dT = dTIterator.next();
          //  System.out.println ("dictterm: "  + dT.getCoveredText () + ", begin: " + dT.getBegin () + ", end: " + dT.getEnd());
            if ((dT.getBegin() >= startNPChunk) && (dT.getEnd() <= end))
            {
           //     System.out.println ("Negating: " + dT.getCoveredText ());
                if ((dT.getBegin() == lastBegin) || lastBegin == -1){
                   negateTerm (jcas, (DictTerm)dT);
                   lastBegin = dT.getBegin();
                //   System.out.println("last = " + lastBegin);
                }
                else
                    done = 1;
               
               // negateTerm (jcas, dT);
              
               // done = true;
               //  done = 1;
            }
           else
            {
                doNotNegateTerm (jcas, (DictTerm)dT);
            }
        }

        return done;
    }


    protected void negateTerm (JCas jcas, DictTerm dT)
    {
        dT.setMarked(dT.getMarked () | DictTermMarkers.NEGATED_INDICATOR);
    }	
    


    protected void doNotNegateTerm (JCas jcas, DictTerm dT)
    {
        // do nothing
    }   
    

    /**
     * @param dictTerms
     * @param syntacticUnits
     */
    /*
    private void processDictTermsLymph (JCas jcas, AnnotationIndex NPIndex, Set<Annotation> dictTermsLymphSorted, Set<Annotation> negationsSorted, TreeSet<Annotation> NPChunksSorted)
    {
        int startNoTerm;;//, endNoTerm;
        
        Iterator<Annotation> noTermIterator = negationsSorted.iterator();
        Iterator<Annotation> NPChunkIterator = NPChunksSorted.iterator();
       
        Annotation noTerm = null;
        if (noTermIterator.hasNext())
        {
            noTerm = noTermIterator.next();
        }
        
        while (noTerm != null)
        {
            Annotation nextNoTerm = null;
            
            if (noTermIterator.hasNext ())
            {
                nextNoTerm = noTermIterator.next();
            }

            //System.err.println ("****Processing no term (LYMPH): "  + noTerm.getCoveredText ());
            
            // find the first NPChunk which either contains
            // contains the noTerm or is the next NP chunk
            startNoTerm = noTerm.getBegin();
            //System.err.println ("!!!\n!!!> No Term = " + noTerm.getCoveredText () + ", startNo: "  + startNoTerm + ", endNo: " + noTerm.getEnd());

            boolean termFound = false;
            Annotation currentNP_Chunk = null;
            Stack<Annotation> prevChunks = new Stack<Annotation> ();
            
            // find chunks prior-to and containing/after NoTerm
            while (NPChunkIterator.hasNext() && (! termFound))
            {
                currentNP_Chunk = NPChunkIterator.next();
                
                if (startNoTerm >= currentNP_Chunk.getEnd ())
                {
                    prevChunks.push (currentNP_Chunk);
                }
                else
                {
                    termFound = true;
                }
            }
            
            //System.err.println ("Searching current/subsequent chunks...");
            // at this point, currentNP_Chunk either contains or follows NoTerm (termFound==true) OR no chunks left (termFound==false)
       //     boolean done = true;
            int done = 0;
            if (termFound)
            {
             //   done = false;
                done = 0;
          //      while ((! done) && (currentNP_Chunk != null))
                while ((done != 1) && (currentNP_Chunk != null))
                {
                    if (npHasLymph (NPIndex, currentNP_Chunk))
                    {
                        done = findAndMarkTerms (jcas, currentNP_Chunk, dictTermsLymphSorted, nextNoTerm);
                    }
                    // if don't find any DictTerms, move to next NPChunk
                    //System.err.println ("get next chunk...");
                 //   if (! done)
                    if (done != 2)
                    {
                        if (NPChunkIterator.hasNext())
                        {
                            currentNP_Chunk = NPChunkIterator.next();                        
                        }
                        else
                        {
                            currentNP_Chunk = null;
                        }
                    }
                }
            }
            
            // if no terms negated, look at previous NPChunks
       //     if (!done)
            if (done != 1)
            {
                //System.err.println ("Searching prior chunks...");
              //  while ((! done) && (! prevChunks.empty ()))
                while ((done != 1) && (! prevChunks.empty ()))
                {
                    currentNP_Chunk = prevChunks.pop ();
                    if (npHasLymph (NPIndex, currentNP_Chunk))
                    {
                        done = findAndMarkTerms (jcas, currentNP_Chunk, dictTermsLymphSorted, null);
                    }
                }
            }
            noTerm = nextNoTerm;
        }
        
    }
*/

    private Set<Annotation> getAnnotationsForSpan (AnnotationIndex index, Annotation enclosingSpan)
    {
        Set<Annotation> result = new HashSet<Annotation> ();
        FSIterator annotationIterator = index.subiterator (enclosingSpan);
        
        //System.err.println ("Annotations for span: ");
        while (annotationIterator.hasNext())
        {
            Annotation annotation = (Annotation) annotationIterator.next();
            //System.err.println (annotation.getCoveredText ());
            result.add(annotation);
        }
        //System.err.println ("--------");
        return result;
    }
    

    private Set<Annotation> getAnnotationsForSpanFromSet (AnnotationIndex index, Annotation enclosingSpan)
    {
    	DictTerm dT;
    	
        Set<Annotation> result = new HashSet<Annotation> ();
        // retrieve only DictTerms which have a semantic class specified in the TAE descriptor
       
        FSIterator annotationIterator = index.subiterator (enclosingSpan);
        //System.err.println ("Annotations for span from set: ");
        while (annotationIterator.hasNext())
        {
            dT = (DictTerm)(annotationIterator.next());
            
            if ((semClassesToApplyNegation != null) && (semClassesToApplyNegation.contains(dT.getSemClass())))
        	    {
                //System.err.println (dT.getCoveredText () + ", semClass: " + dT.getSemClass ());

        	        result.add(dT);
      
        	    }
        }
        //System.err.println ("--------");
       
        return result;
    }
    
    /*
    private boolean npHasLymph (AnnotationIndex index, Annotation NPSpan)
    {
        String s = "";
        DictTerm dT;
        
        // retrieve only NPChunks which contain a lymph node 
        FSIterator annotationIterator = index.subiterator (NPSpan);
        while (annotationIterator.hasNext())
        {
            dT = (DictTerm)(annotationIterator.next());
            s = dT.getSemClass();
            if (s.compareTo("Lymph") == 0)
            {
               return true;
            }
        }
       
        return false;
    }*/

    private Set<Annotation> getAnnotationsForSpanLymph (AnnotationIndex index, Annotation enclosingSpan)
    {
        String s = "";
        DictTerm dT;
        
        Set<Annotation> result = new HashSet<Annotation> ();
        // retrieve only DictTerms which have a semantic class of
        // Diagnosis
       
        FSIterator annotationIterator = index.subiterator (enclosingSpan);
        while (annotationIterator.hasNext())
        {
            dT = (DictTerm)(annotationIterator.next());
            s = dT.getSemClass();
            
            if (s.compareTo("Lymph") == 0){
                
                result.add(dT);
      
            }
        }
       
        return result;
    }
    /**
     * @param syntacticUnitIterator
     */
//    private void processSyntacticUnits (AnnotationIndex syntacticUnitIndex, FSIterator syntacticUnitIterator, AnnotationIndex noTermIndex, AnnotationIndex dictTermIndex, HashMap negations, HashMap dictTerms, HashMap syntacticUnits)
//    {
//        int numOfNegationsProcessed = 0;
//        while (syntacticUnitIterator.hasNext ())
//        {
//            SyntacticUnit syntacticUnit = (SyntacticUnit) syntacticUnitIterator.next();
//            FSIterator syntacticUnitSubIterator = syntacticUnitIndex.subiterator (syntacticUnit);
//            if (syntacticUnitSubIterator.hasNext())
//            {
//                processSyntacticUnits (syntacticUnitIndex, syntacticUnitSubIterator.copy(), noTermIndex, dictTermIndex, negations, dictTerms, syntacticUnits);
//            }
//            FSIterator noTermIterator = noTermIndex.subiterator (syntacticUnit);
//            
//            while (noTermIterator.hasNext())
//            {
//                Annotation noTerm = (Annotation) noTermIterator.next ();
//                Object val = negations.get(noTerm);
//                if (val == null)
//                {
//                    negations.put(noTerm, syntacticUnit);
//                    numOfNegationsProcessed += 1;
//                    syntacticUnits.put(syntacticUnit, True);
//                }
//            }
//            
//            FSIterator dictTermIterator = dictTermIndex.subiterator (syntacticUnit);
//            while (dictTermIterator.hasNext())
//            {
//                DictTerm dictTerm = (DictTerm) dictTermIterator.next ();
//                Object val = dictTerms.get(dictTerm);
//                if (val == null)
//                {
//                    dictTerms.put(dictTerm, syntacticUnit);
//                }
//            }
//        }
//        // if number of negations processed is less than total for span, then some reside outside of 
//        // all syntactic units, which implies that they are within the main part of the span
//        if (numOfNegationsProcessed < negations.size())
//        {
//            syntacticUnits.put(null, True);
//        }
//    }
    
    public class AnnotComparator
    implements Comparator<Annotation>
{

/* (non-Javadoc)
 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
 */
public int compare (Annotation left, Annotation right)
{
    if (left.getBegin() <= right.getBegin())
    {
        return -1;
    }
    else if (left.getBegin() > right.getBegin())
    {
        return 1;
    }
    else
    {
        return 0;
    }
}

}

}
