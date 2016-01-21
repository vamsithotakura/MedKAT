
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

/** primary tumor object
 * Updated by JCasGen Mon Mar 23 14:01:46 EDT 2009
 * @generated */
public class SCRMultipleReadingModel_Type extends Annotation_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (SCRMultipleReadingModel_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = SCRMultipleReadingModel_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new SCRMultipleReadingModel(addr, SCRMultipleReadingModel_Type.this);
  			   SCRMultipleReadingModel_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new SCRMultipleReadingModel(addr, SCRMultipleReadingModel_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = SCRMultipleReadingModel.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.ohnlp.medkat.scr.types.SCRMultipleReadingModel");
 
  /** @generated */
  final Feature casFeat_Readings;
  /** @generated */
  final int     casFeatCode_Readings;
  /** @generated */ 
  public int getReadings(int addr) {
        if (featOkTst && casFeat_Readings == null)
      jcas.throwFeatMissing("Readings", "org.ohnlp.medkat.scr.types.SCRMultipleReadingModel");
    return ll_cas.ll_getRefValue(addr, casFeatCode_Readings);
  }
  /** @generated */    
  public void setReadings(int addr, int v) {
        if (featOkTst && casFeat_Readings == null)
      jcas.throwFeatMissing("Readings", "org.ohnlp.medkat.scr.types.SCRMultipleReadingModel");
    ll_cas.ll_setRefValue(addr, casFeatCode_Readings, v);}
    
   /** @generated */
  public int getReadings(int addr, int i) {
        if (featOkTst && casFeat_Readings == null)
      jcas.throwFeatMissing("Readings", "org.ohnlp.medkat.scr.types.SCRMultipleReadingModel");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_Readings), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_Readings), i);
  return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_Readings), i);
  }
   
  /** @generated */ 
  public void setReadings(int addr, int i, int v) {
        if (featOkTst && casFeat_Readings == null)
      jcas.throwFeatMissing("Readings", "org.ohnlp.medkat.scr.types.SCRMultipleReadingModel");
    if (lowLevelTypeChecks)
      ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_Readings), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_Readings), i);
    ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_Readings), i, v);
  }
 



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public SCRMultipleReadingModel_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_Readings = jcas.getRequiredFeatureDE(casType, "Readings", "uima.cas.FSArray", featOkTst);
    casFeatCode_Readings  = (null == casFeat_Readings) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Readings).getCode();

  }
}



    