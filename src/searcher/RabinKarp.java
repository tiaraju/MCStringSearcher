package searcher;

import java.util.ArrayList;
import java.util.List;

public class RabinKarp implements Searcher{
	
	private int numberOfOperations;
	
	public RabinKarp() {
		this.numberOfOperations=0;
	}

	@Override
	public boolean searchPattern (String textToSearch, String patternToSearch) {
		List<String> resultados = new ArrayList<String>();
		char[] text = textToSearch.toCharArray();
		char[] pattern = patternToSearch.toCharArray();
		int n = text.length ;
		int m = pattern.length ;
		int h = 255;
		int primo = 31;
		int base = 1;
		for ( int i = 0; i < m -1; i ++) {
			base *= h ;
			numberOfOperations++;
		}
		int hashTexto = 0;
		int hashPadrao = 0;
		for ( int i = 0; i < m && i < n ; i ++) {
			hashTexto = hashTexto * h + text [ i ];
			hashPadrao = hashPadrao * h + pattern [ i ];
			numberOfOperations++;
		}
		hashPadrao %= primo ;
		if ( hashTexto % primo == hashPadrao ){
			int k = 0;
			while (k < m && pattern [ k ]== text [ k ]){
				k ++;
				numberOfOperations++;
			}
			if ( k == m ){
				resultados.add(0+"");
				numberOfOperations++;
			}
		}
		for ( int i = m ; i < n ; i ++) {
			hashTexto = hashTexto - text [i - m ]* base ;
			hashTexto *= h ;
			hashTexto += text [ i ];
			numberOfOperations++;
			if ( hashTexto % primo == hashPadrao ){
				int k =0;
				while (k < m && pattern [ k ]== text [i - m +1+ k ]){
					k ++;
					numberOfOperations++;
				}
				if ( k == m ){
					resultados.add(i - m +1+"");
					numberOfOperations++;
				}
			}
		}
		return !resultados.isEmpty() ;
	}

	@Override
	public int getNumberOfOperations() {
		return this.numberOfOperations;
	}

	
}
