/*
 * $Id$
 */

package org.ohnlp.medkat.taes.subsectionDetector;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import org.apache.uima.analysis_engine.ResultSpecification;
import org.apache.uima.analysis_engine.annotator.AnnotatorConfigurationException;
import org.apache.uima.analysis_engine.annotator.AnnotatorContext;
import org.apache.uima.analysis_engine.annotator.AnnotatorContextException;
import org.apache.uima.analysis_engine.annotator.AnnotatorInitializationException;
import org.apache.uima.analysis_engine.annotator.AnnotatorProcessException;
import org.apache.uima.analysis_engine.annotator.JTextAnnotator_ImplBase;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.jcas.JFSIndexRepository;
import org.apache.uima.jcas.JCas;
import org.ohnlp.medkat.taes.sectionFinder.SectionAnnotation;


/*
 * @version $Revision$
 *
 * @author Michael Tanenblatt <mtan@us.ibm.com>
 * @author (c) Copyright 2005 IBM
 * @author All Rights Reserved
 */
public class SubsectionDetector
        extends JTextAnnotator_ImplBase
{

    /**
     * separates the prefix region from the content region of the SubHeading
     */
    private static final char PREFIX_CONTENT_SEPARATOR = ':';
    private static final Pattern prefixPattern = Pattern.compile (".*" + PREFIX_CONTENT_SEPARATOR + "\\s");
    private static final String RANGE_INDICATOR = "-";

    public class MatchedSubsection
    {
        private int sectionNumber;
        private int begin;
        private int end;
        
        
        /**
         * @param sectionNumber
         * @param begin
         * @param end
         */
        public MatchedSubsection (int sectionNumber, int begin, int end)
        {
            super ();
            this.sectionNumber = sectionNumber;
            this.begin = begin;
            this.end = end;
        }
        public int getBegin ()
        {
            return begin;
        }
        public void setBegin (int begin)
        {
            this.begin = begin;
        }
        public int getEnd ()
        {
            return end;
        }
        public void setEnd (int end)
        {
            this.end = end;
        }
        public int getSectionNumber ()
        {
            return sectionNumber;
        }
        public void setSectionNumber (int sectionNumber)
        {
            this.sectionNumber = sectionNumber;
        }
    }
    
    
    Pattern [] patterns;
    
    public void initialize (AnnotatorContext annotatorContext)
        throws AnnotatorInitializationException,
            AnnotatorConfigurationException
    {
        super.initialize (annotatorContext);
        
        try
        {
            String[] patternStrings = (String[]) getContext().getConfigParameterValue("Patterns");
            patterns = new Pattern [patternStrings.length];
            for (int i = 0; i < patternStrings.length; i++)
            {
                patterns[i] = Pattern.compile (patternStrings[i], Pattern.MULTILINE);
            }
        }
        catch (AnnotatorContextException e)
        {
            e.printStackTrace();
            throw new AnnotatorInitializationException ();
        }
    }
    
    
    /* (non-Javadoc)
     * @see org.apache.uima.analysis_engine.annotator.JTextAnnotator#process(org.apache.uima.jcas.JCas, org.apache.uima.analysis_engine.ResultSpecification)
     */
    public void process (JCas jcas, ResultSpecification resultSpecification)
        throws AnnotatorProcessException
    {
        JFSIndexRepository indexes = jcas.getJFSIndexRepository ();
        FSIndex sectionIndex = indexes.getAnnotationIndex (SectionAnnotation.typeIndexID);
        FSIterator sectionIterator = sectionIndex.iterator();
        
        int maxSubsectionNumber = 1;
        int maxSubsectionBegin = 0;
        int maxSubsectionEnd = 0;

        // process all sections
        while (sectionIterator.hasNext ())
        {
            SectionAnnotation section = (SectionAnnotation) sectionIterator.next();
            SubHeading newSubsection = null;
            
            maxSubsectionBegin = section.getBegin ();
            maxSubsectionEnd = section.getEnd ();

            int sectionEnd = section.getEnd();
            ArrayList<?> [] matches = findSectionMatches (jcas, section);
            MatchedSubsection [] bestMatch = chooseBestMatch (matches);
            
            if (bestMatch != null)
            {
                int end = sectionEnd;
                int prevBegin = -1;

                for (int i = bestMatch.length - 1; i >= 0; i--)
                {
                    MatchedSubsection matched = bestMatch[i];
                    if ((prevBegin != -1) && (prevBegin != matched.getBegin()))
                    {
                        end = prevBegin;
                    }
                    prevBegin = matched.getBegin();
                    String sectionAsString = "" + matched.getSectionNumber();
                    if (matched.getSectionNumber () > maxSubsectionNumber)
                    {
                        maxSubsectionNumber = matched.getSectionNumber ();
                        maxSubsectionBegin = matched.getBegin ();
                        maxSubsectionEnd = matched.getEnd ();                        
                    }
                    int prefixEnd = matched.getBegin() + sectionAsString.length();
                    newSubsection = makeSubsectionAnnotation (jcas, matched.getSectionNumber(), matched.getBegin(), end);
                    setTextRegions (newSubsection, prefixEnd, end);
                }
            }
            // if no subsection, consider the whole section to be have a single subsection
            else
            {
                int prefixEnd = section.getBegin() + section.getHeaderText().length() + 1;
                newSubsection = makeSubsectionAnnotation (jcas, 1, prefixEnd - 1, sectionEnd);
                //setTextRegions (newSubsection, prefixEnd, sectionEnd);
                newSubsection.setContentBegin(prefixEnd + 1);
                //System.err.println("Setting regions, no delimiter");
                newSubsection.setPrefix("");
                //System.err.println ("Prefix: '" + newSubsection.getPrefix() + "'");
                newSubsection.setContent(newSubsection.getCoveredText());
                //System.err.println ("Content: '" + newSubsection.getContent() + "'");
            }
        }
        MaxSubsectionIndicator maxsub = new MaxSubsectionIndicator (jcas);
        maxsub.setBegin (maxSubsectionBegin);
        maxsub.setEnd (maxSubsectionEnd);
        maxsub.setValue (maxSubsectionNumber);
        maxsub.addToIndexes ();
        //System.err.println("MAXSUBSECTION #: " + maxSubsectionNumber);
    }

        /**
     * @param newSubsection
     * @param prefixEnd
     * @param subSectionEnd
     */
    private void setTextRegions (SubHeading newSubsection, int prefixEnd, int subSectionEnd)
    {
        int headerBegin = prefixEnd + 1;
        if ((newSubsection != null) && (headerBegin < subSectionEnd))
        {
            Matcher prefixMatcher = prefixPattern.matcher (newSubsection.getCoveredText());
            if (prefixMatcher.find ())
            {
                int delimiter = prefixMatcher.group().length ();
                //System.err.println ("Computed Prefix: '" + prefixMatcher.group() + "'");
                newSubsection.setContentBegin(headerBegin + delimiter);
                
                //System.err.println("Setting regions, headerBegin: " + headerBegin + ", delimiter: " + delimiter);
//                newSubsection.setPrefix(newSubsection.getCoveredText().substring(headerBegin - newSubsection.getBegin(), delimiter + 1));
                newSubsection.setPrefix(newSubsection.getCoveredText().substring(headerBegin - newSubsection.getBegin(), delimiter));
                //System.err.println ("Prefix: '" + newSubsection.getPrefix() + "'");

                newSubsection.setContent(newSubsection.getCoveredText().substring(delimiter));
                //System.err.println ("Content: '" + newSubsection.getContent() + "'");
            }
        }
    }

    private SubHeading makeSubsectionAnnotation (JCas jcas, int subsectionNumber, int begin, int end)
        {
            //System.err.println ("Making subSection, #:" + subsectionNumber + ", begin: " + begin + ", end: " + end);
            SubHeading annotation = new SubHeading (jcas);
            annotation.setSubSectionNumber(subsectionNumber);
            annotation.setBegin(begin);
            annotation.setEnd (end);
            annotation.addToIndexes();
            return annotation;
        }
    /**
     * @param jcas
     * @param section
     * @return an array (one entry for each pattern)  of ArrayList, each containing matches for that pattern
     * @throws AnnotatorProcessException
     */
    private ArrayList<?> [] findSectionMatches (JCas jcas, SectionAnnotation section)
            throws AnnotatorProcessException
    {
        ArrayList<?> [] matches = new ArrayList<?> [patterns.length];
        int begin = section.getBegin();
        
        String sectionText = section.getCoveredText();
        
        for (int i = 0; i < patterns.length; i++)
        {
            ArrayList<MatchedSubsection> currentPatternMatches = new ArrayList<MatchedSubsection> ();
            matches[i] = currentPatternMatches;
            Matcher matcher = patterns[i].matcher(sectionText);
            while (matcher.find())
            {
                //int sectionNumber = 1;
                
                // if has section number, use it
                if (matcher.groupCount() > 0)
                {
                    //System.err.println ("GroupCount: " + matcher.groupCount() + ", matched: " + matcher.group(0));
                    boolean inRange = false;
                    int prevSectionNumber = -1;
                    for (int j = 1; j <= matcher.groupCount(); j++)
                    {
                        String matched = matcher.group(j);
                        //System.err.println ("group[" + j + "]: " + matcher.group(j));
                        if (matched != null)
                        {
                            if (matched.equals(RANGE_INDICATOR))
                            {
                                //System.err.println("IN RANGE");
                                inRange = true;
                            }
                            else if (inRange && (prevSectionNumber > 0))
                            {
                                int finalSectionNumber = Integer.parseInt(matched);
                                
                                // already created one, so skip "prevSectionNumber"
                                for (int sectionNumber = prevSectionNumber + 1; sectionNumber <= finalSectionNumber; sectionNumber++)
                                {
                                    //System.err.println("Creating section: " + sectionNumber);
                                    prevSectionNumber = newMatchedSubsection (currentPatternMatches, sectionNumber, begin + matcher.start (), 
                                                                              begin + matcher.end ());
                                }
                                inRange = false;
                            }
                            else
                            {
                                //System.err.println("Matched: " + matched + ", indexof comma: " + matched.indexOf(','));
                                if (matched.indexOf(',') != -1)
                                {
                                    String [] sections = matched.split(",");
                                    //System.err.println("sections.length: " + sections.length);
                                    for (int k = 0; k < sections.length; k++)
                                    {
                                        String potentialSectionNumber = sections[k].trim();
                                        //System.err.println("potentialSectionNumber: " + potentialSectionNumber);
                                        if (potentialSectionNumber.length() > 0)
                                        {
                                            int sectionNumber = Integer.parseInt(potentialSectionNumber);
                                            prevSectionNumber = newMatchedSubsection (currentPatternMatches, sectionNumber, begin + matcher.start (), 
                                                                                      begin + matcher.end ());
                                        }
                                    }
                                }
                                else
                                {
                                    String potentialSectionNumber = matched.trim();
                                    //System.err.println("potentialSectionNumber: " + potentialSectionNumber);
                                    if (potentialSectionNumber.length() > 0)
                                    {
                                        //System.err.println("Using Matched: " + matched);
                                        int sectionNumber = 0;
                                        
                                        if (potentialSectionNumber.matches("\\d+"))
                                        {
                                            sectionNumber = Integer.parseInt(potentialSectionNumber);
                                        }
                                        else if (potentialSectionNumber.matches("[A-Z]"))
                                        {
                                            sectionNumber = potentialSectionNumber.charAt(0) - 'A' + 1;
                                        }
                                        else if (potentialSectionNumber.matches("[a-z]"))
                                        {
                                            sectionNumber = potentialSectionNumber.charAt(0) - 'a' + 1;
                                        }
                                        // no zeros--must be some kind of weird layout problem, so skip
                                        if (sectionNumber > 0)
                                        {
                                            prevSectionNumber = newMatchedSubsection (currentPatternMatches, sectionNumber, begin + matcher.start (), 
                                                                                      begin + matcher.end ());
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return matches;
    }
    
    private int newMatchedSubsection (ArrayList<MatchedSubsection> currentPatternMatches, int sectionNumber, int begin, int end)
    {
        //System.err.println ("+++Adding subsection number: " + sectionNumber);
        currentPatternMatches.add(new MatchedSubsection (sectionNumber,
                                                         begin, 
                                                         end));
        return sectionNumber;
    }

    MatchedSubsection [] chooseBestMatch (ArrayList<?> [] matches)
    {
        MatchedSubsection [] returnVal = null;
        
        if (matches != null)
        {
            for (int i = 0; i < matches.length; i++)
            {
                if (((returnVal == null) && (matches[i].size () > 0)) ||
                    ((returnVal != null) && (returnVal.length < matches[i].size ())))
                {
                    returnVal = (MatchedSubsection []) matches[i].toArray (new MatchedSubsection [matches[i].size ()]);
                }
            }
        }
        return returnVal;
    }
}
