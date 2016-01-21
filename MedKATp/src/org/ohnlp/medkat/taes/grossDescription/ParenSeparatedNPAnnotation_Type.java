
/* First created by JCasGen Wed Jun 11 12:10:52 EDT 2008 */
package org.ohnlp.medkat.taes.grossDescription;

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
public class ParenSeparatedNPAnnotation_Type extends Annotation_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (ParenSeparatedNPAnnotation_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = ParenSeparatedNPAnnotation_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new ParenSeparatedNPAnnotation(addr, ParenSeparatedNPAnnotation_Type.this);
  			   ParenSeparatedNPAnnotation_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new ParenSeparatedNPAnnotation(addr, ParenSeparatedNPAnnotation_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = ParenSeparatedNPAnnotation.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.ohnlp.medkat.taes.grossDescription.ParenSeparatedNPAnnotation");
 
  /** @generated */
  final Feature casFeat_insideParen;
  /** @generated */
  final int     casFeatCode_insideParen;
  /** @generated */ 
  public int getInsideParen(int addr) {
        if (featOkTst && casFeat_insideParen == null)
      jcas.throwFeatMissing("insideParen", "org.ohnlp.medkat.taes.grossDescription.ParenSeparatedNPAnnotation");
    return ll_cas.ll_getRefValue(addr, casFeatCode_insideParen);
  }
  /** @generated */    
  public void setInsideParen(int addr, int v) {
        if (featOkTst && casFeat_insideParen == null)
      jcas.throwFeatMissing("insideParen", "org.ohnlp.medkat.taes.grossDescription.ParenSeparatedNPAnnotation");
    ll_cas.ll_setRefValue(addr, casFeatCode_insideParen, v);}
    
   /** @generated */
  public int getInsideParen(int addr, int i) {
        if (featOkTst && casFeat_insideParen == null)
      jcas.throwFeatMissing("insideParen", "org.ohnlp.medkat.taes.grossDescription.ParenSeparatedNPAnnotation");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_insideParen), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_insideParen), i);
  return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_insideParen), i);
  }
   
  /** @generated */ 
  public void setInsideParen(int addr, int i, int v) {
        if (featOkTst && casFeat_insideParen == null)
      jcas.throwFeatMissing("insideParen", "org.ohnlp.medkat.taes.grossDescription.ParenSeparatedNPAnnotation");
    if (lowLevelTypeChecks)
      ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_insideParen), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_insideParen), i);
    ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_insideParen), i, v);
  }
 
 
  /** @generated */
  final Feature casFeat_leftOfParen;
  /** @generated */
  final int     casFeatCode_leftOfParen;
  /** @generated */ 
  public int getLeftOfParen(int addr) {
        if (featOkTst && casFeat_leftOfParen == null)
      jcas.throwFeatMissing("leftOfParen", "org.ohnlp.medkat.taes.grossDescription.ParenSeparatedNPAnnotation");
    return ll_cas.ll_getRefValue(addr, casFeatCode_leftOfParen);
  }
  /** @generated */    
  public void setLeftOfParen(int addr, int v) {
        if (featOkTst && casFeat_leftOfParen == null)
      jcas.throwFeatMissing("leftOfParen", "org.ohnlp.medkat.taes.grossDescription.ParenSeparatedNPAnnotation");
    ll_cas.ll_setRefValue(addr, casFeatCode_leftOfParen, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public ParenSeparatedNPAnnotation_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_insideParen = jcas.getRequiredFeatureDE(casType, "insideParen", "uima.cas.FSArray", featOkTst);
    casFeatCode_insideParen  = (null == casFeat_insideParen) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_insideParen).getCode();

 
    casFeat_leftOfParen = jcas.getRequiredFeatureDE(casType, "leftOfParen", "uima.tcas.Annotation", featOkTst);
    casFeatCode_leftOfParen  = (null == casFeat_leftOfParen) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_leftOfParen).getCode();

  }
}



    