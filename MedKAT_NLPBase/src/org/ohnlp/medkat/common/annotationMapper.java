package org.ohnlp.medkat.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.uima.cas.FSIterator;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;

public class annotationMapper
{
    
    static private FSIterator getSubiterator (JCas jcas, Class<? extends Annotation> enclosed, Annotation enclosing)
    {
        try 
        {  
            return FeatureConstrainedIterator.getEnclosedIterator(jcas, enclosed, enclosing.getBegin(), enclosing.getEnd());        
        }
        catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        catch (SecurityException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return null;
    }


    static public ArrayList<Annotation> getSubAnnotations (JCas jcas, Class<? extends Annotation> enclosed, Annotation enclosing)
    {
        FSIterator annotIter = getSubiterator (jcas, enclosed, enclosing);
        return getSubAnnotationsForIterator(annotIter, new UIMAAnnotationOffsetComparator());
    }

    

    static public ArrayList<Annotation> getSubAnnotationsForIterator (FSIterator annotIter, Comparator<Annotation> comparator)
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
    

    static public Map<Annotation, Annotation> fillMap (JCas jcas,
                               Annotation boundingSpan,
                               Class<? extends Annotation> spanningClass,
                               Class<? extends Annotation> enclosedClass)
    {
        Map<Annotation, Annotation> result = new TreeMap<Annotation, Annotation>(new UIMAAnnotationOffsetComparator());
        Collection<Annotation> spans;
        spans = getSubAnnotations (jcas, spanningClass, boundingSpan);
        
        Iterator<Annotation> spanIter = spans.iterator();
        while (spanIter.hasNext())
        {
            Annotation span = spanIter.next();
            //System.err.println("Adding items for span: '" + span.getCoveredText() + "'");
            result = addItemsForSpanMap (jcas, result, enclosedClass, span);            
        }

        return result;
    }
    
    static public Map<Annotation, Annotation> addItemsForSpanMap (JCas jcas, Map<Annotation, Annotation> map, Class<? extends Annotation> classOfAnnotation, Annotation enclosingSpan)
    {        
        FSIterator annotIter = getSubiterator (jcas, classOfAnnotation, enclosingSpan);

        while (annotIter.hasNext ())
        {
            Annotation annot = (Annotation) annotIter.next ();
            //System.err.println ("\tAdding item annot: '" + annot.getCoveredText() + "'");
            map.put (annot, enclosingSpan);
        }

        return map;
    
    }
    
    


    static public Map<Annotation, Collection<Annotation>> fillOneToManyMap (JCas jcas,
                                  Annotation boundingSpan,
                                  Class<? extends Annotation> enclosingClass,
                                  Class<? extends Annotation> enclosedClass)
    {
        Map<Annotation, Collection<Annotation>> result = new TreeMap<Annotation, Collection<Annotation>>(new UIMAAnnotationOffsetComparator ());
        Collection<Annotation> pps = getSubAnnotations (jcas, enclosingClass, boundingSpan);
        
        Iterator<Annotation> ppIter = pps.iterator();
        while (ppIter.hasNext())
        {
            Annotation span = ppIter.next();
            //System.err.println("Adding tokens for pp: '" + span.getCoveredText() + "'");
            result = addItemsForOneToManyMap (jcas, result, enclosedClass, span);            
        }

        return result;
    }
    
    static private Map<Annotation, Collection<Annotation>> addItemsForOneToManyMap (JCas jcas, Map<Annotation, Collection<Annotation>> map, Class<? extends Annotation> enclosedClass, Annotation oneTo)
    {        
        FSIterator enclosedIter = getSubiterator (jcas, enclosedClass, oneTo);
        List<Annotation> encloseds = new ArrayList<Annotation> ();
        
        while (enclosedIter.hasNext ())
        {
            Annotation enclosed = (Annotation) enclosedIter.next ();
            //System.err.println ("\tAdding item enclosed: '" + enclosed.getCoveredText() + "'");
            encloseds.add (enclosed);
        }
        map.put (oneTo, encloseds);

        return map;
    
    }
    

}
