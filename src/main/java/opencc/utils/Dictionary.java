package opencc.utils;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Dictionary holds the mappings for converting Chinese characters
 */
public class Dictionary {

    String config;
    List<Map<String, String>> dictSet;
    Map<String, String> dict;

    /**
     *
     * @param config the config to use, including 'hk2s', 's2hk', 's2t', 's2tw', 's2twp',
     *               't2hk', 't2s', 't2tw', 'tw2s', and 'tw2sp'
     */
    public Dictionary(String config) {

        dictSet = new ArrayList<>();

        this.config = "";

        setConfig(config);
    }


    public void setConfig(String config) {
        config = config.toLowerCase();

        if (this.config.equals(config)) {
            return;
        }

        dictSet.clear();

        switch (config) {
            case "hk2s":
            case "s2hk":
            case "s2t":
            case "s2tw":
            case "s2twp":
            case "t2hk":
            case "t2s":
            case "t2tw":
            case "tw2s":
            case "tw2sp":
                this.config = config;
                break;
            default:
                this.config = "s2t";
                break;
        }

        loadDict();
    }

    /**
     * load dictionary files into dictSet
     */
    private void loadDict() {
        dictSet.clear();

        JSONParser jsonParser = new JSONParser();

        try {
            URL url = getClass().getClassLoader().getResource("config/" + config + ".json");
            System.out.println(url.getFile());
            Object obj = jsonParser.parse(new FileReader(url.getFile()));
            JSONObject jsonObject = (JSONObject) obj;

            JSONArray jsonArray = (JSONArray) jsonObject.get("conversion_chain");
            System.out.println(jsonArray.get(0));

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
