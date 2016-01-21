
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

/** observation object
 * Updated by JCasGen Wed Mar 11 15:34:13 EDT 2009
 * @generated */
public class SCRObservation_Type extends Annotation_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (SCRObservation_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = SCRObservation_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new SCRObservation(addr, SCRObservation_Type.this);
  			   SCRObservation_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new SCRObservation(addr, SCRObservation_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = SCRObservation.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.ohnlp.medkat.scr.types.SCRObservation");
 
  /** @generated */
  final Feature casFeat_TumorSpecimen;
  /** @generated */
  final int     casFeatCode_TumorSpecimen;
  /** @generated */ 
  public int getTumorSpecimen(int addr) {
        if (featOkTst && casFeat_TumorSpecimen == null)
      jcas.throwFeatMissing("TumorSpecimen", "org.ohnlp.medkat.scr.types.SCRObservation");
    return ll_cas.ll_getRefValue(addr, casFeatCode_TumorSpecimen);
  }
  /** @generated */    
  public void setTumorSpecimen(int addr, int v) {
        if (featOkTst && casFeat_TumorSpecimen == null)
      jcas.throwFeatMissing("TumorSpecimen", "org.ohnlp.medkat.scr.types.SCRObservation");
    ll_cas.ll_setRefValue(addr, casFeatCode_TumorSpecimen, v);}
    
  
 
  /** @generated */
  final Feature casFeat_TissueBank;
  /** @generated */
  final int     casFeatCode_TissueBank;
  /** @generated */ 
  public int getTissueBank(int addr) {
        if (featOkTst && casFeat_TissueBank == null)
      jcas.throwFeatMissing("TissueBank", "org.ohnlp.medkat.scr.types.SCRObservation");
    return ll_cas.ll_getRefValue(addr, casFeatCode_TissueBank);
  }
  /** @generated */    
  public void setTissueBank(int addr, int v) {
        if (featOkTst && casFeat_TissueBank == null)
      jcas.throwFeatMissing("TissueBank", "org.ohnlp.medkat.scr.types.SCRObservation");
    ll_cas.ll_setRefValue(addr, casFeatCode_TissueBank, v);}
    
  
 
  /** @generated */
  final Feature casFeat_DocumentType;
  /** @generated */
  final int     casFeatCode_DocumentType;
  /** @generated */ 
  public int getDocumentType(int addr) {
        if (featOkTst && casFeat_DocumentType == null)
      jcas.throwFeatMissing("DocumentType", "org.ohnlp.medkat.scr.types.SCRObservation");
    return ll_cas.ll_getRefValue(addr, casFeatCode_DocumentType);
  }
  /** @generated */    
  public void setDocumentType(int addr, int v) {
        if (featOkTst && casFeat_DocumentType == null)
      jcas.throwFeatMissing("DocumentType", "org.ohnlp.medkat.scr.types.SCRObservation");
    ll_cas.ll_setRefValue(addr, casFeatCode_DocumentType, v);}
    
  
 
  /** @generated */
  final Feature casFeat_TumorBlocks;
  /** @generated */
  final int     casFeatCode_TumorBlocks;
  /** @generated */ 
  public int getTumorBlocks(int addr) {
        if (featOkTst && casFeat_TumorBlocks == null)
      jcas.throwFeatMissing("TumorBlocks", "org.ohnlp.medkat.scr.types.SCRObservation");
    return ll_cas.ll_getRefValue(addr, casFeatCode_TumorBlocks);
  }
  /** @generated */    
  public void setTumorBlocks(int addr, int v) {
        if (featOkTst && casFeat_TumorBlocks == null)
      jcas.throwFeatMissing("TumorBlocks", "org.ohnlp.medkat.scr.types.SCRObservation");
    ll_cas.ll_setRefValue(addr, casFeatCode_TumorBlocks, v);}
    
   /** @generated */
  public int getTumorBlocks(int addr, int i) {
        if (featOkTst && casFeat_TumorBlocks == null)
      jcas.throwFeatMissing("TumorBlocks", "org.ohnlp.medkat.scr.types.SCRObservation");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_TumorBlocks), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_TumorBlocks), i);
  return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_TumorBlocks), i);
  }
   
  /** @generated */ 
  public void setTumorBlocks(int addr, int i, int v) {
        if (featOkTst && casFeat_TumorBlocks == null)
      jcas.throwFeatMissing("TumorBlocks", "org.ohnlp.medkat.scr.types.SCRObservation");
    if (lowLevelTypeChecks)
      ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_TumorBlocks), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_TumorBlocks), i);
    ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_TumorBlocks), i, v);
  }
 



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public SCRObservation_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_TumorSpecimen = jcas.getRequiredFeatureDE(casType, "TumorSpecimen", "uima.tcas.Annotation", featOkTst);
    casFeatCode_TumorSpecimen  = (null == casFeat_TumorSpecimen) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_TumorSpecimen).getCode();

 
    casFeat_TissueBank = jcas.getRequiredFeatureDE(casType, "TissueBank", "uima.tcas.Annotation", featOkTst);
    casFeatCode_TissueBank  = (null == casFeat_TissueBank) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_TissueBank).getCode();

 
    casFeat_DocumentType = jcas.getRequiredFeatureDE(casType, "DocumentType", "uima.tcas.Annotation", featOkTst);
    casFeatCode_DocumentType  = (null == casFeat_DocumentType) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_DocumentType).getCode();

 
    casFeat_TumorBlocks = jcas.getRequiredFeatureDE(casType, "TumorBlocks", "uima.cas.FSArray", featOkTst);
    casFeatCode_TumorBlocks  = (null == casFeat_TumorBlocks) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_TumorBlocks).getCode();

  }
}



    