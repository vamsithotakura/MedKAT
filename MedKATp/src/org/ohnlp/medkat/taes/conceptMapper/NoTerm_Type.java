
/* First created by JCasGen Wed Jun 11 12:10:51 EDT 2008 */
package org.ohnlp.medkat.taes.conceptMapper;

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

/** Annotation for dictionary lookup matches
 * Updated by JCasGen Mon Mar 23 14:01:46 EDT 2009
 * @generated */
public class NoTerm_Type extends Annotation_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (NoTerm_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = NoTerm_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new NoTerm(addr, NoTerm_Type.this);
  			   NoTerm_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new NoTerm(addr, NoTerm_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = NoTerm.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.ohnlp.medkat.taes.conceptMapper.NoTerm");
 
  /** @generated */
  final Feature casFeat_DictCanon;
  /** @generated */
  final int     casFeatCode_DictCanon;
  /** @generated */ 
  public String getDictCanon(int addr) {
        if (featOkTst && casFeat_DictCanon == null)
      jcas.throwFeatMissing("DictCanon", "org.ohnlp.medkat.taes.conceptMapper.NoTerm");
    return ll_cas.ll_getStringValue(addr, casFeatCode_DictCanon);
  }
  /** @generated */    
  public void setDictCanon(int addr, String v) {
        if (featOkTst && casFeat_DictCanon == null)
      jcas.throwFeatMissing("DictCanon", "org.ohnlp.medkat.taes.conceptMapper.NoTerm");
    ll_cas.ll_setStringValue(addr, casFeatCode_DictCanon, v);}
    
  
 
  /** @generated */
  final Feature casFeat_SemClass;
  /** @generated */
  final int     casFeatCode_SemClass;
  /** @generated */ 
  public String getSemClass(int addr) {
        if (featOkTst && casFeat_SemClass == null)
      jcas.throwFeatMissing("SemClass", "org.ohnlp.medkat.taes.conceptMapper.NoTerm");
    return ll_cas.ll_getStringValue(addr, casFeatCode_SemClass);
  }
  /** @generated */    
  public void setSemClass(int addr, String v) {
        if (featOkTst && casFeat_SemClass == null)
      jcas.throwFeatMissing("SemClass", "org.ohnlp.medkat.taes.conceptMapper.NoTerm");
    ll_cas.ll_setStringValue(addr, casFeatCode_SemClass, v);}
    
  
 
  /** @generated */
  final Feature casFeat_enclosingSpan;
  /** @generated */
  final int     casFeatCode_enclosingSpan;
  /** @generated */ 
  public int getEnclosingSpan(int addr) {
        if (featOkTst && casFeat_enclosingSpan == null)
      jcas.throwFeatMissing("enclosingSpan", "org.ohnlp.medkat.taes.conceptMapper.NoTerm");
    return ll_cas.ll_getRefValue(addr, casFeatCode_enclosingSpan);
  }
  /** @generated */    
  public void setEnclosingSpan(int addr, int v) {
        if (featOkTst && casFeat_enclosingSpan == null)
      jcas.throwFeatMissing("enclosingSpan", "org.ohnlp.medkat.taes.conceptMapper.NoTerm");
    ll_cas.ll_setRefValue(addr, casFeatCode_enclosingSpan, v);}
    
  
 
  /** @generated */
  final Feature casFeat_POS;
  /** @generated */
  final int     casFeatCode_POS;
  /** @generated */ 
  public String getPOS(int addr) {
        if (featOkTst && casFeat_POS == null)
      jcas.throwFeatMissing("POS", "org.ohnlp.medkat.taes.conceptMapper.NoTerm");
    return ll_cas.ll_getStringValue(addr, casFeatCode_POS);
  }
  /** @generated */    
  public void setPOS(int addr, String v) {
        if (featOkTst && casFeat_POS == null)
      jcas.throwFeatMissing("POS", "org.ohnlp.medkat.taes.conceptMapper.NoTerm");
    ll_cas.ll_setStringValue(addr, casFeatCode_POS, v);}
    
  
 
  /** @generated */
  final Feature casFeat_matchedText;
  /** @generated */
  final int     casFeatCode_matchedText;
  /** @generated */ 
  public String getMatchedText(int addr) {
        if (featOkTst && casFeat_matchedText == null)
      jcas.throwFeatMissing("matchedText", "org.ohnlp.medkat.taes.conceptMapper.NoTerm");
    return ll_cas.ll_getStringValue(addr, casFeatCode_matchedText);
  }
  /** @generated */    
  public void setMatchedText(int addr, String v) {
        if (featOkTst && casFeat_matchedText == null)
      jcas.throwFeatMissing("matchedText", "org.ohnlp.medkat.taes.conceptMapper.NoTerm");
    ll_cas.ll_setStringValue(addr, casFeatCode_matchedText, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public NoTerm_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_DictCanon = jcas.getRequiredFeatureDE(casType, "DictCanon", "uima.cas.String", featOkTst);
    casFeatCode_DictCanon  = (null == casFeat_DictCanon) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_DictCanon).getCode();

 
    casFeat_SemClass = jcas.getRequiredFeatureDE(casType, "SemClass", "uima.cas.String", featOkTst);
    casFeatCode_SemClass  = (null == casFeat_SemClass) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_SemClass).getCode();

 
    casFeat_enclosingSpan = jcas.getRequiredFeatureDE(casType, "enclosingSpan", "uima.tcas.Annotation", featOkTst);
    casFeatCode_enclosingSpan  = (null == casFeat_enclosingSpan) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_enclosingSpan).getCode();

 
    casFeat_POS = jcas.getRequiredFeatureDE(casType, "POS", "uima.cas.String", featOkTst);
    casFeatCode_POS  = (null == casFeat_POS) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_POS).getCode();

 
    casFeat_matchedText = jcas.getRequiredFeatureDE(casType, "matchedText", "uima.cas.String", featOkTst);
    casFeatCode_matchedText  = (null == casFeat_matchedText) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_matchedText).getCode();

  }
}



    