package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.common.EntityTool;
import com.example.demo.entity.Book;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
class BookServiceTest {

	@Autowired
	private BookService bs;

	@BeforeAll
	public void beforeTest() throws Exception {
		bs.save(getTestData());
	}

	/**
	 * 確保各欄位條件有所作用
	 */
	@Test
	public void testGetByCondition() {

		String[] columns = EntityTool.getFields(Book.class);
		for (String column : columns) {
			Map<String, String> req = new HashMap<String, String>();
			if (column.endsWith("Date")) {
				req.put(column, "2999-01-01");
			} else if (column.contains("Price")) {
				req.put(column, "9999.99");
			} else {
				req.put(column, "test" + column);
			}
			List<Book> books = bs.getByCondition(req);
			assertTrue(books.size() != 0);
			for (Book book : books) {
				assertEquals("testISBN", book.getISBN());
			}

		}
	}

	/**
	 * 確保各欄位條件有所作用
	 */
	@Test
	public void testGetByConditionNoData() {

		String[] columns = EntityTool.getFields(Book.class);
		for (String column : columns) {
			Map<String, String> req = new HashMap<String, String>();
			if (column.endsWith("Date")) {
				req.put(column, "2999-01-02");
			} else if (column.contains("Price")) {
				req.put(column, "99999.99");
			} else {
				req.put(column, "test1" + column);
			}
			List<Book> books = bs.getByCondition(req);
			assertTrue(books.size() == 0);
		}
	}
	/**
	 * 測試資料
	 * @return
	 */
	private Book getTestData() {
		Book book = new Book();
		book.setISBN("testISBN");
		book.setName("testName");
		book.setAuthor("testAuthor");
		book.setTranslator("testTranslator");
		book.setPublisher("testPublisher");
		book.setPrice(9999.99);
		book.setPublicationDate(Date.valueOf("2999-01-01"));

		return book;
	}

}
