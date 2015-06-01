package main;

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

	private static String readPatternFile(String patternFilePath) {
		// TODO return a string containing the whole file's content
		return null;
	}

	private static String readBaseFile(String baseFilePath) {
		// TODO return a string containing the whole file's content
		return null;
	}

	private static void init(int option, String text, String pattern) {
		switch (option) {
		case 1:
			runBruteForceExperiment(text, pattern);
			break;
		case 2:
			runKnuthMorrisExperiment(text, pattern);
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
		int approach = Integer.parseInt(args[1].trim());

		String patternFilePath = args[2].trim();
		String baseFilePath = args[3].trim();

		String text = readBaseFile(baseFilePath);
		String pattern = readPatternFile(patternFilePath);

		init(approach, text, pattern);
	}

}
