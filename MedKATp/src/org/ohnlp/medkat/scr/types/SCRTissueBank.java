

/* First created by JCasGen Wed Aug 27 00:14:06 EDT 2008 */
package org.ohnlp.medkat.scr.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;



/** tissue bank object
 * Updated by JCasGen Wed Mar 11 15:34:13 EDT 2009
 * XML source: C:/eclipse/MedKATp/src/org/ohnlp/medkat/scr/types/SCRTypeSystem.xml
 * @generated */
public class SCRTissueBank extends SCRSpannedAnnotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(SCRTissueBank.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected SCRTissueBank() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public SCRTissueBank(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public SCRTissueBank(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public SCRTissueBank(JCas jcas, int begin, int end) {
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

    