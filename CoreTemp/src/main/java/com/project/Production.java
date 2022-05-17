package com.project;

import java.util.Map;

public class Production {

    int prompt = 0;

    public void startProduction() {


        for (Map.Entry<String, iReadyItemService> iConnectEntry : SpringApp.getApplicationContext().getBeansOfType(iReadyItemService.class).entrySet()) {
            iConnectEntry.getValue().readyItem(1);
            prompt = 1;
        }


        if (prompt == 1) {

            for (Map.Entry<String, iPickupItemAssemblyService> iPickupItemServiceEntry : SpringApp.getApplicationContext().getBeansOfType(iPickupItemAssemblyService.class).entrySet()) {
                iPickupItemServiceEntry.getValue().pickupItemAssembly();

            }
            prompt++;

        }

        if (prompt == 2) {

            for (Map.Entry<String, iDriveToAssemblyService> iConnectEntry : SpringApp.getApplicationContext().getBeansOfType(iDriveToAssemblyService.class).entrySet()) {
                iConnectEntry.getValue().driveToAssembly();

            }
            prompt++;

        }

        if (prompt == 3) {

            for (Map.Entry<String, iPutDownItemService> iConnectEntry : SpringApp.getApplicationContext().getBeansOfType(iPutDownItemService.class).entrySet()) {
                iConnectEntry.getValue().putItemOnAssembly();

            }
            prompt++;

        }

        if (prompt == 4) {

            for (Map.Entry<String, iAssemblyItemService> iConnectEntry : SpringApp.getApplicationContext().getBeansOfType(iAssemblyItemService.class).entrySet()) {
                iConnectEntry.getValue().assemblyItem();

            }
            prompt++;

        }

        if (prompt == 5) {

            for (Map.Entry<String, iPickupItemAssemblyService> iConnectEntry : SpringApp.getApplicationContext().getBeansOfType(iPickupItemAssemblyService.class).entrySet()) {
                iConnectEntry.getValue().pickupItemAssembly();

            }
            prompt++;

        }

        if (prompt == 6) {

            for (Map.Entry<String, iDriveToWarehouseService> iConnectEntry : SpringApp.getApplicationContext().getBeansOfType(iDriveToWarehouseService.class).entrySet()) {
                iConnectEntry.getValue().driveToWarehouse();

            }
            prompt++;

        }

        if (prompt == 7) {

            for (Map.Entry<String, iPutDownItemService> iConnectEntry : SpringApp.getApplicationContext().getBeansOfType(iPutDownItemService.class).entrySet()) {
                iConnectEntry.getValue().putItemInWarehouse();

            }
            prompt++;

        }

        if (prompt == 8) {

            for (Map.Entry<String, iInsertItemWarehouseService> iConnectEntry : SpringApp.getApplicationContext().getBeansOfType(iInsertItemWarehouseService.class).entrySet()) {
                iConnectEntry.getValue().insertItemInWarehouse(1, "hej");

            }

        }

    }

}
