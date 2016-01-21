

/* First created by JCasGen Wed Jun 11 12:10:49 EDT 2008 */
package org.ohnlp.medkat.taes.subSubsectionDetector;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;



/** 
 * Updated by JCasGen Mon Mar 23 14:01:46 EDT 2009
 * XML source: C:/ohnlp/MedKATp/descriptors/analysis_engine/aggregate/MedKATp.xml
 * @generated */
public class DiagnosisBaseSubSubsectionAnnotation extends SubSubsection {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(DiagnosisBaseSubSubsectionAnnotation.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected DiagnosisBaseSubSubsectionAnnotation() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public DiagnosisBaseSubSubsectionAnnotation(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public DiagnosisBaseSubSubsectionAnnotation(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public DiagnosisBaseSubSubsectionAnnotation(JCas jcas, int begin, int end) {
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
     
}

    