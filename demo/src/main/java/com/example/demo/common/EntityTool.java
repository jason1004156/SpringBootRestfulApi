package com.example.demo.common;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;

public class EntityTool {
	
	
	public static <T> String[] getFields(T entity) {
		List<String> columns = new ArrayList<>();
		for (Field field : entity.getClass().getDeclaredFields()) {
			if (field.isAnnotationPresent(Column.class)) {
				columns.add(field.getAnnotation(Column.class).name());
			}
			
		}

		return columns.toArray(new String[0]);
	}
}
