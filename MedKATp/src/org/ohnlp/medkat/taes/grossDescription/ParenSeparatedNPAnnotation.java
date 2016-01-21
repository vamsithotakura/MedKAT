

/* First created by JCasGen Wed Jun 11 12:10:52 EDT 2008 */
package org.ohnlp.medkat.taes.grossDescription;

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
public class ParenSeparatedNPAnnotation extends Annotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(ParenSeparatedNPAnnotation.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected ParenSeparatedNPAnnotation() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public ParenSeparatedNPAnnotation(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public ParenSeparatedNPAnnotation(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public ParenSeparatedNPAnnotation(JCas jcas, int begin, int end) {
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
  //* Feature: insideParen

  /** getter for insideParen - gets 
   * @generated */
  public FSArray getInsideParen() {
    if (ParenSeparatedNPAnnotation_Type.featOkTst && ((ParenSeparatedNPAnnotation_Type)jcasType).casFeat_insideParen == null)
      jcasType.jcas.throwFeatMissing("insideParen", "org.ohnlp.medkat.taes.grossDescription.ParenSeparatedNPAnnotation");
    return (FSArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((ParenSeparatedNPAnnotation_Type)jcasType).casFeatCode_insideParen)));}
    
  /** setter for insideParen - sets  
   * @generated */
  public void setInsideParen(FSArray v) {
    if (ParenSeparatedNPAnnotation_Type.featOkTst && ((ParenSeparatedNPAnnotation_Type)jcasType).casFeat_insideParen == null)
      jcasType.jcas.throwFeatMissing("insideParen", "org.ohnlp.medkat.taes.grossDescription.ParenSeparatedNPAnnotation");
    jcasType.ll_cas.ll_setRefValue(addr, ((ParenSeparatedNPAnnotation_Type)jcasType).casFeatCode_insideParen, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for insideParen - gets an indexed value - 
   * @generated */
  public TOP getInsideParen(int i) {
    if (ParenSeparatedNPAnnotation_Type.featOkTst && ((ParenSeparatedNPAnnotation_Type)jcasType).casFeat_insideParen == null)
      jcasType.jcas.throwFeatMissing("insideParen", "org.ohnlp.medkat.taes.grossDescription.ParenSeparatedNPAnnotation");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((ParenSeparatedNPAnnotation_Type)jcasType).casFeatCode_insideParen), i);
    return (TOP)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((ParenSeparatedNPAnnotation_Type)jcasType).casFeatCode_insideParen), i)));}

  /** indexed setter for insideParen - sets an indexed value - 
   * @generated */
  public void setInsideParen(int i, TOP v) { 
    if (ParenSeparatedNPAnnotation_Type.featOkTst && ((ParenSeparatedNPAnnotation_Type)jcasType).casFeat_insideParen == null)
      jcasType.jcas.throwFeatMissing("insideParen", "org.ohnlp.medkat.taes.grossDescription.ParenSeparatedNPAnnotation");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((ParenSeparatedNPAnnotation_Type)jcasType).casFeatCode_insideParen), i);
    jcasType.ll_cas.ll_setRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((ParenSeparatedNPAnnotation_Type)jcasType).casFeatCode_insideParen), i, jcasType.ll_cas.ll_getFSRef(v));}
   
    
  //*--------------*
  //* Feature: leftOfParen

  /** getter for leftOfParen - gets 
   * @generated */
  public Annotation getLeftOfParen() {
    if (ParenSeparatedNPAnnotation_Type.featOkTst && ((ParenSeparatedNPAnnotation_Type)jcasType).casFeat_leftOfParen == null)
      jcasType.jcas.throwFeatMissing("leftOfParen", "org.ohnlp.medkat.taes.grossDescription.ParenSeparatedNPAnnotation");
    return (Annotation)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((ParenSeparatedNPAnnotation_Type)jcasType).casFeatCode_leftOfParen)));}
    
  /** setter for leftOfParen - sets  
   * @generated */
  public void setLeftOfParen(Annotation v) {
    if (ParenSeparatedNPAnnotation_Type.featOkTst && ((ParenSeparatedNPAnnotation_Type)jcasType).casFeat_leftOfParen == null)
      jcasType.jcas.throwFeatMissing("leftOfParen", "org.ohnlp.medkat.taes.grossDescription.ParenSeparatedNPAnnotation");
    jcasType.ll_cas.ll_setRefValue(addr, ((ParenSeparatedNPAnnotation_Type)jcasType).casFeatCode_leftOfParen, jcasType.ll_cas.ll_getFSRef(v));}    
  }

    