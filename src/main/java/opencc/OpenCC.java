package opencc;

import opencc.utils.Dictionary;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;

/**
 * OpenCC converts Simplified Chinese to Traditional Chinese and vice versa
 */
public class OpenCC {
//    public static final String[] CONVERSIONS = new String[] {
//            "hk2s", "s2hk", "s2t", "s2tw", "s2twp", "t2hk", "t2s",
//            "t2tw", "tw2s", "tw2sp"
//    };

    public static final Map<String, String> CONVERSIONS = new HashMap<>();
    static {
        CONVERSIONS.put("s2t", "簡->繁");
        CONVERSIONS.put("s2hk", "簡->繁(香港用字)");
        CONVERSIONS.put("s2tw", "簡->繁(台灣用字)");
        CONVERSIONS.put("s2twp", "簡->繁(台灣用語)");
        CONVERSIONS.put("t2hk", "繁->繁(香港用字)");
        CONVERSIONS.put("t2tw", "繁->繁(台灣用字)");
        CONVERSIONS.put("t2s", "繁->簡");
        CONVERSIONS.put("hk2s", "繁(香港用字)->簡");
        CONVERSIONS.put("tw2s", "繁(台灣用字)->簡");
        CONVERSIONS.put("tw2sp", "繁(台灣用語)->簡(大陸用語)");
    }

    Dictionary dictionary;

    /**
     * construct OpenCC with default config of "s2t"
     */
    public OpenCC() {
        this("s2t");
    }

    /**
     * construct OpenCC with conversion
     * @param conversion options are "hk2s", "s2hk", "s2t", "s2tw", "s2twp", "t2hk", "t2s",
     *               "t2tw", "tw2s", and "tw2sp"
     */
    public OpenCC(String conversion) {
        dictionary = new Dictionary(conversion);
    }

    /**
     *
     * @return dict name
     */
    public String getDictName() {
        return dictionary.getDictName();
    }

    /**
     * set OpenCC a new conversion
     * @param conversion options are "hk2s", "s2hk", "s2t", "s2tw", "s2twp", "t2hk", "t2s",
     *               "t2tw", "tw2s", and "tw2sp"
     */
    public void setConversion(String conversion) {
        dictionary.setConfig(conversion);
    }

    /**
     * convert the string
     * @param string input string to convert
     * @return converted string
     */
    public String convert(String string) {
        if (string.length() == 0) {
            return "";
        }

        StringBuilder stringBuilder = new StringBuilder(string);

        for (SortedMap<String, String> dictMap : dictionary.getDictChain()) {
            for (Map.Entry<String, String> entry : dictMap.entrySet()) {
                int fromIndex = 0;
                int pos = stringBuilder.indexOf(entry.getKey(), fromIndex);
                String converted = entry.getValue();
                while (pos >= 0) {
                    converted = converted.split(" ")[0];  // get the 1st result if multiple choices available
                    stringBuilder.replace(pos, pos + entry.getKey().length(), converted);
                    fromIndex = pos + converted.length();
                    pos = stringBuilder.indexOf(entry.getKey(), fromIndex);
                }
            }
        }

        return stringBuilder.toString();
    }

}
