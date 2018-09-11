package com.base.util;

import java.util.List;
import java.util.Map;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class JsonUtil {

	private static Gson gson;

	static {

		GsonBuilder builder = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
				.enableComplexMapKeySerialization().setExclusionStrategies(new ExclusionStrategy() {
					@Override
					public boolean shouldSkipClass(Class<?> cls) {
						if (cls == Throwable.class) {
							return true;
						}
						return false;
					}

					@Override
					public boolean shouldSkipField(FieldAttributes attributes) {
						return false;
					}
				}).serializeNulls();
		gson = builder.create();
	}

	public static <T> T fromJson(String str, Class<T> clazz) {
		return gson.fromJson(str, clazz);
	}

	public static String toJson(Object object) {
		return gson.toJson(object);
	}
	
	public static <T> List<T> toList(String str, Class<T> clazz) {
		return gson.fromJson(str,  new TypeToken<List<T>>() {}.getType());
	}
	
	public static List<Map<String, Object>> toListMap(String str) {
		return gson.fromJson(str,  new TypeToken<List<Map<String, Object>>>() {}.getType());
	}

}
