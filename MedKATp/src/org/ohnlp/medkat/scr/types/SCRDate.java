

/* First created by JCasGen Wed Jun 11 12:10:52 EDT 2008 */
package org.ohnlp.medkat.scr.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;



/** Date object
 * Updated by JCasGen Mon Mar 23 14:01:47 EDT 2009
 * XML source: C:/ohnlp/MedKATp/descriptors/analysis_engine/aggregate/MedKATp.xml
 * @generated */
public class SCRDate extends SCRSpannedAnnotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(SCRDate.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected SCRDate() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public SCRDate(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public SCRDate(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public SCRDate(JCas jcas, int begin, int end) {
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
  //* Feature: Day

  /** getter for Day - gets day of the month
   * @generated */
  public int getDay() {
    if (SCRDate_Type.featOkTst && ((SCRDate_Type)jcasType).casFeat_Day == null)
      jcasType.jcas.throwFeatMissing("Day", "org.ohnlp.medkat.scr.types.SCRDate");
    return jcasType.ll_cas.ll_getIntValue(addr, ((SCRDate_Type)jcasType).casFeatCode_Day);}
    
  /** setter for Day - sets day of the month 
   * @generated */
  public void setDay(int v) {
    if (SCRDate_Type.featOkTst && ((SCRDate_Type)jcasType).casFeat_Day == null)
      jcasType.jcas.throwFeatMissing("Day", "org.ohnlp.medkat.scr.types.SCRDate");
    jcasType.ll_cas.ll_setIntValue(addr, ((SCRDate_Type)jcasType).casFeatCode_Day, v);}    
   
    
  //*--------------*
  //* Feature: Month

  /** getter for Month - gets month
   * @generated */
  public int getMonth() {
    if (SCRDate_Type.featOkTst && ((SCRDate_Type)jcasType).casFeat_Month == null)
      jcasType.jcas.throwFeatMissing("Month", "org.ohnlp.medkat.scr.types.SCRDate");
    return jcasType.ll_cas.ll_getIntValue(addr, ((SCRDate_Type)jcasType).casFeatCode_Month);}
    
  /** setter for Month - sets month 
   * @generated */
  public void setMonth(int v) {
    if (SCRDate_Type.featOkTst && ((SCRDate_Type)jcasType).casFeat_Month == null)
      jcasType.jcas.throwFeatMissing("Month", "org.ohnlp.medkat.scr.types.SCRDate");
    jcasType.ll_cas.ll_setIntValue(addr, ((SCRDate_Type)jcasType).casFeatCode_Month, v);}    
   
    
  //*--------------*
  //* Feature: Year

  /** getter for Year - gets year
   * @generated */
  public int getYear() {
    if (SCRDate_Type.featOkTst && ((SCRDate_Type)jcasType).casFeat_Year == null)
      jcasType.jcas.throwFeatMissing("Year", "org.ohnlp.medkat.scr.types.SCRDate");
    return jcasType.ll_cas.ll_getIntValue(addr, ((SCRDate_Type)jcasType).casFeatCode_Year);}
    
  /** setter for Year - sets year 
   * @generated */
  public void setYear(int v) {
    if (SCRDate_Type.featOkTst && ((SCRDate_Type)jcasType).casFeat_Year == null)
      jcasType.jcas.throwFeatMissing("Year", "org.ohnlp.medkat.scr.types.SCRDate");
    jcasType.ll_cas.ll_setIntValue(addr, ((SCRDate_Type)jcasType).casFeatCode_Year, v);}    
  }

    