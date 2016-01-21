

/* First created by JCasGen Wed Aug 27 00:14:05 EDT 2008 */
package org.ohnlp.medkat.scr.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** Size dimension object
 * Updated by JCasGen Wed Mar 11 15:34:12 EDT 2009
 * XML source: C:/eclipse/MedKATp/src/org/ohnlp/medkat/scr/types/SCRTypeSystem.xml
 * @generated */
public class SCRMargin extends Annotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(SCRMargin.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected SCRMargin() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public SCRMargin(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public SCRMargin(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public SCRMargin(JCas jcas, int begin, int end) {
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
  //* Feature: State

  /** getter for State - gets state of the margin (+/-)
   * @generated */
  public String getState() {
    if (SCRMargin_Type.featOkTst && ((SCRMargin_Type)jcasType).casFeat_State == null)
      jcasType.jcas.throwFeatMissing("State", "org.ohnlp.medkat.scr.types.SCRMargin");
    return jcasType.ll_cas.ll_getStringValue(addr, ((SCRMargin_Type)jcasType).casFeatCode_State);}
    
  /** setter for State - sets state of the margin (+/-) 
   * @generated */
  public void setState(String v) {
    if (SCRMargin_Type.featOkTst && ((SCRMargin_Type)jcasType).casFeat_State == null)
      jcasType.jcas.throwFeatMissing("State", "org.ohnlp.medkat.scr.types.SCRMargin");
    jcasType.ll_cas.ll_setStringValue(addr, ((SCRMargin_Type)jcasType).casFeatCode_State, v);}    
   
    
  //*--------------*
  //* Feature: MarginType

  /** getter for MarginType - gets type of the margin
   * @generated */
  public String getMarginType() {
    if (SCRMargin_Type.featOkTst && ((SCRMargin_Type)jcasType).casFeat_MarginType == null)
      jcasType.jcas.throwFeatMissing("MarginType", "org.ohnlp.medkat.scr.types.SCRMargin");
    return jcasType.ll_cas.ll_getStringValue(addr, ((SCRMargin_Type)jcasType).casFeatCode_MarginType);}
    
  /** setter for MarginType - sets type of the margin 
   * @generated */
  public void setMarginType(String v) {
    if (SCRMargin_Type.featOkTst && ((SCRMargin_Type)jcasType).casFeat_MarginType == null)
      jcasType.jcas.throwFeatMissing("MarginType", "org.ohnlp.medkat.scr.types.SCRMargin");
    jcasType.ll_cas.ll_setStringValue(addr, ((SCRMargin_Type)jcasType).casFeatCode_MarginType, v);}    
   
    
  //*--------------*
  //* Feature: Inference

  /** getter for Inference - gets Boolean flag whether the anatomical site properties has been infered from context
   * @generated */
  public int getInference() {
    if (SCRMargin_Type.featOkTst && ((SCRMargin_Type)jcasType).casFeat_Inference == null)
      jcasType.jcas.throwFeatMissing("Inference", "org.ohnlp.medkat.scr.types.SCRMargin");
    return jcasType.ll_cas.ll_getIntValue(addr, ((SCRMargin_Type)jcasType).casFeatCode_Inference);}
    
  /** setter for Inference - sets Boolean flag whether the anatomical site properties has been infered from context 
   * @generated */
  public void setInference(int v) {
    if (SCRMargin_Type.featOkTst && ((SCRMargin_Type)jcasType).casFeat_Inference == null)
      jcasType.jcas.throwFeatMissing("Inference", "org.ohnlp.medkat.scr.types.SCRMargin");
    jcasType.ll_cas.ll_setIntValue(addr, ((SCRMargin_Type)jcasType).casFeatCode_Inference, v);}    
  }

    