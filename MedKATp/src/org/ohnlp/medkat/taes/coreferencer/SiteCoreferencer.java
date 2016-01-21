package org.ohnlp.medkat.taes.coreferencer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.ohnlp.medkat.taes.conceptMapper.DictTerm;
import org.ohnlp.medkat.taes.subsectionDetector.SubHeading;


public class SiteCoreferencer
        extends Coreferencer
{
    /*String findCorefKey (DictTerm term)
    {
        if (isGenericTerm (term))
        {
            return GENERIC_TERM;
        }
        else if (isICDO (term.getAttributeType ()))
        {
            int pos = term.getAttributeValue ().indexOf ('.');
            if (pos == -1)
            {
                return term.getAttributeValue ();
            }
            else
            {
                return term.getAttributeValue ().substring (0, pos);
            }
        }
        return term.getAttributeValue();
    }*/

    void collectCorefsForSection (SubHeading subsection, List<DictTerm> sortedTerms)
    {
        Map<String, Collection<DictTerm>> corefsMap = corefsForSection[subsection.getSubSectionNumber ()];
        Map<String, Collection<DictTerm>> generics = new HashMap<String, Collection<DictTerm>> ();
        
        Iterator<DictTerm> termIter = sortedTerms.iterator();
        //System.err.println ("SUBSECTION: " + subsection.getSubSectionNumber() + ", begin: " + subsection.getBegin() + ", end: " + subsection.getEnd());
        while (termIter.hasNext())
        {
            DictTerm term = termIter.next();
            //System.err.println ("TERM: " + term.getCoveredText() + ", begin: " + term.getBegin() + ", end: " + term.getEnd());
            if ((term.getBegin() >= subsection.getBegin()) && (term.getEnd() <= subsection.getEnd()))
            {
                //System.err.println ("ADDING TERM: " + term.getCoveredText() + " for subsection: " + subsection.getSubSectionNumber());
                if (isGeneric (term))
                {
                    addTermToMap(term.getAttributeValue(), term, generics);
                }
                else
                {
                    addTermToMap(term.getAttributeValue(), term, corefsMap);
                }
            }
        }
        
        Set<String> genericsUsed = new HashSet<String> ();
        Iterator<String> corefsIterator = corefsMap.keySet().iterator();
        while (corefsIterator.hasNext())
        {
            String code = corefsIterator.next();
            String genericCode = code.replaceAll("\\.\\d+$", ".9");
            Collection<?> genericsForThisCode = generics.get(genericCode);
            if (genericsForThisCode != null)
            {
                genericsUsed.add(genericCode);
                Iterator<?> genIter = genericsForThisCode.iterator();
                while (genIter.hasNext())
                {
                    addTermToMap(code, (DictTerm) genIter.next(), corefsMap);
                    //System.err.println ("Adding item with code: " + genericCode + " to items with code: " + code);
                }
            }
        }
        // make sure that all items with generic codes have been added to non-generic items, otherwise must have new entries for them
        Set<String> remainingGenerics = generics.keySet();
        remainingGenerics.removeAll(genericsUsed);
        if (! remainingGenerics.isEmpty())
        {
             Iterator<String> remainingIter = remainingGenerics.iterator();
             while (remainingIter.hasNext())
             {
                 String key = remainingIter.next();
                 //System.err.println ("Adding unused item with code: " + key);
                 corefsMap.put(key, generics.get(key));
             }
        }
        
    }

    private void addTermToMap (String code, DictTerm term, Map<String, Collection<DictTerm>> termMap)
    {
        Collection<DictTerm> items = termMap.get(code);
        if (items == null)
        {
            items = new ArrayList<DictTerm>();
            termMap.put(code, items);
        }
        items.add(term);
    }

    private boolean isGeneric (DictTerm term)
    {
        if (isICDO(term.getAttributeType()))
        {
            if (term.getAttributeValue().endsWith(".9"))
            {
                return true;
            }
        }
        return false;
    }

    
    
}
