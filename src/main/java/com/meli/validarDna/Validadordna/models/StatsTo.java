package com.meli.validarDna.Validadordna.models;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class StatsTo {
	
	private BigDecimal countMutantDna;
	private BigDecimal countHumanDna;
	private BigDecimal ratio;

}
