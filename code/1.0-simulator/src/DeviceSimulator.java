import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author Christian Goetz
 */
public class DeviceSimulator {

    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);

        scheduledExecutorService.schedule(new PahoClient("device1"), 0, TimeUnit.SECONDS);

        scheduledExecutorService.schedule(new PahoClient("device2"), 3, TimeUnit.SECONDS);


    }
}
