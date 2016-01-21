
/* First created by JCasGen Wed Aug 27 00:14:05 EDT 2008 */
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

/** SCR invasaion type
 * Updated by JCasGen Wed Mar 11 15:34:12 EDT 2009
 * @generated */
public class SCRInvasionType_Type extends SCRSpannedAnnotation_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (SCRInvasionType_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = SCRInvasionType_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new SCRInvasionType(addr, SCRInvasionType_Type.this);
  			   SCRInvasionType_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new SCRInvasionType(addr, SCRInvasionType_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = SCRInvasionType.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.ohnlp.medkat.scr.types.SCRInvasionType");
 
  /** @generated */
  final Feature casFeat_Terminology;
  /** @generated */
  final int     casFeatCode_Terminology;
  /** @generated */ 
  public String getTerminology(int addr) {
        if (featOkTst && casFeat_Terminology == null)
      jcas.throwFeatMissing("Terminology", "org.ohnlp.medkat.scr.types.SCRInvasionType");
    return ll_cas.ll_getStringValue(addr, casFeatCode_Terminology);
  }
  /** @generated */    
  public void setTerminology(int addr, String v) {
        if (featOkTst && casFeat_Terminology == null)
      jcas.throwFeatMissing("Terminology", "org.ohnlp.medkat.scr.types.SCRInvasionType");
    ll_cas.ll_setStringValue(addr, casFeatCode_Terminology, v);}
    
  
 
  /** @generated */
  final Feature casFeat_Code;
  /** @generated */
  final int     casFeatCode_Code;
  /** @generated */ 
  public String getCode(int addr) {
        if (featOkTst && casFeat_Code == null)
      jcas.throwFeatMissing("Code", "org.ohnlp.medkat.scr.types.SCRInvasionType");
    return ll_cas.ll_getStringValue(addr, casFeatCode_Code);
  }
  /** @generated */    
  public void setCode(int addr, String v) {
        if (featOkTst && casFeat_Code == null)
      jcas.throwFeatMissing("Code", "org.ohnlp.medkat.scr.types.SCRInvasionType");
    ll_cas.ll_setStringValue(addr, casFeatCode_Code, v);}
    
  
 
  /** @generated */
  final Feature casFeat_InvasionType;
  /** @generated */
  final int     casFeatCode_InvasionType;
  /** @generated */ 
  public String getInvasionType(int addr) {
        if (featOkTst && casFeat_InvasionType == null)
      jcas.throwFeatMissing("InvasionType", "org.ohnlp.medkat.scr.types.SCRInvasionType");
    return ll_cas.ll_getStringValue(addr, casFeatCode_InvasionType);
  }
  /** @generated */    
  public void setInvasionType(int addr, String v) {
        if (featOkTst && casFeat_InvasionType == null)
      jcas.throwFeatMissing("InvasionType", "org.ohnlp.medkat.scr.types.SCRInvasionType");
    ll_cas.ll_setStringValue(addr, casFeatCode_InvasionType, v);}
    
  
 
  /** @generated */
  final Feature casFeat_InvasionLevel;
  /** @generated */
  final int     casFeatCode_InvasionLevel;
  /** @generated */ 
  public String getInvasionLevel(int addr) {
        if (featOkTst && casFeat_InvasionLevel == null)
      jcas.throwFeatMissing("InvasionLevel", "org.ohnlp.medkat.scr.types.SCRInvasionType");
    return ll_cas.ll_getStringValue(addr, casFeatCode_InvasionLevel);
  }
  /** @generated */    
  public void setInvasionLevel(int addr, String v) {
        if (featOkTst && casFeat_InvasionLevel == null)
      jcas.throwFeatMissing("InvasionLevel", "org.ohnlp.medkat.scr.types.SCRInvasionType");
    ll_cas.ll_setStringValue(addr, casFeatCode_InvasionLevel, v);}
    
  
 
  /** @generated */
  final Feature casFeat_Negation;
  /** @generated */
  final int     casFeatCode_Negation;
  /** @generated */ 
  public int getNegation(int addr) {
        if (featOkTst && casFeat_Negation == null)
      jcas.throwFeatMissing("Negation", "org.ohnlp.medkat.scr.types.SCRInvasionType");
    return ll_cas.ll_getIntValue(addr, casFeatCode_Negation);
  }
  /** @generated */    
  public void setNegation(int addr, int v) {
        if (featOkTst && casFeat_Negation == null)
      jcas.throwFeatMissing("Negation", "org.ohnlp.medkat.scr.types.SCRInvasionType");
    ll_cas.ll_setIntValue(addr, casFeatCode_Negation, v);}
    
  
 
  /** @generated */
  final Feature casFeat_Inference;
  /** @generated */
  final int     casFeatCode_Inference;
  /** @generated */ 
  public int getInference(int addr) {
        if (featOkTst && casFeat_Inference == null)
      jcas.throwFeatMissing("Inference", "org.ohnlp.medkat.scr.types.SCRInvasionType");
    return ll_cas.ll_getIntValue(addr, casFeatCode_Inference);
  }
  /** @generated */    
  public void setInference(int addr, int v) {
        if (featOkTst && casFeat_Inference == null)
      jcas.throwFeatMissing("Inference", "org.ohnlp.medkat.scr.types.SCRInvasionType");
    ll_cas.ll_setIntValue(addr, casFeatCode_Inference, v);}
    
  
 
  /** @generated */
  final Feature casFeat_Sites;
  /** @generated */
  final int     casFeatCode_Sites;
  /** @generated */ 
  public int getSites(int addr) {
        if (featOkTst && casFeat_Sites == null)
      jcas.throwFeatMissing("Sites", "org.ohnlp.medkat.scr.types.SCRInvasionType");
    return ll_cas.ll_getRefValue(addr, casFeatCode_Sites);
  }
  /** @generated */    
  public void setSites(int addr, int v) {
        if (featOkTst && casFeat_Sites == null)
      jcas.throwFeatMissing("Sites", "org.ohnlp.medkat.scr.types.SCRInvasionType");
    ll_cas.ll_setRefValue(addr, casFeatCode_Sites, v);}
    
   /** @generated */
  public int getSites(int addr, int i) {
        if (featOkTst && casFeat_Sites == null)
      jcas.throwFeatMissing("Sites", "org.ohnlp.medkat.scr.types.SCRInvasionType");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_Sites), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_Sites), i);
  return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_Sites), i);
  }
   
  /** @generated */ 
  public void setSites(int addr, int i, int v) {
        if (featOkTst && casFeat_Sites == null)
      jcas.throwFeatMissing("Sites", "org.ohnlp.medkat.scr.types.SCRInvasionType");
    if (lowLevelTypeChecks)
      ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_Sites), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_Sites), i);
    ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_Sites), i, v);
  }
 



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public SCRInvasionType_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_Terminology = jcas.getRequiredFeatureDE(casType, "Terminology", "uima.cas.String", featOkTst);
    casFeatCode_Terminology  = (null == casFeat_Terminology) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Terminology).getCode();

 
    casFeat_Code = jcas.getRequiredFeatureDE(casType, "Code", "uima.cas.String", featOkTst);
    casFeatCode_Code  = (null == casFeat_Code) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Code).getCode();

 
    casFeat_InvasionType = jcas.getRequiredFeatureDE(casType, "InvasionType", "uima.cas.String", featOkTst);
    casFeatCode_InvasionType  = (null == casFeat_InvasionType) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_InvasionType).getCode();

 
    casFeat_InvasionLevel = jcas.getRequiredFeatureDE(casType, "InvasionLevel", "uima.cas.String", featOkTst);
    casFeatCode_InvasionLevel  = (null == casFeat_InvasionLevel) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_InvasionLevel).getCode();

 
    casFeat_Negation = jcas.getRequiredFeatureDE(casType, "Negation", "uima.cas.Integer", featOkTst);
    casFeatCode_Negation  = (null == casFeat_Negation) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Negation).getCode();

 
    casFeat_Inference = jcas.getRequiredFeatureDE(casType, "Inference", "uima.cas.Integer", featOkTst);
    casFeatCode_Inference  = (null == casFeat_Inference) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Inference).getCode();

 
    casFeat_Sites = jcas.getRequiredFeatureDE(casType, "Sites", "uima.cas.FSArray", featOkTst);
    casFeatCode_Sites  = (null == casFeat_Sites) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Sites).getCode();

  }
}



    