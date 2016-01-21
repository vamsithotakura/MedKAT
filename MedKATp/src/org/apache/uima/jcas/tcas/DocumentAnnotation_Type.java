
/* First created by JCasGen Tue Jan 20 18:38:34 EST 2009 */
package org.apache.uima.jcas.tcas;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;

/** Document metadata.
 * Updated by JCasGen Wed Mar 11 15:36:22 EDT 2009
 * @generated */
public class DocumentAnnotation_Type extends Annotation_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (DocumentAnnotation_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = DocumentAnnotation_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new DocumentAnnotation(addr, DocumentAnnotation_Type.this);
  			   DocumentAnnotation_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new DocumentAnnotation(addr, DocumentAnnotation_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = DocumentAnnotation.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("uima.tcas.DocumentAnnotation");
 
  /** @generated */
  final Feature casFeat_language;
  /** @generated */
  final int     casFeatCode_language;
  /** @generated */ 
  public String getLanguage(int addr) {
        if (featOkTst && casFeat_language == null)
      jcas.throwFeatMissing("language", "uima.tcas.DocumentAnnotation");
    return ll_cas.ll_getStringValue(addr, casFeatCode_language);
  }
  /** @generated */    
  public void setLanguage(int addr, String v) {
        if (featOkTst && casFeat_language == null)
      jcas.throwFeatMissing("language", "uima.tcas.DocumentAnnotation");
    ll_cas.ll_setStringValue(addr, casFeatCode_language, v);}
    
  
 
  /** @generated */
  final Feature casFeat_categories;
  /** @generated */
  final int     casFeatCode_categories;
  /** @generated */ 
  public int getCategories(int addr) {
        if (featOkTst && casFeat_categories == null)
      jcas.throwFeatMissing("categories", "uima.tcas.DocumentAnnotation");
    return ll_cas.ll_getRefValue(addr, casFeatCode_categories);
  }
  /** @generated */    
  public void setCategories(int addr, int v) {
        if (featOkTst && casFeat_categories == null)
      jcas.throwFeatMissing("categories", "uima.tcas.DocumentAnnotation");
    ll_cas.ll_setRefValue(addr, casFeatCode_categories, v);}
    
  
 
  /** @generated */
  final Feature casFeat_languageCandidates;
  /** @generated */
  final int     casFeatCode_languageCandidates;
  /** @generated */ 
  public int getLanguageCandidates(int addr) {
        if (featOkTst && casFeat_languageCandidates == null)
      jcas.throwFeatMissing("languageCandidates", "uima.tcas.DocumentAnnotation");
    return ll_cas.ll_getRefValue(addr, casFeatCode_languageCandidates);
  }
  /** @generated */    
  public void setLanguageCandidates(int addr, int v) {
        if (featOkTst && casFeat_languageCandidates == null)
      jcas.throwFeatMissing("languageCandidates", "uima.tcas.DocumentAnnotation");
    ll_cas.ll_setRefValue(addr, casFeatCode_languageCandidates, v);}
    
  
 
  /** @generated */
  final Feature casFeat_id;
  /** @generated */
  final int     casFeatCode_id;
  /** @generated */ 
  public String getId(int addr) {
        if (featOkTst && casFeat_id == null)
      jcas.throwFeatMissing("id", "uima.tcas.DocumentAnnotation");
    return ll_cas.ll_getStringValue(addr, casFeatCode_id);
  }
  /** @generated */    
  public void setId(int addr, String v) {
        if (featOkTst && casFeat_id == null)
      jcas.throwFeatMissing("id", "uima.tcas.DocumentAnnotation");
    ll_cas.ll_setStringValue(addr, casFeatCode_id, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public DocumentAnnotation_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_language = jcas.getRequiredFeatureDE(casType, "language", "uima.cas.String", featOkTst);
    casFeatCode_language  = (null == casFeat_language) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_language).getCode();

 
    casFeat_categories = jcas.getRequiredFeatureDE(casType, "categories", "uima.cas.FSList", featOkTst);
    casFeatCode_categories  = (null == casFeat_categories) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_categories).getCode();

 
    casFeat_languageCandidates = jcas.getRequiredFeatureDE(casType, "languageCandidates", "uima.cas.FSList", featOkTst);
    casFeatCode_languageCandidates  = (null == casFeat_languageCandidates) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_languageCandidates).getCode();

 
    casFeat_id = jcas.getRequiredFeatureDE(casType, "id", "uima.cas.String", featOkTst);
    casFeatCode_id  = (null == casFeat_id) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_id).getCode();

  }
}



    