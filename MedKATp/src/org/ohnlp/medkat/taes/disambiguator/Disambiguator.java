//*********************************************************************
//* IBM Confidential                                                  *
//* OCO Source Materials                                              *
//* Package: com.ibm.uima.cas                                         *
//* (C) Copyright IBM Corp. 2003                                      *
//* The source code for this program is not published or              *
//* otherwise divested of its trade secrets, irrespective of          *
//* what has been deposited with the US Copyright Office.             *
//*********************************************************************
package org.ohnlp.medkat.taes.disambiguator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Vector;


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
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.FSTypeConstraint;
import org.apache.uima.cas.Feature;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.TypeSystem;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.cas.CAS;
import org.apache.uima.jcas.JFSIndexRepository;
import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.ohnlp.medkat.common.UIMAAnnotationOffsetComparator;
import org.ohnlp.medkat.common.UIMAAnnotationUtils;
import org.ohnlp.medkat.common.annotationMapper;
import org.ohnlp.medkat.taes.conceptMapper.DictTerm;
import org.ohnlp.medkat.taes.dimensionAnnotator.DimensionAnnotator;
import org.ohnlp.medkat.taes.dimensionAnnotator.DimensionSetAnnotation;
import org.ohnlp.medkat.taes.dimensionAnnotator.RangeAnnotation;
import org.ohnlp.medkat.taes.sectionFinder.DiagnosisAnnotation;

/**
 * @author Anni Coden (<a href="mailto:anni@us.ibm.com">anni@us.ibm.com</a>)
 */

public class Disambiguator extends Annotator_ImplBase implements TextAnnotator
{
    public static final String MESSAGE_DIGEST       = "tae.disambiguator_Messages";

    private static final String PARAM_SITE_ANNOTATION_NAME = "siteAnnotationName";
    private static final String PARAM_SPAN_ANNOTATION_NAME = "spanAnnotationName";
    private static final String PARAM_NPP_ANNOTATION_NAME  = "nppAnnotationName";
    private static final String PARAM_NP_ANNOTATION_NAME   = "npAnnotationName";
    //private static final String PARAM_PP_ANNOTATION_NAME   = "ppAnnotationName";
    //private static final String PARAM_EXCLUDING_PREPS = "ExcludingPrepositions";

    // public static final String CONTAIN_ANNOTATION_NAME =
    // "containAnnotationName";

    public Vector<Integer>              spanStart, spanEnd;
    public Vector<AnnotationFS>              spanAnnotation;
    public int[]               spanNumberContain;

    private String             siteAnnotationName;
    private String             spanAnnotationName;
    private String             NPPAnnotationName;
    private String             NPAnnotationName;
    //private String             PPAnnotationName;
    // String containAnnotationName;

    private Class<? extends Annotation> dictTermAnnotationClass;
    private Class<? extends Annotation> subsectionAnnotationClass;
    private Class<? extends Annotation> spanAnnotationClass;
    private Class<? extends Annotation> nppAnnotationClass;
    private Class<? extends Annotation> npAnnotationClass;
    //private Class<? extends Annotation> ppAnnotationClass;
    private Class<? extends Annotation> siteAnnotationClass;
    private Class<? extends Annotation> sizeAnnotationClass;

    //private Class tokenAnnotationClass;

    private Type               spanAnnotationType;
    private Type               containAnnotationType;

    private Type               marginType;
    private Feature            marginDimensionFeature;

    private Type               sizeType;
    private Feature            sizeTypeDimensionFeature;

    private Type               otherType;
    private Feature            otherTypeDimensionFeature;

    private AnnotationIndex    dictTermIndex;

    private Map<?, ?>                size2NP_Map;
    private Map<?, ?>                size2NPP_Map;
    private Map<?, ?>                size2sentenceMap;
    private Map<?, ?> size2subsectionMap;;

    //private Map                site2sentenceMap;
    //private Map                pp2sentenceMap;

    private Map<Annotation, Collection<Annotation>> np2sitesMap;
    private Map<Annotation, Collection<Annotation>> npp2sitesMap;
    private Map<Annotation, Collection<Annotation>> npp2marginsMap;
    private Map<Annotation, Collection<Annotation>> np2marginsMap;

    //private Map sentence2ppMap;

    //private Map pp2tokenMap;

    
    //private Set exclusionPreps;

    Annotation diagnosisSectionAnnotation;

    private String subsectionAnnotationName;





