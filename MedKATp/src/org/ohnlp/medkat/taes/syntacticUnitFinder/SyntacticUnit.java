

/* First created by JCasGen Wed Jun 11 14:52:53 EDT 2008 */
package org.ohnlp.medkat.taes.syntacticUnitFinder;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Mon Mar 23 14:01:46 EDT 2009
 * XML source: C:/ohnlp/MedKATp/descriptors/analysis_engine/aggregate/MedKATp.xml
 * @generated */
public class SyntacticUnit extends Annotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(SyntacticUnit.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected SyntacticUnit() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public SyntacticUnit(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public SyntacticUnit(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public SyntacticUnit(JCas jcas, int begin, int end) {
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
  //* Feature: scope

  /** getter for scope - gets 
   * @generated */
  public int getScope() {
    if (SyntacticUnit_Type.featOkTst && ((SyntacticUnit_Type)jcasType).casFeat_scope == null)
      jcasType.jcas.throwFeatMissing("scope", "org.ohnlp.medkat.taes.syntacticUnitFinder.SyntacticUnit");
    return jcasType.ll_cas.ll_getIntValue(addr, ((SyntacticUnit_Type)jcasType).casFeatCode_scope);}
    
  /** setter for scope - sets  
   * @generated */
  public void setScope(int v) {
    if (SyntacticUnit_Type.featOkTst && ((SyntacticUnit_Type)jcasType).casFeat_scope == null)
      jcasType.jcas.throwFeatMissing("scope", "org.ohnlp.medkat.taes.syntacticUnitFinder.SyntacticUnit");
    jcasType.ll_cas.ll_setIntValue(addr, ((SyntacticUnit_Type)jcasType).casFeatCode_scope, v);}    
  }

    