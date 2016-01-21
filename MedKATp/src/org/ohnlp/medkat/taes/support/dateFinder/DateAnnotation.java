

/* First created by JCasGen Wed Jun 11 12:10:52 EDT 2008 */
package org.ohnlp.medkat.taes.support.dateFinder;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Mon Mar 23 14:01:47 EDT 2009
 * XML source: C:/ohnlp/MedKATp/descriptors/analysis_engine/aggregate/MedKATp.xml
 * @generated */
public class DateAnnotation extends Annotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(DateAnnotation.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected DateAnnotation() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public DateAnnotation(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public DateAnnotation(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public DateAnnotation(JCas jcas, int begin, int end) {
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
  //* Feature: day

  /** getter for day - gets day
   * @generated */
  public int getDay() {
    if (DateAnnotation_Type.featOkTst && ((DateAnnotation_Type)jcasType).casFeat_day == null)
      jcasType.jcas.throwFeatMissing("day", "org.ohnlp.medkat.taes.support.dateFinder.DateAnnotation");
    return jcasType.ll_cas.ll_getIntValue(addr, ((DateAnnotation_Type)jcasType).casFeatCode_day);}
    
  /** setter for day - sets day 
   * @generated */
  public void setDay(int v) {
    if (DateAnnotation_Type.featOkTst && ((DateAnnotation_Type)jcasType).casFeat_day == null)
      jcasType.jcas.throwFeatMissing("day", "org.ohnlp.medkat.taes.support.dateFinder.DateAnnotation");
    jcasType.ll_cas.ll_setIntValue(addr, ((DateAnnotation_Type)jcasType).casFeatCode_day, v);}    
   
    
  //*--------------*
  //* Feature: month

  /** getter for month - gets month
   * @generated */
  public int getMonth() {
    if (DateAnnotation_Type.featOkTst && ((DateAnnotation_Type)jcasType).casFeat_month == null)
      jcasType.jcas.throwFeatMissing("month", "org.ohnlp.medkat.taes.support.dateFinder.DateAnnotation");
    return jcasType.ll_cas.ll_getIntValue(addr, ((DateAnnotation_Type)jcasType).casFeatCode_month);}
    
  /** setter for month - sets month 
   * @generated */
  public void setMonth(int v) {
    if (DateAnnotation_Type.featOkTst && ((DateAnnotation_Type)jcasType).casFeat_month == null)
      jcasType.jcas.throwFeatMissing("month", "org.ohnlp.medkat.taes.support.dateFinder.DateAnnotation");
    jcasType.ll_cas.ll_setIntValue(addr, ((DateAnnotation_Type)jcasType).casFeatCode_month, v);}    
   
    
  //*--------------*
  //* Feature: year

  /** getter for year - gets year
   * @generated */
  public int getYear() {
    if (DateAnnotation_Type.featOkTst && ((DateAnnotation_Type)jcasType).casFeat_year == null)
      jcasType.jcas.throwFeatMissing("year", "org.ohnlp.medkat.taes.support.dateFinder.DateAnnotation");
    return jcasType.ll_cas.ll_getIntValue(addr, ((DateAnnotation_Type)jcasType).casFeatCode_year);}
    
  /** setter for year - sets year 
   * @generated */
  public void setYear(int v) {
    if (DateAnnotation_Type.featOkTst && ((DateAnnotation_Type)jcasType).casFeat_year == null)
      jcasType.jcas.throwFeatMissing("year", "org.ohnlp.medkat.taes.support.dateFinder.DateAnnotation");
    jcasType.ll_cas.ll_setIntValue(addr, ((DateAnnotation_Type)jcasType).casFeatCode_year, v);}    
  }

    