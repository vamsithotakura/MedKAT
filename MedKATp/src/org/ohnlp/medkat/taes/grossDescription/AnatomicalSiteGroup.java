

/* First created by JCasGen Wed Jun 11 12:10:52 EDT 2008 */
package org.ohnlp.medkat.taes.grossDescription;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.jcas.cas.TOP;


/** 
 * Updated by JCasGen Mon Mar 23 14:01:47 EDT 2009
 * XML source: C:/ohnlp/MedKATp/descriptors/analysis_engine/aggregate/MedKATp.xml
 * @generated */
public class AnatomicalSiteGroup extends Annotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(AnatomicalSiteGroup.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected AnatomicalSiteGroup() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public AnatomicalSiteGroup(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public AnatomicalSiteGroup(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public AnatomicalSiteGroup(JCas jcas, int begin, int end) {
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
  //* Feature: Sites

  /** getter for Sites - gets 
   * @generated */
  public FSArray getSites() {
    if (AnatomicalSiteGroup_Type.featOkTst && ((AnatomicalSiteGroup_Type)jcasType).casFeat_Sites == null)
      jcasType.jcas.throwFeatMissing("Sites", "org.ohnlp.medkat.taes.grossDescription.AnatomicalSiteGroup");
    return (FSArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((AnatomicalSiteGroup_Type)jcasType).casFeatCode_Sites)));}
    
  /** setter for Sites - sets  
   * @generated */
  public void setSites(FSArray v) {
    if (AnatomicalSiteGroup_Type.featOkTst && ((AnatomicalSiteGroup_Type)jcasType).casFeat_Sites == null)
      jcasType.jcas.throwFeatMissing("Sites", "org.ohnlp.medkat.taes.grossDescription.AnatomicalSiteGroup");
    jcasType.ll_cas.ll_setRefValue(addr, ((AnatomicalSiteGroup_Type)jcasType).casFeatCode_Sites, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for Sites - gets an indexed value - 
   * @generated */
  public TOP getSites(int i) {
    if (AnatomicalSiteGroup_Type.featOkTst && ((AnatomicalSiteGroup_Type)jcasType).casFeat_Sites == null)
      jcasType.jcas.throwFeatMissing("Sites", "org.ohnlp.medkat.taes.grossDescription.AnatomicalSiteGroup");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((AnatomicalSiteGroup_Type)jcasType).casFeatCode_Sites), i);
    return (TOP)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((AnatomicalSiteGroup_Type)jcasType).casFeatCode_Sites), i)));}

  /** indexed setter for Sites - sets an indexed value - 
   * @generated */
  public void setSites(int i, TOP v) { 
    if (AnatomicalSiteGroup_Type.featOkTst && ((AnatomicalSiteGroup_Type)jcasType).casFeat_Sites == null)
      jcasType.jcas.throwFeatMissing("Sites", "org.ohnlp.medkat.taes.grossDescription.AnatomicalSiteGroup");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((AnatomicalSiteGroup_Type)jcasType).casFeatCode_Sites), i);
    jcasType.ll_cas.ll_setRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((AnatomicalSiteGroup_Type)jcasType).casFeatCode_Sites), i, jcasType.ll_cas.ll_getFSRef(v));}
  }

    