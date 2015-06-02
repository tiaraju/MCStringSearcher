package searcher;

import java.util.ArrayList;
import java.util.List;

public class BruteForceStringSearcher implements Searcher{

	@Override
	public boolean searchPattern(String pattern, String text) {
		String[] splittedText = text.split("[\\s,.!\\?:;][\\s]*");
		List<String> result = new ArrayList<String>(); 
		for (String string : splittedText) {
			if(string.contains(pattern)){
				result.add(string);
			}
		}
		return !result.isEmpty();
	}
	

}
