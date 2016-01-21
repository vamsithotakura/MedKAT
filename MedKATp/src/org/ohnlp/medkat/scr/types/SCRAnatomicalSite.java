

/* First created by JCasGen Wed Jun 11 12:10:51 EDT 2008 */
package org.ohnlp.medkat.scr.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.cas.TOP;


/** SCR AnatomicalSite type
 * Updated by JCasGen Mon Mar 23 14:01:47 EDT 2009
 * XML source: C:/ohnlp/MedKATp/descriptors/analysis_engine/aggregate/MedKATp.xml
 * @generated */
public class SCRAnatomicalSite extends SCRNamedEntityBase {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(SCRAnatomicalSite.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected SCRAnatomicalSite() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public SCRAnatomicalSite(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public SCRAnatomicalSite(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public SCRAnatomicalSite(JCas jcas, int begin, int end) {
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
  //* Feature: Laterality

  /** getter for Laterality - gets Laterality of anatomical site
   * @generated */
  public String getLaterality() {
    if (SCRAnatomicalSite_Type.featOkTst && ((SCRAnatomicalSite_Type)jcasType).casFeat_Laterality == null)
      jcasType.jcas.throwFeatMissing("Laterality", "org.ohnlp.medkat.scr.types.SCRAnatomicalSite");
    return jcasType.ll_cas.ll_getStringValue(addr, ((SCRAnatomicalSite_Type)jcasType).casFeatCode_Laterality);}
    
  /** setter for Laterality - sets Laterality of anatomical site 
   * @generated */
  public void setLaterality(String v) {
    if (SCRAnatomicalSite_Type.featOkTst && ((SCRAnatomicalSite_Type)jcasType).casFeat_Laterality == null)
      jcasType.jcas.throwFeatMissing("Laterality", "org.ohnlp.medkat.scr.types.SCRAnatomicalSite");
    jcasType.ll_cas.ll_setStringValue(addr, ((SCRAnatomicalSite_Type)jcasType).casFeatCode_Laterality, v);}    
   
    
  //*--------------*
  //* Feature: Modifiers

  /** getter for Modifiers - gets modifiers for the anatomical site
   * @generated */
  public FSArray getModifiers() {
    if (SCRAnatomicalSite_Type.featOkTst && ((SCRAnatomicalSite_Type)jcasType).casFeat_Modifiers == null)
      jcasType.jcas.throwFeatMissing("Modifiers", "org.ohnlp.medkat.scr.types.SCRAnatomicalSite");
    return (FSArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((SCRAnatomicalSite_Type)jcasType).casFeatCode_Modifiers)));}
    
  /** setter for Modifiers - sets modifiers for the anatomical site 
   * @generated */
  public void setModifiers(FSArray v) {
    if (SCRAnatomicalSite_Type.featOkTst && ((SCRAnatomicalSite_Type)jcasType).casFeat_Modifiers == null)
      jcasType.jcas.throwFeatMissing("Modifiers", "org.ohnlp.medkat.scr.types.SCRAnatomicalSite");
    jcasType.ll_cas.ll_setRefValue(addr, ((SCRAnatomicalSite_Type)jcasType).casFeatCode_Modifiers, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for Modifiers - gets an indexed value - modifiers for the anatomical site
   * @generated */
  public TOP getModifiers(int i) {
    if (SCRAnatomicalSite_Type.featOkTst && ((SCRAnatomicalSite_Type)jcasType).casFeat_Modifiers == null)
      jcasType.jcas.throwFeatMissing("Modifiers", "org.ohnlp.medkat.scr.types.SCRAnatomicalSite");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((SCRAnatomicalSite_Type)jcasType).casFeatCode_Modifiers), i);
    return (TOP)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((SCRAnatomicalSite_Type)jcasType).casFeatCode_Modifiers), i)));}

  /** indexed setter for Modifiers - sets an indexed value - modifiers for the anatomical site
   * @generated */
  public void setModifiers(int i, TOP v) { 
    if (SCRAnatomicalSite_Type.featOkTst && ((SCRAnatomicalSite_Type)jcasType).casFeat_Modifiers == null)
      jcasType.jcas.throwFeatMissing("Modifiers", "org.ohnlp.medkat.scr.types.SCRAnatomicalSite");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((SCRAnatomicalSite_Type)jcasType).casFeatCode_Modifiers), i);
    jcasType.ll_cas.ll_setRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((SCRAnatomicalSite_Type)jcasType).casFeatCode_Modifiers), i, jcasType.ll_cas.ll_getFSRef(v));}
   
    
  //*--------------*
  //* Feature: InferredCode

  /** getter for InferredCode - gets Code that is assigned by NLP algorithms rather than dictionary matching
   * @generated */
  public String getInferredCode() {
    if (SCRAnatomicalSite_Type.featOkTst && ((SCRAnatomicalSite_Type)jcasType).casFeat_InferredCode == null)
      jcasType.jcas.throwFeatMissing("InferredCode", "org.ohnlp.medkat.scr.types.SCRAnatomicalSite");
    return jcasType.ll_cas.ll_getStringValue(addr, ((SCRAnatomicalSite_Type)jcasType).casFeatCode_InferredCode);}
    
  /** setter for InferredCode - sets Code that is assigned by NLP algorithms rather than dictionary matching 
   * @generated */
  public void setInferredCode(String v) {
    if (SCRAnatomicalSite_Type.featOkTst && ((SCRAnatomicalSite_Type)jcasType).casFeat_InferredCode == null)
      jcasType.jcas.throwFeatMissing("InferredCode", "org.ohnlp.medkat.scr.types.SCRAnatomicalSite");
    jcasType.ll_cas.ll_setStringValue(addr, ((SCRAnatomicalSite_Type)jcasType).casFeatCode_InferredCode, v);}    
   
    
  //*--------------*
  //* Feature: Coreferences

  /** getter for Coreferences - gets array of coreference objects that contain this site
   * @generated */
  public FSArray getCoreferences() {
    if (SCRAnatomicalSite_Type.featOkTst && ((SCRAnatomicalSite_Type)jcasType).casFeat_Coreferences == null)
      jcasType.jcas.throwFeatMissing("Coreferences", "org.ohnlp.medkat.scr.types.SCRAnatomicalSite");
    return (FSArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((SCRAnatomicalSite_Type)jcasType).casFeatCode_Coreferences)));}
    
  /** setter for Coreferences - sets array of coreference objects that contain this site 
   * @generated */
  public void setCoreferences(FSArray v) {
    if (SCRAnatomicalSite_Type.featOkTst && ((SCRAnatomicalSite_Type)jcasType).casFeat_Coreferences == null)
      jcasType.jcas.throwFeatMissing("Coreferences", "org.ohnlp.medkat.scr.types.SCRAnatomicalSite");
    jcasType.ll_cas.ll_setRefValue(addr, ((SCRAnatomicalSite_Type)jcasType).casFeatCode_Coreferences, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for Coreferences - gets an indexed value - array of coreference objects that contain this site
   * @generated */
  public TOP getCoreferences(int i) {
    if (SCRAnatomicalSite_Type.featOkTst && ((SCRAnatomicalSite_Type)jcasType).casFeat_Coreferences == null)
      jcasType.jcas.throwFeatMissing("Coreferences", "org.ohnlp.medkat.scr.types.SCRAnatomicalSite");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((SCRAnatomicalSite_Type)jcasType).casFeatCode_Coreferences), i);
    return (TOP)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((SCRAnatomicalSite_Type)jcasType).casFeatCode_Coreferences), i)));}

  /** indexed setter for Coreferences - sets an indexed value - array of coreference objects that contain this site
   * @generated */
  public void setCoreferences(int i, TOP v) { 
    if (SCRAnatomicalSite_Type.featOkTst && ((SCRAnatomicalSite_Type)jcasType).casFeat_Coreferences == null)
      jcasType.jcas.throwFeatMissing("Coreferences", "org.ohnlp.medkat.scr.types.SCRAnatomicalSite");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((SCRAnatomicalSite_Type)jcasType).casFeatCode_Coreferences), i);
    jcasType.ll_cas.ll_setRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((SCRAnatomicalSite_Type)jcasType).casFeatCode_Coreferences), i, jcasType.ll_cas.ll_getFSRef(v));}
  }

    