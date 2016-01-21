package org.ohnlp.medkat.taes.grossDescription;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;


import org.apache.uima.analysis_engine.ResultSpecification;
import org.apache.uima.analysis_engine.annotator.AnnotatorConfigurationException;
import org.apache.uima.analysis_engine.annotator.AnnotatorContext;
import org.apache.uima.analysis_engine.annotator.AnnotatorInitializationException;
import org.apache.uima.analysis_engine.annotator.AnnotatorProcessException;
import org.apache.uima.analysis_engine.annotator.JTextAnnotator_ImplBase;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.Feature;
import org.apache.uima.cas.TypeSystem;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.jcas.JFSIndexRepository;
import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.ohnlp.medkat.common.ComparableArray;
import org.ohnlp.medkat.common.DocumentURI;
import org.ohnlp.medkat.common.FeatureConstrainedIterator;
import org.ohnlp.medkat.common.UIMAAnnotationOffsetComparator;
import org.ohnlp.medkat.common.UIMAAnnotationUtils;
import org.ohnlp.medkat.logger.Logger;
import org.ohnlp.medkat.scr.types.SCRGrossDescription;
import org.ohnlp.medkat.scr.types.SCRGrossDescriptionPart;
import org.ohnlp.medkat.taes.npMerger.NPCombinedAnnotation;
import org.ohnlp.medkat.taes.sectionFinder.GrossDescriptionAnnotation;
import org.ohnlp.medkat.taes.subsectionDetector.SubHeading;
import org.ohnlp.medkat.taes.syntacticUnitFinder.SyntacticUnit;

import uima.tt.SentenceAnnotation;


