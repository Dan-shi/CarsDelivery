/*
 *  Copyright 2020 北京渤远物流. All Rights Reserved.
 */

package com.boyuan.delivery.mapper;

import com.boyuan.delivery.model.LocationHistory;

public interface LocationHistoryMapper {

    int createLocationHis(LocationHistory location);

    int updateLocationHis(LocationHistory location);
}
