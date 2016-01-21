

/* First created by JCasGen Wed Jun 11 12:10:52 EDT 2008 */
package org.ohnlp.medkat.scr.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** metastatic tumor reading object
 * Updated by JCasGen Mon Mar 23 14:01:48 EDT 2009
 * XML source: C:/ohnlp/MedKATp/descriptors/analysis_engine/aggregate/MedKATp.xml
 * @generated */
public class SCRMetastaticTumorReading extends SCRPrimaryTumorReading {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(SCRMetastaticTumorReading.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected SCRMetastaticTumorReading() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public SCRMetastaticTumorReading(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public SCRMetastaticTumorReading(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public SCRMetastaticTumorReading(JCas jcas, int begin, int end) {
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
  //* Feature: OriginatingSite

  /** getter for OriginatingSite - gets anatomical site that originated the tumor
   * @generated */
  public Annotation getOriginatingSite() {
    if (SCRMetastaticTumorReading_Type.featOkTst && ((SCRMetastaticTumorReading_Type)jcasType).casFeat_OriginatingSite == null)
      jcasType.jcas.throwFeatMissing("OriginatingSite", "org.ohnlp.medkat.scr.types.SCRMetastaticTumorReading");
    return (Annotation)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((SCRMetastaticTumorReading_Type)jcasType).casFeatCode_OriginatingSite)));}
    
  /** setter for OriginatingSite - sets anatomical site that originated the tumor 
   * @generated */
  public void setOriginatingSite(Annotation v) {
    if (SCRMetastaticTumorReading_Type.featOkTst && ((SCRMetastaticTumorReading_Type)jcasType).casFeat_OriginatingSite == null)
      jcasType.jcas.throwFeatMissing("OriginatingSite", "org.ohnlp.medkat.scr.types.SCRMetastaticTumorReading");
    jcasType.ll_cas.ll_setRefValue(addr, ((SCRMetastaticTumorReading_Type)jcasType).casFeatCode_OriginatingSite, jcasType.ll_cas.ll_getFSRef(v));}    
  }

    