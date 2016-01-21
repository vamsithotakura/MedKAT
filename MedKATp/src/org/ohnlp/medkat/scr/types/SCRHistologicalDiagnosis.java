

/* First created by JCasGen Wed Jun 11 12:10:51 EDT 2008 */
package org.ohnlp.medkat.scr.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.cas.TOP;


/** SCR HistologicalDiagnosis type
 * Updated by JCasGen Mon Mar 23 14:01:47 EDT 2009
 * XML source: C:/ohnlp/MedKATp/descriptors/analysis_engine/aggregate/MedKATp.xml
 * @generated */
public class SCRHistologicalDiagnosis extends SCRNamedEntityBase {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(SCRHistologicalDiagnosis.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected SCRHistologicalDiagnosis() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public SCRHistologicalDiagnosis(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public SCRHistologicalDiagnosis(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public SCRHistologicalDiagnosis(JCas jcas, int begin, int end) {
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
  //* Feature: Coreferences

  /** getter for Coreferences - gets array of coreference objects that contain this diagnosis
   * @generated */
  public FSArray getCoreferences() {
    if (SCRHistologicalDiagnosis_Type.featOkTst && ((SCRHistologicalDiagnosis_Type)jcasType).casFeat_Coreferences == null)
      jcasType.jcas.throwFeatMissing("Coreferences", "org.ohnlp.medkat.scr.types.SCRHistologicalDiagnosis");
    return (FSArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((SCRHistologicalDiagnosis_Type)jcasType).casFeatCode_Coreferences)));}
    
  /** setter for Coreferences - sets array of coreference objects that contain this diagnosis 
   * @generated */
  public void setCoreferences(FSArray v) {
    if (SCRHistologicalDiagnosis_Type.featOkTst && ((SCRHistologicalDiagnosis_Type)jcasType).casFeat_Coreferences == null)
      jcasType.jcas.throwFeatMissing("Coreferences", "org.ohnlp.medkat.scr.types.SCRHistologicalDiagnosis");
    jcasType.ll_cas.ll_setRefValue(addr, ((SCRHistologicalDiagnosis_Type)jcasType).casFeatCode_Coreferences, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for Coreferences - gets an indexed value - array of coreference objects that contain this diagnosis
   * @generated */
  public TOP getCoreferences(int i) {
    if (SCRHistologicalDiagnosis_Type.featOkTst && ((SCRHistologicalDiagnosis_Type)jcasType).casFeat_Coreferences == null)
      jcasType.jcas.throwFeatMissing("Coreferences", "org.ohnlp.medkat.scr.types.SCRHistologicalDiagnosis");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((SCRHistologicalDiagnosis_Type)jcasType).casFeatCode_Coreferences), i);
    return (TOP)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((SCRHistologicalDiagnosis_Type)jcasType).casFeatCode_Coreferences), i)));}

  /** indexed setter for Coreferences - sets an indexed value - array of coreference objects that contain this diagnosis
   * @generated */
  public void setCoreferences(int i, TOP v) { 
    if (SCRHistologicalDiagnosis_Type.featOkTst && ((SCRHistologicalDiagnosis_Type)jcasType).casFeat_Coreferences == null)
      jcasType.jcas.throwFeatMissing("Coreferences", "org.ohnlp.medkat.scr.types.SCRHistologicalDiagnosis");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((SCRHistologicalDiagnosis_Type)jcasType).casFeatCode_Coreferences), i);
    jcasType.ll_cas.ll_setRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((SCRHistologicalDiagnosis_Type)jcasType).casFeatCode_Coreferences), i, jcasType.ll_cas.ll_getFSRef(v));}
  }

    