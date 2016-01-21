package org.ohnlp.medkat.taes.dictTermProcessor;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


import org.apache.uima.analysis_engine.ResultSpecification;
import org.apache.uima.analysis_engine.annotator.AnnotatorConfigurationException;
import org.apache.uima.analysis_engine.annotator.AnnotatorContext;
import org.apache.uima.analysis_engine.annotator.AnnotatorInitializationException;
import org.apache.uima.analysis_engine.annotator.AnnotatorProcessException;
import org.apache.uima.analysis_engine.annotator.Annotator_ImplBase;
import org.apache.uima.analysis_engine.annotator.TextAnnotator;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.TypeSystem;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.cas.CAS;
import org.apache.uima.jcas.JFSIndexRepository;
import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.ohnlp.medkat.common.ParameterProcessor;
import org.ohnlp.medkat.logger.Logger;
import org.ohnlp.medkat.taes.conceptMapper.DictTerm;

/**
 * @author mtan
 * 
 * iterate through spans (e.g. sentences) and remove all annotations of type "DictTerm" that are subsumed by others with the SemClass:
 *
 * Remove duplicates (duplicates are defined as having equal begin, end and semantic class)
 * Subsumption processing:
 *    Diagnosis only:
 *      if term is one of the set of potential co-referring diagnoses, specified by the parameter "GenericTermCodes"
 *      and wholly contains another diagnosis, the co-referring diagnosis is removed
 * 
 *    If "comma overrides subsumption" parameter is not set, or text containing term does not contain a comma:
 *      if term is wholly contained within another term, remove the contained term
 */  
