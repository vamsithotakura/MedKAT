package org.ohnlp.medkat.common;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;




public class NumericStringUtils
{
    static Map<String, Integer> m_numericStringVariants = null;
    static String m_numericStringGroupPattern = null;
    
    
    private static final String[] m_baseNumbers = {
        "zero",
        "one",
        "two",
        "three",
        "four",
        "five",
        "six",
        "seven",
        "eight",
        "nine",
        "ten",
        "eleven",
        "twelve",
        "thirteen",
        "fourteen",
        "fifteen",
        "sixteen",
        "seventeen",
        "eighteen",
        "nineteen"
    };
    
    private static final String[] m_tenBasedNumbers = {
        "twenty",
        "thirty",
        "fourty",
        "fifty",
        "sixty",
        "seventy",
        "eighty",
        "ninety"
        };
    
    private static String convert2NumericString (int number) {

        if (number >= 100){
            return null;
        }
        if (number < 20) {
            return m_baseNumbers[number];
        }
        String result = "";
        int ind = (number / 10) - 2; // as at starts from 20 (index 0)
        result = m_tenBasedNumbers[ind];
        ind = number % 10;
        if (ind > 0) {
            result += " " + m_baseNumbers[ind];
        }
        return result; 
    }    
    
    private static void generateNumericStringVariants(int max, Map<String, Integer> values, Collection<String> patterns)
    {
        values.put("single", new Integer(1));
        patterns.add("single");
        
        for (int i = 0; i < max; ++i) {
            Integer value = new Integer(i);
            String variant = convert2NumericString(i);
            values.put(variant, value);
            if (-1 == variant.indexOf(" ")) {
                patterns.add(variant);
            }
            else {
                String variant2 = variant.replaceAll(" ", "[\\\\s-]");
                patterns.add(variant2);
                variant2 = variant.replaceAll(" ", "-");
                values.put(variant2, value);
            }
        }
    }
    
    private static String generateNumericStringGroupPattern(int max, Map<String, Integer> variants)
    {
        Collection<String> numericStringPatterns = new TreeSet<String>();
        StringBuffer result = new StringBuffer(max * 5); 
        
        generateNumericStringVariants(max, variants, numericStringPatterns);
        
        for (Iterator<String> it = numericStringPatterns.iterator(); it.hasNext();) {
            String pattern = it.next();
            if (0 != result.length()) {
                result.append("|");
            }
            result.append(pattern); 
        }
        return result.toString();
    }

    public static String getNumericStringGroupPattern(int max)
    {
        if (null != m_numericStringGroupPattern) {
            return m_numericStringGroupPattern;
        }
        
        m_numericStringVariants = new TreeMap<String, Integer>(); 
        m_numericStringGroupPattern = generateNumericStringGroupPattern(max, m_numericStringVariants);
        return m_numericStringGroupPattern;
    }
    
    public static Integer getIntegerFromNumericString(String numericString)
    {
        String s = numericString.toLowerCase();
        s = s.trim();
        if (s.matches("^\\d+$")) {
            return new Integer(s);
        }
        if (m_numericStringVariants.containsKey(s)) {
            return m_numericStringVariants.get(s);
        }
        return null;
    }
}
