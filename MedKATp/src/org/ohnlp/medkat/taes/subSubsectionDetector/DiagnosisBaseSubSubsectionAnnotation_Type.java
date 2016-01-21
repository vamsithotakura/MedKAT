
/* First created by JCasGen Wed Jun 11 12:10:49 EDT 2008 */
package org.ohnlp.medkat.taes.subSubsectionDetector;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;

/** 
 * Updated by JCasGen Mon Mar 23 14:01:46 EDT 2009
 * @generated */
public class DiagnosisBaseSubSubsectionAnnotation_Type extends SubSubsection_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (DiagnosisBaseSubSubsectionAnnotation_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = DiagnosisBaseSubSubsectionAnnotation_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new DiagnosisBaseSubSubsectionAnnotation(addr, DiagnosisBaseSubSubsectionAnnotation_Type.this);
  			   DiagnosisBaseSubSubsectionAnnotation_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new DiagnosisBaseSubSubsectionAnnotation(addr, DiagnosisBaseSubSubsectionAnnotation_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = DiagnosisBaseSubSubsectionAnnotation.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.ohnlp.medkat.taes.subSubsectionDetector.DiagnosisBaseSubSubsectionAnnotation");



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public DiagnosisBaseSubSubsectionAnnotation_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

  }
}



    