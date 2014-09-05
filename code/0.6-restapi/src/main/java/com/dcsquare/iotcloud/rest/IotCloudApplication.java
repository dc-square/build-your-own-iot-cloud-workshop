package com.dcsquare.iotcloud.rest;

import com.dcsquare.iotcloud.rest.resources.SensorDataResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class IotCloudApplication extends Application<IoTCloudConfiguration> {

    final Logger logger = LoggerFactory.getLogger(IotCloudApplication.class);

    public static void main(String[] args) throws Exception {
        new IotCloudApplication().run(args);
    }

    @Override
    public String getName() {
        return "iotcloud-rest-api";
    }

    @Override
    public void initialize(Bootstrap<IoTCloudConfiguration> bootstrap) {
    }

    @Override
    public void run(IoTCloudConfiguration configuration,
                    Environment environment) throws ClassNotFoundException {

        String dbUrl = configuration.getDataSourceFactory().getUrl();
        String username = configuration.getDataSourceFactory().getUser();
        String password = configuration.getDataSourceFactory().getPassword();
        Connection connection = null;
        try {
            Class.forName(configuration.getDataSourceFactory().getDriverClass());
            connection = DriverManager.getConnection(dbUrl,
                    username, password);
        } catch (ClassNotFoundException e) {
            logger.error("MySQL Treiber nicht gefunden!", e);
        } catch (SQLException e) {
            logger.error("Datenbankverbindung konnte nicht hergestellt werden!", e);
        }


        environment.jersey().register(new SensorDataResource(connection));
    }
}
