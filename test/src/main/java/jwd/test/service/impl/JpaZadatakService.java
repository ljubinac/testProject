package jwd.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import jwd.test.model.Stanje;
import jwd.test.model.Zadatak;
import jwd.test.repository.StanjeRepository;
import jwd.test.repository.ZadatakRepository;
import jwd.test.service.ZadatakService;

@Service
public class JpaZadatakService implements ZadatakService{

	@Autowired
	private ZadatakRepository zadatakRepository;
	@Autowired
	private StanjeRepository stanjeRepository;
	
	@Override
	public List<Zadatak> findAll() {
		// TODO Auto-generated method stub
		return zadatakRepository.findAll();
	}

	@Override
	public Page<Zadatak> findAll(int pageNum) {
		// TODO Auto-generated method stub
		return zadatakRepository.findAll(new PageRequest(pageNum, 5));
	}

	@Override
	public Zadatak findOne(Long id) {
		// TODO Auto-generated method stub
		return zadatakRepository.findOne(id);
	}

	@Override
	public Zadatak save(Zadatak zadatak) {
		
		return zadatakRepository.save(zadatak);
	}

	@Override
	public Zadatak delete(Long id) {
		Zadatak zadatak = zadatakRepository.findOne(id);
		if(zadatak == null) {
			throw new IllegalStateException("Nije pronadjen zadatak");
		}
		zadatakRepository.delete(zadatak);
		return zadatak;
	}

	@Override
	public Page<Zadatak> search(String ime, Long sprintId, int pageNum) {
		
		if(ime != null) {
			ime = '%' + ime + '%';
		}
		return zadatakRepository.search(ime, sprintId, new PageRequest(pageNum, 5));
	}

	@Override
	public Stanje predji(Long id) {
		Zadatak zadatak = findOne(id);
		
		if(zadatak != null) {
			Stanje stanje = null;
			if(!(zadatak.getStanje().getId()).equals(3L)) {
				stanje = stanjeRepository.findOne(zadatak.getStanje().getId() + 1);
				zadatak.setStanje(stanje);
				stanje.addZadatak(zadatak);
				zadatakRepository.save(zadatak);
				stanjeRepository.save(stanje);
			}
			
			return stanje;
		} else {
			throw new IllegalArgumentException("Stanje je maksimalno!");
		}
	}

}