    /**
     * Performs any startup tasks required by this annotator. This
     * implementation reads the configuration parmaeters and compiles the
     * regular expressions.
     * 
     * @see org.apache.uima.analysis_engine.annotator.TextAnnotator#initialize(AnnotatorContext)
     */
    public void initialize (AnnotatorContext annotatorContext)
            throws AnnotatorConfigurationException,
            AnnotatorInitializationException
    {
        super.initialize(annotatorContext);

        try 
        {
            // Retrieve configuration parameters
            // spanAnnotationName identifies annotation type whose span is
            // checked whether
            // it contains annotation types specified containAnnotationNames
            spanAnnotationName = (String) getContext().getConfigParameterValue (PARAM_SPAN_ANNOTATION_NAME);
            siteAnnotationName = (String) getContext().getConfigParameterValue (PARAM_SITE_ANNOTATION_NAME);
            //PPAnnotationName = (String) getContext().getConfigParameterValue (PARAM_PP_ANNOTATION_NAME);
            NPAnnotationName = (String) getContext().getConfigParameterValue (PARAM_NP_ANNOTATION_NAME);
            NPPAnnotationName = (String) getContext().getConfigParameterValue (PARAM_NPP_ANNOTATION_NAME);
            subsectionAnnotationName = "org.ohnlp.medkat.taes.subsectionDetector.SubHeading";
            
            //ppAnnotationClass = UIMAAnnotationUtils.forName (PPAnnotationName);
            npAnnotationClass = UIMAAnnotationUtils.forName (NPAnnotationName);
            nppAnnotationClass = UIMAAnnotationUtils.forName (NPPAnnotationName);
            siteAnnotationClass = UIMAAnnotationUtils.forName (siteAnnotationName);
            spanAnnotationClass = UIMAAnnotationUtils.forName (spanAnnotationName);
            subsectionAnnotationClass = UIMAAnnotationUtils.forName(subsectionAnnotationName);
            dictTermAnnotationClass = UIMAAnnotationUtils.forName("org.ohnlp.medkat.taes.conceptMapper.DictTerm");
            //tokenAnnotationClass = UIMAAnnotationUtils.forName ("uima.tt.TokenAnnotation");
            sizeAnnotationClass = UIMAAnnotationUtils.forName("org.ohnlp.medkat.taes.dimensionAnnotator.RangeAnnotation");

            //exclusionPreps = ParameterProcessor.paramArrayToSet(annotatorContext, PARAM_EXCLUDING_PREPS, true);

            // containAnnotationName specifies the annotation types which are
            // checked whether
            // they are fully contained in the annotation defined by
            // spanAnnotationName
            /*
             * containAnnotationName =(String)
             * getContext().getConfigParameterValue(CONTAIN_ANNOTATION_NAME);
             */

        }
        catch (AnnotatorContextException e)
        {
            throw new AnnotatorInitializationException(e);
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
            throw new AnnotatorInitializationException(e);
        }
    }

    /**
     * Acquires references to CAS Type and Feature objects that are later used
     * during the {@link #process(CAS,ResultSpecification)} method.
     * 
     * @see org.apache.uima.analysis_engine.annotator.JTextAnnotator#typeSystemInit(TypeSystem)
     */
    public void typeSystemInit (TypeSystem typeSystem)
            throws AnnotatorInitializationException
    {
        spanAnnotationType = typeSystem.getType(spanAnnotationName);
        // containAnnotationType = aTypeSystem.getType(containAnnotationName);

    }

    static public SizeDimensionAnnotation createSizeDimensionAnnotation (JCas jcas,
                                                                         OtherDimensionAnnotation other)
    {
        SizeDimensionAnnotation sda = new SizeDimensionAnnotation(jcas, other.getBegin(), other.getEnd());
        sda.setRange(other.getRange());
        sda.addToIndexes();
        return sda;
    }

    static public int compare (DimensionAnnotationBase dab1,
                               DimensionAnnotationBase dab2)
    {
        return DimensionAnnotator.compare(dab1.getRange().getHigh(), dab2.getRange().getHigh());
    }

