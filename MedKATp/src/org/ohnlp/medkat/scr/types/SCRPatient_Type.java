
/* First created by JCasGen Wed Aug 27 00:14:06 EDT 2008 */
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

/** patient object
 * Updated by JCasGen Wed Mar 11 15:34:13 EDT 2009
 * @generated */
public class SCRPatient_Type extends Annotation_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (SCRPatient_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = SCRPatient_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new SCRPatient(addr, SCRPatient_Type.this);
  			   SCRPatient_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new SCRPatient(addr, SCRPatient_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = SCRPatient.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.ohnlp.medkat.scr.types.SCRPatient");
 
  /** @generated */
  final Feature casFeat_Observations;
  /** @generated */
  final int     casFeatCode_Observations;
  /** @generated */ 
  public int getObservations(int addr) {
        if (featOkTst && casFeat_Observations == null)
      jcas.throwFeatMissing("Observations", "org.ohnlp.medkat.scr.types.SCRPatient");
    return ll_cas.ll_getRefValue(addr, casFeatCode_Observations);
  }
  /** @generated */    
  public void setObservations(int addr, int v) {
        if (featOkTst && casFeat_Observations == null)
      jcas.throwFeatMissing("Observations", "org.ohnlp.medkat.scr.types.SCRPatient");
    ll_cas.ll_setRefValue(addr, casFeatCode_Observations, v);}
    
   /** @generated */
  public int getObservations(int addr, int i) {
        if (featOkTst && casFeat_Observations == null)
      jcas.throwFeatMissing("Observations", "org.ohnlp.medkat.scr.types.SCRPatient");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_Observations), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_Observations), i);
  return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_Observations), i);
  }
   
  /** @generated */ 
  public void setObservations(int addr, int i, int v) {
        if (featOkTst && casFeat_Observations == null)
      jcas.throwFeatMissing("Observations", "org.ohnlp.medkat.scr.types.SCRPatient");
    if (lowLevelTypeChecks)
      ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_Observations), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_Observations), i);
    ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_Observations), i, v);
  }
 



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public SCRPatient_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_Observations = jcas.getRequiredFeatureDE(casType, "Observations", "uima.cas.FSArray", featOkTst);
    casFeatCode_Observations  = (null == casFeat_Observations) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Observations).getCode();

  }
}



    