package com.meli.validarDna.Validadordna.services;

import com.meli.validarDna.Validadordna.models.DnaModel;
import com.meli.validarDna.Validadordna.models.DnaTo;
import com.meli.validarDna.Validadordna.models.StatsTo;

public interface DnaServices {
	public boolean isMutant(DnaTo dnaTo) throws Exception;

	public StatsTo getEstadistica() throws Exception;

	public DnaModel consultarDna(String dna) throws Exception;

	public DnaModel guardarRegistroDna(DnaModel dnaModel) throws Exception;

	public Long contarIsMutant(String tipo) throws Exception;
}
