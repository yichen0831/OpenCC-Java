package opencc;

import java.util.ArrayList;
import java.util.List;

/**
 * OpenCC
 */
public class OpenCC {

	private List<Converter> converterList = new ArrayList<>();
	private boolean isConversionSet;

	public void setConversion(String conversion) {
		loadConfig(conversion);
		isConversionSet = true;
	}

	private void loadConfig(String conversion) {
		ConfigLoader configLoader = new ConfigLoader();
		Config config = configLoader.load(conversion);
//		System.out.println(config.getName());
		List<List<String>> conversionList = config.getConversionChain();
		converterList.clear();
		for (List<String> dictNameList : conversionList) {
			Converter converter = new Converter(dictNameList);
			converterList.add(converter);
		}
	}

	public String convert(String input) {
		if (!isConversionSet) {
			setConversion("s2t");
		}
		
		StringBuilder stringBuilder = new StringBuilder(input);
		for(Converter converter : converterList) {
			stringBuilder = converter.convert(stringBuilder);
		}
		return stringBuilder.toString();
	}
}