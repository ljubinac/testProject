package jwd.test.service;

import java.util.List;

import jwd.test.model.Sprint;

public interface SprintService {

	List<Sprint> findAll();
	Sprint findOne(Long id);
	Sprint save(Sprint sprint);
}
