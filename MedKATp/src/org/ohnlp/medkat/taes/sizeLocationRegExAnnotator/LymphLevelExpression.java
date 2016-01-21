

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
public class LymphLevelExpression extends Annotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(LymphLevelExpression.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected LymphLevelExpression() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public LymphLevelExpression(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public LymphLevelExpression(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public LymphLevelExpression(JCas jcas, int begin, int end) {
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
  //* Feature: status

  /** getter for status - gets annotates positive (1) or negative (0) lymph nodes
   * @generated */
  public int getStatus() {
    if (LymphLevelExpression_Type.featOkTst && ((LymphLevelExpression_Type)jcasType).casFeat_status == null)
      jcasType.jcas.throwFeatMissing("status", "org.ohnlp.medkat.taes.sizeLocationRegExAnnotator.LymphLevelExpression");
    return jcasType.ll_cas.ll_getIntValue(addr, ((LymphLevelExpression_Type)jcasType).casFeatCode_status);}
    
  /** setter for status - sets annotates positive (1) or negative (0) lymph nodes 
   * @generated */
  public void setStatus(int v) {
    if (LymphLevelExpression_Type.featOkTst && ((LymphLevelExpression_Type)jcasType).casFeat_status == null)
      jcasType.jcas.throwFeatMissing("status", "org.ohnlp.medkat.taes.sizeLocationRegExAnnotator.LymphLevelExpression");
    jcasType.ll_cas.ll_setIntValue(addr, ((LymphLevelExpression_Type)jcasType).casFeatCode_status, v);}    
   
    
  //*--------------*
  //* Feature: nodeExpression

  /** getter for nodeExpression - gets 
   * @generated */
  public String getNodeExpression() {
    if (LymphLevelExpression_Type.featOkTst && ((LymphLevelExpression_Type)jcasType).casFeat_nodeExpression == null)
      jcasType.jcas.throwFeatMissing("nodeExpression", "org.ohnlp.medkat.taes.sizeLocationRegExAnnotator.LymphLevelExpression");
    return jcasType.ll_cas.ll_getStringValue(addr, ((LymphLevelExpression_Type)jcasType).casFeatCode_nodeExpression);}
    
  /** setter for nodeExpression - sets  
   * @generated */
  public void setNodeExpression(String v) {
    if (LymphLevelExpression_Type.featOkTst && ((LymphLevelExpression_Type)jcasType).casFeat_nodeExpression == null)
      jcasType.jcas.throwFeatMissing("nodeExpression", "org.ohnlp.medkat.taes.sizeLocationRegExAnnotator.LymphLevelExpression");
    jcasType.ll_cas.ll_setStringValue(addr, ((LymphLevelExpression_Type)jcasType).casFeatCode_nodeExpression, v);}    
   
    
  //*--------------*
  //* Feature: numPositive

  /** getter for numPositive - gets 
   * @generated */
  public int getNumPositive() {
    if (LymphLevelExpression_Type.featOkTst && ((LymphLevelExpression_Type)jcasType).casFeat_numPositive == null)
      jcasType.jcas.throwFeatMissing("numPositive", "org.ohnlp.medkat.taes.sizeLocationRegExAnnotator.LymphLevelExpression");
    return jcasType.ll_cas.ll_getIntValue(addr, ((LymphLevelExpression_Type)jcasType).casFeatCode_numPositive);}
    
  /** setter for numPositive - sets  
   * @generated */
  public void setNumPositive(int v) {
    if (LymphLevelExpression_Type.featOkTst && ((LymphLevelExpression_Type)jcasType).casFeat_numPositive == null)
      jcasType.jcas.throwFeatMissing("numPositive", "org.ohnlp.medkat.taes.sizeLocationRegExAnnotator.LymphLevelExpression");
    jcasType.ll_cas.ll_setIntValue(addr, ((LymphLevelExpression_Type)jcasType).casFeatCode_numPositive, v);}    
   
    
  //*--------------*
  //* Feature: numTotal

  /** getter for numTotal - gets 
   * @generated */
  public int getNumTotal() {
    if (LymphLevelExpression_Type.featOkTst && ((LymphLevelExpression_Type)jcasType).casFeat_numTotal == null)
      jcasType.jcas.throwFeatMissing("numTotal", "org.ohnlp.medkat.taes.sizeLocationRegExAnnotator.LymphLevelExpression");
    return jcasType.ll_cas.ll_getIntValue(addr, ((LymphLevelExpression_Type)jcasType).casFeatCode_numTotal);}
    
  /** setter for numTotal - sets  
   * @generated */
  public void setNumTotal(int v) {
    if (LymphLevelExpression_Type.featOkTst && ((LymphLevelExpression_Type)jcasType).casFeat_numTotal == null)
      jcasType.jcas.throwFeatMissing("numTotal", "org.ohnlp.medkat.taes.sizeLocationRegExAnnotator.LymphLevelExpression");
    jcasType.ll_cas.ll_setIntValue(addr, ((LymphLevelExpression_Type)jcasType).casFeatCode_numTotal, v);}    
  }

    