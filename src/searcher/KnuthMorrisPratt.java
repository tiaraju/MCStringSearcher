package searcher;

import java.util.ArrayList;
import java.util.List;

public class KnuthMorrisPratt {
	private char[] texto;
	private char[] padrao;
	private int[] prefixos;

	public KnuthMorrisPratt() {
	}

	private List<Integer> search() {
		this.preProcessamento();
		List<Integer> resultado = new ArrayList<Integer>();
		int n = texto.length;
		int m = padrao.length;
		int s = -1;
		for (int i = 0; i <= n - 1; i++) {
			while (s > -1 && padrao[s + 1] != texto[i]) {
				s = prefixos[s];
			}
			if (padrao[s + 1] == texto[i]) {
				s++;
			}
			if (s == m - 1) {
				resultado.add(i - m + 1);
				s = prefixos[s];
			}
		}
		return resultado;
	}

	public int[] calculaPrefixos(char[] P) {
		int m = P.length;
		int[] prefixos = new int[m];
		prefixos[0] = -1;
		for (int i = 1, s = -1; i < m; i++) {
			while (s > -1 && P[s + 1] != P[i]) {
				s = prefixos[s];
			}
			if (P[s + 1] == P[i]) {
				s++;
			}
			prefixos[i] = s;
		}
		return prefixos;
	}

	public void setTexto(String texto) {
		this.texto = texto.toCharArray();
	}

	public void setPadrao(String padrao) {
		this.padrao = padrao.toCharArray();
	}

	private void preProcessamento() {
		this.prefixos = calculaPrefixos(padrao);
	}
	
	
	public static void main(String[] args) {
		KnuthMorrisPratt kmp = new KnuthMorrisPratt();
		kmp.setPadrao("anderson");
		kmp.setTexto("anderson gustavo, tuliom , lari, anderson");
		System.out.println(kmp.search());
		
	}
}
