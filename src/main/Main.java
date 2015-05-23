package main;

import searcher.BruteForceStringSearcher;
import searcher.Searcher;

public class Main {

	Searcher searcher;

	private void runBruteForceExperiment() {
		searcher = new BruteForceStringSearcher();
		
	}

	private void runBoyerMooreExperiment() {

	}

	private void runWagnerFisherExperiment() {

	}

	public static void main(String[] args) {
		int approach = Integer.parseInt(args[1].trim());
		String patternFilePath = args[2];
		String baseFilePath = args[3];
		
		switch (approach) {
		case 1:
			
			break;
		case 2:
			break;
			
		case 3:
			break;
		default:
			break;
		}
	}

}
