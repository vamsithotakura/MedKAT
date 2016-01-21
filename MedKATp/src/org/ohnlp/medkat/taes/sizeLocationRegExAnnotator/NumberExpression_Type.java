
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
public class NumberExpression_Type extends Annotation_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (NumberExpression_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = NumberExpression_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new NumberExpression(addr, NumberExpression_Type.this);
  			   NumberExpression_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new NumberExpression(addr, NumberExpression_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = NumberExpression.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.ohnlp.medkat.taes.sizeLocationRegExAnnotator.NumberExpression");
 
  /** @generated */
  final Feature casFeat_numeric;
  /** @generated */
  final int     casFeatCode_numeric;
  /** @generated */ 
  public String getNumeric(int addr) {
        if (featOkTst && casFeat_numeric == null)
      jcas.throwFeatMissing("numeric", "org.ohnlp.medkat.taes.sizeLocationRegExAnnotator.NumberExpression");
    return ll_cas.ll_getStringValue(addr, casFeatCode_numeric);
  }
  /** @generated */    
  public void setNumeric(int addr, String v) {
        if (featOkTst && casFeat_numeric == null)
      jcas.throwFeatMissing("numeric", "org.ohnlp.medkat.taes.sizeLocationRegExAnnotator.NumberExpression");
    ll_cas.ll_setStringValue(addr, casFeatCode_numeric, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public NumberExpression_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_numeric = jcas.getRequiredFeatureDE(casType, "numeric", "uima.cas.String", featOkTst);
    casFeatCode_numeric  = (null == casFeat_numeric) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_numeric).getCode();

  }
}



    