

/* First created by JCasGen Wed Jun 11 12:10:51 EDT 2008 */
package org.ohnlp.medkat.scr.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.cas.TOP;


/** description of lymph nodes reading
 * Updated by JCasGen Mon Mar 23 14:01:48 EDT 2009
 * XML source: C:/ohnlp/MedKATp/descriptors/analysis_engine/aggregate/MedKATp.xml
 * @generated */
public class SCRLymphNodesReading extends SCRGenericReading {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(SCRLymphNodesReading.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected SCRLymphNodesReading() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public SCRLymphNodesReading(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public SCRLymphNodesReading(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public SCRLymphNodesReading(JCas jcas, int begin, int end) {
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
  //* Feature: Sites

  /** getter for Sites - gets Sites
   * @generated */
  public FSArray getSites() {
    if (SCRLymphNodesReading_Type.featOkTst && ((SCRLymphNodesReading_Type)jcasType).casFeat_Sites == null)
      jcasType.jcas.throwFeatMissing("Sites", "org.ohnlp.medkat.scr.types.SCRLymphNodesReading");
    return (FSArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((SCRLymphNodesReading_Type)jcasType).casFeatCode_Sites)));}
    
  /** setter for Sites - sets Sites 
   * @generated */
  public void setSites(FSArray v) {
    if (SCRLymphNodesReading_Type.featOkTst && ((SCRLymphNodesReading_Type)jcasType).casFeat_Sites == null)
      jcasType.jcas.throwFeatMissing("Sites", "org.ohnlp.medkat.scr.types.SCRLymphNodesReading");
    jcasType.ll_cas.ll_setRefValue(addr, ((SCRLymphNodesReading_Type)jcasType).casFeatCode_Sites, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for Sites - gets an indexed value - Sites
   * @generated */
  public TOP getSites(int i) {
    if (SCRLymphNodesReading_Type.featOkTst && ((SCRLymphNodesReading_Type)jcasType).casFeat_Sites == null)
      jcasType.jcas.throwFeatMissing("Sites", "org.ohnlp.medkat.scr.types.SCRLymphNodesReading");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((SCRLymphNodesReading_Type)jcasType).casFeatCode_Sites), i);
    return (TOP)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((SCRLymphNodesReading_Type)jcasType).casFeatCode_Sites), i)));}

  /** indexed setter for Sites - sets an indexed value - Sites
   * @generated */
  public void setSites(int i, TOP v) { 
    if (SCRLymphNodesReading_Type.featOkTst && ((SCRLymphNodesReading_Type)jcasType).casFeat_Sites == null)
      jcasType.jcas.throwFeatMissing("Sites", "org.ohnlp.medkat.scr.types.SCRLymphNodesReading");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((SCRLymphNodesReading_Type)jcasType).casFeatCode_Sites), i);
    jcasType.ll_cas.ll_setRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((SCRLymphNodesReading_Type)jcasType).casFeatCode_Sites), i, jcasType.ll_cas.ll_getFSRef(v));}
   
    
  //*--------------*
  //* Feature: Diagnoses

  /** getter for Diagnoses - gets diagnoses
   * @generated */
  public FSArray getDiagnoses() {
    if (SCRLymphNodesReading_Type.featOkTst && ((SCRLymphNodesReading_Type)jcasType).casFeat_Diagnoses == null)
      jcasType.jcas.throwFeatMissing("Diagnoses", "org.ohnlp.medkat.scr.types.SCRLymphNodesReading");
    return (FSArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((SCRLymphNodesReading_Type)jcasType).casFeatCode_Diagnoses)));}
    
  /** setter for Diagnoses - sets diagnoses 
   * @generated */
  public void setDiagnoses(FSArray v) {
    if (SCRLymphNodesReading_Type.featOkTst && ((SCRLymphNodesReading_Type)jcasType).casFeat_Diagnoses == null)
      jcasType.jcas.throwFeatMissing("Diagnoses", "org.ohnlp.medkat.scr.types.SCRLymphNodesReading");
    jcasType.ll_cas.ll_setRefValue(addr, ((SCRLymphNodesReading_Type)jcasType).casFeatCode_Diagnoses, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for Diagnoses - gets an indexed value - diagnoses
   * @generated */
  public TOP getDiagnoses(int i) {
    if (SCRLymphNodesReading_Type.featOkTst && ((SCRLymphNodesReading_Type)jcasType).casFeat_Diagnoses == null)
      jcasType.jcas.throwFeatMissing("Diagnoses", "org.ohnlp.medkat.scr.types.SCRLymphNodesReading");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((SCRLymphNodesReading_Type)jcasType).casFeatCode_Diagnoses), i);
    return (TOP)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((SCRLymphNodesReading_Type)jcasType).casFeatCode_Diagnoses), i)));}

  /** indexed setter for Diagnoses - sets an indexed value - diagnoses
   * @generated */
  public void setDiagnoses(int i, TOP v) { 
    if (SCRLymphNodesReading_Type.featOkTst && ((SCRLymphNodesReading_Type)jcasType).casFeat_Diagnoses == null)
      jcasType.jcas.throwFeatMissing("Diagnoses", "org.ohnlp.medkat.scr.types.SCRLymphNodesReading");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((SCRLymphNodesReading_Type)jcasType).casFeatCode_Diagnoses), i);
    jcasType.ll_cas.ll_setRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((SCRLymphNodesReading_Type)jcasType).casFeatCode_Diagnoses), i, jcasType.ll_cas.ll_getFSRef(v));}
   
    
  //*--------------*
  //* Feature: PositiveNodes

  /** getter for PositiveNodes - gets count of lymph nodes positive for tumor
   * @generated */
  public int getPositiveNodes() {
    if (SCRLymphNodesReading_Type.featOkTst && ((SCRLymphNodesReading_Type)jcasType).casFeat_PositiveNodes == null)
      jcasType.jcas.throwFeatMissing("PositiveNodes", "org.ohnlp.medkat.scr.types.SCRLymphNodesReading");
    return jcasType.ll_cas.ll_getIntValue(addr, ((SCRLymphNodesReading_Type)jcasType).casFeatCode_PositiveNodes);}
    
  /** setter for PositiveNodes - sets count of lymph nodes positive for tumor 
   * @generated */
  public void setPositiveNodes(int v) {
    if (SCRLymphNodesReading_Type.featOkTst && ((SCRLymphNodesReading_Type)jcasType).casFeat_PositiveNodes == null)
      jcasType.jcas.throwFeatMissing("PositiveNodes", "org.ohnlp.medkat.scr.types.SCRLymphNodesReading");
    jcasType.ll_cas.ll_setIntValue(addr, ((SCRLymphNodesReading_Type)jcasType).casFeatCode_PositiveNodes, v);}    
   
    
  //*--------------*
  //* Feature: TotalNodes

  /** getter for TotalNodes - gets total nodes count
   * @generated */
  public int getTotalNodes() {
    if (SCRLymphNodesReading_Type.featOkTst && ((SCRLymphNodesReading_Type)jcasType).casFeat_TotalNodes == null)
      jcasType.jcas.throwFeatMissing("TotalNodes", "org.ohnlp.medkat.scr.types.SCRLymphNodesReading");
    return jcasType.ll_cas.ll_getIntValue(addr, ((SCRLymphNodesReading_Type)jcasType).casFeatCode_TotalNodes);}
    
  /** setter for TotalNodes - sets total nodes count 
   * @generated */
  public void setTotalNodes(int v) {
    if (SCRLymphNodesReading_Type.featOkTst && ((SCRLymphNodesReading_Type)jcasType).casFeat_TotalNodes == null)
      jcasType.jcas.throwFeatMissing("TotalNodes", "org.ohnlp.medkat.scr.types.SCRLymphNodesReading");
    jcasType.ll_cas.ll_setIntValue(addr, ((SCRLymphNodesReading_Type)jcasType).casFeatCode_TotalNodes, v);}    
   
    
  //*--------------*
  //* Feature: Inference

  /** getter for Inference - gets Boolean flag whether the anatomical site properties has been infered from context
   * @generated */
  public int getInference() {
    if (SCRLymphNodesReading_Type.featOkTst && ((SCRLymphNodesReading_Type)jcasType).casFeat_Inference == null)
      jcasType.jcas.throwFeatMissing("Inference", "org.ohnlp.medkat.scr.types.SCRLymphNodesReading");
    return jcasType.ll_cas.ll_getIntValue(addr, ((SCRLymphNodesReading_Type)jcasType).casFeatCode_Inference);}
    
  /** setter for Inference - sets Boolean flag whether the anatomical site properties has been infered from context 
   * @generated */
  public void setInference(int v) {
    if (SCRLymphNodesReading_Type.featOkTst && ((SCRLymphNodesReading_Type)jcasType).casFeat_Inference == null)
      jcasType.jcas.throwFeatMissing("Inference", "org.ohnlp.medkat.scr.types.SCRLymphNodesReading");
    jcasType.ll_cas.ll_setIntValue(addr, ((SCRLymphNodesReading_Type)jcasType).casFeatCode_Inference, v);}    
  }

    