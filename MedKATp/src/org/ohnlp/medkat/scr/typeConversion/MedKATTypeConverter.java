package org.ohnlp.medkat.scr.typeConversion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.tcas.Annotation;
import org.ohnlp.medkat.common.UIMAAnnotationUtils;
import org.ohnlp.medkat.scr.types.SCRAnatomicalSite;
import org.ohnlp.medkat.scr.types.SCRCoreference;
import org.ohnlp.medkat.scr.types.SCRDate;
import org.ohnlp.medkat.scr.types.SCRDimension;
import org.ohnlp.medkat.scr.types.SCRGradeValue;
import org.ohnlp.medkat.scr.types.SCRHistologicalDiagnosis;
import org.ohnlp.medkat.scr.types.SCRNamedEntity;
import org.ohnlp.medkat.scr.types.SCRNamedEntityBase;
import org.ohnlp.medkat.scr.types.SCRSize;
import org.ohnlp.medkat.scr.types.SCRSpannedAnnotation;
import org.ohnlp.medkat.taes.conceptMapper.DictTerm;
import org.ohnlp.medkat.taes.conceptMapper.DictTermMarkers;
import org.ohnlp.medkat.taes.coreferencer.CorefAnnotation;
import org.ohnlp.medkat.taes.dimensionAnnotator.DimensionAnnotation;
import org.ohnlp.medkat.taes.dimensionAnnotator.DimensionSetAnnotation;
import org.ohnlp.medkat.taes.disambiguator.DimensionAnnotationBase;
import org.ohnlp.medkat.taes.gradeDetector.GradeAnnotation;
import org.ohnlp.medkat.taes.support.dateFinder.DateAnnotation;


