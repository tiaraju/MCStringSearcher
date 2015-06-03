package searcher;


public interface Searcher {
	
	public boolean searchPattern(String pattern, String text);
	
	public int getNumberOfOperations();
	
	public long getTimeOfExecution();

}
