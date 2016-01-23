package opencc.utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Dictionary holds the mappings for converting Chinese characters
 */
public class Dictionary {

    protected String name;
    protected String config;
    protected List<Map<String, String>> dictChain;
    protected Map<String, String> dict;

    /**
     *
     * @param config the config to use, including "hk2s", "s2hk", "s2t", "s2tw", "s2twp",
     *               "t2hk", "t2s", "t2tw", "tw2s", and "tw2sp"
     */
    public Dictionary(String config) {

        dictChain = new ArrayList<>();

        name = "";
        this.config = "";

        setConfig(config);
    }


    public void setConfig(String config) {
        config = config.toLowerCase();

        if (this.config.equals(config)) {
            return;
        }

        dictChain.clear();

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
     * load dictionary files into dictChain
     */
    private void loadDict() {
        dictChain.clear();

        JSONParser jsonParser = new JSONParser();

        List<String> dictFileNames = new ArrayList<>();

        try {
            URL url = getClass().getClassLoader().getResource("config/" + config + ".json");
            Object object = jsonParser.parse(new FileReader(url.getFile()));

            JSONObject dictRoot = (JSONObject) object;

            name = (String) dictRoot.get("name");
            JSONArray jsonArray = (JSONArray) dictRoot.get("conversion_chain");

            for (Object obj : jsonArray) {
                JSONObject dictObj = (JSONObject) ((JSONObject) obj).get("dict");
                dictFileNames.addAll(getDictFileNames(dictObj));
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        for (String filename : dictFileNames) {
            dict = new HashMap<>();

            URL url = getClass().getClassLoader().getResource("dictionary/" + filename);
            try {
                List<String> lines = Files.readAllLines(Paths.get(url.toURI()));

                for (String line : lines) {
                    String[] words = line.trim().split("\t");
                    dict.put(words[0], words[1]);
                }

            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }

            dictChain.add(dict);
        }

    }

    private List<String> getDictFileNames(JSONObject dictObject) {
        List<String> filenames = new ArrayList<>();

        String type = (String) dictObject.get("type");

        if (type.equals("txt")) {
            filenames.add((String) dictObject.get("file"));
        }
        else if (type.equals("group")) {
            JSONArray dictGroup = (JSONArray) dictObject.get("dicts");
            for (Object obj : dictGroup) {
                filenames.addAll(getDictFileNames((JSONObject) obj));
            }
        }

        return filenames;
    }


    /**
     *
     * @return dictChain
     */
    public List<Map<String, String>> getDictChain() {
        return dictChain;
    }
}
