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

import br.com.marcon.data.vo.v1.BookVO;
import br.com.marcon.data.vo.v1.PearsonVO;
import br.com.marcon.data.vo.v2.PearsonVOV2;
import br.com.marcon.services.BookServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/book")
@Tag(name = "Books" , description = "Endpoints for managing books")
public class BookController {
	
	@Autowired
	private BookServices service;

	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@Operation(summary = "Finds All Books", description = "Finds All Books",
		tags = {"Books"},
		responses = {
			@ApiResponse(description = "Success", responseCode = "200",
					content = {
						@Content(
							mediaType = "application/json",
							array = @ArraySchema(schema = @Schema(implementation = BookVO.class))
						)
					}),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
		}
			)
	public List<BookVO> findAll(
		) throws Exception {
		return service.findAll();
	}
	
	@GetMapping(value = "/{id}",
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@Operation(summary = "Finds a book", description = "Finds a book",
	tags = {"Books"},
	responses = {
		@ApiResponse(description = "Success", responseCode = "200",
				content = @Content(schema = @Schema(implementation = BookVO.class))
		),
		@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
		@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
		@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
		@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
		@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
	}
		)
	public BookVO findById(@PathVariable(value = "id") Long id) 
		throws Exception{
		return service.findByID(id);
	}
	
	@PostMapping(
			value = "/createBook",
			consumes = "application/json",
			produces = "application/json")
	@Operation(summary = "Creates a book", description = "Creates a book by JSON or XML",
	tags = {"Books"},
	responses = {
		@ApiResponse(description = "Success", responseCode = "200",
				content = @Content(schema = @Schema(implementation = BookVO.class))
		),
		@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
		@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
		@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
	}
		)
	public BookVO create(@RequestBody BookVO book
		) throws Exception {
		return service.create(book);
	}
	
	//@PostMapping(
	//		value = "/createPearson/V2",
	//		consumes = "application/json",
	//		produces = "application/json")
	//public PearsonVOV2 createPearsonV2(@RequestBody PearsonVOV2 pearson 
	//	) throws Exception {
	//	return service.createPearsonV2(pearson);
	//}
	
	@PutMapping(
			value = "/updateBook",
			consumes = "application/json",
			produces = "application/json")
	@Operation(summary = "Updates a book", description = "Updates a book by JSON or XML",
	tags = {"Books"},
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
	public BookVO update(@RequestBody BookVO book
		) throws Exception {
		return service.update(book);
	}
	
	@DeleteMapping("/deleteBook/{id}")
	@Operation(summary = "Deletes a book", description = "Deletes a book",
	tags = {"Books"},
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
