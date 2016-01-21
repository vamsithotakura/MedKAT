package org.ohnlp.medkat.taes.conceptMapper;

public class DictTermMarkers
{
    public static final int NEGATED_INDICATOR                 = 1 << 0; // 1
    public static final int IGNORED_INDICATOR                 = 1 << 1; // 2
    
    public static final int DUPLICATE_INDICATOR               = 1 << 2; // 4
    public static final int SUBSUMED_INDICATOR                = 1 << 3; // 8
    public static final int SUPERFLUOUS_INDICATOR             = 1 << 4; // 16
    public static final int MODIFIER_INDICATOR                = 1 << 5; // 32
    public static final int CONTAINSDISALLOWEDTERM_INDICATOR  = 1 << 6; // 64
    
    public static final int METASTATIC_INDICATOR              = 1 << 8; // 256

    

    /**
     * @param dictTerm
     * @param flag
     * @return true if flag is set in DictTerm
     */ 
    public static final boolean isMarked (DictTerm dictTerm)
    {
        return (0 != dictTerm.getMarked ());
    }


    /**
     * @param dictTerm
     * @param flag
     * @return true if flag is set in DictTerm
     */ 
    public static final boolean isMarkedAs (DictTerm dictTerm, int flag)
    {
        return isMarkedAs (dictTerm.getMarked (), flag);
    }


    /**
     * @param marker
     * @param flag
     * @return true if flag is set in DictTerm
     */ 
    public static final boolean isMarkedAs (int marker, int flag)
    {
        return ((marker & flag) == flag);
    }

    /**
     * 
     * @param dictTerm
     * @param flag
     * @return true if flag is the ONLY flag set in DictTerm
     */
    public static final boolean isOnlyMarkedAs (DictTerm dictTerm, int flag)
    {
        return isOnlyMarkedAs (dictTerm.getMarked (), flag);
    }

    /**
     * 
     * @param marker
     * @param flag
     * @return true if flag is the ONLY flag set in DictTerm
     */
    public static final boolean isOnlyMarkedAs (int marker, int flag)
    {
        return (marker == flag);
    }
    
    
    /**
     * 
     * @param dictTerm
     * @param flag
     * @return true if ANY of the flags is set in DictTerm
     */
    public static final boolean isAnyMarkedAs (DictTerm dictTerm, int flag)
    {
        return isAnyMarkedAs (dictTerm.getMarked (), flag);
    }
    
    /**
     * 
     * @param marker
     * @param flag
     * @return true if ANY of the flags is set in DictTerm
     */
    public static final boolean isAnyMarkedAs (int marker, int flag)
    {
        return ((marker & flag) != 0);
    }
    
    
    
    public static final boolean isMarkedAsNegated (DictTerm dictTerm)
    {
        return isMarkedAs (dictTerm, NEGATED_INDICATOR);
    }
    public static final boolean isOnlyMarkedAsNegated (DictTerm dictTerm)
    {
        return isOnlyMarkedAs (dictTerm, NEGATED_INDICATOR);
    }
    
    public static final boolean isMarkedAsNegated (int marker)
    {
        return isMarkedAs (marker, NEGATED_INDICATOR);
    }
    public static final boolean isOnlyMarkedAsNegated (int marker)
    {
        return isOnlyMarkedAs (marker, NEGATED_INDICATOR);
    }
    
    public static final boolean isMarkedAsDuplicate (DictTerm dictTerm)
    {
        return isMarkedAs (dictTerm, DUPLICATE_INDICATOR);
    }
    public static final boolean isOnlyMarkedAsDuplicate (DictTerm dictTerm)
    {
        return isOnlyMarkedAs (dictTerm, DUPLICATE_INDICATOR);
    }

    public static final boolean isMarkedAsDuplicate (int marker)
    {
        return isMarkedAs (marker, DUPLICATE_INDICATOR);
    }
    public static final boolean isOnlyMarkedAsDuplicate (int marker)
    {
        return isOnlyMarkedAs (marker, DUPLICATE_INDICATOR);
    }

    public static final boolean isMarkedAsSubsumed (DictTerm dictTerm)
    {
        return isMarkedAs (dictTerm, SUBSUMED_INDICATOR);
    }
    public static final boolean isOnlyMarkedAsSubsumed (DictTerm dictTerm)
    {
        return isOnlyMarkedAs (dictTerm, SUBSUMED_INDICATOR);
    }

    public static final boolean isMarkedAsSubsumed (int marker)
    {
        return isMarkedAs (marker, SUBSUMED_INDICATOR);
    }
    public static final boolean isOnlyMarkedAsSubsumed (int marker)
    {
        return isOnlyMarkedAs (marker, SUBSUMED_INDICATOR);
    }

    public static final boolean isMarkedAsSuperfluous (DictTerm dictTerm)
    {
        return isMarkedAs (dictTerm, SUPERFLUOUS_INDICATOR);
    }
    public static final boolean isOnlyMarkedAsSuperfluous (DictTerm dictTerm)
    {
        return isOnlyMarkedAs (dictTerm, SUPERFLUOUS_INDICATOR);
    }

    public static final boolean isMarkedAsSuperfluous (int marker)
    {
        return isMarkedAs (marker, SUPERFLUOUS_INDICATOR);
    }
    public static final boolean isOnlyMarkedAsSuperfluous (int marker)
    {
        return isOnlyMarkedAs (marker, SUPERFLUOUS_INDICATOR);
    }

    public static final boolean isMarkedAsMetastatic (DictTerm dictTerm)
    {
        return isMarkedAs (dictTerm, METASTATIC_INDICATOR);
    }
    
    public static final boolean isOnlyMarkedAsMetastatic (DictTerm dictTerm)
    {
        return isOnlyMarkedAs (dictTerm, METASTATIC_INDICATOR);
    }

    public static final boolean isMarkedAsMetastatic (int marker)
    {
        return isMarkedAs (marker, METASTATIC_INDICATOR);
    }
    
    public static final boolean isOnlyMarkedAsMetastatic (int marker)
    {
        return isOnlyMarkedAs (marker, METASTATIC_INDICATOR);
    }

    public static final boolean isOnlyMarkedAsModifer (DictTerm dictTerm)
    {
        return isOnlyMarkedAs (dictTerm, MODIFIER_INDICATOR);
    }

    public static final boolean isMarkedAsModifer (DictTerm dictTerm)
    {
        return isMarkedAs (dictTerm, MODIFIER_INDICATOR);
    }

    public static final boolean isOnlyMarkedAsModifer (int marker)
    {
        return isOnlyMarkedAs (marker, MODIFIER_INDICATOR);
    }

    public static final boolean isMarkedAsModifer (int marker)
    {
        return isMarkedAs (marker, MODIFIER_INDICATOR);
    }

    public static final boolean isOnlyMarkedAsContainsDisallowed (DictTerm dictTerm)
    {
        return isOnlyMarkedAs (dictTerm, CONTAINSDISALLOWEDTERM_INDICATOR);
    }

    public static final boolean isMarkedAsContainsDisallowed (DictTerm dictTerm)
    {
        return isMarkedAs (dictTerm, CONTAINSDISALLOWEDTERM_INDICATOR);
    }

    public static final boolean isOnlyMarkedAsContainsDisallowed (int marker)
    {
        return isOnlyMarkedAs (marker, CONTAINSDISALLOWEDTERM_INDICATOR);
    }

    public static final boolean isMarkedAsContainsDisallowed (int marker)
    {
        return isMarkedAs (marker, CONTAINSDISALLOWEDTERM_INDICATOR);
    }
}

