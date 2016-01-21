
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

/** base class for reading object
 * Updated by JCasGen Mon Mar 23 14:01:46 EDT 2009
 * @generated */
public class SCRGenericReading_Type extends Annotation_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (SCRGenericReading_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = SCRGenericReading_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new SCRGenericReading(addr, SCRGenericReading_Type.this);
  			   SCRGenericReading_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new SCRGenericReading(addr, SCRGenericReading_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = SCRGenericReading.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.ohnlp.medkat.scr.types.SCRGenericReading");
 
  /** @generated */
  final Feature casFeat_Institution;
  /** @generated */
  final int     casFeatCode_Institution;
  /** @generated */ 
  public String getInstitution(int addr) {
        if (featOkTst && casFeat_Institution == null)
      jcas.throwFeatMissing("Institution", "org.ohnlp.medkat.scr.types.SCRGenericReading");
    return ll_cas.ll_getStringValue(addr, casFeatCode_Institution);
  }
  /** @generated */    
  public void setInstitution(int addr, String v) {
        if (featOkTst && casFeat_Institution == null)
      jcas.throwFeatMissing("Institution", "org.ohnlp.medkat.scr.types.SCRGenericReading");
    ll_cas.ll_setStringValue(addr, casFeatCode_Institution, v);}
    
  
 
  /** @generated */
  final Feature casFeat_Date;
  /** @generated */
  final int     casFeatCode_Date;
  /** @generated */ 
  public int getDate(int addr) {
        if (featOkTst && casFeat_Date == null)
      jcas.throwFeatMissing("Date", "org.ohnlp.medkat.scr.types.SCRGenericReading");
    return ll_cas.ll_getRefValue(addr, casFeatCode_Date);
  }
  /** @generated */    
  public void setDate(int addr, int v) {
        if (featOkTst && casFeat_Date == null)
      jcas.throwFeatMissing("Date", "org.ohnlp.medkat.scr.types.SCRGenericReading");
    ll_cas.ll_setRefValue(addr, casFeatCode_Date, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public SCRGenericReading_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_Institution = jcas.getRequiredFeatureDE(casType, "Institution", "uima.cas.String", featOkTst);
    casFeatCode_Institution  = (null == casFeat_Institution) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Institution).getCode();

 
    casFeat_Date = jcas.getRequiredFeatureDE(casType, "Date", "uima.tcas.Annotation", featOkTst);
    casFeatCode_Date  = (null == casFeat_Date) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Date).getCode();

  }
}



    