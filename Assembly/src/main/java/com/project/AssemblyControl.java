package com.project;

import org.eclipse.paho.client.mqttv3.*;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Component
public class AssemblyControl implements IAssemblyItemService, IAssemblyPrintService {

    private static final String OPERATION = "emulator/operation";
    private static final String TOPIC = "emulator/status";
    private static final String HEALTH = "emulator/checkhealth";
    IMqttClient publisher;

    private String label = "";
    private String health = "";

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
        String s = new String(payload, StandardCharsets.UTF_8);
        return new MqttMessage(payload);
    }

    @Override
    public String connect() {
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

        return "Connected";

    }

    @Override
    public void subscription() {

        CountDownLatch receivedSignal = new CountDownLatch(1);

        // subscriber til et enkelt topic
        try {
            System.out.println("assembly subscription");
            this.publisher.subscribe(AssemblyControl.TOPIC, (topic, msg) -> {
                String payload = new String(msg.getPayload(), StandardCharsets.UTF_8);
                System.out.println(payload);
                labelSetter(payload);

                receivedSignal.countDown();
            });

            receivedSignal.await(1, TimeUnit.MINUTES);
        } catch (MqttException ex) {
            ex.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean isHealthy() {
        CountDownLatch receivedSignal = new CountDownLatch(1);
        // subscriber til et enkelt topic

        try {
            this.publisher.subscribe(AssemblyControl.HEALTH, (topic, msg) -> {
                String payload = new String(msg.getPayload(), StandardCharsets.UTF_8);
                System.out.println(payload);
                healthSetter(payload);
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


    public void healthSetter(String newHealth) {
        this.health = newHealth;
    }

    public String getHealth() {

        if (health == null) {
            return "";
        }

        return health;
    }

    @Override
    public String healthPrint() {

        getHealth();

        if (health != "") {

            return health;
        }

        return "";
    }

    public void labelSetter(String newLabel) {
        this.label = newLabel;
    }

    public String getLabel() {

        if (label == null) {
            return "";
        }

        return label;
    }


    @Override
    public String labelPrint() {

        getLabel();

        if (label != "") {

            String array[] = label.split(",");
            String lastOperation = array[0].replace("{\"LastOperation\":", "");
            String currentOperation = array[1].replace("\"CurrentOperation\":", "");
            String state = array[2].replace("\"State\":", "");

            return state + ", " + lastOperation + ", " + currentOperation;
        }

        return "";
    }

}
