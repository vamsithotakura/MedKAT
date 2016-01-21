
/* First created by JCasGen Wed Jun 11 12:10:52 EDT 2008 */
package org.ohnlp.medkat.taes.sizeLocationRegExAnnotator;

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
public class LymphLevelExpression_Type extends Annotation_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (LymphLevelExpression_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = LymphLevelExpression_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new LymphLevelExpression(addr, LymphLevelExpression_Type.this);
  			   LymphLevelExpression_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new LymphLevelExpression(addr, LymphLevelExpression_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = LymphLevelExpression.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.ohnlp.medkat.taes.sizeLocationRegExAnnotator.LymphLevelExpression");
 
  /** @generated */
  final Feature casFeat_status;
  /** @generated */
  final int     casFeatCode_status;
  /** @generated */ 
  public int getStatus(int addr) {
        if (featOkTst && casFeat_status == null)
      jcas.throwFeatMissing("status", "org.ohnlp.medkat.taes.sizeLocationRegExAnnotator.LymphLevelExpression");
    return ll_cas.ll_getIntValue(addr, casFeatCode_status);
  }
  /** @generated */    
  public void setStatus(int addr, int v) {
        if (featOkTst && casFeat_status == null)
      jcas.throwFeatMissing("status", "org.ohnlp.medkat.taes.sizeLocationRegExAnnotator.LymphLevelExpression");
    ll_cas.ll_setIntValue(addr, casFeatCode_status, v);}
    
  
 
  /** @generated */
  final Feature casFeat_nodeExpression;
  /** @generated */
  final int     casFeatCode_nodeExpression;
  /** @generated */ 
  public String getNodeExpression(int addr) {
        if (featOkTst && casFeat_nodeExpression == null)
      jcas.throwFeatMissing("nodeExpression", "org.ohnlp.medkat.taes.sizeLocationRegExAnnotator.LymphLevelExpression");
    return ll_cas.ll_getStringValue(addr, casFeatCode_nodeExpression);
  }
  /** @generated */    
  public void setNodeExpression(int addr, String v) {
        if (featOkTst && casFeat_nodeExpression == null)
      jcas.throwFeatMissing("nodeExpression", "org.ohnlp.medkat.taes.sizeLocationRegExAnnotator.LymphLevelExpression");
    ll_cas.ll_setStringValue(addr, casFeatCode_nodeExpression, v);}
    
  
 
  /** @generated */
  final Feature casFeat_numPositive;
  /** @generated */
  final int     casFeatCode_numPositive;
  /** @generated */ 
  public int getNumPositive(int addr) {
        if (featOkTst && casFeat_numPositive == null)
      jcas.throwFeatMissing("numPositive", "org.ohnlp.medkat.taes.sizeLocationRegExAnnotator.LymphLevelExpression");
    return ll_cas.ll_getIntValue(addr, casFeatCode_numPositive);
  }
  /** @generated */    
  public void setNumPositive(int addr, int v) {
        if (featOkTst && casFeat_numPositive == null)
      jcas.throwFeatMissing("numPositive", "org.ohnlp.medkat.taes.sizeLocationRegExAnnotator.LymphLevelExpression");
    ll_cas.ll_setIntValue(addr, casFeatCode_numPositive, v);}
    
  
 
  /** @generated */
  final Feature casFeat_numTotal;
  /** @generated */
  final int     casFeatCode_numTotal;
  /** @generated */ 
  public int getNumTotal(int addr) {
        if (featOkTst && casFeat_numTotal == null)
      jcas.throwFeatMissing("numTotal", "org.ohnlp.medkat.taes.sizeLocationRegExAnnotator.LymphLevelExpression");
    return ll_cas.ll_getIntValue(addr, casFeatCode_numTotal);
  }
  /** @generated */    
  public void setNumTotal(int addr, int v) {
        if (featOkTst && casFeat_numTotal == null)
      jcas.throwFeatMissing("numTotal", "org.ohnlp.medkat.taes.sizeLocationRegExAnnotator.LymphLevelExpression");
    ll_cas.ll_setIntValue(addr, casFeatCode_numTotal, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public LymphLevelExpression_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_status = jcas.getRequiredFeatureDE(casType, "status", "uima.cas.Integer", featOkTst);
    casFeatCode_status  = (null == casFeat_status) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_status).getCode();

 
    casFeat_nodeExpression = jcas.getRequiredFeatureDE(casType, "nodeExpression", "uima.cas.String", featOkTst);
    casFeatCode_nodeExpression  = (null == casFeat_nodeExpression) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_nodeExpression).getCode();

 
    casFeat_numPositive = jcas.getRequiredFeatureDE(casType, "numPositive", "uima.cas.Integer", featOkTst);
    casFeatCode_numPositive  = (null == casFeat_numPositive) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_numPositive).getCode();

 
    casFeat_numTotal = jcas.getRequiredFeatureDE(casType, "numTotal", "uima.cas.Integer", featOkTst);
    casFeatCode_numTotal  = (null == casFeat_numTotal) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_numTotal).getCode();

  }
}



    