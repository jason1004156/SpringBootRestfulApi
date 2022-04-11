package com.example.demo.controller.Interface;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.compoment.ReturnMsg;
import com.example.demo.entity.Book;



@RequestMapping("/default")
public interface RestfulController<T> {
	
	@GetMapping
	ReturnMsg get(HttpServletRequest req) throws Exception;
	
	@GetMapping("/{id}")
	ReturnMsg getById(@PathVariable String id) throws Exception;
	
	@PostMapping
	ReturnMsg save(@RequestBody T req) throws Exception;
	
	@PutMapping("/{id}")
	ReturnMsg update(@PathVariable String id, @RequestBody Book book) throws Exception;
	
	@DeleteMapping("/{id}")
	ReturnMsg detete(@PathVariable String id) throws Exception;
	
	
	

}
