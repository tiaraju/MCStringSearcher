package searcher;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class RabinKarp implements Searcher{

	@Override
	public boolean searchPattern (String textToSearch, String patternToSearch) {
		long time1 = Calendar.getInstance().getTimeInMillis();
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
		}
		int hashTexto = 0;
		int hashPadrao = 0;
		for ( int i = 0; i < m && i < n ; i ++) {
			hashTexto = hashTexto * h + text [ i ];
			hashPadrao = hashPadrao * h + pattern [ i ];
		}
		hashPadrao %= primo ;
		if ( hashTexto % primo == hashPadrao ){
			int k = 0;
			while (k < m && pattern [ k ]== text [ k ]){
				k ++;
			}
			if ( k == m ){
				resultados.add(0+"");
			}
		}
		for ( int i = m ; i < n ; i ++) {
			hashTexto = hashTexto - text [i - m ]* base ;
			hashTexto *= h ;
			hashTexto += text [ i ];
			if ( hashTexto % primo == hashPadrao ){
				int k =0;
				while (k < m && pattern [ k ]== text [i - m +1+ k ]){
					k ++;
				}
				if ( k == m ){
					resultados.add(i - m +1+"");
				}
			}
		}
		long time2 = Calendar.getInstance().getTimeInMillis();
		System.out.println("Time: " + (time2 - time1));
		return !resultados.isEmpty() ;
	}
	
}
