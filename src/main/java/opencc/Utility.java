package opencc;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class Utility {
	public static InputStream getInputStream(Object obj, String filename) throws FileNotFoundException {
		return obj.getClass().getResourceAsStream(filename);
	}
}
