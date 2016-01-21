

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
public class ExtentAnnotation extends Annotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(ExtentAnnotation.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected ExtentAnnotation() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public ExtentAnnotation(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public ExtentAnnotation(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public ExtentAnnotation(JCas jcas, int begin, int end) {
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
  //* Feature: equality

  /** getter for equality - gets 
   * @generated */
  public String getEquality() {
    if (ExtentAnnotation_Type.featOkTst && ((ExtentAnnotation_Type)jcasType).casFeat_equality == null)
      jcasType.jcas.throwFeatMissing("equality", "org.ohnlp.medkat.taes.dimensionAnnotator.ExtentAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((ExtentAnnotation_Type)jcasType).casFeatCode_equality);}
    
  /** setter for equality - sets  
   * @generated */
  public void setEquality(String v) {
    if (ExtentAnnotation_Type.featOkTst && ((ExtentAnnotation_Type)jcasType).casFeat_equality == null)
      jcasType.jcas.throwFeatMissing("equality", "org.ohnlp.medkat.taes.dimensionAnnotator.ExtentAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((ExtentAnnotation_Type)jcasType).casFeatCode_equality, v);}    
  }

    