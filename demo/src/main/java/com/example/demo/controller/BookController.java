package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.compoment.ReturnMsg;
import com.example.demo.controller.Interface.RestfulController;
import com.example.demo.entity.Book;
import com.example.demo.service.BookService;

@RestController
@RequestMapping("/book")
public class BookController implements RestfulController<Book> {

	@Autowired
	private BookService bs;

	/**
	 * 依條件取得
	 */
	@Override
	public ReturnMsg get(HttpServletRequest req) throws Exception {
		try {
			return ReturnMsg.succ(bs.getByCondition(getParameterMap(req)));
		} catch (Exception e) {
			return ReturnMsg.err(e.getMessage());
		}
	}

	/**
	 * 依ISBN尋找書號
	 */
	@Override
	public ReturnMsg getById(String id) throws Exception {
		try {
			return ReturnMsg.succ(bs.getById(id));
		} catch (Exception e) {
			return ReturnMsg.err(e.getMessage());
		}
	}

	/**
	 * 儲存
	 */
	@Override
	public ReturnMsg save(Book req) throws Exception {
		try {
			bs.save(req);
		} catch (Exception e) {
			return ReturnMsg.err(e.getMessage());
		}
		return ReturnMsg.succ();
	}

	/**
	 * 更新
	 */
	@Override
	public ReturnMsg update(String id, Book book) throws Exception {
		try {
			bs.update(id, book);
		} catch (Exception e) {
			return ReturnMsg.err(e.getMessage());
		}
		return ReturnMsg.succ();
	}

	/**
	 * 刪除
	 */
	@Override
	public ReturnMsg detete(String id) throws Exception {
		try {
			bs.delete(id);
		} catch (Exception e) {
			return ReturnMsg.err(e.getMessage());
		}
		return ReturnMsg.succ();
	}

	/**
	 * 自HttpServletRequest取得Map
	 * 
	 * @param request
	 * @return
	 */
	private Map<String, String> getParameterMap(HttpServletRequest request) {
		Map<String, String[]> springParameterMap = request.getParameterMap();
		Map<String, String> pluginParameterMap = new HashMap<>();
		for (String parameterName : springParameterMap.keySet()) {
			String[] values = springParameterMap.get(parameterName);
			if (values != null && values.length > 0) {
				pluginParameterMap.put(parameterName, values[0]);
			}
		}
		return pluginParameterMap;
	}
}
