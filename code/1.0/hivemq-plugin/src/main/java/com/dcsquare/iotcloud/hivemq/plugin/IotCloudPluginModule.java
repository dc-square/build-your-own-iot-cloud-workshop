/*
 * Copyright (C) dc-square GmbH - All Rights Reserved
 */

package com.dcsquare.iotcloud.hivemq.plugin;

import com.dcsquare.hivemq.spi.HiveMQPluginModule;
import com.dcsquare.hivemq.spi.PluginEntryPoint;
import com.dcsquare.hivemq.spi.plugin.meta.Information;
import com.dcsquare.hivemq.spi.util.PathUtils;
import com.google.inject.Provider;
import org.apache.commons.configuration.AbstractConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

import static com.dcsquare.hivemq.spi.config.Configurations.newConfigurationProvider;


@Information(name = "IotCloud HiveMQ MySQL Plugin",
        author = "dc-square GmbH",
        version = "1.0",
        description = "This module is responsible for persisting sensor data from devices" +
                "to the database")
public class IotCloudPluginModule extends HiveMQPluginModule {

    private static final String PROPERTY_FILE_NAME = "iotcloud.properties";

    private static Logger log = LoggerFactory.getLogger(IotCloudPluginModule.class);


    @Override
    public Provider<Iterable<? extends AbstractConfiguration>> getConfigurations() {
        final PropertiesConfiguration propertiesConfiguration;
        final File configurationFile = new File(PathUtils.getPluginFolder(), PROPERTY_FILE_NAME);
        try {
            propertiesConfiguration = new PropertiesConfiguration(configurationFile);

            return newConfigurationProvider(propertiesConfiguration);
        } catch (ConfigurationException e) {
            log.error("Could not load configuration file {}", configurationFile.getAbsolutePath());
        }
        throw new RuntimeException("Could not load configurations");
    }


    @Override
    protected void configurePlugin() {
    }


    @Override
    protected Class<? extends PluginEntryPoint> entryPointClass() {
        return IotCloudPlugin.class;
    }

}
