package jwd.test.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.test.model.Sprint;
import jwd.test.web.dto.SprintDTO;

@Component
public class SprintToDto implements Converter<Sprint, SprintDTO>{

	@Override
	public SprintDTO convert(Sprint s) {
		SprintDTO dto = new SprintDTO();
		
		dto.setId(s.getId());
		dto.setIme(s.getIme());
		dto.setUkupnoBodova(s.getUkupnoBodova());
		return dto;
	}

	public List<SprintDTO> convert(List<Sprint> sprintovi){
		List<SprintDTO> ret = new ArrayList<>();
		
		for (Sprint s : sprintovi) {
			ret.add(convert(s));
		}
		return ret;
	}
}
