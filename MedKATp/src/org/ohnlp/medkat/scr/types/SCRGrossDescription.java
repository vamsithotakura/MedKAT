

/* First created by JCasGen Wed Jun 11 12:10:52 EDT 2008 */
package org.ohnlp.medkat.scr.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.cas.TOP;


/** gross description object
 * Updated by JCasGen Mon Mar 23 14:01:47 EDT 2009
 * XML source: C:/ohnlp/MedKATp/descriptors/analysis_engine/aggregate/MedKATp.xml
 * @generated */
public class SCRGrossDescription extends SCRGenericReading {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(SCRGrossDescription.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected SCRGrossDescription() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public SCRGrossDescription(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public SCRGrossDescription(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public SCRGrossDescription(JCas jcas, int begin, int end) {
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
  //* Feature: Parts

  /** getter for Parts - gets gross description parts
   * @generated */
  public FSArray getParts() {
    if (SCRGrossDescription_Type.featOkTst && ((SCRGrossDescription_Type)jcasType).casFeat_Parts == null)
      jcasType.jcas.throwFeatMissing("Parts", "org.ohnlp.medkat.scr.types.SCRGrossDescription");
    return (FSArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((SCRGrossDescription_Type)jcasType).casFeatCode_Parts)));}
    
  /** setter for Parts - sets gross description parts 
   * @generated */
  public void setParts(FSArray v) {
    if (SCRGrossDescription_Type.featOkTst && ((SCRGrossDescription_Type)jcasType).casFeat_Parts == null)
      jcasType.jcas.throwFeatMissing("Parts", "org.ohnlp.medkat.scr.types.SCRGrossDescription");
    jcasType.ll_cas.ll_setRefValue(addr, ((SCRGrossDescription_Type)jcasType).casFeatCode_Parts, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for Parts - gets an indexed value - gross description parts
   * @generated */
  public TOP getParts(int i) {
    if (SCRGrossDescription_Type.featOkTst && ((SCRGrossDescription_Type)jcasType).casFeat_Parts == null)
      jcasType.jcas.throwFeatMissing("Parts", "org.ohnlp.medkat.scr.types.SCRGrossDescription");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((SCRGrossDescription_Type)jcasType).casFeatCode_Parts), i);
    return (TOP)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((SCRGrossDescription_Type)jcasType).casFeatCode_Parts), i)));}

  /** indexed setter for Parts - sets an indexed value - gross description parts
   * @generated */
  public void setParts(int i, TOP v) { 
    if (SCRGrossDescription_Type.featOkTst && ((SCRGrossDescription_Type)jcasType).casFeat_Parts == null)
      jcasType.jcas.throwFeatMissing("Parts", "org.ohnlp.medkat.scr.types.SCRGrossDescription");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((SCRGrossDescription_Type)jcasType).casFeatCode_Parts), i);
    jcasType.ll_cas.ll_setRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((SCRGrossDescription_Type)jcasType).casFeatCode_Parts), i, jcasType.ll_cas.ll_getFSRef(v));}
  }

    