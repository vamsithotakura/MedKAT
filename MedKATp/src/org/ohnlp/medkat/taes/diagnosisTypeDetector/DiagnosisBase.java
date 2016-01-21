

/* First created by JCasGen Wed Jun 11 12:10:52 EDT 2008 */
package org.ohnlp.medkat.taes.diagnosisTypeDetector;

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
public class DiagnosisBase extends Annotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(DiagnosisBase.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected DiagnosisBase() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public DiagnosisBase(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public DiagnosisBase(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public DiagnosisBase(JCas jcas, int begin, int end) {
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
  //* Feature: term

  /** getter for term - gets 
   * @generated */
  public Annotation getTerm() {
    if (DiagnosisBase_Type.featOkTst && ((DiagnosisBase_Type)jcasType).casFeat_term == null)
      jcasType.jcas.throwFeatMissing("term", "org.ohnlp.medkat.taes.diagnosisTypeDetector.DiagnosisBase");
    return (Annotation)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((DiagnosisBase_Type)jcasType).casFeatCode_term)));}
    
  /** setter for term - sets  
   * @generated */
  public void setTerm(Annotation v) {
    if (DiagnosisBase_Type.featOkTst && ((DiagnosisBase_Type)jcasType).casFeat_term == null)
      jcasType.jcas.throwFeatMissing("term", "org.ohnlp.medkat.taes.diagnosisTypeDetector.DiagnosisBase");
    jcasType.ll_cas.ll_setRefValue(addr, ((DiagnosisBase_Type)jcasType).casFeatCode_term, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: sites

  /** getter for sites - gets 
   * @generated */
  public FSArray getSites() {
    if (DiagnosisBase_Type.featOkTst && ((DiagnosisBase_Type)jcasType).casFeat_sites == null)
      jcasType.jcas.throwFeatMissing("sites", "org.ohnlp.medkat.taes.diagnosisTypeDetector.DiagnosisBase");
    return (FSArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((DiagnosisBase_Type)jcasType).casFeatCode_sites)));}
    
  /** setter for sites - sets  
   * @generated */
  public void setSites(FSArray v) {
    if (DiagnosisBase_Type.featOkTst && ((DiagnosisBase_Type)jcasType).casFeat_sites == null)
      jcasType.jcas.throwFeatMissing("sites", "org.ohnlp.medkat.taes.diagnosisTypeDetector.DiagnosisBase");
    jcasType.ll_cas.ll_setRefValue(addr, ((DiagnosisBase_Type)jcasType).casFeatCode_sites, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for sites - gets an indexed value - 
   * @generated */
  public TOP getSites(int i) {
    if (DiagnosisBase_Type.featOkTst && ((DiagnosisBase_Type)jcasType).casFeat_sites == null)
      jcasType.jcas.throwFeatMissing("sites", "org.ohnlp.medkat.taes.diagnosisTypeDetector.DiagnosisBase");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((DiagnosisBase_Type)jcasType).casFeatCode_sites), i);
    return (TOP)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((DiagnosisBase_Type)jcasType).casFeatCode_sites), i)));}

  /** indexed setter for sites - sets an indexed value - 
   * @generated */
  public void setSites(int i, TOP v) { 
    if (DiagnosisBase_Type.featOkTst && ((DiagnosisBase_Type)jcasType).casFeat_sites == null)
      jcasType.jcas.throwFeatMissing("sites", "org.ohnlp.medkat.taes.diagnosisTypeDetector.DiagnosisBase");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((DiagnosisBase_Type)jcasType).casFeatCode_sites), i);
    jcasType.ll_cas.ll_setRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((DiagnosisBase_Type)jcasType).casFeatCode_sites), i, jcasType.ll_cas.ll_getFSRef(v));}
  }

    