package opencc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Dictionary
 */
public class Dictionary {

	private int maxKeyLength = 1;

	public int getMaxKeyLength() {
		return maxKeyLength;
	}

	private boolean isPhrase;

	public boolean isPhrase() {
		return isPhrase;
	}

	private Set<Character> firstCharacterSet = new HashSet<>();
	private Map<String, String> conversionMap = new HashMap<>();
	
	public boolean matchFirstCharacter(char fistChar) {
		return firstCharacterSet.contains(fistChar);
	}
	
	public String getConverted(String input) {
		return conversionMap.get(input);
	}

	public void load(String dictName) {
		this.isPhrase = dictName.contains("Phrases");
		String filename = "/dictionary/" + dictName;
		try (InputStream inputStream = Utility.getInputStream(this, filename)) {
			try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
				String line = reader.readLine();
				while (line != null) {
					String[] splitLine = line.split("\t");
					String key = splitLine[0];
					String tmpValue = splitLine[1];
					String[] splitValue = tmpValue.split(" ");
					String value = splitValue[0];
					conversionMap.put(key, value);

					if (isPhrase) {
						if (key.length() > maxKeyLength) {
							maxKeyLength = key.length();
						}
						
						firstCharacterSet.add(key.charAt(0));
					}
					line = reader.readLine();
				}
			}
		} catch (IOException e) {
			System.err.println("Failed to read \"" + dictName + "\": " + e.getMessage());
		}
	}

}