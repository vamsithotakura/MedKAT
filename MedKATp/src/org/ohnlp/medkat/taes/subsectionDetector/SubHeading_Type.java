
/* First created by JCasGen Wed Jun 11 12:10:49 EDT 2008 */
package org.ohnlp.medkat.taes.subsectionDetector;

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
 * Updated by JCasGen Mon Mar 23 14:01:46 EDT 2009
 * @generated */
public class SubHeading_Type extends Annotation_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (SubHeading_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = SubHeading_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new SubHeading(addr, SubHeading_Type.this);
  			   SubHeading_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new SubHeading(addr, SubHeading_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = SubHeading.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.ohnlp.medkat.taes.subsectionDetector.SubHeading");
 
  /** @generated */
  final Feature casFeat_subSectionNumber;
  /** @generated */
  final int     casFeatCode_subSectionNumber;
  /** @generated */ 
  public int getSubSectionNumber(int addr) {
        if (featOkTst && casFeat_subSectionNumber == null)
      jcas.throwFeatMissing("subSectionNumber", "org.ohnlp.medkat.taes.subsectionDetector.SubHeading");
    return ll_cas.ll_getIntValue(addr, casFeatCode_subSectionNumber);
  }
  /** @generated */    
  public void setSubSectionNumber(int addr, int v) {
        if (featOkTst && casFeat_subSectionNumber == null)
      jcas.throwFeatMissing("subSectionNumber", "org.ohnlp.medkat.taes.subsectionDetector.SubHeading");
    ll_cas.ll_setIntValue(addr, casFeatCode_subSectionNumber, v);}
    
  
 
  /** @generated */
  final Feature casFeat_subSubsections;
  /** @generated */
  final int     casFeatCode_subSubsections;
  /** @generated */ 
  public int getSubSubsections(int addr) {
        if (featOkTst && casFeat_subSubsections == null)
      jcas.throwFeatMissing("subSubsections", "org.ohnlp.medkat.taes.subsectionDetector.SubHeading");
    return ll_cas.ll_getRefValue(addr, casFeatCode_subSubsections);
  }
  /** @generated */    
  public void setSubSubsections(int addr, int v) {
        if (featOkTst && casFeat_subSubsections == null)
      jcas.throwFeatMissing("subSubsections", "org.ohnlp.medkat.taes.subsectionDetector.SubHeading");
    ll_cas.ll_setRefValue(addr, casFeatCode_subSubsections, v);}
    
   /** @generated */
  public int getSubSubsections(int addr, int i) {
        if (featOkTst && casFeat_subSubsections == null)
      jcas.throwFeatMissing("subSubsections", "org.ohnlp.medkat.taes.subsectionDetector.SubHeading");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_subSubsections), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_subSubsections), i);
  return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_subSubsections), i);
  }
   
  /** @generated */ 
  public void setSubSubsections(int addr, int i, int v) {
        if (featOkTst && casFeat_subSubsections == null)
      jcas.throwFeatMissing("subSubsections", "org.ohnlp.medkat.taes.subsectionDetector.SubHeading");
    if (lowLevelTypeChecks)
      ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_subSubsections), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_subSubsections), i);
    ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_subSubsections), i, v);
  }
 
 
  /** @generated */
  final Feature casFeat_content;
  /** @generated */
  final int     casFeatCode_content;
  /** @generated */ 
  public String getContent(int addr) {
        if (featOkTst && casFeat_content == null)
      jcas.throwFeatMissing("content", "org.ohnlp.medkat.taes.subsectionDetector.SubHeading");
    return ll_cas.ll_getStringValue(addr, casFeatCode_content);
  }
  /** @generated */    
  public void setContent(int addr, String v) {
        if (featOkTst && casFeat_content == null)
      jcas.throwFeatMissing("content", "org.ohnlp.medkat.taes.subsectionDetector.SubHeading");
    ll_cas.ll_setStringValue(addr, casFeatCode_content, v);}
    
  
 
  /** @generated */
  final Feature casFeat_prefix;
  /** @generated */
  final int     casFeatCode_prefix;
  /** @generated */ 
  public String getPrefix(int addr) {
        if (featOkTst && casFeat_prefix == null)
      jcas.throwFeatMissing("prefix", "org.ohnlp.medkat.taes.subsectionDetector.SubHeading");
    return ll_cas.ll_getStringValue(addr, casFeatCode_prefix);
  }
  /** @generated */    
  public void setPrefix(int addr, String v) {
        if (featOkTst && casFeat_prefix == null)
      jcas.throwFeatMissing("prefix", "org.ohnlp.medkat.taes.subsectionDetector.SubHeading");
    ll_cas.ll_setStringValue(addr, casFeatCode_prefix, v);}
    
  
 
  /** @generated */
  final Feature casFeat_contentBegin;
  /** @generated */
  final int     casFeatCode_contentBegin;
  /** @generated */ 
  public int getContentBegin(int addr) {
        if (featOkTst && casFeat_contentBegin == null)
      jcas.throwFeatMissing("contentBegin", "org.ohnlp.medkat.taes.subsectionDetector.SubHeading");
    return ll_cas.ll_getIntValue(addr, casFeatCode_contentBegin);
  }
  /** @generated */    
  public void setContentBegin(int addr, int v) {
        if (featOkTst && casFeat_contentBegin == null)
      jcas.throwFeatMissing("contentBegin", "org.ohnlp.medkat.taes.subsectionDetector.SubHeading");
    ll_cas.ll_setIntValue(addr, casFeatCode_contentBegin, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public SubHeading_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_subSectionNumber = jcas.getRequiredFeatureDE(casType, "subSectionNumber", "uima.cas.Integer", featOkTst);
    casFeatCode_subSectionNumber  = (null == casFeat_subSectionNumber) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_subSectionNumber).getCode();

 
    casFeat_subSubsections = jcas.getRequiredFeatureDE(casType, "subSubsections", "uima.cas.FSArray", featOkTst);
    casFeatCode_subSubsections  = (null == casFeat_subSubsections) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_subSubsections).getCode();

 
    casFeat_content = jcas.getRequiredFeatureDE(casType, "content", "uima.cas.String", featOkTst);
    casFeatCode_content  = (null == casFeat_content) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_content).getCode();

 
    casFeat_prefix = jcas.getRequiredFeatureDE(casType, "prefix", "uima.cas.String", featOkTst);
    casFeatCode_prefix  = (null == casFeat_prefix) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_prefix).getCode();

 
    casFeat_contentBegin = jcas.getRequiredFeatureDE(casType, "contentBegin", "uima.cas.Integer", featOkTst);
    casFeatCode_contentBegin  = (null == casFeat_contentBegin) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_contentBegin).getCode();

  }
}



    