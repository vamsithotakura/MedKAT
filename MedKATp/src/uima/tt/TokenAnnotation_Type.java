
/* First created by JCasGen Thu Feb 05 11:48:07 EST 2009 */
package uima.tt;

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

/** Tokens.
 * Updated by JCasGen Mon Mar 23 14:01:46 EDT 2009
 * @generated */
public class TokenAnnotation_Type extends Annotation_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (TokenAnnotation_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = TokenAnnotation_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new TokenAnnotation(addr, TokenAnnotation_Type.this);
  			   TokenAnnotation_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new TokenAnnotation(addr, TokenAnnotation_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = TokenAnnotation.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("uima.tt.TokenAnnotation");



  /** @generated */
  final Feature casFeat_pennTag;
  /** @generated */
  final int     casFeatCode_pennTag;
  /** @generated */ 
  public String getPennTag(int addr) {
        if (featOkTst && casFeat_pennTag == null)
      jcas.throwFeatMissing("pennTag", "uima.tt.TokenAnnotation");
    return ll_cas.ll_getStringValue(addr, casFeatCode_pennTag);
  }
  /** @generated */    
  public void setPennTag(int addr, String v) {
        if (featOkTst && casFeat_pennTag == null)
      jcas.throwFeatMissing("pennTag", "uima.tt.TokenAnnotation");
    ll_cas.ll_setStringValue(addr, casFeatCode_pennTag, v);}
    
  
 
  /** @generated */
  final Feature casFeat_SemClass;
  /** @generated */
  final int     casFeatCode_SemClass;
  /** @generated */ 
  public String getSemClass(int addr) {
        if (featOkTst && casFeat_SemClass == null)
      jcas.throwFeatMissing("SemClass", "uima.tt.TokenAnnotation");
    return ll_cas.ll_getStringValue(addr, casFeatCode_SemClass);
  }
  /** @generated */    
  public void setSemClass(int addr, String v) {
        if (featOkTst && casFeat_SemClass == null)
      jcas.throwFeatMissing("SemClass", "uima.tt.TokenAnnotation");
    ll_cas.ll_setStringValue(addr, casFeatCode_SemClass, v);}
    
  
 
  /** @generated */
  final Feature casFeat_POS;
  /** @generated */
  final int     casFeatCode_POS;
  /** @generated */ 
  public String getPOS(int addr) {
        if (featOkTst && casFeat_POS == null)
      jcas.throwFeatMissing("POS", "uima.tt.TokenAnnotation");
    return ll_cas.ll_getStringValue(addr, casFeatCode_POS);
  }
  /** @generated */    
  public void setPOS(int addr, String v) {
        if (featOkTst && casFeat_POS == null)
      jcas.throwFeatMissing("POS", "uima.tt.TokenAnnotation");
    ll_cas.ll_setStringValue(addr, casFeatCode_POS, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public TokenAnnotation_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_pennTag = jcas.getRequiredFeatureDE(casType, "pennTag", "uima.cas.String", featOkTst);
    casFeatCode_pennTag  = (null == casFeat_pennTag) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_pennTag).getCode();

 
    casFeat_SemClass = jcas.getRequiredFeatureDE(casType, "SemClass", "uima.cas.String", featOkTst);
    casFeatCode_SemClass  = (null == casFeat_SemClass) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_SemClass).getCode();

 
    casFeat_POS = jcas.getRequiredFeatureDE(casType, "POS", "uima.cas.String", featOkTst);
    casFeatCode_POS  = (null == casFeat_POS) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_POS).getCode();

  }
}



    