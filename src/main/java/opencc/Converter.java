package opencc;

import java.util.ArrayList;
import java.util.List;

public class Converter {
	List<Dictionary> dictList = new ArrayList<>();

	public Converter(Iterable<String> mappingTables) {
		for (String mappingTable : mappingTables) {
			Dictionary dict = new Dictionary();
//			System.out.println("Loading... " + mappingTable);
			dict.load(mappingTable);
			dictList.add(dict);
		}
	}

	public StringBuilder convert(String input) {
		StringBuilder stringBuilder = new StringBuilder(input);
		return convert(stringBuilder);
	}

	public StringBuilder convert(StringBuilder input) {
		int index = 0;
		while (index < input.length()) {
			int count = 1;
			boolean converted = false;
			int dictIndex = 0;
			while (!converted && dictIndex < dictList.size()) {
				Dictionary dictionary = dictList.get(dictIndex);
				if (dictionary.isPhrase()) {
					if (dictionary.matchFirstCharacter(input.charAt(index))) {
						int length = Math.min(input.length() - index, dictionary.getMaxKeyLength());
						for (int i = length; i > 0; i--) {
							String sub = input.substring(index, index + i);
							String convertedString = dictionary.getConverted(sub);
							if (convertedString != null) {
								input.replace(index, index + i, convertedString);
								count = convertedString.length();
								converted = true;
								break;
							}
						}
					}
				} else {
					char ch = input.charAt(index);
					String convertedString = dictionary.getConverted(String.valueOf(ch));
					if (convertedString != null) {
						input.replace(index, index + convertedString.length(), convertedString);
						converted = true;
					}
				}
				dictIndex++;
			}

			index += count;
		}

		return input;
	}
}
