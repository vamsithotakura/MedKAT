

/* First created by JCasGen Wed Aug 27 00:14:05 EDT 2008 */
package org.ohnlp.medkat.scr.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.jcas.cas.TOP;


/** primary tumor object
 * Updated by JCasGen Wed Mar 11 15:34:12 EDT 2009
 * XML source: C:/eclipse/MedKATp/src/org/ohnlp/medkat/scr/types/SCRTypeSystem.xml
 * @generated */
public class SCRDisease extends Annotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(SCRDisease.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected SCRDisease() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public SCRDisease(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public SCRDisease(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public SCRDisease(JCas jcas, int begin, int end) {
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
  //* Feature: TumorSpecimens

  /** getter for TumorSpecimens - gets collection of tumor spesimen objects
   * @generated */
  public FSArray getTumorSpecimens() {
    if (SCRDisease_Type.featOkTst && ((SCRDisease_Type)jcasType).casFeat_TumorSpecimens == null)
      jcasType.jcas.throwFeatMissing("TumorSpecimens", "org.ohnlp.medkat.scr.types.SCRDisease");
    return (FSArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((SCRDisease_Type)jcasType).casFeatCode_TumorSpecimens)));}
    
  /** setter for TumorSpecimens - sets collection of tumor spesimen objects 
   * @generated */
  public void setTumorSpecimens(FSArray v) {
    if (SCRDisease_Type.featOkTst && ((SCRDisease_Type)jcasType).casFeat_TumorSpecimens == null)
      jcasType.jcas.throwFeatMissing("TumorSpecimens", "org.ohnlp.medkat.scr.types.SCRDisease");
    jcasType.ll_cas.ll_setRefValue(addr, ((SCRDisease_Type)jcasType).casFeatCode_TumorSpecimens, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for TumorSpecimens - gets an indexed value - collection of tumor spesimen objects
   * @generated */
  public TOP getTumorSpecimens(int i) {
    if (SCRDisease_Type.featOkTst && ((SCRDisease_Type)jcasType).casFeat_TumorSpecimens == null)
      jcasType.jcas.throwFeatMissing("TumorSpecimens", "org.ohnlp.medkat.scr.types.SCRDisease");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((SCRDisease_Type)jcasType).casFeatCode_TumorSpecimens), i);
    return (TOP)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((SCRDisease_Type)jcasType).casFeatCode_TumorSpecimens), i)));}

  /** indexed setter for TumorSpecimens - sets an indexed value - collection of tumor spesimen objects
   * @generated */
  public void setTumorSpecimens(int i, TOP v) { 
    if (SCRDisease_Type.featOkTst && ((SCRDisease_Type)jcasType).casFeat_TumorSpecimens == null)
      jcasType.jcas.throwFeatMissing("TumorSpecimens", "org.ohnlp.medkat.scr.types.SCRDisease");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((SCRDisease_Type)jcasType).casFeatCode_TumorSpecimens), i);
    jcasType.ll_cas.ll_setRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((SCRDisease_Type)jcasType).casFeatCode_TumorSpecimens), i, jcasType.ll_cas.ll_getFSRef(v));}
   
    
  //*--------------*
  //* Feature: LymphNodes

  /** getter for LymphNodes - gets collection of lymph nodes objects
   * @generated */
  public FSArray getLymphNodes() {
    if (SCRDisease_Type.featOkTst && ((SCRDisease_Type)jcasType).casFeat_LymphNodes == null)
      jcasType.jcas.throwFeatMissing("LymphNodes", "org.ohnlp.medkat.scr.types.SCRDisease");
    return (FSArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((SCRDisease_Type)jcasType).casFeatCode_LymphNodes)));}
    
  /** setter for LymphNodes - sets collection of lymph nodes objects 
   * @generated */
  public void setLymphNodes(FSArray v) {
    if (SCRDisease_Type.featOkTst && ((SCRDisease_Type)jcasType).casFeat_LymphNodes == null)
      jcasType.jcas.throwFeatMissing("LymphNodes", "org.ohnlp.medkat.scr.types.SCRDisease");
    jcasType.ll_cas.ll_setRefValue(addr, ((SCRDisease_Type)jcasType).casFeatCode_LymphNodes, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for LymphNodes - gets an indexed value - collection of lymph nodes objects
   * @generated */
  public TOP getLymphNodes(int i) {
    if (SCRDisease_Type.featOkTst && ((SCRDisease_Type)jcasType).casFeat_LymphNodes == null)
      jcasType.jcas.throwFeatMissing("LymphNodes", "org.ohnlp.medkat.scr.types.SCRDisease");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((SCRDisease_Type)jcasType).casFeatCode_LymphNodes), i);
    return (TOP)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((SCRDisease_Type)jcasType).casFeatCode_LymphNodes), i)));}

  /** indexed setter for LymphNodes - sets an indexed value - collection of lymph nodes objects
   * @generated */
  public void setLymphNodes(int i, TOP v) { 
    if (SCRDisease_Type.featOkTst && ((SCRDisease_Type)jcasType).casFeat_LymphNodes == null)
      jcasType.jcas.throwFeatMissing("LymphNodes", "org.ohnlp.medkat.scr.types.SCRDisease");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((SCRDisease_Type)jcasType).casFeatCode_LymphNodes), i);
    jcasType.ll_cas.ll_setRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((SCRDisease_Type)jcasType).casFeatCode_LymphNodes), i, jcasType.ll_cas.ll_getFSRef(v));}
   
    
  //*--------------*
  //* Feature: Stages

  /** getter for Stages - gets collection of stage objects
   * @generated */
  public FSArray getStages() {
    if (SCRDisease_Type.featOkTst && ((SCRDisease_Type)jcasType).casFeat_Stages == null)
      jcasType.jcas.throwFeatMissing("Stages", "org.ohnlp.medkat.scr.types.SCRDisease");
    return (FSArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((SCRDisease_Type)jcasType).casFeatCode_Stages)));}
    
  /** setter for Stages - sets collection of stage objects 
   * @generated */
  public void setStages(FSArray v) {
    if (SCRDisease_Type.featOkTst && ((SCRDisease_Type)jcasType).casFeat_Stages == null)
      jcasType.jcas.throwFeatMissing("Stages", "org.ohnlp.medkat.scr.types.SCRDisease");
    jcasType.ll_cas.ll_setRefValue(addr, ((SCRDisease_Type)jcasType).casFeatCode_Stages, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for Stages - gets an indexed value - collection of stage objects
   * @generated */
  public TOP getStages(int i) {
    if (SCRDisease_Type.featOkTst && ((SCRDisease_Type)jcasType).casFeat_Stages == null)
      jcasType.jcas.throwFeatMissing("Stages", "org.ohnlp.medkat.scr.types.SCRDisease");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((SCRDisease_Type)jcasType).casFeatCode_Stages), i);
    return (TOP)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((SCRDisease_Type)jcasType).casFeatCode_Stages), i)));}

  /** indexed setter for Stages - sets an indexed value - collection of stage objects
   * @generated */
  public void setStages(int i, TOP v) { 
    if (SCRDisease_Type.featOkTst && ((SCRDisease_Type)jcasType).casFeat_Stages == null)
      jcasType.jcas.throwFeatMissing("Stages", "org.ohnlp.medkat.scr.types.SCRDisease");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((SCRDisease_Type)jcasType).casFeatCode_Stages), i);
    jcasType.ll_cas.ll_setRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((SCRDisease_Type)jcasType).casFeatCode_Stages), i, jcasType.ll_cas.ll_getFSRef(v));}
   
    
  //*--------------*
  //* Feature: GrossDescription

  /** getter for GrossDescription - gets gross description object
   * @generated */
  public Annotation getGrossDescription() {
    if (SCRDisease_Type.featOkTst && ((SCRDisease_Type)jcasType).casFeat_GrossDescription == null)
      jcasType.jcas.throwFeatMissing("GrossDescription", "org.ohnlp.medkat.scr.types.SCRDisease");
    return (Annotation)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((SCRDisease_Type)jcasType).casFeatCode_GrossDescription)));}
    
  /** setter for GrossDescription - sets gross description object 
   * @generated */
  public void setGrossDescription(Annotation v) {
    if (SCRDisease_Type.featOkTst && ((SCRDisease_Type)jcasType).casFeat_GrossDescription == null)
      jcasType.jcas.throwFeatMissing("GrossDescription", "org.ohnlp.medkat.scr.types.SCRDisease");
    jcasType.ll_cas.ll_setRefValue(addr, ((SCRDisease_Type)jcasType).casFeatCode_GrossDescription, jcasType.ll_cas.ll_getFSRef(v));}    
  }

    