import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AGVControlSystem implements IAGVControlSystem{




    @Override
    public void loadProgram(String program, String state) {
        // Put Request
        RestTemplate restTemplate = new RestTemplate();

        System.out.println("dadabadba her");
        // opretter object af RestTemplate
        // opretter forbindelse til AGV'en
        System.out.println(restTemplate.getForObject(
                "http://localhost:8082/v1/status",
                String.class,""));

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
