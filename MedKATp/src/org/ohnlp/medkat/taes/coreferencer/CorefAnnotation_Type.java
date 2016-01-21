
/* First created by JCasGen Wed Jun 11 12:10:51 EDT 2008 */
package org.ohnlp.medkat.taes.coreferencer;

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

/** 
 * Updated by JCasGen Mon Mar 23 14:01:47 EDT 2009
 * @generated */
public class CorefAnnotation_Type extends Annotation_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (CorefAnnotation_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = CorefAnnotation_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new CorefAnnotation(addr, CorefAnnotation_Type.this);
  			   CorefAnnotation_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new CorefAnnotation(addr, CorefAnnotation_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = CorefAnnotation.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.ohnlp.medkat.taes.coreferencer.CorefAnnotation");
 
  /** @generated */
  final Feature casFeat_subsectionNumber;
  /** @generated */
  final int     casFeatCode_subsectionNumber;
  /** @generated */ 
  public int getSubsectionNumber(int addr) {
        if (featOkTst && casFeat_subsectionNumber == null)
      jcas.throwFeatMissing("subsectionNumber", "org.ohnlp.medkat.taes.coreferencer.CorefAnnotation");
    return ll_cas.ll_getIntValue(addr, casFeatCode_subsectionNumber);
  }
  /** @generated */    
  public void setSubsectionNumber(int addr, int v) {
        if (featOkTst && casFeat_subsectionNumber == null)
      jcas.throwFeatMissing("subsectionNumber", "org.ohnlp.medkat.taes.coreferencer.CorefAnnotation");
    ll_cas.ll_setIntValue(addr, casFeatCode_subsectionNumber, v);}
    
  
 
  /** @generated */
  final Feature casFeat_elements;
  /** @generated */
  final int     casFeatCode_elements;
  /** @generated */ 
  public int getElements(int addr) {
        if (featOkTst && casFeat_elements == null)
      jcas.throwFeatMissing("elements", "org.ohnlp.medkat.taes.coreferencer.CorefAnnotation");
    return ll_cas.ll_getRefValue(addr, casFeatCode_elements);
  }
  /** @generated */    
  public void setElements(int addr, int v) {
        if (featOkTst && casFeat_elements == null)
      jcas.throwFeatMissing("elements", "org.ohnlp.medkat.taes.coreferencer.CorefAnnotation");
    ll_cas.ll_setRefValue(addr, casFeatCode_elements, v);}
    
   /** @generated */
  public int getElements(int addr, int i) {
        if (featOkTst && casFeat_elements == null)
      jcas.throwFeatMissing("elements", "org.ohnlp.medkat.taes.coreferencer.CorefAnnotation");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_elements), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_elements), i);
  return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_elements), i);
  }
   
  /** @generated */ 
  public void setElements(int addr, int i, int v) {
        if (featOkTst && casFeat_elements == null)
      jcas.throwFeatMissing("elements", "org.ohnlp.medkat.taes.coreferencer.CorefAnnotation");
    if (lowLevelTypeChecks)
      ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_elements), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_elements), i);
    ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_elements), i, v);
  }
 



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public CorefAnnotation_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_subsectionNumber = jcas.getRequiredFeatureDE(casType, "subsectionNumber", "uima.cas.Integer", featOkTst);
    casFeatCode_subsectionNumber  = (null == casFeat_subsectionNumber) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_subsectionNumber).getCode();

 
    casFeat_elements = jcas.getRequiredFeatureDE(casType, "elements", "uima.cas.FSArray", featOkTst);
    casFeatCode_elements  = (null == casFeat_elements) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_elements).getCode();

  }
}



    