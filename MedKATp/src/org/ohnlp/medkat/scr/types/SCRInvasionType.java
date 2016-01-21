

/* First created by JCasGen Wed Aug 27 00:14:05 EDT 2008 */
package org.ohnlp.medkat.scr.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.cas.TOP;


/** SCR invasaion type
 * Updated by JCasGen Wed Mar 11 15:34:12 EDT 2009
 * XML source: C:/eclipse/MedKATp/src/org/ohnlp/medkat/scr/types/SCRTypeSystem.xml
 * @generated */
public class SCRInvasionType extends SCRSpannedAnnotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(SCRInvasionType.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected SCRInvasionType() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public SCRInvasionType(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public SCRInvasionType(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public SCRInvasionType(JCas jcas, int begin, int end) {
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
  //* Feature: Terminology

  /** getter for Terminology - gets Terminology identifier
   * @generated */
  public String getTerminology() {
    if (SCRInvasionType_Type.featOkTst && ((SCRInvasionType_Type)jcasType).casFeat_Terminology == null)
      jcasType.jcas.throwFeatMissing("Terminology", "org.ohnlp.medkat.scr.types.SCRInvasionType");
    return jcasType.ll_cas.ll_getStringValue(addr, ((SCRInvasionType_Type)jcasType).casFeatCode_Terminology);}
    
  /** setter for Terminology - sets Terminology identifier 
   * @generated */
  public void setTerminology(String v) {
    if (SCRInvasionType_Type.featOkTst && ((SCRInvasionType_Type)jcasType).casFeat_Terminology == null)
      jcasType.jcas.throwFeatMissing("Terminology", "org.ohnlp.medkat.scr.types.SCRInvasionType");
    jcasType.ll_cas.ll_setStringValue(addr, ((SCRInvasionType_Type)jcasType).casFeatCode_Terminology, v);}    
   
    
  //*--------------*
  //* Feature: Code

  /** getter for Code - gets Code from terminology's vocablurary
   * @generated */
  public String getCode() {
    if (SCRInvasionType_Type.featOkTst && ((SCRInvasionType_Type)jcasType).casFeat_Code == null)
      jcasType.jcas.throwFeatMissing("Code", "org.ohnlp.medkat.scr.types.SCRInvasionType");
    return jcasType.ll_cas.ll_getStringValue(addr, ((SCRInvasionType_Type)jcasType).casFeatCode_Code);}
    
  /** setter for Code - sets Code from terminology's vocablurary 
   * @generated */
  public void setCode(String v) {
    if (SCRInvasionType_Type.featOkTst && ((SCRInvasionType_Type)jcasType).casFeat_Code == null)
      jcasType.jcas.throwFeatMissing("Code", "org.ohnlp.medkat.scr.types.SCRInvasionType");
    jcasType.ll_cas.ll_setStringValue(addr, ((SCRInvasionType_Type)jcasType).casFeatCode_Code, v);}    
   
    
  //*--------------*
  //* Feature: InvasionType

  /** getter for InvasionType - gets type of invasion
   * @generated */
  public String getInvasionType() {
    if (SCRInvasionType_Type.featOkTst && ((SCRInvasionType_Type)jcasType).casFeat_InvasionType == null)
      jcasType.jcas.throwFeatMissing("InvasionType", "org.ohnlp.medkat.scr.types.SCRInvasionType");
    return jcasType.ll_cas.ll_getStringValue(addr, ((SCRInvasionType_Type)jcasType).casFeatCode_InvasionType);}
    
  /** setter for InvasionType - sets type of invasion 
   * @generated */
  public void setInvasionType(String v) {
    if (SCRInvasionType_Type.featOkTst && ((SCRInvasionType_Type)jcasType).casFeat_InvasionType == null)
      jcasType.jcas.throwFeatMissing("InvasionType", "org.ohnlp.medkat.scr.types.SCRInvasionType");
    jcasType.ll_cas.ll_setStringValue(addr, ((SCRInvasionType_Type)jcasType).casFeatCode_InvasionType, v);}    
   
    
  //*--------------*
  //* Feature: InvasionLevel

  /** getter for InvasionLevel - gets level of the invasion
   * @generated */
  public String getInvasionLevel() {
    if (SCRInvasionType_Type.featOkTst && ((SCRInvasionType_Type)jcasType).casFeat_InvasionLevel == null)
      jcasType.jcas.throwFeatMissing("InvasionLevel", "org.ohnlp.medkat.scr.types.SCRInvasionType");
    return jcasType.ll_cas.ll_getStringValue(addr, ((SCRInvasionType_Type)jcasType).casFeatCode_InvasionLevel);}
    
  /** setter for InvasionLevel - sets level of the invasion 
   * @generated */
  public void setInvasionLevel(String v) {
    if (SCRInvasionType_Type.featOkTst && ((SCRInvasionType_Type)jcasType).casFeat_InvasionLevel == null)
      jcasType.jcas.throwFeatMissing("InvasionLevel", "org.ohnlp.medkat.scr.types.SCRInvasionType");
    jcasType.ll_cas.ll_setStringValue(addr, ((SCRInvasionType_Type)jcasType).casFeatCode_InvasionLevel, v);}    
   
    
  //*--------------*
  //* Feature: Negation

  /** getter for Negation - gets boolean flag whether the site has been negated in context
   * @generated */
  public int getNegation() {
    if (SCRInvasionType_Type.featOkTst && ((SCRInvasionType_Type)jcasType).casFeat_Negation == null)
      jcasType.jcas.throwFeatMissing("Negation", "org.ohnlp.medkat.scr.types.SCRInvasionType");
    return jcasType.ll_cas.ll_getIntValue(addr, ((SCRInvasionType_Type)jcasType).casFeatCode_Negation);}
    
  /** setter for Negation - sets boolean flag whether the site has been negated in context 
   * @generated */
  public void setNegation(int v) {
    if (SCRInvasionType_Type.featOkTst && ((SCRInvasionType_Type)jcasType).casFeat_Negation == null)
      jcasType.jcas.throwFeatMissing("Negation", "org.ohnlp.medkat.scr.types.SCRInvasionType");
    jcasType.ll_cas.ll_setIntValue(addr, ((SCRInvasionType_Type)jcasType).casFeatCode_Negation, v);}    
   
    
  //*--------------*
  //* Feature: Inference

  /** getter for Inference - gets Boolean flag whether the anatomical site properties has been infered from context
   * @generated */
  public int getInference() {
    if (SCRInvasionType_Type.featOkTst && ((SCRInvasionType_Type)jcasType).casFeat_Inference == null)
      jcasType.jcas.throwFeatMissing("Inference", "org.ohnlp.medkat.scr.types.SCRInvasionType");
    return jcasType.ll_cas.ll_getIntValue(addr, ((SCRInvasionType_Type)jcasType).casFeatCode_Inference);}
    
  /** setter for Inference - sets Boolean flag whether the anatomical site properties has been infered from context 
   * @generated */
  public void setInference(int v) {
    if (SCRInvasionType_Type.featOkTst && ((SCRInvasionType_Type)jcasType).casFeat_Inference == null)
      jcasType.jcas.throwFeatMissing("Inference", "org.ohnlp.medkat.scr.types.SCRInvasionType");
    jcasType.ll_cas.ll_setIntValue(addr, ((SCRInvasionType_Type)jcasType).casFeatCode_Inference, v);}    
   
    
  //*--------------*
  //* Feature: Sites

  /** getter for Sites - gets invaded sites
   * @generated */
  public FSArray getSites() {
    if (SCRInvasionType_Type.featOkTst && ((SCRInvasionType_Type)jcasType).casFeat_Sites == null)
      jcasType.jcas.throwFeatMissing("Sites", "org.ohnlp.medkat.scr.types.SCRInvasionType");
    return (FSArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((SCRInvasionType_Type)jcasType).casFeatCode_Sites)));}
    
  /** setter for Sites - sets invaded sites 
   * @generated */
  public void setSites(FSArray v) {
    if (SCRInvasionType_Type.featOkTst && ((SCRInvasionType_Type)jcasType).casFeat_Sites == null)
      jcasType.jcas.throwFeatMissing("Sites", "org.ohnlp.medkat.scr.types.SCRInvasionType");
    jcasType.ll_cas.ll_setRefValue(addr, ((SCRInvasionType_Type)jcasType).casFeatCode_Sites, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for Sites - gets an indexed value - invaded sites
   * @generated */
  public TOP getSites(int i) {
    if (SCRInvasionType_Type.featOkTst && ((SCRInvasionType_Type)jcasType).casFeat_Sites == null)
      jcasType.jcas.throwFeatMissing("Sites", "org.ohnlp.medkat.scr.types.SCRInvasionType");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((SCRInvasionType_Type)jcasType).casFeatCode_Sites), i);
    return (TOP)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((SCRInvasionType_Type)jcasType).casFeatCode_Sites), i)));}

  /** indexed setter for Sites - sets an indexed value - invaded sites
   * @generated */
  public void setSites(int i, TOP v) { 
    if (SCRInvasionType_Type.featOkTst && ((SCRInvasionType_Type)jcasType).casFeat_Sites == null)
      jcasType.jcas.throwFeatMissing("Sites", "org.ohnlp.medkat.scr.types.SCRInvasionType");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((SCRInvasionType_Type)jcasType).casFeatCode_Sites), i);
    jcasType.ll_cas.ll_setRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((SCRInvasionType_Type)jcasType).casFeatCode_Sites), i, jcasType.ll_cas.ll_getFSRef(v));}
  }

    