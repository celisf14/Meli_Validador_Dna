package com.meli.validarDna.Validadordna.controllers;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.meli.validarDna.Validadordna.models.DnaModel;
import com.meli.validarDna.Validadordna.models.DnaTo;
import com.meli.validarDna.Validadordna.repositories.DnaRepository;

@ExtendWith(value = SpringExtension.class)
@SpringBootTest
public class DnaControllerTest {

	@Autowired
	DnaController dnaController;

	DnaRepository dnaRepositoryMock = Mockito.mock(DnaRepository.class);

	@BeforeEach
	void setUp() {

		// prueba 1
		String[] dna = { "ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG" };
		String dnaString = String.join(";", dna);
		DnaModel consultaDanAntesMok = new DnaModel();
		DnaModel consultaDanDespuesMok = new DnaModel();
		consultaDanAntesMok.setDna(dnaString);
		consultaDanAntesMok.setIsMutant("S");

		consultaDanDespuesMok.setId((long) 1);
		consultaDanDespuesMok.setDna(dnaString);
		consultaDanDespuesMok.setIsMutant("S");
		Mockito.when(dnaRepositoryMock.findByDna(dnaString)).thenReturn(null);
		Mockito.when(dnaRepositoryMock.save(consultaDanAntesMok)).thenReturn(consultaDanDespuesMok);

		// prueba 2
		String[] dna2 = { "ATGCGA", "CAGTGC" };
		String dnaString2 = String.join(";", dna2);
		DnaModel consultaDanAntesMok2 = new DnaModel();
		DnaModel consultaDanDespuesMok2 = new DnaModel();
		consultaDanAntesMok.setDna(dnaString2);
		consultaDanAntesMok.setIsMutant("N");

		consultaDanDespuesMok2.setId((long) 1);
		consultaDanDespuesMok2.setDna(dnaString2);
		consultaDanDespuesMok2.setIsMutant("N");
		Mockito.when(dnaRepositoryMock.findByDna(dnaString2)).thenReturn(null);
		Mockito.when(dnaRepositoryMock.save(consultaDanAntesMok2)).thenReturn(consultaDanDespuesMok2);

		// prueba 3

		String[] dna3 = { "AAAATTTT" };
		String dnaString3 = String.join(";", dna3);
		DnaModel consultaDanMok3 = new DnaModel();
		List<DnaModel> resultado = new ArrayList<>();

		consultaDanMok3.setId((long) 1);
		consultaDanMok3.setDna(dnaString3);
		consultaDanMok3.setIsMutant("S");
		resultado.add(consultaDanMok3);
		Mockito.when(dnaRepositoryMock.findByDna(dnaString3)).thenReturn(resultado);

		// prueba 4

		String[] dna4 = { "ATGCGA", "CAGTGC" };
		String dnaString4 = String.join(";", dna4);
		DnaModel consultaDanMok4 = new DnaModel();
		List<DnaModel> resultado4 = new ArrayList<>();

		consultaDanMok4.setId((long) 1);
		consultaDanMok4.setDna(dnaString3);
		consultaDanMok4.setIsMutant("N");
		resultado4.add(consultaDanMok4);
		Mockito.when(dnaRepositoryMock.findByDna(dnaString4)).thenReturn(resultado4);

	}

	@Test
	void validarAdnIsMutant() throws Exception {
		DnaTo dnaTO = new DnaTo();
		dnaTO.setDna(new String[] { "ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG" });
		ResponseEntity<String> response;
		response = dnaController.ValidarAdn(dnaTO);
		Assertions.assertEquals(new ResponseEntity<>("isMutant", HttpStatus.OK), response);
	}

	@Test
	void validarAdnNotIsMutant() throws Exception {
		DnaTo dnaTO2 = new DnaTo();
		dnaTO2.setDna(new String[] { "ATGCGA", "CAGTGC" });
		ResponseEntity<String> response;
		response = dnaController.ValidarAdn(dnaTO2);
		Assertions.assertEquals(new ResponseEntity<>("notIsMutant", HttpStatus.FORBIDDEN), response);
	}

	@Test
	void validarAdnIsMutantExists() throws Exception {
		DnaTo dnaTO = new DnaTo();
		dnaTO.setDna(new String[] { "AAAATTTT" });
		ResponseEntity<String> response;
		response = dnaController.ValidarAdn(dnaTO);
		Assertions.assertEquals(new ResponseEntity<>("isMutant", HttpStatus.OK), response);
	}

	@Test
	void validarAdnNotIsMutantExists() throws Exception {
		DnaTo dnaTO2 = new DnaTo();
		dnaTO2.setDna(new String[] { "ATGC", "ATGC" });
		ResponseEntity<String> response;
		response = dnaController.ValidarAdn(dnaTO2);
		Assertions.assertEquals(new ResponseEntity<>("notIsMutant", HttpStatus.FORBIDDEN), response);
	}

	@Test
	void getEstadisticasTest() throws Exception {
		Assertions.assertNotNull(dnaController.getEstadisticas());
	}

}
