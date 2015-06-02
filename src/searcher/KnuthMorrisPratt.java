package searcher;

import java.util.ArrayList;
import java.util.List;

public class KnuthMorrisPratt implements Searcher{
	private int[] prefixos;
	private char[] pattern;

	@Override
	public boolean searchPattern(String patternToSearch, String textToSearch) {
		pattern = patternToSearch.toCharArray();
		char[] text = textToSearch.toCharArray();
		this.preProcessamento();
		List<String> resultado = new ArrayList<String>();
		int n = text.length;
		int m = pattern.length;
		int s = -1;
		for (int i = 0; i <= n - 1; i++) {
			while (s > -1 && pattern[s + 1] != text[i]) {
				s = prefixos[s];
			}
			if (pattern[s + 1] == text[i]) {
				s++;
			}
			if (s == m - 1) {
				resultado.add(i - m + 1+"");
				s = prefixos[s];
			}
		}
		return !resultado.isEmpty();
	}

	private int[] calculaPrefixos(char[] P) {
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


	private void preProcessamento() {
		this.prefixos = calculaPrefixos(pattern);
	}
	
	
}
