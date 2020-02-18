package jwd.test.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jwd.test.model.Stanje;
import jwd.test.service.StanjeService;
import jwd.test.support.StanjeToDto;
import jwd.test.web.dto.StanjeDTO;

@RestController
@RequestMapping(value = "/api/stanja")
public class ApiStanjeController {

	@Autowired
	private StanjeService stanjeService;
	@Autowired
	private StanjeToDto toDto;
	
	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity<List<StanjeDTO>> getStanja(){
		List<Stanje> stanja = stanjeService.findAll();
		
		if(stanja == null || stanja.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(toDto.convert(stanja), HttpStatus.OK);
	}
}
