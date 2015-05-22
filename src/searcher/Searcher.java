package searcher;

import java.util.List;

public interface Searcher {
	
	public List<String> searchPattern(String pattern, String text);

}
