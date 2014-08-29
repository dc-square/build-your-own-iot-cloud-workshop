package com.dcsquare.iotcloud.rest;

import com.dcsquare.iotcloud.rest.db.SensorDataDao;
import com.dcsquare.iotcloud.rest.resources.SensorDataResource;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;

public class IotCloudApplication extends Application<IoTCloudConfiguration> {

    public static void main(String[] args) throws Exception {
        new IotCloudApplication().run(args);
    }

    @Override
    public String getName() {
        return "iotcloud-rest-api";
    }

    @Override
    public void initialize(Bootstrap<IoTCloudConfiguration> bootstrap) {
        bootstrap.addBundle(new AssetsBundle("/assets", "/app"));
    }

    @Override
    public void run(IoTCloudConfiguration configuration,
                    Environment environment) throws ClassNotFoundException {

        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, configuration.getDataSourceFactory(), "mysql");
        final SensorDataDao dao = jdbi.onDemand(SensorDataDao.class);

        environment.jersey().register(new SensorDataResource(dao));
    }
}
