package br.com.marcon.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.marcon.data.vo.v1.PearsonVO;
import br.com.marcon.data.vo.v2.PearsonVOV2;
import br.com.marcon.services.PearsonServices;

@RestController
@RequestMapping("/pearson")
public class PearsonController {
	
	@Autowired
	private PearsonServices service;

	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public List<PearsonVO> findAll(
		) throws Exception {
		return service.findAll();
	}
	
	@GetMapping(value = "/{id}",
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public PearsonVO findById(@PathVariable(value = "id") Long id) 
		throws Exception{
		return service.findByID(id);
	}
	
	@PostMapping(
			value = "/createPearson",
			consumes = "application/json",
			produces = "application/json")
	public PearsonVO createPearson(@RequestBody PearsonVO pearson 
		) throws Exception {
		return service.createPearson(pearson);
	}
	
	@PostMapping(
			value = "/createPearson/V2",
			consumes = "application/json",
			produces = "application/json")
	public PearsonVOV2 createPearsonV2(@RequestBody PearsonVOV2 pearson 
		) throws Exception {
		return service.createPearsonV2(pearson);
	}
	
	@PutMapping(
			value = "/updatePearson",
			consumes = "application/json",
			produces = "application/json")
	public PearsonVO update(@RequestBody PearsonVO pearson 
		) throws Exception {
		return service.update(pearson);
	}
	
	@DeleteMapping("/deletePearson/{id}")
	public ResponseEntity<?> delete(
			@PathVariable(value = "id") Long id
		) throws Exception {
		service.delete(id);
		return ResponseEntity.noContent().build();	}
}
