

/* First created by JCasGen Wed Jun 11 12:10:50 EDT 2008 */
package org.ohnlp.medkat.taes.sectionFinder;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Mon Mar 23 14:01:46 EDT 2009
 * XML source: C:/ohnlp/MedKATp/descriptors/analysis_engine/aggregate/MedKATp.xml
 * @generated */
public class SectionAnnotation extends Annotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(SectionAnnotation.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected SectionAnnotation() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public SectionAnnotation(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public SectionAnnotation(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public SectionAnnotation(JCas jcas, int begin, int end) {
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
  //* Feature: headerText

  /** getter for headerText - gets 
   * @generated */
  public String getHeaderText() {
    if (SectionAnnotation_Type.featOkTst && ((SectionAnnotation_Type)jcasType).casFeat_headerText == null)
      jcasType.jcas.throwFeatMissing("headerText", "org.ohnlp.medkat.taes.sectionFinder.SectionAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((SectionAnnotation_Type)jcasType).casFeatCode_headerText);}
    
  /** setter for headerText - sets  
   * @generated */
  public void setHeaderText(String v) {
    if (SectionAnnotation_Type.featOkTst && ((SectionAnnotation_Type)jcasType).casFeat_headerText == null)
      jcasType.jcas.throwFeatMissing("headerText", "org.ohnlp.medkat.taes.sectionFinder.SectionAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((SectionAnnotation_Type)jcasType).casFeatCode_headerText, v);}    
  }

    