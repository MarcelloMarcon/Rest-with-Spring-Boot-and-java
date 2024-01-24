package br.com.marcon.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.marcon.data.vo.v1.PearsonVO;
import br.com.marcon.data.vo.v2.PearsonVOV2;
import br.com.marcon.exceptions.ResourceNotFoundException;
import br.com.marcon.mapper.ModelMapperUtil;
import br.com.marcon.mapper.custom.PearsonMapper;
import br.com.marcon.model.Pearson;
import br.com.marcon.respositories.PearsonRepository;

@Service
public class PearsonServices {
	
	private Logger logger = Logger.getLogger(PearsonServices.class.getName());
	
	@Autowired
	PearsonRepository repository;
	
	@Autowired
	PearsonMapper mapper;
	
	public List<PearsonVO> findAll(){
		logger.info("Finding all people");
		
		List<Pearson> entities = repository.findAll();
		return ModelMapperUtil.mapList(entities, PearsonVO.class);
	}
	
	public PearsonVO createPearson(PearsonVO pearson) {
		
		logger.info("Creating a pearson");
		
		Pearson entity = ModelMapperUtil.map(pearson, Pearson.class);
		Pearson savedEntity = repository.save(entity);
		
		return ModelMapperUtil.map(savedEntity, PearsonVO.class);
	}
	
	public PearsonVOV2 createPearsonV2(PearsonVOV2 pearson) {
		
		logger.info("Creating a pearson");
		
		Pearson entity = mapper.convertVoToEntity(pearson);
		Pearson savedEntity = repository.save(entity);
		
		return mapper.convertEntityToVo(savedEntity);
	}
	
	public PearsonVO update(PearsonVO pearson) {
		logger.info("Updating a pearson");
		
		Pearson entity = repository.findById(pearson.getId())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this id."));
		
		entity.setFirstName(pearson.getFirstName());
		entity.setLastName(pearson.getLastName());
		entity.setAdress(pearson.getAdress());
		entity.setGender(pearson.getGender());

		Pearson savedEntity = repository.save(entity);
		return ModelMapperUtil.map(savedEntity, PearsonVO.class);
	}
	
	public void delete(Long id) {
		
		logger.info("Deleting a pearson");
		
		Pearson entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this id."));
		repository.delete(entity);
	}
	
	public PearsonVO findByID(Long id) {
		
		logger.info("Finding one pearson.");
		
		Pearson entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this id."));
		return ModelMapperUtil.map(entity, PearsonVO.class);
	}
}
