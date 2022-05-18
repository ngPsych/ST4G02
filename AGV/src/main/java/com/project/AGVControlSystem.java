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
public class AGVControlSystem implements IAGVControlSystem, IPickupItemAssemblyService, IMoveToAssemblyService, IMoveToWarehouseService, IPutDownItemService, IPickUpWarehouseService{


    // Disse 2 pick up og put down metoder er lavet med tanken at samle noget op og sætte det ned er det samme ligemeget hvor agv'en er
    // ellers lav pick up og put down til både warehouse og assembly station.


    @Override
    public void pickupWarehouse() {
        loadProgram("PickWarehouseOperation","1");
        chooseState("2");
        System.out.println("The items are being picked up at the warehouse");
    }

    @Override
    public void moveToAssembly() {
        continueNextOperation();
        loadProgram("MoveToAssemblyOperation","1");
        chooseState("2");
        System.out.println("drivetoass");
    }

    @Override
    public void putItemAtAssembly() {
        continueNextOperation();
        loadProgram("PutAssemblyOperation","1");
        chooseState("2");
        System.out.println("Putting item at assembly");
    }

    @Override
    public void pickupItemAssembly() {

        checkState();
        loadProgram("PickAssemblyOperation","1");
        System.out.println("hihihi");
        chooseState("2");
        System.out.println("pickitemassembly");
    }

    @Override
    public void moveToWarehouse(){

        loadProgram("MoveToStorageOperation","1");
        loadProgram("MoveToStorageOperation","2");
        System.out.println("drivetowarehouse");

    }



    @Override
    public void putItemAtWarehouse() {
        loadProgram("PutWarehouseOperation","1");
        loadProgram("PutWarehouseOperation","2");
        System.out.println("putinwarehouse");
    }


    /*
    public boolean continueToNextOperation(){
        while(checkState() == 2){
            try{
                Thread.sleep(100);
                System.out.println("hello");
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
        return false;
    }*/

    // This method runs until the state is at 1.
    public void continueNextOperation(){
        while(checkState() == 2){
            try {
                Thread.sleep(100);
                System.out.println("hello");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public int checkState(){
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject("http://localhost:8082/v1/status", String.class, "42", "21");


        JSONObject jsonObject = new JSONObject(result);
        int state = jsonObject.getInt("state");
        return state;
    }

    public void chooseState(String state){
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
                String.class,""));
    }





    @Override
    public void loadProgram(String program, String state) {
        // Put Request
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

        HttpEntity<String> httpEntity = new HttpEntity<>("{\"Program name\":\"" + program + "\",\"State\":" + state + "}", headers);
        HttpEntity<String> httpEntity1 = new HttpEntity<>("{\"State\":" + state + "}", headers);
        //"{\"Program name\":\"MoveToStorageOperation\",\"Sta
        restTemplate.put("http://localhost:8082/v1/status", httpEntity);



        System.out.println(restTemplate.getForObject(
                "http://localhost:8082/v1/status",
                String.class,""));

        /*
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> httpEntity = new HttpEntity<>("{\"Program name\":\"" + program + "}", headers);
        HttpEntity<String> httpEntity2 = new HttpEntity<>("{\"State\":\"" + state + "}", headers);
        restTemplate.put("http://localhost:8082/v1/status", httpEntity);
        restTemplate.put("http://localhost:8082/v1/status", httpEntity2);


        System.out.println(restTemplate.getForObject(
                "http://localhost:8082/v1/status",
                String.class,""));

        System.out.println("program loaded");
        // HttpEntity<String> httpEntity = new HttpEntity<>("{\"Program name\":\"MoveToAssemblyOperation\",\"State\":1}", headers);
        //HttpEntity<String> httpEntity = new HttpEntity<>("{\"State\":2}", headers);

         */
    }

    /*
    //test PUT request
    public async void PutOperation()
    {
        //build json content string
        var msg = new OperationMessage();
        msg.State = 1;
        msg.Programname = "MoveToAssemblyOperation";

        //new request obj
        RestRequest putRequest = request;
        putRequest.AddJsonBody(msg);//add body
        //putRequest.RequestFormat = DataFormat.Json;//define format
        //putRequest.Method = Method.Put;

        //PUT request
        //var response = await client.PutAsync(putRequest);
        //Console.WriteLine("PUT request response" + response.Content);
    }
*/

    @Override
    public String getStatus() {
        return null;
    }

    @Override
    public void batteryCheck() {
    }

}
