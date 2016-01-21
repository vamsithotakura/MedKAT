
/* First created by JCasGen Wed Jun 11 12:10:52 EDT 2008 */
package org.ohnlp.medkat.taes.dimensionAnnotator;

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
public class ExtentAnnotation_Type extends Annotation_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (ExtentAnnotation_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = ExtentAnnotation_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new ExtentAnnotation(addr, ExtentAnnotation_Type.this);
  			   ExtentAnnotation_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new ExtentAnnotation(addr, ExtentAnnotation_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = ExtentAnnotation.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.ohnlp.medkat.taes.dimensionAnnotator.ExtentAnnotation");
 
  /** @generated */
  final Feature casFeat_equality;
  /** @generated */
  final int     casFeatCode_equality;
  /** @generated */ 
  public String getEquality(int addr) {
        if (featOkTst && casFeat_equality == null)
      jcas.throwFeatMissing("equality", "org.ohnlp.medkat.taes.dimensionAnnotator.ExtentAnnotation");
    return ll_cas.ll_getStringValue(addr, casFeatCode_equality);
  }
  /** @generated */    
  public void setEquality(int addr, String v) {
        if (featOkTst && casFeat_equality == null)
      jcas.throwFeatMissing("equality", "org.ohnlp.medkat.taes.dimensionAnnotator.ExtentAnnotation");
    ll_cas.ll_setStringValue(addr, casFeatCode_equality, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public ExtentAnnotation_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_equality = jcas.getRequiredFeatureDE(casType, "equality", "uima.cas.String", featOkTst);
    casFeatCode_equality  = (null == casFeat_equality) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_equality).getCode();

  }
}



    