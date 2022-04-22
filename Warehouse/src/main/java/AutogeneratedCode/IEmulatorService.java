
package AutogeneratedCode;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.3.2
 * Generated source version: 2.2
 * 
 */
@WebService(name = "IEmulatorService", targetNamespace = "http://tempuri.org/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface IEmulatorService {


    /**
     * 
     * @param trayId
     * @return
     *     returns java.lang.String
     */
    @WebMethod(operationName = "PickItem", action = "http://tempuri.org/IEmulatorService/PickItem")
    @WebResult(name = "PickItemResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "PickItem", targetNamespace = "http://tempuri.org/", className = "AutogeneratedCode.PickItem")
    @ResponseWrapper(localName = "PickItemResponse", targetNamespace = "http://tempuri.org/", className = "AutogeneratedCode.PickItemResponse")
    @Action(input = "http://tempuri.org/IEmulatorService/PickItem", output = "http://tempuri.org/IEmulatorService/PickItemResponse")
    public String pickItem(
        @WebParam(name = "trayId", targetNamespace = "http://tempuri.org/")
        int trayId);

    /**
     * 
     * @param trayId
     * @param name
     * @return
     *     returns java.lang.String
     */
    @WebMethod(operationName = "InsertItem", action = "http://tempuri.org/IEmulatorService/InsertItem")
    @WebResult(name = "InsertItemResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "InsertItem", targetNamespace = "http://tempuri.org/", className = "AutogeneratedCode.InsertItem")
    @ResponseWrapper(localName = "InsertItemResponse", targetNamespace = "http://tempuri.org/", className = "AutogeneratedCode.InsertItemResponse")
    @Action(input = "http://tempuri.org/IEmulatorService/InsertItem", output = "http://tempuri.org/IEmulatorService/InsertItemResponse")
    public String insertItem(
        @WebParam(name = "trayId", targetNamespace = "http://tempuri.org/")
        int trayId,
        @WebParam(name = "name", targetNamespace = "http://tempuri.org/")
        String name);

    /**
     * 
     * @return
     *     returns java.lang.String
     */
    @WebMethod(operationName = "GetInventory", action = "http://tempuri.org/IEmulatorService/GetInventory")
    @WebResult(name = "GetInventoryResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "GetInventory", targetNamespace = "http://tempuri.org/", className = "AutogeneratedCode.GetInventory")
    @ResponseWrapper(localName = "GetInventoryResponse", targetNamespace = "http://tempuri.org/", className = "AutogeneratedCode.GetInventoryResponse")
    @Action(input = "http://tempuri.org/IEmulatorService/GetInventory", output = "http://tempuri.org/IEmulatorService/GetInventoryResponse")
    public String getInventory();

}
