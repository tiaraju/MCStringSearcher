package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static test.TestUtil.PALAVRA_1_PATH;
import static test.TestUtil.PALAVRA_2_PATH;
import static test.TestUtil.PALAVRA_3_PATH;
import static test.TestUtil.PALAVRA_4_PATH;
import static test.TestUtil.PALAVRA_5_PATH;
import static test.TestUtil.TEXTO_1_PATH;
import static test.TestUtil.TEXTO_2_PATH;
import static test.TestUtil.getPattern;

import java.io.IOException;

import main.Main;

import org.junit.Test;

public class RabinKarpTest {
	
	private static final int SEARCHER = 3;

	@Test
	public void testContainsTexto1Pattern1() {
		String pattern1 = getPattern(PALAVRA_1_PATH);
		try {
			assertTrue(Main.init(SEARCHER, TEXTO_1_PATH, pattern1));
		} catch (IOException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testContainsTexto1Pattern4() {
		String pattern4 = getPattern(PALAVRA_4_PATH);
		try {
			assertTrue(Main.init(SEARCHER, TEXTO_1_PATH, pattern4));
		} catch (IOException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testContainsTexto2Pattern2() {
		String pattern2 = getPattern(PALAVRA_2_PATH);
		try {
			assertTrue(Main.init(SEARCHER, TEXTO_2_PATH, pattern2));
		} catch (IOException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testContainsTexto2Pattern3() {
		String pattern3 = getPattern(PALAVRA_3_PATH);
		try {
			assertTrue(Main.init(SEARCHER, TEXTO_2_PATH, pattern3));
		} catch (IOException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testContainsTexto2Pattern5() {
		String pattern5 = getPattern(PALAVRA_5_PATH);
		try {
			assertTrue(Main.init(SEARCHER, TEXTO_2_PATH, pattern5));
		} catch (IOException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testPatternContainsText() {
		String pattern = getPattern(TEXTO_1_PATH);
		try {
			assertFalse("O pattern não deveria conter o texto", 
					Main.init(SEARCHER, PALAVRA_1_PATH, pattern));
		} catch (IOException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testContainsItself() {
		String pattern = getPattern(PALAVRA_1_PATH);
		try {
			assertTrue(Main.init(SEARCHER, PALAVRA_1_PATH, pattern));
		} catch (IOException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testContainsItselfHeavyLoad() {
		String pattern = getPattern(TEXTO_1_PATH);
		try {
			assertTrue(Main.init(SEARCHER, TEXTO_1_PATH, pattern));
		} catch (IOException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
}
