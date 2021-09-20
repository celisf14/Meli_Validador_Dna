package com.meli.validarDna.Validadordna.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.meli.validarDna.Validadordna.models.DnaTo;
import com.meli.validarDna.Validadordna.models.StatsTo;

@ExtendWith(value = SpringExtension.class)
@SpringBootTest
public class DnaServiceTest {

	@Autowired
	DnaServiceImpl dnaService;

	DnaTo dnaTo;

	@Test
	void isMutantTrue() throws Exception {
		try {
			dnaTo = new DnaTo();
			String[] dna = { "ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG" };
			dnaTo.setDna(dna);
			Assertions.assertTrue(dnaService.isMutant(dnaTo));

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	@Test
	void isMutantFalse() throws Exception {
		try {
			dnaTo = new DnaTo();
			String[] dna = { "ATGCGA" };
			dnaTo.setDna(dna);
			Assertions.assertFalse(dnaService.isMutant(dnaTo));

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Test
	void getEstadistica() throws Exception {
		try {
			StatsTo statsTo = new StatsTo();
			statsTo = dnaService.getEstadistica();
			Assertions.assertNotNull(statsTo);
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	/*
	 * 
	 * getEstadistica
	 * 
	 * @Test void dnaRepositorySave() throws Exception {
	 * 
	 * try { String[] dna = { "ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA",
	 * "TCACTG" }; String dnaString = String.join(";", dna); DnaModel dnaModel = new
	 * DnaModel(); dnaModel.setId(1L); dnaModel.setDna(dnaString);
	 * dnaModel.setIsMutant("S"); dnaModel = dnaRepository.save(dnaModel);
	 * Assertions.assertEquals(dnaModel, dnaModel); } catch (Exception e) {
	 * System.out.println(e); }
	 * 
	 * }
	 * 
	 */

}
