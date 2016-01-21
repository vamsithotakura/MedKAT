
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

/** Date object
 * Updated by JCasGen Mon Mar 23 14:01:47 EDT 2009
 * @generated */
public class SCRDate_Type extends SCRSpannedAnnotation_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (SCRDate_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = SCRDate_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new SCRDate(addr, SCRDate_Type.this);
  			   SCRDate_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new SCRDate(addr, SCRDate_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = SCRDate.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.ohnlp.medkat.scr.types.SCRDate");
 
  /** @generated */
  final Feature casFeat_Day;
  /** @generated */
  final int     casFeatCode_Day;
  /** @generated */ 
  public int getDay(int addr) {
        if (featOkTst && casFeat_Day == null)
      jcas.throwFeatMissing("Day", "org.ohnlp.medkat.scr.types.SCRDate");
    return ll_cas.ll_getIntValue(addr, casFeatCode_Day);
  }
  /** @generated */    
  public void setDay(int addr, int v) {
        if (featOkTst && casFeat_Day == null)
      jcas.throwFeatMissing("Day", "org.ohnlp.medkat.scr.types.SCRDate");
    ll_cas.ll_setIntValue(addr, casFeatCode_Day, v);}
    
  
 
  /** @generated */
  final Feature casFeat_Month;
  /** @generated */
  final int     casFeatCode_Month;
  /** @generated */ 
  public int getMonth(int addr) {
        if (featOkTst && casFeat_Month == null)
      jcas.throwFeatMissing("Month", "org.ohnlp.medkat.scr.types.SCRDate");
    return ll_cas.ll_getIntValue(addr, casFeatCode_Month);
  }
  /** @generated */    
  public void setMonth(int addr, int v) {
        if (featOkTst && casFeat_Month == null)
      jcas.throwFeatMissing("Month", "org.ohnlp.medkat.scr.types.SCRDate");
    ll_cas.ll_setIntValue(addr, casFeatCode_Month, v);}
    
  
 
  /** @generated */
  final Feature casFeat_Year;
  /** @generated */
  final int     casFeatCode_Year;
  /** @generated */ 
  public int getYear(int addr) {
        if (featOkTst && casFeat_Year == null)
      jcas.throwFeatMissing("Year", "org.ohnlp.medkat.scr.types.SCRDate");
    return ll_cas.ll_getIntValue(addr, casFeatCode_Year);
  }
  /** @generated */    
  public void setYear(int addr, int v) {
        if (featOkTst && casFeat_Year == null)
      jcas.throwFeatMissing("Year", "org.ohnlp.medkat.scr.types.SCRDate");
    ll_cas.ll_setIntValue(addr, casFeatCode_Year, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public SCRDate_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_Day = jcas.getRequiredFeatureDE(casType, "Day", "uima.cas.Integer", featOkTst);
    casFeatCode_Day  = (null == casFeat_Day) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Day).getCode();

 
    casFeat_Month = jcas.getRequiredFeatureDE(casType, "Month", "uima.cas.Integer", featOkTst);
    casFeatCode_Month  = (null == casFeat_Month) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Month).getCode();

 
    casFeat_Year = jcas.getRequiredFeatureDE(casType, "Year", "uima.cas.Integer", featOkTst);
    casFeatCode_Year  = (null == casFeat_Year) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Year).getCode();

  }
}



    