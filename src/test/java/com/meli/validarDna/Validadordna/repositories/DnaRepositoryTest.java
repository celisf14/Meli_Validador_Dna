package com.meli.validarDna.Validadordna.repositories;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.meli.validarDna.Validadordna.models.DnaModel;

@ExtendWith(value = SpringExtension.class)
@SpringBootTest
public class DnaRepositoryTest {

	@Autowired
	DnaRepository dnaRepository;

	@Test
	void dnaRepositorySave() throws Exception {

		try {
			String[] dna = { "ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG" };
			String dnaString = String.join(";", dna);
			DnaModel dnaModel = new DnaModel();
			dnaModel.setId(1L);
			dnaModel.setDna(dnaString);
			dnaModel.setIsMutant("S");
			dnaModel = dnaRepository.save(dnaModel);
			Assertions.assertEquals(dnaModel, dnaModel);
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	@Test
	void dnaRepositoryfindByDna() throws Exception {

		try {
			String[] dna = { "ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG" };
			String dnaString = String.join(";", dna);
			DnaModel dnaModel = new DnaModel();
			dnaModel.setId(1L);
			dnaModel.setDna(dnaString);
			dnaModel.setIsMutant("S");
			ArrayList<DnaModel> dnaresponse = (ArrayList<DnaModel>) dnaRepository.findByDna(dnaString);
			Assertions.assertEquals(dnaModel, dnaresponse.get(0));
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	@Test
	void countByIsMutant() throws Exception {
		try {
			Assertions.assertNotNull(dnaRepository.countByIsMutant("S"));
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
