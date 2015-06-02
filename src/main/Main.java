package main;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import searcher.BruteForceStringSearcher;
import searcher.KnuthMorrisPratt;
import searcher.RabinKarp;
import searcher.Searcher;

public class Main {

	private static Searcher searcher;

	private static List<String> runBruteForceExperiment(String text,
			String pattern) {
		searcher = new BruteForceStringSearcher();
		return searcher.searchPattern(pattern, text);

	}

	private static List<String> runKnuthMorrisExperiment(String text,
			String pattern) {
		searcher = new KnuthMorrisPratt();
		return searcher.searchPattern(pattern, text);
	}

	private static List<String> runRabinKarpExperiment(String text,
			String pattern) {
		searcher = new RabinKarp();
		return searcher.searchPattern(pattern, text);

	}

	private static String readPatternFile(String patternFilePath) throws IOException {
		 byte[] encoded = Files.readAllBytes(Paths.get(patternFilePath));
		 return new String(encoded, Charset.defaultCharset());
	}

	private static String readBaseFile(String baseFilePath) throws IOException {
		 byte[] encoded = Files.readAllBytes(Paths.get(baseFilePath));
		 return new String(encoded, Charset.defaultCharset());
	}

	private static void init(int option, String text, String pattern) {
		switch (option) {
		case 1:
			System.out.println(runBruteForceExperiment(text, pattern));
			break;
		case 2:
			System.out.println(runKnuthMorrisExperiment(text, pattern));
			break;
		case 3:
			runRabinKarpExperiment(text, pattern);
			break;
		default:
			System.out.println("Invalid Option. Try Again later.");
			break;
		}
	}

	public static void main(String[] args) {
		int approach = Integer.parseInt(args[0].trim());

		String patternFilePath = args[1].trim();
		String baseFilePath = args[2].trim();

		String text;
		try {
			text = readBaseFile(baseFilePath);
			String pattern = readPatternFile(patternFilePath);

			init(approach, text, pattern); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
