

/* First created by JCasGen Wed Aug 27 00:14:06 EDT 2008 */
package org.ohnlp.medkat.scr.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.jcas.cas.TOP;


/** Procedure object
 * Updated by JCasGen Wed Mar 11 15:34:13 EDT 2009
 * XML source: C:/eclipse/MedKATp/src/org/ohnlp/medkat/scr/types/SCRTypeSystem.xml
 * @generated */
public class SCRProcedure extends SCRSpannedAnnotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(SCRProcedure.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected SCRProcedure() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public SCRProcedure(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public SCRProcedure(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public SCRProcedure(JCas jcas, int begin, int end) {
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

  /** getter for Institution - gets institution where prosedure was performed
   * @generated */
  public String getInstitution() {
    if (SCRProcedure_Type.featOkTst && ((SCRProcedure_Type)jcasType).casFeat_Institution == null)
      jcasType.jcas.throwFeatMissing("Institution", "org.ohnlp.medkat.scr.types.SCRProcedure");
    return jcasType.ll_cas.ll_getStringValue(addr, ((SCRProcedure_Type)jcasType).casFeatCode_Institution);}
    
  /** setter for Institution - sets institution where prosedure was performed 
   * @generated */
  public void setInstitution(String v) {
    if (SCRProcedure_Type.featOkTst && ((SCRProcedure_Type)jcasType).casFeat_Institution == null)
      jcasType.jcas.throwFeatMissing("Institution", "org.ohnlp.medkat.scr.types.SCRProcedure");
    jcasType.ll_cas.ll_setStringValue(addr, ((SCRProcedure_Type)jcasType).casFeatCode_Institution, v);}    
   
    
  //*--------------*
  //* Feature: Date

  /** getter for Date - gets date of the procedure
   * @generated */
  public Annotation getDate() {
    if (SCRProcedure_Type.featOkTst && ((SCRProcedure_Type)jcasType).casFeat_Date == null)
      jcasType.jcas.throwFeatMissing("Date", "org.ohnlp.medkat.scr.types.SCRProcedure");
    return (Annotation)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((SCRProcedure_Type)jcasType).casFeatCode_Date)));}
    
  /** setter for Date - sets date of the procedure 
   * @generated */
  public void setDate(Annotation v) {
    if (SCRProcedure_Type.featOkTst && ((SCRProcedure_Type)jcasType).casFeat_Date == null)
      jcasType.jcas.throwFeatMissing("Date", "org.ohnlp.medkat.scr.types.SCRProcedure");
    jcasType.ll_cas.ll_setRefValue(addr, ((SCRProcedure_Type)jcasType).casFeatCode_Date, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: Sites

  /** getter for Sites - gets sites affected by the procedure
   * @generated */
  public FSArray getSites() {
    if (SCRProcedure_Type.featOkTst && ((SCRProcedure_Type)jcasType).casFeat_Sites == null)
      jcasType.jcas.throwFeatMissing("Sites", "org.ohnlp.medkat.scr.types.SCRProcedure");
    return (FSArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((SCRProcedure_Type)jcasType).casFeatCode_Sites)));}
    
  /** setter for Sites - sets sites affected by the procedure 
   * @generated */
  public void setSites(FSArray v) {
    if (SCRProcedure_Type.featOkTst && ((SCRProcedure_Type)jcasType).casFeat_Sites == null)
      jcasType.jcas.throwFeatMissing("Sites", "org.ohnlp.medkat.scr.types.SCRProcedure");
    jcasType.ll_cas.ll_setRefValue(addr, ((SCRProcedure_Type)jcasType).casFeatCode_Sites, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for Sites - gets an indexed value - sites affected by the procedure
   * @generated */
  public TOP getSites(int i) {
    if (SCRProcedure_Type.featOkTst && ((SCRProcedure_Type)jcasType).casFeat_Sites == null)
      jcasType.jcas.throwFeatMissing("Sites", "org.ohnlp.medkat.scr.types.SCRProcedure");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((SCRProcedure_Type)jcasType).casFeatCode_Sites), i);
    return (TOP)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((SCRProcedure_Type)jcasType).casFeatCode_Sites), i)));}

  /** indexed setter for Sites - sets an indexed value - sites affected by the procedure
   * @generated */
  public void setSites(int i, TOP v) { 
    if (SCRProcedure_Type.featOkTst && ((SCRProcedure_Type)jcasType).casFeat_Sites == null)
      jcasType.jcas.throwFeatMissing("Sites", "org.ohnlp.medkat.scr.types.SCRProcedure");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((SCRProcedure_Type)jcasType).casFeatCode_Sites), i);
    jcasType.ll_cas.ll_setRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((SCRProcedure_Type)jcasType).casFeatCode_Sites), i, jcasType.ll_cas.ll_getFSRef(v));}
   
    
  //*--------------*
  //* Feature: Terminology

  /** getter for Terminology - gets Terminology identifier
   * @generated */
  public String getTerminology() {
    if (SCRProcedure_Type.featOkTst && ((SCRProcedure_Type)jcasType).casFeat_Terminology == null)
      jcasType.jcas.throwFeatMissing("Terminology", "org.ohnlp.medkat.scr.types.SCRProcedure");
    return jcasType.ll_cas.ll_getStringValue(addr, ((SCRProcedure_Type)jcasType).casFeatCode_Terminology);}
    
  /** setter for Terminology - sets Terminology identifier 
   * @generated */
  public void setTerminology(String v) {
    if (SCRProcedure_Type.featOkTst && ((SCRProcedure_Type)jcasType).casFeat_Terminology == null)
      jcasType.jcas.throwFeatMissing("Terminology", "org.ohnlp.medkat.scr.types.SCRProcedure");
    jcasType.ll_cas.ll_setStringValue(addr, ((SCRProcedure_Type)jcasType).casFeatCode_Terminology, v);}    
   
    
  //*--------------*
  //* Feature: Code

  /** getter for Code - gets Code from terminology's vocablurary
   * @generated */
  public String getCode() {
    if (SCRProcedure_Type.featOkTst && ((SCRProcedure_Type)jcasType).casFeat_Code == null)
      jcasType.jcas.throwFeatMissing("Code", "org.ohnlp.medkat.scr.types.SCRProcedure");
    return jcasType.ll_cas.ll_getStringValue(addr, ((SCRProcedure_Type)jcasType).casFeatCode_Code);}
    
  /** setter for Code - sets Code from terminology's vocablurary 
   * @generated */
  public void setCode(String v) {
    if (SCRProcedure_Type.featOkTst && ((SCRProcedure_Type)jcasType).casFeat_Code == null)
      jcasType.jcas.throwFeatMissing("Code", "org.ohnlp.medkat.scr.types.SCRProcedure");
    jcasType.ll_cas.ll_setStringValue(addr, ((SCRProcedure_Type)jcasType).casFeatCode_Code, v);}    
   
    
  //*--------------*
  //* Feature: Modifiers

  /** getter for Modifiers - gets modifiers for the anatomical site
   * @generated */
  public FSArray getModifiers() {
    if (SCRProcedure_Type.featOkTst && ((SCRProcedure_Type)jcasType).casFeat_Modifiers == null)
      jcasType.jcas.throwFeatMissing("Modifiers", "org.ohnlp.medkat.scr.types.SCRProcedure");
    return (FSArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((SCRProcedure_Type)jcasType).casFeatCode_Modifiers)));}
    
  /** setter for Modifiers - sets modifiers for the anatomical site 
   * @generated */
  public void setModifiers(FSArray v) {
    if (SCRProcedure_Type.featOkTst && ((SCRProcedure_Type)jcasType).casFeat_Modifiers == null)
      jcasType.jcas.throwFeatMissing("Modifiers", "org.ohnlp.medkat.scr.types.SCRProcedure");
    jcasType.ll_cas.ll_setRefValue(addr, ((SCRProcedure_Type)jcasType).casFeatCode_Modifiers, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for Modifiers - gets an indexed value - modifiers for the anatomical site
   * @generated */
  public TOP getModifiers(int i) {
    if (SCRProcedure_Type.featOkTst && ((SCRProcedure_Type)jcasType).casFeat_Modifiers == null)
      jcasType.jcas.throwFeatMissing("Modifiers", "org.ohnlp.medkat.scr.types.SCRProcedure");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((SCRProcedure_Type)jcasType).casFeatCode_Modifiers), i);
    return (TOP)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((SCRProcedure_Type)jcasType).casFeatCode_Modifiers), i)));}

  /** indexed setter for Modifiers - sets an indexed value - modifiers for the anatomical site
   * @generated */
  public void setModifiers(int i, TOP v) { 
    if (SCRProcedure_Type.featOkTst && ((SCRProcedure_Type)jcasType).casFeat_Modifiers == null)
      jcasType.jcas.throwFeatMissing("Modifiers", "org.ohnlp.medkat.scr.types.SCRProcedure");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((SCRProcedure_Type)jcasType).casFeatCode_Modifiers), i);
    jcasType.ll_cas.ll_setRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((SCRProcedure_Type)jcasType).casFeatCode_Modifiers), i, jcasType.ll_cas.ll_getFSRef(v));}
   
    
  //*--------------*
  //* Feature: Negation

  /** getter for Negation - gets boolean flag whether the site has been negated in context
   * @generated */
  public int getNegation() {
    if (SCRProcedure_Type.featOkTst && ((SCRProcedure_Type)jcasType).casFeat_Negation == null)
      jcasType.jcas.throwFeatMissing("Negation", "org.ohnlp.medkat.scr.types.SCRProcedure");
    return jcasType.ll_cas.ll_getIntValue(addr, ((SCRProcedure_Type)jcasType).casFeatCode_Negation);}
    
  /** setter for Negation - sets boolean flag whether the site has been negated in context 
   * @generated */
  public void setNegation(int v) {
    if (SCRProcedure_Type.featOkTst && ((SCRProcedure_Type)jcasType).casFeat_Negation == null)
      jcasType.jcas.throwFeatMissing("Negation", "org.ohnlp.medkat.scr.types.SCRProcedure");
    jcasType.ll_cas.ll_setIntValue(addr, ((SCRProcedure_Type)jcasType).casFeatCode_Negation, v);}    
   
    
  //*--------------*
  //* Feature: Inference

  /** getter for Inference - gets boolean flag whether the procedue properties has been infered from context
   * @generated */
  public int getInference() {
    if (SCRProcedure_Type.featOkTst && ((SCRProcedure_Type)jcasType).casFeat_Inference == null)
      jcasType.jcas.throwFeatMissing("Inference", "org.ohnlp.medkat.scr.types.SCRProcedure");
    return jcasType.ll_cas.ll_getIntValue(addr, ((SCRProcedure_Type)jcasType).casFeatCode_Inference);}
    
  /** setter for Inference - sets boolean flag whether the procedue properties has been infered from context 
   * @generated */
  public void setInference(int v) {
    if (SCRProcedure_Type.featOkTst && ((SCRProcedure_Type)jcasType).casFeat_Inference == null)
      jcasType.jcas.throwFeatMissing("Inference", "org.ohnlp.medkat.scr.types.SCRProcedure");
    jcasType.ll_cas.ll_setIntValue(addr, ((SCRProcedure_Type)jcasType).casFeatCode_Inference, v);}    
  }

    