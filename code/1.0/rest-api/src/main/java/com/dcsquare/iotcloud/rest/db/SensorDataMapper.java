package com.dcsquare.iotcloud.rest.db;

import com.dcsquare.iotcloud.rest.core.SensorDataItem;
import org.joda.time.DateTime;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Christian Götz
 */
public class SensorDataMapper implements ResultSetMapper<SensorDataItem>{

    @Override
    public SensorDataItem map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        return new SensorDataItem(r.getInt("id"), r.getString("deviceId"), r.getString("value"), new DateTime(r.getTimestamp("timestamp")));
    }
}
