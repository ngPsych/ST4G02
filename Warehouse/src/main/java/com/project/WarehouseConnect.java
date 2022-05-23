package com.project;


import AutogeneratedCode.IEmulatorService;
import AutogeneratedCode.IEmulatorService_Service;
import org.json.JSONObject;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
@ComponentScan
public class WarehouseConnect implements IReadyItemService, IInsertItemWarehouseService, IGetInventory, IGetState, IWarehouseConnectionChecker {

    IEmulatorService_Service service = new IEmulatorService_Service();
    IEmulatorService iEmulatorService = service.getBasicHttpBindingIEmulatorService();
    static int trayId;

    @Override
    public void readyItem() {
        String inventory = iEmulatorService.getInventory();
        // delete the first part of the whole inventory string, to get rid of unnecessary text.
        String tempInventory = inventory.substring(14);

        // Split the tempinventorystring into multiple strings that can be translated to jsonobjects.
        String[] jsonStrings = tempInventory.split("},|}]");
        JSONObject[] jsonObjects = new JSONObject[10];
        for (int i = 0; i < 10; i++) {
            trayId = i + 1;

            // Adding missing } to create json strings.
            jsonStrings[i] += "}";

            // prints out all of the warehouse inventory.
            System.out.println(jsonStrings[i]);
            jsonObjects[i] = new JSONObject(jsonStrings[i]);

            //TODO WE SHOULD RETURN SOME SORT OF VALUE SO THAT THE PRODUCTION LINE STOPS WITH NO INGREDIENTS.
            if (jsonObjects[i].getString("Content").equals("Item " + trayId)) {
                iEmulatorService.pickItem(trayId);
                System.out.println("Warehouse item ready for pick up");
                break;
            } else if (jsonObjects[i].getInt("Id") == 10) {
                System.out.println("No more items in the warehouse");
                trayId = 1000;
                break;
            }
        }
    }

    @Override
    public void insertItemInWarehouse() {
        String droneName = "Drone " + trayId;
        iEmulatorService.insertItem(trayId, droneName);

        System.out.println("Warehouse have inserted the item");
        System.out.println(iEmulatorService.getInventory());
    }

    @Override
    public String getInventory() {
        String inventory = iEmulatorService.getInventory();

        String array[] = inventory.split(",");

        String tray1 = array[0].replace("{\"Inventory\":[", "") + " " + array[1].replace("","");
        String tray2 = array[2].replace("}", "") + " " + array[3].replace("{","");
        String tray3 = array[4].replace("}", "") + " " + array[5].replace("{","");
        String tray4 = array[6].replace("}", "") + " " + array[7].replace("{","");
        String tray5 = array[8].replace("}", "") + " " + array[9].replace("{","");
        String tray6 = array[10].replace("}", "") + " " + array[11].replace("{","");
        String tray7 = array[12].replace("}", "") + " " + array[13].replace("{","");
        String tray8 = array[14].replace("}", "") + " " + array[15].replace("{","");
        String tray9 = array[16].replace("}", "") + " " + array[17].replace("{","");
        String tray10 = array[18].replace("}", "") + " " + array[19].replace("{]","");

        return tray1 + "\n" + tray2 + "\n" +tray3 + "\n" + tray4 + "\n" + tray5 +
                "\n" + tray6 + "\n" + tray7 + "\n" + tray8 + "\n" + tray9 + "\n" + tray10;
    }

    @Override
    public String getState() {


        IEmulatorService iEmulatorService = service.getBasicHttpBindingIEmulatorService();
        String state = iEmulatorService.getInventory();

        if (state.contains("State\":0")) {
            state = "0";
        } else if (state.contains("State\":1")) {
            state = "1";
        } else if (state.contains("State\":2")) {
            state = "2";
        }


        return state;


    }

    @Override
    public String check() {

        IEmulatorService iEmulatorService = service.getBasicHttpBindingIEmulatorService();

        String connection = iEmulatorService.getInventory();

        if (connection.contains("State\":0")) {
            connection = "Connected";
        } else if (connection.contains("State\":1")) {
            connection = "Connected";
        } else if (connection.contains("State\":2")) {
            connection = "Connected";
        }

        return connection;
    }




   /*
    @Override
    public void connect() {

        IEmulatorService_Service service = new IEmulatorService_Service();
        IEmulatorService iEmulatorService = service.getBasicHttpBindingIEmulatorService();

        System.out.println(iEmulatorService.getInventory());

    }*/


}
