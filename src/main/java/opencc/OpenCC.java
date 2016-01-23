package opencc;

import opencc.utils.Dictionary;

import java.util.Map;

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
     * construct OpenCC with config
     * @param config options are "hk2s", "s2hk", "s2t", "s2tw", "s2twp", "t2hk", "t2s",
     *               "t2tw", "tw2s", and "tw2sp"
     */
    public OpenCC(String config) {
        dictionary = new Dictionary(config);
    }

    /**
     * set OpenCC a new config
     * @param config options are "hk2s", "s2hk", "s2t", "s2tw", "s2twp", "t2hk", "t2s",
     *               "t2tw", "tw2s", and "tw2sp"
     */
    public void setConfig(String config) {
        dictionary.setConfig(config);
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

        for (Map<String, String> dictMap : dictionary.getDictChain()) {
            for (String key : dictMap.keySet()) {
                int fromIndex = 0;
                int pos = stringBuilder.indexOf(key, fromIndex);
                while (pos >= 0) {
                    String converted = dictMap.get(key);
                    converted = converted.split(" ")[0];  // get the 1st result if multiple choices available
                    stringBuilder.replace(pos, pos + key.length(), converted);
                    fromIndex = pos + converted.length();
                    pos = stringBuilder.indexOf(key, fromIndex);
                }
            }
        }

        return stringBuilder.toString();
    }

}
