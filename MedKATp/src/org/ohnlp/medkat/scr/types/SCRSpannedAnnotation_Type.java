
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
import org.apache.uima.jcas.tcas.Annotation_Type;

/** Annotation that contains text fragments relevant to a specific concept
 * Updated by JCasGen Mon Mar 23 14:01:46 EDT 2009
 * @generated */
public class SCRSpannedAnnotation_Type extends Annotation_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (SCRSpannedAnnotation_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = SCRSpannedAnnotation_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new SCRSpannedAnnotation(addr, SCRSpannedAnnotation_Type.this);
  			   SCRSpannedAnnotation_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new SCRSpannedAnnotation(addr, SCRSpannedAnnotation_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = SCRSpannedAnnotation.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.ohnlp.medkat.scr.types.SCRSpannedAnnotation");
 
  /** @generated */
  final Feature casFeat_Fragments;
  /** @generated */
  final int     casFeatCode_Fragments;
  /** @generated */ 
  public int getFragments(int addr) {
        if (featOkTst && casFeat_Fragments == null)
      jcas.throwFeatMissing("Fragments", "org.ohnlp.medkat.scr.types.SCRSpannedAnnotation");
    return ll_cas.ll_getRefValue(addr, casFeatCode_Fragments);
  }
  /** @generated */    
  public void setFragments(int addr, int v) {
        if (featOkTst && casFeat_Fragments == null)
      jcas.throwFeatMissing("Fragments", "org.ohnlp.medkat.scr.types.SCRSpannedAnnotation");
    ll_cas.ll_setRefValue(addr, casFeatCode_Fragments, v);}
    
   /** @generated */
  public int getFragments(int addr, int i) {
        if (featOkTst && casFeat_Fragments == null)
      jcas.throwFeatMissing("Fragments", "org.ohnlp.medkat.scr.types.SCRSpannedAnnotation");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_Fragments), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_Fragments), i);
  return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_Fragments), i);
  }
   
  /** @generated */ 
  public void setFragments(int addr, int i, int v) {
        if (featOkTst && casFeat_Fragments == null)
      jcas.throwFeatMissing("Fragments", "org.ohnlp.medkat.scr.types.SCRSpannedAnnotation");
    if (lowLevelTypeChecks)
      ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_Fragments), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_Fragments), i);
    ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_Fragments), i, v);
  }
 



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public SCRSpannedAnnotation_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_Fragments = jcas.getRequiredFeatureDE(casType, "Fragments", "uima.cas.FSArray", featOkTst);
    casFeatCode_Fragments  = (null == casFeat_Fragments) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Fragments).getCode();

  }
}



    