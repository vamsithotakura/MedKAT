
/* First created by JCasGen Wed Jun 11 12:10:52 EDT 2008 */
package org.ohnlp.medkat.taes.diagnosisTypeDetector;

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
 * Updated by JCasGen Mon Mar 23 14:01:46 EDT 2009
 * @generated */
public class DiagnosisBase_Type extends Annotation_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (DiagnosisBase_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = DiagnosisBase_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new DiagnosisBase(addr, DiagnosisBase_Type.this);
  			   DiagnosisBase_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new DiagnosisBase(addr, DiagnosisBase_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = DiagnosisBase.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.ohnlp.medkat.taes.diagnosisTypeDetector.DiagnosisBase");
 
  /** @generated */
  final Feature casFeat_term;
  /** @generated */
  final int     casFeatCode_term;
  /** @generated */ 
  public int getTerm(int addr) {
        if (featOkTst && casFeat_term == null)
      jcas.throwFeatMissing("term", "org.ohnlp.medkat.taes.diagnosisTypeDetector.DiagnosisBase");
    return ll_cas.ll_getRefValue(addr, casFeatCode_term);
  }
  /** @generated */    
  public void setTerm(int addr, int v) {
        if (featOkTst && casFeat_term == null)
      jcas.throwFeatMissing("term", "org.ohnlp.medkat.taes.diagnosisTypeDetector.DiagnosisBase");
    ll_cas.ll_setRefValue(addr, casFeatCode_term, v);}
    
  
 
  /** @generated */
  final Feature casFeat_sites;
  /** @generated */
  final int     casFeatCode_sites;
  /** @generated */ 
  public int getSites(int addr) {
        if (featOkTst && casFeat_sites == null)
      jcas.throwFeatMissing("sites", "org.ohnlp.medkat.taes.diagnosisTypeDetector.DiagnosisBase");
    return ll_cas.ll_getRefValue(addr, casFeatCode_sites);
  }
  /** @generated */    
  public void setSites(int addr, int v) {
        if (featOkTst && casFeat_sites == null)
      jcas.throwFeatMissing("sites", "org.ohnlp.medkat.taes.diagnosisTypeDetector.DiagnosisBase");
    ll_cas.ll_setRefValue(addr, casFeatCode_sites, v);}
    
   /** @generated */
  public int getSites(int addr, int i) {
        if (featOkTst && casFeat_sites == null)
      jcas.throwFeatMissing("sites", "org.ohnlp.medkat.taes.diagnosisTypeDetector.DiagnosisBase");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_sites), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_sites), i);
  return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_sites), i);
  }
   
  /** @generated */ 
  public void setSites(int addr, int i, int v) {
        if (featOkTst && casFeat_sites == null)
      jcas.throwFeatMissing("sites", "org.ohnlp.medkat.taes.diagnosisTypeDetector.DiagnosisBase");
    if (lowLevelTypeChecks)
      ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_sites), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_sites), i);
    ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_sites), i, v);
  }
 



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public DiagnosisBase_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_term = jcas.getRequiredFeatureDE(casType, "term", "uima.tcas.Annotation", featOkTst);
    casFeatCode_term  = (null == casFeat_term) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_term).getCode();

 
    casFeat_sites = jcas.getRequiredFeatureDE(casType, "sites", "uima.cas.FSArray", featOkTst);
    casFeatCode_sites  = (null == casFeat_sites) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_sites).getCode();

  }
}



    