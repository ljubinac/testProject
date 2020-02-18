package jwd.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jwd.test.model.Stanje;
import jwd.test.repository.StanjeRepository;
import jwd.test.service.StanjeService;

@Service
public class JpaStanjeService implements StanjeService{

	@Autowired
	private StanjeRepository stanjerepository;
	
	@Override
	public List<Stanje> findAll() {
		// TODO Auto-generated method stub
		return stanjerepository.findAll();
	}

	@Override
	public Stanje findOne(Long id) {
		// TODO Auto-generated method stub
		return stanjerepository.findOne(id);
	}

	@Override
	public Stanje save(Stanje stanje) {
		
		return stanjerepository.save(stanje);
	}

}
