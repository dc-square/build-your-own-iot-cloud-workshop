/*
 * Copyright (C) dc-square GmbH - All Rights Reserved
 */

package com.dcsquare.iotcloud.hivemq.plugin;

import com.dcsquare.hivemq.spi.PluginEntryPoint;
import com.dcsquare.hivemq.spi.callback.registry.CallbackRegistry;
import com.dcsquare.iotcloud.hivemq.callbacks.OnPublishReceived;
import org.apache.commons.configuration.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class IotCloudPlugin extends PluginEntryPoint {

    final Logger logger = LoggerFactory.getLogger(IotCloudPlugin.class);

    private final Configuration configuration;

    @Inject
    public IotCloudPlugin(Configuration configuration) {
        this.configuration = configuration;
    }


    @PostConstruct
    public void postConstruct() {

        final CallbackRegistry callbackRegistry = getCallbackRegistry();
        callbackRegistry.addCallback(new OnPublishReceived(connectToDatabase()));
    }

    public Connection connectToDatabase() {
        String dbUrl = "jdbc:mysql://" + configuration.getString("database.host") + ":" + configuration.getString("database.port") + "/" + configuration.getString("database.name");
        String username = configuration.getString("database.username");
        String password = configuration.getString("database.password");
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(dbUrl,
                    username, password);
        } catch (ClassNotFoundException e) {
            logger.error("MySQL Treiber nicht gefunden!", e);
        } catch (SQLException e) {
            logger.error("Datenbankverbindung konnte nicht hergestellt werden!", e);
        }

        return connection;
    }

}
