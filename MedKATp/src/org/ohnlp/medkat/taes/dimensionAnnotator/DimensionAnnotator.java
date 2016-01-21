package org.ohnlp.medkat.taes.dimensionAnnotator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import org.apache.uima.analysis_engine.ResultSpecification;
import org.apache.uima.analysis_engine.annotator.AnnotatorProcessException;
import org.apache.uima.analysis_engine.annotator.JTextAnnotator_ImplBase;
import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.JCas;
import org.ohnlp.medkat.scr.typeConversion.MedKATTypeConverter;


public class DimensionAnnotator
        extends JTextAnnotator_ImplBase
{
    static final String value = "(\\d+(\\.\\d*)?|\\.\\d+)"; // 2 capture groups
    static final String unit = "([cm]m)"; // 1 capture group
    static final String by = "\\s*x\\s*"; // 0 capture groups
    static final String rangeIndicator = "(?:-|to|and)"; // 0 capture groups

    ////Capturing Group #:                        12               3    45       6                                  78                 9                 AB           CD       E         FG             H                              IJ           KL       M         NO                   P 
    //static final String dimPatternString = "(?i)((<|>|<=|>=)?\\s*(" + value + "(\\s*" + rangeIndicator + "\\s*" + value + ")?\\s*" + unit + "))|(?:" + value + by + value + "(" + by + value + ")?\\s*(" + rangeIndicator + "\\s*" + value + by + value + "(" + by + value + ")?)?\\s*" + unit + ")";

    //Capturing Group #:                        12               3    45               6             7                                  89                 A                 BC           DE       F         GH                 I            J                              KL           MN       O         PQ                   R 
    static final String dimPatternString = "(?i)((<|>|<=|>=)?\\s*(" + value + "\\s*" + unit + "?" + "(\\s*" + rangeIndicator + "\\s*" + value + ")?\\s*" + unit + "))|(?:" + value + by + value + "(" + by + value + ")?\\s*" + unit + "?\\s*(" + rangeIndicator + "\\s*" + value + by + value + "(" + by + value + ")?)?\\s*" + unit + ")";
    static final Pattern dimPattern = Pattern.compile (dimPatternString);
    
    public void process (JCas jcas, ResultSpecification resultSpecification)
        throws AnnotatorProcessException
    {
        String docText = jcas.getDocumentText ();
        Matcher dimMatcher = dimPattern.matcher (docText);

        while (dimMatcher.find ())
        {
            //Capturing Group #: 12               3    45               6             7                                  89                 A          
            //                   ((<|>|<=|>=)?\\s*(" + value + "\\s*" + unit + "?" + "(\\s*" + rangeIndicator + "\\s*" + value + ")?\\s*" + unit + "))
            if (dimMatcher.group(1) != null)
            {

                UnitAnnotation unitAnnotation2 = makeUnitAnnotation (jcas, dimMatcher, 10);
                
                /*System.err.println ("Unit begin: '"
                                    + unitAnnotation.getBegin()
                                    + "', end: '"
                                    + unitAnnotation.getEnd()
                                    + "', Value: '"
                                    + unitAnnotation.getCoveredText ()
                                    +"'");*/
                UnitAnnotation unitAnnotation1 = unitAnnotation2;
                if (dimMatcher.group(6) != null)
                {
                    unitAnnotation1 = makeUnitAnnotation (jcas, dimMatcher, 6);
                }                
                
                DimensionAnnotation dimLeft = makeDimensionAnnotation (jcas, makeExtentAnnotation (jcas, dimMatcher, 4, 2), unitAnnotation1);
                DimensionSetAnnotation dimSetLeft = makeDimensionSetAnnotation (jcas, dimLeft);
                
                DimensionSetAnnotation dimSetRight = null;
                if (dimMatcher.group(7) != null)
                {
                    
                    //System.err.println ("Document: " + DocumentURI.get(jcas) +  "DIMENSION RANGE INDICATOR: " + dimMatcher.group(6));
                    //TODO: clean up this hack: deal with conjoined entries more clearly
                    if (isConjunction (dimMatcher.group (7)))
                    {
                        DimensionAnnotation secondDimLeft = makeDimensionAnnotation (jcas, makeExtentAnnotation (jcas, dimMatcher, 8), unitAnnotation2);
                        DimensionSetAnnotation secondDimSetLeft = makeDimensionSetAnnotation (jcas, secondDimLeft);
                        /*range = */makeRangeAnnotation (jcas, secondDimSetLeft, null);
                    }
                    else
                    {
                        DimensionAnnotation dimRight = makeDimensionAnnotation (jcas, makeExtentAnnotation (jcas, dimMatcher, 8), unitAnnotation2);
                        dimSetRight = makeDimensionSetAnnotation (jcas, dimRight);
                    }
                }
                /*range = */makeRangeAnnotation (jcas, dimSetLeft, dimSetRight);
               
            }

            //Capturing Group #:        BC           DE       F         GH                 I            J                              KL           MN       O         PQ                   R 
            //                   (?:" + value + by + value + "(" + by + value + ")?\\s*" + unit + "?\\s*(" + rangeIndicator + "\\s*" + value + by + value + "(" + by + value + ")?)?\\s*" + unit + ")";
            else if (dimMatcher.group(11) != null)
            {

                DimensionAnnotation leftDim1 = null;
                DimensionAnnotation leftDim2 = null;
                DimensionAnnotation leftDim3 = null;
                UnitAnnotation unitAnnotation2 = null;
                DimensionSetAnnotation dimSetLeft = null;
                DimensionSetAnnotation dimSetRight = null;
                
                if (dimMatcher.group(27) != null)
                {
                    unitAnnotation2 = makeUnitAnnotation (jcas, dimMatcher, 27);
                }                
                UnitAnnotation unitAnnotation1 = unitAnnotation2;
                if (dimMatcher.group(18) != null)
                {
                    unitAnnotation1 = makeUnitAnnotation (jcas, dimMatcher, 18);
                }                
                

                leftDim1 = makeDimensionAnnotation (jcas, makeExtentAnnotation (jcas, dimMatcher, 11), unitAnnotation1);

                if (dimMatcher.group(13) != null)
                {
                    leftDim2 = makeDimensionAnnotation (jcas, makeExtentAnnotation (jcas, dimMatcher, 13), unitAnnotation1);
                    if (dimMatcher.group(16) != null)
                    {
                        leftDim3 = makeDimensionAnnotation (jcas, makeExtentAnnotation (jcas, dimMatcher, 16), unitAnnotation1);
                    }
                }
                dimSetLeft = makeDimensionSetAnnotation (jcas, leftDim1, leftDim2, leftDim3);

                if (dimMatcher.group(19) != null)
                {
                    DimensionAnnotation rightDim1 = null;
                    DimensionAnnotation rightDim2 = null;
                    DimensionAnnotation rightDim3 = null;

                    rightDim1 = makeDimensionAnnotation (jcas, makeExtentAnnotation (jcas, dimMatcher, 20), unitAnnotation2);
                        
                    if (dimMatcher.group(22) != null)
                    {
                        rightDim2 = makeDimensionAnnotation (jcas, makeExtentAnnotation (jcas, dimMatcher, 22), unitAnnotation2);
                        if (dimMatcher.group(24) != null)
                        {
                            rightDim3 = makeDimensionAnnotation (jcas, makeExtentAnnotation (jcas, dimMatcher, 25), unitAnnotation2);
                        }
                    }
                    dimSetRight = makeDimensionSetAnnotation (jcas, rightDim1, rightDim2, rightDim3);
                    //System.err.println ("Document: " + DocumentURI.get(jcas) +  "DIMENSION RANGE INDICATOR: " + dimMatcher.group(18));
                    //TODO: clean up this hack: deal with conjoined entries more clearly
                    if (isConjunction (dimMatcher.group (19)))
                    {
                        /*range = */makeRangeAnnotation (jcas, dimSetRight, null);
                        dimSetRight = null;
                    }
                    
                }
                /*range = */makeRangeAnnotation (jcas, dimSetLeft, dimSetRight);
            }
        }
    }

    private boolean isConjunction (String string)
    {
        if (string.toLowerCase().indexOf("and") != -1)
        {
            return true;
        }
        return false;
    }

    private RangeAnnotation makeRangeAnnotation (JCas jcas, DimensionSetAnnotation dimSetLeft, DimensionSetAnnotation dimSetRight)
    {
        RangeAnnotation range = new RangeAnnotation (jcas);
        range.setBegin (dimSetLeft.getBegin ());
        if (dimSetRight == null)
        {
            range.setHigh (dimSetLeft);
            range.setLow (null);
            range.setEnd (dimSetLeft.getEnd());
        }
        else
        {
            range.setHigh (dimSetRight);
            range.setLow (dimSetLeft);
            range.setEnd (dimSetRight.getEnd ());
        }
        range.addToIndexes ();
        return range;
    }
    
    private DimensionSetAnnotation makeDimensionSetAnnotation (JCas jcas, DimensionAnnotation dim1)
    {
        return makeDimensionSetAnnotation (jcas, dim1, null, null);
    }
    
    /*private DimensionSetAnnotation makeDimensionSetAnnotation (JCas jcas, DimensionAnnotation dim1, DimensionAnnotation dim2)
    {
        return makeDimensionSetAnnotation (jcas, dim1, dim2, null);
    }*/

    private DimensionSetAnnotation makeDimensionSetAnnotation (JCas jcas, DimensionAnnotation dim1, DimensionAnnotation dim2, DimensionAnnotation dim3)
    {
        DimensionSetAnnotation dimensions = new DimensionSetAnnotation (jcas);
        FSArray dims = new FSArray (jcas, 3);
        dims.set (0, dim1);
        dims.set (1, dim2);
        dims.set (2, dim3);
        dimensions.setDimensions (dims);
        dimensions.setBegin (dim1.getBegin ());
        if (dim3 != null)
        {
            dimensions.setEnd (dim3.getEnd ());
        }
        else if (dim2 != null)
        {
            dimensions.setEnd (dim2.getEnd ());
        }
        else
        {
            dimensions.setEnd (dim1.getEnd ());
        }
        dimensions.addToIndexes ();
        
        // do not need to call MedKATTypeConverter.convert(JCas, DimensionAnnotation)
        // since makeDimensionSetAnnotation is always called after creating DimensionAnnotation  
        MedKATTypeConverter.convert(jcas, dimensions);
        return dimensions;

    }

    private DimensionAnnotation makeDimensionAnnotation (JCas jcas, ExtentAnnotation valAnnotation, UnitAnnotation unitAnnotation)
    {
        DimensionAnnotation dimensionAnnotation = new DimensionAnnotation (jcas);
        dimensionAnnotation.setBegin (valAnnotation.getBegin ());
        dimensionAnnotation.setEnd ((unitAnnotation != null) ? unitAnnotation.getEnd () : valAnnotation.getEnd ());
        dimensionAnnotation.setValue (valAnnotation);
        dimensionAnnotation.setUnit (unitAnnotation);
        dimensionAnnotation.addToIndexes ();
        
        // do not need to call MedKATTypeConverter.convert(JCas, DimensionAnnotation)
        // since makeDimensionSetAnnotation is always called after creating DimensionAnnotation  
        return dimensionAnnotation;
    }

    private UnitAnnotation makeUnitAnnotation (JCas jcas, Matcher dimMatcher, int capturingGroup)
    {
        UnitAnnotation unitAnnotation = new UnitAnnotation(jcas);
        unitAnnotation.setBegin (dimMatcher.start (capturingGroup));
        unitAnnotation.setEnd (dimMatcher.end (capturingGroup));
        unitAnnotation.addToIndexes ();
        return unitAnnotation;
    }

    // use inequalityGroup=-1 to indicate no inequality indicated
    private ExtentAnnotation makeExtentAnnotation (JCas jcas, Matcher dimMatcher, int capturingGroup)
    {
        return makeExtentAnnotation (jcas, dimMatcher, capturingGroup, -1);
    }
    
    // use inequalityGroup=-1 to indicate no inequality indicated
    private ExtentAnnotation makeExtentAnnotation (JCas jcas, Matcher dimMatcher, int capturingGroup, int inequalityGroup)
    {
        String inequalityString = (inequalityGroup == -1) ? null : dimMatcher.group(inequalityGroup);
        ExtentAnnotation extentAnnotation = new ExtentAnnotation (jcas);
        extentAnnotation.setBegin (dimMatcher.start (capturingGroup));
        extentAnnotation.setEnd (dimMatcher.end (capturingGroup));
        extentAnnotation.setEquality (equalityValueFromString (inequalityString));
        extentAnnotation.addToIndexes ();
        return extentAnnotation;
    }

    private String equalityValueFromString (String equalityString)
    {
        return ((equalityString == null) ? "=" : equalityString);
    }
    
    public static float getNormalizedExtent (DimensionAnnotation ann)
    throws IllegalArgumentException    
    {
        float extent;
        String unit;
        float norm_factor = 1.0F;
        
        extent = Float.parseFloat(ann.getValue().getCoveredText());
        unit = ann.getUnit().getCoveredText();

        if (unit.equalsIgnoreCase("mm")) {
            norm_factor = 0.1F;
        }
        if (unit.equalsIgnoreCase("m")) {
            norm_factor = 100.0F;
        }
        return extent * norm_factor; 
    }

    private static List<DimensionAnnotation> getDimensions (DimensionSetAnnotation ann)
    {
        List<DimensionAnnotation> dims = new ArrayList<DimensionAnnotation>();
        for (int i = 0; i < ann.getDimensions().size(); ++i) {
            DimensionAnnotation da = (DimensionAnnotation)ann.getDimensions(i);
            if (null == da) {
                continue;
            }
            dims.add(da);
        }
        return dims;
    }
    
    public static int compare (DimensionSetAnnotation ds1, DimensionSetAnnotation ds2)
    {
        List<DimensionAnnotation> dims1 = getDimensions (ds1);
        List<DimensionAnnotation> dims2 = getDimensions (ds2);
        
        if (dims1.size() < dims2.size()) {
            return -1;
        }
        else if (dims1.size() > dims2.size()) {
            return 1;
        }
        
        float v1 = 1.0F;
        float v2 = 1.0F;
        for (int i = 0; i < dims1.size(); ++i) {
            v1 *= getNormalizedExtent(dims1.get(i)); 
            v2 *= getNormalizedExtent(dims2.get(i)); 
        }
        return (v1 == v2) ? 0 : ((v1 < v2) ? -1 : 1); 
    }
}
