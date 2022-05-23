package com.project;

public interface IMoveService {

    void moveToAssembly();

    void moveToWarehouse();

    void moveToChargerOperation();

    void putItemAtAssembly();

    void putItemAtWarehouse();

    void pickupWarehouse();

    void pickupItemAssembly();

}
