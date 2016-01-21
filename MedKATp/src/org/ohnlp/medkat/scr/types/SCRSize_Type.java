
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

/** geometrical size object
 * Updated by JCasGen Mon Mar 23 14:01:47 EDT 2009
 * @generated */
public class SCRSize_Type extends Annotation_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (SCRSize_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = SCRSize_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new SCRSize(addr, SCRSize_Type.this);
  			   SCRSize_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new SCRSize(addr, SCRSize_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = SCRSize.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.ohnlp.medkat.scr.types.SCRSize");
 
  /** @generated */
  final Feature casFeat_Dimensions;
  /** @generated */
  final int     casFeatCode_Dimensions;
  /** @generated */ 
  public int getDimensions(int addr) {
        if (featOkTst && casFeat_Dimensions == null)
      jcas.throwFeatMissing("Dimensions", "org.ohnlp.medkat.scr.types.SCRSize");
    return ll_cas.ll_getRefValue(addr, casFeatCode_Dimensions);
  }
  /** @generated */    
  public void setDimensions(int addr, int v) {
        if (featOkTst && casFeat_Dimensions == null)
      jcas.throwFeatMissing("Dimensions", "org.ohnlp.medkat.scr.types.SCRSize");
    ll_cas.ll_setRefValue(addr, casFeatCode_Dimensions, v);}
    
   /** @generated */
  public int getDimensions(int addr, int i) {
        if (featOkTst && casFeat_Dimensions == null)
      jcas.throwFeatMissing("Dimensions", "org.ohnlp.medkat.scr.types.SCRSize");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_Dimensions), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_Dimensions), i);
  return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_Dimensions), i);
  }
   
  /** @generated */ 
  public void setDimensions(int addr, int i, int v) {
        if (featOkTst && casFeat_Dimensions == null)
      jcas.throwFeatMissing("Dimensions", "org.ohnlp.medkat.scr.types.SCRSize");
    if (lowLevelTypeChecks)
      ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_Dimensions), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_Dimensions), i);
    ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_Dimensions), i, v);
  }
 



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public SCRSize_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_Dimensions = jcas.getRequiredFeatureDE(casType, "Dimensions", "uima.cas.FSArray", featOkTst);
    casFeatCode_Dimensions  = (null == casFeat_Dimensions) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Dimensions).getCode();

  }
}



    