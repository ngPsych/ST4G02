import interfaces.iConnect;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.HttpURLConnection;


@Component
public class AGVConnect implements iConnect {

    @Override
    public void connect() {
        System.out.println("bi her");
        // opretter object af RestTemplate
        RestTemplate restTemplate = new RestTemplate();
        // opretter forbindelse til AGV'en
        System.out.println(restTemplate.getForObject(
                "http://localhost:8082/v1/status",
                String.class,""));
    }
}





 /*
        String result =
                restTemplate.getForObject(
                        "http://localhost:8082/v1/status",
                        String.class,"42", "21"
                );

         */

/*
    using Newtonsoft.Json;
        using RestSharp;
        using System;
        using System.Threading;
        using System.Threading.Tasks;

        namespace ST4_ImplementationExamples
        {
public class REST
{
    public REST()
    {
    }

    //init REST
    private RestClient client = new RestClient("http://localhost:8082");
    private RestRequest request = new RestRequest("v1/status/");

    //runner
    public async Task RunExample()
{
    GetStatus();
    PutOperation();
}

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

    //test status method
    public async void GetStatus()
{
    //GET request
    RestResponse response = await client.GetAsync(request);
    Console.WriteLine("GET request response: " + response.Content);
}
}

//class to serialize json objects
public class OperationMessage
{
    //tag forces the name of the json attribute on serialization to the specified PropertyName
        [JsonProperty(PropertyName = "Program name")]
    public string Programname { get; set; }
    public int State { get; set; }
}
}
 */
