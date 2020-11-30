package com.dkatalis.parkinglot.constant;

/**
 * @author Ajay Singh Pundir
 * Used to capture the name of all the parking lot operation.
 */
public enum ParkingCommand {

    CREATE_PARKING_LOT("create_parking_lot"), PARK("park"), LEAVE("leave"), STATUS("status");

    private String message;

    ParkingCommand(String message) {
        this.message = message;
    }

    public static ParkingCommand setByMessage(String message) {
        ParkingCommand command = null;
        switch (message) {
            case "create_parking_lot":
                command = CREATE_PARKING_LOT;
                break;
            case "park":
                command = PARK;
                break;
            case "leave":
                command = LEAVE;
                break;
            case "status":
                command = STATUS;
                break;
            default:
                System.out.println("Wrong Command");
        }
        return command;
    }
}
