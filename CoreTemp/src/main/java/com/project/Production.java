package com.project;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;

public class Production {

    int prompt = 0;

    public void startProduction() {


        for (Map.Entry<String, iReadyItemService> iConnectEntry : SpringApp.getApplicationContext().getBeansOfType(iReadyItemService.class).entrySet()) {
            iConnectEntry.getValue().readyItem();
            prompt = 1;
        }


        if (prompt == 1) {

            for (Map.Entry<String, iPickupItemService> iPickupItemServiceEntry : SpringApp.getApplicationContext().getBeansOfType(iPickupItemService.class).entrySet()) {
                iPickupItemServiceEntry.getValue().pickupItem();

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
                iConnectEntry.getValue().putDownItemService();

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

            for (Map.Entry<String, iPickupItemService> iConnectEntry : SpringApp.getApplicationContext().getBeansOfType(iPickupItemService.class).entrySet()) {
                iConnectEntry.getValue().pickupItem();

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
                iConnectEntry.getValue().putDownItemService();

            }
            prompt++;

        }

        if (prompt == 8) {

            for (Map.Entry<String, iInsertItemWarehouseService> iConnectEntry : SpringApp.getApplicationContext().getBeansOfType(iInsertItemWarehouseService.class).entrySet()) {
                iConnectEntry.getValue().insertItemInWarehouse();

            }

        }

    }

}
