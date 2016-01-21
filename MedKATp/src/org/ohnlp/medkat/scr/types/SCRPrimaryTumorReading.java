

/* First created by JCasGen Wed Jun 11 12:10:52 EDT 2008 */
package org.ohnlp.medkat.scr.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.jcas.cas.TOP;


/** primary tumor reading object
 * Updated by JCasGen Mon Mar 23 14:01:46 EDT 2009
 * XML source: C:/ohnlp/MedKATp/descriptors/analysis_engine/aggregate/MedKATp.xml
 * @generated */
public class SCRPrimaryTumorReading extends SCRGenericReading {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(SCRPrimaryTumorReading.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected SCRPrimaryTumorReading() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public SCRPrimaryTumorReading(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public SCRPrimaryTumorReading(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public SCRPrimaryTumorReading(JCas jcas, int begin, int end) {
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
  //* Feature: PrimarySites

  /** getter for PrimarySites - gets 
   * @generated */
  public FSArray getPrimarySites() {
    if (SCRPrimaryTumorReading_Type.featOkTst && ((SCRPrimaryTumorReading_Type)jcasType).casFeat_PrimarySites == null)
      jcasType.jcas.throwFeatMissing("PrimarySites", "org.ohnlp.medkat.scr.types.SCRPrimaryTumorReading");
    return (FSArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((SCRPrimaryTumorReading_Type)jcasType).casFeatCode_PrimarySites)));}
    
  /** setter for PrimarySites - sets  
   * @generated */
  public void setPrimarySites(FSArray v) {
    if (SCRPrimaryTumorReading_Type.featOkTst && ((SCRPrimaryTumorReading_Type)jcasType).casFeat_PrimarySites == null)
      jcasType.jcas.throwFeatMissing("PrimarySites", "org.ohnlp.medkat.scr.types.SCRPrimaryTumorReading");
    jcasType.ll_cas.ll_setRefValue(addr, ((SCRPrimaryTumorReading_Type)jcasType).casFeatCode_PrimarySites, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for PrimarySites - gets an indexed value - 
   * @generated */
  public TOP getPrimarySites(int i) {
    if (SCRPrimaryTumorReading_Type.featOkTst && ((SCRPrimaryTumorReading_Type)jcasType).casFeat_PrimarySites == null)
      jcasType.jcas.throwFeatMissing("PrimarySites", "org.ohnlp.medkat.scr.types.SCRPrimaryTumorReading");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((SCRPrimaryTumorReading_Type)jcasType).casFeatCode_PrimarySites), i);
    return (TOP)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((SCRPrimaryTumorReading_Type)jcasType).casFeatCode_PrimarySites), i)));}

  /** indexed setter for PrimarySites - sets an indexed value - 
   * @generated */
  public void setPrimarySites(int i, TOP v) { 
    if (SCRPrimaryTumorReading_Type.featOkTst && ((SCRPrimaryTumorReading_Type)jcasType).casFeat_PrimarySites == null)
      jcasType.jcas.throwFeatMissing("PrimarySites", "org.ohnlp.medkat.scr.types.SCRPrimaryTumorReading");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((SCRPrimaryTumorReading_Type)jcasType).casFeatCode_PrimarySites), i);
    jcasType.ll_cas.ll_setRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((SCRPrimaryTumorReading_Type)jcasType).casFeatCode_PrimarySites), i, jcasType.ll_cas.ll_getFSRef(v));}
   
    
  //*--------------*
  //* Feature: GradeValues

  /** getter for GradeValues - gets grade values of the primary tumor
   * @generated */
  public FSArray getGradeValues() {
    if (SCRPrimaryTumorReading_Type.featOkTst && ((SCRPrimaryTumorReading_Type)jcasType).casFeat_GradeValues == null)
      jcasType.jcas.throwFeatMissing("GradeValues", "org.ohnlp.medkat.scr.types.SCRPrimaryTumorReading");
    return (FSArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((SCRPrimaryTumorReading_Type)jcasType).casFeatCode_GradeValues)));}
    
  /** setter for GradeValues - sets grade values of the primary tumor 
   * @generated */
  public void setGradeValues(FSArray v) {
    if (SCRPrimaryTumorReading_Type.featOkTst && ((SCRPrimaryTumorReading_Type)jcasType).casFeat_GradeValues == null)
      jcasType.jcas.throwFeatMissing("GradeValues", "org.ohnlp.medkat.scr.types.SCRPrimaryTumorReading");
    jcasType.ll_cas.ll_setRefValue(addr, ((SCRPrimaryTumorReading_Type)jcasType).casFeatCode_GradeValues, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for GradeValues - gets an indexed value - grade values of the primary tumor
   * @generated */
  public TOP getGradeValues(int i) {
    if (SCRPrimaryTumorReading_Type.featOkTst && ((SCRPrimaryTumorReading_Type)jcasType).casFeat_GradeValues == null)
      jcasType.jcas.throwFeatMissing("GradeValues", "org.ohnlp.medkat.scr.types.SCRPrimaryTumorReading");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((SCRPrimaryTumorReading_Type)jcasType).casFeatCode_GradeValues), i);
    return (TOP)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((SCRPrimaryTumorReading_Type)jcasType).casFeatCode_GradeValues), i)));}

  /** indexed setter for GradeValues - sets an indexed value - grade values of the primary tumor
   * @generated */
  public void setGradeValues(int i, TOP v) { 
    if (SCRPrimaryTumorReading_Type.featOkTst && ((SCRPrimaryTumorReading_Type)jcasType).casFeat_GradeValues == null)
      jcasType.jcas.throwFeatMissing("GradeValues", "org.ohnlp.medkat.scr.types.SCRPrimaryTumorReading");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((SCRPrimaryTumorReading_Type)jcasType).casFeatCode_GradeValues), i);
    jcasType.ll_cas.ll_setRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((SCRPrimaryTumorReading_Type)jcasType).casFeatCode_GradeValues), i, jcasType.ll_cas.ll_getFSRef(v));}
   
    
  //*--------------*
  //* Feature: Diagnoses

  /** getter for Diagnoses - gets diagnoses assosiated with the primary tumor
   * @generated */
  public FSArray getDiagnoses() {
    if (SCRPrimaryTumorReading_Type.featOkTst && ((SCRPrimaryTumorReading_Type)jcasType).casFeat_Diagnoses == null)
      jcasType.jcas.throwFeatMissing("Diagnoses", "org.ohnlp.medkat.scr.types.SCRPrimaryTumorReading");
    return (FSArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((SCRPrimaryTumorReading_Type)jcasType).casFeatCode_Diagnoses)));}
    
  /** setter for Diagnoses - sets diagnoses assosiated with the primary tumor 
   * @generated */
  public void setDiagnoses(FSArray v) {
    if (SCRPrimaryTumorReading_Type.featOkTst && ((SCRPrimaryTumorReading_Type)jcasType).casFeat_Diagnoses == null)
      jcasType.jcas.throwFeatMissing("Diagnoses", "org.ohnlp.medkat.scr.types.SCRPrimaryTumorReading");
    jcasType.ll_cas.ll_setRefValue(addr, ((SCRPrimaryTumorReading_Type)jcasType).casFeatCode_Diagnoses, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for Diagnoses - gets an indexed value - diagnoses assosiated with the primary tumor
   * @generated */
  public TOP getDiagnoses(int i) {
    if (SCRPrimaryTumorReading_Type.featOkTst && ((SCRPrimaryTumorReading_Type)jcasType).casFeat_Diagnoses == null)
      jcasType.jcas.throwFeatMissing("Diagnoses", "org.ohnlp.medkat.scr.types.SCRPrimaryTumorReading");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((SCRPrimaryTumorReading_Type)jcasType).casFeatCode_Diagnoses), i);
    return (TOP)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((SCRPrimaryTumorReading_Type)jcasType).casFeatCode_Diagnoses), i)));}

  /** indexed setter for Diagnoses - sets an indexed value - diagnoses assosiated with the primary tumor
   * @generated */
  public void setDiagnoses(int i, TOP v) { 
    if (SCRPrimaryTumorReading_Type.featOkTst && ((SCRPrimaryTumorReading_Type)jcasType).casFeat_Diagnoses == null)
      jcasType.jcas.throwFeatMissing("Diagnoses", "org.ohnlp.medkat.scr.types.SCRPrimaryTumorReading");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((SCRPrimaryTumorReading_Type)jcasType).casFeatCode_Diagnoses), i);
    jcasType.ll_cas.ll_setRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((SCRPrimaryTumorReading_Type)jcasType).casFeatCode_Diagnoses), i, jcasType.ll_cas.ll_getFSRef(v));}
   
    
  //*--------------*
  //* Feature: InvasionTypes

  /** getter for InvasionTypes - gets invasion types associated with the primary tumor
   * @generated */
  public FSArray getInvasionTypes() {
    if (SCRPrimaryTumorReading_Type.featOkTst && ((SCRPrimaryTumorReading_Type)jcasType).casFeat_InvasionTypes == null)
      jcasType.jcas.throwFeatMissing("InvasionTypes", "org.ohnlp.medkat.scr.types.SCRPrimaryTumorReading");
    return (FSArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((SCRPrimaryTumorReading_Type)jcasType).casFeatCode_InvasionTypes)));}
    
  /** setter for InvasionTypes - sets invasion types associated with the primary tumor 
   * @generated */
  public void setInvasionTypes(FSArray v) {
    if (SCRPrimaryTumorReading_Type.featOkTst && ((SCRPrimaryTumorReading_Type)jcasType).casFeat_InvasionTypes == null)
      jcasType.jcas.throwFeatMissing("InvasionTypes", "org.ohnlp.medkat.scr.types.SCRPrimaryTumorReading");
    jcasType.ll_cas.ll_setRefValue(addr, ((SCRPrimaryTumorReading_Type)jcasType).casFeatCode_InvasionTypes, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for InvasionTypes - gets an indexed value - invasion types associated with the primary tumor
   * @generated */
  public TOP getInvasionTypes(int i) {
    if (SCRPrimaryTumorReading_Type.featOkTst && ((SCRPrimaryTumorReading_Type)jcasType).casFeat_InvasionTypes == null)
      jcasType.jcas.throwFeatMissing("InvasionTypes", "org.ohnlp.medkat.scr.types.SCRPrimaryTumorReading");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((SCRPrimaryTumorReading_Type)jcasType).casFeatCode_InvasionTypes), i);
    return (TOP)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((SCRPrimaryTumorReading_Type)jcasType).casFeatCode_InvasionTypes), i)));}

  /** indexed setter for InvasionTypes - sets an indexed value - invasion types associated with the primary tumor
   * @generated */
  public void setInvasionTypes(int i, TOP v) { 
    if (SCRPrimaryTumorReading_Type.featOkTst && ((SCRPrimaryTumorReading_Type)jcasType).casFeat_InvasionTypes == null)
      jcasType.jcas.throwFeatMissing("InvasionTypes", "org.ohnlp.medkat.scr.types.SCRPrimaryTumorReading");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((SCRPrimaryTumorReading_Type)jcasType).casFeatCode_InvasionTypes), i);
    jcasType.ll_cas.ll_setRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((SCRPrimaryTumorReading_Type)jcasType).casFeatCode_InvasionTypes), i, jcasType.ll_cas.ll_getFSRef(v));}
   
    
  //*--------------*
  //* Feature: Size

  /** getter for Size - gets size of the primary tumor
   * @generated */
  public Annotation getSize() {
    if (SCRPrimaryTumorReading_Type.featOkTst && ((SCRPrimaryTumorReading_Type)jcasType).casFeat_Size == null)
      jcasType.jcas.throwFeatMissing("Size", "org.ohnlp.medkat.scr.types.SCRPrimaryTumorReading");
    return (Annotation)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((SCRPrimaryTumorReading_Type)jcasType).casFeatCode_Size)));}
    
  /** setter for Size - sets size of the primary tumor 
   * @generated */
  public void setSize(Annotation v) {
    if (SCRPrimaryTumorReading_Type.featOkTst && ((SCRPrimaryTumorReading_Type)jcasType).casFeat_Size == null)
      jcasType.jcas.throwFeatMissing("Size", "org.ohnlp.medkat.scr.types.SCRPrimaryTumorReading");
    jcasType.ll_cas.ll_setRefValue(addr, ((SCRPrimaryTumorReading_Type)jcasType).casFeatCode_Size, jcasType.ll_cas.ll_getFSRef(v));}    
  }

    