public abstract class DictTermProcessorBase
    extends Annotator_ImplBase
    implements TextAnnotator
{
    
    protected class NumTokensComparator
    implements Comparator<DictTerm>
{
    // swap meaning of result to sort into descending order:
    // Returns a negative integer:  first argument is greater than the second argument
    //           zero               first argument equals the second argument
    //           positive integer:  first argument is less than the second argument
    public int compare (DictTerm left, DictTerm right)
    {
        FSArray leftTokens = left.getMatchedTokens ();
        FSArray rightTokens = right.getMatchedTokens ();
        
        if ((leftTokens == null) && (rightTokens == null))
        {
            return 0;
        }
        else if (leftTokens == null)
        {
            return 1;
        }
        else if (rightTokens == null)
        {
            return -1;
        }
        else
        {
            return (right.getMatchedTokens ().size () - left.getMatchedTokens ().size ());
        }
    }
}

    private JCas jcas;
    private Logger logger;

    /**
     * name of parameter that contains the name of the span annotaion name (e.g., SentenceAnnotation)
     */
    private static final String PARAM_ENCLOSINGSPAN = "EnclosingSpan";
    private String enclosingSpanTypeName;
    private Type enclosingSpanType;

    
    /**
     * name of parameter that contains a bit mask of allowed markers (i.e., if bit is set in DictTerm, it is still OK for consideration).
     * a "0" in a position means the bit is not allowed to be set
     */
    private static final String PARAM_ALLOWEDMARKERSMASK = "AllowedMarkersMask";
    private int allowMask = 0;
    private AnnotationIndex dictTermIndex;
    
    
    /**
     * name of parameter that contains a set of semclasses for consideration.
     */
    private static final String PARAM_SEMCLASSES = "SemanticClasses";
    private Set<String> semanticClasses = new HashSet<String> ();
    


    public JCas getJCas ()
    {
        return jcas;
    }


    public void setJCas (JCas jcas)
    {
        this.jcas = jcas;
    }


    public Logger getLogger ()
    {
        return logger;
    }


    public void setLogger (Logger logger)
    {
        this.logger = logger;
    }


    public int getAllowMask ()
    {
        return allowMask;
    }


    public void setAllowMask (int allowMask)
    {
        this.allowMask = allowMask;
    }



    public AnnotationIndex getDictTermIndex ()
    {
        return dictTermIndex;
    }


    public void setDictTermIndex (AnnotationIndex dictTermIndex)
    {
        this.dictTermIndex = dictTermIndex;
    }

    public boolean isOK_SemanticClass (String semClass)
    {
        return semanticClasses.contains (semClass);
    }
    

    public void initialize (AnnotatorContext annotatorContext) throws AnnotatorInitializationException, AnnotatorConfigurationException
    {
        super.initialize(annotatorContext);
        // Process configration parameters
        try
        {
            logger = new Logger (annotatorContext.getLogger ());

            enclosingSpanTypeName = (String) getContext ().getConfigParameterValue (PARAM_ENCLOSINGSPAN);
            Integer allowMaskObj = (Integer) getContext ().getConfigParameterValue (PARAM_ALLOWEDMARKERSMASK);
            semanticClasses = ParameterProcessor.paramArrayToSet (annotatorContext, PARAM_SEMCLASSES, false);
            
            
            if (allowMaskObj != null)
            {
                setAllowMask (allowMaskObj.intValue ());
            }
        }
        catch (Exception e)
        {
            throw new AnnotatorConfigurationException (e);
        }
    }


    public void typeSystemInit (TypeSystem typeSystem) throws AnnotatorInitializationException, AnnotatorConfigurationException
    {
        super.typeSystemInit(typeSystem);
        enclosingSpanType = typeSystem.getType (enclosingSpanTypeName);
    }


    public void process (CAS tcas, ResultSpecification resultSpecification)
        throws AnnotatorProcessException
    {

        try
        {
            setJCas (tcas.getJCas ());
            getLogger ().setupDocument (getJCas ());

            JFSIndexRepository indexes = getJCas ().getJFSIndexRepository ();
            setDictTermIndex ((AnnotationIndex) indexes.getAnnotationIndex (DictTerm.typeIndexID));
            FSIndex spanIndex = tcas.getAnnotationIndex (enclosingSpanType);
            FSIterator spanIterator = spanIndex.iterator ();

            processSemClasses (getDictTermIndex (), spanIterator);
        }
        catch (Exception e)
        {
            throw new AnnotatorProcessException (e);
        }

    }



    private Map<String, ArrayList<DictTerm>> collectTermsBySemClass (Annotation span, AnnotationIndex dictTermIndex)
    {
        //System.err.println("collectTermsBySemClass");
        HashMap<String, ArrayList<DictTerm>> result = new HashMap<String, ArrayList<DictTerm>> ();

        FSIterator dictTermIter = dictTermIndex.subiterator (span);

        //System.err.println ("Tokens:");

        // get all tokens for the specified block
        while (dictTermIter.hasNext ())
        {
            DictTerm dictTerm = (DictTerm) dictTermIter.next ();
            if (isAllowed (getAllowMask (), dictTerm))
            {
                String semClass = dictTerm.getSemClass ();
                if (isOK_SemanticClass (semClass))
                {
                    ArrayList<DictTerm> terms = result.get (semClass);
                    if (terms == null)
                    {
                        terms = new ArrayList<DictTerm> ();
                    }
                    terms.add (dictTerm);
                    //System.err.println("Adding term for SemClass = '" + semClass + "'");
                    result.put (semClass, terms);
                }
            }
        }
                
        return result;
    }

    protected void processSemClasses (AnnotationIndex termIndex, FSIterator spanIterator) throws AnnotatorProcessException
    {
        while (spanIterator.hasNext ())
        {
            Annotation span = (Annotation) spanIterator.next ();
            Map<String, ArrayList<DictTerm>> termsBySemClass = collectTermsBySemClass (span, termIndex);
            // sort by length
            Iterator<String> keyIter = termsBySemClass.keySet ().iterator ();
            while (keyIter.hasNext ())
            {
                String semClass = keyIter.next ();
                ArrayList<DictTerm> terms = termsBySemClass.get (semClass);
                
                processSemclass (span, semClass, terms);
            }
        }
    }

    /**
     * for each span, called once for each semclass
     * @param semClass the semantic class
     * @param terms the set of terms within the current span with the given semclass
     * @throws AnnotatorProcessException
     */
    abstract protected void processSemclass (Annotation span, String semClass, ArrayList<DictTerm> terms) throws AnnotatorProcessException;

    // return true if no disallowed bits are set
    protected boolean isAllowed (int allowMask, DictTerm term)
    {
        //System.err.println ("isAllowed(), allowMask: " + allowMask + ", term text: " + term.getCoveredText() + ", term dictCanon: " + term.getDictCanon() + ", term.getMarked: " + term.getMarked());
        return (((~allowMask) & term.getMarked ()) == 0);
    }



    protected static boolean subsumes (int currentBegin, int currentEnd, int toCompareBegin, int toCompareEnd)
    {
        return ((toCompareBegin >= currentBegin) && (toCompareEnd <= currentEnd));
    }

}
