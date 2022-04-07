package com.example.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Book;

@SpringBootTest
class BookServiceTest {

	@Autowired
	private BookService bs;
	
	@Test
	public void testGetByCondition() {
		Map<String,String> req = new HashMap<String,String>();
		req.put("PublicationDateStart", "2000-04-07");
		List<Book> books = bs.getByCondition(req);
		for (Book book : books) {
			System.out.println(book);
		}
	}

}
