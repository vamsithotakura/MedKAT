package org.ohnlp.medkat.taes.coreferencer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.ohnlp.medkat.taes.conceptMapper.DictTerm;
import org.ohnlp.medkat.taes.subsectionDetector.SubHeading;


public class DiagnosisCoreferencer
        extends Coreferencer
{
    
    protected static final String BENIGN_TERM = "BENIGN";
    protected static final String METASTATIC_TERM = "METASTATIC";
    protected static final String OTHER_TERM = "OTHER";

    protected static final String GENERIC_TERM = "GENERIC";


    // generic terms (e.g., "tumor", "cancer", carcinoma") cannot be coreferenced with benign diagnoses
    // 
    protected void collectCorefsForSection (SubHeading subsection, List<DictTerm> sortedTerms)
    {
        Map<String, Collection<DictTerm>> corefsMap = corefsForSection[subsection.getSubSectionNumber ()];
        
        Map<String, String> previousByType = new HashMap<String, String> ();
        String previousType = OTHER_TERM;
        
        Iterator<DictTerm> termIter = sortedTerms.iterator();
        while (termIter.hasNext())
        {
            DictTerm term = termIter.next();
            //System.err.println ("DIAGNOSIS COREF, term: " + term.getAttributeValue());
            if ((term.getBegin() >= subsection.getBegin()) && (term.getEnd() <= subsection.getEnd()))
            {
    
                String key = findCorefKey (term);       // either the code or a "generic" indicator
                //System.err.println ("findCorefKey: " + key);
                String termType = findTermType (term);  // indicator of "benign", "metastatic", etc.
                //System.err.println ("findTermType: " + termType);
    
                if (key == genericKey())
                {
                    //System.err.println ("key == genericKey()");
                    key = previousByType.get(termType.equals(OTHER_TERM) ? previousType : termType);
                    //System.err.println ("new key: " + key);
                    if (key == null)
                    {
                        key = genericKey ();
                    }
                }
                else
                {
                    previousByType.put(termType, key);
                    previousType = termType;
                }
    
                ArrayList<DictTerm> corefs = (ArrayList<DictTerm>) corefsMap.get (key);
    
                if (corefs == null)
                {
                    corefs = new ArrayList<DictTerm> ();
                }
                corefs.add (term);
                corefsMap.put (key, corefs);
                //System.err.println ("adding coref: " + term.getAttributeValue() + " to type " + key);
            }
        }
    }

    
    
    private String findTermType (DictTerm term)
    {
        if (isBenignCode (term))
        {
            return BENIGN_TERM;
        }
        else if (isMetastaticCode (term))
        {
            return METASTATIC_TERM;
        }
        else
        {
            return OTHER_TERM;
        }
    }



    protected boolean isBenignCode (DictTerm term)
    {
        if (term.getAttributeType().equals("ICDO"))
        {
            if (baseKey(term).endsWith("/0"))
            {
                return true;
            }
        }
        else
        {
            System.err.println("DiagnosisCoreferencer.isBenignCode(): UNKNOWN CODING SYSTEM: " + term.getAttributeType());
        }
        return false;
    }



    protected boolean isMetastaticCode (DictTerm term)
    {
        if (term.getAttributeType().equals("ICDO"))
        {
            if (term.getAttributeValue().endsWith("/6"))
            {
                return true;
            }
        }
        else
        {
            System.err.println("DiagnosisCoreferencer.isMetastaticCode(): UNKNOWN CODING SYSTEM: " + term.getAttributeType());
        }
        return false;
    }



    protected boolean isGenericTerm (DictTerm term)
    {
        return (genericTermCodes.contains (term.getAttributeValue ()));
    }


    private String findCorefKey (DictTerm term)
    {
        if (isGenericTerm (term))
        {
            return genericKey ();
        }
        else
        {
            return baseKey(term);
        }
    }



    private String baseKey (DictTerm term)
    {
        return term.getAttributeValue();
    }
    

    private String genericKey ()
    {
        return GENERIC_TERM;
    }


}
