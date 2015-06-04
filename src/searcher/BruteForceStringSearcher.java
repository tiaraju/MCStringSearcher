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
		return text.contains(pattern);
	}

	@Override
	public int getNumberOfOperations() {
		return this.numberOfOperations;
	}
	

}
