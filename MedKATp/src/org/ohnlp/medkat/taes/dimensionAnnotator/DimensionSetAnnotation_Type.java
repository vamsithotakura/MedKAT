
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
public class DimensionSetAnnotation_Type extends Annotation_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (DimensionSetAnnotation_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = DimensionSetAnnotation_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new DimensionSetAnnotation(addr, DimensionSetAnnotation_Type.this);
  			   DimensionSetAnnotation_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new DimensionSetAnnotation(addr, DimensionSetAnnotation_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = DimensionSetAnnotation.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.ohnlp.medkat.taes.dimensionAnnotator.DimensionSetAnnotation");
 
  /** @generated */
  final Feature casFeat_dimensions;
  /** @generated */
  final int     casFeatCode_dimensions;
  /** @generated */ 
  public int getDimensions(int addr) {
        if (featOkTst && casFeat_dimensions == null)
      jcas.throwFeatMissing("dimensions", "org.ohnlp.medkat.taes.dimensionAnnotator.DimensionSetAnnotation");
    return ll_cas.ll_getRefValue(addr, casFeatCode_dimensions);
  }
  /** @generated */    
  public void setDimensions(int addr, int v) {
        if (featOkTst && casFeat_dimensions == null)
      jcas.throwFeatMissing("dimensions", "org.ohnlp.medkat.taes.dimensionAnnotator.DimensionSetAnnotation");
    ll_cas.ll_setRefValue(addr, casFeatCode_dimensions, v);}
    
   /** @generated */
  public int getDimensions(int addr, int i) {
        if (featOkTst && casFeat_dimensions == null)
      jcas.throwFeatMissing("dimensions", "org.ohnlp.medkat.taes.dimensionAnnotator.DimensionSetAnnotation");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_dimensions), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_dimensions), i);
  return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_dimensions), i);
  }
   
  /** @generated */ 
  public void setDimensions(int addr, int i, int v) {
        if (featOkTst && casFeat_dimensions == null)
      jcas.throwFeatMissing("dimensions", "org.ohnlp.medkat.taes.dimensionAnnotator.DimensionSetAnnotation");
    if (lowLevelTypeChecks)
      ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_dimensions), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_dimensions), i);
    ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_dimensions), i, v);
  }
 



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public DimensionSetAnnotation_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_dimensions = jcas.getRequiredFeatureDE(casType, "dimensions", "uima.cas.FSArray", featOkTst);
    casFeatCode_dimensions  = (null == casFeat_dimensions) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_dimensions).getCode();

  }
}



    