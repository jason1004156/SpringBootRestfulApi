package com.example.demo.service;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Book;
import com.example.demo.repository.BookRepository;

@Service
@SuppressWarnings("unchecked")
public class BookService {

	@Autowired
	private BookRepository rep;
	
	@Autowired
	private EntityManagerFactory emf;

	private static String BaseSQL = "select b from Book as b where 1=1 ";
	private static String[] columns = { "ISBN", "Name", "Author", "Translator", "Publisher", "PublicationDate",
			"Price" };

	/**
	 * 取得所有資料
	 * @return
	 */
	public List<Book> getAll(){
		return rep.findAll();
	}
	
	/**
	 * 依id(ISBN)取得資料
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Optional<Book> getById(String id) throws Exception{
		if (StringUtils.isBlank(id)) {
			throw new Exception("ISBN不可為空!");
		}
		return rep.findById(id);
	}
	
	
	/**
	 * 依據條件查詢
	 * 
	 * @param req
	 * @return
	 */
	public List<Book> getByCondition(Map<String, String> req) {

		EntityManager em = emf.createEntityManager();
		String sql = BaseSQL + getConditionSql(req);
		Query q = em.createQuery(sql);
		setParam(q, req);
		List<Book> res = (List<Book>) q.getResultList();
		return res;

	}
	
	/**
	 * 儲存
	 * @param book
	 * @throws Exception
	 */
	public void save(Book book) throws Exception {
		if (StringUtils.isBlank(book.getISBN())) {
			throw new Exception("ISBN不可為空!");
		}
		rep.save(book);
	}
	/**
	 * 更新
	 * @param id
	 * @param book
	 * @throws Exception
	 */
	public void update(String id, Book book) throws Exception{
		if (StringUtils.isBlank(book.getISBN())) {
			throw new Exception("ISBN不可為空!");
		}
		
		if(StringUtils.isNotBlank(id) && !id.equals(book.getISBN())) {
			delete(id);
		}
		rep.save(book);
	}
	
	/**
	 * 刪除
	 * @param id
	 * @throws Exception
	 */
	public void delete(String id) throws Exception {
		if (StringUtils.isBlank(id)) {
			throw new Exception("ISBN不可為空!");
		}
		try {
			rep.deleteById(id);
		}catch (EmptyResultDataAccessException e) {
			throw new Exception("無此ISBN");
		}
	}

	/**
	 * 組合條件字串
	 * 
	 * @param req
	 * @return
	 */
	private String getConditionSql(Map<String, String> req) {
		StringBuilder conditionSql = new StringBuilder("");
		for (String column : columns) {
			String temp = MapUtils.getString(req, column);
			if (StringUtils.isNotBlank(temp)) {
				conditionSql.append(" and " + column + " = :" + column);
			}

			if (column.contains("Date")) {
				String tempStart = column + "Start";
				String tempEnd = column + "End";
				String dateStart = MapUtils.getString(req, tempStart);
				String dateEnd = MapUtils.getString(req, tempEnd);

				if (StringUtils.isNoneEmpty(dateStart, dateEnd)) {
					conditionSql.append(" and " + column + " between :" + tempStart + " and :" + tempEnd);
				} else if (StringUtils.isNotBlank(dateStart)) {
					conditionSql.append(" and " + column + " >= :" + tempStart);
				} else if (StringUtils.isNotBlank(dateEnd)) {
					conditionSql.append(" and " + column + " <= :" + tempEnd);
				}
			}
		}
		return conditionSql.toString();
	}

	/**
	 * 設定條件
	 * 
	 * @param q
	 * @param req
	 */
	private void setParam(Query q, Map<String, String> req) {
		for (String column : columns) {
			String temp = MapUtils.getString(req, column);
			if (StringUtils.isNotBlank(temp)) {
				if (column.contains("Date")) {
					q.setParameter(column, Date.valueOf(temp));
				}else if (column.contains("Price")) {
					q.setParameter(column, Double.valueOf(temp));
				} else {
					q.setParameter(column, temp);
				}
			}
			if (column.contains("Date")) {
				String tempStart = column + "Start";
				String tempEnd = column + "End";
				String dateStart = MapUtils.getString(req, tempStart);
				String dateEnd = MapUtils.getString(req, tempEnd);

				if (StringUtils.isNotBlank(dateStart)) {
					q.setParameter(tempStart, Date.valueOf(dateStart));
				} 
				if (StringUtils.isNotBlank(dateEnd)) {
					q.setParameter(tempEnd, Date.valueOf(dateEnd));
				}
			}
		}
	}

}
