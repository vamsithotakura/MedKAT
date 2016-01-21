

/* First created by JCasGen Wed Jun 11 12:10:52 EDT 2008 */
package org.ohnlp.medkat.taes.dimensionAnnotator;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Mon Mar 23 14:01:47 EDT 2009
 * XML source: C:/ohnlp/MedKATp/descriptors/analysis_engine/aggregate/MedKATp.xml
 * @generated */
public class DimensionAnnotation extends Annotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(DimensionAnnotation.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected DimensionAnnotation() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public DimensionAnnotation(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public DimensionAnnotation(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public DimensionAnnotation(JCas jcas, int begin, int end) {
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
  //* Feature: value

  /** getter for value - gets 
   * @generated */
  public ExtentAnnotation getValue() {
    if (DimensionAnnotation_Type.featOkTst && ((DimensionAnnotation_Type)jcasType).casFeat_value == null)
      jcasType.jcas.throwFeatMissing("value", "org.ohnlp.medkat.taes.dimensionAnnotator.DimensionAnnotation");
    return (ExtentAnnotation)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((DimensionAnnotation_Type)jcasType).casFeatCode_value)));}
    
  /** setter for value - sets  
   * @generated */
  public void setValue(ExtentAnnotation v) {
    if (DimensionAnnotation_Type.featOkTst && ((DimensionAnnotation_Type)jcasType).casFeat_value == null)
      jcasType.jcas.throwFeatMissing("value", "org.ohnlp.medkat.taes.dimensionAnnotator.DimensionAnnotation");
    jcasType.ll_cas.ll_setRefValue(addr, ((DimensionAnnotation_Type)jcasType).casFeatCode_value, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: unit

  /** getter for unit - gets 
   * @generated */
  public UnitAnnotation getUnit() {
    if (DimensionAnnotation_Type.featOkTst && ((DimensionAnnotation_Type)jcasType).casFeat_unit == null)
      jcasType.jcas.throwFeatMissing("unit", "org.ohnlp.medkat.taes.dimensionAnnotator.DimensionAnnotation");
    return (UnitAnnotation)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((DimensionAnnotation_Type)jcasType).casFeatCode_unit)));}
    
  /** setter for unit - sets  
   * @generated */
  public void setUnit(UnitAnnotation v) {
    if (DimensionAnnotation_Type.featOkTst && ((DimensionAnnotation_Type)jcasType).casFeat_unit == null)
      jcasType.jcas.throwFeatMissing("unit", "org.ohnlp.medkat.taes.dimensionAnnotator.DimensionAnnotation");
    jcasType.ll_cas.ll_setRefValue(addr, ((DimensionAnnotation_Type)jcasType).casFeatCode_unit, jcasType.ll_cas.ll_getFSRef(v));}    
  }

    