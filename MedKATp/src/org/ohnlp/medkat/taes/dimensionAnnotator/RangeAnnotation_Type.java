
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
public class RangeAnnotation_Type extends Annotation_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (RangeAnnotation_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = RangeAnnotation_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new RangeAnnotation(addr, RangeAnnotation_Type.this);
  			   RangeAnnotation_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new RangeAnnotation(addr, RangeAnnotation_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = RangeAnnotation.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.ohnlp.medkat.taes.dimensionAnnotator.RangeAnnotation");
 
  /** @generated */
  final Feature casFeat_low;
  /** @generated */
  final int     casFeatCode_low;
  /** @generated */ 
  public int getLow(int addr) {
        if (featOkTst && casFeat_low == null)
      jcas.throwFeatMissing("low", "org.ohnlp.medkat.taes.dimensionAnnotator.RangeAnnotation");
    return ll_cas.ll_getRefValue(addr, casFeatCode_low);
  }
  /** @generated */    
  public void setLow(int addr, int v) {
        if (featOkTst && casFeat_low == null)
      jcas.throwFeatMissing("low", "org.ohnlp.medkat.taes.dimensionAnnotator.RangeAnnotation");
    ll_cas.ll_setRefValue(addr, casFeatCode_low, v);}
    
  
 
  /** @generated */
  final Feature casFeat_high;
  /** @generated */
  final int     casFeatCode_high;
  /** @generated */ 
  public int getHigh(int addr) {
        if (featOkTst && casFeat_high == null)
      jcas.throwFeatMissing("high", "org.ohnlp.medkat.taes.dimensionAnnotator.RangeAnnotation");
    return ll_cas.ll_getRefValue(addr, casFeatCode_high);
  }
  /** @generated */    
  public void setHigh(int addr, int v) {
        if (featOkTst && casFeat_high == null)
      jcas.throwFeatMissing("high", "org.ohnlp.medkat.taes.dimensionAnnotator.RangeAnnotation");
    ll_cas.ll_setRefValue(addr, casFeatCode_high, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public RangeAnnotation_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_low = jcas.getRequiredFeatureDE(casType, "low", "org.ohnlp.medkat.taes.dimensionAnnotator.DimensionSetAnnotation", featOkTst);
    casFeatCode_low  = (null == casFeat_low) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_low).getCode();

 
    casFeat_high = jcas.getRequiredFeatureDE(casType, "high", "org.ohnlp.medkat.taes.dimensionAnnotator.DimensionSetAnnotation", featOkTst);
    casFeatCode_high  = (null == casFeat_high) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_high).getCode();

  }
}



    