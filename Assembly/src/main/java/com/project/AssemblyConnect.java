package com.project;

import com.project.interfaces.IConnect;
import org.eclipse.paho.client.mqttv3.*;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Component
public class AssemblyConnect implements iAssemblyItemService {

    private static final String OPERATION = "emulator/operation";
    private static final String TOPIC = "emulator/status";
    private static final String HEALTH = "emulator/checkhealth";
    IMqttClient publisher;

    public static void main(String[] args) {
        AssemblyConnect as = new AssemblyConnect();
        as.connect();
        as.subscription();
        as.assembleItem();
    }

    @Override
    public void assembleItem() {
        subscription();
        try {
            MqttMessage msg = readMessage();
            msg.setQos(0);
            msg.setRetained(true);
            this.publisher.publish(OPERATION, msg);
            System.out.println("The items are being assembled at the Assembly Station" + msg);

        } catch (MqttException e) {
            e.printStackTrace();
        }
        isHealthy();
        System.out.println("Assembly done?");
    }

    private MqttMessage readMessage() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("ProcessID", 1234);
        //String string1 = "{\"ProcessID\":12345}";
        byte[] payload = jsonObject.toString().getBytes(StandardCharsets.UTF_8);
        return new MqttMessage(payload);
    }

    @Override
    public void connect() {
        System.out.println("assemblyConnect here");
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

        } catch (MqttException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean isHealthy(){
        CountDownLatch receivedSignal = new CountDownLatch(1);
        // subscriber til et enkelt topic
        try {
            this.publisher.subscribe(AssemblyConnect.HEALTH, (topic, msg) -> {
                String payload = new String(msg.getPayload(), StandardCharsets.UTF_8);
                System.out.println(payload);
                receivedSignal.countDown();
            });
            receivedSignal.await(1, TimeUnit.MINUTES);
        } catch (MqttException ex) {
            ex.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public void subscription() {
        CountDownLatch receivedSignal = new CountDownLatch(1);

        // subscriber til et enkelt topic
        try {
            System.out.println("assembly subscription");
            this.publisher.subscribe(AssemblyConnect.TOPIC, (topic, msg) -> {
                String payload = new String(msg.getPayload(), StandardCharsets.UTF_8);
                System.out.println(payload);
                receivedSignal.countDown();
            });

            receivedSignal.await(1, TimeUnit.MINUTES);
        } catch (MqttException ex) {
            ex.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
