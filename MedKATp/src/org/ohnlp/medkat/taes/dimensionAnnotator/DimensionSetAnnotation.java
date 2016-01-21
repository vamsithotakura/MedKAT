

/* First created by JCasGen Wed Jun 11 12:10:52 EDT 2008 */
package org.ohnlp.medkat.taes.dimensionAnnotator;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.jcas.cas.TOP;


/** 
 * Updated by JCasGen Mon Mar 23 14:01:47 EDT 2009
 * XML source: C:/ohnlp/MedKATp/descriptors/analysis_engine/aggregate/MedKATp.xml
 * @generated */
public class DimensionSetAnnotation extends Annotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(DimensionSetAnnotation.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected DimensionSetAnnotation() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public DimensionSetAnnotation(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public DimensionSetAnnotation(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public DimensionSetAnnotation(JCas jcas, int begin, int end) {
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
  //* Feature: dimensions

  /** getter for dimensions - gets 
   * @generated */
  public FSArray getDimensions() {
    if (DimensionSetAnnotation_Type.featOkTst && ((DimensionSetAnnotation_Type)jcasType).casFeat_dimensions == null)
      jcasType.jcas.throwFeatMissing("dimensions", "org.ohnlp.medkat.taes.dimensionAnnotator.DimensionSetAnnotation");
    return (FSArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((DimensionSetAnnotation_Type)jcasType).casFeatCode_dimensions)));}
    
  /** setter for dimensions - sets  
   * @generated */
  public void setDimensions(FSArray v) {
    if (DimensionSetAnnotation_Type.featOkTst && ((DimensionSetAnnotation_Type)jcasType).casFeat_dimensions == null)
      jcasType.jcas.throwFeatMissing("dimensions", "org.ohnlp.medkat.taes.dimensionAnnotator.DimensionSetAnnotation");
    jcasType.ll_cas.ll_setRefValue(addr, ((DimensionSetAnnotation_Type)jcasType).casFeatCode_dimensions, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for dimensions - gets an indexed value - 
   * @generated */
  public TOP getDimensions(int i) {
    if (DimensionSetAnnotation_Type.featOkTst && ((DimensionSetAnnotation_Type)jcasType).casFeat_dimensions == null)
      jcasType.jcas.throwFeatMissing("dimensions", "org.ohnlp.medkat.taes.dimensionAnnotator.DimensionSetAnnotation");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((DimensionSetAnnotation_Type)jcasType).casFeatCode_dimensions), i);
    return (TOP)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((DimensionSetAnnotation_Type)jcasType).casFeatCode_dimensions), i)));}

  /** indexed setter for dimensions - sets an indexed value - 
   * @generated */
  public void setDimensions(int i, TOP v) { 
    if (DimensionSetAnnotation_Type.featOkTst && ((DimensionSetAnnotation_Type)jcasType).casFeat_dimensions == null)
      jcasType.jcas.throwFeatMissing("dimensions", "org.ohnlp.medkat.taes.dimensionAnnotator.DimensionSetAnnotation");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((DimensionSetAnnotation_Type)jcasType).casFeatCode_dimensions), i);
    jcasType.ll_cas.ll_setRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((DimensionSetAnnotation_Type)jcasType).casFeatCode_dimensions), i, jcasType.ll_cas.ll_getFSRef(v));}
  }

    