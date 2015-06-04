package util;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidParameterException;

public class FileIO {
	
	
	/**
	 * reads a given file and returns its content as a single string
	 * @return
	 * @throws IOException 
	 */
	public static String readFile(String filePath) throws IOException{
		if(filePath == null || filePath.isEmpty()){
			throw new InvalidParameterException();
		}
		byte[] encoded = Files.readAllBytes(Paths.get(filePath));
		return new String(encoded, Charset.defaultCharset());
		
	}

}
