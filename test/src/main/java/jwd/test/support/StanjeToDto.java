package jwd.test.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.test.model.Stanje;
import jwd.test.web.dto.StanjeDTO;

@Component
public class StanjeToDto implements Converter<Stanje, StanjeDTO>{

	@Override
	public StanjeDTO convert(Stanje s) {
		StanjeDTO dto = new StanjeDTO();
		
		dto.setId(s.getId());
		dto.setIme(s.getIme());
		return dto;
	}

	public List<StanjeDTO> convert(List<Stanje> stanja){
		List<StanjeDTO> ret = new ArrayList<>();
		
		for (Stanje s : stanja) {
			ret.add(convert(s));
		}
		return ret;
	}
}
