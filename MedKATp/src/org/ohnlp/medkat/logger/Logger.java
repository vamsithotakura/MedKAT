/*
 * $Id$
 */

package org.ohnlp.medkat.logger;

import org.apache.uima.UIMAFramework;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.CAS;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.DocumentAnnotation;
import org.apache.uima.util.Level;

;

/**
 * @version $Revision$
 *
 * @author Michael Tanenblatt &lt;mtan@us.ibm.com&gt;
 * @author (c) Copyright 2006 IBM
 * @author All Rights Reserved
 * 
 * This class is used to log messages to the MedKAT log file. If possible, an
 * ErrorAnnotation will be written to the CAS as well, assuming that the CAS is instantiated
 * and setupDocument() has been called (i.e., if the logger is being used in some
 * part of the code prior to the existence of the CAS, such as initialize(), no 
 * ErrorAnnotation will be created).
 * 
 * IMPORTANT:
 * setupDocument() MUST be called one time before using this logger before an ErrorAnnotation
 * can be written to the CAS!
 */

public class Logger
{


    private static final String LOGERROR_STRING = "MedKAT ERROR: ";
    private static final String LOGWARNING_STRING = "MedKAT WARNING: ";
    private static final String LOGINFO_STRING = "MedKAT INFO: ";
    private static final String LOGFINEST_STRING = "MedKAT INFO: ";
    
    
    private org.apache.uima.util.Logger uimaLogger;
    
    private JCas jcas;

    /**
     * @param logger
     */
    public Logger (org.apache.uima.util.Logger logger)
    {
        if (null == logger) {
            uimaLogger = UIMAFramework.getLogger();
        }
        else {
            uimaLogger = logger;
        }
    }
    
    protected Logger ()
    {
        // do not use this!
    }
    
    static public Logger getDefaultLogger ()
    {
        return new Logger(null);
    }
    
    public void setupDocument (JCas jcas)
    {
        this.jcas = jcas;
    }
    
    
    public void setupDocument (CAS tcas)
    {
        try
        {
            setupDocument(tcas.getJCas());
        }
        catch (CASException e)
        {
            logError ("Logger.setupDocument(): could not get JCas");
            e.printStackTrace();
        }
    }
    
    
    protected void log (Level level, String message)
    {
        if (uimaLogger == null)
        {
            System.err.println("WARNING: No Logger! Log message: '" + message + "'");
        }
        else
        {
            uimaLogger.log(level, message);
        }
    }
    
    

    public void logInfo (String message)
    {
        this.log(Level.INFO, LOGINFO_STRING + message);
        //System.out.println(LOGINFO_STRING + message);
    }


    public void logError (String message)
    {

        //System.out.println(LOGERROR_STRING + message);
        this.log(Level.SEVERE, LOGERROR_STRING + message);
        if (jcas != null)
        {
            ErrorAnnotation annotation = new ErrorAnnotation (jcas);
            DocumentAnnotation documentAnnotation = (DocumentAnnotation) jcas.getDocumentAnnotationFs ();
            if (documentAnnotation != null)
            {
                annotation.setBegin (documentAnnotation.getBegin());
                annotation.setEnd (documentAnnotation.getEnd());
                annotation.setOffendingDocument(documentAnnotation);
                annotation.setErrorText(message);
                annotation.addToIndexes ();
            }
        }
    }



    public void logWarning (String message)
    {
        this.log(Level.WARNING, LOGWARNING_STRING + message);
    }



    public void logFinest (String message)
    {
        this.log(Level.FINEST, LOGFINEST_STRING + message);
    }

}

