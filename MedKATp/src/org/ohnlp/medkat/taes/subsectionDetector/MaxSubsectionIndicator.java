

/* First created by JCasGen Wed Jun 11 12:10:50 EDT 2008 */
package org.ohnlp.medkat.taes.subsectionDetector;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Mon Mar 23 14:01:46 EDT 2009
 * XML source: C:/ohnlp/MedKATp/descriptors/analysis_engine/aggregate/MedKATp.xml
 * @generated */
public class MaxSubsectionIndicator extends Annotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(MaxSubsectionIndicator.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected MaxSubsectionIndicator() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public MaxSubsectionIndicator(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public MaxSubsectionIndicator(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public MaxSubsectionIndicator(JCas jcas, int begin, int end) {
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
  //* Feature: value

  /** getter for value - gets 
   * @generated */
  public int getValue() {
    if (MaxSubsectionIndicator_Type.featOkTst && ((MaxSubsectionIndicator_Type)jcasType).casFeat_value == null)
      jcasType.jcas.throwFeatMissing("value", "org.ohnlp.medkat.taes.subsectionDetector.MaxSubsectionIndicator");
    return jcasType.ll_cas.ll_getIntValue(addr, ((MaxSubsectionIndicator_Type)jcasType).casFeatCode_value);}
    
  /** setter for value - sets  
   * @generated */
  public void setValue(int v) {
    if (MaxSubsectionIndicator_Type.featOkTst && ((MaxSubsectionIndicator_Type)jcasType).casFeat_value == null)
      jcasType.jcas.throwFeatMissing("value", "org.ohnlp.medkat.taes.subsectionDetector.MaxSubsectionIndicator");
    jcasType.ll_cas.ll_setIntValue(addr, ((MaxSubsectionIndicator_Type)jcasType).casFeatCode_value, v);}    
  }

    