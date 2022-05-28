package com.project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WarehouseControlTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void testreadyItem() {
        WarehouseControl warehouseControl = new WarehouseControl();
        warehouseControl.readyItem();
        System.out.println("It readies item");
    }

    @Test
    void testinsertItemInWarehouse() {
        WarehouseControl warehouseControl = new WarehouseControl();
        warehouseControl.insertItemInWarehouse();
        System.out.println(" it inserts item");
    }

    @Test
    void testgetInventory() {
        try {
            WarehouseControl warehouseControl = new WarehouseControl();
            String inventory = warehouseControl.getInventory();
            System.out.println("The inventory is: " + "\n" + inventory);
            System.out.println("\nTEST SUCCESFUL");
        }catch(Exception e){
            System.out.println("\nTEST FAILED");
        }
    }


    @Test
    void testgetState() {
        WarehouseControl warehouseControl = new WarehouseControl();
        String state = warehouseControl.getState();
        System.out.println(" it gets the state: " + state);
    }

    @Test
    void check() {
        WarehouseControl warehouseControl = new WarehouseControl();
        String check = warehouseControl.check();
        System.out.println("it gets check" + check);
    }
}