package com.dkatalis.parkinglot;

import com.dkatalis.parkinglot.constant.ApplicationConstants;
import com.dkatalis.parkinglot.exception.ParkingException;
import com.dkatalis.parkinglot.logger.ConsoleLogger;
import com.dkatalis.parkinglot.logger.OutputLogger;
import com.dkatalis.parkinglot.model.ParkingOperation;
import com.dkatalis.parkinglot.parser.InputParser;
import com.dkatalis.parkinglot.parser.InputParserImpl;
import com.dkatalis.parkinglot.processor.ParkingProcessor;
import com.dkatalis.parkinglot.service.BillCalculator;
import com.dkatalis.parkinglot.service.ParkingManager;
import com.dkatalis.parkinglot.service.ParkingService;
import com.dkatalis.parkinglot.service.ParkingStrategy;
import com.dkatalis.parkinglot.service.impl.BillCalculatorImpl;
import com.dkatalis.parkinglot.service.impl.NearestSlotFirstStrategy;
import com.dkatalis.parkinglot.service.impl.ParkingManagerImpl;
import com.dkatalis.parkinglot.service.impl.ParkingServiceImpl;

import java.util.List;
import java.util.Objects;

public class ApplicationLauncher {

    public static void main(String[] args) {
        if (args == null || args.length < 1) {
            System.err.println("No file path provided.");
        }
        else{
            new ApplicationLauncher().startApplication(args[0]);
        }
    }

    private void startApplication(String filePath) {
        ParkingService parkingService = initializeParkingService();
        OutputLogger outputLogger = new ConsoleLogger();
        InputParser parser = new InputParserImpl();
        ParkingProcessor processor = new ParkingProcessor(parser, parkingService, outputLogger);
        List<ParkingOperation> operationList = processor.parseInputFile(filePath, ApplicationConstants.TXT_EXTENSION);
        if (Objects.isNull(operationList)) {
            throw new ParkingException("Failed to Parse Input File");
        } else {
            processor.performOperation(operationList);

        }
    }

    private ParkingServiceImpl initializeParkingService() {
        ParkingStrategy parkingStrategy = new NearestSlotFirstStrategy();
        BillCalculator billCalculator = new BillCalculatorImpl();
        ParkingManager parkingManager = new ParkingManagerImpl(parkingStrategy, billCalculator);
        return new ParkingServiceImpl(parkingManager);
    }

}
