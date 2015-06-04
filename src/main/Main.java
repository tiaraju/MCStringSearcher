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
	private static final double FILE_SIZE = 10e8;
	private static int operations=0;

	private static boolean runExperiment(String filePath, String pattern) throws IOException {
		if (searcher != null) {
			BufferedReader reader = new BufferedReader(new FileReader(filePath));
			StringBuilder builder = new StringBuilder();
			try {
				String line = reader.readLine();
				while (line != null) {
					builder.append(line);
					line = reader.readLine();
					if (line != null) {
						builder.append("\n");
					}
					if (builder.length() < FILE_SIZE) {
						contains = contains || searcher.searchPattern(pattern,builder.toString());
						operations+=searcher.getNumberOfOperations();
						builder.delete(0, builder.length());
					}
				}
			} finally {
				reader.close();
			}
		
		}

		return contains;
	}

	private static void init(int option, String baseFilePath, String pattern) throws IOException {
		switch (option) {
		case 1:
			searcher = new BruteForceStringSearcher();
			break;
		case 2:
			searcher = new KnuthMorrisPratt();
			break;
		case 3:
			searcher = new RabinKarp();
			break;
		default:
			System.out.println("Invalid Option. Try Again later.");
			break;
		}
		runExperiment(baseFilePath, pattern);
	}

	public static void main(String[] args) {
		int approach = Integer.parseInt(args[0].trim());
		String patternFilePath = args[1].trim();
		String baseFilePath = args[2].trim();
		MemoryWatcher memory = new MemoryWatcher();
		Thread thread = new Thread(memory);
		String pattern;
		try {
			pattern = FileIO.readFile(patternFilePath).replaceAll(
					System.getProperty("line.separator"), "");
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

			System.out.println("Texto buscado: " + patternFilePath
					+ " \nTexto_busca: " + baseFilePath + " \nResultado: "
					+ contains + " \nTempo execução: " + timeOfExecution
					+ " \nConsumo de Memória: " + memoryConsumption
					+ "\n Número de Operações: " + operations);

		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
