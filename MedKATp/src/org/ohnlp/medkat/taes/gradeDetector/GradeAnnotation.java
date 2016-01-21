

/* First created by JCasGen Wed Jun 11 12:10:52 EDT 2008 */
package org.ohnlp.medkat.taes.gradeDetector;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Mon Mar 23 14:01:46 EDT 2009
 * XML source: C:/ohnlp/MedKATp/descriptors/analysis_engine/aggregate/MedKATp.xml
 * @generated */
public class GradeAnnotation extends Annotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(GradeAnnotation.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected GradeAnnotation() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public GradeAnnotation(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public GradeAnnotation(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public GradeAnnotation(JCas jcas, int begin, int end) {
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
  //* Feature: gradeType

  /** getter for gradeType - gets 
   * @generated */
  public String getGradeType() {
    if (GradeAnnotation_Type.featOkTst && ((GradeAnnotation_Type)jcasType).casFeat_gradeType == null)
      jcasType.jcas.throwFeatMissing("gradeType", "org.ohnlp.medkat.taes.gradeDetector.GradeAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((GradeAnnotation_Type)jcasType).casFeatCode_gradeType);}
    
  /** setter for gradeType - sets  
   * @generated */
  public void setGradeType(String v) {
    if (GradeAnnotation_Type.featOkTst && ((GradeAnnotation_Type)jcasType).casFeat_gradeType == null)
      jcasType.jcas.throwFeatMissing("gradeType", "org.ohnlp.medkat.taes.gradeDetector.GradeAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((GradeAnnotation_Type)jcasType).casFeatCode_gradeType, v);}    
   
    
  //*--------------*
  //* Feature: value

  /** getter for value - gets 
   * @generated */
  public String getValue() {
    if (GradeAnnotation_Type.featOkTst && ((GradeAnnotation_Type)jcasType).casFeat_value == null)
      jcasType.jcas.throwFeatMissing("value", "org.ohnlp.medkat.taes.gradeDetector.GradeAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((GradeAnnotation_Type)jcasType).casFeatCode_value);}
    
  /** setter for value - sets  
   * @generated */
  public void setValue(String v) {
    if (GradeAnnotation_Type.featOkTst && ((GradeAnnotation_Type)jcasType).casFeat_value == null)
      jcasType.jcas.throwFeatMissing("value", "org.ohnlp.medkat.taes.gradeDetector.GradeAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((GradeAnnotation_Type)jcasType).casFeatCode_value, v);}    
   
    
  //*--------------*
  //* Feature: subsection

  /** getter for subsection - gets 
   * @generated */
  public int getSubsection() {
    if (GradeAnnotation_Type.featOkTst && ((GradeAnnotation_Type)jcasType).casFeat_subsection == null)
      jcasType.jcas.throwFeatMissing("subsection", "org.ohnlp.medkat.taes.gradeDetector.GradeAnnotation");
    return jcasType.ll_cas.ll_getIntValue(addr, ((GradeAnnotation_Type)jcasType).casFeatCode_subsection);}
    
  /** setter for subsection - sets  
   * @generated */
  public void setSubsection(int v) {
    if (GradeAnnotation_Type.featOkTst && ((GradeAnnotation_Type)jcasType).casFeat_subsection == null)
      jcasType.jcas.throwFeatMissing("subsection", "org.ohnlp.medkat.taes.gradeDetector.GradeAnnotation");
    jcasType.ll_cas.ll_setIntValue(addr, ((GradeAnnotation_Type)jcasType).casFeatCode_subsection, v);}    
   
    
  //*--------------*
  //* Feature: maxValue

  /** getter for maxValue - gets maximum value, if specified, else zero. For example, if grade is "II/IV", value would be "II" and max would be "IV"
   * @generated */
  public String getMaxValue() {
    if (GradeAnnotation_Type.featOkTst && ((GradeAnnotation_Type)jcasType).casFeat_maxValue == null)
      jcasType.jcas.throwFeatMissing("maxValue", "org.ohnlp.medkat.taes.gradeDetector.GradeAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((GradeAnnotation_Type)jcasType).casFeatCode_maxValue);}
    
  /** setter for maxValue - sets maximum value, if specified, else zero. For example, if grade is "II/IV", value would be "II" and max would be "IV" 
   * @generated */
  public void setMaxValue(String v) {
    if (GradeAnnotation_Type.featOkTst && ((GradeAnnotation_Type)jcasType).casFeat_maxValue == null)
      jcasType.jcas.throwFeatMissing("maxValue", "org.ohnlp.medkat.taes.gradeDetector.GradeAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((GradeAnnotation_Type)jcasType).casFeatCode_maxValue, v);}    
  }

    