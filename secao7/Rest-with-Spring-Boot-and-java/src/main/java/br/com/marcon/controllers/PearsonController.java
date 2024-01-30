package br.com.marcon.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/pearson")
@Tag(name = "People" , description = "Endpoints for managing people")
public class PearsonController {
	
	@Autowired
	private PearsonServices service;

	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@Operation(summary = "Finds All People", description = "Finds All People",
		tags = {"People"},
		responses = {
			@ApiResponse(description = "Success", responseCode = "200",
					content = {
						@Content(
							mediaType = "application/json",
							array = @ArraySchema(schema = @Schema(implementation = PearsonVO.class))
						)
					}),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
		}
			)
	public List<PearsonVO> findAll(
		) throws Exception {
		return service.findAll();
	}
	
	@CrossOrigin(origins = "http://localhost:8080")
	@GetMapping(value = "/{id}",
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@Operation(summary = "Finds a pearson", description = "Finds a pearson",
	tags = {"People"},
	responses = {
		@ApiResponse(description = "Success", responseCode = "200",
				content = @Content(schema = @Schema(implementation = PearsonVO.class))
		),
		@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
		@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
		@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
		@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
		@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
	}
		)
	public PearsonVO findById(@PathVariable(value = "id") Long id) 
		throws Exception{
		return service.findByID(id);
	}
	
	@PostMapping(
			value = "/createPearson",
			consumes = "application/json",
			produces = "application/json")
	@Operation(summary = "Creates a pearson", description = "Create a pearson by JSON or XML",
	tags = {"People"},
	responses = {
		@ApiResponse(description = "Success", responseCode = "200",
				content = @Content(schema = @Schema(implementation = PearsonVO.class))
		),
		@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
		@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
		@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
	}
		)
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
	@Operation(summary = "Updates a pearson", description = "Update a pearson by JSON or XML",
	tags = {"People"},
	responses = {
		@ApiResponse(description = "Updated", responseCode = "200",
				content = @Content(schema = @Schema(implementation = PearsonVO.class))
		),
		@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
		@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
		@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
		@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
	}
		)
	public PearsonVO update(@RequestBody PearsonVO pearson 
		) throws Exception {
		return service.update(pearson);
	}
	
	@DeleteMapping("/deletePearson/{id}")
	@Operation(summary = "Deletes a pearson", description = "Deletes a pearson",
	tags = {"People"},
	responses = {
		@ApiResponse(description = "No content", responseCode = "204", content = @Content),
		@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
		@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
		@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
		@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
	}
		)
	public ResponseEntity<?> delete(
			@PathVariable(value = "id") Long id
		) throws Exception {
		service.delete(id);
		return ResponseEntity.noContent().build();	}
}
