package jwd.test.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import jwd.test.model.Stanje;
import jwd.test.model.Zadatak;

public interface ZadatakService {

	List<Zadatak> findAll();
	Page<Zadatak> findAll(int pageNum);
	Zadatak findOne(Long id);
	Zadatak save(Zadatak zadatak);
	Zadatak delete(Long id);
	Page<Zadatak>search(
			@Param("ime") String ime, 
			@Param("sprintId") Long sprintId,
			int pageNum
			);
	Stanje predji(Long id);
	
}
