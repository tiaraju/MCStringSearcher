package main;

import java.io.IOException;

import searcher.BruteForceStringSearcher;
import searcher.KnuthMorrisPratt;
import searcher.RabinKarp;
import searcher.Searcher;
import util.FileIO;

public class Main {

	private static Searcher searcher;

	private static boolean runBruteForceExperiment(String text, String pattern) {
		searcher = new BruteForceStringSearcher();
		return searcher.searchPattern(pattern, text);

	}

	private static boolean runKnuthMorrisExperiment(String text, String pattern) {
		searcher = new KnuthMorrisPratt();
		return searcher.searchPattern(pattern, text);
	}

	private static boolean runRabinKarpExperiment(String text, String pattern) {
		searcher = new RabinKarp();
		return searcher.searchPattern(pattern, text);

	}

	private static boolean isNumeric(String str) {
		try {
			Integer.parseInt(str);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
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
			System.out.println(runRabinKarpExperiment(text, pattern));
			break;
		default:
			System.out.println("Invalid Option. Try Again later.");
			break;
		}
	}

	public static void main(String[] args) {
		if (args[0].isEmpty() || args[1].isEmpty() || args[2].isEmpty()) {
			System.out.println("Missing Parameter. Please try again.");
			return;
		}
		if (!isNumeric(args[0].trim())) {
			System.out.println("The first argument should be an integer");
			return;
		}

		int approach = Integer.parseInt(args[0].trim());
		String patternFilePath = args[1].trim();
		String baseFilePath = args[2].trim();
		String text, pattern;
		try {
			text = FileIO.readFile(baseFilePath).replaceAll(
					System.getProperty("line.separator"), "");
			pattern = FileIO.readFile(patternFilePath).replaceAll(
					System.getProperty("line.separator"), "");
			init(approach, text, pattern);
		} catch (IOException e) {
			System.err.println("FileNotFound: " + e.getMessage());
		}

	}

}
