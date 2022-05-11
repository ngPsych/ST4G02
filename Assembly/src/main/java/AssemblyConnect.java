import interfaces.iConnect;
import org.eclipse.paho.client.mqttv3.*;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Component
public class AssemblyConnect implements iConnect, iAssemblyItemService{

    private static final String OPERATION = "emulator/operation";
    private static final String TOPIC = "emulator/status";

    IMqttClient client;
    String publisherId = "Assembly Station";
    IMqttClient publisher;


    public static void main(String[] args){
        AssemblyConnect as = new AssemblyConnect();
        as.connect();
        as.assembleItem();
        as.subscription();

    }

    @Override
    public void assembleItem() {
        System.out.println("assembly here");

        //opretter id for MQTT client

        try {
            //MqttMessage msg = new MqttMessage();
            MqttMessage msg = readMessage();
            // msg.setPayload(("{\"ProcessID\":12345}").getBytes(StandardCharsets.UTF_8));
            msg.setQos(0);
            msg.setRetained(true);

            System.out.println("yo we tryin");
            publisher.publish(OPERATION, msg);
            System.out.println("The item have been assembled at the Assembly" + msg);


        } catch (MqttException e) {
            e.printStackTrace();
        }






    }

    private MqttMessage readMessage(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("ProcessID", 1222);
        //String string1 = "{\"ProcessID\":12345}";
        byte[] payload = jsonObject.toString().getBytes(StandardCharsets.UTF_8);
        return new MqttMessage(payload);
    }





    @Override
    public void connect(){
        System.out.println("assembly here");
        //opretter id for MQTT client
        String publisherId = "Assembly Station";

        try {
            publisher = new MqttClient("tcp://localhost:1883", publisherId);
            //opretter MQTTclient object, hvor vi tilføjer hvilket netværk vi skal oprette forbindelse til
            //IMqttClient publisher = new MqttClient("tcp://localhost:1883", publisherId);
            //opstiller options for connection
            MqttConnectOptions options = new MqttConnectOptions();
            options.setAutomaticReconnect(true);
            options.setCleanSession(true);
            options.setConnectionTimeout(10);
            //opretter forbindelse til Assembly Station
            publisher.connect(options);
            System.out.println("heyy");

        } catch (MqttException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void subscription(){

        //
        CountDownLatch receivedSignal = new CountDownLatch(15);
        // subscriber til et enkelt topic

        try {
            System.out.println("mamamamamaa");
            publisher.subscribe(AssemblyConnect.TOPIC, (topic, msg) -> {
                String payload = new String(msg.getPayload(), StandardCharsets.UTF_8);
                System.out.println(payload);
                receivedSignal.countDown();
            });
            receivedSignal.await(1, TimeUnit.MINUTES);
        }
    catch (MqttException ex) {
                ex.printStackTrace();
            } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }}
