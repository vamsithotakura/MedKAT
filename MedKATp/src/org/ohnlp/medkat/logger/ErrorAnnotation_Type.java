
/* First created by JCasGen Wed Jun 11 12:20:15 EDT 2008 */
package org.ohnlp.medkat.logger;

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
 * Updated by JCasGen Wed Mar 11 15:41:19 EDT 2009
 * @generated */
public class ErrorAnnotation_Type extends Annotation_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (ErrorAnnotation_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = ErrorAnnotation_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new ErrorAnnotation(addr, ErrorAnnotation_Type.this);
  			   ErrorAnnotation_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new ErrorAnnotation(addr, ErrorAnnotation_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = ErrorAnnotation.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.ohnlp.medkat.logger.ErrorAnnotation");
 
  /** @generated */
  final Feature casFeat_errorText;
  /** @generated */
  final int     casFeatCode_errorText;
  /** @generated */ 
  public String getErrorText(int addr) {
        if (featOkTst && casFeat_errorText == null)
      jcas.throwFeatMissing("errorText", "org.ohnlp.medkat.logger.ErrorAnnotation");
    return ll_cas.ll_getStringValue(addr, casFeatCode_errorText);
  }
  /** @generated */    
  public void setErrorText(int addr, String v) {
        if (featOkTst && casFeat_errorText == null)
      jcas.throwFeatMissing("errorText", "org.ohnlp.medkat.logger.ErrorAnnotation");
    ll_cas.ll_setStringValue(addr, casFeatCode_errorText, v);}
    
  
 
  /** @generated */
  final Feature casFeat_offendingDocument;
  /** @generated */
  final int     casFeatCode_offendingDocument;
  /** @generated */ 
  public int getOffendingDocument(int addr) {
        if (featOkTst && casFeat_offendingDocument == null)
      jcas.throwFeatMissing("offendingDocument", "org.ohnlp.medkat.logger.ErrorAnnotation");
    return ll_cas.ll_getRefValue(addr, casFeatCode_offendingDocument);
  }
  /** @generated */    
  public void setOffendingDocument(int addr, int v) {
        if (featOkTst && casFeat_offendingDocument == null)
      jcas.throwFeatMissing("offendingDocument", "org.ohnlp.medkat.logger.ErrorAnnotation");
    ll_cas.ll_setRefValue(addr, casFeatCode_offendingDocument, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public ErrorAnnotation_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_errorText = jcas.getRequiredFeatureDE(casType, "errorText", "uima.cas.String", featOkTst);
    casFeatCode_errorText  = (null == casFeat_errorText) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_errorText).getCode();

 
    casFeat_offendingDocument = jcas.getRequiredFeatureDE(casType, "offendingDocument", "uima.tcas.DocumentAnnotation", featOkTst);
    casFeatCode_offendingDocument  = (null == casFeat_offendingDocument) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_offendingDocument).getCode();

  }
}



    