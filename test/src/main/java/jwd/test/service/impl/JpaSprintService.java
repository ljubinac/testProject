package jwd.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jwd.test.model.Sprint;
import jwd.test.repository.SprintRepository;
import jwd.test.service.SprintService;

@Service
public class JpaSprintService implements SprintService{

	@Autowired
	private SprintRepository sprintRepository;
	
	@Override
	public List<Sprint> findAll() {
		// TODO Auto-generated method stub
		return sprintRepository.findAll();
	}

	@Override
	public Sprint findOne(Long id) {
		// TODO Auto-generated method stub
		return sprintRepository.findOne(id);
	}

	@Override
	public Sprint save(Sprint sprint) {
		// TODO Auto-generated method stub
		return sprintRepository.save(sprint);
	}

}
