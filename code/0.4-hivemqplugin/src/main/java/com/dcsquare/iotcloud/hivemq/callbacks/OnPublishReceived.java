/*
 * Copyright (C) dc-square GmbH - All Rights Reserved
 */

package com.dcsquare.iotcloud.hivemq.callbacks;

import com.dcsquare.hivemq.spi.callback.CallbackPriority;
import com.dcsquare.hivemq.spi.callback.events.OnPublishReceivedCallback;
import com.dcsquare.hivemq.spi.callback.exception.OnPublishReceivedException;
import com.dcsquare.hivemq.spi.message.PUBLISH;
import com.dcsquare.hivemq.spi.security.ClientData;
import com.google.common.base.Charsets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OnPublishReceived implements OnPublishReceivedCallback {

    final Logger logger = LoggerFactory.getLogger(OnPublishReceived.class);
    private final String SQL_INSERT_QUERY = "INSERT INTO `data` (`id`, `timestamp`, `deviceId`, `value`) VALUES (NULL, CURRENT_TIMESTAMP, ?, ?)";
    private PreparedStatement preparedStatement;

    public OnPublishReceived(Connection connection) {

        try {
            preparedStatement = connection.prepareStatement(SQL_INSERT_QUERY);
        } catch (SQLException e) {
            logger.error("Fehler beim Vorbereiten des PreparedStatements in die Datenbank!");
        }
    }

    public void onPublishReceived(final PUBLISH publish, final ClientData clientData) throws OnPublishReceivedException {

        final String message = new String(publish.getPayload(), Charsets.UTF_8);

        persistTemperatureData(clientData.getClientId(), message);
    }

    private void persistTemperatureData(final String clientId, final String message) {

        try {
            preparedStatement.setString(1, clientId);
            preparedStatement.setString(2, message);
            preparedStatement.execute();
        } catch (SQLException e) {
            logger.error("Fehler beim Schreiben in die Datenbank!");
        }

    }


    public int priority() {
        return CallbackPriority.CRITICAL;
    }
}
