

/* First created by JCasGen Wed Jun 11 12:10:51 EDT 2008 */
package org.ohnlp.medkat.scr.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.jcas.cas.TOP;


/** Annotation that contains text fragments relevant to a specific concept
 * Updated by JCasGen Mon Mar 23 14:01:46 EDT 2009
 * XML source: C:/ohnlp/MedKATp/descriptors/analysis_engine/aggregate/MedKATp.xml
 * @generated */
public class SCRSpannedAnnotation extends Annotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(SCRSpannedAnnotation.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected SCRSpannedAnnotation() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public SCRSpannedAnnotation(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public SCRSpannedAnnotation(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public SCRSpannedAnnotation(JCas jcas, int begin, int end) {
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
  //* Feature: Fragments

  /** getter for Fragments - gets Continuous text fragment
   * @generated */
  public FSArray getFragments() {
    if (SCRSpannedAnnotation_Type.featOkTst && ((SCRSpannedAnnotation_Type)jcasType).casFeat_Fragments == null)
      jcasType.jcas.throwFeatMissing("Fragments", "org.ohnlp.medkat.scr.types.SCRSpannedAnnotation");
    return (FSArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((SCRSpannedAnnotation_Type)jcasType).casFeatCode_Fragments)));}
    
  /** setter for Fragments - sets Continuous text fragment 
   * @generated */
  public void setFragments(FSArray v) {
    if (SCRSpannedAnnotation_Type.featOkTst && ((SCRSpannedAnnotation_Type)jcasType).casFeat_Fragments == null)
      jcasType.jcas.throwFeatMissing("Fragments", "org.ohnlp.medkat.scr.types.SCRSpannedAnnotation");
    jcasType.ll_cas.ll_setRefValue(addr, ((SCRSpannedAnnotation_Type)jcasType).casFeatCode_Fragments, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for Fragments - gets an indexed value - Continuous text fragment
   * @generated */
  public TOP getFragments(int i) {
    if (SCRSpannedAnnotation_Type.featOkTst && ((SCRSpannedAnnotation_Type)jcasType).casFeat_Fragments == null)
      jcasType.jcas.throwFeatMissing("Fragments", "org.ohnlp.medkat.scr.types.SCRSpannedAnnotation");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((SCRSpannedAnnotation_Type)jcasType).casFeatCode_Fragments), i);
    return (TOP)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((SCRSpannedAnnotation_Type)jcasType).casFeatCode_Fragments), i)));}

  /** indexed setter for Fragments - sets an indexed value - Continuous text fragment
   * @generated */
  public void setFragments(int i, TOP v) { 
    if (SCRSpannedAnnotation_Type.featOkTst && ((SCRSpannedAnnotation_Type)jcasType).casFeat_Fragments == null)
      jcasType.jcas.throwFeatMissing("Fragments", "org.ohnlp.medkat.scr.types.SCRSpannedAnnotation");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((SCRSpannedAnnotation_Type)jcasType).casFeatCode_Fragments), i);
    jcasType.ll_cas.ll_setRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((SCRSpannedAnnotation_Type)jcasType).casFeatCode_Fragments), i, jcasType.ll_cas.ll_getFSRef(v));}
  }

    