
/* First created by JCasGen Wed Aug 27 00:14:06 EDT 2008 */
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

/** stage object
 * Updated by JCasGen Wed Mar 11 15:34:13 EDT 2009
 * @generated */
public class SCRStage_Type extends Annotation_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (SCRStage_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = SCRStage_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new SCRStage(addr, SCRStage_Type.this);
  			   SCRStage_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new SCRStage(addr, SCRStage_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = SCRStage.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.ohnlp.medkat.scr.types.SCRStage");
 
  /** @generated */
  final Feature casFeat_StageType;
  /** @generated */
  final int     casFeatCode_StageType;
  /** @generated */ 
  public String getStageType(int addr) {
        if (featOkTst && casFeat_StageType == null)
      jcas.throwFeatMissing("StageType", "org.ohnlp.medkat.scr.types.SCRStage");
    return ll_cas.ll_getStringValue(addr, casFeatCode_StageType);
  }
  /** @generated */    
  public void setStageType(int addr, String v) {
        if (featOkTst && casFeat_StageType == null)
      jcas.throwFeatMissing("StageType", "org.ohnlp.medkat.scr.types.SCRStage");
    ll_cas.ll_setStringValue(addr, casFeatCode_StageType, v);}
    
  
 
  /** @generated */
  final Feature casFeat_StageValue;
  /** @generated */
  final int     casFeatCode_StageValue;
  /** @generated */ 
  public String getStageValue(int addr) {
        if (featOkTst && casFeat_StageValue == null)
      jcas.throwFeatMissing("StageValue", "org.ohnlp.medkat.scr.types.SCRStage");
    return ll_cas.ll_getStringValue(addr, casFeatCode_StageValue);
  }
  /** @generated */    
  public void setStageValue(int addr, String v) {
        if (featOkTst && casFeat_StageValue == null)
      jcas.throwFeatMissing("StageValue", "org.ohnlp.medkat.scr.types.SCRStage");
    ll_cas.ll_setStringValue(addr, casFeatCode_StageValue, v);}
    
  
 
  /** @generated */
  final Feature casFeat_Identifier;
  /** @generated */
  final int     casFeatCode_Identifier;
  /** @generated */ 
  public String getIdentifier(int addr) {
        if (featOkTst && casFeat_Identifier == null)
      jcas.throwFeatMissing("Identifier", "org.ohnlp.medkat.scr.types.SCRStage");
    return ll_cas.ll_getStringValue(addr, casFeatCode_Identifier);
  }
  /** @generated */    
  public void setIdentifier(int addr, String v) {
        if (featOkTst && casFeat_Identifier == null)
      jcas.throwFeatMissing("Identifier", "org.ohnlp.medkat.scr.types.SCRStage");
    ll_cas.ll_setStringValue(addr, casFeatCode_Identifier, v);}
    
  
 
  /** @generated */
  final Feature casFeat_Inference;
  /** @generated */
  final int     casFeatCode_Inference;
  /** @generated */ 
  public int getInference(int addr) {
        if (featOkTst && casFeat_Inference == null)
      jcas.throwFeatMissing("Inference", "org.ohnlp.medkat.scr.types.SCRStage");
    return ll_cas.ll_getIntValue(addr, casFeatCode_Inference);
  }
  /** @generated */    
  public void setInference(int addr, int v) {
        if (featOkTst && casFeat_Inference == null)
      jcas.throwFeatMissing("Inference", "org.ohnlp.medkat.scr.types.SCRStage");
    ll_cas.ll_setIntValue(addr, casFeatCode_Inference, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public SCRStage_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_StageType = jcas.getRequiredFeatureDE(casType, "StageType", "uima.cas.String", featOkTst);
    casFeatCode_StageType  = (null == casFeat_StageType) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_StageType).getCode();

 
    casFeat_StageValue = jcas.getRequiredFeatureDE(casType, "StageValue", "uima.cas.String", featOkTst);
    casFeatCode_StageValue  = (null == casFeat_StageValue) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_StageValue).getCode();

 
    casFeat_Identifier = jcas.getRequiredFeatureDE(casType, "Identifier", "uima.cas.String", featOkTst);
    casFeatCode_Identifier  = (null == casFeat_Identifier) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Identifier).getCode();

 
    casFeat_Inference = jcas.getRequiredFeatureDE(casType, "Inference", "uima.cas.Integer", featOkTst);
    casFeatCode_Inference  = (null == casFeat_Inference) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Inference).getCode();

  }
}



    