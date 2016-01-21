
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

/** primary tumor reading object
 * Updated by JCasGen Mon Mar 23 14:01:47 EDT 2009
 * @generated */
public class SCRPrimaryTumorReading_Type extends SCRGenericReading_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (SCRPrimaryTumorReading_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = SCRPrimaryTumorReading_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new SCRPrimaryTumorReading(addr, SCRPrimaryTumorReading_Type.this);
  			   SCRPrimaryTumorReading_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new SCRPrimaryTumorReading(addr, SCRPrimaryTumorReading_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = SCRPrimaryTumorReading.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.ohnlp.medkat.scr.types.SCRPrimaryTumorReading");
 
  /** @generated */
  final Feature casFeat_PrimarySites;
  /** @generated */
  final int     casFeatCode_PrimarySites;
  /** @generated */ 
  public int getPrimarySites(int addr) {
        if (featOkTst && casFeat_PrimarySites == null)
      jcas.throwFeatMissing("PrimarySites", "org.ohnlp.medkat.scr.types.SCRPrimaryTumorReading");
    return ll_cas.ll_getRefValue(addr, casFeatCode_PrimarySites);
  }
  /** @generated */    
  public void setPrimarySites(int addr, int v) {
        if (featOkTst && casFeat_PrimarySites == null)
      jcas.throwFeatMissing("PrimarySites", "org.ohnlp.medkat.scr.types.SCRPrimaryTumorReading");
    ll_cas.ll_setRefValue(addr, casFeatCode_PrimarySites, v);}
    
   /** @generated */
  public int getPrimarySites(int addr, int i) {
        if (featOkTst && casFeat_PrimarySites == null)
      jcas.throwFeatMissing("PrimarySites", "org.ohnlp.medkat.scr.types.SCRPrimaryTumorReading");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_PrimarySites), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_PrimarySites), i);
  return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_PrimarySites), i);
  }
   
  /** @generated */ 
  public void setPrimarySites(int addr, int i, int v) {
        if (featOkTst && casFeat_PrimarySites == null)
      jcas.throwFeatMissing("PrimarySites", "org.ohnlp.medkat.scr.types.SCRPrimaryTumorReading");
    if (lowLevelTypeChecks)
      ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_PrimarySites), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_PrimarySites), i);
    ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_PrimarySites), i, v);
  }
 
 
  /** @generated */
  final Feature casFeat_GradeValues;
  /** @generated */
  final int     casFeatCode_GradeValues;
  /** @generated */ 
  public int getGradeValues(int addr) {
        if (featOkTst && casFeat_GradeValues == null)
      jcas.throwFeatMissing("GradeValues", "org.ohnlp.medkat.scr.types.SCRPrimaryTumorReading");
    return ll_cas.ll_getRefValue(addr, casFeatCode_GradeValues);
  }
  /** @generated */    
  public void setGradeValues(int addr, int v) {
        if (featOkTst && casFeat_GradeValues == null)
      jcas.throwFeatMissing("GradeValues", "org.ohnlp.medkat.scr.types.SCRPrimaryTumorReading");
    ll_cas.ll_setRefValue(addr, casFeatCode_GradeValues, v);}
    
   /** @generated */
  public int getGradeValues(int addr, int i) {
        if (featOkTst && casFeat_GradeValues == null)
      jcas.throwFeatMissing("GradeValues", "org.ohnlp.medkat.scr.types.SCRPrimaryTumorReading");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_GradeValues), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_GradeValues), i);
  return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_GradeValues), i);
  }
   
  /** @generated */ 
  public void setGradeValues(int addr, int i, int v) {
        if (featOkTst && casFeat_GradeValues == null)
      jcas.throwFeatMissing("GradeValues", "org.ohnlp.medkat.scr.types.SCRPrimaryTumorReading");
    if (lowLevelTypeChecks)
      ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_GradeValues), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_GradeValues), i);
    ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_GradeValues), i, v);
  }
 
 
  /** @generated */
  final Feature casFeat_Diagnoses;
  /** @generated */
  final int     casFeatCode_Diagnoses;
  /** @generated */ 
  public int getDiagnoses(int addr) {
        if (featOkTst && casFeat_Diagnoses == null)
      jcas.throwFeatMissing("Diagnoses", "org.ohnlp.medkat.scr.types.SCRPrimaryTumorReading");
    return ll_cas.ll_getRefValue(addr, casFeatCode_Diagnoses);
  }
  /** @generated */    
  public void setDiagnoses(int addr, int v) {
        if (featOkTst && casFeat_Diagnoses == null)
      jcas.throwFeatMissing("Diagnoses", "org.ohnlp.medkat.scr.types.SCRPrimaryTumorReading");
    ll_cas.ll_setRefValue(addr, casFeatCode_Diagnoses, v);}
    
   /** @generated */
  public int getDiagnoses(int addr, int i) {
        if (featOkTst && casFeat_Diagnoses == null)
      jcas.throwFeatMissing("Diagnoses", "org.ohnlp.medkat.scr.types.SCRPrimaryTumorReading");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_Diagnoses), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_Diagnoses), i);
  return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_Diagnoses), i);
  }
   
  /** @generated */ 
  public void setDiagnoses(int addr, int i, int v) {
        if (featOkTst && casFeat_Diagnoses == null)
      jcas.throwFeatMissing("Diagnoses", "org.ohnlp.medkat.scr.types.SCRPrimaryTumorReading");
    if (lowLevelTypeChecks)
      ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_Diagnoses), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_Diagnoses), i);
    ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_Diagnoses), i, v);
  }
 
 
  /** @generated */
  final Feature casFeat_InvasionTypes;
  /** @generated */
  final int     casFeatCode_InvasionTypes;
  /** @generated */ 
  public int getInvasionTypes(int addr) {
        if (featOkTst && casFeat_InvasionTypes == null)
      jcas.throwFeatMissing("InvasionTypes", "org.ohnlp.medkat.scr.types.SCRPrimaryTumorReading");
    return ll_cas.ll_getRefValue(addr, casFeatCode_InvasionTypes);
  }
  /** @generated */    
  public void setInvasionTypes(int addr, int v) {
        if (featOkTst && casFeat_InvasionTypes == null)
      jcas.throwFeatMissing("InvasionTypes", "org.ohnlp.medkat.scr.types.SCRPrimaryTumorReading");
    ll_cas.ll_setRefValue(addr, casFeatCode_InvasionTypes, v);}
    
   /** @generated */
  public int getInvasionTypes(int addr, int i) {
        if (featOkTst && casFeat_InvasionTypes == null)
      jcas.throwFeatMissing("InvasionTypes", "org.ohnlp.medkat.scr.types.SCRPrimaryTumorReading");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_InvasionTypes), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_InvasionTypes), i);
  return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_InvasionTypes), i);
  }
   
  /** @generated */ 
  public void setInvasionTypes(int addr, int i, int v) {
        if (featOkTst && casFeat_InvasionTypes == null)
      jcas.throwFeatMissing("InvasionTypes", "org.ohnlp.medkat.scr.types.SCRPrimaryTumorReading");
    if (lowLevelTypeChecks)
      ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_InvasionTypes), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_InvasionTypes), i);
    ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_InvasionTypes), i, v);
  }
 
 
  /** @generated */
  final Feature casFeat_Size;
  /** @generated */
  final int     casFeatCode_Size;
  /** @generated */ 
  public int getSize(int addr) {
        if (featOkTst && casFeat_Size == null)
      jcas.throwFeatMissing("Size", "org.ohnlp.medkat.scr.types.SCRPrimaryTumorReading");
    return ll_cas.ll_getRefValue(addr, casFeatCode_Size);
  }
  /** @generated */    
  public void setSize(int addr, int v) {
        if (featOkTst && casFeat_Size == null)
      jcas.throwFeatMissing("Size", "org.ohnlp.medkat.scr.types.SCRPrimaryTumorReading");
    ll_cas.ll_setRefValue(addr, casFeatCode_Size, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public SCRPrimaryTumorReading_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_PrimarySites = jcas.getRequiredFeatureDE(casType, "PrimarySites", "uima.cas.FSArray", featOkTst);
    casFeatCode_PrimarySites  = (null == casFeat_PrimarySites) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_PrimarySites).getCode();

 
    casFeat_GradeValues = jcas.getRequiredFeatureDE(casType, "GradeValues", "uima.cas.FSArray", featOkTst);
    casFeatCode_GradeValues  = (null == casFeat_GradeValues) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_GradeValues).getCode();

 
    casFeat_Diagnoses = jcas.getRequiredFeatureDE(casType, "Diagnoses", "uima.cas.FSArray", featOkTst);
    casFeatCode_Diagnoses  = (null == casFeat_Diagnoses) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Diagnoses).getCode();

 
    casFeat_InvasionTypes = jcas.getRequiredFeatureDE(casType, "InvasionTypes", "uima.cas.FSArray", featOkTst);
    casFeatCode_InvasionTypes  = (null == casFeat_InvasionTypes) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_InvasionTypes).getCode();

 
    casFeat_Size = jcas.getRequiredFeatureDE(casType, "Size", "uima.tcas.Annotation", featOkTst);
    casFeatCode_Size  = (null == casFeat_Size) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Size).getCode();

  }
}



    