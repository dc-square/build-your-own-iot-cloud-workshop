package com.dcsquare.iotcloud.rest.resources;

import com.codahale.metrics.annotation.Timed;
import com.dcsquare.iotcloud.rest.core.SensorDataItem;
import com.dcsquare.iotcloud.rest.db.SensorDataDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/sensor-data")
@Produces(MediaType.APPLICATION_JSON)
public class SensorDataResource {

    private static final Logger log = LoggerFactory.getLogger(SensorDataResource.class);
    private final SensorDataDao dao;

    public SensorDataResource(SensorDataDao dao) {
        this.dao = dao;
    }

    @GET
    @Timed(name = "get-requests")
    public List<SensorDataItem> getAllSensorData() {
        log.info("External application called GetAll!");
        return dao.getAll();
    }

}
