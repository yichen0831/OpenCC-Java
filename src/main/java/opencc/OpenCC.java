package opencc;

import opencc.utils.Dictionary;

import java.util.Map;
import java.util.SortedMap;

/**
 * OpenCC converts Simplified Chinese to Traditional Chinese and vice versa
 */
public class OpenCC {

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
