package main;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import searcher.BruteForceStringSearcher;
import searcher.KnuthMorrisPratt;
import searcher.RabinKarp;
import searcher.Searcher;
import util.MemoryWatcher;

public class Main {

	private static Searcher searcher;
	private static boolean contains = false;

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

	private static String readPatternFile(String patternFilePath)
			throws IOException {
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
			contains = runBruteForceExperiment(text, pattern);
			break;
		case 2:
			contains = runKnuthMorrisExperiment(text, pattern);
			break;
		case 3:
			contains = runRabinKarpExperiment(text, pattern);
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
		MemoryWatcher memory = new MemoryWatcher();
		Thread thread = new Thread(memory);
		try {
			text = readBaseFile(baseFilePath).replaceAll(
					System.getProperty("line.separator"), "");
			String pattern = readPatternFile(patternFilePath).replaceAll(
					System.getProperty("line.separator"), "");
			;
			thread.start();
			init(approach, text, pattern);

			if (thread != null) {
				memory.stopWatcher();
				thread.join();
			}

			List<Long> values = memory.getMemoryConsumptionValues();
			Collections.sort(values);
			long memoryConsumption = values.get(values.size() / 2);
			long timeOfExecution = searcher.getTimeOfExecution();
			int numberOfOperations = searcher.getNumberOfOperations();

			System.out.println("Texto buscado: " + pattern + " \nTexto_busca: "
					+ text + " \nResultado: " + contains
					+ " \nTempo execução: " + timeOfExecution
					+ " \nConsumo de Memória: "+memoryConsumption+"\n Número de Operações: "+numberOfOperations);

		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
