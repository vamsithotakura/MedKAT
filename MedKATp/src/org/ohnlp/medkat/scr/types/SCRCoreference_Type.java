
/* First created by JCasGen Wed Jun 11 12:10:51 EDT 2008 */
package org.ohnlp.medkat.scr.types;

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

/** Contains references to semanticly related objects
 * Updated by JCasGen Mon Mar 23 14:01:46 EDT 2009
 * @generated */
public class SCRCoreference_Type extends Annotation_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (SCRCoreference_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = SCRCoreference_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new SCRCoreference(addr, SCRCoreference_Type.this);
  			   SCRCoreference_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new SCRCoreference(addr, SCRCoreference_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = SCRCoreference.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.ohnlp.medkat.scr.types.SCRCoreference");
 
  /** @generated */
  final Feature casFeat_Elements;
  /** @generated */
  final int     casFeatCode_Elements;
  /** @generated */ 
  public int getElements(int addr) {
        if (featOkTst && casFeat_Elements == null)
      jcas.throwFeatMissing("Elements", "org.ohnlp.medkat.scr.types.SCRCoreference");
    return ll_cas.ll_getRefValue(addr, casFeatCode_Elements);
  }
  /** @generated */    
  public void setElements(int addr, int v) {
        if (featOkTst && casFeat_Elements == null)
      jcas.throwFeatMissing("Elements", "org.ohnlp.medkat.scr.types.SCRCoreference");
    ll_cas.ll_setRefValue(addr, casFeatCode_Elements, v);}
    
   /** @generated */
  public int getElements(int addr, int i) {
        if (featOkTst && casFeat_Elements == null)
      jcas.throwFeatMissing("Elements", "org.ohnlp.medkat.scr.types.SCRCoreference");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_Elements), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_Elements), i);
  return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_Elements), i);
  }
   
  /** @generated */ 
  public void setElements(int addr, int i, int v) {
        if (featOkTst && casFeat_Elements == null)
      jcas.throwFeatMissing("Elements", "org.ohnlp.medkat.scr.types.SCRCoreference");
    if (lowLevelTypeChecks)
      ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_Elements), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_Elements), i);
    ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_Elements), i, v);
  }
 



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public SCRCoreference_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_Elements = jcas.getRequiredFeatureDE(casType, "Elements", "uima.cas.FSArray", featOkTst);
    casFeatCode_Elements  = (null == casFeat_Elements) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Elements).getCode();

  }
}



    