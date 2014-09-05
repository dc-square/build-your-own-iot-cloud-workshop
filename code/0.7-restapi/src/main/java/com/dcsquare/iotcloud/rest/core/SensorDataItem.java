package com.dcsquare.iotcloud.rest.core;

/**
 * @author Christian Götz
 */
import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.DateTime;

public class SensorDataItem {
    private long id;

    private String deviceId;

    private String value;

    private DateTime timestamp;

    public SensorDataItem() {
        // Jackson deserialization
    }

    public SensorDataItem(long id, String deviceId, String value, DateTime timestamp) {
        this.id = id;
        this.deviceId = deviceId;
        this.value = value;
        this.timestamp = timestamp;
    }

    @JsonProperty
    public long getId() {
        return id;
    }

    @JsonProperty
    public String getDeviceId() {
        return deviceId;
    }

    @JsonProperty
    public DateTime getTimestamp() {
        return timestamp;
    }

    @JsonProperty
    public String getValue() {
        return value;
    }
}
