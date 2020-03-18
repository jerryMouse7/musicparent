package com.liu.utils.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

/**
 * json相关工具类
 */
public class JsonUtils {

	private final static Logger logger = LoggerFactory
			.getLogger(JsonUtils.class);

	private static ObjectMapper jsonMapper;

	static {
		jsonMapper = new ObjectMapper();
		// set DateTime default formatter "yyyy-MM-dd'T'HH:mm:ss.SSSZ"
		jsonMapper.registerModule(new JodaModule());
		jsonMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
	}

	public static <T> T toObject(String value, Class<T> clazz) {
		if (StringUtils.isBlank(value)) {
			return null;
		}
		try {
			return jsonMapper.readValue(value, clazz);
		} catch (Exception e) {
			logger.info("", e);
			return null;
		}
	}

	/**
	 * 转成Json格式数据
	 *
	 * @param obj
	 * @return
	 */
	public static String toString(Object obj) {
		if (Objects.isNull(obj)) {
			return "";
		}
		try {

			return jsonMapper.writeValueAsString(obj);
		} catch (Exception e) {

			logger.info("", e);
			return null;
		}
	}

	/**
	 * 对象保存为JSON String
	 *
	 * @param value
	 *
	 * @return String
	 * @throws Exception
	 */
	public static String writeJsonString(Object value) throws Exception {
		return jsonMapper.writeValueAsString(value);
	}

	/**
	 * JSON String 转化为对象
	 *
	 * @param value
	 * @param clazz
	 *
	 * @return T
	 * @throws Exception
	 */
	public static <T> T readJsonObject(String value, Class<T> clazz)
			throws Exception {
		return jsonMapper.readValue(value, clazz);
	}

	/**
	 * JSON String 转化为对象
	 *
	 * @param value
	 * @param valueTypeRef
	 *
	 * @return T
	 * @throws Exception
	 */
	public static <T> T readJsonObject(String value, TypeReference valueTypeRef)
			throws Exception {
		return jsonMapper.readValue(value, valueTypeRef);
	}

	/**
	 * JSON String 转化为对象
	 *
	 * @param value
	 * @param clazz
	 *
	 * @return T
	 * @throws Exception
	 */
	public static <T> T readJsonObject(byte[] value, Class<T> clazz)
			throws Exception {
		return jsonMapper.readValue(value, clazz);
	}

	/**
	 * 将Object类型的对象转换成指定类型
	 */
	public static <T> T convert(Object obj, Class<T> clazz) {
	    if(Objects.isNull(obj)){
	        return null;
        }

		String objStr = toString(obj);
		return toObject(objStr, clazz);
	}
}
