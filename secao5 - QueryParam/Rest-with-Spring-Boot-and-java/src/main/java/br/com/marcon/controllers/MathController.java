package br.com.marcon.controllers;
import org.springframework.web.bind.annotation.*;
import br.com.marcon.converters.NumberConvert;
import br.com.marcon.exceptions.UnsupportedMathOperationException;
import br.com.marcon.math.SimpleMath;

@RestController
public class MathController {
	
	private SimpleMath math = new SimpleMath();
	
	@GetMapping("/sum/{numberOne}/{numberTwo}")
	public Double sum(
			@PathVariable(value = "numberOne") String numberOne,
			@PathVariable(value = "numberTwo") String numberTwo
		) throws Exception {
		if(!NumberConvert.isNumeric(numberOne) || !NumberConvert.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value!");
		}
		return math.sum(NumberConvert.convertToDouble(numberOne),NumberConvert.convertToDouble(numberTwo));
	}
	
	@RequestMapping(value = "/sub/{numberOne}/{numberTwo}",
			method=RequestMethod.GET)
	public Double sub(
			@PathVariable(value = "numberOne") String numberOne,
			@PathVariable(value = "numberTwo") String numberTwo
		) throws Exception {
		if(!NumberConvert.isNumeric(numberOne) || !NumberConvert.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value!");
		}
		return math.sub(NumberConvert.convertToDouble(numberOne), NumberConvert.convertToDouble(numberTwo)) ;
	}
	
	@RequestMapping(value = "/mult/{numberOne}/{numberTwo}",
			method=RequestMethod.GET)
	public Double mult(
			@PathVariable(value = "numberOne") String numberOne,
			@PathVariable(value = "numberTwo") String numberTwo
		) throws Exception {
		if(!NumberConvert.isNumeric(numberOne) || !NumberConvert.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value!");
		}
		return math.mult(NumberConvert.convertToDouble(numberOne), NumberConvert.convertToDouble(numberTwo));
	}

	@RequestMapping(value = "/div/{numberOne}/{numberTwo}",
			method=RequestMethod.GET)
	public Double div(
			@PathVariable(value = "numberOne") String numberOne,
			@PathVariable(value = "numberTwo") String numberTwo
		) throws Exception {
		if(!NumberConvert.isNumeric(numberOne) || !NumberConvert.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value!");
		}
		return math.div(NumberConvert.convertToDouble(numberOne), NumberConvert.convertToDouble(numberTwo)) ;
	}
	
	@RequestMapping(value = "/media/{numberOne}/{numberTwo}",
			method=RequestMethod.GET)
	public Double media(
			@PathVariable(value = "numberOne") String numberOne,
			@PathVariable(value = "numberTwo") String numberTwo
		) throws Exception {
		if(!NumberConvert.isNumeric(numberOne) || !NumberConvert.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value!");
		}
		return math.media(NumberConvert.convertToDouble(numberOne),NumberConvert.convertToDouble(numberTwo));
	}
	
	@RequestMapping(value = "/raiz/{number}",
			method=RequestMethod.GET)
	public Double raiz(
			@PathVariable(value = "number") String number
		) throws Exception {
		if(!NumberConvert.isNumeric(number)) {
			throw new UnsupportedMathOperationException("Please set a numeric value!");
		}
		return math.raiz(NumberConvert.convertToDouble(number));
	}
}
