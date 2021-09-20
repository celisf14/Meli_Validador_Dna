package com.meli.validarDna.Validadordna.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.meli.validarDna.Validadordna.models.DnaTo;
import com.meli.validarDna.Validadordna.models.StatsTo;
import com.meli.validarDna.Validadordna.services.DnaServices;

@RestController
public class DnaController {

	@Autowired
	DnaServices dnaServices;

	@PostMapping("/mutant")
	public ResponseEntity<String> ValidarAdn(@RequestBody DnaTo dnaTo) throws Exception {
		if (dnaServices.isMutant(dnaTo)) {
			return new ResponseEntity<>("isMutant", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("notIsMutant", HttpStatus.FORBIDDEN);
		}
	}

	@GetMapping("/stats")
	public StatsTo getEstadisticas() throws Exception {
		return dnaServices.getEstadistica();
	}

}