public class MedKATTypeConverter
{
    private static boolean isSameSpanNE (SCRNamedEntityBase neBase, DictTerm dterm)
    {
        if ((dterm.getBegin() != neBase.getBegin()) || (dterm.getEnd() != neBase.getEnd())) {
            return false;
        }
        FSArray dtSpans = dterm.getMatchedTokens();
        FSArray neSpans = neBase.getFragments();
        
        if ((0 == dtSpans.size()) ^ ((null == neSpans) || (0 == neSpans.size()))) {
            return false;
        }
        if (dtSpans.size() != neSpans.size()) {
            return false;
        }
        
        for (int i = 0; i < dtSpans.size(); ++i) {
            Annotation dtSpan = (Annotation)dtSpans.get(i); 
            Annotation neSpan = (Annotation)neSpans.get(i);
            if (!UIMAAnnotationUtils.sameSpanAnnotations(dtSpan, neSpan)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isSameNEBase (SCRNamedEntityBase neBase, DictTerm dterm)
    {
        if (!isSameSpanNE(neBase, dterm)) {
            return false;
        }
        if (!neBase.getTerminology().equals(dterm.getAttributeType())) {
            return false;
        }
        return neBase.getCode().equals(dterm.getAttributeValue());
    }
    
    private static boolean isSameNE (SCRNamedEntity ne, DictTerm dterm)
    {
        if (!ne.getSemanticClass().equals(dterm.getSemClass())) {
            return false;
        }
        return isSameNEBase(ne, dterm);
    }
    

    private static void convertNEBase (JCas jcas, SCRNamedEntityBase neBase, DictTerm dterm)
    {
        neBase.setTerminology(dterm.getAttributeType());
        neBase.setCode(dterm.getAttributeValue());
        neBase.setNegation(DictTermMarkers.isMarkedAsNegated(dterm) ? 1 : 0);
        neBase.setFragments(dterm.getMatchedTokens());
    }

    private static SCRAnatomicalSite convertAS (JCas jcas, DictTerm dterm)
    {
        List<SCRAnatomicalSite> scr_ASs = UIMAAnnotationUtils.getSameOffsetAnnotations(jcas, SCRAnatomicalSite.class, dterm);
        for (SCRAnatomicalSite scr_AS : scr_ASs) {
            if (isSameNEBase(scr_AS, dterm)) {
                return scr_AS;
            }
        }
        SCRAnatomicalSite scr_AS = new SCRAnatomicalSite(jcas, dterm.getBegin(), dterm.getEnd());
        convertNEBase(jcas, scr_AS, dterm);
        scr_AS.addToIndexes();
        return scr_AS; 
    }
    
    private static SCRHistologicalDiagnosis convertHD (JCas jcas, DictTerm dterm)
    {
        List<SCRHistologicalDiagnosis> scr_HDs = UIMAAnnotationUtils.getSameOffsetAnnotations(jcas, SCRHistologicalDiagnosis.class, dterm);
        for (SCRHistologicalDiagnosis scr_HD : scr_HDs) {
            if (isSameNEBase(scr_HD, dterm)) {
                return scr_HD;
            }
        }
        SCRHistologicalDiagnosis scr_HD = new SCRHistologicalDiagnosis(jcas, dterm.getBegin(), dterm.getEnd());
        convertNEBase(jcas, scr_HD, dterm);
        scr_HD.addToIndexes();
        return scr_HD; 
    }

    public static SCRNamedEntity convertNE (JCas jcas, DictTerm dterm)
    {
        int allowedMarks = DictTermMarkers.METASTATIC_INDICATOR |
                           DictTermMarkers.MODIFIER_INDICATOR |
                           DictTermMarkers.NEGATED_INDICATOR;
        if (DictTermMarkers.isMarked(dterm) &&
            DictTermMarkers.isAnyMarkedAs(dterm, ~allowedMarks)) {
            return null;
        }
        
        List<SCRNamedEntity> scr_NEs = UIMAAnnotationUtils.getSameOffsetAnnotations(jcas, SCRNamedEntity.class, dterm);
        for (SCRNamedEntity scr_NE : scr_NEs) {
            if (isSameNE(scr_NE, dterm)) {
                return scr_NE;
            }
        }
        SCRNamedEntity scr_NE = new SCRNamedEntity(jcas, dterm.getBegin(), dterm.getEnd());
        convertNEBase(jcas, scr_NE, dterm);
        scr_NE.setSemanticClass(dterm.getSemClass());
        scr_NE.setMetastatic(DictTermMarkers.isMarkedAsMetastatic(dterm) ? 1 : 0);
        scr_NE.setModifier(DictTermMarkers.isMarkedAsModifer(dterm) ? 1 : 0);
        scr_NE.addToIndexes();
        return scr_NE; 
    }

    private static SCRSpannedAnnotation convert (JCas jcas, DictTerm dterm)
    {
        if (null == dterm) {
            return null;
        }
        else if (dterm.getSemClass().equalsIgnoreCase("diagnosis")) {
            return convertHD(jcas, dterm);
        }
        else if (dterm.getSemClass().equalsIgnoreCase("site")) {
            return convertAS(jcas, dterm);
        }
       return null;
    }
    
    public static SCRCoreference convert (JCas jcas, CorefAnnotation coref)
    {
        SCRCoreference scr_cr = new SCRCoreference(jcas);
        
        List<Annotation> coref_objects = new ArrayList<Annotation>();
        FSArray elements = coref.getElements();
        for (int i = 0; i < elements.size(); ++i) {
            Annotation ann = convert(jcas, (DictTerm)elements.get(i));
            coref_objects.add(ann);
            UIMAAnnotationUtils.addAnnotationsToFSArray(
                    jcas, ann, "Coreferences", Arrays.asList(new Annotation[]{scr_cr}), false);
        }
        UIMAAnnotationUtils.addAnnotationsToFSArray(
                jcas, scr_cr, "Elements", coref_objects, true);
        scr_cr.addToIndexes();
        return scr_cr;
    }
    
/*
    public static SCRCoreference convert (JCas jcas, CoreferringDiagnoses coref)
    {
        SCRCoreference scr_cr = new SCRCoreference(jcas);
        
        List coref_objects = new ArrayList();
        FSArray elements = coref.getElements();
        for (int i = 0; i < elements.size(); ++i) {
            Annotation ann = convertHD(jcas, (DictTerm)elements.get(i));
            coref_objects.add(ann);
            UIMAAnnotationUtils.addAnnotationToFSArray(
                    jcas, ann, "Coreferences", Arrays.asList(new Object[]{scr_cr}), false);
        }
        UIMAAnnotationUtils.addAnnotationToFSArray(
                jcas, scr_cr, "Elements", coref_objects, true);
        scr_cr.addToIndexes();
        return scr_cr;
        
    }
    
    public static SCRCoreference convert (JCas jcas, CoreferringSites coref)
    {
        SCRCoreference scr_cr = new SCRCoreference(jcas);
        
        List coref_objects = new ArrayList();
        FSArray elements = coref.getElements();
        for (int i = 0; i < elements.size(); ++i) {
            Annotation ann = convertAS(jcas, (DictTerm)elements.get(i));
            coref_objects.add(ann);
            UIMAAnnotationUtils.addAnnotationToFSArray(
                    jcas, ann, "Coreferences", Arrays.asList(new Object[]{scr_cr}), false);
        }
        UIMAAnnotationUtils.addAnnotationToFSArray(
                jcas, scr_cr, "Elements", coref_objects, true);
        scr_cr.addToIndexes();
        return scr_cr;
    }
*/    
    public static SCRSize[] convert (JCas jcas, DimensionAnnotationBase dab)
    {
        List<DimensionSetAnnotation> dsas = new ArrayList<DimensionSetAnnotation>();
        DimensionSetAnnotation dsa = dab.getRange().getHigh();
        if (null != dsa) {
            dsas.add(dsa);
        }
        dsa = dab.getRange().getLow();
        if (null != dsa) {
            dsas.add(dsa);
        }
        if (0 == dsas.size()) {
            return null;
        }
        SCRSize[] scr_sz = new SCRSize[dsas.size()];
        for (int i = 0; i < dsas.size(); ++i) {
            scr_sz[i] = convert(jcas, dsas.get(i));
        }
        return scr_sz;
    }

    private static SCRDimension convert (JCas jcas, DimensionAnnotation da)
    {
        SCRDimension scr_dim = (SCRDimension)UIMAAnnotationUtils.getSameOffsetAnnotation(
                jcas, SCRDimension.class, da);
        if (null != scr_dim) {
            return scr_dim;
        }

        scr_dim = new SCRDimension(jcas, da.getBegin(), da.getEnd());
        scr_dim.setExtent(da.getValue().getCoveredText());
        scr_dim.setUnit(da.getUnit().getCoveredText());
        scr_dim.addToIndexes();
        return scr_dim;
    }
    
    public static SCRSize convert (JCas jcas, DimensionSetAnnotation dsa)
    {
        FSArray dims = dsa.getDimensions();
        if (0 == dims.size()) {
            return null;
        }
        List<Annotation> scr_dims = new ArrayList<Annotation>();
        for (int i = 0; i < dims.size(); ++i) {
            DimensionAnnotation da = (DimensionAnnotation)dims.get(i);
            if (null == da) {
                continue;
            }
            scr_dims.add(convert(jcas, da)); 
        }

        SCRSize scr_sz = new SCRSize(jcas, dsa.getBegin(), dsa.getEnd());
        UIMAAnnotationUtils.addAnnotationsToFSArray(jcas, scr_sz, "Dimensions", scr_dims, true);
        scr_sz.addToIndexes();
        return scr_sz;
    }
    
    public static SCRDate convert (JCas jcas, DateAnnotation da)
    {
        SCRDate scr_dt = (SCRDate)UIMAAnnotationUtils.getSameOffsetAnnotation (jcas, SCRDate.class, da);
        if (null == scr_dt) {
            scr_dt = new SCRDate(jcas, da.getBegin(), da.getEnd());
            scr_dt.setDay(da.getDay());
            scr_dt.setMonth(da.getMonth());
            scr_dt.setYear(da.getYear());
            scr_dt.addToIndexes();
        }
        return scr_dt;
    }
    
    public static SCRGradeValue convert (JCas jcas, GradeAnnotation ga)
    {
        SCRGradeValue scr_gv = (SCRGradeValue)UIMAAnnotationUtils.getSameOffsetAnnotation (jcas, SCRGradeValue.class, ga);
        if (null == scr_gv) {
            scr_gv = new SCRGradeValue(jcas, ga.getBegin(), ga.getEnd());
            scr_gv.setGradeType(ga.getGradeType());
            scr_gv.setGradeValue(ga.getValue());
            scr_gv.setGradeScale(ga.getMaxValue());
            scr_gv.addToIndexes();
        }
        return scr_gv;
    }
}
