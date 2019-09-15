package opencc;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.internal.LinkedTreeMap;

public class Config {
	private String name;
//	private LinkedTreeMap<String, ?> segmentation;
	private ArrayList<LinkedTreeMap<String, ?>> conversion_chain;

	public String getName() {
		return name;
	}

	public List<List<String>> getConversionChain() {
		List<List<String>> conversionList = new ArrayList<>();
		for (LinkedTreeMap<String, ?> conversionMap : conversion_chain) {
			List<String> mappingTableList = new ArrayList<>();

			LinkedTreeMap<String, ?> dictMap = (LinkedTreeMap<String, ?>) conversionMap.get("dict");
			if (((String) dictMap.get("type")).contentEquals("group")) {
				ArrayList<LinkedTreeMap<String, ?>> groupDictMapList = (ArrayList<LinkedTreeMap<String, ?>>) dictMap
						.get("dicts");
				for (LinkedTreeMap<String, ?> groupDictMap : groupDictMapList) {
					mappingTableList.add((String) groupDictMap.get("file"));
				}
			} else {
				mappingTableList.add((String) dictMap.get("file"));
			}

			conversionList.add(mappingTableList);
		}

		return conversionList;
	}
}
