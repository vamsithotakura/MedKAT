

/* First created by JCasGen Wed Jun 11 12:10:51 EDT 2008 */
package org.ohnlp.medkat.scr.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.jcas.cas.TOP;


/** primary tumor object
 * Updated by JCasGen Mon Mar 23 14:01:46 EDT 2009
 * XML source: C:/ohnlp/MedKATp/descriptors/analysis_engine/aggregate/MedKATp.xml
 * @generated */
public class SCRMultipleReadingModel extends Annotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(SCRMultipleReadingModel.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected SCRMultipleReadingModel() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public SCRMultipleReadingModel(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public SCRMultipleReadingModel(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public SCRMultipleReadingModel(JCas jcas, int begin, int end) {
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
  //* Feature: Readings

  /** getter for Readings - gets collection of primary tumor readings
   * @generated */
  public FSArray getReadings() {
    if (SCRMultipleReadingModel_Type.featOkTst && ((SCRMultipleReadingModel_Type)jcasType).casFeat_Readings == null)
      jcasType.jcas.throwFeatMissing("Readings", "org.ohnlp.medkat.scr.types.SCRMultipleReadingModel");
    return (FSArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((SCRMultipleReadingModel_Type)jcasType).casFeatCode_Readings)));}
    
  /** setter for Readings - sets collection of primary tumor readings 
   * @generated */
  public void setReadings(FSArray v) {
    if (SCRMultipleReadingModel_Type.featOkTst && ((SCRMultipleReadingModel_Type)jcasType).casFeat_Readings == null)
      jcasType.jcas.throwFeatMissing("Readings", "org.ohnlp.medkat.scr.types.SCRMultipleReadingModel");
    jcasType.ll_cas.ll_setRefValue(addr, ((SCRMultipleReadingModel_Type)jcasType).casFeatCode_Readings, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for Readings - gets an indexed value - collection of primary tumor readings
   * @generated */
  public TOP getReadings(int i) {
    if (SCRMultipleReadingModel_Type.featOkTst && ((SCRMultipleReadingModel_Type)jcasType).casFeat_Readings == null)
      jcasType.jcas.throwFeatMissing("Readings", "org.ohnlp.medkat.scr.types.SCRMultipleReadingModel");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((SCRMultipleReadingModel_Type)jcasType).casFeatCode_Readings), i);
    return (TOP)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((SCRMultipleReadingModel_Type)jcasType).casFeatCode_Readings), i)));}

  /** indexed setter for Readings - sets an indexed value - collection of primary tumor readings
   * @generated */
  public void setReadings(int i, TOP v) { 
    if (SCRMultipleReadingModel_Type.featOkTst && ((SCRMultipleReadingModel_Type)jcasType).casFeat_Readings == null)
      jcasType.jcas.throwFeatMissing("Readings", "org.ohnlp.medkat.scr.types.SCRMultipleReadingModel");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((SCRMultipleReadingModel_Type)jcasType).casFeatCode_Readings), i);
    jcasType.ll_cas.ll_setRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((SCRMultipleReadingModel_Type)jcasType).casFeatCode_Readings), i, jcasType.ll_cas.ll_getFSRef(v));}
  }

    