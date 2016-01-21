

/* First created by JCasGen Wed Jun 11 12:10:49 EDT 2008 */
package org.ohnlp.medkat.taes.subsectionDetector;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.jcas.cas.TOP;


/** 
 * Updated by JCasGen Mon Mar 23 14:01:46 EDT 2009
 * XML source: C:/ohnlp/MedKATp/descriptors/analysis_engine/aggregate/MedKATp.xml
 * @generated */
public class SubHeading extends Annotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(SubHeading.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected SubHeading() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public SubHeading(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public SubHeading(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public SubHeading(JCas jcas, int begin, int end) {
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
  //* Feature: subSectionNumber

  /** getter for subSectionNumber - gets 
   * @generated */
  public int getSubSectionNumber() {
    if (SubHeading_Type.featOkTst && ((SubHeading_Type)jcasType).casFeat_subSectionNumber == null)
      jcasType.jcas.throwFeatMissing("subSectionNumber", "org.ohnlp.medkat.taes.subsectionDetector.SubHeading");
    return jcasType.ll_cas.ll_getIntValue(addr, ((SubHeading_Type)jcasType).casFeatCode_subSectionNumber);}
    
  /** setter for subSectionNumber - sets  
   * @generated */
  public void setSubSectionNumber(int v) {
    if (SubHeading_Type.featOkTst && ((SubHeading_Type)jcasType).casFeat_subSectionNumber == null)
      jcasType.jcas.throwFeatMissing("subSectionNumber", "org.ohnlp.medkat.taes.subsectionDetector.SubHeading");
    jcasType.ll_cas.ll_setIntValue(addr, ((SubHeading_Type)jcasType).casFeatCode_subSectionNumber, v);}    
   
    
  //*--------------*
  //* Feature: subSubsections

  /** getter for subSubsections - gets 
   * @generated */
  public FSArray getSubSubsections() {
    if (SubHeading_Type.featOkTst && ((SubHeading_Type)jcasType).casFeat_subSubsections == null)
      jcasType.jcas.throwFeatMissing("subSubsections", "org.ohnlp.medkat.taes.subsectionDetector.SubHeading");
    return (FSArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((SubHeading_Type)jcasType).casFeatCode_subSubsections)));}
    
  /** setter for subSubsections - sets  
   * @generated */
  public void setSubSubsections(FSArray v) {
    if (SubHeading_Type.featOkTst && ((SubHeading_Type)jcasType).casFeat_subSubsections == null)
      jcasType.jcas.throwFeatMissing("subSubsections", "org.ohnlp.medkat.taes.subsectionDetector.SubHeading");
    jcasType.ll_cas.ll_setRefValue(addr, ((SubHeading_Type)jcasType).casFeatCode_subSubsections, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for subSubsections - gets an indexed value - 
   * @generated */
  public TOP getSubSubsections(int i) {
    if (SubHeading_Type.featOkTst && ((SubHeading_Type)jcasType).casFeat_subSubsections == null)
      jcasType.jcas.throwFeatMissing("subSubsections", "org.ohnlp.medkat.taes.subsectionDetector.SubHeading");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((SubHeading_Type)jcasType).casFeatCode_subSubsections), i);
    return (TOP)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((SubHeading_Type)jcasType).casFeatCode_subSubsections), i)));}

  /** indexed setter for subSubsections - sets an indexed value - 
   * @generated */
  public void setSubSubsections(int i, TOP v) { 
    if (SubHeading_Type.featOkTst && ((SubHeading_Type)jcasType).casFeat_subSubsections == null)
      jcasType.jcas.throwFeatMissing("subSubsections", "org.ohnlp.medkat.taes.subsectionDetector.SubHeading");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((SubHeading_Type)jcasType).casFeatCode_subSubsections), i);
    jcasType.ll_cas.ll_setRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((SubHeading_Type)jcasType).casFeatCode_subSubsections), i, jcasType.ll_cas.ll_getFSRef(v));}
   
    
  //*--------------*
  //* Feature: content

  /** getter for content - gets 
   * @generated */
  public String getContent() {
    if (SubHeading_Type.featOkTst && ((SubHeading_Type)jcasType).casFeat_content == null)
      jcasType.jcas.throwFeatMissing("content", "org.ohnlp.medkat.taes.subsectionDetector.SubHeading");
    return jcasType.ll_cas.ll_getStringValue(addr, ((SubHeading_Type)jcasType).casFeatCode_content);}
    
  /** setter for content - sets  
   * @generated */
  public void setContent(String v) {
    if (SubHeading_Type.featOkTst && ((SubHeading_Type)jcasType).casFeat_content == null)
      jcasType.jcas.throwFeatMissing("content", "org.ohnlp.medkat.taes.subsectionDetector.SubHeading");
    jcasType.ll_cas.ll_setStringValue(addr, ((SubHeading_Type)jcasType).casFeatCode_content, v);}    
   
    
  //*--------------*
  //* Feature: prefix

  /** getter for prefix - gets 
   * @generated */
  public String getPrefix() {
    if (SubHeading_Type.featOkTst && ((SubHeading_Type)jcasType).casFeat_prefix == null)
      jcasType.jcas.throwFeatMissing("prefix", "org.ohnlp.medkat.taes.subsectionDetector.SubHeading");
    return jcasType.ll_cas.ll_getStringValue(addr, ((SubHeading_Type)jcasType).casFeatCode_prefix);}
    
  /** setter for prefix - sets  
   * @generated */
  public void setPrefix(String v) {
    if (SubHeading_Type.featOkTst && ((SubHeading_Type)jcasType).casFeat_prefix == null)
      jcasType.jcas.throwFeatMissing("prefix", "org.ohnlp.medkat.taes.subsectionDetector.SubHeading");
    jcasType.ll_cas.ll_setStringValue(addr, ((SubHeading_Type)jcasType).casFeatCode_prefix, v);}    
   
    
  //*--------------*
  //* Feature: contentBegin

  /** getter for contentBegin - gets 
   * @generated */
  public int getContentBegin() {
    if (SubHeading_Type.featOkTst && ((SubHeading_Type)jcasType).casFeat_contentBegin == null)
      jcasType.jcas.throwFeatMissing("contentBegin", "org.ohnlp.medkat.taes.subsectionDetector.SubHeading");
    return jcasType.ll_cas.ll_getIntValue(addr, ((SubHeading_Type)jcasType).casFeatCode_contentBegin);}
    
  /** setter for contentBegin - sets  
   * @generated */
  public void setContentBegin(int v) {
    if (SubHeading_Type.featOkTst && ((SubHeading_Type)jcasType).casFeat_contentBegin == null)
      jcasType.jcas.throwFeatMissing("contentBegin", "org.ohnlp.medkat.taes.subsectionDetector.SubHeading");
    jcasType.ll_cas.ll_setIntValue(addr, ((SubHeading_Type)jcasType).casFeatCode_contentBegin, v);}    
  }

    