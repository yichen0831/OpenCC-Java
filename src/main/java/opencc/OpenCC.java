package opencc;

import opencc.utils.Dictionary;

/**
 * OpenCC converts Simplified Chinese to Traditional Chinese and vice versa
 */
public class OpenCC {

    Dictionary dictionary;

    public OpenCC() {
        this("s2t");
    }

    public OpenCC(String config) {
        dictionary = new Dictionary(config);
    }

    public void setConfig(String config) {
        dictionary.setConfig(config);
    }

    public String convert(String string) {
        return "require implementation";
    }
    
    public static void main(String[] args) {
        OpenCC openCC = new OpenCC("hk2s");
        
    }
}
