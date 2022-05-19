package com.project;

import java.util.Map;

public class Production {

    int prompt = 0;

    public String prodInventory(){
        String inventory = "";
        for (Map.Entry<String, IGetInventory> iGetInventory : SpringApp.getApplicationContext().getBeansOfType(IGetInventory.class).entrySet()) {
            inventory = iGetInventory.getValue().getInventory();

        }

        return inventory;
    }

    public String stateSetter(){
        String state ="";

        for (Map.Entry<String, IGetState> iGetState : SpringApp.getApplicationContext().getBeansOfType(IGetState.class).entrySet()) {
            state = iGetState.getValue().getState();

        }
        return state;
    }

    public String agvConnectionCheck(){
        String connect = "";
        for (Map.Entry<String, IAGVConnectionChecker> iagvConnectionCheckerEntry : SpringApp.getApplicationContext().getBeansOfType(IAGVConnectionChecker.class).entrySet()) {
            connect = iagvConnectionCheckerEntry.getValue().check();

        }
        return connect;
    }

    public String warehouseConnectionCheck(){
        String connect = "";

        for (Map.Entry<String, IWarehouseConnectionChecker> iWarehouseConnectionCheckerEntry : SpringApp.getApplicationContext().getBeansOfType(IWarehouseConnectionChecker.class).entrySet()) {
            connect = iWarehouseConnectionCheckerEntry.getValue().check();

        }

        return connect;
    }

    public String assemblyConnectionCheck(){
        String connect = "";

        for (Map.Entry<String, IAssemblyConnectionChecker> iAssemblyConnectionCheckerEntry : SpringApp.getApplicationContext().getBeansOfType(IAssemblyConnectionChecker.class).entrySet()) {
            connect = iAssemblyConnectionCheckerEntry.getValue().check();

        }

        return connect;
    }

    public String assemblyStatusPrint(){
        String status = "";

        for (Map.Entry<String, IAssemblyStatus> iAssemblyStatusEntry : SpringApp.getApplicationContext().getBeansOfType(IAssemblyStatus.class).entrySet()) {
            status = iAssemblyStatusEntry.getValue().print();

        }

        return status;
    }






    //----------------------------------------------------

    public void startProduction() {



        for (Map.Entry<String, IGetInventory> iGetInventory : SpringApp.getApplicationContext().getBeansOfType(IGetInventory.class).entrySet()) {
            iGetInventory.getValue().getInventory();
            prompt = 1;
        }


        for (Map.Entry<String, IReadyItemService> iReadyItemServiceEntry : SpringApp.getApplicationContext().getBeansOfType(IReadyItemService.class).entrySet()) {
            iReadyItemServiceEntry.getValue().readyItem();
            prompt = 1;
        }



        //--------
/*
        for (Map.Entry<String, IPickUpWarehouseService>  iPickupWarehouseServiceEntry : SpringApp.getApplicationContext().getBeansOfType(IPickUpWarehouseService.class).entrySet()) {
            iPickupWarehouseServiceEntry.getValue().pickupWarehouse();
            prompt = 1;
        }*/

        if (prompt == 2) {

            for (Map.Entry<String, IDriveToAssemblyService> iConnectEntry : SpringApp.getApplicationContext().getBeansOfType(IDriveToAssemblyService.class).entrySet()) {
                iConnectEntry.getValue().driveToAssembly();

            }
            prompt++;

        }

        if (prompt == 3) {

            for (Map.Entry<String, IPutDownItemService> iConnectEntry : SpringApp.getApplicationContext().getBeansOfType(IPutDownItemService.class).entrySet()) {
                iConnectEntry.getValue().putItemAtAssembly();

            }
            prompt++;

        }

        if (prompt == 4) {

            for (Map.Entry<String, iAssemblyItemService> iConnectEntry : SpringApp.getApplicationContext().getBeansOfType(iAssemblyItemService.class).entrySet()) {
                iConnectEntry.getValue().assemblyItem();

            }
            prompt++;

        }



        //-------
    /*    if (prompt == 10) {

            for (Map.Entry<String, IPickupItemAssemblyService> iPickupItemServiceEntry : SpringApp.getApplicationContext().getBeansOfType(IPickupItemAssemblyService.class).entrySet()) {
                iPickupItemServiceEntry.getValue().pickupItemAssembly();

            }
            prompt++;

        } */

        if (prompt == 6) {

            for (Map.Entry<String, IDriveToWarehouseService> iConnectEntry : SpringApp.getApplicationContext().getBeansOfType(IDriveToWarehouseService.class).entrySet()) {
                iConnectEntry.getValue().driveToWarehouse();

            }
            prompt++;

        }


        if (prompt == 7) {

            for (Map.Entry<String, IPutDownItemService> iConnectEntry : SpringApp.getApplicationContext().getBeansOfType(IPutDownItemService.class).entrySet()) {
                iConnectEntry.getValue().putItemAtWarehouse();

            }
            prompt++;

        }

        if (prompt == 8) {

            for (Map.Entry<String, IInsertItemWarehouseService> iConnectEntry : SpringApp.getApplicationContext().getBeansOfType(IInsertItemWarehouseService.class).entrySet()) {
                iConnectEntry.getValue().insertItemInWarehouse(1, "hej");

            }

        }

    }



}
