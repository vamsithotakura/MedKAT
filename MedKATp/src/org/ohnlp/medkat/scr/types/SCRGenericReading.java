

/* First created by JCasGen Wed Jun 11 12:10:51 EDT 2008 */
package org.ohnlp.medkat.scr.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** base class for reading object
 * Updated by JCasGen Mon Mar 23 14:01:46 EDT 2009
 * XML source: C:/ohnlp/MedKATp/descriptors/analysis_engine/aggregate/MedKATp.xml
 * @generated */
public class SCRGenericReading extends Annotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(SCRGenericReading.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected SCRGenericReading() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public SCRGenericReading(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public SCRGenericReading(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public SCRGenericReading(JCas jcas, int begin, int end) {
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
  //* Feature: Institution

  /** getter for Institution - gets medical institution where tumor was described
   * @generated */
  public String getInstitution() {
    if (SCRGenericReading_Type.featOkTst && ((SCRGenericReading_Type)jcasType).casFeat_Institution == null)
      jcasType.jcas.throwFeatMissing("Institution", "org.ohnlp.medkat.scr.types.SCRGenericReading");
    return jcasType.ll_cas.ll_getStringValue(addr, ((SCRGenericReading_Type)jcasType).casFeatCode_Institution);}
    
  /** setter for Institution - sets medical institution where tumor was described 
   * @generated */
  public void setInstitution(String v) {
    if (SCRGenericReading_Type.featOkTst && ((SCRGenericReading_Type)jcasType).casFeat_Institution == null)
      jcasType.jcas.throwFeatMissing("Institution", "org.ohnlp.medkat.scr.types.SCRGenericReading");
    jcasType.ll_cas.ll_setStringValue(addr, ((SCRGenericReading_Type)jcasType).casFeatCode_Institution, v);}    
   
    
  //*--------------*
  //* Feature: Date

  /** getter for Date - gets date of the reading
   * @generated */
  public Annotation getDate() {
    if (SCRGenericReading_Type.featOkTst && ((SCRGenericReading_Type)jcasType).casFeat_Date == null)
      jcasType.jcas.throwFeatMissing("Date", "org.ohnlp.medkat.scr.types.SCRGenericReading");
    return (Annotation)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((SCRGenericReading_Type)jcasType).casFeatCode_Date)));}
    
  /** setter for Date - sets date of the reading 
   * @generated */
  public void setDate(Annotation v) {
    if (SCRGenericReading_Type.featOkTst && ((SCRGenericReading_Type)jcasType).casFeat_Date == null)
      jcasType.jcas.throwFeatMissing("Date", "org.ohnlp.medkat.scr.types.SCRGenericReading");
    jcasType.ll_cas.ll_setRefValue(addr, ((SCRGenericReading_Type)jcasType).casFeatCode_Date, jcasType.ll_cas.ll_getFSRef(v));}    
  }

    