
/* First created by JCasGen Tue Feb 10 10:48:54 EST 2009 */
package org.ohnlp.medkat.taes.textdocparser.subannots;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.jcas.tcas.Annotation_Type;

/** Annotation of new-line delimited lines.
 * Updated by JCasGen Mon Mar 23 14:01:47 EDT 2009
 * @generated */
public class NewlineSentenceAnnotation_Type extends Annotation_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (NewlineSentenceAnnotation_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = NewlineSentenceAnnotation_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new NewlineSentenceAnnotation(addr, NewlineSentenceAnnotation_Type.this);
  			   NewlineSentenceAnnotation_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new NewlineSentenceAnnotation(addr, NewlineSentenceAnnotation_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = NewlineSentenceAnnotation.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.ohnlp.medkat.taes.textdocparser.subannots.NewlineSentenceAnnotation");



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public NewlineSentenceAnnotation_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

  }
}



    