package com.masai.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.model.Responce;
import com.masai.service.ServiceInterface;

@RestController
@CrossOrigin("*")
public class Controller {
	@Autowired
    private ServiceInterface serviceInterface;
	
	
	@PostMapping("/start/{level}/{name}")
	ResponseEntity<Responce> post(@PathVariable Integer level,@PathVariable String name){
		
		return new ResponseEntity<Responce>(serviceInterface.createGame(level,name),HttpStatus.CREATED);
	}
	
	@GetMapping("/test/{number}")
	ResponseEntity<Responce> testNumber(@PathVariable Integer number){
		
		return new ResponseEntity<Responce>(serviceInterface.testNumber(number),HttpStatus.OK);
	}
	@CrossOrigin("*")
	@GetMapping("/result")
	ResponseEntity<List<Responce>> getResult(){
		
		return new ResponseEntity<List<Responce>>(serviceInterface.getResult(),HttpStatus.OK);
	}
	@DeleteMapping("/resetGame")
    ResponseEntity<Responce> resetGame(){
		
		return new ResponseEntity<Responce>(serviceInterface.resetGame(),HttpStatus.OK);
	}
	@PostMapping("/sms")
	ResponseEntity<Responce> sms(){
		return new ResponseEntity<Responce>(serviceInterface.sms(),HttpStatus.OK);
	}
}
