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
	 * 取得所有資料
	 */
	@Override
	public ReturnMsg getAll() {
		return ReturnMsg.succ(bs.getAll());
	}

	/**
	 * 依ISBN尋找書號
	 */
	@Override
	public ReturnMsg getById(String id) throws Exception {
		return ReturnMsg.succ(bs.getById(id));
	}

	/**
	 * 依條件取得
	 */
	@Override
	public ReturnMsg getByCondition(HttpServletRequest req) throws Exception {
		return ReturnMsg.succ(bs.getByCondition(getParameterMap(req)));
	}

	/**
	 * 儲存
	 * 
	 * @throws Exception
	 */
	@Override
	public ReturnMsg save(Book req) throws Exception {
		bs.save(req);
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
