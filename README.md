# Build your own IoT Cloud

This repository contains all code snippets and materials for our workshop "Build your own IoT Cloud" at IoTCon 2014 in Berlin (01.09-03.09.2014)

## How to use this repository

### Eclipse

The folder code is an Eclipse workspace. You can just open that in Eclipse and all projects should be there.

Attention: You need an Eclipse with the M2E (Maven Eclipse Plugin) installed, in order to compile and run the HiveMQ plugin and the REST API.

### IntelliJ

Just open the individual projects in IntelliJ and enable Maven Auto-Import functionality.

## Run Simulator

Run Devicesimulator.java in your IDE.

## Run HiveMQ Plugin

- Download HiveMQ
- Add path to HiveMQ in pom.xml
- Run mvn package -P RunWithHiveMQ 

## Run Dropwizard REST API

- Run IotCloudApplication.java with the following programm arguments: server iotcloud.yml

## Use Websocket Client

- Open index.html in a current browser

## Run Web App

- Copy web app into 1.0-restapi/src/main/resources/assets
- Run REST API
- Open http://localhost:8080/app in your local browser



