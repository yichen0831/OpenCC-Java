package opencc;

import java.io.IOException;
import java.io.InputStreamReader;

import com.google.gson.Gson;

public class ConfigLoader {
	public Config load(String conversion) {
		Config config = null;

		String filename = "/config/" + conversion + ".json";
		try (InputStreamReader reader = new InputStreamReader(Utility.getInputStream(this, filename))) {
			Gson gson = new Gson();
			config = gson.fromJson(reader, Config.class);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return config;
	}
}
