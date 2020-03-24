/*
 *  Copyright 2020 北京渤远物流. All Rights Reserved.
 */

package com.boyuan.delivery.service;

import com.boyuan.delivery.model.LocationHistory;

/**
 * Location history service
 */
public interface LocationHistoryService {

    int createLocationHis(LocationHistory location);

    int upateLocationHis(LocationHistory location);
}
