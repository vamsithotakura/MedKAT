
/* First created by JCasGen Wed Jun 11 12:10:51 EDT 2008 */
package org.ohnlp.medkat.taes.sectionFinder;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;

/** 
 * Updated by JCasGen Mon Mar 23 14:01:47 EDT 2009
 * @generated */
public class DateOfCollection_Procedure_OutsideReportAnnotation_Type extends DateSectionAnnotation_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (DateOfCollection_Procedure_OutsideReportAnnotation_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = DateOfCollection_Procedure_OutsideReportAnnotation_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new DateOfCollection_Procedure_OutsideReportAnnotation(addr, DateOfCollection_Procedure_OutsideReportAnnotation_Type.this);
  			   DateOfCollection_Procedure_OutsideReportAnnotation_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new DateOfCollection_Procedure_OutsideReportAnnotation(addr, DateOfCollection_Procedure_OutsideReportAnnotation_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = DateOfCollection_Procedure_OutsideReportAnnotation.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.ohnlp.medkat.taes.sectionFinder.DateOfCollection_Procedure_OutsideReportAnnotation");



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public DateOfCollection_Procedure_OutsideReportAnnotation_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

  }
}



    