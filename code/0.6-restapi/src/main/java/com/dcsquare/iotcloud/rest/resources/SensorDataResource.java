package com.dcsquare.iotcloud.rest.resources;

import com.codahale.metrics.annotation.Timed;
import com.dcsquare.iotcloud.rest.core.SensorDataItem;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Path("/sensor-data")
@Produces(MediaType.APPLICATION_JSON)
public class SensorDataResource {

    private static final Logger log = LoggerFactory.getLogger(SensorDataResource.class);
    private final Connection connection;

    public SensorDataResource(Connection connection) {
        this.connection = connection;
    }

    @GET
    @Timed(name = "get-requests")
    public List<SensorDataItem> getAllSensorData() {

        ResultSet resultSet;
        ArrayList<SensorDataItem> sensorDataItems = null;
        try {
            resultSet = connection.createStatement().executeQuery("Select * from data");


            sensorDataItems = new ArrayList<SensorDataItem>();
            while (resultSet.next()) {

                sensorDataItems.add(new SensorDataItem(resultSet.getInt("id"), resultSet.getString("deviceId"), resultSet.getString("value"), new DateTime(resultSet.getTimestamp("timestamp"))));

            }

        } catch (SQLException e) {
            log.error("Datenbankfehler", e);
        }

        log.info("External application called GetAll!");
        return sensorDataItems;
    }

}
