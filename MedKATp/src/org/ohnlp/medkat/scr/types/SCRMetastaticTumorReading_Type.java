
/* First created by JCasGen Wed Jun 11 12:10:52 EDT 2008 */
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

/** metastatic tumor reading object
 * Updated by JCasGen Mon Mar 23 14:01:48 EDT 2009
 * @generated */
public class SCRMetastaticTumorReading_Type extends SCRPrimaryTumorReading_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (SCRMetastaticTumorReading_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = SCRMetastaticTumorReading_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new SCRMetastaticTumorReading(addr, SCRMetastaticTumorReading_Type.this);
  			   SCRMetastaticTumorReading_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new SCRMetastaticTumorReading(addr, SCRMetastaticTumorReading_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = SCRMetastaticTumorReading.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.ohnlp.medkat.scr.types.SCRMetastaticTumorReading");
 
  /** @generated */
  final Feature casFeat_OriginatingSite;
  /** @generated */
  final int     casFeatCode_OriginatingSite;
  /** @generated */ 
  public int getOriginatingSite(int addr) {
        if (featOkTst && casFeat_OriginatingSite == null)
      jcas.throwFeatMissing("OriginatingSite", "org.ohnlp.medkat.scr.types.SCRMetastaticTumorReading");
    return ll_cas.ll_getRefValue(addr, casFeatCode_OriginatingSite);
  }
  /** @generated */    
  public void setOriginatingSite(int addr, int v) {
        if (featOkTst && casFeat_OriginatingSite == null)
      jcas.throwFeatMissing("OriginatingSite", "org.ohnlp.medkat.scr.types.SCRMetastaticTumorReading");
    ll_cas.ll_setRefValue(addr, casFeatCode_OriginatingSite, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public SCRMetastaticTumorReading_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_OriginatingSite = jcas.getRequiredFeatureDE(casType, "OriginatingSite", "uima.tcas.Annotation", featOkTst);
    casFeatCode_OriginatingSite  = (null == casFeat_OriginatingSite) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_OriginatingSite).getCode();

  }
}



    