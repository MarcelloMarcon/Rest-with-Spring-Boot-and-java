package br.com.marcon.mapper;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

public class ModelMapperUtil {

    private static final ModelMapper modelMapper = new ModelMapper();

    public static <O, D> D map(O origin, Class<D> destinationType) {
        return modelMapper.map(origin, destinationType);
    }

    public static <O, D> List<D> mapList(List<O> originList, Class<D> destinationType) {
        return modelMapper.map(originList, new TypeToken<List<D>>() {}.getType());
    }
}