    public void process (CAS aTCAS, ResultSpecification aResultSpec)
            throws AnnotatorProcessException
    {

        try 
        {
            JCas jcas = aTCAS.getJCas();

            //System.err.println("===> PROCESSING DOCUMENT: '" + DocumentURI.get(jcas) + "'");

            marginType = jcas.getCasType (MarginAnnotation.type);
            marginDimensionFeature = marginType.getFeatureByBaseName ("range");
            otherType = jcas.getCasType (OtherDimensionAnnotation.type);
            otherTypeDimensionFeature = otherType.getFeatureByBaseName ("range");
            sizeType = jcas.getCasType (TumorSizeAnnotation.type);
            sizeTypeDimensionFeature = sizeType.getFeatureByBaseName ("range");
            containAnnotationType = jcas.getCasType (RangeAnnotation.type);

            JFSIndexRepository indexes = jcas.getJFSIndexRepository ();
            dictTermIndex = ((AnnotationIndex)indexes.getAnnotationIndex (DictTerm.typeIndexID));
            diagnosisSectionAnnotation = findDiagnosisSectionAnnotation (jcas);
            // make sure is real path report
            if (diagnosisSectionAnnotation == null) 
            {
                //System.err.println ("No diagnosisSectionAnnotation found!");
                return;
            }
            mapPhrasalUnits (jcas, diagnosisSectionAnnotation);

        }
        catch (CASException e1)
        {
            e1.printStackTrace();
        }

        int start, end;
        int i = 0;

        spanStart = new Vector<Integer>();
        spanEnd = new Vector<Integer>();
        spanAnnotation = new Vector<AnnotationFS>();
        Vector<RangeAnnotation> discard = new Vector<RangeAnnotation>();
        Vector<AnnotationFS> toAdd = new Vector<AnnotationFS>();

        int indicator;

        AnnotationFS temp;

        AnnotationFS spanAnnot;

        FSIterator spanIt = aTCAS.getAnnotationIndex(spanAnnotationType).iterator();
        int counter = 0;
        // determines the start, end of the spanAnnotation
        // example: spanAnnotationis sentence

        for (spanIt.moveToFirst(); spanIt.isValid(); spanIt.moveToNext())
        {
            spanAnnot = (AnnotationFS)spanIt.get ();

            spanStart.addElement(new Integer(spanAnnot.getBegin()));
            spanEnd.addElement(new Integer(spanAnnot.getEnd()));
            counter = counter + 1;
            spanAnnotation.addElement(spanAnnot);
        }

        // initialize the array which contains the annotations
        spanNumberContain = new int[counter];
        for (i = 0; i < counter; i++) 
        {
            spanNumberContain[i] = 0;
        }

        ConstraintFactory cf = ConstraintFactory.instance();
        FSTypeConstraint tokenTypeConstraint = cf.createTypeConstraint();
        tokenTypeConstraint.add(containAnnotationType);

        FSIterator iterator = aTCAS.getAnnotationIndex().iterator();
        if (tokenTypeConstraint != null) 
        {
            iterator = aTCAS.createFilteredIterator(iterator, tokenTypeConstraint);

            while (iterator.isValid()) 
            {
                RangeAnnotation next = (RangeAnnotation)iterator.get ();
                start = next.getBegin ();
                end = next.getEnd ();
                indicator = determineSpanNumber(start, end);
                DimensionSetAnnotation highRangeSet = next.getHigh ();
                FSArray dimensions = highRangeSet.getDimensions();
                // FSArray ranges = ((DimensionSetAnnotation) next).getRanges
                // ();

                boolean oneDimensional = ((dimensions != null) && (dimensions.get(1) == null)) ? true : false;

                if ((indicator > 0) && (oneDimensional)) 
                {
                    discard.addElement(next);
                    //System.err.println("MarginType: " + marginType);
                    AnnotationFS annotation = aTCAS.createAnnotation(marginType, start, end);
                    annotation.setFeatureValue(marginDimensionFeature, next);
                    //System.err.println("Creating Margin: " + annotation.getCoveredText() + ", start: " + start + ", end: " + end);
                    toAdd.add(annotation);
                }
                else 
                {
                    // if more than 1 dimension, or is not in NP or NPP with site, is tumor size
                    if (inDiagnosisSection (next) && 
                        ((!oneDimensional) ||
                         ((! phrasalUnitContainsTerm (next, np2sitesMap, npp2sitesMap)) && (! phrasalUnitContainsTerm (next, np2marginsMap, npp2marginsMap)) && (! inGrossDescription (next)))))
                    {
                        AnnotationFS annotation = aTCAS.createAnnotation(sizeType, start, end);
                        annotation.setFeatureValue(sizeTypeDimensionFeature, next);
                        //System.err.println("Creating Size Dimension Type: " + annotation.getCoveredText() + ", start: " + start + ", end: " + end);
                        toAdd.add(annotation);
                    }
                    // only 1 dimension--can be distance
                    else 
                    {
                        AnnotationFS annotation = aTCAS.createAnnotation(otherType, start, end);
                        annotation.setFeatureValue(otherTypeDimensionFeature, next);
                        //System.err.println("Creating Other Dimension Type: " + annotation.getCoveredText() + ", start: " + start + ", end: " + end);
                        toAdd.add(annotation);
                    }
                }
                iterator.moveToNext();
            }
            Iterator<AnnotationFS> toAddIter = toAdd.iterator();
            while (toAddIter.hasNext())
            {
                AnnotationFS annotation = toAddIter.next();
                aTCAS.getIndexRepository().addFS(annotation);
            }
        }

        Enumeration<RangeAnnotation> e = discard.elements();
        while (e.hasMoreElements())
        {
            temp = e.nextElement();
            aTCAS.getIndexRepository().removeFS(temp);
        }
    }

