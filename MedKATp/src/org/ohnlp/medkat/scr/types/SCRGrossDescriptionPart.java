

/* First created by JCasGen Wed Jun 11 12:10:52 EDT 2008 */
package org.ohnlp.medkat.scr.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.jcas.cas.TOP;


/** grade value object
 * Updated by JCasGen Mon Mar 23 14:01:47 EDT 2009
 * XML source: C:/ohnlp/MedKATp/descriptors/analysis_engine/aggregate/MedKATp.xml
 * @generated */
public class SCRGrossDescriptionPart extends Annotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(SCRGrossDescriptionPart.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected SCRGrossDescriptionPart() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public SCRGrossDescriptionPart(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public SCRGrossDescriptionPart(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public SCRGrossDescriptionPart(JCas jcas, int begin, int end) {
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
  //* Feature: Size

  /** getter for Size - gets size of gross description
   * @generated */
  public Annotation getSize() {
    if (SCRGrossDescriptionPart_Type.featOkTst && ((SCRGrossDescriptionPart_Type)jcasType).casFeat_Size == null)
      jcasType.jcas.throwFeatMissing("Size", "org.ohnlp.medkat.scr.types.SCRGrossDescriptionPart");
    return (Annotation)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((SCRGrossDescriptionPart_Type)jcasType).casFeatCode_Size)));}
    
  /** setter for Size - sets size of gross description 
   * @generated */
  public void setSize(Annotation v) {
    if (SCRGrossDescriptionPart_Type.featOkTst && ((SCRGrossDescriptionPart_Type)jcasType).casFeat_Size == null)
      jcasType.jcas.throwFeatMissing("Size", "org.ohnlp.medkat.scr.types.SCRGrossDescriptionPart");
    jcasType.ll_cas.ll_setRefValue(addr, ((SCRGrossDescriptionPart_Type)jcasType).casFeatCode_Size, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: Sites

  /** getter for Sites - gets releated sites
   * @generated */
  public FSArray getSites() {
    if (SCRGrossDescriptionPart_Type.featOkTst && ((SCRGrossDescriptionPart_Type)jcasType).casFeat_Sites == null)
      jcasType.jcas.throwFeatMissing("Sites", "org.ohnlp.medkat.scr.types.SCRGrossDescriptionPart");
    return (FSArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((SCRGrossDescriptionPart_Type)jcasType).casFeatCode_Sites)));}
    
  /** setter for Sites - sets releated sites 
   * @generated */
  public void setSites(FSArray v) {
    if (SCRGrossDescriptionPart_Type.featOkTst && ((SCRGrossDescriptionPart_Type)jcasType).casFeat_Sites == null)
      jcasType.jcas.throwFeatMissing("Sites", "org.ohnlp.medkat.scr.types.SCRGrossDescriptionPart");
    jcasType.ll_cas.ll_setRefValue(addr, ((SCRGrossDescriptionPart_Type)jcasType).casFeatCode_Sites, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for Sites - gets an indexed value - releated sites
   * @generated */
  public TOP getSites(int i) {
    if (SCRGrossDescriptionPart_Type.featOkTst && ((SCRGrossDescriptionPart_Type)jcasType).casFeat_Sites == null)
      jcasType.jcas.throwFeatMissing("Sites", "org.ohnlp.medkat.scr.types.SCRGrossDescriptionPart");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((SCRGrossDescriptionPart_Type)jcasType).casFeatCode_Sites), i);
    return (TOP)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((SCRGrossDescriptionPart_Type)jcasType).casFeatCode_Sites), i)));}

  /** indexed setter for Sites - sets an indexed value - releated sites
   * @generated */
  public void setSites(int i, TOP v) { 
    if (SCRGrossDescriptionPart_Type.featOkTst && ((SCRGrossDescriptionPart_Type)jcasType).casFeat_Sites == null)
      jcasType.jcas.throwFeatMissing("Sites", "org.ohnlp.medkat.scr.types.SCRGrossDescriptionPart");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((SCRGrossDescriptionPart_Type)jcasType).casFeatCode_Sites), i);
    jcasType.ll_cas.ll_setRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((SCRGrossDescriptionPart_Type)jcasType).casFeatCode_Sites), i, jcasType.ll_cas.ll_getFSRef(v));}
   
    
  //*--------------*
  //* Feature: Enclosing

  /** getter for Enclosing - gets 
   * @generated */
  public Annotation getEnclosing() {
    if (SCRGrossDescriptionPart_Type.featOkTst && ((SCRGrossDescriptionPart_Type)jcasType).casFeat_Enclosing == null)
      jcasType.jcas.throwFeatMissing("Enclosing", "org.ohnlp.medkat.scr.types.SCRGrossDescriptionPart");
    return (Annotation)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((SCRGrossDescriptionPart_Type)jcasType).casFeatCode_Enclosing)));}
    
  /** setter for Enclosing - sets  
   * @generated */
  public void setEnclosing(Annotation v) {
    if (SCRGrossDescriptionPart_Type.featOkTst && ((SCRGrossDescriptionPart_Type)jcasType).casFeat_Enclosing == null)
      jcasType.jcas.throwFeatMissing("Enclosing", "org.ohnlp.medkat.scr.types.SCRGrossDescriptionPart");
    jcasType.ll_cas.ll_setRefValue(addr, ((SCRGrossDescriptionPart_Type)jcasType).casFeatCode_Enclosing, jcasType.ll_cas.ll_getFSRef(v));}    
  }

    