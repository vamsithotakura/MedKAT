package org.ohnlp.medkat.taes.medTermStemmer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;


import org.ohnlp.medkat.common.PathFinder;
import org.ohnlp.medkat.conceptMapper.support.stemmer.Stemmer;

public class MedTermStemmer
        implements Stemmer
{

    private static Map<String, String> terms = new HashMap<String, String>();
    
    public MedTermStemmer ()
    {
        super();
    }

    public String stem (String token)
    {

        String term = terms.get (token);
        
        return (term == null) ? token : term;

    }

    public void initialize (String dictionary) throws FileNotFoundException, ParseException
    {
        File dictFile = PathFinder.findFile (dictionary);
        BufferedReader reader = new BufferedReader (new InputStreamReader (new FileInputStream (dictFile)));
        
        int line = 0;
        String input;
        try
        {
            //System.err.println ("Loading MedTermStemmer dictionary '" + dictionary + "'");
            while ((input = reader.readLine ()) != null)
            {
                String [] inputStemAndUnstemmed = input.split (":");
                if (inputStemAndUnstemmed.length == 2)
                {
                    String stem = inputStemAndUnstemmed[0].trim ();
                    String [] unstemmed = inputStemAndUnstemmed[1].split (",");
                    for (int i = 0; i < unstemmed.length; i++)
                    {
                        terms.put (unstemmed[i].trim (), stem);
                        //System.err.println ("Adding '" + unstemmed[i].trim () + "' -> '" + stem + "'");
                    }
                }
                else if (input.trim().length () != 0)
                {
                    System.err.println ("Error parsing file: " + dictionary + ", at line: " + line);
                    System.err.println ("\t failed on text: '" + input + "'");
                    throw new ParseException ("Error parsing file: " + dictionary, line);
                }
                line += 1;
            }
        }
        catch (IOException e)
        {
            throw new ParseException ("Error parsing file: " + dictionary, line);
        }
        
    }

}
