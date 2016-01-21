

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
public class RangeAnnotation extends Annotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(RangeAnnotation.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected RangeAnnotation() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public RangeAnnotation(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public RangeAnnotation(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public RangeAnnotation(JCas jcas, int begin, int end) {
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
  //* Feature: low

  /** getter for low - gets 
   * @generated */
  public DimensionSetAnnotation getLow() {
    if (RangeAnnotation_Type.featOkTst && ((RangeAnnotation_Type)jcasType).casFeat_low == null)
      jcasType.jcas.throwFeatMissing("low", "org.ohnlp.medkat.taes.dimensionAnnotator.RangeAnnotation");
    return (DimensionSetAnnotation)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((RangeAnnotation_Type)jcasType).casFeatCode_low)));}
    
  /** setter for low - sets  
   * @generated */
  public void setLow(DimensionSetAnnotation v) {
    if (RangeAnnotation_Type.featOkTst && ((RangeAnnotation_Type)jcasType).casFeat_low == null)
      jcasType.jcas.throwFeatMissing("low", "org.ohnlp.medkat.taes.dimensionAnnotator.RangeAnnotation");
    jcasType.ll_cas.ll_setRefValue(addr, ((RangeAnnotation_Type)jcasType).casFeatCode_low, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: high

  /** getter for high - gets 
   * @generated */
  public DimensionSetAnnotation getHigh() {
    if (RangeAnnotation_Type.featOkTst && ((RangeAnnotation_Type)jcasType).casFeat_high == null)
      jcasType.jcas.throwFeatMissing("high", "org.ohnlp.medkat.taes.dimensionAnnotator.RangeAnnotation");
    return (DimensionSetAnnotation)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((RangeAnnotation_Type)jcasType).casFeatCode_high)));}
    
  /** setter for high - sets  
   * @generated */
  public void setHigh(DimensionSetAnnotation v) {
    if (RangeAnnotation_Type.featOkTst && ((RangeAnnotation_Type)jcasType).casFeat_high == null)
      jcasType.jcas.throwFeatMissing("high", "org.ohnlp.medkat.taes.dimensionAnnotator.RangeAnnotation");
    jcasType.ll_cas.ll_setRefValue(addr, ((RangeAnnotation_Type)jcasType).casFeatCode_high, jcasType.ll_cas.ll_getFSRef(v));}    
  }

    