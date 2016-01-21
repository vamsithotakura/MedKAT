/*
 * $Id$
 */

package org.ohnlp.medkat.taes.gradeDetector;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

/**
 * @version $Revision$
 *
 * @author Michael Tanenblatt &lt;mtan@us.ibm.com&gt;
 * @author (c) Copyright 2006 IBM
 * @author All Rights Reserved
 */

public class GradeMap
    extends Hashtable<String, String []>
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public static GradeMap convert (Properties gradeMapProps)
    {
        GradeMap result = new GradeMap ();
        
        Set<Object> keys = gradeMapProps.keySet();
        Iterator<Object> keyIterator = keys.iterator();
        while (keyIterator.hasNext())
        {
            String key = (String)keyIterator.next();
            String value = (String) gradeMapProps.get(key);
            String [] values = value.split(",");
            String [] newValues = new String [value.length()];
            for (int i = 0; i < value.length(); i++)
            {
                newValues[i] = values[i].trim();
            }
            result.put(key, newValues);
        }
        
        return result;
    }
    
}

