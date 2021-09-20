package com.meli.validarDna.Validadordna.validator;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.meli.validarDna.Validadordna.general.Constantes;

import lombok.extern.slf4j.Slf4j;

@Component("ValidadorDna")
@Slf4j
public class ValidadorDna {

	private int horizontal = 0;
	private int vertical = 0;
	private int diagonalIzquierda = 0;
	private int diagonalDerecha = 0;
	private int filas = 0;
	private int columnas = 0;
	private int secuenciasEncontradas = 0;

	public ValidadorDna() {

	}

	/**
	 * isDnaMutant - valida valida y busca DNA mutante
	 *
	 * @param dna
	 * @return
	 * @throws Exception
	 */
	public boolean isDnaMutant(String[] dna) throws Exception {
		boolean dnaMutant = true;
		try {
			this.inicializarVariables();
			if (!this.validaMatriz(dna))
				return false;

			if (!this.buscaSecuenciasMatrix(dna))
				return false;

		} catch (Exception e) {
			log.error("isDnaMutant: " + e);
			return false;
		}

		return dnaMutant;
	}

	/**
	 * validaMatriz - Metodo encargado de validar las dimenciones y consistencia de
	 * la matrix con la que se va a trabajar
	 * <p>
	 * Todas las filas deben ser del mismo tamaño
	 *
	 * @param dna
	 * @return
	 * @throws Exception
	 */
	private boolean validaMatriz(String[] dna) throws Exception {
		boolean validacion = true;

		this.filas = dna[0].length();
		this.columnas = dna.length;

		// valida tamaño minimos de la matriz
		if ((this.filas < Constantes.LONGITUD_SECUENCIA && this.columnas < Constantes.LONGITUD_SECUENCIA)
				|| this.filas == Constantes.CERO || this.columnas == Constantes.CERO) {
			return false;
		}

		// valida consistencia de la matriz
		for (int i = 0; i < this.columnas; i++) {
			if (this.filas != dna[i].length() || !Pattern.matches(Constantes.SECUENCIA_VALIDA, dna[i])) {
				return false;
			}
		}

		return validacion;
	}

	/**
	 * buscaSecuenciasMatrix - Recorre uno a uno los campos de la matriz en busca de
	 * secuencias mutantes hasta completar el minimo requerido, retorna true si
	 * encuentra las secuencias solicitadas en caso contrario retorna false
	 *
	 * @param arreglo
	 * @return
	 * @throws Exception
	 */
	private boolean buscaSecuenciasMatrix(String[] arreglo) throws Exception {

		// recorre la matriz
		for (int i = Constantes.CERO; i < columnas; i++) {
			for (int j = Constantes.CERO; j < filas; j++) {

				this.validaCampo(arreglo, i, j);

				if (this.secuenciasEncontradas >= Constantes.TOTAL_SECUENCIAS_REQUERIDAS)
					return true;

			}
		}

		if (this.secuenciasEncontradas >= Constantes.TOTAL_SECUENCIAS_REQUERIDAS)
			return true;
		else
			return false;
	}

	/**
	 * validaCampo - a partir de un campo en la matriz lo recorre en todas las
	 * direcciones en busca de una secuencia mutante
	 *
	 * @param arreglo
	 * @param i
	 * @param j
	 */
	private void validaCampo(String[] arreglo, int i, int j) {

		char aux = arreglo[i].charAt(j);
		this.inicializarContadores();
		for (int k = Constantes.UNO; k < Constantes.LONGITUD_SECUENCIA; k++) {

			// busca horizontal -
			this.buscaHorizontal(arreglo, aux, i, j, k);

			// busca vertical |
			this.buscaVertical(arreglo, aux, i, j, k);

			// busca diagonal Izquierda\
			this.buscaDiagonalIzquierda(arreglo, aux, i, j, k);

			// busca diagonal Derecha /
			this.buscaDiagonalDerecha(arreglo, aux, i, j, k);
		}

		this.validaSecuenciasEncontradas();
	}

	/**
	 * buscaHorizontal
	 *
	 * @param arreglo
	 * @param aux
	 * @param i
	 * @param j
	 * @param k
	 */
	private void buscaHorizontal(String[] arreglo, char aux, int i, int j, int k) {

		if (j <= filas - Constantes.LONGITUD_SECUENCIA) {
			if (aux == arreglo[i].charAt(j + k)) {
				this.horizontal += Constantes.UNO;
			}
		}
	}

	/**
	 * buscaVertical
	 *
	 * @param arreglo
	 * @param aux
	 * @param i
	 * @param j
	 * @param k
	 */
	private void buscaVertical(String[] arreglo, char aux, int i, int j, int k) {

		if (i <= columnas - Constantes.LONGITUD_SECUENCIA) {
			if (aux == arreglo[i + k].charAt(j)) {
				this.vertical += Constantes.UNO;
			}
		}
	}

	/**
	 * buscaDiagonalIzquierda
	 *
	 * @param arreglo
	 * @param aux
	 * @param i
	 * @param j
	 * @param k
	 */
	private void buscaDiagonalIzquierda(String[] arreglo, char aux, int i, int j, int k) {

		if (j <= filas - Constantes.LONGITUD_SECUENCIA && i <= columnas - Constantes.LONGITUD_SECUENCIA) {
			if (aux == arreglo[i + k].charAt(j + k)) {
				this.diagonalIzquierda += Constantes.UNO;
			}
		}

	}

	/**
	 * buscaDiagonalDerecha
	 *
	 * @param arreglo
	 * @param aux
	 * @param i
	 * @param j
	 * @param k
	 */
	private void buscaDiagonalDerecha(String[] arreglo, char aux, int i, int j, int k) {

		if (j >= (Constantes.LONGITUD_SECUENCIA - Constantes.UNO) && i <= columnas - Constantes.LONGITUD_SECUENCIA) {
			if (aux == arreglo[i + k].charAt(j - k)) {
				this.diagonalDerecha += Constantes.UNO;
			}
		}

	}

	/**
	 * validaSecuenciasEncontradas
	 */
	private void validaSecuenciasEncontradas() {

		if (this.horizontal == Constantes.LONGITUD_SECUENCIA)
			this.secuenciasEncontradas += Constantes.UNO;

		if (this.vertical == Constantes.LONGITUD_SECUENCIA)
			this.secuenciasEncontradas += Constantes.UNO;

		if (this.diagonalIzquierda == Constantes.LONGITUD_SECUENCIA)
			this.secuenciasEncontradas += Constantes.UNO;

		if (this.diagonalDerecha == Constantes.LONGITUD_SECUENCIA)
			this.secuenciasEncontradas += Constantes.UNO;
	}

	/**
	 *
	 */
	private void inicializarContadores() {
		this.horizontal = 1;
		this.vertical = 1;
		this.diagonalIzquierda = 1;
		this.diagonalDerecha = 1;
	}

	private void inicializarVariables() {

		this.horizontal = 0;
		this.vertical = 0;
		this.diagonalIzquierda = 0;
		this.diagonalDerecha = 0;
		this.filas = 0;
		this.columnas = 0;
		this.secuenciasEncontradas = 0;

	}

}
