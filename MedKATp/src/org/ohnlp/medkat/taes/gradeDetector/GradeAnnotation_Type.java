
/* First created by JCasGen Wed Jun 11 12:10:52 EDT 2008 */
package org.ohnlp.medkat.taes.gradeDetector;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.tcas.Annotation_Type;

/** 
 * Updated by JCasGen Mon Mar 23 14:01:46 EDT 2009
 * @generated */
public class GradeAnnotation_Type extends Annotation_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (GradeAnnotation_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = GradeAnnotation_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new GradeAnnotation(addr, GradeAnnotation_Type.this);
  			   GradeAnnotation_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new GradeAnnotation(addr, GradeAnnotation_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = GradeAnnotation.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.ohnlp.medkat.taes.gradeDetector.GradeAnnotation");
 
  /** @generated */
  final Feature casFeat_gradeType;
  /** @generated */
  final int     casFeatCode_gradeType;
  /** @generated */ 
  public String getGradeType(int addr) {
        if (featOkTst && casFeat_gradeType == null)
      jcas.throwFeatMissing("gradeType", "org.ohnlp.medkat.taes.gradeDetector.GradeAnnotation");
    return ll_cas.ll_getStringValue(addr, casFeatCode_gradeType);
  }
  /** @generated */    
  public void setGradeType(int addr, String v) {
        if (featOkTst && casFeat_gradeType == null)
      jcas.throwFeatMissing("gradeType", "org.ohnlp.medkat.taes.gradeDetector.GradeAnnotation");
    ll_cas.ll_setStringValue(addr, casFeatCode_gradeType, v);}
    
  
 
  /** @generated */
  final Feature casFeat_value;
  /** @generated */
  final int     casFeatCode_value;
  /** @generated */ 
  public String getValue(int addr) {
        if (featOkTst && casFeat_value == null)
      jcas.throwFeatMissing("value", "org.ohnlp.medkat.taes.gradeDetector.GradeAnnotation");
    return ll_cas.ll_getStringValue(addr, casFeatCode_value);
  }
  /** @generated */    
  public void setValue(int addr, String v) {
        if (featOkTst && casFeat_value == null)
      jcas.throwFeatMissing("value", "org.ohnlp.medkat.taes.gradeDetector.GradeAnnotation");
    ll_cas.ll_setStringValue(addr, casFeatCode_value, v);}
    
  
 
  /** @generated */
  final Feature casFeat_subsection;
  /** @generated */
  final int     casFeatCode_subsection;
  /** @generated */ 
  public int getSubsection(int addr) {
        if (featOkTst && casFeat_subsection == null)
      jcas.throwFeatMissing("subsection", "org.ohnlp.medkat.taes.gradeDetector.GradeAnnotation");
    return ll_cas.ll_getIntValue(addr, casFeatCode_subsection);
  }
  /** @generated */    
  public void setSubsection(int addr, int v) {
        if (featOkTst && casFeat_subsection == null)
      jcas.throwFeatMissing("subsection", "org.ohnlp.medkat.taes.gradeDetector.GradeAnnotation");
    ll_cas.ll_setIntValue(addr, casFeatCode_subsection, v);}
    
  
 
  /** @generated */
  final Feature casFeat_maxValue;
  /** @generated */
  final int     casFeatCode_maxValue;
  /** @generated */ 
  public String getMaxValue(int addr) {
        if (featOkTst && casFeat_maxValue == null)
      jcas.throwFeatMissing("maxValue", "org.ohnlp.medkat.taes.gradeDetector.GradeAnnotation");
    return ll_cas.ll_getStringValue(addr, casFeatCode_maxValue);
  }
  /** @generated */    
  public void setMaxValue(int addr, String v) {
        if (featOkTst && casFeat_maxValue == null)
      jcas.throwFeatMissing("maxValue", "org.ohnlp.medkat.taes.gradeDetector.GradeAnnotation");
    ll_cas.ll_setStringValue(addr, casFeatCode_maxValue, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public GradeAnnotation_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_gradeType = jcas.getRequiredFeatureDE(casType, "gradeType", "uima.cas.String", featOkTst);
    casFeatCode_gradeType  = (null == casFeat_gradeType) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_gradeType).getCode();

 
    casFeat_value = jcas.getRequiredFeatureDE(casType, "value", "uima.cas.String", featOkTst);
    casFeatCode_value  = (null == casFeat_value) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_value).getCode();

 
    casFeat_subsection = jcas.getRequiredFeatureDE(casType, "subsection", "uima.cas.Integer", featOkTst);
    casFeatCode_subsection  = (null == casFeat_subsection) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_subsection).getCode();

 
    casFeat_maxValue = jcas.getRequiredFeatureDE(casType, "maxValue", "uima.cas.String", featOkTst);
    casFeatCode_maxValue  = (null == casFeat_maxValue) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_maxValue).getCode();

  }
}



    