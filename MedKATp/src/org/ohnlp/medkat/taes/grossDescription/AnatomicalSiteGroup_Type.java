
/* First created by JCasGen Wed Jun 11 12:10:52 EDT 2008 */
package org.ohnlp.medkat.taes.grossDescription;

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
public class AnatomicalSiteGroup_Type extends Annotation_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (AnatomicalSiteGroup_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = AnatomicalSiteGroup_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new AnatomicalSiteGroup(addr, AnatomicalSiteGroup_Type.this);
  			   AnatomicalSiteGroup_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new AnatomicalSiteGroup(addr, AnatomicalSiteGroup_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = AnatomicalSiteGroup.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.ohnlp.medkat.taes.grossDescription.AnatomicalSiteGroup");
 
  /** @generated */
  final Feature casFeat_Sites;
  /** @generated */
  final int     casFeatCode_Sites;
  /** @generated */ 
  public int getSites(int addr) {
        if (featOkTst && casFeat_Sites == null)
      jcas.throwFeatMissing("Sites", "org.ohnlp.medkat.taes.grossDescription.AnatomicalSiteGroup");
    return ll_cas.ll_getRefValue(addr, casFeatCode_Sites);
  }
  /** @generated */    
  public void setSites(int addr, int v) {
        if (featOkTst && casFeat_Sites == null)
      jcas.throwFeatMissing("Sites", "org.ohnlp.medkat.taes.grossDescription.AnatomicalSiteGroup");
    ll_cas.ll_setRefValue(addr, casFeatCode_Sites, v);}
    
   /** @generated */
  public int getSites(int addr, int i) {
        if (featOkTst && casFeat_Sites == null)
      jcas.throwFeatMissing("Sites", "org.ohnlp.medkat.taes.grossDescription.AnatomicalSiteGroup");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_Sites), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_Sites), i);
  return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_Sites), i);
  }
   
  /** @generated */ 
  public void setSites(int addr, int i, int v) {
        if (featOkTst && casFeat_Sites == null)
      jcas.throwFeatMissing("Sites", "org.ohnlp.medkat.taes.grossDescription.AnatomicalSiteGroup");
    if (lowLevelTypeChecks)
      ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_Sites), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_Sites), i);
    ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_Sites), i, v);
  }
 



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public AnatomicalSiteGroup_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_Sites = jcas.getRequiredFeatureDE(casType, "Sites", "uima.cas.FSArray", featOkTst);
    casFeatCode_Sites  = (null == casFeat_Sites) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Sites).getCode();

  }
}



    