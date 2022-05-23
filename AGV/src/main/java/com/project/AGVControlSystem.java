package com.project;

import org.json.JSONObject;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@ComponentScan
public class AGVControlSystem implements IAGVControlSystem, IPickupItemAssemblyService, IMoveService, IPutDownItemService, IPickUpWarehouseService, IAGVConnectionChecker {


    // Disse 2 pick up og put down metoder er lavet med tanken at samle noget op og sætte det ned er det samme ligemeget hvor agv'en er
    // ellers lav pick up og put down til både warehouse og assembly station.

    @Override
    public void batteryCheck(String statusInformation) {
        String[] tempArray = statusInformation.split(":|,");
        int batteryLevel = Integer.parseInt(tempArray[1]);
        String currentOperation = tempArray[3].substring(1,tempArray[3].length()-1);
        System.out.println(currentOperation);
        if(batteryLevel <= 11 && !currentOperation.equals("MoveToChargerOperation")){
            moveToChargerOperation();
        }
    }

    @Override
    public void pickupWarehouse() {
        continueNextOperation();
        loadProgram("PickWarehouseOperation", "1");
        chooseState("2");
        System.out.println("The items are being picked up at the warehouse");
    }

    @Override
    public void moveToAssembly() {
      continueNextOperation();
        loadProgram("MoveToAssemblyOperation", "1");
        chooseState("2");
        System.out.println("Driving to the Assembly station");
    }

    @Override
    public void putItemAtAssembly() {
        continueNextOperation();
        loadProgram("PutAssemblyOperation", "1");
        chooseState("2");
        System.out.println("Putting item at assembly");
    }

    @Override
    public void pickupItemAssembly() {
        continueNextOperation();
        loadProgram("PickAssemblyOperation", "1");
        chooseState("2");
        System.out.println("Currently picking up item from assembly station");
    }

    @Override
    public void moveToWarehouse() {
        continueNextOperation();
        loadProgram("MoveToStorageOperation", "1");
        chooseState("2");
        System.out.println(" Driving to the warehouse");
    }

    @Override
    public void moveToChargerOperation() {
        continueNextOperation();
        loadProgram("MoveToChargerOperation", "1");
        chooseState("2");
        System.out.println("Charging??");
    }


    @Override
    public void putItemAtWarehouse() {
        continueNextOperation();
        loadProgram("PutWarehouseOperation", "1");
        chooseState("2");
        System.out.println("Putting items at warehouse");
    }


    // This method runs until the state is at 1.
    public void continueNextOperation() {
        while (checkState() == 2 || checkState() == 3) {
            try {
                Thread.sleep(100);
                //System.out.println("hello");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public int checkState() {
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject("http://localhost:8082/v1/status", String.class, "42", "21");


        JSONObject jsonObject = new JSONObject(result);
        int state = jsonObject.getInt("state");
        return state;
    }

    public void chooseState(String state) {
        RestTemplate restTemplate = new RestTemplate();

        System.out.println("dadabadba her");
        // opretter object af RestTemplate
        // opretter forbindelse til AGV'en
        /*System.out.println(restTemplate.getForObject(
                "http://localhost:8082/v1/status",
                String.class,""));
*/
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);


        HttpEntity<String> httpEntity = new HttpEntity<>("{\"State\":" + state + "}", headers);
        //"{\"Program name\":\"MoveToStorageOperation\",\"Sta
        restTemplate.put("http://localhost:8082/v1/status", httpEntity);


        System.out.println(restTemplate.getForObject(
                "http://localhost:8082/v1/status",
                String.class, ""));
    }


    @Override
    public void loadProgram(String program, String state) {
        // Put Request
        RestTemplate restTemplate = new RestTemplate();

        // opretter object af RestTemplate
        // opretter forbindelse til AGV'en

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> httpEntity = new HttpEntity<>("{\"Program name\":\"" + program + "\",\"State\":" + state + "}", headers);

        restTemplate.put("http://localhost:8082/v1/status", httpEntity);

        String string = restTemplate.getForObject("http://localhost:8082/v1/status", String.class, "");

        System.out.println(string);
        batteryCheck(string);

    }


    @Override
    public String check() {
        String connection ="";

        if(checkState()==1 ){
            connection = "Connected";
        }

        return connection;
    }


    @Override
    public String getStatus() {

        return null;
    }
}
