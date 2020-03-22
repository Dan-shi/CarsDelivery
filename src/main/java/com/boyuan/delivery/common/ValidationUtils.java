/*
 *  Copyright 2020 北京渤远物流. All Rights Reserved.
 */

package com.boyuan.delivery.common;


import com.boyuan.delivery.model.ValidationResult;
import org.apache.commons.collections4.CollectionUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;

public class ValidationUtils {

    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    public static <T> ValidationResult validateEntity(T obj) {
        ValidationResult result = new ValidationResult();
        Set<ConstraintViolation<T>> set = validator.validate(obj, Default.class);
        if (CollectionUtils.isNotEmpty(set)) {
            result.setHasErrors(true);
            Map<String, StringBuffer> errorMsg = setErrorMsg(set);
            result.setErrorMsg(errorMsg);
        }
        return result;
    }

    public static <T> ValidationResult validateProperty(T obj, String propertyName) {
        ValidationResult result = new ValidationResult();
        Set<ConstraintViolation<T>> set = validator.validateProperty(obj, propertyName, Default.class);
        if (set != null && set.size() != 0) {
            result.setHasErrors(true);
            Map<String, StringBuffer> errorMsg = setErrorMsg(set);
            result.setErrorMsg(errorMsg);
        }
        return result;
    }

    private static <T> Map<String, StringBuffer> setErrorMsg(Set<ConstraintViolation<T>> set){
        Map<String, StringBuffer> errorMsg = new HashMap<String, StringBuffer>();
        String property = null;
        for (ConstraintViolation<T> cv : set) {

            property = cv.getPropertyPath().toString();
            if(errorMsg.get(property) != null){
                errorMsg.get(property).append("," + cv.getMessage());
            }else{
                StringBuffer sb = new StringBuffer();
                sb.append(cv.getMessage());
                errorMsg.put(property, sb);
            }
        }
        return errorMsg;
    }

}
