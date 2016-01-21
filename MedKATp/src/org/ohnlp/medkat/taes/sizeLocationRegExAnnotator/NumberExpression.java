

/* First created by JCasGen Wed Jun 11 12:10:52 EDT 2008 */
package org.ohnlp.medkat.taes.sizeLocationRegExAnnotator;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Mon Mar 23 14:01:46 EDT 2009
 * XML source: C:/ohnlp/MedKATp/descriptors/analysis_engine/aggregate/MedKATp.xml
 * @generated */
public class NumberExpression extends Annotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(NumberExpression.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected NumberExpression() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public NumberExpression(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public NumberExpression(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public NumberExpression(JCas jcas, int begin, int end) {
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
  //* Feature: numeric

  /** getter for numeric - gets 
   * @generated */
  public String getNumeric() {
    if (NumberExpression_Type.featOkTst && ((NumberExpression_Type)jcasType).casFeat_numeric == null)
      jcasType.jcas.throwFeatMissing("numeric", "org.ohnlp.medkat.taes.sizeLocationRegExAnnotator.NumberExpression");
    return jcasType.ll_cas.ll_getStringValue(addr, ((NumberExpression_Type)jcasType).casFeatCode_numeric);}
    
  /** setter for numeric - sets  
   * @generated */
  public void setNumeric(String v) {
    if (NumberExpression_Type.featOkTst && ((NumberExpression_Type)jcasType).casFeat_numeric == null)
      jcasType.jcas.throwFeatMissing("numeric", "org.ohnlp.medkat.taes.sizeLocationRegExAnnotator.NumberExpression");
    jcasType.ll_cas.ll_setStringValue(addr, ((NumberExpression_Type)jcasType).casFeatCode_numeric, v);}    
  }

    