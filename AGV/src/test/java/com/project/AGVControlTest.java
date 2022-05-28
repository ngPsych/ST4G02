package com.project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AGVControlTest {
    AGVControl agvControl;
    @BeforeEach
    void setUp() {
       agvControl = new AGVControl();
    }

    @Test
    void pickupWarehouse() {
        try {
            agvControl.pickupWarehouse();
            System.out.println("\nTEST SUCCESFUL");
        }catch(Exception e){
            System.out.println("\nTEST FAILED");
        }
    }

    @Test
    void moveToAssembly() {
        try {
            agvControl.moveToAssembly();
            System.out.println("\nTEST SUCCESFUL");
        }catch(Exception e){
            System.out.println("\nTEST FAILED");
        }

    }

    @Test
    void putItemAtAssembly() {
        try {
            agvControl.putItemAtAssembly();
            System.out.println("\nTEST SUCCESFUL");
        }catch(Exception e){
            System.out.println("\nTEST FAILED");
        }
    }

    @Test
    void pickupItemAssembly() {
        try {
            agvControl.pickupItemAssembly();
            System.out.println("\nTEST SUCCESFUL");
        }catch(Exception e){
            System.out.println("\nTEST FAILED");
        }
    }

    @Test
    void moveToWarehouse() {
        try {
            agvControl.moveToWarehouse();
            System.out.println("\nTEST SUCCESFUL");
        }catch(Exception e){
            System.out.println("\nTEST FAILED");
        }
    }

    @Test
    void moveToChargerOperation() {
        try {
            agvControl.moveToChargerOperation();
            System.out.println("\nTEST SUCCESFUL");
        }catch(Exception e){
            System.out.println("\nTEST FAILED");
        }
    }

    @Test
    void putItemAtWarehouse() {
        try {
            agvControl.putItemAtWarehouse();
            System.out.println("\nTEST SUCCESFUL");
        }catch(Exception e){
            System.out.println("\nTEST FAILED");
        }
    }

    @Test
    void continueNextOperation() {
        try {
            agvControl.continueNextOperation();
            System.out.println("\nTEST SUCCESFUL");
        }catch(Exception e){
            System.out.println("\nTEST FAILED");
        }
    }

    @Test
    void checkState() {
        try {
            int i = agvControl.checkState();
            System.out.println("checkstate is: "+  i);
            System.out.println("\nTEST SUCCESFUL");
        }catch(Exception e){
            System.out.println("\nTEST FAILED");
        }
    }

    @Test
    void check() {
        try {
            String check = agvControl.check();
            System.out.println("the check is :" + check);
            System.out.println("\nTEST SUCCESFUL");
        }catch(Exception e){
            System.out.println("\nTEST FAILED");
        }
    }

    @Test
    void getStatus() {
        try {
            String status = agvControl.getStatus();
            System.out.println("The status: " + status);
            System.out.println("\nTEST SUCCESFUL");
        }catch(Exception e){
            System.out.println("\nTEST FAILED");
        }
    }
}