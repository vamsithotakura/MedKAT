
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

/** description of lymph nodes reading
 * Updated by JCasGen Mon Mar 23 14:01:48 EDT 2009
 * @generated */
public class SCRLymphNodesReading_Type extends SCRGenericReading_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (SCRLymphNodesReading_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = SCRLymphNodesReading_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new SCRLymphNodesReading(addr, SCRLymphNodesReading_Type.this);
  			   SCRLymphNodesReading_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new SCRLymphNodesReading(addr, SCRLymphNodesReading_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = SCRLymphNodesReading.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.ohnlp.medkat.scr.types.SCRLymphNodesReading");
 
  /** @generated */
  final Feature casFeat_Sites;
  /** @generated */
  final int     casFeatCode_Sites;
  /** @generated */ 
  public int getSites(int addr) {
        if (featOkTst && casFeat_Sites == null)
      jcas.throwFeatMissing("Sites", "org.ohnlp.medkat.scr.types.SCRLymphNodesReading");
    return ll_cas.ll_getRefValue(addr, casFeatCode_Sites);
  }
  /** @generated */    
  public void setSites(int addr, int v) {
        if (featOkTst && casFeat_Sites == null)
      jcas.throwFeatMissing("Sites", "org.ohnlp.medkat.scr.types.SCRLymphNodesReading");
    ll_cas.ll_setRefValue(addr, casFeatCode_Sites, v);}
    
   /** @generated */
  public int getSites(int addr, int i) {
        if (featOkTst && casFeat_Sites == null)
      jcas.throwFeatMissing("Sites", "org.ohnlp.medkat.scr.types.SCRLymphNodesReading");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_Sites), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_Sites), i);
  return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_Sites), i);
  }
   
  /** @generated */ 
  public void setSites(int addr, int i, int v) {
        if (featOkTst && casFeat_Sites == null)
      jcas.throwFeatMissing("Sites", "org.ohnlp.medkat.scr.types.SCRLymphNodesReading");
    if (lowLevelTypeChecks)
      ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_Sites), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_Sites), i);
    ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_Sites), i, v);
  }
 
 
  /** @generated */
  final Feature casFeat_Diagnoses;
  /** @generated */
  final int     casFeatCode_Diagnoses;
  /** @generated */ 
  public int getDiagnoses(int addr) {
        if (featOkTst && casFeat_Diagnoses == null)
      jcas.throwFeatMissing("Diagnoses", "org.ohnlp.medkat.scr.types.SCRLymphNodesReading");
    return ll_cas.ll_getRefValue(addr, casFeatCode_Diagnoses);
  }
  /** @generated */    
  public void setDiagnoses(int addr, int v) {
        if (featOkTst && casFeat_Diagnoses == null)
      jcas.throwFeatMissing("Diagnoses", "org.ohnlp.medkat.scr.types.SCRLymphNodesReading");
    ll_cas.ll_setRefValue(addr, casFeatCode_Diagnoses, v);}
    
   /** @generated */
  public int getDiagnoses(int addr, int i) {
        if (featOkTst && casFeat_Diagnoses == null)
      jcas.throwFeatMissing("Diagnoses", "org.ohnlp.medkat.scr.types.SCRLymphNodesReading");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_Diagnoses), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_Diagnoses), i);
  return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_Diagnoses), i);
  }
   
  /** @generated */ 
  public void setDiagnoses(int addr, int i, int v) {
        if (featOkTst && casFeat_Diagnoses == null)
      jcas.throwFeatMissing("Diagnoses", "org.ohnlp.medkat.scr.types.SCRLymphNodesReading");
    if (lowLevelTypeChecks)
      ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_Diagnoses), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_Diagnoses), i);
    ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_Diagnoses), i, v);
  }
 
 
  /** @generated */
  final Feature casFeat_PositiveNodes;
  /** @generated */
  final int     casFeatCode_PositiveNodes;
  /** @generated */ 
  public int getPositiveNodes(int addr) {
        if (featOkTst && casFeat_PositiveNodes == null)
      jcas.throwFeatMissing("PositiveNodes", "org.ohnlp.medkat.scr.types.SCRLymphNodesReading");
    return ll_cas.ll_getIntValue(addr, casFeatCode_PositiveNodes);
  }
  /** @generated */    
  public void setPositiveNodes(int addr, int v) {
        if (featOkTst && casFeat_PositiveNodes == null)
      jcas.throwFeatMissing("PositiveNodes", "org.ohnlp.medkat.scr.types.SCRLymphNodesReading");
    ll_cas.ll_setIntValue(addr, casFeatCode_PositiveNodes, v);}
    
  
 
  /** @generated */
  final Feature casFeat_TotalNodes;
  /** @generated */
  final int     casFeatCode_TotalNodes;
  /** @generated */ 
  public int getTotalNodes(int addr) {
        if (featOkTst && casFeat_TotalNodes == null)
      jcas.throwFeatMissing("TotalNodes", "org.ohnlp.medkat.scr.types.SCRLymphNodesReading");
    return ll_cas.ll_getIntValue(addr, casFeatCode_TotalNodes);
  }
  /** @generated */    
  public void setTotalNodes(int addr, int v) {
        if (featOkTst && casFeat_TotalNodes == null)
      jcas.throwFeatMissing("TotalNodes", "org.ohnlp.medkat.scr.types.SCRLymphNodesReading");
    ll_cas.ll_setIntValue(addr, casFeatCode_TotalNodes, v);}
    
  
 
  /** @generated */
  final Feature casFeat_Inference;
  /** @generated */
  final int     casFeatCode_Inference;
  /** @generated */ 
  public int getInference(int addr) {
        if (featOkTst && casFeat_Inference == null)
      jcas.throwFeatMissing("Inference", "org.ohnlp.medkat.scr.types.SCRLymphNodesReading");
    return ll_cas.ll_getIntValue(addr, casFeatCode_Inference);
  }
  /** @generated */    
  public void setInference(int addr, int v) {
        if (featOkTst && casFeat_Inference == null)
      jcas.throwFeatMissing("Inference", "org.ohnlp.medkat.scr.types.SCRLymphNodesReading");
    ll_cas.ll_setIntValue(addr, casFeatCode_Inference, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public SCRLymphNodesReading_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_Sites = jcas.getRequiredFeatureDE(casType, "Sites", "uima.cas.FSArray", featOkTst);
    casFeatCode_Sites  = (null == casFeat_Sites) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Sites).getCode();

 
    casFeat_Diagnoses = jcas.getRequiredFeatureDE(casType, "Diagnoses", "uima.cas.FSArray", featOkTst);
    casFeatCode_Diagnoses  = (null == casFeat_Diagnoses) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Diagnoses).getCode();

 
    casFeat_PositiveNodes = jcas.getRequiredFeatureDE(casType, "PositiveNodes", "uima.cas.Integer", featOkTst);
    casFeatCode_PositiveNodes  = (null == casFeat_PositiveNodes) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_PositiveNodes).getCode();

 
    casFeat_TotalNodes = jcas.getRequiredFeatureDE(casType, "TotalNodes", "uima.cas.Integer", featOkTst);
    casFeatCode_TotalNodes  = (null == casFeat_TotalNodes) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_TotalNodes).getCode();

 
    casFeat_Inference = jcas.getRequiredFeatureDE(casType, "Inference", "uima.cas.Integer", featOkTst);
    casFeatCode_Inference  = (null == casFeat_Inference) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Inference).getCode();

  }
}



    