    private boolean inGrossDescription (Annotation size)
    {
        Annotation sentence = (Annotation) size2sentenceMap.get(size);
        //System.err.println ("sentence == " + sentence);
        Annotation subsection = (Annotation) size2subsectionMap.get(size);
        //System.err.println ("subsection == " + subsection);
        
        if ((sentence != null) && (subsection != null) && (sentence.getBegin() == subsection.getBegin()))
        {
            //System.err.println ("inGrossDescription == true");
            return true;
        }
        //System.err.println ("inGrossDescription == false");        
        return false;
    }

    private boolean inDiagnosisSection (Annotation size)
    {
        return ((size.getBegin() >= diagnosisSectionAnnotation.getBegin()) &&
                (size.getEnd() <= diagnosisSectionAnnotation.getEnd()));
    }

    
    private boolean phrasalUnitContainsTerm (Annotation size, Map<Annotation, Collection<Annotation>> np2sitesMap2, Map<Annotation, Collection<Annotation>> npp2sitesMap2)
    {
        //System.err.println ("phrasalUnitContainsTerm, size: " + size.getCoveredText());
        Object np = size2NP_Map.get (size);
        //System.err.println ("phrasalUnitContainsTerm, np: " + np);
        Object npp = size2NPP_Map.get (size);
        //System.err.println ("phrasalUnitContainsTerm, npp: " + npp);
        
        if ((np != null) && np2sitesMap2.containsKey(np))
        {
            Collection<?> sites = np2sitesMap2.get(np);
            //System.err.println ("phrasalUnitContainsTerm, np2termMap.containsKey, sites = " + sites);
            //Iterator siteIter = sites.iterator();
            //while (siteIter.hasNext())
            //{
            //    Annotation site = (Annotation) siteIter.next();
            //    System.err.println ("phrasalUnitContainsTerm, np2term: " + site.getCoveredText());
            //}
            if (! sites.isEmpty())
            {
                //System.err.println ("phrasalUnitContainsTerm, TRUE");
                return true;
            }
        }
        if ((npp != null) && npp2sitesMap2.containsKey(npp))
        {            
            Collection<?> sites = npp2sitesMap2.get(npp);
            //System.err.println ("phrasalUnitContainsTerm, npp2term.containsKey, sites = " + sites);
            //Iterator siteIter = sites.iterator();
            //while (siteIter.hasNext())
            //{
            //    Annotation site = (Annotation) siteIter.next();
            //    System.err.println ("phrasalUnitContainsTerm, npp2term: " + site.getCoveredText());
            //}
            if (! sites.isEmpty())
            {
                //System.err.println ("phrasalUnitContainsTerm, TRUE");
                return true;
            }
        }
        //System.err.println ("phrasalUnitContainsTerm, FALSE");
        return false;
    }

    private Annotation findDiagnosisSectionAnnotation (JCas jcas)
    {
        AnnotationIndex index = (AnnotationIndex) jcas.getJFSIndexRepository ().getAnnotationIndex (DiagnosisAnnotation.type);
        FSIterator iter = index.iterator ();
        if (iter.hasNext ()) 
        {
            Annotation doc = (Annotation) iter.next ();
            return doc;
        }
        return null;
    }

    private void mapPhrasalUnits (JCas jcas, Annotation diagnosisSectionAnnotation)
    {
        size2NP_Map = annotationMapper.fillMap(jcas, diagnosisSectionAnnotation, npAnnotationClass, sizeAnnotationClass);
        size2NPP_Map = annotationMapper.fillMap(jcas, diagnosisSectionAnnotation, nppAnnotationClass, sizeAnnotationClass);
        size2sentenceMap = annotationMapper.fillMap(jcas, diagnosisSectionAnnotation, spanAnnotationClass, sizeAnnotationClass);
        size2subsectionMap = annotationMapper.fillMap(jcas, diagnosisSectionAnnotation, subsectionAnnotationClass, sizeAnnotationClass);
        //site2SentenceMap = annotationMapper.fillMap(jcas, documentAnnotation, spanAnnotationClass, siteAnnotationClass);
        //pp2sentenceMap = annotationMapper.fillMap(jcas, diagnosisSectionAnnotation, spanAnnotationClass, ppAnnotationClass);
        npp2sitesMap = annotationMapper.fillOneToManyMap(jcas, diagnosisSectionAnnotation, nppAnnotationClass, siteAnnotationClass);
        np2sitesMap = annotationMapper.fillOneToManyMap(jcas, diagnosisSectionAnnotation, npAnnotationClass, siteAnnotationClass);
        //pp2tokenMap = annotationMapper.fillOneToManyMap (jcas, diagnosisSectionAnnotation, ppAnnotationClass, tokenAnnotationClass);
        //sentence2ppMap = annotationMapper.fillOneToManyMap (jcas, diagnosisSectionAnnotation, spanAnnotationClass, ppAnnotationClass);
        fillMarginMaps (jcas);
    }
    
    


