package searcher;

import java.math.BigInteger;
import java.util.Random;

public class RabinKarp implements Searcher {
	private String pat; // the pattern // needed only for Las Vegas
	private long patHash; // pattern hash value
	private int M; // pattern length
	private long Q; // a large prime, small enough to avoid long overflow
	private int R; // radix
	private long RM; // R^(M-1) % Q
	private int numberOfOperations;

	public RabinKarp(String pat) {
		this.pat = pat; // save pattern (needed only for Las Vegas)
		R = 256;
		M = pat.length();
		Q = longRandomPrime();
		this.numberOfOperations = 0;

		// precompute R^(M-1) % Q for use in removing leading digit
		RM = 1;
		for (int i = 1; i <= M - 1; i++) {
			RM = (R * RM) % Q;
			this.numberOfOperations++;
		}
		patHash = hash(pat, M);
	}

	// Compute hash for key[0..M-1].
	private long hash(String key, int M) {
		long h = 0;
		for (int j = 0; j < M; j++) {
			h = (R * h + key.charAt(j)) % Q;
			this.numberOfOperations++;
		}
		return h;
	}

	// Las Vegas version: does pat[] match txt[i..i-M+1] ?
	private boolean check(String txt, int i) {
		for (int j = 0; j < M; j++) {
			this.numberOfOperations++;
			if (pat.charAt(j) != txt.charAt(i + j))
				return false;
		}
		return true;
	}

	// check for exact match
	public boolean searchPattern(String pattern, String txt) {
		this.pat = pattern;
		int N = txt.length();
		if (N < M)
			return false;
		long txtHash = hash(txt, M);

		// check for match at offset 0
		if ((patHash == txtHash) && check(txt, 0))
			return true;

		// check for hash match; if hash match, check for exact match
		for (int i = M; i < N; i++) {
			// Remove leading digit, add trailing digit, check for match.
			txtHash = (txtHash + Q - RM * txt.charAt(i - M) % Q) % Q;
			txtHash = (txtHash * R + txt.charAt(i)) % Q;
			this.numberOfOperations++;

			// match
			int offset = i - M + 1;
			if ((patHash == txtHash) && check(txt, offset))
				return true;
		}

		// no match
		return false;
	}

	// a random 31-bit prime
	private static long longRandomPrime() {
		BigInteger prime = BigInteger.probablePrime(31, new Random());
		return prime.longValue();
	}

	@Override
	public int getNumberOfOperations() {
		return this.numberOfOperations;
	}

}
