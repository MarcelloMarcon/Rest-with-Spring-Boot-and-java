package br.com.marcon.mapper.custom;

import java.util.Date;

import org.springframework.stereotype.Service;

import br.com.marcon.data.vo.v2.PearsonVOV2;
import br.com.marcon.model.Pearson;

@Service
public class PearsonMapper {
	
	public PearsonVOV2 convertEntityToVo(Pearson pearson) {
		PearsonVOV2 vo = new PearsonVOV2();
		vo.setId(pearson.getId());
		vo.setFirstName(pearson.getFirstName());
		vo.setLastName(pearson.getLastName());
		vo.setAdress(pearson.getAdress());
		vo.setGender(pearson.getGender());
		vo.setBirthDay(new Date());
		
		return vo;
	}
	
	public Pearson convertVoToEntity(PearsonVOV2 pearson) {
		Pearson entity = new Pearson();
		entity.setId(pearson.getId());
		entity.setFirstName(pearson.getFirstName());
		entity.setLastName(pearson.getLastName());
		entity.setAdress(pearson.getAdress());
		entity.setGender(pearson.getGender());
		//entity.setBirthDay(new Date());
		
		return entity;
	}
}
