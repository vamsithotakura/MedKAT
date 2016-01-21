

/* First created by JCasGen Wed Jun 11 12:10:51 EDT 2008 */
package org.ohnlp.medkat.scr.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;



/** base class for SCR named entity types
 * Updated by JCasGen Mon Mar 23 14:01:47 EDT 2009
 * XML source: C:/ohnlp/MedKATp/descriptors/analysis_engine/aggregate/MedKATp.xml
 * @generated */
public class SCRNamedEntityBase extends SCRSpannedAnnotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(SCRNamedEntityBase.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected SCRNamedEntityBase() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public SCRNamedEntityBase(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public SCRNamedEntityBase(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public SCRNamedEntityBase(JCas jcas, int begin, int end) {
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
    if (SCRNamedEntityBase_Type.featOkTst && ((SCRNamedEntityBase_Type)jcasType).casFeat_Terminology == null)
      jcasType.jcas.throwFeatMissing("Terminology", "org.ohnlp.medkat.scr.types.SCRNamedEntityBase");
    return jcasType.ll_cas.ll_getStringValue(addr, ((SCRNamedEntityBase_Type)jcasType).casFeatCode_Terminology);}
    
  /** setter for Terminology - sets Terminology identifier 
   * @generated */
  public void setTerminology(String v) {
    if (SCRNamedEntityBase_Type.featOkTst && ((SCRNamedEntityBase_Type)jcasType).casFeat_Terminology == null)
      jcasType.jcas.throwFeatMissing("Terminology", "org.ohnlp.medkat.scr.types.SCRNamedEntityBase");
    jcasType.ll_cas.ll_setStringValue(addr, ((SCRNamedEntityBase_Type)jcasType).casFeatCode_Terminology, v);}    
   
    
  //*--------------*
  //* Feature: Code

  /** getter for Code - gets Code from terminology's vocabulary
   * @generated */
  public String getCode() {
    if (SCRNamedEntityBase_Type.featOkTst && ((SCRNamedEntityBase_Type)jcasType).casFeat_Code == null)
      jcasType.jcas.throwFeatMissing("Code", "org.ohnlp.medkat.scr.types.SCRNamedEntityBase");
    return jcasType.ll_cas.ll_getStringValue(addr, ((SCRNamedEntityBase_Type)jcasType).casFeatCode_Code);}
    
  /** setter for Code - sets Code from terminology's vocabulary 
   * @generated */
  public void setCode(String v) {
    if (SCRNamedEntityBase_Type.featOkTst && ((SCRNamedEntityBase_Type)jcasType).casFeat_Code == null)
      jcasType.jcas.throwFeatMissing("Code", "org.ohnlp.medkat.scr.types.SCRNamedEntityBase");
    jcasType.ll_cas.ll_setStringValue(addr, ((SCRNamedEntityBase_Type)jcasType).casFeatCode_Code, v);}    
   
    
  //*--------------*
  //* Feature: Negation

  /** getter for Negation - gets boolean flag whether the site has been negated in context
   * @generated */
  public int getNegation() {
    if (SCRNamedEntityBase_Type.featOkTst && ((SCRNamedEntityBase_Type)jcasType).casFeat_Negation == null)
      jcasType.jcas.throwFeatMissing("Negation", "org.ohnlp.medkat.scr.types.SCRNamedEntityBase");
    return jcasType.ll_cas.ll_getIntValue(addr, ((SCRNamedEntityBase_Type)jcasType).casFeatCode_Negation);}
    
  /** setter for Negation - sets boolean flag whether the site has been negated in context 
   * @generated */
  public void setNegation(int v) {
    if (SCRNamedEntityBase_Type.featOkTst && ((SCRNamedEntityBase_Type)jcasType).casFeat_Negation == null)
      jcasType.jcas.throwFeatMissing("Negation", "org.ohnlp.medkat.scr.types.SCRNamedEntityBase");
    jcasType.ll_cas.ll_setIntValue(addr, ((SCRNamedEntityBase_Type)jcasType).casFeatCode_Negation, v);}    
   
    
  //*--------------*
  //* Feature: Inference

  /** getter for Inference - gets Boolean flag whether the anatomical site properties has been inferred from context
   * @generated */
  public int getInference() {
    if (SCRNamedEntityBase_Type.featOkTst && ((SCRNamedEntityBase_Type)jcasType).casFeat_Inference == null)
      jcasType.jcas.throwFeatMissing("Inference", "org.ohnlp.medkat.scr.types.SCRNamedEntityBase");
    return jcasType.ll_cas.ll_getIntValue(addr, ((SCRNamedEntityBase_Type)jcasType).casFeatCode_Inference);}
    
  /** setter for Inference - sets Boolean flag whether the anatomical site properties has been inferred from context 
   * @generated */
  public void setInference(int v) {
    if (SCRNamedEntityBase_Type.featOkTst && ((SCRNamedEntityBase_Type)jcasType).casFeat_Inference == null)
      jcasType.jcas.throwFeatMissing("Inference", "org.ohnlp.medkat.scr.types.SCRNamedEntityBase");
    jcasType.ll_cas.ll_setIntValue(addr, ((SCRNamedEntityBase_Type)jcasType).casFeatCode_Inference, v);}    
  }

    