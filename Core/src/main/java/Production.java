import interfaces.iConnect;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;
import java.util.Scanner;

public class Production {

    public void startProduction(){

        Scanner scanner = new Scanner(System.in);

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        //scan i package navn efter komponeter
        context.scan("");
        //skal man kalde
        context.refresh();

        for (Map.Entry<String, iReadyItemService> iConnectEntry: context.getBeansOfType(iReadyItemService.class).entrySet()) {
            iConnectEntry.getValue().readyItem();

        }


        if (scanner.next().equals("Warehouse item ready")){

            for (Map.Entry<String, iPickupItemService> iConnectEntry: context.getBeansOfType(iPickupItemService.class).entrySet()) {
                iConnectEntry.getValue().pickupItem();

            }

        }

        if (scanner.next().equals("AGV have picked up item at Warehouse")) {

            for (Map.Entry<String, iDriveToAssemblyService> iConnectEntry: context.getBeansOfType( iDriveToAssemblyService.class).entrySet()) {
                iConnectEntry.getValue().driveToAssembly();

            }

        }

        if (scanner.next().equals("AGV have arrived at Assembly")) {

            for (Map.Entry<String, iPutItemAssemblyService> iConnectEntry: context.getBeansOfType( iPutItemAssemblyService.class).entrySet()) {
                iConnectEntry.getValue().putItemOnAssembly();

            }

        }

        if (scanner.next().equals("Item is placed on Assembly")) {

            for (Map.Entry<String, iAssemblyItemService> iConnectEntry: context.getBeansOfType( iAssemblyItemService.class).entrySet()) {
                iConnectEntry.getValue().assemblyItem();

            }

        }

        if (scanner.next().equals("Item is done at Assembly")) {

            for (Map.Entry<String, iPickupItemAssemblyService> iConnectEntry: context.getBeansOfType( iPickupItemAssemblyService.class).entrySet()) {
                iConnectEntry.getValue().pickupItemAssembly();

            }

        }

        if (scanner.next().equals("AGV have picked up item at Warehous")) {

            for (Map.Entry<String, iPickupItemAssemblyService> iConnectEntry: context.getBeansOfType( iPickupItemAssemblyService.class).entrySet()) {
                iConnectEntry.getValue().pickupItemAssembly();

            }

        }

        if (scanner.next().equals("AGV have picked up item at Warehouse")) {

            for (Map.Entry<String, iDriveToWarehouseService> iConnectEntry: context.getBeansOfType( iDriveToWarehouseService.class).entrySet()) {
                iConnectEntry.getValue().driveToWarehouse();

            }

        }

        if (scanner.next().equals("AGV have arrived at Assembly")) {

            for (Map.Entry<String, iPutItemWarehouseService> iConnectEntry: context.getBeansOfType( iPutItemWarehouseService.class).entrySet()) {
                iConnectEntry.getValue().putItemInWarehouse();

            }

        }

        if (scanner.next().equals("Item is placed in Warehouse")) {

            for (Map.Entry<String, iInsertItemWarehouseService> iConnectEntry: context.getBeansOfType( iInsertItemWarehouseService.class).entrySet()) {
                iConnectEntry.getValue().insertItemInWarehouse();

            }

        }








    }


}
