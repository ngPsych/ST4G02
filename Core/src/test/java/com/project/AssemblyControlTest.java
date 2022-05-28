package com.project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AssemblyControlTest {
    AssemblyControl assemblyControl;

    @BeforeEach
    void setUp() {
        assemblyControl = new AssemblyControl();
    }

    @Test
    void assembleItem() {
        try {
            String ting = assemblyControl.connect();
            assemblyControl.assembleItem();
            System.out.println("\nTEST SUCCESFUL");
        }catch(Exception e){
            System.out.println("\nTEST FAILED");
        }
    }

    @Test
    void connect() {
        try {
            String connect = assemblyControl.connect();
            System.out.println("connect is:" + connect);
            System.out.println("\nTEST SUCCESFUL");
        }catch(Exception e){
            System.out.println("\nTEST FAILED");
        }
    }

    @Test
    void subscription() {
        try {
            String ting = assemblyControl.connect();
            assemblyControl.subscription();
            System.out.println("\nTEST SUCCESFUL");
        }catch(Exception e){
            System.out.println("\nTEST FAILED");
        }
    }



    @Test
    void getHealth() {
        try {
            String ting = assemblyControl.connect();
            assemblyControl.getHealth();
            System.out.println("\nTEST SUCCESFUL");
        }catch(Exception e){
            System.out.println("\nTEST FAILED");
        }
    }
}