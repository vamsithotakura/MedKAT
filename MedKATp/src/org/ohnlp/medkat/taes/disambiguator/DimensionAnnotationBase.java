

/* First created by JCasGen Wed Jun 11 12:10:52 EDT 2008 */
package org.ohnlp.medkat.taes.disambiguator;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;


import org.apache.uima.jcas.tcas.Annotation;
import org.ohnlp.medkat.taes.dimensionAnnotator.RangeAnnotation;


/** 
 * Updated by JCasGen Mon Mar 23 14:01:47 EDT 2009
 * XML source: C:/ohnlp/MedKATp/descriptors/analysis_engine/aggregate/MedKATp.xml
 * @generated */
public class DimensionAnnotationBase extends Annotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(DimensionAnnotationBase.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected DimensionAnnotationBase() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public DimensionAnnotationBase(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public DimensionAnnotationBase(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public DimensionAnnotationBase(JCas jcas, int begin, int end) {
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
  //* Feature: range

  /** getter for range - gets 
   * @generated */
  public RangeAnnotation getRange() {
    if (DimensionAnnotationBase_Type.featOkTst && ((DimensionAnnotationBase_Type)jcasType).casFeat_range == null)
      jcasType.jcas.throwFeatMissing("range", "org.ohnlp.medkat.taes.disambiguator.DimensionAnnotationBase");
    return (RangeAnnotation)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((DimensionAnnotationBase_Type)jcasType).casFeatCode_range)));}
    
  /** setter for range - sets  
   * @generated */
  public void setRange(RangeAnnotation v) {
    if (DimensionAnnotationBase_Type.featOkTst && ((DimensionAnnotationBase_Type)jcasType).casFeat_range == null)
      jcasType.jcas.throwFeatMissing("range", "org.ohnlp.medkat.taes.disambiguator.DimensionAnnotationBase");
    jcasType.ll_cas.ll_setRefValue(addr, ((DimensionAnnotationBase_Type)jcasType).casFeatCode_range, jcasType.ll_cas.ll_getFSRef(v));}    
  }

    