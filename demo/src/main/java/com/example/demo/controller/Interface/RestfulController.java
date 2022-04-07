package com.example.demo.controller.Interface;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.compoment.ReturnMsg;



@RequestMapping("/default")
public interface RestfulController<T> {
	
	@GetMapping
	ReturnMsg getAll();
	
	@GetMapping("/{id}")
	ReturnMsg getById(@PathVariable String id) throws Exception;
	
	@GetMapping("/getByCondition")
	ReturnMsg getByCondition(HttpServletRequest req) throws Exception;
	
	@PostMapping("/save")
	ReturnMsg save(@RequestBody T req) throws Exception;
	
	

}
