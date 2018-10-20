package com.lottery.common.util;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

/**
 * @author zhangjin E-mail: zhangjin@meirongzongjian.com
 * @version 1.0
 */
public class JSONUtil {
    private static Logger logger = LoggerFactory.getLogger(JSONUtil.class);
//    private static ObjectMapper mapper = new ObjectMapper();
//    private static ObjectMapper dynamicMapper = new ObjectMapper();
    private static ObjectMappingCustomer selfMapper = new ObjectMappingCustomer();
    private static ObjectMappingCustomer dynamicSelfMapper = new ObjectMappingCustomer();

    public static class ObjectMappingCustomer extends ObjectMapper {
    	
		private static final long serialVersionUID = 1L;

		public ObjectMappingCustomer() {  
            super();
            this.getSerializerProvider().setNullKeySerializer(new JsonSerializer<Object>() {

    			@Override
    			public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers)
    					throws IOException, JsonProcessingException {
					gen.writeString("beiquan-null-key");
    			}
    		});
            this.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {

    			@Override
    			public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers)
    					throws IOException, JsonProcessingException {
					gen.writeString("");
    			}
    		});
      
        }  
    }
    
    static {
    	dynamicSelfMapper.setSerializationInclusion(Include.NON_EMPTY);
    	dynamicSelfMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    	dynamicSelfMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
    }

    public JSONUtil() {
    }

    public static String objToStr(Object obj) {
        return objToStr(obj, false);
    }

    public static String objToStr(Object obj, boolean writeNull) {
        try {
            return writeNull ? selfMapper.writeValueAsString(obj) : dynamicSelfMapper.writeValueAsString(obj);
        } catch (Exception e) {
            logger.error("Error: {}",e);
            return null;
        }
    }

    public static <T> T strToObj(String str, Class<T> claz) {
        return strToObj(str, claz, false);
    }

    public static <T> T strToObj(String str, Class<T> claz, boolean writeNull) {
        try {
            return writeNull ? selfMapper.readValue(str, claz) : dynamicSelfMapper.readValue(str, claz);
        } catch (Exception e) {
        	logger.error("Error: {},{}",str,e);
            return null;
        }
    }

    @SuppressWarnings("unchecked")
	public static <T> T cloneObj(T obj) {
        return (T) cloneObj(obj, obj.getClass());
    }

    public static <T> T cloneObj(Object obj, Class<T> claz) {
        try {
            return dynamicSelfMapper.readValue(dynamicSelfMapper.writeValueAsString(obj), claz);
        } catch (Exception e) {
        	logger.error("Error: {},{}",claz.getName(),e);
            return null;
        }
    }

    @SuppressWarnings("rawtypes")
	public static String getParam(String content, String key) {
        Map result = JSONUtil.strToObj(content,Map.class);
        return String.valueOf(result.get(key));
    }
}
