

/* First created by JCasGen Wed Jun 11 12:10:52 EDT 2008 */
package org.ohnlp.medkat.scr.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.jcas.cas.TOP;


/** geometrical size object
 * Updated by JCasGen Mon Mar 23 14:01:47 EDT 2009
 * XML source: C:/ohnlp/MedKATp/descriptors/analysis_engine/aggregate/MedKATp.xml
 * @generated */
public class SCRSize extends Annotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(SCRSize.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected SCRSize() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public SCRSize(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public SCRSize(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public SCRSize(JCas jcas, int begin, int end) {
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
  //* Feature: Dimensions

  /** getter for Dimensions - gets size dimensions
   * @generated */
  public FSArray getDimensions() {
    if (SCRSize_Type.featOkTst && ((SCRSize_Type)jcasType).casFeat_Dimensions == null)
      jcasType.jcas.throwFeatMissing("Dimensions", "org.ohnlp.medkat.scr.types.SCRSize");
    return (FSArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((SCRSize_Type)jcasType).casFeatCode_Dimensions)));}
    
  /** setter for Dimensions - sets size dimensions 
   * @generated */
  public void setDimensions(FSArray v) {
    if (SCRSize_Type.featOkTst && ((SCRSize_Type)jcasType).casFeat_Dimensions == null)
      jcasType.jcas.throwFeatMissing("Dimensions", "org.ohnlp.medkat.scr.types.SCRSize");
    jcasType.ll_cas.ll_setRefValue(addr, ((SCRSize_Type)jcasType).casFeatCode_Dimensions, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for Dimensions - gets an indexed value - size dimensions
   * @generated */
  public TOP getDimensions(int i) {
    if (SCRSize_Type.featOkTst && ((SCRSize_Type)jcasType).casFeat_Dimensions == null)
      jcasType.jcas.throwFeatMissing("Dimensions", "org.ohnlp.medkat.scr.types.SCRSize");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((SCRSize_Type)jcasType).casFeatCode_Dimensions), i);
    return (TOP)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((SCRSize_Type)jcasType).casFeatCode_Dimensions), i)));}

  /** indexed setter for Dimensions - sets an indexed value - size dimensions
   * @generated */
  public void setDimensions(int i, TOP v) { 
    if (SCRSize_Type.featOkTst && ((SCRSize_Type)jcasType).casFeat_Dimensions == null)
      jcasType.jcas.throwFeatMissing("Dimensions", "org.ohnlp.medkat.scr.types.SCRSize");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((SCRSize_Type)jcasType).casFeatCode_Dimensions), i);
    jcasType.ll_cas.ll_setRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((SCRSize_Type)jcasType).casFeatCode_Dimensions), i, jcasType.ll_cas.ll_getFSRef(v));}
  }

    