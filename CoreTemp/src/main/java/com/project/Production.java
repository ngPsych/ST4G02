package com.project;

import java.util.Map;

public class Production {

    static int prompt;
    static int tempPrompt;
    int i = WarehouseConnect.trayId;


    public void startProduction() {
        prompt = tempPrompt;

        if(prompt == 0) {
            for (Map.Entry<String, IMoveService> iMoveToWarehouseServiceEntry : SpringApp.getApplicationContext().getBeansOfType(IMoveService.class).entrySet()) {
                iMoveToWarehouseServiceEntry.getValue().moveToWarehouse();
            }
        }

        System.out.println(WarehouseConnect.trayId + "SJDJASJDA");
        while (WarehouseConnect.trayId <= 9 && prompt <= 10) {
            // Start the connection to the MQTT immediately

        if(prompt == 0) {
            for (Map.Entry<String, IWarehousePrint> iGetInventory : SpringApp.getApplicationContext().getBeansOfType(IWarehousePrint.class).entrySet()) {
                iGetInventory.getValue().getInventory();
                prompt = 1;
            }
        }


            if (prompt == 1) {

                for (Map.Entry<String, IItemService> iReadyItemServiceEntry : SpringApp.getApplicationContext().getBeansOfType(IItemService.class).entrySet()) {
                    iReadyItemServiceEntry.getValue().readyItem();
                    prompt++;
                }
            }

            if (prompt == 2) {
                for (Map.Entry<String, IMoveService> iPickupWarehouseServiceEntry : SpringApp.getApplicationContext().getBeansOfType(IMoveService.class).entrySet()) {
                    iPickupWarehouseServiceEntry.getValue().pickupWarehouse();

                }
                prompt++;
            }

            if (prompt == 3) {

                for (Map.Entry<String, IMoveService> iConnectEntry : SpringApp.getApplicationContext().getBeansOfType(IMoveService.class).entrySet()) {
                    iConnectEntry.getValue().moveToAssembly();

                }
                prompt++;

            }

            if (prompt == 4) {

                for (Map.Entry<String, IMoveService> iConnectEntry : SpringApp.getApplicationContext().getBeansOfType(IMoveService.class).entrySet()) {
                    iConnectEntry.getValue().putItemAtAssembly();

                }
                prompt++;

            }

            if (prompt == 5) {

                for (Map.Entry<String, iAssemblyItemService> iConnectEntry : SpringApp.getApplicationContext().getBeansOfType(iAssemblyItemService.class).entrySet()) {
                    iConnectEntry.getValue().assembleItem();

                }
                prompt++;

            }

            if (prompt == 6) {

                for (Map.Entry<String, IMoveService> iPickupItemServiceEntry : SpringApp.getApplicationContext().getBeansOfType(IMoveService.class).entrySet()) {
                    iPickupItemServiceEntry.getValue().pickupItemAssembly();

                }
                prompt++;

            }

            if (prompt == 7) {

                for (Map.Entry<String, IMoveService> iConnectEntry : SpringApp.getApplicationContext().getBeansOfType(IMoveService.class).entrySet()) {
                    iConnectEntry.getValue().moveToWarehouse();

                }
                prompt++;

            }


            if (prompt == 8) {

                for (Map.Entry<String, IMoveService> iConnectEntry : SpringApp.getApplicationContext().getBeansOfType(IMoveService.class).entrySet()) {
                    iConnectEntry.getValue().putItemAtWarehouse();

                }
                prompt++;

            }

            if (prompt == 9) {

                for (Map.Entry<String, IItemService> iConnectEntry : SpringApp.getApplicationContext().getBeansOfType(IItemService.class).entrySet()) {
                    iConnectEntry.getValue().insertItemInWarehouse();

                }
                prompt++;


            }
            if(prompt == 10) {
                prompt = 0;
            }
        }
        System.out.println("The warehouse is now filled.");

    }

    public void pauseProduction(){
        tempPrompt = prompt;
        prompt = 20;
    }


    //:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::


    public String prodInventory() {
        String inventory = "";

        for (Map.Entry<String, IWarehousePrint> iGetInventory : SpringApp.getApplicationContext().getBeansOfType(IWarehousePrint.class).entrySet()) {
            inventory = iGetInventory.getValue().getInventory();

        }

        return inventory;
    }

    public String stateSetter() {
        String state = "";

        for (Map.Entry<String, IWarehousePrint> iGetState : SpringApp.getApplicationContext().getBeansOfType(IWarehousePrint.class).entrySet()) {
            state = iGetState.getValue().getState();

        }
        return state;
    }

    public String agvConnectionCheck() {
        String connect = "";
        for (Map.Entry<String, IAGVControlSystem> iagvConnectionCheckerEntry : SpringApp.getApplicationContext().getBeansOfType(IAGVControlSystem.class).entrySet()) {
            connect = iagvConnectionCheckerEntry.getValue().check();

        }
        return connect;
    }

    public String warehouseConnectionCheck() {
        String connect = "";

        for (Map.Entry<String, IWarehousePrint> iWarehouseConnectionCheckerEntry : SpringApp.getApplicationContext().getBeansOfType(IWarehousePrint.class).entrySet()) {
            connect = iWarehouseConnectionCheckerEntry.getValue().check();

        }

        return connect;
    }


    public String assemblyConnectionCheck() {
        String connect = "";

        for (Map.Entry<String, iAssemblyItemService> iAssemblyItemServiceEntry : SpringApp.getApplicationContext().getBeansOfType(iAssemblyItemService.class).entrySet()) {
            connect = iAssemblyItemServiceEntry.getValue().connect();

        }

        return connect;
    }

    public String agvGetStatus(){
        String status = "";

        for (Map.Entry<String, IAGVControlSystem> iAGVControlEntry : SpringApp.getApplicationContext().getBeansOfType(IAGVControlSystem.class).entrySet()) {
            status = iAGVControlEntry.getValue().getStatus();

        }

        return status;
    }



    public String assemblyLabelMain(){
        String label = "";

        for (Map.Entry<String, IAssemblyPrint> iAssemblyLabelEntry : SpringApp.getApplicationContext().getBeansOfType(IAssemblyPrint.class).entrySet()) {
            label = iAssemblyLabelEntry.getValue().labelPrint();

        }

        return label;
    }

    public String assemblyHealth(){
        String label = "";

        for (Map.Entry<String, IAssemblyPrint> iAssemblyLabelEntry : SpringApp.getApplicationContext().getBeansOfType(IAssemblyPrint.class).entrySet()) {
            label = iAssemblyLabelEntry.getValue().healthPrint();

        }

        return label;
    }



}