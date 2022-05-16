package com.project;


import AutogeneratedCode.IEmulatorService;
import AutogeneratedCode.IEmulatorService_Service;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
@ComponentScan
public class WarehouseConnect implements iReadyItemService, iInsertItemWarehouseService, IGetInventory {

    IEmulatorService_Service service = new IEmulatorService_Service();
    IEmulatorService iEmulatorService = service.getBasicHttpBindingIEmulatorService();

    @Override
    public void readyItem(int trayId) {
        iEmulatorService.pickItem(trayId);
        System.out.println("Warehouse item ready for pick up");

    }

    @Override
    public void insertItemInWarehouse(int trayId, String name) {
        iEmulatorService.insertItem(trayId, name);
        System.out.println("Warehouse have inserted the item");
    }

    @Override
    public void getInventory() {


        System.out.println(iEmulatorService.getInventory());
    }




   /*
    @Override
    public void connect() {

        IEmulatorService_Service service = new IEmulatorService_Service();
        IEmulatorService iEmulatorService = service.getBasicHttpBindingIEmulatorService();

        System.out.println(iEmulatorService.getInventory());

    }*/





}
