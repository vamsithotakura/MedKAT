

/* First created by JCasGen Wed Jun 11 12:10:51 EDT 2008 */
package org.ohnlp.medkat.taes.coreferencer;

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
public class CorefAnnotation extends Annotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(CorefAnnotation.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected CorefAnnotation() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public CorefAnnotation(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public CorefAnnotation(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public CorefAnnotation(JCas jcas, int begin, int end) {
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
  //* Feature: subsectionNumber

  /** getter for subsectionNumber - gets 
   * @generated */
  public int getSubsectionNumber() {
    if (CorefAnnotation_Type.featOkTst && ((CorefAnnotation_Type)jcasType).casFeat_subsectionNumber == null)
      jcasType.jcas.throwFeatMissing("subsectionNumber", "org.ohnlp.medkat.taes.coreferencer.CorefAnnotation");
    return jcasType.ll_cas.ll_getIntValue(addr, ((CorefAnnotation_Type)jcasType).casFeatCode_subsectionNumber);}
    
  /** setter for subsectionNumber - sets  
   * @generated */
  public void setSubsectionNumber(int v) {
    if (CorefAnnotation_Type.featOkTst && ((CorefAnnotation_Type)jcasType).casFeat_subsectionNumber == null)
      jcasType.jcas.throwFeatMissing("subsectionNumber", "org.ohnlp.medkat.taes.coreferencer.CorefAnnotation");
    jcasType.ll_cas.ll_setIntValue(addr, ((CorefAnnotation_Type)jcasType).casFeatCode_subsectionNumber, v);}    
   
    
  //*--------------*
  //* Feature: elements

  /** getter for elements - gets 
   * @generated */
  public FSArray getElements() {
    if (CorefAnnotation_Type.featOkTst && ((CorefAnnotation_Type)jcasType).casFeat_elements == null)
      jcasType.jcas.throwFeatMissing("elements", "org.ohnlp.medkat.taes.coreferencer.CorefAnnotation");
    return (FSArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((CorefAnnotation_Type)jcasType).casFeatCode_elements)));}
    
  /** setter for elements - sets  
   * @generated */
  public void setElements(FSArray v) {
    if (CorefAnnotation_Type.featOkTst && ((CorefAnnotation_Type)jcasType).casFeat_elements == null)
      jcasType.jcas.throwFeatMissing("elements", "org.ohnlp.medkat.taes.coreferencer.CorefAnnotation");
    jcasType.ll_cas.ll_setRefValue(addr, ((CorefAnnotation_Type)jcasType).casFeatCode_elements, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for elements - gets an indexed value - 
   * @generated */
  public TOP getElements(int i) {
    if (CorefAnnotation_Type.featOkTst && ((CorefAnnotation_Type)jcasType).casFeat_elements == null)
      jcasType.jcas.throwFeatMissing("elements", "org.ohnlp.medkat.taes.coreferencer.CorefAnnotation");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((CorefAnnotation_Type)jcasType).casFeatCode_elements), i);
    return (TOP)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((CorefAnnotation_Type)jcasType).casFeatCode_elements), i)));}

  /** indexed setter for elements - sets an indexed value - 
   * @generated */
  public void setElements(int i, TOP v) { 
    if (CorefAnnotation_Type.featOkTst && ((CorefAnnotation_Type)jcasType).casFeat_elements == null)
      jcasType.jcas.throwFeatMissing("elements", "org.ohnlp.medkat.taes.coreferencer.CorefAnnotation");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((CorefAnnotation_Type)jcasType).casFeatCode_elements), i);
    jcasType.ll_cas.ll_setRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((CorefAnnotation_Type)jcasType).casFeatCode_elements), i, jcasType.ll_cas.ll_getFSRef(v));}
  }

    