
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

/** SCR HistologicalDiagnosis type
 * Updated by JCasGen Mon Mar 23 14:01:47 EDT 2009
 * @generated */
public class SCRHistologicalDiagnosis_Type extends SCRNamedEntityBase_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (SCRHistologicalDiagnosis_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = SCRHistologicalDiagnosis_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new SCRHistologicalDiagnosis(addr, SCRHistologicalDiagnosis_Type.this);
  			   SCRHistologicalDiagnosis_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new SCRHistologicalDiagnosis(addr, SCRHistologicalDiagnosis_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = SCRHistologicalDiagnosis.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.ohnlp.medkat.scr.types.SCRHistologicalDiagnosis");
 
  /** @generated */
  final Feature casFeat_Coreferences;
  /** @generated */
  final int     casFeatCode_Coreferences;
  /** @generated */ 
  public int getCoreferences(int addr) {
        if (featOkTst && casFeat_Coreferences == null)
      jcas.throwFeatMissing("Coreferences", "org.ohnlp.medkat.scr.types.SCRHistologicalDiagnosis");
    return ll_cas.ll_getRefValue(addr, casFeatCode_Coreferences);
  }
  /** @generated */    
  public void setCoreferences(int addr, int v) {
        if (featOkTst && casFeat_Coreferences == null)
      jcas.throwFeatMissing("Coreferences", "org.ohnlp.medkat.scr.types.SCRHistologicalDiagnosis");
    ll_cas.ll_setRefValue(addr, casFeatCode_Coreferences, v);}
    
   /** @generated */
  public int getCoreferences(int addr, int i) {
        if (featOkTst && casFeat_Coreferences == null)
      jcas.throwFeatMissing("Coreferences", "org.ohnlp.medkat.scr.types.SCRHistologicalDiagnosis");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_Coreferences), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_Coreferences), i);
  return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_Coreferences), i);
  }
   
  /** @generated */ 
  public void setCoreferences(int addr, int i, int v) {
        if (featOkTst && casFeat_Coreferences == null)
      jcas.throwFeatMissing("Coreferences", "org.ohnlp.medkat.scr.types.SCRHistologicalDiagnosis");
    if (lowLevelTypeChecks)
      ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_Coreferences), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_Coreferences), i);
    ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_Coreferences), i, v);
  }
 



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public SCRHistologicalDiagnosis_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_Coreferences = jcas.getRequiredFeatureDE(casType, "Coreferences", "uima.cas.FSArray", featOkTst);
    casFeatCode_Coreferences  = (null == casFeat_Coreferences) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Coreferences).getCode();

  }
}



    