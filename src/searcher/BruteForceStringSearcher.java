package searcher;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class BruteForceStringSearcher implements Searcher{

	private int numberOfOperations;
	
	public BruteForceStringSearcher() {
		this.numberOfOperations = 0;
	}
	@Override
	public boolean searchPattern(String pattern, String text) {
		String[] splittedText = text.split("[\\s,.!\\?:;][\\s]*");
		List<String> result = new ArrayList<String>(); 
		for (String string : splittedText) {
			numberOfOperations++;
			if(string.contains(pattern)){
				result.add(string);
				numberOfOperations++;
			}
		}
		return !result.isEmpty();
	}

	@Override
	public int getNumberOfOperations() {
		return this.numberOfOperations;
	}
	

}
