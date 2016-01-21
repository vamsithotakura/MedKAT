
/* First created by JCasGen Tue Oct 28 20:47:43 EDT 2008 */
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
 * Updated by JCasGen Mon Mar 23 13:44:59 EDT 2009
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
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public TokenAnnotation_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_pennTag = jcas.getRequiredFeatureDE(casType, "pennTag", "uima.cas.String", featOkTst);
    casFeatCode_pennTag  = (null == casFeat_pennTag) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_pennTag).getCode();

  }
}



    