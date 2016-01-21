

/* First created by JCasGen Wed Jun 11 12:10:51 EDT 2008 */
package org.ohnlp.medkat.scr.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.jcas.cas.TOP;


/** Contains references to semanticly related objects
 * Updated by JCasGen Mon Mar 23 14:01:46 EDT 2009
 * XML source: C:/ohnlp/MedKATp/descriptors/analysis_engine/aggregate/MedKATp.xml
 * @generated */
public class SCRCoreference extends Annotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(SCRCoreference.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected SCRCoreference() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public SCRCoreference(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public SCRCoreference(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public SCRCoreference(JCas jcas, int begin, int end) {
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
  //* Feature: Elements

  /** getter for Elements - gets contains corefered objects
   * @generated */
  public FSArray getElements() {
    if (SCRCoreference_Type.featOkTst && ((SCRCoreference_Type)jcasType).casFeat_Elements == null)
      jcasType.jcas.throwFeatMissing("Elements", "org.ohnlp.medkat.scr.types.SCRCoreference");
    return (FSArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((SCRCoreference_Type)jcasType).casFeatCode_Elements)));}
    
  /** setter for Elements - sets contains corefered objects 
   * @generated */
  public void setElements(FSArray v) {
    if (SCRCoreference_Type.featOkTst && ((SCRCoreference_Type)jcasType).casFeat_Elements == null)
      jcasType.jcas.throwFeatMissing("Elements", "org.ohnlp.medkat.scr.types.SCRCoreference");
    jcasType.ll_cas.ll_setRefValue(addr, ((SCRCoreference_Type)jcasType).casFeatCode_Elements, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for Elements - gets an indexed value - contains corefered objects
   * @generated */
  public TOP getElements(int i) {
    if (SCRCoreference_Type.featOkTst && ((SCRCoreference_Type)jcasType).casFeat_Elements == null)
      jcasType.jcas.throwFeatMissing("Elements", "org.ohnlp.medkat.scr.types.SCRCoreference");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((SCRCoreference_Type)jcasType).casFeatCode_Elements), i);
    return (TOP)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((SCRCoreference_Type)jcasType).casFeatCode_Elements), i)));}

  /** indexed setter for Elements - sets an indexed value - contains corefered objects
   * @generated */
  public void setElements(int i, TOP v) { 
    if (SCRCoreference_Type.featOkTst && ((SCRCoreference_Type)jcasType).casFeat_Elements == null)
      jcasType.jcas.throwFeatMissing("Elements", "org.ohnlp.medkat.scr.types.SCRCoreference");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((SCRCoreference_Type)jcasType).casFeatCode_Elements), i);
    jcasType.ll_cas.ll_setRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((SCRCoreference_Type)jcasType).casFeatCode_Elements), i, jcasType.ll_cas.ll_getFSRef(v));}
  }

    