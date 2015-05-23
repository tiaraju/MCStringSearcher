package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.security.InvalidParameterException;

public class FileIO {
	
	
	/**
	 * reads a given file and returns its content as a single string
	 * @return
	 * @throws IOException 
	 */
	public String readFile(String filePath) throws IOException{
		if(filePath == null || filePath.isEmpty()){
			throw new InvalidParameterException();
		}
		File file = new File(filePath);
		String result = "";
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line = reader.readLine();
		while(line != null){
			result += line+" ";
			line = reader.readLine();
		}
		reader.close();
		return result;
		
	}

}
