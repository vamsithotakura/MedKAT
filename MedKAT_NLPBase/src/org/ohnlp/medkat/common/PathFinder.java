package org.ohnlp.medkat.common;

import java.io.File;
import java.io.FileNotFoundException;

public class PathFinder
{

    public static File findFile (String fName)
        throws FileNotFoundException
    {
        File file = new File(fName);
        if (! file.exists ())
        {
            String classPath = System.getProperty("java.class.path",".");
            //System.err.println("CLASSPATH: " + classPath);
            String [] pathElements = classPath.split (File.pathSeparator);
            int i = 0;
            boolean found = false;
            while ((i < pathElements.length) && !found)
            {
                file = new File(pathElements[i], fName);
                //System.err.println("LOOKING FOR FILE: " + file.getAbsolutePath ());
                found = file.exists ();
                if (found)
                {
                    System.err.println("FILE FOUND: " + file.getAbsolutePath ());
                }
                i += 1;
            }
            if (! found)
            {
                System.err.println ("DICTIONARY NOT FOUND IN PATH!");
                throw new FileNotFoundException ();
            }
        }
        return file;
    }
}
