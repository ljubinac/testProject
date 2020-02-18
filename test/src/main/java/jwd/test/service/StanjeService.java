package jwd.test.service;

import java.util.List;

import jwd.test.model.Stanje;

public interface StanjeService {

	List<Stanje> findAll();
	Stanje findOne(Long id);
	Stanje save(Stanje stanje);
}
