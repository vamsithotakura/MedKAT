
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

/** gross description object
 * Updated by JCasGen Mon Mar 23 14:01:47 EDT 2009
 * @generated */
public class SCRGrossDescription_Type extends SCRGenericReading_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (SCRGrossDescription_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = SCRGrossDescription_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new SCRGrossDescription(addr, SCRGrossDescription_Type.this);
  			   SCRGrossDescription_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new SCRGrossDescription(addr, SCRGrossDescription_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = SCRGrossDescription.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.ohnlp.medkat.scr.types.SCRGrossDescription");
 
  /** @generated */
  final Feature casFeat_Parts;
  /** @generated */
  final int     casFeatCode_Parts;
  /** @generated */ 
  public int getParts(int addr) {
        if (featOkTst && casFeat_Parts == null)
      jcas.throwFeatMissing("Parts", "org.ohnlp.medkat.scr.types.SCRGrossDescription");
    return ll_cas.ll_getRefValue(addr, casFeatCode_Parts);
  }
  /** @generated */    
  public void setParts(int addr, int v) {
        if (featOkTst && casFeat_Parts == null)
      jcas.throwFeatMissing("Parts", "org.ohnlp.medkat.scr.types.SCRGrossDescription");
    ll_cas.ll_setRefValue(addr, casFeatCode_Parts, v);}
    
   /** @generated */
  public int getParts(int addr, int i) {
        if (featOkTst && casFeat_Parts == null)
      jcas.throwFeatMissing("Parts", "org.ohnlp.medkat.scr.types.SCRGrossDescription");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_Parts), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_Parts), i);
  return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_Parts), i);
  }
   
  /** @generated */ 
  public void setParts(int addr, int i, int v) {
        if (featOkTst && casFeat_Parts == null)
      jcas.throwFeatMissing("Parts", "org.ohnlp.medkat.scr.types.SCRGrossDescription");
    if (lowLevelTypeChecks)
      ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_Parts), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_Parts), i);
    ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_Parts), i, v);
  }
 



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public SCRGrossDescription_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_Parts = jcas.getRequiredFeatureDE(casType, "Parts", "uima.cas.FSArray", featOkTst);
    casFeatCode_Parts  = (null == casFeat_Parts) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Parts).getCode();

  }
}



    