public class GrossDescriptionAnnotator
        extends JTextAnnotator_ImplBase
{

    List<String> m_site_fragments_features_names = new ArrayList<String>();
    List<Feature> m_site_fragments_features = new ArrayList<Feature>();

    List<String> m_site_typeNames = new ArrayList<String>();
    List<Integer> m_site_typeIndexIDs = new ArrayList<Integer>();
    
    List<String> m_size_typeNames = new ArrayList<String>();
    List<Integer> m_size_typeIndexIDs = new ArrayList<Integer>();
    
    Class<? extends Annotation> m_sentenceClass;
    Class<? extends Annotation> m_tokenClass;
    Class<? extends Annotation> m_npClass;
    Class<? extends Annotation> m_npListClass;
    Class<? extends Annotation> m_nppClass;
    Class<? extends Annotation> m_npsClass;


    final static String PARAM_SENTENCECLASS = "SentenceClass";
    final static String PARAM_TOKENCLASS = "TokenClass";
    final static String PARAM_NPCLASS = "NPClass";
    final static String PARAM_NPLISTCLASS = "NPListClass";
    final static String PARAM_NPPCLASS = "NPPClass";
    final static String PARAM_NPSCLASS = "NPSClass";
    
    
    final static String PARAM_FRAGMENTSFEATURENAMES = "FragmentsFeatureNames";
    final static String PARAM_SITETYPES = "SiteTypeNames";
    final static String PARAM_SIZETYPES = "SizeTypeNames";

    public void initialize(AnnotatorContext ac)
    throws AnnotatorInitializationException, AnnotatorConfigurationException
    {
        Logger.getDefaultLogger().logInfo("initializing");
        
        super.initialize(ac);
        
        try {
            
            
            m_sentenceClass = UIMAAnnotationUtils.forName((String)ac.getConfigParameterValue(PARAM_SENTENCECLASS)); 
            m_tokenClass = UIMAAnnotationUtils.forName((String)ac.getConfigParameterValue(PARAM_TOKENCLASS)); 
            m_npClass = UIMAAnnotationUtils.forName((String)ac.getConfigParameterValue(PARAM_NPCLASS)); 
            m_npListClass = UIMAAnnotationUtils.forName((String)ac.getConfigParameterValue(PARAM_NPLISTCLASS)); 
            m_nppClass = UIMAAnnotationUtils.forName((String)ac.getConfigParameterValue(PARAM_NPPCLASS)); 
            m_npsClass = UIMAAnnotationUtils.forName((String)ac.getConfigParameterValue(PARAM_NPSCLASS)); 
            
            m_site_fragments_features_names.addAll(Arrays.asList((String[])ac.getConfigParameterValue(PARAM_FRAGMENTSFEATURENAMES)));
            m_site_typeNames.addAll(Arrays.asList((String[])ac.getConfigParameterValue(PARAM_SITETYPES)));
            m_size_typeNames.addAll(Arrays.asList((String[])ac.getConfigParameterValue(PARAM_SIZETYPES)));
            if (m_site_fragments_features_names.size() != m_site_typeNames.size()) {
                throw new AnnotatorInitializationException("Length of \"" + PARAM_FRAGMENTSFEATURENAMES +
                                                           "\" and \"" + PARAM_SITETYPES + "\" do not match",
                                                           new Object[] {m_site_fragments_features_names, m_site_typeNames}); 
            }

            SUBH_TAGS = new  Object [] {new Integer(5), SubHeading.class.getName()};
            SENT_TAGS = new  Object [] {new Integer(10), m_sentenceClass.getName()};
            PARN_TAGS = new  Object [] {new Integer(20), SyntacticUnit.class.getName()};
            NPCB_TAGS = new  Object [] {new Integer(30), NPCombinedAnnotation.class.getName()};
            NPPH_TAGS = new  Object [] {new Integer(40), m_nppClass.getName()};
            PSNP_TAGS = new  Object [] {new Integer(50), ParenSeparatedNPAnnotation.class.getName()};
            NPHR_TAGS = new  Object [] {new Integer(60), m_npClass.getName()};
            
            GRDS_TAGS = new  Object [] {new Integer(70), SCRGrossDescriptionPart.class.getName()};
            SITE_TAGS = new  Object [] {new Integer(80), "site"};
            SIZE_TAGS = new  Object [] {new Integer(90), "size"};

        }
        catch (Exception e) {
            throw new AnnotatorInitializationException(e); 
        }
    }
    
    public void typeSystemInit (TypeSystem type_system)
        throws AnnotatorConfigurationException,
            AnnotatorInitializationException
    {
        try {
            for (int i = 0; i < m_site_typeNames.size(); ++i) {
                // System.out.println("Site type: " + site_types[i]);
                String tname = m_site_typeNames.get(i);
                m_site_fragments_features.add(type_system.getFeatureByFullName(tname + ":" + m_site_fragments_features_names.get(i)));
                m_site_typeIndexIDs.add(new Integer(UIMAAnnotationUtils.getTypeIndexId(tname)));
            }
            for (int i = 0; i < m_size_typeNames.size(); ++i) {
                // System.out.println("Size type: " + size_types[i]);
                String tname = m_size_typeNames.get(i);
                m_size_typeIndexIDs.add(new Integer(UIMAAnnotationUtils.getTypeIndexId(tname)));
            }
        }
        catch (Exception e) {
            throw new AnnotatorInitializationException(e);
        }
    }

    private List<SCRGrossDescriptionPart> createGDPAnnotation(JCas jcas, Annotation enclosing, List<Annotation> site_groups, List<Annotation> sizes)
    {
        List<SCRGrossDescriptionPart> result = new ArrayList<SCRGrossDescriptionPart>();
        for (Iterator<Annotation> it = sizes.iterator(); it.hasNext();) {
            result.add(createGDPAnnotation(jcas, enclosing, site_groups, it.next()));
        }
        return result;
    }
    
    private List<Annotation> getAllSites (List<Annotation> site_groups)
    {
        List<Annotation> sites = new ArrayList<Annotation>();
        for (Iterator<Annotation> it = site_groups.iterator(); it.hasNext();) {
            Annotation ann = (Annotation)it.next();
            if (ann instanceof AnatomicalSiteGroup) {
                UIMAAnnotationUtils.addFSArrayToAnnotations(sites, ((AnatomicalSiteGroup)ann).getSites());
            }
            else if (isValidSiteAnnotation(ann)){
                sites.add(ann);
            }
            else {
                System.err.println("WARNING: Unsupported annoation type given for site: " + 
                                    ann.getClass().getName() + ": " + ann.getBegin() + "|" + ann.getEnd());
            }
        }
        return sites;
    }
    
    private SCRGrossDescriptionPart createGDPAnnotation(JCas jcas, Annotation enclosing, List<Annotation> site_groups, Annotation size)
    {
        // System.out.println("creating annotation part");
        List<Annotation> sites = getAllSites(site_groups);

//        for (Iterator it = sites.iterator(); it.hasNext();) {
//            Annotation ann = (Annotation)it.next();
//            System.out.println("\tsite:" + ann.getBegin() + "|" + ann.getEnd() +  ":" + ann.getCoveredText());
//        }
//        System.out.println("\tsize:" + ((null == size) ? "" : size.getBegin() + "|" + size.getEnd() +  ":" + size.getCoveredText()));

        SCRGrossDescriptionPart gdpa = new SCRGrossDescriptionPart(jcas, null == size ? 0 : size.getBegin(), null == size ? 0 : size.getEnd());
        gdpa.setSize(size);
        gdpa.setEnclosing(enclosing);
        UIMAAnnotationUtils.addAnnotationsToFSArray(jcas, gdpa, "Sites", sites, true);
        // gdpa.addToIndexes(); addAnnotationToFSArray will add it to index
        return gdpa;
    }
    
    private void addSitesToGDPAnnotation(JCas jcas, Annotation enclosing, SCRGrossDescriptionPart gdpa, List<Annotation> site_groups)
    {
        // System.out.println("changing:" + gdpa.getBegin() + "|" + gdpa.getEnd());
        
        List<Annotation> sites = getAllSites(site_groups);
//        for (Iterator it = sites.iterator(); it.hasNext();) {
//            Annotation ann = (Annotation)it.next();
//            System.out.println("\tsite:" + ann.getBegin() + "|" + ann.getEnd() +  ":" + ann.getCoveredText());
//        }
        
        gdpa.setEnclosing(enclosing);
        UIMAAnnotationUtils.addAnnotationsToFSArray(jcas, gdpa, "Sites", sites, true);
    }
    

    private ComparableArray createCA (Annotation ann, boolean begin, int priority, String tag)
    {
        if (begin) {
            return new ComparableArray(new Comparable[] {
                    new Integer(ann.getBegin()),
                    new Integer(priority),
                    new Integer(ann.getEnd()),
                    new String(tag + "_b")});
        }
        else {
            return new ComparableArray(new Comparable[] {
                    new Integer(ann.getEnd()),
                    new Integer(-priority),
                    new Integer(-ann.getBegin()),
                    new String(tag + "_e")});
        }
    }
    
    private void putCA (Map<ComparableArray, Annotation> offset2anns, Annotation ann, Object[] tags)
    {
        // System.err.println("adding " + ann.getClass().getName() + ": " + ann.getBegin() + "|" + ann.getEnd() + ": " + tags[0] + ": " + tags[1]);
        
        offset2anns.put(createCA(ann, true, ((Integer)tags[0]).intValue(), (String)tags[1]), ann);
        offset2anns.put(createCA(ann, false, ((Integer)tags[0]).intValue(), (String)tags[1]), ann);
    }

    private void putCA (Map<ComparableArray, Annotation> offset2anns, List<SCRGrossDescriptionPart> anns, Object[] tags)
    {
        for (Iterator<SCRGrossDescriptionPart> it = anns.iterator(); it.hasNext();) {
            putCA (offset2anns, it.next(), tags);
        }
    }

    private void removeCA (Map<ComparableArray, Annotation> offset2anns, Annotation ann, Object[] tags)
    {
        // System.err.println("removing " + ann.getClass().getName() + ": " + ann.getBegin() + "|" + ann.getEnd() + ": " + tags[0] + ": " + tags[1]);
        
        offset2anns.remove(createCA(ann, true, ((Integer)tags[0]).intValue(), (String)tags[1]));
        offset2anns.remove(createCA(ann, false, ((Integer)tags[0]).intValue(), (String)tags[1]));
    }

    void removeCA (Map<ComparableArray, Annotation> offset2anns, List<Annotation> anns, Object[] tags)
    {
        for (Iterator<Annotation> it = anns.iterator(); it.hasNext();) {
            removeCA (offset2anns, it.next(), tags);
        }
    }

    Object[] SUBH_TAGS;
    Object[] SENT_TAGS;
    Object[] PARN_TAGS;
    Object[] NPCB_TAGS;
    Object[] NPPH_TAGS;
    Object[] PSNP_TAGS;
    Object[] NPHR_TAGS;
    
    Object[] GRDS_TAGS;
    Object[] SITE_TAGS;
    Object[] SIZE_TAGS;
    
    private Map<ComparableArray, Annotation> createOrderedAnnotationSequence (JCas jcas, Annotation enclosing, Collection<Annotation> ignore_annotations)
    throws IllegalArgumentException, SecurityException, ClassNotFoundException, IllegalAccessException, NoSuchFieldException
    {
        JFSIndexRepository indxs = jcas.getJFSIndexRepository ();
        
        AnnotationIndex ann_ind = (AnnotationIndex)indxs.getAnnotationIndex(Annotation.typeIndexID);
        
        Map<ComparableArray, Annotation> offset2anns = new TreeMap<ComparableArray, Annotation>();
        Annotation prev_size_ann = null;
        
        for (Iterator<?> ann_it = ann_ind.subiterator(enclosing); ann_it.hasNext();) {
            Annotation ann = (Annotation)ann_it.next();
            if (m_sentenceClass.isInstance(ann)) {
                putCA(offset2anns, ann, SENT_TAGS);
            }
            else if (SubHeading.class.isInstance(ann)) {
                putCA(offset2anns, ann, SUBH_TAGS);
            }
            else if (SyntacticUnit.class.isInstance(ann)) {
                Annotation sent = FeatureConstrainedIterator.getEnclosingAnnotation(jcas, SentenceAnnotation.class, ann);
                if (null == sent) {
                    System.err.printf("WARNING: %s: %s (%d|%d) does not have enclosing sentence\n",
                            DocumentURI.get(jcas), ann.getClass().getName(), ann.getBegin(), ann.getEnd());
                }
                else {
                    putCA(offset2anns, ann, PARN_TAGS);
                }
            }
            else if (NPCombinedAnnotation.class.isInstance(ann)) {
                putCA(offset2anns, ann, NPCB_TAGS);
            }
            else if (m_nppClass.isInstance(ann)) {
                putCA(offset2anns, ann, NPPH_TAGS);
            }
            else if (m_npClass.isInstance(ann)) {
                putCA(offset2anns, ann, NPHR_TAGS);
            }
            else if (isValidSiteAnnotation(ann)) {
                putCA(offset2anns, ann, SITE_TAGS);
            }
            else if (isValidSizeAnnotation(ann)) {
                if (isValidSizeAnnotation(prev_size_ann) && isPartOfSameSize(jcas, prev_size_ann, ann)) {
                    removeCA(offset2anns, prev_size_ann, SIZE_TAGS);
                    ignore_annotations.add(prev_size_ann);
                }
                putCA(offset2anns, ann, SIZE_TAGS);
                prev_size_ann = ann;
            }
            ignore_annotations.addAll(removeInvalidSites(jcas, offset2anns));
        }
        isolateAnatomicalSiteGroups (jcas, enclosing, offset2anns); 
        return offset2anns;
    }

    private List<Annotation> removeInvalidSites (JCas jcas, Map<ComparableArray, Annotation> offset2ann)
    {
        List<Annotation> invalidSites = new ArrayList<Annotation>();
        List<Annotation> openSites = new ArrayList<Annotation>();
        for (Iterator<ComparableArray> it = offset2ann.keySet().iterator(); it.hasNext();) {
            ComparableArray ca = it.next();
            Annotation ann = offset2ann.get(ca);
            if (isValidSiteAnnotation(ann)) {
                if (((Integer)ca.m_src[0]).intValue() == ann.getBegin()) {
                    openSites.add(ann);
                }
                else { 
                    openSites.remove(ann);
                }
            }
            if (isValidSizeAnnotation(ann) && (((Integer)ca.m_src[0]).intValue() == ann.getBegin()))  {
                invalidSites.addAll(openSites);
            }
        }
        removeCA (offset2ann, invalidSites, SITE_TAGS);
        return invalidSites;
    }
    

    private void produceSiteGroups (JCas jcas, Collection<Annotation> all_groups, Map<ComparableArray, Annotation> offset2ann)
    {
        int b = 0, e = 0;
        List<Annotation> group = new ArrayList<Annotation>();
        for (Iterator<Annotation> it = all_groups.iterator(); it.hasNext();) {
            Annotation ann = it.next();
            if (group.isEmpty()) {
                group.add(ann);
                b = ann.getBegin();
                e = ann.getEnd();
            }
            else {
                if (ann.getBegin() == b) {
                    group.add(ann);
                    if (ann.getEnd() > e) {
                        e = ann.getEnd();
                    }
                }
                else if (ann.getBegin() > b) {
                    if (ann.getEnd() <= e) {
                        group.add(ann);
                    }
                    else {
                        AnatomicalSiteGroup asg = new AnatomicalSiteGroup(jcas); 
                        UIMAAnnotationUtils.addAnnotationsToFSArray(jcas, asg, "Sites", group, true);
                        removeCA(offset2ann, group, SITE_TAGS);
                        putCA(offset2ann, asg, SITE_TAGS);
                        group.clear();
                        group.add(ann);
                        b = ann.getBegin();
                        e = ann.getEnd();
                    }
                }
            }
        }
        if (!group.isEmpty()) {
            AnatomicalSiteGroup asg = new AnatomicalSiteGroup(jcas); 
            UIMAAnnotationUtils.addAnnotationsToFSArray(jcas, asg, "Sites", group, true);
            removeCA(offset2ann, group, SITE_TAGS);
            putCA(offset2ann, asg, SITE_TAGS);
        }
    }    
    
    private void isolateAnatomicalSiteGroups (JCas jcas, Annotation enclosing, Map<ComparableArray, Annotation> offset2ann)
    {
        Collection<Annotation> group_anns = new TreeSet<Annotation>(new UIMAAnnotationOffsetComparator());
        for (Iterator<ComparableArray> it = offset2ann.keySet().iterator(); it.hasNext();) {
            ComparableArray ca = it.next();
            Annotation ann = offset2ann.get(ca);
            if (isValidSiteAnnotation(ann) && (((Integer)ca.m_src[0]).intValue() == ann.getBegin())) {
                group_anns.add(ann);
            }
        }
        produceSiteGroups(jcas, group_anns, offset2ann);
    }
    

    private boolean isValidEnclosingAnnotation(Annotation ann, Object[] tags)
    {
        String cl = (String)tags[1];
        if (ann.getClass().getName().equals(cl)) {
            return true; 
        }
        else {
            return false;
        }
    }
    
    private static boolean isValidGDPAnnotation(Annotation ann)
    {
        return ann instanceof SCRGrossDescriptionPart;
    }
    
    private boolean isValidSiteAnnotation(Annotation ann)
    {
        if (null == ann) {
            return false;
        }
        
        if (!m_site_typeIndexIDs.contains(new Integer (ann.getTypeIndexID()))) {
            return false;
        }
        return true;
    }

    private boolean isASGroupAnyEnclosed (JCas jcas, AnatomicalSiteGroup asGroup, Annotation enclosing)
    throws IllegalArgumentException, SecurityException, IllegalAccessException, NoSuchFieldException
    {
        for (int i = 0; i < asGroup.getSites().size(); ++i) {
            if (isSiteAnyFragmentEnclosed(jcas, (Annotation)asGroup.getSites().get(i), enclosing)) {
                return true;
            }
        }
        return false;
    }

    private boolean isSiteAnyFragmentEnclosed (JCas jcas, Annotation site, Annotation enclosing)
    throws IllegalArgumentException, SecurityException, IllegalAccessException, NoSuchFieldException
    {
        int ind = m_site_typeIndexIDs.indexOf(new Integer(site.getTypeIndexID()));
        if (ind < 0) {
            throw new IllegalArgumentException("site object of illegal type: " + site.getType().getName());
        }
        Feature ff = m_site_fragments_features.get(ind);
        if (null == ff) {
            throw new NoSuchFieldException("no reqired fragment feature for type: " + site.getType().getName());
        }
        FSArray frgmnts = (FSArray)site.getFeatureValue(ff);
        if (null == frgmnts) {
            return (site.getBegin() >= enclosing.getBegin()) &&
                   (site.getEnd() <= enclosing.getEnd());  
        }
        
        for (int i = 0; i < frgmnts.size(); ++i) {
            Annotation frgmnt = (Annotation)frgmnts.get(i);
            if ((frgmnt.getBegin() >= enclosing.getBegin()) && (frgmnt.getEnd() <= enclosing.getEnd())) {
                return true;
            }
        }
        return false;
    }

    private boolean isValidSizeAnnotation (Annotation ann)
    {
        if (null == ann) {
            return false;
        }
        
        if (m_size_typeIndexIDs.contains(new Integer (ann.getTypeIndexID()))) {
            return true;
        }
        else {
            return false;
        }
    }
    
    private boolean isPartOfSameSize(JCas jcas, Annotation left, Annotation right)
    throws IllegalArgumentException, SecurityException, ClassNotFoundException, IllegalAccessException, NoSuchFieldException
    {
        if ((left.getBegin() <= right.getBegin()) && (left.getEnd() == right.getEnd())) {
            return true;
        }
        int cnt = 0;
        for (Iterator<?> it = FeatureConstrainedIterator.getEnclosedIterator(
                jcas, m_tokenClass, left.getEnd(), right.getBegin()); it.hasNext();) {
            if (cnt > 0) {
                return false;
            }
            String token = ((Annotation)it.next()).getCoveredText();
            if (token.equals("-") || token.equals ("to")) {
                cnt++;
            }
            else {
                return false;
            }
        }
        return true;
    }
    
    private class UIMABoundaryComparator implements Comparator<Object>
    {
        final boolean m_right;
        UIMABoundaryComparator(boolean right)
        {
            m_right = right;
        }

        public int compare (Object arg1, Object arg2)
        {
            Annotation o1 = (Annotation)arg1;
            Annotation o2 = (Annotation)arg2;
            
            return m_right ? o2.getEnd() - o1.getEnd() : o1.getBegin() - o2.getBegin();
        }
    }

    private List<Annotation> fitSizes (JCas jcas, Annotation enclosing, List<Annotation> sizes, List<SCRGrossDescriptionPart> matched_gdpas)
    throws IllegalArgumentException,
           SecurityException,
           ClassNotFoundException,
           IllegalAccessException,
           NoSuchFieldException
    {
        List<Annotation> result = new ArrayList<Annotation>();
        for (Iterator<Annotation> it = sizes.iterator(); it.hasNext();) {
            Annotation size = it.next();
            if (fitSize(jcas, enclosing, size, matched_gdpas)) {
                result.add(size);
            }
        }
        return result;
    }

    private boolean fitSize (JCas jcas, Annotation enclosing, Annotation size, List<SCRGrossDescriptionPart> matched_gdpas)
    throws IllegalArgumentException,
           SecurityException,
           ClassNotFoundException,
           IllegalAccessException,
           NoSuchFieldException
    {
        SCRGrossDescriptionPart prev_gdpa = null;
        SCRGrossDescriptionPart curr_gdpa = null;
        for (Iterator<?> it = FeatureConstrainedIterator.getEnclosedIterator(
                jcas, SCRGrossDescriptionPart.class, enclosing); it.hasNext();) {
            curr_gdpa = (SCRGrossDescriptionPart)it.next();
            if (size.getEnd() < curr_gdpa.getBegin()) {
                return disambiguateSize(jcas, prev_gdpa, curr_gdpa, size, enclosing, matched_gdpas);
            }
            prev_gdpa = curr_gdpa;
            curr_gdpa = null;
        }
        return disambiguateSize(jcas, prev_gdpa, curr_gdpa, size, enclosing, matched_gdpas);
    }
    
    private boolean disambiguateSize (JCas          jcas,
                              SCRGrossDescriptionPart left,
                              SCRGrossDescriptionPart right,
                              Annotation    size,
                              Annotation    enclosing,
                              List<SCRGrossDescriptionPart>          matched_gdpas)
    {
        if ((null == left) || (null != right)) {
            return false;
        }
        
        FSArray sites = left.getSites();
        if (0 == sites.size()) {
            return false;
        }
        Annotation gdpa_size = left.getSize();
        if (null == gdpa_size) {
            left.setSize(gdpa_size);
            return true;
        }
        if (left.getEnclosing() instanceof ParenSeparatedNPAnnotation && (left.getEnclosing() == enclosing)) {
            matched_gdpas.add(createGDPAnnotation(jcas, enclosing, UIMAAnnotationUtils.fsArrayToAnnotations(sites), size));
            return true;
        }
        return false;
    }

    
    private List<Annotation> fitSites (JCas jcas, Annotation enclosing, List<Annotation> sites, List<SCRGrossDescriptionPart> matched_gdpas, boolean create_unmatched)
    throws IllegalArgumentException, SecurityException, ClassNotFoundException, IllegalAccessException, NoSuchFieldException
    {
        Object[] sorted_sites = sites.toArray();
        Arrays.sort(sorted_sites, new UIMABoundaryComparator(true));
        List<Annotation> result = new ArrayList<Annotation>();
        for (int i = 0; i < sorted_sites.length; ++i) {
            AnatomicalSiteGroup site = (AnatomicalSiteGroup)sorted_sites[i];
            if (fitSite(jcas, enclosing, site, matched_gdpas, create_unmatched)) {
                result.add(site);
            }
        }
        return result;
    }
    
    private boolean fitSite (JCas jcas, Annotation enclosing, AnatomicalSiteGroup site, List<SCRGrossDescriptionPart> matched_gdpas, boolean create_unmatched)
    throws IllegalArgumentException, SecurityException, ClassNotFoundException, IllegalAccessException, NoSuchFieldException
    {
        SCRGrossDescriptionPart prev_gdpa = null;
        SCRGrossDescriptionPart curr_gdpa = null;
        for (Iterator<?> it = FeatureConstrainedIterator.getEnclosedIterator(
                jcas, SCRGrossDescriptionPart.class, enclosing); it.hasNext();) {
            curr_gdpa = (SCRGrossDescriptionPart)it.next();
            Annotation curr_size = curr_gdpa.getSize();
            if (((null == curr_size) && (site.getBegin() < curr_gdpa.getBegin())) ||
                ((null != curr_size) && (site.getEnd() < curr_size.getBegin()))) {
                return disambiguateSite(jcas, prev_gdpa, curr_gdpa, site, enclosing, matched_gdpas, false);
            }
            prev_gdpa = curr_gdpa;
            curr_gdpa = null;
        }
        return disambiguateSite(jcas, prev_gdpa, curr_gdpa, site, enclosing, matched_gdpas, create_unmatched);
    }
    
    private static Annotation getLeftMostSite(SCRGrossDescriptionPart gdpa)
    {
        Annotation result = null;
        for (int i = 0; i < gdpa.getSites().size(); ++i) {
            Annotation site = (Annotation)gdpa.getSites(i);
            if (null == result) {
                result = site;
            }
            else {
                if (result.getBegin() > site.getBegin()) {
                    result = site;
                }
            }
        }
        return result;
    }

    private static Annotation getRightMostSite(SCRGrossDescriptionPart gdpa)
    {
        Annotation result = null;
        for (int i = 0; i < gdpa.getSites().size(); ++i) {
            Annotation site = (Annotation)gdpa.getSites(i);
            if (null == result) {
                result = site;
            }
            else {
                if (result.getEnd() < site.getEnd()) {
                    result = site;
                }
            }
        }
        return result;
    }
    
    private boolean isNPDerivedAnnoation (Annotation ann)
    {
        return (m_npClass.isInstance(ann)) ||
               (m_npListClass.isInstance(ann)) ||
               (m_nppClass.isInstance(ann)) ||
               (m_npsClass.isInstance(ann)) ||
               (NPCombinedAnnotation.class.isInstance(ann)) ||
               (ParenSeparatedNPAnnotation.class.isInstance(ann));
        
    }
    
    private boolean disambiguateSite (JCas                  jcas,
                                      SCRGrossDescriptionPart         left,
                                      SCRGrossDescriptionPart         right,
                                      AnatomicalSiteGroup   site,
                                      Annotation            enclosing,
                                      List<SCRGrossDescriptionPart>                  matched_gdpas,
                                      boolean               create_unmatched)
    throws IllegalArgumentException, SecurityException, ClassNotFoundException, IllegalAccessException, NoSuchFieldException
    {
        if ((null == left) && (null == right)) {
            if (create_unmatched) {
                matched_gdpas.add(createGDPAnnotation(jcas, enclosing, Arrays.asList(new Annotation[]{site}), (Annotation)null));
                return true;
            }
            return false;
        }
        else if (null == left) {
            Annotation gdpa_site = getLeftMostSite(right);
            Annotation gdpa_size = right.getSize();
            
            if (gdpa_site.getBegin() <= site.getBegin()) {
                addSitesToGDPAnnotation(jcas, enclosing, right, Arrays.asList(new Annotation[] {site}));
                return true;
            }            
            if ((null != gdpa_site) && ((null == gdpa_size) || (gdpa_site.getEnd() < gdpa_size.getBegin()))) {
                if (isNPDerivedAnnoation(right.getEnclosing()) && isNPDerivedAnnoation(enclosing)) {
                    addSitesToGDPAnnotation(jcas, enclosing, right, Arrays.asList(new Annotation[] {site}));
                    return true;
                }
                else if (gdpa_site.getEnd() <= site.getEnd()){
                    addSitesToGDPAnnotation(jcas, enclosing, right, Arrays.asList(new Annotation[] {site}));
                    return true;
                }
                else if (right.getEnclosing() instanceof ParenSeparatedNPAnnotation) {
                    addSitesToGDPAnnotation(jcas, enclosing, right, Arrays.asList(new Annotation[] {site}));
                    return true;
                }
                else {
                    matched_gdpas.add(createGDPAnnotation(jcas, enclosing, Arrays.asList(new Annotation[]{site}), (Annotation)null));
                    return true;
                }
            }
            else {
                matched_gdpas.add(createGDPAnnotation(jcas, enclosing, Arrays.asList(new Annotation[]{site}), (Annotation)null));
                return true;
            }
        }
        else if (null == right) {
            Annotation gdpa_site = getRightMostSite(left);
            Annotation gdpa_size = left.getSize();
            if (gdpa_site.getEnd() >= site.getEnd()) {
                addSitesToGDPAnnotation(jcas, enclosing, left, Arrays.asList(new Annotation[] {site}));
                return true;
            }            
            if ((null != gdpa_site) && ((null == gdpa_size) || (gdpa_site.getEnd() > gdpa_size.getEnd()))) {
                if (!(isNPDerivedAnnoation(left.getEnclosing()) ^ isNPDerivedAnnoation(enclosing))) {
                    addSitesToGDPAnnotation(jcas, enclosing, left, Arrays.asList(new Annotation[] {site}));
                    return true;
                }
                else {
                    matched_gdpas.add(createGDPAnnotation(jcas, enclosing, Arrays.asList(new Annotation[]{site}), (Annotation)null));
                    return true;
                }
            }
            else {
                matched_gdpas.add(createGDPAnnotation(jcas, enclosing, Arrays.asList(new Annotation[]{site}), (Annotation)null));
                return true;
            }
        }
        else {
            Annotation gdpa_site = getLeftMostSite(right);
            Annotation gdpa_size = right.getSize();
            if ((null != gdpa_site) && ((null == gdpa_size) || (gdpa_site.getEnd() < gdpa_size.getBegin()))) {
                if ((right.getEnclosing() instanceof ParenSeparatedNPAnnotation) ||
                    (right.getEnclosing() instanceof SyntacticUnit)) {
                    addSitesToGDPAnnotation(jcas, enclosing, right, Arrays.asList(new Annotation[]{site}));
                    return true;
                }
            }
            gdpa_site = getRightMostSite(left);
            gdpa_size = left.getSize();
            if ((null != gdpa_site) && ((null == gdpa_size) || (gdpa_site.getBegin() > gdpa_size.getEnd()))) {
                addSitesToGDPAnnotation(jcas, enclosing, left, Arrays.asList(new Annotation[] {site}));
                return true;
            }
            else {
                matched_gdpas.add(createGDPAnnotation(jcas, enclosing, Arrays.asList(new Annotation[]{site}), (Annotation)null));
                return true;
            }
        }
    }
    
    private void fixBrokenParenAnnotations(JCas jcas, Map<ComparableArray, Annotation> offset2ann)
    {
        Annotation site = null;
        List<Annotation> sizes = new ArrayList<Annotation>();
        Annotation paren = null;
        Annotation nppar = null;
        boolean start_over = false;
        List<ParenSeparatedNPAnnotation> new_anns = new ArrayList<ParenSeparatedNPAnnotation>(); 
        
        for (Iterator<ComparableArray> it = offset2ann.keySet().iterator(); it.hasNext();) {
            ComparableArray ca = it.next();
            Annotation ann = offset2ann.get(ca);
            if (ann instanceof AnatomicalSiteGroup) { 
                if (((Integer)ca.m_src[0]).intValue() == ann.getBegin()) {
                    if (null == paren) {
                        if ((null == site) || (ann.getBegin() > site.getEnd())) {
                            site = ann;
                        }
                    }
                    else {
                        start_over = true;
                    }
                }
                else {
                    if (null != paren) {
                        start_over = true;
                    }
                }
            }
            else if (ann instanceof SyntacticUnit) {
                if (((Integer)ca.m_src[0]).intValue() == ann.getBegin()) {
                    if ((null == paren) && (null != site)) {
                        paren = ann;
                    }
                    else {
                        start_over = true;
                    }
                }
                else {
                    if ((null != site) && (!sizes.isEmpty()) && (null != paren) /*&& (null != nppar)*/) {
                        ParenSeparatedNPAnnotation psa = new ParenSeparatedNPAnnotation(jcas);
                        psa.setLeftOfParen(site);
                        psa.setBegin(site.getBegin());
                        psa.setEnd(paren.getEnd());
                        UIMAAnnotationUtils.addAnnotationsToFSArray(jcas, psa, "insideParen", sizes, true);
                        // System.out.println(psa.getClass().getName() + ": created new at " + psa.getBegin() + "|" + psa.getEnd());
                        new_anns.add(psa);
                    }
                    start_over = true;
                }
            }
            else if (m_npClass.isInstance(ann)) {
                if (((Integer)ca.m_src[0]).intValue() == ann.getBegin()) {
                    if ((null == nppar) && (null != paren) && (null != site)) {
                        nppar = ann;
                    }
                    else if ((null == nppar) && (null != paren) && (null == site)) {
                        start_over = true;
                    }
                }
                else {
                    nppar = null;
                }
            }
            else if (isValidSizeAnnotation(ann)) {
                if (((Integer)ca.m_src[0]).intValue() == ann.getBegin()) {
                    if (/*(null == size) &&*/ (null != nppar) && (null != paren) && (null != site)) {
                        sizes.add(ann);
                    }
                    else {
                        start_over = true;
                    }
                }
            }
            if (start_over) {
                site = null;
                sizes.clear();
                paren = null;
                nppar = null;
                start_over= false;
            }
        }
        for (Iterator<ParenSeparatedNPAnnotation> it = new_anns.iterator(); it.hasNext();) {
            putCA(offset2ann, it.next(), PSNP_TAGS);
        }
    }
    

    private void processEnclosedAnnotaions(JCas                                 jcas,
                                           Map<ComparableArray, Annotation>     offset2ann,
                                           Object[]                             enclosing_tags,
                                           boolean                              create_unmatched,
                                           boolean                              remove_processed)
    throws IllegalArgumentException, SecurityException, ClassNotFoundException, IllegalAccessException, NoSuchFieldException
    {
        // System.err.println("\tProcessing " + enclosing_tags[1]);
        Annotation enclosing = null;
    
        ArrayList<Annotation> enclosed_sites = new ArrayList<Annotation>();
        ArrayList<Annotation> enclosed_sizes = new ArrayList<Annotation>();

        List<Annotation> matched_sites = new ArrayList<Annotation>();
        List<Annotation> matched_sizes = new ArrayList<Annotation>();
        List<SCRGrossDescriptionPart> matched_gdpas = new ArrayList<SCRGrossDescriptionPart>();
        List<Annotation> processed_enclosing = new ArrayList<Annotation>();
        
        for (Iterator<ComparableArray> it = offset2ann.keySet().iterator(); it.hasNext();) {
            ComparableArray ca = it.next();
            Annotation ann = offset2ann.get(ca);
            
            if (!isValidEnclosingAnnotation(ann, enclosing_tags) && (null == enclosing)) {
                continue;
            }
            
            if (ann instanceof AnatomicalSiteGroup && !isASGroupAnyEnclosed(jcas, (AnatomicalSiteGroup)ann, enclosing)) {
                continue;
            }
            else if (!isValidEnclosingAnnotation(ann, enclosing_tags) &&
                (ann.getBegin() < enclosing.getEnd()) && (ann.getEnd() > enclosing.getEnd())) {
                continue;
            }
//            System.err.printf("\t\t%s(%d|%d) - processing for CA(%d|%d|%d|%s)\n",
//                    ann.getClass().getName(), ann.getBegin(), ann.getEnd(),
//                    ca.m_src[0], ca.m_src[1], ca.m_src[2], ca.m_src[3]);
            
            if (isValidEnclosingAnnotation(ann, enclosing_tags)) {
                if (((Integer)ca.m_src[0]).intValue() == ann.getBegin()) {
                    if (null != enclosing) {
                        System.err.printf("WARNING: previous enclosing {%s(%d|%d)} was not reset before a new {%s(%d|%d)} was encountered: overlapping is possible\n",
                                enclosing.getClass().getSimpleName(), enclosing.getBegin(), enclosing.getEnd(),
                                ann.getClass().getSimpleName(), ann.getBegin(), ann.getEnd());
                        processed_enclosing.add(enclosing);
                    }
                    enclosing = ann;
                    // System.err.printf("\t\t\t%s(%d|%d) - set as enclosing\n", ann.getClass().getName(), ann.getBegin(), ann.getEnd());
                    enclosed_sites.clear();
                    enclosed_sizes.clear();
                }
                else {
                    if (!enclosed_sites.isEmpty() && !enclosed_sizes.isEmpty()) {
                        List<SCRGrossDescriptionPart> gdpas = createGDPAnnotation(jcas, enclosing, enclosed_sites, enclosed_sizes);
                        matched_gdpas.addAll(gdpas);
                        matched_sites.addAll(enclosed_sites);
                        matched_sizes.addAll(enclosed_sizes);
                        enclosed_sites.clear();
                        enclosed_sizes.clear();
                    }
                    else if (!enclosed_sites.isEmpty()) {
                        List<Annotation> fitted_sites = fitSites(jcas, enclosing, enclosed_sites, matched_gdpas, create_unmatched);
                        matched_sites.addAll(fitted_sites);
                        enclosed_sites.clear();
                    }
                    if (null == enclosing) {
                        System.err.printf("WARNING: %s(%d|%d): enclosing annotation is null while its closing tag is encountered\n",
                                ann.getClass().getName(), ann.getBegin(), ann.getEnd());
                    }
                    else {
                        processed_enclosing.add(enclosing);
                        // System.err.printf("\t\t\t%s(%d|%d) - reset enclosing\n", ann.getClass().getName(), ann.getBegin(), ann.getEnd());
                        enclosing = null;
                    }
                }
            }
            else if (isValidGDPAnnotation(ann) && (((Integer)ca.m_src[0]).intValue() == ann.getBegin())) {
                if (!enclosed_sites.isEmpty() && !enclosed_sizes.isEmpty()) {
                    List<SCRGrossDescriptionPart> gdpas = createGDPAnnotation(jcas, enclosing, enclosed_sites, enclosed_sizes);
                    matched_gdpas.addAll(gdpas);
                    matched_sites.addAll(enclosed_sites);
                    matched_sizes.addAll(enclosed_sizes);
                    enclosed_sites.clear();
                    enclosed_sizes.clear();
                }
                else {
                    if (!enclosed_sites.isEmpty()) {
                        List<Annotation> fitted_sites = fitSites(jcas, enclosing, enclosed_sites, matched_gdpas, false);
                        matched_sites.addAll(fitted_sites);
                        enclosed_sites.clear();
                    }
                    else if (!enclosed_sizes.isEmpty()) {
                        // TODO process size: see if it should be used instead of already selected
                        enclosed_sizes.clear();
                    }
                }
            }
            else if (isValidSizeAnnotation(ann) && (((Integer)ca.m_src[0]).intValue() == ann.getBegin())) {
                if (!enclosed_sites.isEmpty() && !enclosed_sizes.isEmpty()) {
                    List<SCRGrossDescriptionPart> gdpas = createGDPAnnotation(jcas, enclosing, enclosed_sites, enclosed_sizes);
                    matched_gdpas.addAll(gdpas);
                    matched_sites.addAll(enclosed_sites);
                    matched_sizes.addAll(enclosed_sizes);
                    enclosed_sites.clear();
                    enclosed_sizes.clear();
                }
                enclosed_sizes.add(ann);
                if (!enclosed_sites.isEmpty() && !enclosed_sizes.isEmpty()) {
                    List<SCRGrossDescriptionPart> gdpas = createGDPAnnotation(jcas, enclosing, enclosed_sites, enclosed_sizes);
                    matched_gdpas.addAll(gdpas);
                    matched_sites.addAll(enclosed_sites);
                    matched_sizes.addAll(enclosed_sizes);
                    enclosed_sites.clear();
                    enclosed_sizes.clear();
                }
                else if (!enclosed_sizes.isEmpty()) {
                    List<Annotation> fitted_sizes = fitSizes(jcas, enclosing, enclosed_sizes, matched_gdpas);
                    matched_sizes.addAll(fitted_sizes);
                    enclosed_sizes.removeAll(fitted_sizes);
                }
            }
            else if ((ann instanceof AnatomicalSiteGroup) && (((Integer)ca.m_src[0]).intValue() == ann.getBegin())) {
                enclosed_sites.add(ann);
                if (!enclosed_sites.isEmpty() && !enclosed_sizes.isEmpty()) {
                    List<SCRGrossDescriptionPart> gdpas = createGDPAnnotation(jcas, enclosing, enclosed_sites, enclosed_sizes);
                    matched_gdpas.addAll(gdpas);
                    matched_sites.addAll(enclosed_sites);
                    matched_sizes.addAll(enclosed_sizes);
                    enclosed_sites.clear();
                    enclosed_sizes.clear();
                }
                else if (!enclosed_sites.isEmpty()) {
                    List<Annotation> fitted_sites = fitSites(jcas, enclosing, enclosed_sites, matched_gdpas, false);
                    matched_sites.addAll(fitted_sites);
                    enclosed_sites.removeAll(fitted_sites);
                }
            }
        }
        removeCA(offset2ann, matched_sites, SITE_TAGS);
        removeCA(offset2ann, matched_sizes, SIZE_TAGS);
        if (remove_processed) {
            removeCA(offset2ann, processed_enclosing, enclosing_tags);
        }
        putCA(offset2ann, matched_gdpas, GRDS_TAGS);
    }

    private void processUnmatchedAnnotaions (Map<ComparableArray, Annotation> offset2ann) throws CASException
    {
        for (Iterator<ComparableArray> it = offset2ann.keySet().iterator(); it.hasNext();) {
            ComparableArray ca = it.next();
            Annotation ann = offset2ann.get(ca);
            if ((ann instanceof AnatomicalSiteGroup) || isValidSizeAnnotation(ann)) {
                System.err.println(DocumentURI.get(ann.getCAS().getJCas()) +
                                   ": UNMATCHED ANNOTATION: " + ann.getClass().getName() +
                                   ": " + ann.getBegin() + "|" + ann.getEnd());
            }
        }
    }

    public void process (JCas jcas, ResultSpecification arg1)
    throws AnnotatorProcessException
    {
        Logger.getDefaultLogger().logInfo("processing");
        
        try {
            
            // System.out.println("Processing " + CommonFeatureMatcher.getDocumentId(jcas, null));
        
            JFSIndexRepository indxs = jcas.getJFSIndexRepository ();
            
            AnnotationIndex ind = (AnnotationIndex)indxs.getAnnotationIndex(GrossDescriptionAnnotation.typeIndexID);
            
            for (Iterator<?> it = ind.iterator(); it.hasNext();) {
                Annotation enclosing = (GrossDescriptionAnnotation)it.next();
                Collection<Annotation> ingore_annotations = new HashSet<Annotation>();
                Map<ComparableArray, Annotation> offset2ann = createOrderedAnnotationSequence(jcas, enclosing, ingore_annotations);

//                for (Iterator inv_it = ingore_annotations.iterator(); inv_it.hasNext();) {
//                    Annotation ann = (Annotation)inv_it.next();
//                    System.err.println(CommonFeatureMatcher.getDocumentId(jcas, null) + ": WARNING: Ignored Annotation: " + ann.getClass().getName() + ": "+ ann.getBegin() + "|" + ann.getEnd());
//                }
                
                fixBrokenParenAnnotations(jcas, offset2ann);
                processEnclosedAnnotaions(jcas, offset2ann, NPHR_TAGS, false, true);
                processEnclosedAnnotaions(jcas, offset2ann, PSNP_TAGS, false, true);
//                 processEnclosedAnnotaions(jcas, offset2ann, NPPH_TAGS, false, true);
                processEnclosedAnnotaions(jcas, offset2ann, NPCB_TAGS, false, false);
                processEnclosedAnnotaions(jcas, offset2ann, PARN_TAGS, false, true);
                processEnclosedAnnotaions(jcas, offset2ann, SENT_TAGS, false, true);
                processEnclosedAnnotaions(jcas, offset2ann, SUBH_TAGS, false, true);
                
                // create GDPA object containing sites only
                processEnclosedAnnotaions(jcas, offset2ann, NPCB_TAGS, true, true);
                
                processUnmatchedAnnotaions(offset2ann);
            }
            ind = (AnnotationIndex)indxs.getAnnotationIndex(SCRGrossDescriptionPart.typeIndexID);
            List<Annotation> scr_gdps = new ArrayList<Annotation>();
            for (Iterator<?> it = ind.iterator(); it.hasNext();) {
                scr_gdps.add((Annotation)it.next());
            }
            SCRGrossDescription scr_gd = new SCRGrossDescription(jcas);
            UIMAAnnotationUtils.addAnnotationsToFSArray(jcas, scr_gd, "Parts", scr_gdps, true);
            scr_gd.addToIndexes();
        }
        catch (Exception e) {
            throw new AnnotatorProcessException(e);
        }
    }
}
