
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

/** Size dimension object
 * Updated by JCasGen Wed Mar 11 15:34:12 EDT 2009
 * @generated */
public class SCRMargin_Type extends Annotation_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (SCRMargin_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = SCRMargin_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new SCRMargin(addr, SCRMargin_Type.this);
  			   SCRMargin_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new SCRMargin(addr, SCRMargin_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = SCRMargin.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.ohnlp.medkat.scr.types.SCRMargin");
 
  /** @generated */
  final Feature casFeat_State;
  /** @generated */
  final int     casFeatCode_State;
  /** @generated */ 
  public String getState(int addr) {
        if (featOkTst && casFeat_State == null)
      jcas.throwFeatMissing("State", "org.ohnlp.medkat.scr.types.SCRMargin");
    return ll_cas.ll_getStringValue(addr, casFeatCode_State);
  }
  /** @generated */    
  public void setState(int addr, String v) {
        if (featOkTst && casFeat_State == null)
      jcas.throwFeatMissing("State", "org.ohnlp.medkat.scr.types.SCRMargin");
    ll_cas.ll_setStringValue(addr, casFeatCode_State, v);}
    
  
 
  /** @generated */
  final Feature casFeat_MarginType;
  /** @generated */
  final int     casFeatCode_MarginType;
  /** @generated */ 
  public String getMarginType(int addr) {
        if (featOkTst && casFeat_MarginType == null)
      jcas.throwFeatMissing("MarginType", "org.ohnlp.medkat.scr.types.SCRMargin");
    return ll_cas.ll_getStringValue(addr, casFeatCode_MarginType);
  }
  /** @generated */    
  public void setMarginType(int addr, String v) {
        if (featOkTst && casFeat_MarginType == null)
      jcas.throwFeatMissing("MarginType", "org.ohnlp.medkat.scr.types.SCRMargin");
    ll_cas.ll_setStringValue(addr, casFeatCode_MarginType, v);}
    
  
 
  /** @generated */
  final Feature casFeat_Inference;
  /** @generated */
  final int     casFeatCode_Inference;
  /** @generated */ 
  public int getInference(int addr) {
        if (featOkTst && casFeat_Inference == null)
      jcas.throwFeatMissing("Inference", "org.ohnlp.medkat.scr.types.SCRMargin");
    return ll_cas.ll_getIntValue(addr, casFeatCode_Inference);
  }
  /** @generated */    
  public void setInference(int addr, int v) {
        if (featOkTst && casFeat_Inference == null)
      jcas.throwFeatMissing("Inference", "org.ohnlp.medkat.scr.types.SCRMargin");
    ll_cas.ll_setIntValue(addr, casFeatCode_Inference, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public SCRMargin_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_State = jcas.getRequiredFeatureDE(casType, "State", "uima.cas.String", featOkTst);
    casFeatCode_State  = (null == casFeat_State) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_State).getCode();

 
    casFeat_MarginType = jcas.getRequiredFeatureDE(casType, "MarginType", "uima.cas.String", featOkTst);
    casFeatCode_MarginType  = (null == casFeat_MarginType) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_MarginType).getCode();

 
    casFeat_Inference = jcas.getRequiredFeatureDE(casType, "Inference", "uima.cas.Integer", featOkTst);
    casFeatCode_Inference  = (null == casFeat_Inference) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Inference).getCode();

  }
}



    