package jwd.test.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jwd.test.model.Stanje;
import jwd.test.model.Zadatak;
import jwd.test.service.ZadatakService;
import jwd.test.support.DtoToZadatak;
import jwd.test.support.StanjeToDto;
import jwd.test.support.ZadatakToDto;
import jwd.test.web.dto.StanjeDTO;
import jwd.test.web.dto.ZadatakDTO;

@RestController
@RequestMapping(value = "/api/zadaci")
public class ApiZadatakController {

	@Autowired
	private ZadatakService zadatakService;
	@Autowired
	private ZadatakToDto toDto;
	@Autowired
	private DtoToZadatak toZadatak;
	@Autowired
	private StanjeToDto toStanjeDto;
	
	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity<List<ZadatakDTO>> getZadaci(
			@RequestParam(required = false) String ime, 
			@RequestParam(required = false) Long sprintId,
			@RequestParam(value = "pageNum", defaultValue = "0") int pageNum
			){
		Page<Zadatak> zadaciPage = null;
		
		if (ime != null || sprintId != null) {
			zadaciPage = zadatakService.search(ime, sprintId, pageNum);
		} else {
			zadaciPage = zadatakService.findAll(pageNum);
		}
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("totalPages", Integer.toString(zadaciPage.getTotalPages()));
		
		return new ResponseEntity<>(toDto.convert(zadaciPage.getContent()), headers, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	ResponseEntity<ZadatakDTO> getZadatak(@PathVariable Long id){
		Zadatak zadatak = zadatakService.findOne(id);
		
		if (zadatak == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(toDto.convert(zadatak), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	ResponseEntity<ZadatakDTO> delete(@PathVariable Long id){
		Zadatak zadatak = zadatakService.findOne(id);
		
		if (zadatak == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		zadatak = zadatakService.delete(id);
		
		return new ResponseEntity<>(toDto.convert(zadatak), HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}", consumes = "application/json")
	ResponseEntity<ZadatakDTO> edit(@Validated @RequestBody ZadatakDTO edited, @PathVariable Long id){
		if (!id.equals(edited.getId())) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Zadatak zadatak = zadatakService.save(toZadatak.convert(edited));
		
		return new ResponseEntity<>(toDto.convert(zadatak), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	ResponseEntity<ZadatakDTO> add(@Validated @RequestBody ZadatakDTO novi){
		
		Zadatak zadatak = zadatakService.save(toZadatak.convert(novi));
		
		return new ResponseEntity<>(toDto.convert(zadatak), HttpStatus.CREATED);
	}
	
	@RequestMapping(method= RequestMethod.POST, value = "/{id}")
	ResponseEntity<StanjeDTO> predji(@PathVariable Long id){
		Stanje stanje = zadatakService.predji(id);
		
		if(stanje == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<StanjeDTO>(toStanjeDto.convert(stanje), HttpStatus.CREATED);
		}
	}
	
	
	@ExceptionHandler(value = DataIntegrityViolationException.class)
	public ResponseEntity<Void> handle(){
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
