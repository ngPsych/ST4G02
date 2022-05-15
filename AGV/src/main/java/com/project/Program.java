package com.project;

public enum Program {
    MOVE_TO_CHARGER_OPERATION("MoveToChargerOperation"),
    MOVE_TO_ASSEMBLY_OPERATION("MoveToAssemblyOperation"),
    MOVE_TO_STORAGE_OPERATION("MoveToStorageOperation"),
    PUT_ASSEMBLY_OPERATION("PutAssemblyOperation"),
    PICK_ASSEMBLY_OPERATION("PickAssemblyOperation"),
    PICK_WAREHOUSE_OPERATION("PickWarehouseOperation"),
    PUT_WAREHOUSE_OPERATION("PutWarehouseOperation")
    ;

    private final String text;

    Program(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
