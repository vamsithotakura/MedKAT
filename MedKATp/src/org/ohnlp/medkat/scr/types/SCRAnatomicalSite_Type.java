
/* First created by JCasGen Wed Jun 11 12:10:51 EDT 2008 */
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

/** SCR AnatomicalSite type
 * Updated by JCasGen Mon Mar 23 14:01:47 EDT 2009
 * @generated */
public class SCRAnatomicalSite_Type extends SCRNamedEntityBase_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (SCRAnatomicalSite_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = SCRAnatomicalSite_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new SCRAnatomicalSite(addr, SCRAnatomicalSite_Type.this);
  			   SCRAnatomicalSite_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new SCRAnatomicalSite(addr, SCRAnatomicalSite_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = SCRAnatomicalSite.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.ohnlp.medkat.scr.types.SCRAnatomicalSite");
 
  /** @generated */
  final Feature casFeat_Laterality;
  /** @generated */
  final int     casFeatCode_Laterality;
  /** @generated */ 
  public String getLaterality(int addr) {
        if (featOkTst && casFeat_Laterality == null)
      jcas.throwFeatMissing("Laterality", "org.ohnlp.medkat.scr.types.SCRAnatomicalSite");
    return ll_cas.ll_getStringValue(addr, casFeatCode_Laterality);
  }
  /** @generated */    
  public void setLaterality(int addr, String v) {
        if (featOkTst && casFeat_Laterality == null)
      jcas.throwFeatMissing("Laterality", "org.ohnlp.medkat.scr.types.SCRAnatomicalSite");
    ll_cas.ll_setStringValue(addr, casFeatCode_Laterality, v);}
    
  
 
  /** @generated */
  final Feature casFeat_Modifiers;
  /** @generated */
  final int     casFeatCode_Modifiers;
  /** @generated */ 
  public int getModifiers(int addr) {
        if (featOkTst && casFeat_Modifiers == null)
      jcas.throwFeatMissing("Modifiers", "org.ohnlp.medkat.scr.types.SCRAnatomicalSite");
    return ll_cas.ll_getRefValue(addr, casFeatCode_Modifiers);
  }
  /** @generated */    
  public void setModifiers(int addr, int v) {
        if (featOkTst && casFeat_Modifiers == null)
      jcas.throwFeatMissing("Modifiers", "org.ohnlp.medkat.scr.types.SCRAnatomicalSite");
    ll_cas.ll_setRefValue(addr, casFeatCode_Modifiers, v);}
    
   /** @generated */
  public int getModifiers(int addr, int i) {
        if (featOkTst && casFeat_Modifiers == null)
      jcas.throwFeatMissing("Modifiers", "org.ohnlp.medkat.scr.types.SCRAnatomicalSite");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_Modifiers), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_Modifiers), i);
  return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_Modifiers), i);
  }
   
  /** @generated */ 
  public void setModifiers(int addr, int i, int v) {
        if (featOkTst && casFeat_Modifiers == null)
      jcas.throwFeatMissing("Modifiers", "org.ohnlp.medkat.scr.types.SCRAnatomicalSite");
    if (lowLevelTypeChecks)
      ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_Modifiers), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_Modifiers), i);
    ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_Modifiers), i, v);
  }
 
 
  /** @generated */
  final Feature casFeat_InferredCode;
  /** @generated */
  final int     casFeatCode_InferredCode;
  /** @generated */ 
  public String getInferredCode(int addr) {
        if (featOkTst && casFeat_InferredCode == null)
      jcas.throwFeatMissing("InferredCode", "org.ohnlp.medkat.scr.types.SCRAnatomicalSite");
    return ll_cas.ll_getStringValue(addr, casFeatCode_InferredCode);
  }
  /** @generated */    
  public void setInferredCode(int addr, String v) {
        if (featOkTst && casFeat_InferredCode == null)
      jcas.throwFeatMissing("InferredCode", "org.ohnlp.medkat.scr.types.SCRAnatomicalSite");
    ll_cas.ll_setStringValue(addr, casFeatCode_InferredCode, v);}
    
  
 
  /** @generated */
  final Feature casFeat_Coreferences;
  /** @generated */
  final int     casFeatCode_Coreferences;
  /** @generated */ 
  public int getCoreferences(int addr) {
        if (featOkTst && casFeat_Coreferences == null)
      jcas.throwFeatMissing("Coreferences", "org.ohnlp.medkat.scr.types.SCRAnatomicalSite");
    return ll_cas.ll_getRefValue(addr, casFeatCode_Coreferences);
  }
  /** @generated */    
  public void setCoreferences(int addr, int v) {
        if (featOkTst && casFeat_Coreferences == null)
      jcas.throwFeatMissing("Coreferences", "org.ohnlp.medkat.scr.types.SCRAnatomicalSite");
    ll_cas.ll_setRefValue(addr, casFeatCode_Coreferences, v);}
    
   /** @generated */
  public int getCoreferences(int addr, int i) {
        if (featOkTst && casFeat_Coreferences == null)
      jcas.throwFeatMissing("Coreferences", "org.ohnlp.medkat.scr.types.SCRAnatomicalSite");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_Coreferences), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_Coreferences), i);
  return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_Coreferences), i);
  }
   
  /** @generated */ 
  public void setCoreferences(int addr, int i, int v) {
        if (featOkTst && casFeat_Coreferences == null)
      jcas.throwFeatMissing("Coreferences", "org.ohnlp.medkat.scr.types.SCRAnatomicalSite");
    if (lowLevelTypeChecks)
      ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_Coreferences), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_Coreferences), i);
    ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_Coreferences), i, v);
  }
 



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public SCRAnatomicalSite_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_Laterality = jcas.getRequiredFeatureDE(casType, "Laterality", "uima.cas.String", featOkTst);
    casFeatCode_Laterality  = (null == casFeat_Laterality) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Laterality).getCode();

 
    casFeat_Modifiers = jcas.getRequiredFeatureDE(casType, "Modifiers", "uima.cas.FSArray", featOkTst);
    casFeatCode_Modifiers  = (null == casFeat_Modifiers) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Modifiers).getCode();

 
    casFeat_InferredCode = jcas.getRequiredFeatureDE(casType, "InferredCode", "uima.cas.String", featOkTst);
    casFeatCode_InferredCode  = (null == casFeat_InferredCode) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_InferredCode).getCode();

 
    casFeat_Coreferences = jcas.getRequiredFeatureDE(casType, "Coreferences", "uima.cas.FSArray", featOkTst);
    casFeatCode_Coreferences  = (null == casFeat_Coreferences) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Coreferences).getCode();

  }
}



    