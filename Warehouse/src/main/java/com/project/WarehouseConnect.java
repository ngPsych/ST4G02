package com.project;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
@ComponentScan
public class WarehouseConnect implements iReadyItemService, iInsertItemWarehouseService {



    @Override
    public void readyItem() {

        System.out.println("Warehouse item ready for pick up");

    }

    @Override
    public void insertItemInWarehouse() {

        System.out.println("Warehouse have inserted the item");

    }






   /*
    @Override
    public void connect() {

        IEmulatorService_Service service = new IEmulatorService_Service();
        IEmulatorService iEmulatorService = service.getBasicHttpBindingIEmulatorService();

        System.out.println(iEmulatorService.getInventory());

    }*/





}
