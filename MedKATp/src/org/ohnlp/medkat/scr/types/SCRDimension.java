

/* First created by JCasGen Wed Jun 11 12:10:52 EDT 2008 */
package org.ohnlp.medkat.scr.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** Size dimension object
 * Updated by JCasGen Mon Mar 23 14:01:47 EDT 2009
 * XML source: C:/ohnlp/MedKATp/descriptors/analysis_engine/aggregate/MedKATp.xml
 * @generated */
public class SCRDimension extends Annotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(SCRDimension.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected SCRDimension() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public SCRDimension(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public SCRDimension(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public SCRDimension(JCas jcas, int begin, int end) {
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
  //* Feature: Extent

  /** getter for Extent - gets extent of the dimension
   * @generated */
  public String getExtent() {
    if (SCRDimension_Type.featOkTst && ((SCRDimension_Type)jcasType).casFeat_Extent == null)
      jcasType.jcas.throwFeatMissing("Extent", "org.ohnlp.medkat.scr.types.SCRDimension");
    return jcasType.ll_cas.ll_getStringValue(addr, ((SCRDimension_Type)jcasType).casFeatCode_Extent);}
    
  /** setter for Extent - sets extent of the dimension 
   * @generated */
  public void setExtent(String v) {
    if (SCRDimension_Type.featOkTst && ((SCRDimension_Type)jcasType).casFeat_Extent == null)
      jcasType.jcas.throwFeatMissing("Extent", "org.ohnlp.medkat.scr.types.SCRDimension");
    jcasType.ll_cas.ll_setStringValue(addr, ((SCRDimension_Type)jcasType).casFeatCode_Extent, v);}    
   
    
  //*--------------*
  //* Feature: Unit

  /** getter for Unit - gets unit of the dimension
   * @generated */
  public String getUnit() {
    if (SCRDimension_Type.featOkTst && ((SCRDimension_Type)jcasType).casFeat_Unit == null)
      jcasType.jcas.throwFeatMissing("Unit", "org.ohnlp.medkat.scr.types.SCRDimension");
    return jcasType.ll_cas.ll_getStringValue(addr, ((SCRDimension_Type)jcasType).casFeatCode_Unit);}
    
  /** setter for Unit - sets unit of the dimension 
   * @generated */
  public void setUnit(String v) {
    if (SCRDimension_Type.featOkTst && ((SCRDimension_Type)jcasType).casFeat_Unit == null)
      jcasType.jcas.throwFeatMissing("Unit", "org.ohnlp.medkat.scr.types.SCRDimension");
    jcasType.ll_cas.ll_setStringValue(addr, ((SCRDimension_Type)jcasType).casFeatCode_Unit, v);}    
   
    
  //*--------------*
  //* Feature: Inference

  /** getter for Inference - gets Boolean flag whether the anatomical site properties has been infered from context
   * @generated */
  public int getInference() {
    if (SCRDimension_Type.featOkTst && ((SCRDimension_Type)jcasType).casFeat_Inference == null)
      jcasType.jcas.throwFeatMissing("Inference", "org.ohnlp.medkat.scr.types.SCRDimension");
    return jcasType.ll_cas.ll_getIntValue(addr, ((SCRDimension_Type)jcasType).casFeatCode_Inference);}
    
  /** setter for Inference - sets Boolean flag whether the anatomical site properties has been infered from context 
   * @generated */
  public void setInference(int v) {
    if (SCRDimension_Type.featOkTst && ((SCRDimension_Type)jcasType).casFeat_Inference == null)
      jcasType.jcas.throwFeatMissing("Inference", "org.ohnlp.medkat.scr.types.SCRDimension");
    jcasType.ll_cas.ll_setIntValue(addr, ((SCRDimension_Type)jcasType).casFeatCode_Inference, v);}    
  }

    