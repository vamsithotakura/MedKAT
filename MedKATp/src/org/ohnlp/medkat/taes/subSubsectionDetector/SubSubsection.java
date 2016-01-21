

/* First created by JCasGen Wed Jun 11 12:10:49 EDT 2008 */
package org.ohnlp.medkat.taes.subSubsectionDetector;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Mon Mar 23 14:01:46 EDT 2009
 * XML source: C:/ohnlp/MedKATp/descriptors/analysis_engine/aggregate/MedKATp.xml
 * @generated */
public class SubSubsection extends Annotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(SubSubsection.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected SubSubsection() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public SubSubsection(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public SubSubsection(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public SubSubsection(JCas jcas, int begin, int end) {
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
  //* Feature: label

  /** getter for label - gets 
   * @generated */
  public String getLabel() {
    if (SubSubsection_Type.featOkTst && ((SubSubsection_Type)jcasType).casFeat_label == null)
      jcasType.jcas.throwFeatMissing("label", "org.ohnlp.medkat.taes.subSubsectionDetector.SubSubsection");
    return jcasType.ll_cas.ll_getStringValue(addr, ((SubSubsection_Type)jcasType).casFeatCode_label);}
    
  /** setter for label - sets  
   * @generated */
  public void setLabel(String v) {
    if (SubSubsection_Type.featOkTst && ((SubSubsection_Type)jcasType).casFeat_label == null)
      jcasType.jcas.throwFeatMissing("label", "org.ohnlp.medkat.taes.subSubsectionDetector.SubSubsection");
    jcasType.ll_cas.ll_setStringValue(addr, ((SubSubsection_Type)jcasType).casFeatCode_label, v);}    
   
    
  //*--------------*
  //* Feature: parent

  /** getter for parent - gets 
   * @generated */
  public Annotation getParent() {
    if (SubSubsection_Type.featOkTst && ((SubSubsection_Type)jcasType).casFeat_parent == null)
      jcasType.jcas.throwFeatMissing("parent", "org.ohnlp.medkat.taes.subSubsectionDetector.SubSubsection");
    return (Annotation)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((SubSubsection_Type)jcasType).casFeatCode_parent)));}
    
  /** setter for parent - sets  
   * @generated */
  public void setParent(Annotation v) {
    if (SubSubsection_Type.featOkTst && ((SubSubsection_Type)jcasType).casFeat_parent == null)
      jcasType.jcas.throwFeatMissing("parent", "org.ohnlp.medkat.taes.subSubsectionDetector.SubSubsection");
    jcasType.ll_cas.ll_setRefValue(addr, ((SubSubsection_Type)jcasType).casFeatCode_parent, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: concept

  /** getter for concept - gets A concept (e.g., from dictionary) that may be used to describe this item in further processing modules
   * @generated */
  public String getConcept() {
    if (SubSubsection_Type.featOkTst && ((SubSubsection_Type)jcasType).casFeat_concept == null)
      jcasType.jcas.throwFeatMissing("concept", "org.ohnlp.medkat.taes.subSubsectionDetector.SubSubsection");
    return jcasType.ll_cas.ll_getStringValue(addr, ((SubSubsection_Type)jcasType).casFeatCode_concept);}
    
  /** setter for concept - sets A concept (e.g., from dictionary) that may be used to describe this item in further processing modules 
   * @generated */
  public void setConcept(String v) {
    if (SubSubsection_Type.featOkTst && ((SubSubsection_Type)jcasType).casFeat_concept == null)
      jcasType.jcas.throwFeatMissing("concept", "org.ohnlp.medkat.taes.subSubsectionDetector.SubSubsection");
    jcasType.ll_cas.ll_setStringValue(addr, ((SubSubsection_Type)jcasType).casFeatCode_concept, v);}    
  }

    