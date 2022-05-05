import interfaces.iConnect;
import org.eclipse.paho.client.mqttv3.*;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Component
public class AssemblyConnect implements iConnect, iAssemblyItemService{

    @Override
    public void assemblyItem() {

        System.out.println("The item have been assembled at the Assembly");

    }




    private static final String TOPIC = "emulator/status";

    @Override
    public void connect(){
        System.out.println("assembly here");

        //opretter id for MQTT client
        String publisherId = "Assembly Station";

        try {
            //opretter MQTTclient object, hvor vi tilføjer hvilket netværk vi skal oprette forbindelse til
            IMqttClient publisher = new MqttClient("tcp://localhost:1883", publisherId);


            //opstiller options for connection
            MqttConnectOptions options = new MqttConnectOptions();
            options.setAutomaticReconnect(true);
            options.setCleanSession(true);
            options.setConnectionTimeout(10);
            //opretter forbindelse til Assembly Station
            publisher.connect(options);

            System.out.println("heyy");

            //
            CountDownLatch receivedSignal = new CountDownLatch(10);
            // subscriber til et enkelt topic
            publisher.subscribe(AssemblyConnect.TOPIC, (topic, msg) -> {
                String payload = new String(msg.getPayload(), StandardCharsets.UTF_8);
                System.out.println(payload);
                receivedSignal.countDown();
            });
            receivedSignal.await(1, TimeUnit.MINUTES);

        } catch (MqttException | InterruptedException e) {
            e.printStackTrace();
        }
    }


}
