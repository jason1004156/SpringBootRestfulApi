package com.example.demo.compoment;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.demo.entity.Book;
import com.example.demo.repository.BookRepository;

@Component
public class DataLoader implements CommandLineRunner {
	@Autowired
	private BookRepository rep;

	/**
	 * 資料庫預設資料
	 */
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		Book book = new Book();
		book.setISBN("986-18-1106-0");
		book.setName("Spring 2.0技術手冊");
		book.setAuthor("林信良");
		book.setPublisher("電子工業出版社");
		book.setPublicationDate(Date.valueOf("2008-11-01"));
		
		rep.save(book);
		
		book = new Book();
		book.setISBN("000-18-1803-1");
		book.setName("我寫的書");
		book.setAuthor("扛");
		book.setTranslator("扛義");
		book.setPublisher("電子工業出版社");
		book.setPublicationDate(Date.valueOf("2022-04-07"));
		
		rep.save(book);

	}
}
