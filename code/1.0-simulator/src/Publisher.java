import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

/**
 * @author Christian Goetz
 */
public class Publisher {

    private MqttClient client = null;
    private final RandomGenerator randomGenerator;
    private String TEMPERATURE_TOPIC=null;

    public Publisher(MqttClient client) {
        this.client = client;
        this.randomGenerator = new RandomGenerator();

        TEMPERATURE_TOPIC = "iotcloud/"+client.getClientId()+"/temperature";
    }

    public void publishData() throws MqttException {
        final Integer temperature = publishTemperature();

        System.out.println(client.getClientId()+": Temperature: \t"+temperature);
    }

    private Integer publishTemperature() throws MqttException {
        Integer temperature = randomGenerator.getRandomTemp();
        client.publish(TEMPERATURE_TOPIC, temperature.toString().getBytes(), 2, false);
        return temperature;
    }

}
