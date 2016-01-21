

/* First created by JCasGen Tue Oct 28 20:47:42 EDT 2008 */
package org.apache.uima.jcas.tcas;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.FSList;


/** Document metadata.
 * Updated by JCasGen Tue Feb 10 12:04:34 EST 2009
 * XML source: C:/eclipse/OpenNLP_Pipeline/descriptors/analysis_engine/primitive/OpenNLPTokenizer.xml
 * @generated */
public class DocumentAnnotation extends Annotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(DocumentAnnotation.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected DocumentAnnotation() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public DocumentAnnotation(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public DocumentAnnotation(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public DocumentAnnotation(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** <!-- begin-user-doc -->
    * Write your own initialization here
    * <!-- end-user-doc -->
  @generated modifiable */
  private void readObject() {}
     
 
    
  //*--------------*
  //* Feature: language

  /** getter for language - gets 
   * @generated */
  public String getLanguage() {
    if (DocumentAnnotation_Type.featOkTst && ((DocumentAnnotation_Type)jcasType).casFeat_language == null)
      jcasType.jcas.throwFeatMissing("language", "uima.tcas.DocumentAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((DocumentAnnotation_Type)jcasType).casFeatCode_language);}
    
  /** setter for language - sets  
   * @generated */
  public void setLanguage(String v) {
    if (DocumentAnnotation_Type.featOkTst && ((DocumentAnnotation_Type)jcasType).casFeat_language == null)
      jcasType.jcas.throwFeatMissing("language", "uima.tcas.DocumentAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((DocumentAnnotation_Type)jcasType).casFeatCode_language, v);}    
   
    
  //*--------------*
  //* Feature: categories

  /** getter for categories - gets Categories added by a text categorizer.  The
                   	entries in the list are of type uima.tt.CategoryConfidencePair.
   * @generated */
  public FSList getCategories() {
    if (DocumentAnnotation_Type.featOkTst && ((DocumentAnnotation_Type)jcasType).casFeat_categories == null)
      jcasType.jcas.throwFeatMissing("categories", "uima.tcas.DocumentAnnotation");
    return (FSList)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((DocumentAnnotation_Type)jcasType).casFeatCode_categories)));}
    
  /** setter for categories - sets Categories added by a text categorizer.  The
                   	entries in the list are of type uima.tt.CategoryConfidencePair. 
   * @generated */
  public void setCategories(FSList v) {
    if (DocumentAnnotation_Type.featOkTst && ((DocumentAnnotation_Type)jcasType).casFeat_categories == null)
      jcasType.jcas.throwFeatMissing("categories", "uima.tcas.DocumentAnnotation");
    jcasType.ll_cas.ll_setRefValue(addr, ((DocumentAnnotation_Type)jcasType).casFeatCode_categories, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: languageCandidates

  /** getter for languageCandidates - gets Document language candidate list found be automatic
                    language detection.  Values in the list are of type uima.tt.LanguageConfidencePair.
                    The list is supposed to be sorted, with the most likely language
                    at the beginning.
   * @generated */
  public FSList getLanguageCandidates() {
    if (DocumentAnnotation_Type.featOkTst && ((DocumentAnnotation_Type)jcasType).casFeat_languageCandidates == null)
      jcasType.jcas.throwFeatMissing("languageCandidates", "uima.tcas.DocumentAnnotation");
    return (FSList)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((DocumentAnnotation_Type)jcasType).casFeatCode_languageCandidates)));}
    
  /** setter for languageCandidates - sets Document language candidate list found be automatic
                    language detection.  Values in the list are of type uima.tt.LanguageConfidencePair.
                    The list is supposed to be sorted, with the most likely language
                    at the beginning. 
   * @generated */
  public void setLanguageCandidates(FSList v) {
    if (DocumentAnnotation_Type.featOkTst && ((DocumentAnnotation_Type)jcasType).casFeat_languageCandidates == null)
      jcasType.jcas.throwFeatMissing("languageCandidates", "uima.tcas.DocumentAnnotation");
    jcasType.ll_cas.ll_setRefValue(addr, ((DocumentAnnotation_Type)jcasType).casFeatCode_languageCandidates, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: id

  /** getter for id - gets Document ID, such as a URL.
   * @generated */
  public String getId() {
    if (DocumentAnnotation_Type.featOkTst && ((DocumentAnnotation_Type)jcasType).casFeat_id == null)
      jcasType.jcas.throwFeatMissing("id", "uima.tcas.DocumentAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((DocumentAnnotation_Type)jcasType).casFeatCode_id);}
    
  /** setter for id - sets Document ID, such as a URL. 
   * @generated */
  public void setId(String v) {
    if (DocumentAnnotation_Type.featOkTst && ((DocumentAnnotation_Type)jcasType).casFeat_id == null)
      jcasType.jcas.throwFeatMissing("id", "uima.tcas.DocumentAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((DocumentAnnotation_Type)jcasType).casFeatCode_id, v);}    
  }

    