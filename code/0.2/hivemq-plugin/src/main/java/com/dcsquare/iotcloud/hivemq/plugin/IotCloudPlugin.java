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
        callbackRegistry.addCallback(new OnPublishReceived());
    }

}
