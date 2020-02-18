package jwd.test;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jwd.test.model.Sprint;
import jwd.test.model.Stanje;
import jwd.test.model.Zadatak;
import jwd.test.service.SprintService;
import jwd.test.service.StanjeService;
import jwd.test.service.ZadatakService;

@Component
public class TestData {

	@Autowired
	private SprintService sprintService;
	@Autowired
	private StanjeService stanjeService;
	@Autowired
	private ZadatakService zadatakService;
	
	@PostConstruct
	public void init(){
		
		Sprint s1 = sprintService.save(new Sprint("Sprint1", 8));
		Sprint s2 = sprintService.save(new Sprint("Sprint2", 7));
		
		Stanje st1 = stanjeService.save(new Stanje("nov"));
		Stanje st2 = stanjeService.save(new Stanje("u toku"));
		Stanje st3 = stanjeService.save(new Stanje("zavrsen"));
		
		Zadatak z1 = new Zadatak();
		z1.setIme("Kreirati rest servis");
		z1.setZaduzeni("Nikola");
		z1.setBodovi(8);
		z1.setStanje(st3);
		z1.setSprint(s1);
		z1 = zadatakService.save(z1);
		
		Zadatak z2 = new Zadatak();
		z2.setIme("Kreirati pocetnu stranicu");
		z2.setZaduzeni("Bane");
		z2.setBodovi(5);
		z2.setStanje(st2);
		z2.setSprint(s1);
		z2 = zadatakService.save(z2);
		
		Zadatak z3 = new Zadatak();
		z3.setIme("Kreirati logo");
		z3.setZaduzeni("Ana");
		z3.setBodovi(8);
		z3.setStanje(st1);
		z3.setSprint(s2);
		z3 = zadatakService.save(z3);
		
		
		
	}
}
