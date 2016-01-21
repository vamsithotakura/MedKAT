

/* First created by JCasGen Wed Aug 27 00:14:06 EDT 2008 */
package org.ohnlp.medkat.scr.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.jcas.cas.TOP;


/** primary tumor object
 * Updated by JCasGen Wed Mar 11 15:34:13 EDT 2009
 * XML source: C:/eclipse/MedKATp/src/org/ohnlp/medkat/scr/types/SCRTypeSystem.xml
 * @generated */
public class SCRTumorSpecimen extends Annotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(SCRTumorSpecimen.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected SCRTumorSpecimen() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public SCRTumorSpecimen(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public SCRTumorSpecimen(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public SCRTumorSpecimen(JCas jcas, int begin, int end) {
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
  //* Feature: PrimaryTumors

  /** getter for PrimaryTumors - gets collection of primary tumor objects
   * @generated */
  public FSArray getPrimaryTumors() {
    if (SCRTumorSpecimen_Type.featOkTst && ((SCRTumorSpecimen_Type)jcasType).casFeat_PrimaryTumors == null)
      jcasType.jcas.throwFeatMissing("PrimaryTumors", "org.ohnlp.medkat.scr.types.SCRTumorSpecimen");
    return (FSArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((SCRTumorSpecimen_Type)jcasType).casFeatCode_PrimaryTumors)));}
    
  /** setter for PrimaryTumors - sets collection of primary tumor objects 
   * @generated */
  public void setPrimaryTumors(FSArray v) {
    if (SCRTumorSpecimen_Type.featOkTst && ((SCRTumorSpecimen_Type)jcasType).casFeat_PrimaryTumors == null)
      jcasType.jcas.throwFeatMissing("PrimaryTumors", "org.ohnlp.medkat.scr.types.SCRTumorSpecimen");
    jcasType.ll_cas.ll_setRefValue(addr, ((SCRTumorSpecimen_Type)jcasType).casFeatCode_PrimaryTumors, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for PrimaryTumors - gets an indexed value - collection of primary tumor objects
   * @generated */
  public TOP getPrimaryTumors(int i) {
    if (SCRTumorSpecimen_Type.featOkTst && ((SCRTumorSpecimen_Type)jcasType).casFeat_PrimaryTumors == null)
      jcasType.jcas.throwFeatMissing("PrimaryTumors", "org.ohnlp.medkat.scr.types.SCRTumorSpecimen");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((SCRTumorSpecimen_Type)jcasType).casFeatCode_PrimaryTumors), i);
    return (TOP)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((SCRTumorSpecimen_Type)jcasType).casFeatCode_PrimaryTumors), i)));}

  /** indexed setter for PrimaryTumors - sets an indexed value - collection of primary tumor objects
   * @generated */
  public void setPrimaryTumors(int i, TOP v) { 
    if (SCRTumorSpecimen_Type.featOkTst && ((SCRTumorSpecimen_Type)jcasType).casFeat_PrimaryTumors == null)
      jcasType.jcas.throwFeatMissing("PrimaryTumors", "org.ohnlp.medkat.scr.types.SCRTumorSpecimen");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((SCRTumorSpecimen_Type)jcasType).casFeatCode_PrimaryTumors), i);
    jcasType.ll_cas.ll_setRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((SCRTumorSpecimen_Type)jcasType).casFeatCode_PrimaryTumors), i, jcasType.ll_cas.ll_getFSRef(v));}
   
    
  //*--------------*
  //* Feature: MetstaticTumors

  /** getter for MetstaticTumors - gets collection of metstatic tumor objects
   * @generated */
  public FSArray getMetstaticTumors() {
    if (SCRTumorSpecimen_Type.featOkTst && ((SCRTumorSpecimen_Type)jcasType).casFeat_MetstaticTumors == null)
      jcasType.jcas.throwFeatMissing("MetstaticTumors", "org.ohnlp.medkat.scr.types.SCRTumorSpecimen");
    return (FSArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((SCRTumorSpecimen_Type)jcasType).casFeatCode_MetstaticTumors)));}
    
  /** setter for MetstaticTumors - sets collection of metstatic tumor objects 
   * @generated */
  public void setMetstaticTumors(FSArray v) {
    if (SCRTumorSpecimen_Type.featOkTst && ((SCRTumorSpecimen_Type)jcasType).casFeat_MetstaticTumors == null)
      jcasType.jcas.throwFeatMissing("MetstaticTumors", "org.ohnlp.medkat.scr.types.SCRTumorSpecimen");
    jcasType.ll_cas.ll_setRefValue(addr, ((SCRTumorSpecimen_Type)jcasType).casFeatCode_MetstaticTumors, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for MetstaticTumors - gets an indexed value - collection of metstatic tumor objects
   * @generated */
  public TOP getMetstaticTumors(int i) {
    if (SCRTumorSpecimen_Type.featOkTst && ((SCRTumorSpecimen_Type)jcasType).casFeat_MetstaticTumors == null)
      jcasType.jcas.throwFeatMissing("MetstaticTumors", "org.ohnlp.medkat.scr.types.SCRTumorSpecimen");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((SCRTumorSpecimen_Type)jcasType).casFeatCode_MetstaticTumors), i);
    return (TOP)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((SCRTumorSpecimen_Type)jcasType).casFeatCode_MetstaticTumors), i)));}

  /** indexed setter for MetstaticTumors - sets an indexed value - collection of metstatic tumor objects
   * @generated */
  public void setMetstaticTumors(int i, TOP v) { 
    if (SCRTumorSpecimen_Type.featOkTst && ((SCRTumorSpecimen_Type)jcasType).casFeat_MetstaticTumors == null)
      jcasType.jcas.throwFeatMissing("MetstaticTumors", "org.ohnlp.medkat.scr.types.SCRTumorSpecimen");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((SCRTumorSpecimen_Type)jcasType).casFeatCode_MetstaticTumors), i);
    jcasType.ll_cas.ll_setRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((SCRTumorSpecimen_Type)jcasType).casFeatCode_MetstaticTumors), i, jcasType.ll_cas.ll_getFSRef(v));}
   
    
  //*--------------*
  //* Feature: Procedures

  /** getter for Procedures - gets collection of procedure objects
   * @generated */
  public FSArray getProcedures() {
    if (SCRTumorSpecimen_Type.featOkTst && ((SCRTumorSpecimen_Type)jcasType).casFeat_Procedures == null)
      jcasType.jcas.throwFeatMissing("Procedures", "org.ohnlp.medkat.scr.types.SCRTumorSpecimen");
    return (FSArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((SCRTumorSpecimen_Type)jcasType).casFeatCode_Procedures)));}
    
  /** setter for Procedures - sets collection of procedure objects 
   * @generated */
  public void setProcedures(FSArray v) {
    if (SCRTumorSpecimen_Type.featOkTst && ((SCRTumorSpecimen_Type)jcasType).casFeat_Procedures == null)
      jcasType.jcas.throwFeatMissing("Procedures", "org.ohnlp.medkat.scr.types.SCRTumorSpecimen");
    jcasType.ll_cas.ll_setRefValue(addr, ((SCRTumorSpecimen_Type)jcasType).casFeatCode_Procedures, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for Procedures - gets an indexed value - collection of procedure objects
   * @generated */
  public TOP getProcedures(int i) {
    if (SCRTumorSpecimen_Type.featOkTst && ((SCRTumorSpecimen_Type)jcasType).casFeat_Procedures == null)
      jcasType.jcas.throwFeatMissing("Procedures", "org.ohnlp.medkat.scr.types.SCRTumorSpecimen");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((SCRTumorSpecimen_Type)jcasType).casFeatCode_Procedures), i);
    return (TOP)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((SCRTumorSpecimen_Type)jcasType).casFeatCode_Procedures), i)));}

  /** indexed setter for Procedures - sets an indexed value - collection of procedure objects
   * @generated */
  public void setProcedures(int i, TOP v) { 
    if (SCRTumorSpecimen_Type.featOkTst && ((SCRTumorSpecimen_Type)jcasType).casFeat_Procedures == null)
      jcasType.jcas.throwFeatMissing("Procedures", "org.ohnlp.medkat.scr.types.SCRTumorSpecimen");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((SCRTumorSpecimen_Type)jcasType).casFeatCode_Procedures), i);
    jcasType.ll_cas.ll_setRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((SCRTumorSpecimen_Type)jcasType).casFeatCode_Procedures), i, jcasType.ll_cas.ll_getFSRef(v));}
  }

    