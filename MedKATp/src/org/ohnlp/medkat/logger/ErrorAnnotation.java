

/* First created by JCasGen Wed Jun 11 12:20:15 EDT 2008 */
package org.ohnlp.medkat.logger;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.DocumentAnnotation;
import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Wed Mar 11 15:41:19 EDT 2009
 * XML source: C:/eclipse/MedKATp/src/org/ohnlp/medkat/logger/ErrorAnnotation.xml
 * @generated */
public class ErrorAnnotation extends Annotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(ErrorAnnotation.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected ErrorAnnotation() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public ErrorAnnotation(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public ErrorAnnotation(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public ErrorAnnotation(JCas jcas, int begin, int end) {
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
  //* Feature: errorText

  /** getter for errorText - gets 
   * @generated */
  public String getErrorText() {
    if (ErrorAnnotation_Type.featOkTst && ((ErrorAnnotation_Type)jcasType).casFeat_errorText == null)
      jcasType.jcas.throwFeatMissing("errorText", "org.ohnlp.medkat.logger.ErrorAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((ErrorAnnotation_Type)jcasType).casFeatCode_errorText);}
    
  /** setter for errorText - sets  
   * @generated */
  public void setErrorText(String v) {
    if (ErrorAnnotation_Type.featOkTst && ((ErrorAnnotation_Type)jcasType).casFeat_errorText == null)
      jcasType.jcas.throwFeatMissing("errorText", "org.ohnlp.medkat.logger.ErrorAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((ErrorAnnotation_Type)jcasType).casFeatCode_errorText, v);}    
   
    
  //*--------------*
  //* Feature: offendingDocument

  /** getter for offendingDocument - gets 
   * @generated */
  public DocumentAnnotation getOffendingDocument() {
    if (ErrorAnnotation_Type.featOkTst && ((ErrorAnnotation_Type)jcasType).casFeat_offendingDocument == null)
      jcasType.jcas.throwFeatMissing("offendingDocument", "org.ohnlp.medkat.logger.ErrorAnnotation");
    return (DocumentAnnotation)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((ErrorAnnotation_Type)jcasType).casFeatCode_offendingDocument)));}
    
  /** setter for offendingDocument - sets  
   * @generated */
  public void setOffendingDocument(DocumentAnnotation v) {
    if (ErrorAnnotation_Type.featOkTst && ((ErrorAnnotation_Type)jcasType).casFeat_offendingDocument == null)
      jcasType.jcas.throwFeatMissing("offendingDocument", "org.ohnlp.medkat.logger.ErrorAnnotation");
    jcasType.ll_cas.ll_setRefValue(addr, ((ErrorAnnotation_Type)jcasType).casFeatCode_offendingDocument, jcasType.ll_cas.ll_getFSRef(v));}    
  }

    