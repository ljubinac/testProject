package jwd.test.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.test.model.Zadatak;
import jwd.test.web.dto.ZadatakDTO;

@Component
public class ZadatakToDto implements Converter<Zadatak, ZadatakDTO>{

	@Override
	public ZadatakDTO convert(Zadatak z) {
		ZadatakDTO dto = new ZadatakDTO();
		
		dto.setId(z.getId());
		dto.setIme(z.getIme());
		dto.setZaduzeni(z.getZaduzeni());
		dto.setBodovi(z.getBodovi());
		dto.setSprintId(z.getSprint().getId());
		dto.setSprintIme(z.getSprint().getIme());
		dto.setStanjeId(z.getStanje().getId());
		dto.setStanjeIme(z.getStanje().getIme());
		return dto;
	}

	public List<ZadatakDTO> convert(List<Zadatak> zadaci){
		List<ZadatakDTO> ret = new ArrayList<>();
		
		for (Zadatak z : zadaci) {
			ret.add(convert(z));
		}
		return ret;
	}
}
