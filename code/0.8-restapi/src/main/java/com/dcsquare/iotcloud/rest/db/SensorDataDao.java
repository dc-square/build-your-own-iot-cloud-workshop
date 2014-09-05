package com.dcsquare.iotcloud.rest.db;

import com.dcsquare.iotcloud.rest.core.SensorDataItem;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

/**
 * @author Christian Götz
 */
@RegisterMapper(SensorDataMapper.class)
public interface SensorDataDao {

    @SqlQuery("select * from data")
    List<SensorDataItem> getAll();
}

