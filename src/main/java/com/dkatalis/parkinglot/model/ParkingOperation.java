package com.dkatalis.parkinglot.model;

import com.dkatalis.parkinglot.constants.ParkingCommand;

import java.util.Arrays;

public class ParkingOperation {

    private ParkingCommand command;
    private String [] operationString;

    public ParkingOperation(ParkingCommand command, String[] operationString) {
        this.command = command;
        this.operationString = operationString;
    }

    public ParkingCommand getCommand() {
        return command;
    }

    public void setCommand(ParkingCommand command) {
        this.command = command;
    }

    public String[] getOperationString() {
        return operationString;
    }

    public void setOperationString(String[] operationString) {
        this.operationString = operationString;
    }

    @Override
    public String toString() {
        return "ParkingOperation{" +
                "command=" + command +
                ", operationString='" + Arrays.toString(operationString) + '\'' +
                '}';
    }
}
