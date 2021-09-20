package com.meli.validarDna.Validadordna.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ValidadorDnaTest {
	ValidadorDna validadorDna;

	@Test
	void isDnaMutantIsTrue() throws Exception {
		validadorDna = new ValidadorDna();
		String[] dna = { "ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG" };
		Assertions.assertEquals(true, validadorDna.isDnaMutant(dna));
	}

	@Test
	void isDnaMutantIsFalse() throws Exception {
		validadorDna = new ValidadorDna();
		String[] dna = { "ATGCGA", "CAGTGC" };
		Assertions.assertEquals(false, validadorDna.isDnaMutant(dna));
	}

	@Test
	void isDnaMutantErrorMatriz() throws Exception {
		validadorDna = new ValidadorDna();
		String[] dna = { "ATGCGA", "CAGTGCCT" };
		Assertions.assertEquals(false, validadorDna.isDnaMutant(dna));
	}

	@Test
	void isDnaMutantColumnZero() throws Exception {
		validadorDna = new ValidadorDna();
		String[] dna = { "" };
		Assertions.assertEquals(false, validadorDna.isDnaMutant(dna));
	}

	@Test
	void isDnaMutantColumnNull() throws Exception {
		validadorDna = new ValidadorDna();
		Assertions.assertEquals(false, validadorDna.isDnaMutant(null));
	}

	@Test
	void isDnaMutantDiagonalDerecha() throws Exception {
		validadorDna = new ValidadorDna();
		String[] dna = { "AGTGCT", "TTGTAT", "AGCCCT", "GGCATG" };
		Assertions.assertEquals(false, validadorDna.isDnaMutant(dna));
	}

}
