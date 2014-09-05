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

public class OnPublishReceived implements OnPublishReceivedCallback {

    final Logger logger = LoggerFactory.getLogger(OnPublishReceived.class);

    @Override
    public void onPublishReceived(final PUBLISH publish, final ClientData clientData) throws OnPublishReceivedException {
        System.out.println(
                "ClientID: "+clientData.getClientId()+
                " Topic: "+publish.getTopic()+
                " Payload "+new String(publish.getPayload(), Charsets.UTF_8));
    }

    @Override
    public int priority() {
        return CallbackPriority.CRITICAL;
    }
}
