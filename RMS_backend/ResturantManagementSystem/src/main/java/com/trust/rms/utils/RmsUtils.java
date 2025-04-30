package com.trust.rms.utils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class RmsUtils {
	
	private RmsUtils() {
		
	}
	
	public static ResponseEntity<String> getResponseEntity(String response, HttpStatus httpStatus){
		return new ResponseEntity<String>("{\"message \": \""+response+"\"}",httpStatus);
	}
	
	public static <T>T mapToEntity(Map<String,String>request, Class<T> clazz){
		try {
            T instance = clazz.getDeclaredConstructor().newInstance();
            for (Map.Entry<String, String> entry : request.entrySet()) {
                Field field = null;
                try {
                    field = clazz.getDeclaredField(entry.getKey());
                    field.setAccessible(true);
                    Object value = convert(field.getType(), entry.getValue());
                    field.set(instance, value);
                } catch (NoSuchFieldException ignored) {
                    // field not present in POJO — you can choose to log it or ignore it
                }
            }
            return instance;
        } catch (Exception e) {
            throw new RuntimeException("Error mapping request to " + clazz.getSimpleName(), e);
        }
	}
	
	@SuppressWarnings("unchecked")
	private static Object convert(Class<?> type, String value) {
		if (type == String.class) return value;
	    if (type == Integer.class || type == int.class) return Integer.parseInt(value);
	    if (type == Double.class || type == double.class) return Double.parseDouble(value);
	    if (type == Float.class || type == float.class) return Float.parseFloat(value);
	    if (type == Boolean.class || type == boolean.class) return Boolean.parseBoolean(value);
	    if (type == Character.class || type == char.class) return value.charAt(0); 
	    if (type.isEnum()) return Enum.valueOf((Class<Enum>) type, value.toUpperCase());
        return null;
	}

}
