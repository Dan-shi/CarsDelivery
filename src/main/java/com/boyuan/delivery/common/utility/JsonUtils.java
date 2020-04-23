/*
 *  Copyright 2020 北京渤远物流. All Rights Reserved.
 */

package com.boyuan.delivery.common.utility;

import com.alibaba.fastjson.JSONObject;
import com.boyuan.delivery.common.exception.JsonException;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.afterburner.AfterburnerModule;

import java.util.List;

/**
 * The utility to serialize and deserialize the JSON string.
 */
public class JsonUtils {

    /**
     * The request scoped Object Mapper
     */
    static ObjectMapper objectMapper;

    /**
     * The static string for error logs
     */
    private static final String DETAIL = " :Details:";

    static {
        objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.registerModule(new AfterburnerModule());
    }

    /**
     * Serialize the "obj" to JSON string
     *
     * @param obj The java bean
     * @param <T> The type of the java bean
     * @return The JSON string
     * @throws JsonException
     */
    public static <T> String serialize(T obj) {

        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new JsonException(e);
        }
    }

    /**
     * Deserialize the json object to the java bean of the type "T".
     *
     * @param obj   The json object
     * @param clazz The class of the java bean
     * @param <T>   The type of the class.
     * @return The java bean.
     * @throws JsonException
     */
    public static <T> T deserialize(Object obj, Class<T> clazz) {
        try {
            if (obj instanceof String) {
                return objectMapper.readValue(String.valueOf(obj), clazz);
            } else if (obj instanceof JSONObject) {
                return objectMapper.readValue(((JSONObject) obj).toString(), clazz);
            } else {
                return objectMapper.convertValue(obj, clazz);
            }
        } catch (Exception e) {
            throw new JsonException(e);
        }
    }

//    /**
//     * To convert a Json string to a list. The class of the item in the list
//     * must be indicated.
//     *
//     * @param json
//     * @param cls
//     * @return
//     * @throws JsonException
//     */
//    public static List<?> deserializeList(String json, Class<?> cls) {
//        List<?> resList = null;
//        try {
//            resList = com.kingland.platform.service_commonutils.common.util.JsonUtility.deserializeList(json, cls);
//        } catch (DataFabricBusinessException e) {
//            throw new JsonException(
//                    ErrorMessage.CANNOT_CONVERT_JSON_TO_LIST_ERROR + DETAIL + e.getMessage());
//        }
//        return resList;
//    }
}
