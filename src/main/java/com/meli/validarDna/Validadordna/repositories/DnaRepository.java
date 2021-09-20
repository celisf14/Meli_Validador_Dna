package com.meli.validarDna.Validadordna.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meli.validarDna.Validadordna.models.DnaModel;

@Repository
public interface DnaRepository extends JpaRepository<DnaModel, Long> {

	List<DnaModel> findByDna(String dna);

	Long countByIsMutant(String tipo);

}
