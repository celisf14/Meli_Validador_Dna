package com.meli.validarDna.Validadordna.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meli.validarDna.Validadordna.general.Constantes;
import com.meli.validarDna.Validadordna.models.DnaModel;
import com.meli.validarDna.Validadordna.models.DnaTo;
import com.meli.validarDna.Validadordna.models.StatsTo;
import com.meli.validarDna.Validadordna.repositories.DnaRepository;
import com.meli.validarDna.Validadordna.validator.ValidadorDna;

@Service
public class DnaServiceImpl implements DnaServices {

	@Autowired
	DnaRepository dnaRepository;

	@Autowired
	ValidadorDna validadorDna;

	DnaModel dnaModel;

	public DnaServiceImpl() {
	}

	/**
	 * isMutant - Metodo que permite detectar si un humano es mutante basándose en
	 * su secuencia de ADN
	 * 
	 * @param DnaTO
	 */
	@Override
	public boolean isMutant(DnaTo dnaTo) throws Exception {
		boolean validacion = true;

		String[] dna = dnaTo.getDna();
		String dnaString = String.join(";", dna);
		dnaModel = consultarDna(dnaString);

		if (dnaModel != null && dnaModel.getIsMutant() != null) {
			if (dnaModel.getIsMutant().equalsIgnoreCase(Constantes.SI)) {
				validacion = true;
			} else {
				validacion = false;
			}

		} else {
			dnaModel = new DnaModel();
			dnaModel.setDna(dnaString);
			if (validadorDna.isDnaMutant(dna)) {
				dnaModel.setIsMutant(Constantes.SI);
				validacion = true;
			} else {
				dnaModel.setIsMutant(Constantes.NO);
				validacion = false;
			}
			dnaModel = guardarRegistroDna(dnaModel);

		}

		return validacion;
	}

	/**
	 * getEstadistica - Servivcio que consulta estadísticas de las verificaciones
	 * 
	 * @return
	 * @throws Exception
	 */
	@Override
	public StatsTo getEstadistica() throws Exception {
		Long mutant;
		Long human;
		BigDecimal ratio;
		StatsTo statsTo = null;

		mutant = contarIsMutant(Constantes.SI);
		human = contarIsMutant(Constantes.NO);

		statsTo = new StatsTo();

		if (mutant == null)
			mutant = (long) 0;

		if (human == null)
			human = (long) 0;

		statsTo.setCountMutantDna(new BigDecimal(mutant));
		statsTo.setCountHumanDna(new BigDecimal(human));

		if (human != 0)
			ratio = statsTo.getCountMutantDna().divide(statsTo.getCountHumanDna(), 2, RoundingMode.DOWN);
		else
			ratio = new BigDecimal(0);

		statsTo.setRatio(ratio);

		return statsTo;
	}

	/**
	 * consultarDna - Servicio que consulta un Dna guardado en base de datos
	 * 
	 * @param dna
	 * @return
	 */
	@Override
	public DnaModel consultarDna(String dna) throws Exception {

		DnaModel dnaModelConsulta = new DnaModel();
		List<DnaModel> resultado = dnaRepository.findByDna(dna);
		if (resultado != null && resultado.size() > 0) {
			dnaModelConsulta = (DnaModel) resultado.get(0);
			return dnaModelConsulta;
		}
		return null;
	}

	/**
	 * guardarRegistroDna - Servicio que permite gusradar un registro en base de
	 * daotos
	 * 
	 * @param dnaModel
	 * @return
	 */
	@Override
	public DnaModel guardarRegistroDna(DnaModel dnaModel) throws Exception {
		return dnaRepository.save(dnaModel);
	}

	/**
	 * contarIsMutante - Servicio que realiza un coutn a base de Datos filtrando por
	 * el campos isMutante
	 */
	@Override
	public Long contarIsMutant(String tipo) throws Exception {
		return dnaRepository.countByIsMutant(tipo);
	}

}
