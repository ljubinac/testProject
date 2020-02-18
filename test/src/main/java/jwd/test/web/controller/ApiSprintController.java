package jwd.test.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jwd.test.model.Sprint;
import jwd.test.service.SprintService;
import jwd.test.support.SprintToDto;
import jwd.test.web.dto.SprintDTO;

@RestController
@RequestMapping(value = "/api/sprintovi")
public class ApiSprintController {

	@Autowired
	private SprintService sprintService;
	@Autowired
	private SprintToDto toDto;
	
	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity<List<SprintDTO>> getSprintovi(){
		List<Sprint> sprintovi = sprintService.findAll();
		
		if(sprintovi == null || sprintovi.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(toDto.convert(sprintovi), HttpStatus.OK);
	}
}
