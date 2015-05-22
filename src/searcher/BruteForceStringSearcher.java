package searcher;

import java.util.ArrayList;
import java.util.List;

public class BruteForceStringSearcher implements Searcher{

	@Override
	public List<String> searchPattern(String pattern, String text) {
		String[] splittedText = text.split("[ ,.!]");
		List<String> result = new ArrayList<String>(); 
		for (String string : splittedText) {
			if(string.contains(pattern)){
				result.add(string);
			}
		}
		return result;
	}
	

}
