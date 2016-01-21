
/* First created by JCasGen Wed Jun 11 14:52:53 EDT 2008 */
package org.ohnlp.medkat.taes.syntacticUnitFinder;

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
public class SyntacticUnit_Type extends Annotation_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (SyntacticUnit_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = SyntacticUnit_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new SyntacticUnit(addr, SyntacticUnit_Type.this);
  			   SyntacticUnit_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new SyntacticUnit(addr, SyntacticUnit_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = SyntacticUnit.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.ohnlp.medkat.taes.syntacticUnitFinder.SyntacticUnit");
 
  /** @generated */
  final Feature casFeat_scope;
  /** @generated */
  final int     casFeatCode_scope;
  /** @generated */ 
  public int getScope(int addr) {
        if (featOkTst && casFeat_scope == null)
      jcas.throwFeatMissing("scope", "org.ohnlp.medkat.taes.syntacticUnitFinder.SyntacticUnit");
    return ll_cas.ll_getIntValue(addr, casFeatCode_scope);
  }
  /** @generated */    
  public void setScope(int addr, int v) {
        if (featOkTst && casFeat_scope == null)
      jcas.throwFeatMissing("scope", "org.ohnlp.medkat.taes.syntacticUnitFinder.SyntacticUnit");
    ll_cas.ll_setIntValue(addr, casFeatCode_scope, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public SyntacticUnit_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_scope = jcas.getRequiredFeatureDE(casType, "scope", "uima.cas.Integer", featOkTst);
    casFeatCode_scope  = (null == casFeat_scope) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_scope).getCode();

  }
}



    