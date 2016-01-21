
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
import org.apache.uima.jcas.tcas.Annotation_Type;

/** primary tumor object
 * Updated by JCasGen Wed Mar 11 15:34:12 EDT 2009
 * @generated */
public class SCRDisease_Type extends Annotation_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (SCRDisease_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = SCRDisease_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new SCRDisease(addr, SCRDisease_Type.this);
  			   SCRDisease_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new SCRDisease(addr, SCRDisease_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = SCRDisease.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.ohnlp.medkat.scr.types.SCRDisease");
 
  /** @generated */
  final Feature casFeat_TumorSpecimens;
  /** @generated */
  final int     casFeatCode_TumorSpecimens;
  /** @generated */ 
  public int getTumorSpecimens(int addr) {
        if (featOkTst && casFeat_TumorSpecimens == null)
      jcas.throwFeatMissing("TumorSpecimens", "org.ohnlp.medkat.scr.types.SCRDisease");
    return ll_cas.ll_getRefValue(addr, casFeatCode_TumorSpecimens);
  }
  /** @generated */    
  public void setTumorSpecimens(int addr, int v) {
        if (featOkTst && casFeat_TumorSpecimens == null)
      jcas.throwFeatMissing("TumorSpecimens", "org.ohnlp.medkat.scr.types.SCRDisease");
    ll_cas.ll_setRefValue(addr, casFeatCode_TumorSpecimens, v);}
    
   /** @generated */
  public int getTumorSpecimens(int addr, int i) {
        if (featOkTst && casFeat_TumorSpecimens == null)
      jcas.throwFeatMissing("TumorSpecimens", "org.ohnlp.medkat.scr.types.SCRDisease");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_TumorSpecimens), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_TumorSpecimens), i);
  return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_TumorSpecimens), i);
  }
   
  /** @generated */ 
  public void setTumorSpecimens(int addr, int i, int v) {
        if (featOkTst && casFeat_TumorSpecimens == null)
      jcas.throwFeatMissing("TumorSpecimens", "org.ohnlp.medkat.scr.types.SCRDisease");
    if (lowLevelTypeChecks)
      ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_TumorSpecimens), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_TumorSpecimens), i);
    ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_TumorSpecimens), i, v);
  }
 
 
  /** @generated */
  final Feature casFeat_LymphNodes;
  /** @generated */
  final int     casFeatCode_LymphNodes;
  /** @generated */ 
  public int getLymphNodes(int addr) {
        if (featOkTst && casFeat_LymphNodes == null)
      jcas.throwFeatMissing("LymphNodes", "org.ohnlp.medkat.scr.types.SCRDisease");
    return ll_cas.ll_getRefValue(addr, casFeatCode_LymphNodes);
  }
  /** @generated */    
  public void setLymphNodes(int addr, int v) {
        if (featOkTst && casFeat_LymphNodes == null)
      jcas.throwFeatMissing("LymphNodes", "org.ohnlp.medkat.scr.types.SCRDisease");
    ll_cas.ll_setRefValue(addr, casFeatCode_LymphNodes, v);}
    
   /** @generated */
  public int getLymphNodes(int addr, int i) {
        if (featOkTst && casFeat_LymphNodes == null)
      jcas.throwFeatMissing("LymphNodes", "org.ohnlp.medkat.scr.types.SCRDisease");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_LymphNodes), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_LymphNodes), i);
  return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_LymphNodes), i);
  }
   
  /** @generated */ 
  public void setLymphNodes(int addr, int i, int v) {
        if (featOkTst && casFeat_LymphNodes == null)
      jcas.throwFeatMissing("LymphNodes", "org.ohnlp.medkat.scr.types.SCRDisease");
    if (lowLevelTypeChecks)
      ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_LymphNodes), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_LymphNodes), i);
    ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_LymphNodes), i, v);
  }
 
 
  /** @generated */
  final Feature casFeat_Stages;
  /** @generated */
  final int     casFeatCode_Stages;
  /** @generated */ 
  public int getStages(int addr) {
        if (featOkTst && casFeat_Stages == null)
      jcas.throwFeatMissing("Stages", "org.ohnlp.medkat.scr.types.SCRDisease");
    return ll_cas.ll_getRefValue(addr, casFeatCode_Stages);
  }
  /** @generated */    
  public void setStages(int addr, int v) {
        if (featOkTst && casFeat_Stages == null)
      jcas.throwFeatMissing("Stages", "org.ohnlp.medkat.scr.types.SCRDisease");
    ll_cas.ll_setRefValue(addr, casFeatCode_Stages, v);}
    
   /** @generated */
  public int getStages(int addr, int i) {
        if (featOkTst && casFeat_Stages == null)
      jcas.throwFeatMissing("Stages", "org.ohnlp.medkat.scr.types.SCRDisease");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_Stages), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_Stages), i);
  return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_Stages), i);
  }
   
  /** @generated */ 
  public void setStages(int addr, int i, int v) {
        if (featOkTst && casFeat_Stages == null)
      jcas.throwFeatMissing("Stages", "org.ohnlp.medkat.scr.types.SCRDisease");
    if (lowLevelTypeChecks)
      ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_Stages), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_Stages), i);
    ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_Stages), i, v);
  }
 
 
  /** @generated */
  final Feature casFeat_GrossDescription;
  /** @generated */
  final int     casFeatCode_GrossDescription;
  /** @generated */ 
  public int getGrossDescription(int addr) {
        if (featOkTst && casFeat_GrossDescription == null)
      jcas.throwFeatMissing("GrossDescription", "org.ohnlp.medkat.scr.types.SCRDisease");
    return ll_cas.ll_getRefValue(addr, casFeatCode_GrossDescription);
  }
  /** @generated */    
  public void setGrossDescription(int addr, int v) {
        if (featOkTst && casFeat_GrossDescription == null)
      jcas.throwFeatMissing("GrossDescription", "org.ohnlp.medkat.scr.types.SCRDisease");
    ll_cas.ll_setRefValue(addr, casFeatCode_GrossDescription, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public SCRDisease_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_TumorSpecimens = jcas.getRequiredFeatureDE(casType, "TumorSpecimens", "uima.cas.FSArray", featOkTst);
    casFeatCode_TumorSpecimens  = (null == casFeat_TumorSpecimens) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_TumorSpecimens).getCode();

 
    casFeat_LymphNodes = jcas.getRequiredFeatureDE(casType, "LymphNodes", "uima.cas.FSArray", featOkTst);
    casFeatCode_LymphNodes  = (null == casFeat_LymphNodes) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_LymphNodes).getCode();

 
    casFeat_Stages = jcas.getRequiredFeatureDE(casType, "Stages", "uima.cas.FSArray", featOkTst);
    casFeatCode_Stages  = (null == casFeat_Stages) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Stages).getCode();

 
    casFeat_GrossDescription = jcas.getRequiredFeatureDE(casType, "GrossDescription", "uima.tcas.Annotation", featOkTst);
    casFeatCode_GrossDescription  = (null == casFeat_GrossDescription) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_GrossDescription).getCode();

  }
}



    