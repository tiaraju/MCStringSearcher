package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import searcher.BruteForceStringSearcher;
import searcher.KnuthMorrisPratt;
import searcher.RabinKarp;
import searcher.Searcher;
import util.FileIO;
import util.MemoryWatcher;

public class Main {

	private static Searcher searcher;
	private static boolean contains = false;
	private static String method;

	public static boolean runExperiment(String filePath, String pattern)
			throws IOException {
		if (searcher != null) {
			BufferedReader reader = new BufferedReader(new FileReader(filePath));
			StringBuilder builder = new StringBuilder();

			long patternSize = pattern.length();
			long bufferSize = patternSize * 3;
			try {
				String line = reader.readLine();
				while (line != null) {
					builder.append(line);
					line = reader.readLine();
					if (line != null) {
						builder.append("\n");
					}
					if (builder.length() > bufferSize) {
						contains = contains
								|| searcher.searchPattern(pattern,
										builder.toString());
						builder.delete(0, builder.length() / 3);
					}
				}
				if (pattern.substring(pattern.length() - 1).equals("\n")) {
					builder.append("\n");
				}
				contains = contains
						|| searcher.searchPattern(pattern, builder.toString());
			} finally {
				reader.close();
			}

		}
		return contains;
	}

	public static boolean init(int option, String baseFilePath, String pattern)
			throws IOException {
		contains = false;
		switch (option) {
		case 1:
			searcher = new BruteForceStringSearcher();
			method = "Brute Force";
			break;
		case 2:
			searcher = new KnuthMorrisPratt();
			method = "Knuth-Morris-Pratt";
			break;
		case 3:
			searcher = new RabinKarp(pattern);
			method = "Rabin-Karp";
			break;
		default:
			System.out.println("Invalid Option. Try Again later.");
			break;
		}
		return runExperiment(baseFilePath, pattern);
	}

	public static void main(String[] args) {
		int approach = Integer.parseInt(args[0].trim());
		String patternFilePath = args[1].trim();
		String baseFilePath = args[2].trim();
		MemoryWatcher memory = new MemoryWatcher();
		Thread thread = new Thread(memory);
		String pattern;
		try {
			pattern = FileIO.readFile(patternFilePath);
			thread.start();
			long startTime = Calendar.getInstance().getTimeInMillis();
			init(approach, baseFilePath, pattern);
			long endTime = Calendar.getInstance().getTimeInMillis();
			if (thread != null) {
				memory.stopWatcher();
				thread.join();
			}

			List<Long> values = memory.getMemoryConsumptionValues();
			Collections.sort(values);
			long memoryConsumption = values.get(values.size() / 2);
			long timeOfExecution = endTime - startTime;

		/*	System.out.println("\nTexto buscado: " + patternFilePath
					+ " \nTexto_busca: " + baseFilePath
					+ "\nAlgoritmo Utilizado: " + method + "\nResultado: "
					+ contains + " \nTempo execução: " + timeOfExecution
					+ " mSec" + " \nConsumo de Memória: " + memoryConsumption
					+ " bytes" + "\nNúmero de Operações: "
					+ searcher.getNumberOfOperations());
*/
			System.out.println(patternFilePath+","+baseFilePath+","+method+","+
				contains+","+timeOfExecution+","+memoryConsumption+","+
				searcher.getNumberOfOperations());
			System.exit(1);

		} catch (IOException e) {
			System.out.println("\nThe specified file was not found. Please check the given file's path and try again");
			System.exit(0);
		} catch (InterruptedException e) {
			System.out.println("Eror found: " + e.getMessage());
			System.exit(0);
		}

	}

}
