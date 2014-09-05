import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class PahoClient implements Runnable {

    //Publish interval in seconds
    private final int DEFAULT_INTERVAL = 5;
    private String deviceId;

    private int interval = DEFAULT_INTERVAL;

    public PahoClient(String deviceId) {
        this.deviceId = deviceId;
    }

    public void run() {

        try {
            MqttClient client = new MqttClient("tcp://localhost", deviceId, new MemoryPersistence());

            client.connect();

            if (client.isConnected()) {
                publishDataPeriodically(client);
            } else {
                System.out.println("Could not connect!");
            }
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    private void publishDataPeriodically(MqttClient client) {

        Publisher publisher = new Publisher(client);

        while (true) {
            try {
                publisher.publishData();
                Thread.sleep(interval * 1000);

            } catch (MqttException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}