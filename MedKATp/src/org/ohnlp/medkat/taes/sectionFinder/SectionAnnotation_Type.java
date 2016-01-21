
/* First created by JCasGen Wed Jun 11 12:10:50 EDT 2008 */
package org.ohnlp.medkat.taes.sectionFinder;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.tcas.Annotation_Type;

/** 
 * Updated by JCasGen Mon Mar 23 14:01:46 EDT 2009
 * @generated */
public class SectionAnnotation_Type extends Annotation_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (SectionAnnotation_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = SectionAnnotation_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new SectionAnnotation(addr, SectionAnnotation_Type.this);
  			   SectionAnnotation_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new SectionAnnotation(addr, SectionAnnotation_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = SectionAnnotation.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.ohnlp.medkat.taes.sectionFinder.SectionAnnotation");
 
  /** @generated */
  final Feature casFeat_headerText;
  /** @generated */
  final int     casFeatCode_headerText;
  /** @generated */ 
  public String getHeaderText(int addr) {
        if (featOkTst && casFeat_headerText == null)
      jcas.throwFeatMissing("headerText", "org.ohnlp.medkat.taes.sectionFinder.SectionAnnotation");
    return ll_cas.ll_getStringValue(addr, casFeatCode_headerText);
  }
  /** @generated */    
  public void setHeaderText(int addr, String v) {
        if (featOkTst && casFeat_headerText == null)
      jcas.throwFeatMissing("headerText", "org.ohnlp.medkat.taes.sectionFinder.SectionAnnotation");
    ll_cas.ll_setStringValue(addr, casFeatCode_headerText, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public SectionAnnotation_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_headerText = jcas.getRequiredFeatureDE(casType, "headerText", "uima.cas.String", featOkTst);
    casFeatCode_headerText  = (null == casFeat_headerText) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_headerText).getCode();

  }
}



    