package br.com.marcon.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.marcon.controllers.BookController;
import br.com.marcon.data.vo.v1.BookVO;
import br.com.marcon.exceptions.ResourceNotFoundException;
import br.com.marcon.mapper.ModelMapperUtil;
import br.com.marcon.mapper.custom.PearsonMapper;
import br.com.marcon.model.Book;
import br.com.marcon.respositories.BookRepository;

@Service
public class BookServices {
	
	private Logger logger = Logger.getLogger(BookServices.class.getName());
	
	@Autowired
	BookRepository repository;
	
	@Autowired
	PearsonMapper mapper;
	
	public List<BookVO> findAll(){
		logger.info("Finding all books");
		
		List<Book> entities = repository.findAll();
		var books = ModelMapperUtil.mapList(entities, BookVO.class);
		return books;
	}
	
	public BookVO findByID(Long id) throws Exception {
		
		logger.info("Finding one book.");
		
		Book entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this id."));
		var vo = ModelMapperUtil.map(entity, BookVO.class);
		vo.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
		return vo;
	}
	
	public BookVO create(BookVO book) throws Exception {
		
		logger.info("Creating a book");
		
		Book entity = ModelMapperUtil.map(book, Book.class);
		Book savedEntity = repository.save(entity);
		var vo = ModelMapperUtil.map(entity, BookVO.class);
		vo.add(linkTo(methodOn(BookController.class).findById(vo.getKey())).withSelfRel());
		return vo;
	}
	
	//public PearsonVOV2 createPearsonV2(PearsonVOV2 pearson) {
	//	
	//	logger.info("Creating a pearson");
	//	
	//	Pearson entity = mapper.convertVoToEntity(pearson);
	//	Pearson savedEntity = repository.save(entity);
	//	
	//	return mapper.convertEntityToVo(savedEntity);
	//}
	
	public BookVO update(BookVO book) throws Exception {
		logger.info("Updating a book");
		
		Book entity = repository.findById(book.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this id."));
		
		entity.setAuthor(book.getAuthor());
		entity.setLaunchDate(book.getLaunchDate());
		entity.setPrice(book.getPrice());
		entity.setTitle(book.getTitle());

		Book savedEntity = repository.save(entity);
		var vo = ModelMapperUtil.map(entity, BookVO.class);
		vo.add(linkTo(methodOn(BookController.class).findById(vo.getKey())).withSelfRel());
		return vo;
	}
	
	public void delete(Long id) {
		
		logger.info("Deleting a book");
		
		Book entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this id."));
		repository.delete(entity);
	}
	
}
