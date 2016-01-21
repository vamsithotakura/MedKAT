/*
 * $Id$
 */

package org.ohnlp.medkat.taes.support.dateFinder;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import org.apache.uima.analysis_engine.ResultSpecification;
import org.apache.uima.analysis_engine.annotator.AnnotatorConfigurationException;
import org.apache.uima.analysis_engine.annotator.AnnotatorContext;
import org.apache.uima.analysis_engine.annotator.AnnotatorInitializationException;
import org.apache.uima.analysis_engine.annotator.AnnotatorProcessException;
import org.apache.uima.analysis_engine.annotator.JTextAnnotator_ImplBase;
import org.apache.uima.jcas.JCas;
import org.ohnlp.medkat.scr.typeConversion.MedKATTypeConverter;


/*
 * @version $Revision$
 *
 * @author Michael Tanenblatt <mtan@us.ibm.com>
 * @author (c) Copyright 2005 IBM
 * @author All Rights Reserved
 */
public class DateFinder
        extends JTextAnnotator_ImplBase
{

    private String monthNames = "(?:(?:Jan\\.?)|(?:January)|" +
            					     "(?:Feb\\.?)|(?:February)|" +
            						 "(?:Mar\\.?)|(?:March)|" +
            						 "(?:Apr\\.?)|(?:April)|" +
            						 "(?:May)|" +
            						 "(?:Jun[\\.e]?)|" +
            						 "(?:Jul[\\.y]?)|" +
            						 "(?:Aug\\.?)|(?:August)|" +
            						 "(?:Sept?\\.?)|(?:September)|" +
            						 "(?:Oct\\.?)|(?:October)|" +
            						 "(?:Nov\\.?)|(?:November)|" +
            						 "(?:Dec\\.?)|(?:December))";
    
    /**
     * 1/2/34, 01/2/34, 1/02/34, 01/02/34, 1/2/1934, 01/2/2034, 1/02/2034, 01/02/2034, 1/2/2034, 01/2/2034, 1/02/2034, 01/02/2034
     */
    private String slashDateRegex = "\\p{Digit}{1,2}/\\p{Digit}{1,2}/(?:(?:19)|(?:20))?\\p{Digit}{2}";
    
    /**
     * 1-2-34, 01-2-34, 1-02-34, 01-02-34, 1-2-1934, 01-2-2034, 1-02-2034, 01-02-2034, 1-2-2034, 01-2-2034, 1-02-2034, 01-02-2034
     */
    private String dashDateRegex = "\\p{Digit}{1,2}-\\p{Digit}{1,2}-(?:(?:19)|(?:20))?\\p{Digit}{2}";

    /**
     * Apr 25 2000 or January 20, 2003 or Apr 25th 2000 or January 20th, 2003
     */
    private String monthDateYearRegex = monthNames + "\\p{Space}\\p{Digit}{1,2}(?:(?:st)|(?:nd)|(?:rd)|(?:th))?,?\\p{Space}(?:(?:19)|(?:20))\\p{Digit}{2}";

    /**
     * 25 Apr 2000 or 20 January, 2003 or 25th Apr 2000 or 20th January, 2003
     */
    private String dateMonthYearRegex = "\\p{Digit}{1,2}(?:(?:st)|(?:nd)|(?:rd)|(?:th))?\\p{Space}" + monthNames + ",?\\p{Space}(?:(?:19)|(?:20))\\p{Digit}{2}";    
    
    private Pattern datePattern = Pattern.compile ("(?:" + monthDateYearRegex + ")|" + "(?:" + dateMonthYearRegex + ")|" + "(?:" + slashDateRegex + ")|" + "(?:" + dashDateRegex + ")", Pattern.CASE_INSENSITIVE);
    /* (non-Javadoc)
     * @see org.apache.uima.analysis_engine.annotator.BaseAnnotator#initialize(org.apache.uima.analysis_engine.annotator.AnnotatorContext)
     */
    public void initialize (AnnotatorContext annotatorContext)
        throws AnnotatorInitializationException,
            AnnotatorConfigurationException
    {
        super.initialize (annotatorContext);
        
    }

    
    /* (non-Javadoc)
     * @see org.apache.uima.analysis_engine.annotator.JTextAnnotator#process(org.apache.uima.jcas.JCas, org.apache.uima.analysis_engine.ResultSpecification)
     */
    public void process (JCas jcas, ResultSpecification resultSpecification)
        throws AnnotatorProcessException
    {

        String docText = jcas.getDocumentText ();
        Matcher dateMatcher = datePattern.matcher (docText);
        Calendar clndr = Calendar.getInstance();

        while (dateMatcher.find ())
        {
            DateAnnotation annotation = new DateAnnotation (jcas);
            annotation.setBegin (dateMatcher.start ());
            annotation.setEnd (dateMatcher.end ());
            /*System.err.println ("Date begin: '"
                                + annotation.getBegin()
                                + "', end: '"
                                + annotation.getEnd()
                                + ", Date: '"
                                + annotation.getCoveredText ()
                                +"'");
            */
            try {
                Date dt = null;
                String text = annotation.getCoveredText();
                if ((text.indexOf("/") >= 0) || ((text.indexOf("-") >= 0))) {
                    if (text.indexOf("-") >= 0) {
                        text = text.replaceAll("-", "/");
                    }
                    dt = DateFormat.getDateInstance(DateFormat.SHORT).parse(text);
                }
                else {
                    dt = DateFormat.getDateInstance(DateFormat.FULL).parse(text);
                }
                
                clndr.setTime(dt);
                annotation.setDay(clndr.get(Calendar.DAY_OF_MONTH));
                annotation.setMonth(clndr.get(Calendar.MONTH) + 1);
                annotation.setYear(clndr.get(Calendar.YEAR));
                MedKATTypeConverter.convert(jcas, annotation);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            
            annotation.addToIndexes ();

        }
    }

}
