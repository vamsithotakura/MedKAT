
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

/** Procedure object
 * Updated by JCasGen Wed Mar 11 15:34:13 EDT 2009
 * @generated */
public class SCRProcedure_Type extends SCRSpannedAnnotation_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (SCRProcedure_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = SCRProcedure_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new SCRProcedure(addr, SCRProcedure_Type.this);
  			   SCRProcedure_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new SCRProcedure(addr, SCRProcedure_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = SCRProcedure.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.ohnlp.medkat.scr.types.SCRProcedure");
 
  /** @generated */
  final Feature casFeat_Institution;
  /** @generated */
  final int     casFeatCode_Institution;
  /** @generated */ 
  public String getInstitution(int addr) {
        if (featOkTst && casFeat_Institution == null)
      jcas.throwFeatMissing("Institution", "org.ohnlp.medkat.scr.types.SCRProcedure");
    return ll_cas.ll_getStringValue(addr, casFeatCode_Institution);
  }
  /** @generated */    
  public void setInstitution(int addr, String v) {
        if (featOkTst && casFeat_Institution == null)
      jcas.throwFeatMissing("Institution", "org.ohnlp.medkat.scr.types.SCRProcedure");
    ll_cas.ll_setStringValue(addr, casFeatCode_Institution, v);}
    
  
 
  /** @generated */
  final Feature casFeat_Date;
  /** @generated */
  final int     casFeatCode_Date;
  /** @generated */ 
  public int getDate(int addr) {
        if (featOkTst && casFeat_Date == null)
      jcas.throwFeatMissing("Date", "org.ohnlp.medkat.scr.types.SCRProcedure");
    return ll_cas.ll_getRefValue(addr, casFeatCode_Date);
  }
  /** @generated */    
  public void setDate(int addr, int v) {
        if (featOkTst && casFeat_Date == null)
      jcas.throwFeatMissing("Date", "org.ohnlp.medkat.scr.types.SCRProcedure");
    ll_cas.ll_setRefValue(addr, casFeatCode_Date, v);}
    
  
 
  /** @generated */
  final Feature casFeat_Sites;
  /** @generated */
  final int     casFeatCode_Sites;
  /** @generated */ 
  public int getSites(int addr) {
        if (featOkTst && casFeat_Sites == null)
      jcas.throwFeatMissing("Sites", "org.ohnlp.medkat.scr.types.SCRProcedure");
    return ll_cas.ll_getRefValue(addr, casFeatCode_Sites);
  }
  /** @generated */    
  public void setSites(int addr, int v) {
        if (featOkTst && casFeat_Sites == null)
      jcas.throwFeatMissing("Sites", "org.ohnlp.medkat.scr.types.SCRProcedure");
    ll_cas.ll_setRefValue(addr, casFeatCode_Sites, v);}
    
   /** @generated */
  public int getSites(int addr, int i) {
        if (featOkTst && casFeat_Sites == null)
      jcas.throwFeatMissing("Sites", "org.ohnlp.medkat.scr.types.SCRProcedure");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_Sites), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_Sites), i);
  return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_Sites), i);
  }
   
  /** @generated */ 
  public void setSites(int addr, int i, int v) {
        if (featOkTst && casFeat_Sites == null)
      jcas.throwFeatMissing("Sites", "org.ohnlp.medkat.scr.types.SCRProcedure");
    if (lowLevelTypeChecks)
      ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_Sites), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_Sites), i);
    ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_Sites), i, v);
  }
 
 
  /** @generated */
  final Feature casFeat_Terminology;
  /** @generated */
  final int     casFeatCode_Terminology;
  /** @generated */ 
  public String getTerminology(int addr) {
        if (featOkTst && casFeat_Terminology == null)
      jcas.throwFeatMissing("Terminology", "org.ohnlp.medkat.scr.types.SCRProcedure");
    return ll_cas.ll_getStringValue(addr, casFeatCode_Terminology);
  }
  /** @generated */    
  public void setTerminology(int addr, String v) {
        if (featOkTst && casFeat_Terminology == null)
      jcas.throwFeatMissing("Terminology", "org.ohnlp.medkat.scr.types.SCRProcedure");
    ll_cas.ll_setStringValue(addr, casFeatCode_Terminology, v);}
    
  
 
  /** @generated */
  final Feature casFeat_Code;
  /** @generated */
  final int     casFeatCode_Code;
  /** @generated */ 
  public String getCode(int addr) {
        if (featOkTst && casFeat_Code == null)
      jcas.throwFeatMissing("Code", "org.ohnlp.medkat.scr.types.SCRProcedure");
    return ll_cas.ll_getStringValue(addr, casFeatCode_Code);
  }
  /** @generated */    
  public void setCode(int addr, String v) {
        if (featOkTst && casFeat_Code == null)
      jcas.throwFeatMissing("Code", "org.ohnlp.medkat.scr.types.SCRProcedure");
    ll_cas.ll_setStringValue(addr, casFeatCode_Code, v);}
    
  
 
  /** @generated */
  final Feature casFeat_Modifiers;
  /** @generated */
  final int     casFeatCode_Modifiers;
  /** @generated */ 
  public int getModifiers(int addr) {
        if (featOkTst && casFeat_Modifiers == null)
      jcas.throwFeatMissing("Modifiers", "org.ohnlp.medkat.scr.types.SCRProcedure");
    return ll_cas.ll_getRefValue(addr, casFeatCode_Modifiers);
  }
  /** @generated */    
  public void setModifiers(int addr, int v) {
        if (featOkTst && casFeat_Modifiers == null)
      jcas.throwFeatMissing("Modifiers", "org.ohnlp.medkat.scr.types.SCRProcedure");
    ll_cas.ll_setRefValue(addr, casFeatCode_Modifiers, v);}
    
   /** @generated */
  public int getModifiers(int addr, int i) {
        if (featOkTst && casFeat_Modifiers == null)
      jcas.throwFeatMissing("Modifiers", "org.ohnlp.medkat.scr.types.SCRProcedure");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_Modifiers), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_Modifiers), i);
  return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_Modifiers), i);
  }
   
  /** @generated */ 
  public void setModifiers(int addr, int i, int v) {
        if (featOkTst && casFeat_Modifiers == null)
      jcas.throwFeatMissing("Modifiers", "org.ohnlp.medkat.scr.types.SCRProcedure");
    if (lowLevelTypeChecks)
      ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_Modifiers), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_Modifiers), i);
    ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_Modifiers), i, v);
  }
 
 
  /** @generated */
  final Feature casFeat_Negation;
  /** @generated */
  final int     casFeatCode_Negation;
  /** @generated */ 
  public int getNegation(int addr) {
        if (featOkTst && casFeat_Negation == null)
      jcas.throwFeatMissing("Negation", "org.ohnlp.medkat.scr.types.SCRProcedure");
    return ll_cas.ll_getIntValue(addr, casFeatCode_Negation);
  }
  /** @generated */    
  public void setNegation(int addr, int v) {
        if (featOkTst && casFeat_Negation == null)
      jcas.throwFeatMissing("Negation", "org.ohnlp.medkat.scr.types.SCRProcedure");
    ll_cas.ll_setIntValue(addr, casFeatCode_Negation, v);}
    
  
 
  /** @generated */
  final Feature casFeat_Inference;
  /** @generated */
  final int     casFeatCode_Inference;
  /** @generated */ 
  public int getInference(int addr) {
        if (featOkTst && casFeat_Inference == null)
      jcas.throwFeatMissing("Inference", "org.ohnlp.medkat.scr.types.SCRProcedure");
    return ll_cas.ll_getIntValue(addr, casFeatCode_Inference);
  }
  /** @generated */    
  public void setInference(int addr, int v) {
        if (featOkTst && casFeat_Inference == null)
      jcas.throwFeatMissing("Inference", "org.ohnlp.medkat.scr.types.SCRProcedure");
    ll_cas.ll_setIntValue(addr, casFeatCode_Inference, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public SCRProcedure_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_Institution = jcas.getRequiredFeatureDE(casType, "Institution", "uima.cas.String", featOkTst);
    casFeatCode_Institution  = (null == casFeat_Institution) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Institution).getCode();

 
    casFeat_Date = jcas.getRequiredFeatureDE(casType, "Date", "uima.tcas.Annotation", featOkTst);
    casFeatCode_Date  = (null == casFeat_Date) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Date).getCode();

 
    casFeat_Sites = jcas.getRequiredFeatureDE(casType, "Sites", "uima.cas.FSArray", featOkTst);
    casFeatCode_Sites  = (null == casFeat_Sites) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Sites).getCode();

 
    casFeat_Terminology = jcas.getRequiredFeatureDE(casType, "Terminology", "uima.cas.String", featOkTst);
    casFeatCode_Terminology  = (null == casFeat_Terminology) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Terminology).getCode();

 
    casFeat_Code = jcas.getRequiredFeatureDE(casType, "Code", "uima.cas.String", featOkTst);
    casFeatCode_Code  = (null == casFeat_Code) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Code).getCode();

 
    casFeat_Modifiers = jcas.getRequiredFeatureDE(casType, "Modifiers", "uima.cas.FSArray", featOkTst);
    casFeatCode_Modifiers  = (null == casFeat_Modifiers) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Modifiers).getCode();

 
    casFeat_Negation = jcas.getRequiredFeatureDE(casType, "Negation", "uima.cas.Integer", featOkTst);
    casFeatCode_Negation  = (null == casFeat_Negation) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Negation).getCode();

 
    casFeat_Inference = jcas.getRequiredFeatureDE(casType, "Inference", "uima.cas.Integer", featOkTst);
    casFeatCode_Inference  = (null == casFeat_Inference) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Inference).getCode();

  }
}



    