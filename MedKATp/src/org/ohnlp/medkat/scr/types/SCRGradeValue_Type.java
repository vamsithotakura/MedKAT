
/* First created by JCasGen Wed Jun 11 12:10:52 EDT 2008 */
package org.ohnlp.medkat.scr.types;

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

/** grade value object
 * Updated by JCasGen Mon Mar 23 14:01:47 EDT 2009
 * @generated */
public class SCRGradeValue_Type extends Annotation_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (SCRGradeValue_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = SCRGradeValue_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new SCRGradeValue(addr, SCRGradeValue_Type.this);
  			   SCRGradeValue_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new SCRGradeValue(addr, SCRGradeValue_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = SCRGradeValue.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.ohnlp.medkat.scr.types.SCRGradeValue");
 
  /** @generated */
  final Feature casFeat_GradeType;
  /** @generated */
  final int     casFeatCode_GradeType;
  /** @generated */ 
  public String getGradeType(int addr) {
        if (featOkTst && casFeat_GradeType == null)
      jcas.throwFeatMissing("GradeType", "org.ohnlp.medkat.scr.types.SCRGradeValue");
    return ll_cas.ll_getStringValue(addr, casFeatCode_GradeType);
  }
  /** @generated */    
  public void setGradeType(int addr, String v) {
        if (featOkTst && casFeat_GradeType == null)
      jcas.throwFeatMissing("GradeType", "org.ohnlp.medkat.scr.types.SCRGradeValue");
    ll_cas.ll_setStringValue(addr, casFeatCode_GradeType, v);}
    
  
 
  /** @generated */
  final Feature casFeat_GradeValue;
  /** @generated */
  final int     casFeatCode_GradeValue;
  /** @generated */ 
  public String getGradeValue(int addr) {
        if (featOkTst && casFeat_GradeValue == null)
      jcas.throwFeatMissing("GradeValue", "org.ohnlp.medkat.scr.types.SCRGradeValue");
    return ll_cas.ll_getStringValue(addr, casFeatCode_GradeValue);
  }
  /** @generated */    
  public void setGradeValue(int addr, String v) {
        if (featOkTst && casFeat_GradeValue == null)
      jcas.throwFeatMissing("GradeValue", "org.ohnlp.medkat.scr.types.SCRGradeValue");
    ll_cas.ll_setStringValue(addr, casFeatCode_GradeValue, v);}
    
  
 
  /** @generated */
  final Feature casFeat_GradeScale;
  /** @generated */
  final int     casFeatCode_GradeScale;
  /** @generated */ 
  public String getGradeScale(int addr) {
        if (featOkTst && casFeat_GradeScale == null)
      jcas.throwFeatMissing("GradeScale", "org.ohnlp.medkat.scr.types.SCRGradeValue");
    return ll_cas.ll_getStringValue(addr, casFeatCode_GradeScale);
  }
  /** @generated */    
  public void setGradeScale(int addr, String v) {
        if (featOkTst && casFeat_GradeScale == null)
      jcas.throwFeatMissing("GradeScale", "org.ohnlp.medkat.scr.types.SCRGradeValue");
    ll_cas.ll_setStringValue(addr, casFeatCode_GradeScale, v);}
    
  
 
  /** @generated */
  final Feature casFeat_Inference;
  /** @generated */
  final int     casFeatCode_Inference;
  /** @generated */ 
  public int getInference(int addr) {
        if (featOkTst && casFeat_Inference == null)
      jcas.throwFeatMissing("Inference", "org.ohnlp.medkat.scr.types.SCRGradeValue");
    return ll_cas.ll_getIntValue(addr, casFeatCode_Inference);
  }
  /** @generated */    
  public void setInference(int addr, int v) {
        if (featOkTst && casFeat_Inference == null)
      jcas.throwFeatMissing("Inference", "org.ohnlp.medkat.scr.types.SCRGradeValue");
    ll_cas.ll_setIntValue(addr, casFeatCode_Inference, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public SCRGradeValue_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_GradeType = jcas.getRequiredFeatureDE(casType, "GradeType", "uima.cas.String", featOkTst);
    casFeatCode_GradeType  = (null == casFeat_GradeType) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_GradeType).getCode();

 
    casFeat_GradeValue = jcas.getRequiredFeatureDE(casType, "GradeValue", "uima.cas.String", featOkTst);
    casFeatCode_GradeValue  = (null == casFeat_GradeValue) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_GradeValue).getCode();

 
    casFeat_GradeScale = jcas.getRequiredFeatureDE(casType, "GradeScale", "uima.cas.String", featOkTst);
    casFeatCode_GradeScale  = (null == casFeat_GradeScale) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_GradeScale).getCode();

 
    casFeat_Inference = jcas.getRequiredFeatureDE(casType, "Inference", "uima.cas.Integer", featOkTst);
    casFeatCode_Inference  = (null == casFeat_Inference) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Inference).getCode();

  }
}



    