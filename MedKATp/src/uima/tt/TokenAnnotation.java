

/* First created by JCasGen Thu Feb 05 11:48:07 EST 2009 */
package uima.tt;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;



import org.apache.uima.jcas.tcas.Annotation;


/** Tokens.
 * Updated by JCasGen Mon Mar 23 14:01:46 EDT 2009
 * XML source: C:/ohnlp/MedKATp/descriptors/analysis_engine/aggregate/MedKATp.xml
 * @generated */
public class TokenAnnotation extends Annotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(TokenAnnotation.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected TokenAnnotation() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public TokenAnnotation(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public TokenAnnotation(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public TokenAnnotation(JCas jcas, int begin, int end) {
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
  //* Feature: pennTag

  /** getter for pennTag - gets 
   * @generated */
  public String getPennTag() {
    if (TokenAnnotation_Type.featOkTst && ((TokenAnnotation_Type)jcasType).casFeat_pennTag == null)
      jcasType.jcas.throwFeatMissing("pennTag", "uima.tt.TokenAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((TokenAnnotation_Type)jcasType).casFeatCode_pennTag);}
    
  /** setter for pennTag - sets  
   * @generated */
  public void setPennTag(String v) {
    if (TokenAnnotation_Type.featOkTst && ((TokenAnnotation_Type)jcasType).casFeat_pennTag == null)
      jcasType.jcas.throwFeatMissing("pennTag", "uima.tt.TokenAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((TokenAnnotation_Type)jcasType).casFeatCode_pennTag, v);}    
   
    
  //*--------------*
  //* Feature: SemClass

  /** getter for SemClass - gets semantic class of token
   * @generated */
  public String getSemClass() {
    if (TokenAnnotation_Type.featOkTst && ((TokenAnnotation_Type)jcasType).casFeat_SemClass == null)
      jcasType.jcas.throwFeatMissing("SemClass", "uima.tt.TokenAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((TokenAnnotation_Type)jcasType).casFeatCode_SemClass);}
    
  /** setter for SemClass - sets semantic class of token 
   * @generated */
  public void setSemClass(String v) {
    if (TokenAnnotation_Type.featOkTst && ((TokenAnnotation_Type)jcasType).casFeat_SemClass == null)
      jcasType.jcas.throwFeatMissing("SemClass", "uima.tt.TokenAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((TokenAnnotation_Type)jcasType).casFeatCode_SemClass, v);}    
   
    
  //*--------------*
  //* Feature: POS

  /** getter for POS - gets Part of Speech of term to which this token is a part
   * @generated */
  public String getPOS() {
    if (TokenAnnotation_Type.featOkTst && ((TokenAnnotation_Type)jcasType).casFeat_POS == null)
      jcasType.jcas.throwFeatMissing("POS", "uima.tt.TokenAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((TokenAnnotation_Type)jcasType).casFeatCode_POS);}
    
  /** setter for POS - sets Part of Speech of term to which this token is a part 
   * @generated */
  public void setPOS(String v) {
    if (TokenAnnotation_Type.featOkTst && ((TokenAnnotation_Type)jcasType).casFeat_POS == null)
      jcasType.jcas.throwFeatMissing("POS", "uima.tt.TokenAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((TokenAnnotation_Type)jcasType).casFeatCode_POS, v);}    
  }

    