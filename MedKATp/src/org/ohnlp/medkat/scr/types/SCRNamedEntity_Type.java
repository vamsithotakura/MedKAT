
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

/** SCR Named Entity type
 * Updated by JCasGen Mon Mar 23 14:01:47 EDT 2009
 * @generated */
public class SCRNamedEntity_Type extends SCRNamedEntityBase_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (SCRNamedEntity_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = SCRNamedEntity_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new SCRNamedEntity(addr, SCRNamedEntity_Type.this);
  			   SCRNamedEntity_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new SCRNamedEntity(addr, SCRNamedEntity_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = SCRNamedEntity.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.ohnlp.medkat.scr.types.SCRNamedEntity");
 
  /** @generated */
  final Feature casFeat_SemanticClass;
  /** @generated */
  final int     casFeatCode_SemanticClass;
  /** @generated */ 
  public String getSemanticClass(int addr) {
        if (featOkTst && casFeat_SemanticClass == null)
      jcas.throwFeatMissing("SemanticClass", "org.ohnlp.medkat.scr.types.SCRNamedEntity");
    return ll_cas.ll_getStringValue(addr, casFeatCode_SemanticClass);
  }
  /** @generated */    
  public void setSemanticClass(int addr, String v) {
        if (featOkTst && casFeat_SemanticClass == null)
      jcas.throwFeatMissing("SemanticClass", "org.ohnlp.medkat.scr.types.SCRNamedEntity");
    ll_cas.ll_setStringValue(addr, casFeatCode_SemanticClass, v);}
    
  
 
  /** @generated */
  final Feature casFeat_Modifier;
  /** @generated */
  final int     casFeatCode_Modifier;
  /** @generated */ 
  public int getModifier(int addr) {
        if (featOkTst && casFeat_Modifier == null)
      jcas.throwFeatMissing("Modifier", "org.ohnlp.medkat.scr.types.SCRNamedEntity");
    return ll_cas.ll_getIntValue(addr, casFeatCode_Modifier);
  }
  /** @generated */    
  public void setModifier(int addr, int v) {
        if (featOkTst && casFeat_Modifier == null)
      jcas.throwFeatMissing("Modifier", "org.ohnlp.medkat.scr.types.SCRNamedEntity");
    ll_cas.ll_setIntValue(addr, casFeatCode_Modifier, v);}
    
  
 
  /** @generated */
  final Feature casFeat_Metastatic;
  /** @generated */
  final int     casFeatCode_Metastatic;
  /** @generated */ 
  public int getMetastatic(int addr) {
        if (featOkTst && casFeat_Metastatic == null)
      jcas.throwFeatMissing("Metastatic", "org.ohnlp.medkat.scr.types.SCRNamedEntity");
    return ll_cas.ll_getIntValue(addr, casFeatCode_Metastatic);
  }
  /** @generated */    
  public void setMetastatic(int addr, int v) {
        if (featOkTst && casFeat_Metastatic == null)
      jcas.throwFeatMissing("Metastatic", "org.ohnlp.medkat.scr.types.SCRNamedEntity");
    ll_cas.ll_setIntValue(addr, casFeatCode_Metastatic, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public SCRNamedEntity_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_SemanticClass = jcas.getRequiredFeatureDE(casType, "SemanticClass", "uima.cas.String", featOkTst);
    casFeatCode_SemanticClass  = (null == casFeat_SemanticClass) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_SemanticClass).getCode();

 
    casFeat_Modifier = jcas.getRequiredFeatureDE(casType, "Modifier", "uima.cas.Integer", featOkTst);
    casFeatCode_Modifier  = (null == casFeat_Modifier) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Modifier).getCode();

 
    casFeat_Metastatic = jcas.getRequiredFeatureDE(casType, "Metastatic", "uima.cas.Integer", featOkTst);
    casFeatCode_Metastatic  = (null == casFeat_Metastatic) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Metastatic).getCode();

  }
}



    