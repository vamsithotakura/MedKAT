
/* First created by JCasGen Thu Jun 12 11:15:20 EDT 2008 */
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
public class DictTerm_Type extends Annotation_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (DictTerm_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = DictTerm_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new DictTerm(addr, DictTerm_Type.this);
  			   DictTerm_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new DictTerm(addr, DictTerm_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = DictTerm.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.ohnlp.medkat.taes.conceptMapper.DictTerm");
 
  /** @generated */
  final Feature casFeat_DictCanon;
  /** @generated */
  final int     casFeatCode_DictCanon;
  /** @generated */ 
  public String getDictCanon(int addr) {
        if (featOkTst && casFeat_DictCanon == null)
      jcas.throwFeatMissing("DictCanon", "org.ohnlp.medkat.taes.conceptMapper.DictTerm");
    return ll_cas.ll_getStringValue(addr, casFeatCode_DictCanon);
  }
  /** @generated */    
  public void setDictCanon(int addr, String v) {
        if (featOkTst && casFeat_DictCanon == null)
      jcas.throwFeatMissing("DictCanon", "org.ohnlp.medkat.taes.conceptMapper.DictTerm");
    ll_cas.ll_setStringValue(addr, casFeatCode_DictCanon, v);}
    
  
 
  /** @generated */
  final Feature casFeat_AttributeValue;
  /** @generated */
  final int     casFeatCode_AttributeValue;
  /** @generated */ 
  public String getAttributeValue(int addr) {
        if (featOkTst && casFeat_AttributeValue == null)
      jcas.throwFeatMissing("AttributeValue", "org.ohnlp.medkat.taes.conceptMapper.DictTerm");
    return ll_cas.ll_getStringValue(addr, casFeatCode_AttributeValue);
  }
  /** @generated */    
  public void setAttributeValue(int addr, String v) {
        if (featOkTst && casFeat_AttributeValue == null)
      jcas.throwFeatMissing("AttributeValue", "org.ohnlp.medkat.taes.conceptMapper.DictTerm");
    ll_cas.ll_setStringValue(addr, casFeatCode_AttributeValue, v);}
    
  
 
  /** @generated */
  final Feature casFeat_SemClass;
  /** @generated */
  final int     casFeatCode_SemClass;
  /** @generated */ 
  public String getSemClass(int addr) {
        if (featOkTst && casFeat_SemClass == null)
      jcas.throwFeatMissing("SemClass", "org.ohnlp.medkat.taes.conceptMapper.DictTerm");
    return ll_cas.ll_getStringValue(addr, casFeatCode_SemClass);
  }
  /** @generated */    
  public void setSemClass(int addr, String v) {
        if (featOkTst && casFeat_SemClass == null)
      jcas.throwFeatMissing("SemClass", "org.ohnlp.medkat.taes.conceptMapper.DictTerm");
    ll_cas.ll_setStringValue(addr, casFeatCode_SemClass, v);}
    
  
 
  /** @generated */
  final Feature casFeat_marked;
  /** @generated */
  final int     casFeatCode_marked;
  /** @generated */ 
  public int getMarked(int addr) {
        if (featOkTst && casFeat_marked == null)
      jcas.throwFeatMissing("marked", "org.ohnlp.medkat.taes.conceptMapper.DictTerm");
    return ll_cas.ll_getIntValue(addr, casFeatCode_marked);
  }
  /** @generated */    
  public void setMarked(int addr, int v) {
        if (featOkTst && casFeat_marked == null)
      jcas.throwFeatMissing("marked", "org.ohnlp.medkat.taes.conceptMapper.DictTerm");
    ll_cas.ll_setIntValue(addr, casFeatCode_marked, v);}
    
  
 
  /** @generated */
  final Feature casFeat_enclosingSpan;
  /** @generated */
  final int     casFeatCode_enclosingSpan;
  /** @generated */ 
  public int getEnclosingSpan(int addr) {
        if (featOkTst && casFeat_enclosingSpan == null)
      jcas.throwFeatMissing("enclosingSpan", "org.ohnlp.medkat.taes.conceptMapper.DictTerm");
    return ll_cas.ll_getRefValue(addr, casFeatCode_enclosingSpan);
  }
  /** @generated */    
  public void setEnclosingSpan(int addr, int v) {
        if (featOkTst && casFeat_enclosingSpan == null)
      jcas.throwFeatMissing("enclosingSpan", "org.ohnlp.medkat.taes.conceptMapper.DictTerm");
    ll_cas.ll_setRefValue(addr, casFeatCode_enclosingSpan, v);}
    
  
 
  /** @generated */
  final Feature casFeat_POS;
  /** @generated */
  final int     casFeatCode_POS;
  /** @generated */ 
  public String getPOS(int addr) {
        if (featOkTst && casFeat_POS == null)
      jcas.throwFeatMissing("POS", "org.ohnlp.medkat.taes.conceptMapper.DictTerm");
    return ll_cas.ll_getStringValue(addr, casFeatCode_POS);
  }
  /** @generated */    
  public void setPOS(int addr, String v) {
        if (featOkTst && casFeat_POS == null)
      jcas.throwFeatMissing("POS", "org.ohnlp.medkat.taes.conceptMapper.DictTerm");
    ll_cas.ll_setStringValue(addr, casFeatCode_POS, v);}
    
  
 
  /** @generated */
  final Feature casFeat_AttributeType;
  /** @generated */
  final int     casFeatCode_AttributeType;
  /** @generated */ 
  public String getAttributeType(int addr) {
        if (featOkTst && casFeat_AttributeType == null)
      jcas.throwFeatMissing("AttributeType", "org.ohnlp.medkat.taes.conceptMapper.DictTerm");
    return ll_cas.ll_getStringValue(addr, casFeatCode_AttributeType);
  }
  /** @generated */    
  public void setAttributeType(int addr, String v) {
        if (featOkTst && casFeat_AttributeType == null)
      jcas.throwFeatMissing("AttributeType", "org.ohnlp.medkat.taes.conceptMapper.DictTerm");
    ll_cas.ll_setStringValue(addr, casFeatCode_AttributeType, v);}
    
  
 
  /** @generated */
  final Feature casFeat_matchedText;
  /** @generated */
  final int     casFeatCode_matchedText;
  /** @generated */ 
  public String getMatchedText(int addr) {
        if (featOkTst && casFeat_matchedText == null)
      jcas.throwFeatMissing("matchedText", "org.ohnlp.medkat.taes.conceptMapper.DictTerm");
    return ll_cas.ll_getStringValue(addr, casFeatCode_matchedText);
  }
  /** @generated */    
  public void setMatchedText(int addr, String v) {
        if (featOkTst && casFeat_matchedText == null)
      jcas.throwFeatMissing("matchedText", "org.ohnlp.medkat.taes.conceptMapper.DictTerm");
    ll_cas.ll_setStringValue(addr, casFeatCode_matchedText, v);}
    
  
 
  /** @generated */
  final Feature casFeat_key;
  /** @generated */
  final int     casFeatCode_key;
  /** @generated */ 
  public String getKey(int addr) {
        if (featOkTst && casFeat_key == null)
      jcas.throwFeatMissing("key", "org.ohnlp.medkat.taes.conceptMapper.DictTerm");
    return ll_cas.ll_getStringValue(addr, casFeatCode_key);
  }
  /** @generated */    
  public void setKey(int addr, String v) {
        if (featOkTst && casFeat_key == null)
      jcas.throwFeatMissing("key", "org.ohnlp.medkat.taes.conceptMapper.DictTerm");
    ll_cas.ll_setStringValue(addr, casFeatCode_key, v);}
    
  
 
  /** @generated */
  final Feature casFeat_parent;
  /** @generated */
  final int     casFeatCode_parent;
  /** @generated */ 
  public String getParent(int addr) {
        if (featOkTst && casFeat_parent == null)
      jcas.throwFeatMissing("parent", "org.ohnlp.medkat.taes.conceptMapper.DictTerm");
    return ll_cas.ll_getStringValue(addr, casFeatCode_parent);
  }
  /** @generated */    
  public void setParent(int addr, String v) {
        if (featOkTst && casFeat_parent == null)
      jcas.throwFeatMissing("parent", "org.ohnlp.medkat.taes.conceptMapper.DictTerm");
    ll_cas.ll_setStringValue(addr, casFeatCode_parent, v);}
    
  
 
  /** @generated */
  final Feature casFeat_matchedTokens;
  /** @generated */
  final int     casFeatCode_matchedTokens;
  /** @generated */ 
  public int getMatchedTokens(int addr) {
        if (featOkTst && casFeat_matchedTokens == null)
      jcas.throwFeatMissing("matchedTokens", "org.ohnlp.medkat.taes.conceptMapper.DictTerm");
    return ll_cas.ll_getRefValue(addr, casFeatCode_matchedTokens);
  }
  /** @generated */    
  public void setMatchedTokens(int addr, int v) {
        if (featOkTst && casFeat_matchedTokens == null)
      jcas.throwFeatMissing("matchedTokens", "org.ohnlp.medkat.taes.conceptMapper.DictTerm");
    ll_cas.ll_setRefValue(addr, casFeatCode_matchedTokens, v);}
    
   /** @generated */
  public int getMatchedTokens(int addr, int i) {
        if (featOkTst && casFeat_matchedTokens == null)
      jcas.throwFeatMissing("matchedTokens", "org.ohnlp.medkat.taes.conceptMapper.DictTerm");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_matchedTokens), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_matchedTokens), i);
  return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_matchedTokens), i);
  }
   
  /** @generated */ 
  public void setMatchedTokens(int addr, int i, int v) {
        if (featOkTst && casFeat_matchedTokens == null)
      jcas.throwFeatMissing("matchedTokens", "org.ohnlp.medkat.taes.conceptMapper.DictTerm");
    if (lowLevelTypeChecks)
      ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_matchedTokens), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_matchedTokens), i);
    ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_matchedTokens), i, v);
  }
 



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public DictTerm_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_DictCanon = jcas.getRequiredFeatureDE(casType, "DictCanon", "uima.cas.String", featOkTst);
    casFeatCode_DictCanon  = (null == casFeat_DictCanon) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_DictCanon).getCode();

 
    casFeat_AttributeValue = jcas.getRequiredFeatureDE(casType, "AttributeValue", "uima.cas.String", featOkTst);
    casFeatCode_AttributeValue  = (null == casFeat_AttributeValue) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_AttributeValue).getCode();

 
    casFeat_SemClass = jcas.getRequiredFeatureDE(casType, "SemClass", "uima.cas.String", featOkTst);
    casFeatCode_SemClass  = (null == casFeat_SemClass) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_SemClass).getCode();

 
    casFeat_marked = jcas.getRequiredFeatureDE(casType, "marked", "uima.cas.Integer", featOkTst);
    casFeatCode_marked  = (null == casFeat_marked) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_marked).getCode();

 
    casFeat_enclosingSpan = jcas.getRequiredFeatureDE(casType, "enclosingSpan", "uima.tcas.Annotation", featOkTst);
    casFeatCode_enclosingSpan  = (null == casFeat_enclosingSpan) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_enclosingSpan).getCode();

 
    casFeat_POS = jcas.getRequiredFeatureDE(casType, "POS", "uima.cas.String", featOkTst);
    casFeatCode_POS  = (null == casFeat_POS) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_POS).getCode();

 
    casFeat_AttributeType = jcas.getRequiredFeatureDE(casType, "AttributeType", "uima.cas.String", featOkTst);
    casFeatCode_AttributeType  = (null == casFeat_AttributeType) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_AttributeType).getCode();

 
    casFeat_matchedText = jcas.getRequiredFeatureDE(casType, "matchedText", "uima.cas.String", featOkTst);
    casFeatCode_matchedText  = (null == casFeat_matchedText) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_matchedText).getCode();

 
    casFeat_key = jcas.getRequiredFeatureDE(casType, "key", "uima.cas.String", featOkTst);
    casFeatCode_key  = (null == casFeat_key) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_key).getCode();

 
    casFeat_parent = jcas.getRequiredFeatureDE(casType, "parent", "uima.cas.String", featOkTst);
    casFeatCode_parent  = (null == casFeat_parent) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_parent).getCode();

 
    casFeat_matchedTokens = jcas.getRequiredFeatureDE(casType, "matchedTokens", "uima.cas.FSArray", featOkTst);
    casFeatCode_matchedTokens  = (null == casFeat_matchedTokens) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_matchedTokens).getCode();

  }
}



    