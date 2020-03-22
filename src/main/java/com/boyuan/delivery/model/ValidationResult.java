/*
 *  Copyright 2020 北京渤远物流. All Rights Reserved.
 */

package com.boyuan.delivery.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * Validation result of validator
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ValidationResult {

    private boolean hasErrors;

    private Map<String, StringBuffer> errorMsg;
}
