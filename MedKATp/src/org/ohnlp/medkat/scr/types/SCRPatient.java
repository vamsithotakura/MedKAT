

/* First created by JCasGen Wed Aug 27 00:14:06 EDT 2008 */
package org.ohnlp.medkat.scr.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.jcas.cas.TOP;


/** patient object
 * Updated by JCasGen Wed Mar 11 15:34:13 EDT 2009
 * XML source: C:/eclipse/MedKATp/src/org/ohnlp/medkat/scr/types/SCRTypeSystem.xml
 * @generated */
public class SCRPatient extends Annotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(SCRPatient.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected SCRPatient() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public SCRPatient(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public SCRPatient(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public SCRPatient(JCas jcas, int begin, int end) {
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
  //* Feature: Observations

  /** getter for Observations - gets observation objects
   * @generated */
  public FSArray getObservations() {
    if (SCRPatient_Type.featOkTst && ((SCRPatient_Type)jcasType).casFeat_Observations == null)
      jcasType.jcas.throwFeatMissing("Observations", "org.ohnlp.medkat.scr.types.SCRPatient");
    return (FSArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((SCRPatient_Type)jcasType).casFeatCode_Observations)));}
    
  /** setter for Observations - sets observation objects 
   * @generated */
  public void setObservations(FSArray v) {
    if (SCRPatient_Type.featOkTst && ((SCRPatient_Type)jcasType).casFeat_Observations == null)
      jcasType.jcas.throwFeatMissing("Observations", "org.ohnlp.medkat.scr.types.SCRPatient");
    jcasType.ll_cas.ll_setRefValue(addr, ((SCRPatient_Type)jcasType).casFeatCode_Observations, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for Observations - gets an indexed value - observation objects
   * @generated */
  public TOP getObservations(int i) {
    if (SCRPatient_Type.featOkTst && ((SCRPatient_Type)jcasType).casFeat_Observations == null)
      jcasType.jcas.throwFeatMissing("Observations", "org.ohnlp.medkat.scr.types.SCRPatient");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((SCRPatient_Type)jcasType).casFeatCode_Observations), i);
    return (TOP)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((SCRPatient_Type)jcasType).casFeatCode_Observations), i)));}

  /** indexed setter for Observations - sets an indexed value - observation objects
   * @generated */
  public void setObservations(int i, TOP v) { 
    if (SCRPatient_Type.featOkTst && ((SCRPatient_Type)jcasType).casFeat_Observations == null)
      jcasType.jcas.throwFeatMissing("Observations", "org.ohnlp.medkat.scr.types.SCRPatient");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((SCRPatient_Type)jcasType).casFeatCode_Observations), i);
    jcasType.ll_cas.ll_setRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((SCRPatient_Type)jcasType).casFeatCode_Observations), i, jcasType.ll_cas.ll_getFSRef(v));}
  }

    