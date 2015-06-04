package test;

import static org.junit.Assert.fail;

import java.io.IOException;

import util.FileIO;

public class TestUtil {
	
	public static final String TEXTO_1_PATH = "texto1.txt";
	public static final String TEXTO_2_PATH = "texto2.txt";
	public static final String PALAVRA_1_PATH = "palavra1.txt";
	public static final String PALAVRA_2_PATH = "palavra2.txt";
	public static final String PALAVRA_3_PATH = "palavra3.txt";
	public static final String PALAVRA_4_PATH = "palavra4.txt";
	public static final String PALAVRA_5_PATH = "palavra5.txt";
	
	public static String getPattern(String path){
		String pattern = null;
		try {
			pattern = FileIO.readFile(path);
		} catch (IOException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		return pattern;
	}

}
