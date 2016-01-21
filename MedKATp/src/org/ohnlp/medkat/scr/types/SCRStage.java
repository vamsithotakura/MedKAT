

/* First created by JCasGen Wed Aug 27 00:14:06 EDT 2008 */
package org.ohnlp.medkat.scr.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** stage object
 * Updated by JCasGen Wed Mar 11 15:34:13 EDT 2009
 * XML source: C:/eclipse/MedKATp/src/org/ohnlp/medkat/scr/types/SCRTypeSystem.xml
 * @generated */
public class SCRStage extends Annotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(SCRStage.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected SCRStage() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public SCRStage(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public SCRStage(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public SCRStage(JCas jcas, int begin, int end) {
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
  //* Feature: StageType

  /** getter for StageType - gets type of stage
   * @generated */
  public String getStageType() {
    if (SCRStage_Type.featOkTst && ((SCRStage_Type)jcasType).casFeat_StageType == null)
      jcasType.jcas.throwFeatMissing("StageType", "org.ohnlp.medkat.scr.types.SCRStage");
    return jcasType.ll_cas.ll_getStringValue(addr, ((SCRStage_Type)jcasType).casFeatCode_StageType);}
    
  /** setter for StageType - sets type of stage 
   * @generated */
  public void setStageType(String v) {
    if (SCRStage_Type.featOkTst && ((SCRStage_Type)jcasType).casFeat_StageType == null)
      jcasType.jcas.throwFeatMissing("StageType", "org.ohnlp.medkat.scr.types.SCRStage");
    jcasType.ll_cas.ll_setStringValue(addr, ((SCRStage_Type)jcasType).casFeatCode_StageType, v);}    
   
    
  //*--------------*
  //* Feature: StageValue

  /** getter for StageValue - gets stage value
   * @generated */
  public String getStageValue() {
    if (SCRStage_Type.featOkTst && ((SCRStage_Type)jcasType).casFeat_StageValue == null)
      jcasType.jcas.throwFeatMissing("StageValue", "org.ohnlp.medkat.scr.types.SCRStage");
    return jcasType.ll_cas.ll_getStringValue(addr, ((SCRStage_Type)jcasType).casFeatCode_StageValue);}
    
  /** setter for StageValue - sets stage value 
   * @generated */
  public void setStageValue(String v) {
    if (SCRStage_Type.featOkTst && ((SCRStage_Type)jcasType).casFeat_StageValue == null)
      jcasType.jcas.throwFeatMissing("StageValue", "org.ohnlp.medkat.scr.types.SCRStage");
    jcasType.ll_cas.ll_setStringValue(addr, ((SCRStage_Type)jcasType).casFeatCode_StageValue, v);}    
   
    
  //*--------------*
  //* Feature: Identifier

  /** getter for Identifier - gets stage identifier
   * @generated */
  public String getIdentifier() {
    if (SCRStage_Type.featOkTst && ((SCRStage_Type)jcasType).casFeat_Identifier == null)
      jcasType.jcas.throwFeatMissing("Identifier", "org.ohnlp.medkat.scr.types.SCRStage");
    return jcasType.ll_cas.ll_getStringValue(addr, ((SCRStage_Type)jcasType).casFeatCode_Identifier);}
    
  /** setter for Identifier - sets stage identifier 
   * @generated */
  public void setIdentifier(String v) {
    if (SCRStage_Type.featOkTst && ((SCRStage_Type)jcasType).casFeat_Identifier == null)
      jcasType.jcas.throwFeatMissing("Identifier", "org.ohnlp.medkat.scr.types.SCRStage");
    jcasType.ll_cas.ll_setStringValue(addr, ((SCRStage_Type)jcasType).casFeatCode_Identifier, v);}    
   
    
  //*--------------*
  //* Feature: Inference

  /** getter for Inference - gets Boolean flag whether the grade value properties has been infered from context
   * @generated */
  public int getInference() {
    if (SCRStage_Type.featOkTst && ((SCRStage_Type)jcasType).casFeat_Inference == null)
      jcasType.jcas.throwFeatMissing("Inference", "org.ohnlp.medkat.scr.types.SCRStage");
    return jcasType.ll_cas.ll_getIntValue(addr, ((SCRStage_Type)jcasType).casFeatCode_Inference);}
    
  /** setter for Inference - sets Boolean flag whether the grade value properties has been infered from context 
   * @generated */
  public void setInference(int v) {
    if (SCRStage_Type.featOkTst && ((SCRStage_Type)jcasType).casFeat_Inference == null)
      jcasType.jcas.throwFeatMissing("Inference", "org.ohnlp.medkat.scr.types.SCRStage");
    jcasType.ll_cas.ll_setIntValue(addr, ((SCRStage_Type)jcasType).casFeatCode_Inference, v);}    
  }

    