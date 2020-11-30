package com.dkatalis.parkinglot.processor;

import com.dkatalis.parkinglot.constant.VehicleSize;
import com.dkatalis.parkinglot.exception.ParkingException;
import com.dkatalis.parkinglot.logger.OutputLogger;
import com.dkatalis.parkinglot.model.Car;
import com.dkatalis.parkinglot.model.ParkingOperation;
import com.dkatalis.parkinglot.parser.InputParser;
import com.dkatalis.parkinglot.service.ParkingService;
import com.dkatalis.parkinglot.util.InputFileReader;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * @author Ajay Singh Pundir
 * This class will handle all the parking operations
 */

public class ParkingProcessor {

    private InputParser parser;
    private ParkingService parkingService;
    private OutputLogger outputLogger;

    public ParkingProcessor(InputParser parser, ParkingService parkingService, OutputLogger outputLogger) {
        this.parser = parser;
        this.parkingService = parkingService;
        this.outputLogger = outputLogger;
    }

    /**
     * It will used to process the parking command
     *
     * @param operationList List of @{@link ParkingOperation} contains
     *                      information about the operation to be performed
     */
    public void performOperation(List<ParkingOperation> operationList) {
        operationList.forEach(parkingOperation -> {
            switch (parkingOperation.getCommand()) {
                case CREATE_PARKING_LOT: {
                    parkingLotCreation(parkingOperation.getOperationString());
                    break;
                }
                case PARK: {
                    parkVehicle(parkingOperation.getOperationString());
                    break;
                }
                case LEAVE: {
                    unParkVehicle(parkingOperation.getOperationString());
                    break;
                }
                case STATUS: {
                    displayStatus();
                    break;
                }
                default:
                    System.out.println("Wrong Operation");
            }
        });
    }

    private void parkingLotCreation(String[] operationString) {
        try {
            int capacity = Integer.parseInt(operationString[1]);
            parkingService.createParkingLot(capacity);
            outputLogger.printParkingLotCreation(capacity);
        } catch (NumberFormatException ne) {
            throw new ParkingException("Invalid capacity Provided", ne);
        }

    }

    private void parkVehicle(String[] operationString) {
        String numberPlate = operationString[1];
        String color = operationString.length > 2 ? operationString[2] : "Blue";
        Car car = new Car(numberPlate, color, VehicleSize.MEDIUM);
        int slot = parkingService.parkVehicle(car);
        outputLogger.printParkResult(slot);
    }

    private void unParkVehicle(String[] operationString) {
        String numberPlate = operationString[1];
        int slotNumber = -1, billAmount = -1;
        slotNumber = parkingService.getSlotNumber(numberPlate);
        float timeInHours = Float.parseFloat(operationString[2]);
        if (slotNumber != -1) {
            billAmount = parkingService.unParkVehicle(numberPlate, timeInHours);
        }
        outputLogger.printUnParkResult(numberPlate, slotNumber, billAmount);
    }

    private void displayStatus() {
        outputLogger.printParkingLotStatus(parkingService.parkingLotStatus());
    }

    public List<ParkingOperation> parseInputFile(String filePath, String type) {
        try {

            return parser.parseInput(InputFileReader.parseFile(filePath, type));

        } catch (FileNotFoundException e) {
            throw new ParkingException("Invalid File Location", e);
        }
    }
}
