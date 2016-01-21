

/* First created by JCasGen Wed Jun 11 12:10:51 EDT 2008 */
package org.ohnlp.medkat.scr.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;



/** SCR Named Entity type
 * Updated by JCasGen Mon Mar 23 14:01:47 EDT 2009
 * XML source: C:/ohnlp/MedKATp/descriptors/analysis_engine/aggregate/MedKATp.xml
 * @generated */
public class SCRNamedEntity extends SCRNamedEntityBase {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(SCRNamedEntity.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected SCRNamedEntity() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public SCRNamedEntity(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public SCRNamedEntity(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public SCRNamedEntity(JCas jcas, int begin, int end) {
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
  //* Feature: SemanticClass

  /** getter for SemanticClass - gets semantic class of the named entity
   * @generated */
  public String getSemanticClass() {
    if (SCRNamedEntity_Type.featOkTst && ((SCRNamedEntity_Type)jcasType).casFeat_SemanticClass == null)
      jcasType.jcas.throwFeatMissing("SemanticClass", "org.ohnlp.medkat.scr.types.SCRNamedEntity");
    return jcasType.ll_cas.ll_getStringValue(addr, ((SCRNamedEntity_Type)jcasType).casFeatCode_SemanticClass);}
    
  /** setter for SemanticClass - sets semantic class of the named entity 
   * @generated */
  public void setSemanticClass(String v) {
    if (SCRNamedEntity_Type.featOkTst && ((SCRNamedEntity_Type)jcasType).casFeat_SemanticClass == null)
      jcasType.jcas.throwFeatMissing("SemanticClass", "org.ohnlp.medkat.scr.types.SCRNamedEntity");
    jcasType.ll_cas.ll_setStringValue(addr, ((SCRNamedEntity_Type)jcasType).casFeatCode_SemanticClass, v);}    
   
    
  //*--------------*
  //* Feature: Modifier

  /** getter for Modifier - gets boolean flag that indicates that this named entity is a modifier to another named entity
   * @generated */
  public int getModifier() {
    if (SCRNamedEntity_Type.featOkTst && ((SCRNamedEntity_Type)jcasType).casFeat_Modifier == null)
      jcasType.jcas.throwFeatMissing("Modifier", "org.ohnlp.medkat.scr.types.SCRNamedEntity");
    return jcasType.ll_cas.ll_getIntValue(addr, ((SCRNamedEntity_Type)jcasType).casFeatCode_Modifier);}
    
  /** setter for Modifier - sets boolean flag that indicates that this named entity is a modifier to another named entity 
   * @generated */
  public void setModifier(int v) {
    if (SCRNamedEntity_Type.featOkTst && ((SCRNamedEntity_Type)jcasType).casFeat_Modifier == null)
      jcasType.jcas.throwFeatMissing("Modifier", "org.ohnlp.medkat.scr.types.SCRNamedEntity");
    jcasType.ll_cas.ll_setIntValue(addr, ((SCRNamedEntity_Type)jcasType).casFeatCode_Modifier, v);}    
   
    
  //*--------------*
  //* Feature: Metastatic

  /** getter for Metastatic - gets boolean flag that indicates that this named entity is metastatic
   * @generated */
  public int getMetastatic() {
    if (SCRNamedEntity_Type.featOkTst && ((SCRNamedEntity_Type)jcasType).casFeat_Metastatic == null)
      jcasType.jcas.throwFeatMissing("Metastatic", "org.ohnlp.medkat.scr.types.SCRNamedEntity");
    return jcasType.ll_cas.ll_getIntValue(addr, ((SCRNamedEntity_Type)jcasType).casFeatCode_Metastatic);}
    
  /** setter for Metastatic - sets boolean flag that indicates that this named entity is metastatic 
   * @generated */
  public void setMetastatic(int v) {
    if (SCRNamedEntity_Type.featOkTst && ((SCRNamedEntity_Type)jcasType).casFeat_Metastatic == null)
      jcasType.jcas.throwFeatMissing("Metastatic", "org.ohnlp.medkat.scr.types.SCRNamedEntity");
    jcasType.ll_cas.ll_setIntValue(addr, ((SCRNamedEntity_Type)jcasType).casFeatCode_Metastatic, v);}    
  }

    