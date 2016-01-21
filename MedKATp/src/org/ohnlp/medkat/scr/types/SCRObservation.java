

/* First created by JCasGen Wed Aug 27 00:14:06 EDT 2008 */
package org.ohnlp.medkat.scr.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.jcas.cas.TOP;


/** observation object
 * Updated by JCasGen Wed Mar 11 15:34:13 EDT 2009
 * XML source: C:/eclipse/MedKATp/src/org/ohnlp/medkat/scr/types/SCRTypeSystem.xml
 * @generated */
public class SCRObservation extends Annotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(SCRObservation.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected SCRObservation() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public SCRObservation(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public SCRObservation(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public SCRObservation(JCas jcas, int begin, int end) {
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
  //* Feature: TumorSpecimen

  /** getter for TumorSpecimen - gets tumor specimen
   * @generated */
  public Annotation getTumorSpecimen() {
    if (SCRObservation_Type.featOkTst && ((SCRObservation_Type)jcasType).casFeat_TumorSpecimen == null)
      jcasType.jcas.throwFeatMissing("TumorSpecimen", "org.ohnlp.medkat.scr.types.SCRObservation");
    return (Annotation)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((SCRObservation_Type)jcasType).casFeatCode_TumorSpecimen)));}
    
  /** setter for TumorSpecimen - sets tumor specimen 
   * @generated */
  public void setTumorSpecimen(Annotation v) {
    if (SCRObservation_Type.featOkTst && ((SCRObservation_Type)jcasType).casFeat_TumorSpecimen == null)
      jcasType.jcas.throwFeatMissing("TumorSpecimen", "org.ohnlp.medkat.scr.types.SCRObservation");
    jcasType.ll_cas.ll_setRefValue(addr, ((SCRObservation_Type)jcasType).casFeatCode_TumorSpecimen, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: TissueBank

  /** getter for TissueBank - gets tissue bank
   * @generated */
  public Annotation getTissueBank() {
    if (SCRObservation_Type.featOkTst && ((SCRObservation_Type)jcasType).casFeat_TissueBank == null)
      jcasType.jcas.throwFeatMissing("TissueBank", "org.ohnlp.medkat.scr.types.SCRObservation");
    return (Annotation)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((SCRObservation_Type)jcasType).casFeatCode_TissueBank)));}
    
  /** setter for TissueBank - sets tissue bank 
   * @generated */
  public void setTissueBank(Annotation v) {
    if (SCRObservation_Type.featOkTst && ((SCRObservation_Type)jcasType).casFeat_TissueBank == null)
      jcasType.jcas.throwFeatMissing("TissueBank", "org.ohnlp.medkat.scr.types.SCRObservation");
    jcasType.ll_cas.ll_setRefValue(addr, ((SCRObservation_Type)jcasType).casFeatCode_TissueBank, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: DocumentType

  /** getter for DocumentType - gets document type
   * @generated */
  public Annotation getDocumentType() {
    if (SCRObservation_Type.featOkTst && ((SCRObservation_Type)jcasType).casFeat_DocumentType == null)
      jcasType.jcas.throwFeatMissing("DocumentType", "org.ohnlp.medkat.scr.types.SCRObservation");
    return (Annotation)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((SCRObservation_Type)jcasType).casFeatCode_DocumentType)));}
    
  /** setter for DocumentType - sets document type 
   * @generated */
  public void setDocumentType(Annotation v) {
    if (SCRObservation_Type.featOkTst && ((SCRObservation_Type)jcasType).casFeat_DocumentType == null)
      jcasType.jcas.throwFeatMissing("DocumentType", "org.ohnlp.medkat.scr.types.SCRObservation");
    jcasType.ll_cas.ll_setRefValue(addr, ((SCRObservation_Type)jcasType).casFeatCode_DocumentType, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: TumorBlocks

  /** getter for TumorBlocks - gets tumor blocks
   * @generated */
  public FSArray getTumorBlocks() {
    if (SCRObservation_Type.featOkTst && ((SCRObservation_Type)jcasType).casFeat_TumorBlocks == null)
      jcasType.jcas.throwFeatMissing("TumorBlocks", "org.ohnlp.medkat.scr.types.SCRObservation");
    return (FSArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((SCRObservation_Type)jcasType).casFeatCode_TumorBlocks)));}
    
  /** setter for TumorBlocks - sets tumor blocks 
   * @generated */
  public void setTumorBlocks(FSArray v) {
    if (SCRObservation_Type.featOkTst && ((SCRObservation_Type)jcasType).casFeat_TumorBlocks == null)
      jcasType.jcas.throwFeatMissing("TumorBlocks", "org.ohnlp.medkat.scr.types.SCRObservation");
    jcasType.ll_cas.ll_setRefValue(addr, ((SCRObservation_Type)jcasType).casFeatCode_TumorBlocks, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for TumorBlocks - gets an indexed value - tumor blocks
   * @generated */
  public TOP getTumorBlocks(int i) {
    if (SCRObservation_Type.featOkTst && ((SCRObservation_Type)jcasType).casFeat_TumorBlocks == null)
      jcasType.jcas.throwFeatMissing("TumorBlocks", "org.ohnlp.medkat.scr.types.SCRObservation");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((SCRObservation_Type)jcasType).casFeatCode_TumorBlocks), i);
    return (TOP)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((SCRObservation_Type)jcasType).casFeatCode_TumorBlocks), i)));}

  /** indexed setter for TumorBlocks - sets an indexed value - tumor blocks
   * @generated */
  public void setTumorBlocks(int i, TOP v) { 
    if (SCRObservation_Type.featOkTst && ((SCRObservation_Type)jcasType).casFeat_TumorBlocks == null)
      jcasType.jcas.throwFeatMissing("TumorBlocks", "org.ohnlp.medkat.scr.types.SCRObservation");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((SCRObservation_Type)jcasType).casFeatCode_TumorBlocks), i);
    jcasType.ll_cas.ll_setRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((SCRObservation_Type)jcasType).casFeatCode_TumorBlocks), i, jcasType.ll_cas.ll_getFSRef(v));}
  }

    