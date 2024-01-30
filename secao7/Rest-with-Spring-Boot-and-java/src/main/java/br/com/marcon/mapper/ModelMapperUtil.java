package br.com.marcon.mapper;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import br.com.marcon.data.vo.v1.PearsonVO;
import br.com.marcon.model.Pearson;

public class ModelMapperUtil {
	
	private static ModelMapper modelMapper = new ModelMapper();
	
	static {
		modelMapper.createTypeMap(Pearson.class, PearsonVO.class).addMapping(Pearson::getId, PearsonVO::setKey);
		modelMapper.createTypeMap(PearsonVO.class, Pearson.class).addMapping(PearsonVO::getKey, Pearson::setId);
	}

    public static <O, D> D map(O origin, Class<D> destinationType) {
        return modelMapper.map(origin, destinationType);
    }

    public static <O, D> List<D> mapList(List<O> originList, Class<D> destinationType) {
        return modelMapper.map(originList, new TypeToken<List<D>>() {}.getType());
    }
}
