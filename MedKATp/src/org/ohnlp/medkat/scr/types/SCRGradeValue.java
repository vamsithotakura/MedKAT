

/* First created by JCasGen Wed Jun 11 12:10:52 EDT 2008 */
package org.ohnlp.medkat.scr.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** grade value object
 * Updated by JCasGen Mon Mar 23 14:01:47 EDT 2009
 * XML source: C:/ohnlp/MedKATp/descriptors/analysis_engine/aggregate/MedKATp.xml
 * @generated */
public class SCRGradeValue extends Annotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(SCRGradeValue.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected SCRGradeValue() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public SCRGradeValue(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public SCRGradeValue(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public SCRGradeValue(JCas jcas, int begin, int end) {
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
  //* Feature: GradeType

  /** getter for GradeType - gets type of grade value
   * @generated */
  public String getGradeType() {
    if (SCRGradeValue_Type.featOkTst && ((SCRGradeValue_Type)jcasType).casFeat_GradeType == null)
      jcasType.jcas.throwFeatMissing("GradeType", "org.ohnlp.medkat.scr.types.SCRGradeValue");
    return jcasType.ll_cas.ll_getStringValue(addr, ((SCRGradeValue_Type)jcasType).casFeatCode_GradeType);}
    
  /** setter for GradeType - sets type of grade value 
   * @generated */
  public void setGradeType(String v) {
    if (SCRGradeValue_Type.featOkTst && ((SCRGradeValue_Type)jcasType).casFeat_GradeType == null)
      jcasType.jcas.throwFeatMissing("GradeType", "org.ohnlp.medkat.scr.types.SCRGradeValue");
    jcasType.ll_cas.ll_setStringValue(addr, ((SCRGradeValue_Type)jcasType).casFeatCode_GradeType, v);}    
   
    
  //*--------------*
  //* Feature: GradeValue

  /** getter for GradeValue - gets grade value
   * @generated */
  public String getGradeValue() {
    if (SCRGradeValue_Type.featOkTst && ((SCRGradeValue_Type)jcasType).casFeat_GradeValue == null)
      jcasType.jcas.throwFeatMissing("GradeValue", "org.ohnlp.medkat.scr.types.SCRGradeValue");
    return jcasType.ll_cas.ll_getStringValue(addr, ((SCRGradeValue_Type)jcasType).casFeatCode_GradeValue);}
    
  /** setter for GradeValue - sets grade value 
   * @generated */
  public void setGradeValue(String v) {
    if (SCRGradeValue_Type.featOkTst && ((SCRGradeValue_Type)jcasType).casFeat_GradeValue == null)
      jcasType.jcas.throwFeatMissing("GradeValue", "org.ohnlp.medkat.scr.types.SCRGradeValue");
    jcasType.ll_cas.ll_setStringValue(addr, ((SCRGradeValue_Type)jcasType).casFeatCode_GradeValue, v);}    
   
    
  //*--------------*
  //* Feature: GradeScale

  /** getter for GradeScale - gets scale of grade value
   * @generated */
  public String getGradeScale() {
    if (SCRGradeValue_Type.featOkTst && ((SCRGradeValue_Type)jcasType).casFeat_GradeScale == null)
      jcasType.jcas.throwFeatMissing("GradeScale", "org.ohnlp.medkat.scr.types.SCRGradeValue");
    return jcasType.ll_cas.ll_getStringValue(addr, ((SCRGradeValue_Type)jcasType).casFeatCode_GradeScale);}
    
  /** setter for GradeScale - sets scale of grade value 
   * @generated */
  public void setGradeScale(String v) {
    if (SCRGradeValue_Type.featOkTst && ((SCRGradeValue_Type)jcasType).casFeat_GradeScale == null)
      jcasType.jcas.throwFeatMissing("GradeScale", "org.ohnlp.medkat.scr.types.SCRGradeValue");
    jcasType.ll_cas.ll_setStringValue(addr, ((SCRGradeValue_Type)jcasType).casFeatCode_GradeScale, v);}    
   
    
  //*--------------*
  //* Feature: Inference

  /** getter for Inference - gets Boolean flag whether the grade value properties has been infered from context
   * @generated */
  public int getInference() {
    if (SCRGradeValue_Type.featOkTst && ((SCRGradeValue_Type)jcasType).casFeat_Inference == null)
      jcasType.jcas.throwFeatMissing("Inference", "org.ohnlp.medkat.scr.types.SCRGradeValue");
    return jcasType.ll_cas.ll_getIntValue(addr, ((SCRGradeValue_Type)jcasType).casFeatCode_Inference);}
    
  /** setter for Inference - sets Boolean flag whether the grade value properties has been infered from context 
   * @generated */
  public void setInference(int v) {
    if (SCRGradeValue_Type.featOkTst && ((SCRGradeValue_Type)jcasType).casFeat_Inference == null)
      jcasType.jcas.throwFeatMissing("Inference", "org.ohnlp.medkat.scr.types.SCRGradeValue");
    jcasType.ll_cas.ll_setIntValue(addr, ((SCRGradeValue_Type)jcasType).casFeatCode_Inference, v);}    
  }

    