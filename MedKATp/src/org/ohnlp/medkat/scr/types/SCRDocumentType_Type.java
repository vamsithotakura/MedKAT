
/* First created by JCasGen Wed Aug 27 00:14:05 EDT 2008 */
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

/** document type
 * Updated by JCasGen Wed Mar 11 15:34:12 EDT 2009
 * @generated */
public class SCRDocumentType_Type extends Annotation_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (SCRDocumentType_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = SCRDocumentType_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new SCRDocumentType(addr, SCRDocumentType_Type.this);
  			   SCRDocumentType_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new SCRDocumentType(addr, SCRDocumentType_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = SCRDocumentType.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.ohnlp.medkat.scr.types.SCRDocumentType");
 
  /** @generated */
  final Feature casFeat_Id;
  /** @generated */
  final int     casFeatCode_Id;
  /** @generated */ 
  public String getId(int addr) {
        if (featOkTst && casFeat_Id == null)
      jcas.throwFeatMissing("Id", "org.ohnlp.medkat.scr.types.SCRDocumentType");
    return ll_cas.ll_getStringValue(addr, casFeatCode_Id);
  }
  /** @generated */    
  public void setId(int addr, String v) {
        if (featOkTst && casFeat_Id == null)
      jcas.throwFeatMissing("Id", "org.ohnlp.medkat.scr.types.SCRDocumentType");
    ll_cas.ll_setStringValue(addr, casFeatCode_Id, v);}
    
  
 
  /** @generated */
  final Feature casFeat_SignatureMD;
  /** @generated */
  final int     casFeatCode_SignatureMD;
  /** @generated */ 
  public String getSignatureMD(int addr) {
        if (featOkTst && casFeat_SignatureMD == null)
      jcas.throwFeatMissing("SignatureMD", "org.ohnlp.medkat.scr.types.SCRDocumentType");
    return ll_cas.ll_getStringValue(addr, casFeatCode_SignatureMD);
  }
  /** @generated */    
  public void setSignatureMD(int addr, String v) {
        if (featOkTst && casFeat_SignatureMD == null)
      jcas.throwFeatMissing("SignatureMD", "org.ohnlp.medkat.scr.types.SCRDocumentType");
    ll_cas.ll_setStringValue(addr, casFeatCode_SignatureMD, v);}
    
  
 
  /** @generated */
  final Feature casFeat_Date;
  /** @generated */
  final int     casFeatCode_Date;
  /** @generated */ 
  public int getDate(int addr) {
        if (featOkTst && casFeat_Date == null)
      jcas.throwFeatMissing("Date", "org.ohnlp.medkat.scr.types.SCRDocumentType");
    return ll_cas.ll_getRefValue(addr, casFeatCode_Date);
  }
  /** @generated */    
  public void setDate(int addr, int v) {
        if (featOkTst && casFeat_Date == null)
      jcas.throwFeatMissing("Date", "org.ohnlp.medkat.scr.types.SCRDocumentType");
    ll_cas.ll_setRefValue(addr, casFeatCode_Date, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public SCRDocumentType_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_Id = jcas.getRequiredFeatureDE(casType, "Id", "uima.cas.String", featOkTst);
    casFeatCode_Id  = (null == casFeat_Id) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Id).getCode();

 
    casFeat_SignatureMD = jcas.getRequiredFeatureDE(casType, "SignatureMD", "uima.cas.String", featOkTst);
    casFeatCode_SignatureMD  = (null == casFeat_SignatureMD) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_SignatureMD).getCode();

 
    casFeat_Date = jcas.getRequiredFeatureDE(casType, "Date", "uima.tcas.Annotation", featOkTst);
    casFeatCode_Date  = (null == casFeat_Date) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Date).getCode();

  }
}



    