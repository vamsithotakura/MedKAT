
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
import org.apache.uima.jcas.tcas.Annotation_Type;

/** Size dimension object
 * Updated by JCasGen Mon Mar 23 14:01:47 EDT 2009
 * @generated */
public class SCRDimension_Type extends Annotation_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (SCRDimension_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = SCRDimension_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new SCRDimension(addr, SCRDimension_Type.this);
  			   SCRDimension_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new SCRDimension(addr, SCRDimension_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = SCRDimension.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.ohnlp.medkat.scr.types.SCRDimension");
 
  /** @generated */
  final Feature casFeat_Extent;
  /** @generated */
  final int     casFeatCode_Extent;
  /** @generated */ 
  public String getExtent(int addr) {
        if (featOkTst && casFeat_Extent == null)
      jcas.throwFeatMissing("Extent", "org.ohnlp.medkat.scr.types.SCRDimension");
    return ll_cas.ll_getStringValue(addr, casFeatCode_Extent);
  }
  /** @generated */    
  public void setExtent(int addr, String v) {
        if (featOkTst && casFeat_Extent == null)
      jcas.throwFeatMissing("Extent", "org.ohnlp.medkat.scr.types.SCRDimension");
    ll_cas.ll_setStringValue(addr, casFeatCode_Extent, v);}
    
  
 
  /** @generated */
  final Feature casFeat_Unit;
  /** @generated */
  final int     casFeatCode_Unit;
  /** @generated */ 
  public String getUnit(int addr) {
        if (featOkTst && casFeat_Unit == null)
      jcas.throwFeatMissing("Unit", "org.ohnlp.medkat.scr.types.SCRDimension");
    return ll_cas.ll_getStringValue(addr, casFeatCode_Unit);
  }
  /** @generated */    
  public void setUnit(int addr, String v) {
        if (featOkTst && casFeat_Unit == null)
      jcas.throwFeatMissing("Unit", "org.ohnlp.medkat.scr.types.SCRDimension");
    ll_cas.ll_setStringValue(addr, casFeatCode_Unit, v);}
    
  
 
  /** @generated */
  final Feature casFeat_Inference;
  /** @generated */
  final int     casFeatCode_Inference;
  /** @generated */ 
  public int getInference(int addr) {
        if (featOkTst && casFeat_Inference == null)
      jcas.throwFeatMissing("Inference", "org.ohnlp.medkat.scr.types.SCRDimension");
    return ll_cas.ll_getIntValue(addr, casFeatCode_Inference);
  }
  /** @generated */    
  public void setInference(int addr, int v) {
        if (featOkTst && casFeat_Inference == null)
      jcas.throwFeatMissing("Inference", "org.ohnlp.medkat.scr.types.SCRDimension");
    ll_cas.ll_setIntValue(addr, casFeatCode_Inference, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public SCRDimension_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_Extent = jcas.getRequiredFeatureDE(casType, "Extent", "uima.cas.String", featOkTst);
    casFeatCode_Extent  = (null == casFeat_Extent) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Extent).getCode();

 
    casFeat_Unit = jcas.getRequiredFeatureDE(casType, "Unit", "uima.cas.String", featOkTst);
    casFeatCode_Unit  = (null == casFeat_Unit) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Unit).getCode();

 
    casFeat_Inference = jcas.getRequiredFeatureDE(casType, "Inference", "uima.cas.Integer", featOkTst);
    casFeatCode_Inference  = (null == casFeat_Inference) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Inference).getCode();

  }
}



    