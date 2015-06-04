package searcher;



public class BruteForceStringSearcher implements Searcher{

	private int numberOfOperations;
	
	public BruteForceStringSearcher() {
		this.numberOfOperations = 0;
	}
	@Override
	public boolean searchPattern(String pattern, String text) {
		boolean contains = false;
		if(pattern != null && text != null){
			int n = text.length();
			int m = pattern.length();
			for (int i = 0; i < n - m + 1; i++) {
				int j = 0;
				while (j < m && text.charAt(i + j) == pattern.charAt(j)) {
					j++;
					this.numberOfOperations++;
				}
				if (j == m) {
					contains = true;
				}
				this.numberOfOperations++;
			}
		}		
		return contains;
	}

	@Override
	public int getNumberOfOperations() {
		return this.numberOfOperations;
	}
	

}
