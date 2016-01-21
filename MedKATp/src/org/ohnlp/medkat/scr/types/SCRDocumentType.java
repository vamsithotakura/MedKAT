

/* First created by JCasGen Wed Aug 27 00:14:05 EDT 2008 */
package org.ohnlp.medkat.scr.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** document type
 * Updated by JCasGen Wed Mar 11 15:34:12 EDT 2009
 * XML source: C:/eclipse/MedKATp/src/org/ohnlp/medkat/scr/types/SCRTypeSystem.xml
 * @generated */
public class SCRDocumentType extends Annotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(SCRDocumentType.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected SCRDocumentType() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public SCRDocumentType(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public SCRDocumentType(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public SCRDocumentType(JCas jcas, int begin, int end) {
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
  //* Feature: Id

  /** getter for Id - gets document id
   * @generated */
  public String getId() {
    if (SCRDocumentType_Type.featOkTst && ((SCRDocumentType_Type)jcasType).casFeat_Id == null)
      jcasType.jcas.throwFeatMissing("Id", "org.ohnlp.medkat.scr.types.SCRDocumentType");
    return jcasType.ll_cas.ll_getStringValue(addr, ((SCRDocumentType_Type)jcasType).casFeatCode_Id);}
    
  /** setter for Id - sets document id 
   * @generated */
  public void setId(String v) {
    if (SCRDocumentType_Type.featOkTst && ((SCRDocumentType_Type)jcasType).casFeat_Id == null)
      jcasType.jcas.throwFeatMissing("Id", "org.ohnlp.medkat.scr.types.SCRDocumentType");
    jcasType.ll_cas.ll_setStringValue(addr, ((SCRDocumentType_Type)jcasType).casFeatCode_Id, v);}    
   
    
  //*--------------*
  //* Feature: SignatureMD

  /** getter for SignatureMD - gets MD who signed the report
   * @generated */
  public String getSignatureMD() {
    if (SCRDocumentType_Type.featOkTst && ((SCRDocumentType_Type)jcasType).casFeat_SignatureMD == null)
      jcasType.jcas.throwFeatMissing("SignatureMD", "org.ohnlp.medkat.scr.types.SCRDocumentType");
    return jcasType.ll_cas.ll_getStringValue(addr, ((SCRDocumentType_Type)jcasType).casFeatCode_SignatureMD);}
    
  /** setter for SignatureMD - sets MD who signed the report 
   * @generated */
  public void setSignatureMD(String v) {
    if (SCRDocumentType_Type.featOkTst && ((SCRDocumentType_Type)jcasType).casFeat_SignatureMD == null)
      jcasType.jcas.throwFeatMissing("SignatureMD", "org.ohnlp.medkat.scr.types.SCRDocumentType");
    jcasType.ll_cas.ll_setStringValue(addr, ((SCRDocumentType_Type)jcasType).casFeatCode_SignatureMD, v);}    
   
    
  //*--------------*
  //* Feature: Date

  /** getter for Date - gets Date of document
   * @generated */
  public Annotation getDate() {
    if (SCRDocumentType_Type.featOkTst && ((SCRDocumentType_Type)jcasType).casFeat_Date == null)
      jcasType.jcas.throwFeatMissing("Date", "org.ohnlp.medkat.scr.types.SCRDocumentType");
    return (Annotation)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((SCRDocumentType_Type)jcasType).casFeatCode_Date)));}
    
  /** setter for Date - sets Date of document 
   * @generated */
  public void setDate(Annotation v) {
    if (SCRDocumentType_Type.featOkTst && ((SCRDocumentType_Type)jcasType).casFeat_Date == null)
      jcasType.jcas.throwFeatMissing("Date", "org.ohnlp.medkat.scr.types.SCRDocumentType");
    jcasType.ll_cas.ll_setRefValue(addr, ((SCRDocumentType_Type)jcasType).casFeatCode_Date, jcasType.ll_cas.ll_getFSRef(v));}    
  }

    