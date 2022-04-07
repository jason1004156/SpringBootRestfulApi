package com.example.demo.compoment;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReturnMsg {
	
	private String state;
	private Object data;
	
	public static ReturnMsg succ(Object data) {
		return new ReturnMsg("200", data);
	}
	
	public static ReturnMsg succ() {
		return new ReturnMsg("200", "操作成功");
	}
}
