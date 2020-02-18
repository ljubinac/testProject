package jwd.test.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.test.model.Sprint;
import jwd.test.model.Stanje;
import jwd.test.model.Zadatak;
import jwd.test.service.SprintService;
import jwd.test.service.StanjeService;
import jwd.test.service.ZadatakService;
import jwd.test.web.dto.ZadatakDTO;


@Component
public class DtoToZadatak implements Converter<ZadatakDTO, Zadatak>{

	@Autowired
	private StanjeService stanjeService;
	@Autowired
	private SprintService sprintService;
	@Autowired
	private ZadatakService zadatakService;
	
	@Override
	public Zadatak convert(ZadatakDTO dto) {
		Sprint sprint = sprintService.findOne(dto.getSprintId());
		Stanje stanje = stanjeService.findOne(dto.getStanjeId());
		
		if(sprint!=null && stanje!=null) {
			
			Zadatak zadatak = null;
			
			if(dto.getId() != null) {
				zadatak = zadatakService.findOne(dto.getId());
			}
			else {
				zadatak = new Zadatak();
			}
			
			zadatak.setId(dto.getId());
			zadatak.setIme(dto.getIme());
			zadatak.setZaduzeni(dto.getZaduzeni());
			zadatak.setBodovi(dto.getBodovi());
			
			zadatak.setSprint(sprint);
			zadatak.setStanje(stanje);
			
			return zadatak;
		}else {
			throw new IllegalStateException("Trying to attach to non-existant entities");
		}
	}

	public List<Zadatak> convert(List<ZadatakDTO> zadaciDto){
		List<Zadatak> ret = new ArrayList<>();
		
		for (ZadatakDTO dto : zadaciDto) {
			ret.add(convert(dto));
		}
		return ret;
	}
}