    private void fillMarginMaps (JCas jcas)
    {
        np2marginsMap = annotationMapper.fillOneToManyMap(jcas, diagnosisSectionAnnotation, npAnnotationClass, dictTermAnnotationClass);
        npp2marginsMap = annotationMapper.fillOneToManyMap(jcas, diagnosisSectionAnnotation, nppAnnotationClass, dictTermAnnotationClass);
        np2marginsMap = removeNonMargins (np2marginsMap);
        npp2marginsMap = removeNonMargins (npp2marginsMap);
    }

    private Map<Annotation, Collection<Annotation>> removeNonMargins (Map<Annotation, Collection<Annotation>> np2marginsMap)
    {
        Map<Annotation, Collection<Annotation>> result = new TreeMap<Annotation, Collection<Annotation>>(new UIMAAnnotationOffsetComparator ());
        if (np2marginsMap != null)
        {
            Set<Annotation> keys = np2marginsMap.keySet();
            Iterator<Annotation> iter = keys.iterator();
            while (iter.hasNext())
            {
                
                Annotation phrase = iter.next();
                Collection<?> terms = np2marginsMap.get (phrase);
                Collection<Annotation> newTerms = new ArrayList<Annotation> ();
                if (terms != null)
                {
                    Iterator<?> termIter = terms.iterator();
                    while (termIter.hasNext())
                    {
                        DictTerm term = (DictTerm) termIter.next();
                        if (term.getSemClass().equals("Margin"))
                        {
                            newTerms.add (term);
                        }
                    }
                }
                if (! newTerms.isEmpty())
                {
                    result.put(phrase, newTerms);
                }
            }
        }
        return result;
    }

    /*
    private boolean precedesExclusionaryPP (Annotation size)
    {
        Annotation sentence = (Annotation) size2sentenceMap.get (size);
        Collection pps = (Collection) sentence2ppMap.get (sentence);
        if (pps != null)
        {
            Iterator ppIter = pps.iterator();
            while (ppIter.hasNext())
            {
                Annotation pp = (Annotation) ppIter.next();
                if ((! inclusivePP(pp)) && (size.getBegin() < pp.getBegin()))
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
            List tokens = (List)pp2tokenMap.get(pp);
            
            if (tokens != null)
            {
                String token = ((Annotation) tokens.get(0)).getCoveredText().toLowerCase();
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

    */

    public int determineSpanNumber (int start, int end)
    {

        // determine if an annotation specified by its start and end
        // is in any span which this annotator is interested in
        // the spanAnnotations are numbered starting with 1
        int count = 0;
        int spanStartNumber = 0;
        int spanEndNumber = 0;
        boolean done = false;
        Enumeration<Integer> e = spanEnd.elements();
        int indicator = -1;

        // cycle through the end values of the specified spans
        while ((!done) && e.hasMoreElements())
        {
            spanEndNumber = e.nextElement().intValue();

            if (end <= spanEndNumber && count < spanStart.size() && count >= 0)
            {
                // check whether the start is bigger than the
                // start of this span
                done = true;

                spanStartNumber = (spanStart.elementAt(count)).intValue();

                if (spanStartNumber > start)
                {
                    count = -1;
                }
            }
            else
            {
                count = count + 1;
            }
        }

        if (count < spanAnnotation.size() && count >= 0)
        {
            AnnotationFS span = (spanAnnotation.elementAt(count));

            FSIterator dictTermIter = dictTermIndex.subiterator(span);
            while ((indicator == -1) && dictTermIter.hasNext())
            {
                DictTerm dictTerm = (DictTerm)dictTermIter.next();
                if (dictTerm.getSemClass().equals("Margin"))
                {
                    indicator = dictTerm.getBegin();
                }
            }
        }
        return indicator;
    }

}
