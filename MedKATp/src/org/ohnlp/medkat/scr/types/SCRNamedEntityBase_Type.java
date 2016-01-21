
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

/** base class for SCR named entity types
 * Updated by JCasGen Mon Mar 23 14:01:47 EDT 2009
 * @generated */
public class SCRNamedEntityBase_Type extends SCRSpannedAnnotation_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (SCRNamedEntityBase_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = SCRNamedEntityBase_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new SCRNamedEntityBase(addr, SCRNamedEntityBase_Type.this);
  			   SCRNamedEntityBase_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new SCRNamedEntityBase(addr, SCRNamedEntityBase_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = SCRNamedEntityBase.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.ohnlp.medkat.scr.types.SCRNamedEntityBase");
 
  /** @generated */
  final Feature casFeat_Terminology;
  /** @generated */
  final int     casFeatCode_Terminology;
  /** @generated */ 
  public String getTerminology(int addr) {
        if (featOkTst && casFeat_Terminology == null)
      jcas.throwFeatMissing("Terminology", "org.ohnlp.medkat.scr.types.SCRNamedEntityBase");
    return ll_cas.ll_getStringValue(addr, casFeatCode_Terminology);
  }
  /** @generated */    
  public void setTerminology(int addr, String v) {
        if (featOkTst && casFeat_Terminology == null)
      jcas.throwFeatMissing("Terminology", "org.ohnlp.medkat.scr.types.SCRNamedEntityBase");
    ll_cas.ll_setStringValue(addr, casFeatCode_Terminology, v);}
    
  
 
  /** @generated */
  final Feature casFeat_Code;
  /** @generated */
  final int     casFeatCode_Code;
  /** @generated */ 
  public String getCode(int addr) {
        if (featOkTst && casFeat_Code == null)
      jcas.throwFeatMissing("Code", "org.ohnlp.medkat.scr.types.SCRNamedEntityBase");
    return ll_cas.ll_getStringValue(addr, casFeatCode_Code);
  }
  /** @generated */    
  public void setCode(int addr, String v) {
        if (featOkTst && casFeat_Code == null)
      jcas.throwFeatMissing("Code", "org.ohnlp.medkat.scr.types.SCRNamedEntityBase");
    ll_cas.ll_setStringValue(addr, casFeatCode_Code, v);}
    
  
 
  /** @generated */
  final Feature casFeat_Negation;
  /** @generated */
  final int     casFeatCode_Negation;
  /** @generated */ 
  public int getNegation(int addr) {
        if (featOkTst && casFeat_Negation == null)
      jcas.throwFeatMissing("Negation", "org.ohnlp.medkat.scr.types.SCRNamedEntityBase");
    return ll_cas.ll_getIntValue(addr, casFeatCode_Negation);
  }
  /** @generated */    
  public void setNegation(int addr, int v) {
        if (featOkTst && casFeat_Negation == null)
      jcas.throwFeatMissing("Negation", "org.ohnlp.medkat.scr.types.SCRNamedEntityBase");
    ll_cas.ll_setIntValue(addr, casFeatCode_Negation, v);}
    
  
 
  /** @generated */
  final Feature casFeat_Inference;
  /** @generated */
  final int     casFeatCode_Inference;
  /** @generated */ 
  public int getInference(int addr) {
        if (featOkTst && casFeat_Inference == null)
      jcas.throwFeatMissing("Inference", "org.ohnlp.medkat.scr.types.SCRNamedEntityBase");
    return ll_cas.ll_getIntValue(addr, casFeatCode_Inference);
  }
  /** @generated */    
  public void setInference(int addr, int v) {
        if (featOkTst && casFeat_Inference == null)
      jcas.throwFeatMissing("Inference", "org.ohnlp.medkat.scr.types.SCRNamedEntityBase");
    ll_cas.ll_setIntValue(addr, casFeatCode_Inference, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public SCRNamedEntityBase_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_Terminology = jcas.getRequiredFeatureDE(casType, "Terminology", "uima.cas.String", featOkTst);
    casFeatCode_Terminology  = (null == casFeat_Terminology) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Terminology).getCode();

 
    casFeat_Code = jcas.getRequiredFeatureDE(casType, "Code", "uima.cas.String", featOkTst);
    casFeatCode_Code  = (null == casFeat_Code) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Code).getCode();

 
    casFeat_Negation = jcas.getRequiredFeatureDE(casType, "Negation", "uima.cas.Integer", featOkTst);
    casFeatCode_Negation  = (null == casFeat_Negation) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Negation).getCode();

 
    casFeat_Inference = jcas.getRequiredFeatureDE(casType, "Inference", "uima.cas.Integer", featOkTst);
    casFeatCode_Inference  = (null == casFeat_Inference) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Inference).getCode();

  }
}



    