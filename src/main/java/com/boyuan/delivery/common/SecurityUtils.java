/*
 *  Copyright 2020 北京渤远物流. All Rights Reserved.
 */

package com.boyuan.delivery.common;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.Field;

/**
 * This util is used for security purpose
 */
public class SecurityUtils {

    protected static final Log logger = LogFactory.getLog(SecurityUtils.class);
    /**
     * In case of malicious input from user.
     * This method will trim all string field in object.
     * If a string field is empty, this field will be set null instead, which will avoid validator validation when this field is not required.
     * @param obj
     */
    public static void trimStringFieldOrSetNull(Object obj){

        if(obj!= null){
            Field[] fields = obj.getClass().getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                if (field.getType().getName().equals("java.lang.String")) {
                    //get field name
                    String key = field.getName();
                    //get access to private field
                    field.setAccessible(true);
                    String value = null;
                    try {

                        value = (String) field.get(obj);
                        if (value == null)
                            continue;

                        if(StringUtils.isEmpty(value.trim())){
                            //Set null if string field is empty
                            field.set(obj, null);
                        } else {
                            //Trim all string field
                            field.set(obj, value.trim());
                        }

                    } catch (IllegalAccessException e){
                        logger.error("Get field value by reflect encountered error, field name: " + key, e);
                    }

                }
            }
        }
    }
}
