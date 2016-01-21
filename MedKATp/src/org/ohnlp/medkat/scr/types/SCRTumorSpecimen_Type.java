
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

/** primary tumor object
 * Updated by JCasGen Wed Mar 11 15:34:13 EDT 2009
 * @generated */
public class SCRTumorSpecimen_Type extends Annotation_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (SCRTumorSpecimen_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = SCRTumorSpecimen_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new SCRTumorSpecimen(addr, SCRTumorSpecimen_Type.this);
  			   SCRTumorSpecimen_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new SCRTumorSpecimen(addr, SCRTumorSpecimen_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = SCRTumorSpecimen.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.ohnlp.medkat.scr.types.SCRTumorSpecimen");
 
  /** @generated */
  final Feature casFeat_PrimaryTumors;
  /** @generated */
  final int     casFeatCode_PrimaryTumors;
  /** @generated */ 
  public int getPrimaryTumors(int addr) {
        if (featOkTst && casFeat_PrimaryTumors == null)
      jcas.throwFeatMissing("PrimaryTumors", "org.ohnlp.medkat.scr.types.SCRTumorSpecimen");
    return ll_cas.ll_getRefValue(addr, casFeatCode_PrimaryTumors);
  }
  /** @generated */    
  public void setPrimaryTumors(int addr, int v) {
        if (featOkTst && casFeat_PrimaryTumors == null)
      jcas.throwFeatMissing("PrimaryTumors", "org.ohnlp.medkat.scr.types.SCRTumorSpecimen");
    ll_cas.ll_setRefValue(addr, casFeatCode_PrimaryTumors, v);}
    
   /** @generated */
  public int getPrimaryTumors(int addr, int i) {
        if (featOkTst && casFeat_PrimaryTumors == null)
      jcas.throwFeatMissing("PrimaryTumors", "org.ohnlp.medkat.scr.types.SCRTumorSpecimen");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_PrimaryTumors), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_PrimaryTumors), i);
  return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_PrimaryTumors), i);
  }
   
  /** @generated */ 
  public void setPrimaryTumors(int addr, int i, int v) {
        if (featOkTst && casFeat_PrimaryTumors == null)
      jcas.throwFeatMissing("PrimaryTumors", "org.ohnlp.medkat.scr.types.SCRTumorSpecimen");
    if (lowLevelTypeChecks)
      ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_PrimaryTumors), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_PrimaryTumors), i);
    ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_PrimaryTumors), i, v);
  }
 
 
  /** @generated */
  final Feature casFeat_MetstaticTumors;
  /** @generated */
  final int     casFeatCode_MetstaticTumors;
  /** @generated */ 
  public int getMetstaticTumors(int addr) {
        if (featOkTst && casFeat_MetstaticTumors == null)
      jcas.throwFeatMissing("MetstaticTumors", "org.ohnlp.medkat.scr.types.SCRTumorSpecimen");
    return ll_cas.ll_getRefValue(addr, casFeatCode_MetstaticTumors);
  }
  /** @generated */    
  public void setMetstaticTumors(int addr, int v) {
        if (featOkTst && casFeat_MetstaticTumors == null)
      jcas.throwFeatMissing("MetstaticTumors", "org.ohnlp.medkat.scr.types.SCRTumorSpecimen");
    ll_cas.ll_setRefValue(addr, casFeatCode_MetstaticTumors, v);}
    
   /** @generated */
  public int getMetstaticTumors(int addr, int i) {
        if (featOkTst && casFeat_MetstaticTumors == null)
      jcas.throwFeatMissing("MetstaticTumors", "org.ohnlp.medkat.scr.types.SCRTumorSpecimen");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_MetstaticTumors), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_MetstaticTumors), i);
  return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_MetstaticTumors), i);
  }
   
  /** @generated */ 
  public void setMetstaticTumors(int addr, int i, int v) {
        if (featOkTst && casFeat_MetstaticTumors == null)
      jcas.throwFeatMissing("MetstaticTumors", "org.ohnlp.medkat.scr.types.SCRTumorSpecimen");
    if (lowLevelTypeChecks)
      ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_MetstaticTumors), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_MetstaticTumors), i);
    ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_MetstaticTumors), i, v);
  }
 
 
  /** @generated */
  final Feature casFeat_Procedures;
  /** @generated */
  final int     casFeatCode_Procedures;
  /** @generated */ 
  public int getProcedures(int addr) {
        if (featOkTst && casFeat_Procedures == null)
      jcas.throwFeatMissing("Procedures", "org.ohnlp.medkat.scr.types.SCRTumorSpecimen");
    return ll_cas.ll_getRefValue(addr, casFeatCode_Procedures);
  }
  /** @generated */    
  public void setProcedures(int addr, int v) {
        if (featOkTst && casFeat_Procedures == null)
      jcas.throwFeatMissing("Procedures", "org.ohnlp.medkat.scr.types.SCRTumorSpecimen");
    ll_cas.ll_setRefValue(addr, casFeatCode_Procedures, v);}
    
   /** @generated */
  public int getProcedures(int addr, int i) {
        if (featOkTst && casFeat_Procedures == null)
      jcas.throwFeatMissing("Procedures", "org.ohnlp.medkat.scr.types.SCRTumorSpecimen");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_Procedures), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_Procedures), i);
  return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_Procedures), i);
  }
   
  /** @generated */ 
  public void setProcedures(int addr, int i, int v) {
        if (featOkTst && casFeat_Procedures == null)
      jcas.throwFeatMissing("Procedures", "org.ohnlp.medkat.scr.types.SCRTumorSpecimen");
    if (lowLevelTypeChecks)
      ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_Procedures), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_Procedures), i);
    ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_Procedures), i, v);
  }
 



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public SCRTumorSpecimen_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_PrimaryTumors = jcas.getRequiredFeatureDE(casType, "PrimaryTumors", "uima.cas.FSArray", featOkTst);
    casFeatCode_PrimaryTumors  = (null == casFeat_PrimaryTumors) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_PrimaryTumors).getCode();

 
    casFeat_MetstaticTumors = jcas.getRequiredFeatureDE(casType, "MetstaticTumors", "uima.cas.FSArray", featOkTst);
    casFeatCode_MetstaticTumors  = (null == casFeat_MetstaticTumors) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_MetstaticTumors).getCode();

 
    casFeat_Procedures = jcas.getRequiredFeatureDE(casType, "Procedures", "uima.cas.FSArray", featOkTst);
    casFeatCode_Procedures  = (null == casFeat_Procedures) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Procedures).getCode();

  }
}



    