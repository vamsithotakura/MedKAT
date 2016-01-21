package org.ohnlp.medkat.common;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.apache.uima.analysis_engine.annotator.AnnotatorContext;
import org.apache.uima.analysis_engine.annotator.AnnotatorContextException;

public class ParameterProcessor
{

    private static void lowerCaseArray (String [] array)
    {
        for (int i = 0; i < array.length; i++)
        {
            array[i] = array[i].toLowerCase ();
        }
    }

    
    public static Set<String> paramArrayToSet (AnnotatorContext annotatorContext, String paramName, boolean downCase) throws AnnotatorContextException
    {
        Set<String> set = new HashSet<String> ();

        String [] paramArray = ((String []) annotatorContext.getConfigParameterValue (paramName));
        if (paramArray != null)
        {
            if (downCase)
            {
                lowerCaseArray (paramArray);
            }
            set.addAll (Arrays.asList (paramArray));
        }
        return set;
    }

}
