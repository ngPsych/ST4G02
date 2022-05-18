package com.project;

import java.util.Map;

public class Production {

    int prompt = 0;

    public void startProduction() {



        for (Map.Entry<String, IGetInventory> iGetInventory : SpringApp.getApplicationContext().getBeansOfType(IGetInventory.class).entrySet()) {
            iGetInventory.getValue().getInventory();
            prompt = 1;
        }


        if(prompt == 1){

        for (Map.Entry<String, IReadyItemService> iReadyItemServiceEntry : SpringApp.getApplicationContext().getBeansOfType(IReadyItemService.class).entrySet()) {
            iReadyItemServiceEntry.getValue().readyItem();
            prompt++;
        }}

        if(prompt == 2) {
            for (Map.Entry<String, IPickUpWarehouseService> iPickupWarehouseServiceEntry : SpringApp.getApplicationContext().getBeansOfType(IPickUpWarehouseService.class).entrySet()) {
                iPickupWarehouseServiceEntry.getValue().pickupWarehouse();
                prompt++;
            }
        }

        if (prompt == 3) {

            for (Map.Entry<String, IMoveToAssemblyService> iConnectEntry : SpringApp.getApplicationContext().getBeansOfType(IMoveToAssemblyService.class).entrySet()) {
                iConnectEntry.getValue().moveToAssembly();

            }
            prompt++;

        }

        if (prompt == 4) {

            for (Map.Entry<String, IPutDownItemService> iConnectEntry : SpringApp.getApplicationContext().getBeansOfType(IPutDownItemService.class).entrySet()) {
                iConnectEntry.getValue().putItemAtAssembly();

            }
            prompt++;

        }

        if (prompt == 5) {

            for (Map.Entry<String, iAssemblyItemService> iConnectEntry : SpringApp.getApplicationContext().getBeansOfType(iAssemblyItemService.class).entrySet()) {
                iConnectEntry.getValue().assemblyItem();

            }
            prompt++;

        }



        //-------
        if (prompt == 10) {

            for (Map.Entry<String, IPickupItemAssemblyService> iPickupItemServiceEntry : SpringApp.getApplicationContext().getBeansOfType(IPickupItemAssemblyService.class).entrySet()) {
                iPickupItemServiceEntry.getValue().pickupItemAssembly();

            }
            prompt++;

        }

        if (prompt == 6) {

            for (Map.Entry<String, IMoveToWarehouseService> iConnectEntry : SpringApp.getApplicationContext().getBeansOfType(IMoveToWarehouseService.class).entrySet()) {
                iConnectEntry.getValue().moveToWarehouse();

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
