package com.dcsquare.iotcloud.rest;

import com.dcsquare.iotcloud.rest.resources.HelloWorldResource;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

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
    }

    @Override
    public void run(IoTCloudConfiguration configuration,
                    Environment environment) throws ClassNotFoundException {

        environment.jersey().register(new HelloWorldResource());
    }
}
