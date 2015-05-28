package searcher;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class RabinKarp {
	public static String texto2 = "anderson gustavo, tuliom , lari, anderson";
	public static String padrao2 = "anderson";

	public static char[] texto = texto2.toCharArray();
	public static char[] padrao = padrao2.toCharArray();

	public static List<Integer> search () {
		long time1 = Calendar.getInstance().getTimeInMillis();
		List<Integer> resultados = new ArrayList<Integer>();
		int n = texto.length ;
		int m = padrao.length ;
		int h = 255;
		int primo = 31;
		int base = 1;
		for ( int i = 0; i < m -1; i ++) {
			base *= h ;
		}
		int hashTexto = 0;
		int hashPadrao = 0;
		for ( int i = 0; i < m && i < n ; i ++) {
			hashTexto = hashTexto * h + texto [ i ];
			hashPadrao = hashPadrao * h + padrao [ i ];
		}
		hashPadrao %= primo ;
		if ( hashTexto % primo == hashPadrao ){
			int k = 0;
			while (k < m && padrao [ k ]== texto [ k ]){
				k ++;
			}
			if ( k == m ){
				resultados.add(0);
			}
		}
		for ( int i = m ; i < n ; i ++) {
			hashTexto = hashTexto - texto [i - m ]* base ;
			hashTexto *= h ;
			hashTexto += texto [ i ];
			if ( hashTexto % primo == hashPadrao ){
				int k =0;
				while (k < m && padrao [ k ]== texto [i - m +1+ k ]){
					k ++;
				}
				if ( k == m ){
					resultados.add(i - m +1);
				}
			}
		}
		long time2 = Calendar.getInstance().getTimeInMillis();
		System.out.println("Time: " + (time2 - time1));
		return resultados ;
	}
	
	public static void main(String[] args) {
		System.out.println(RabinKarp.search());
		
	}
}
