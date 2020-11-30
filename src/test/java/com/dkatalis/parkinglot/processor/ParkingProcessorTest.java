package com.dkatalis.parkinglot.processor;

import com.dkatalis.parkinglot.constant.ApplicationConstants;
import com.dkatalis.parkinglot.constant.ParkingCommand;
import com.dkatalis.parkinglot.constant.VehicleSize;
import com.dkatalis.parkinglot.exception.ParkingException;
import com.dkatalis.parkinglot.logger.ConsoleLogger;
import com.dkatalis.parkinglot.model.Car;
import com.dkatalis.parkinglot.model.ParkingOperation;
import com.dkatalis.parkinglot.model.ParkingSpot;
import com.dkatalis.parkinglot.parser.InputParserImpl;
import com.dkatalis.parkinglot.service.ParkingService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ParkingProcessorTest {

    List<ParkingOperation> parkingOperationList;
    List<ParkingOperation> operationList;
    private ParkingProcessor parkingProcessor;
    private ByteArrayOutputStream outContent;

    @Before
    public void setUp() {
        ParkingService mockedParkingService = Mockito.mock(ParkingService.class);
        parkingProcessor = new ParkingProcessor(new InputParserImpl(), mockedParkingService, new ConsoleLogger());
        operationList = new ArrayList<>();

        Mockito.when(mockedParkingService.
                unParkVehicle(ArgumentMatchers.anyString(), ArgumentMatchers.anyFloat())).thenReturn(30);
        Mockito.when(mockedParkingService.
                getSlotNumber(ArgumentMatchers.anyString())).thenReturn(1);
        Mockito.doNothing().when(mockedParkingService).
                createParkingLot(ArgumentMatchers.anyInt());

        Set<ParkingSpot> parkingSpotSet = new HashSet<>();
        parkingSpotSet.add(new ParkingSpot(1,
                new Car("1234", "Blue", VehicleSize.MEDIUM)));
        Mockito.when(mockedParkingService.parkingLotStatus()).thenReturn(parkingSpotSet);
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

    }

    @Test(expected = ParkingException.class)
    public void performCreateParkingOperationTest() {
        parkingOperationList = new ArrayList<>();
        parkingOperationList.add(new ParkingOperation(ParkingCommand.CREATE_PARKING_LOT,
                new String[]{"create_parking_lot", "abc"}));
        parkingProcessor.performOperation(parkingOperationList);

    }

    @Test
    public void performParkingOperationTest() {
        parkingOperationList = new ArrayList<>();
        parkingOperationList.add(0, new ParkingOperation(ParkingCommand.PARK,
                new String[]{"park", "1234"}));
        parkingProcessor.performOperation(parkingOperationList);
        Assert.assertEquals("Allocated slot number: 1\n",
                outContent.toString());

    }

    @Test
    public void performUnParkingOperationTest() {
        parkingOperationList = new ArrayList<>();
        parkingOperationList.add(0, new ParkingOperation(ParkingCommand.LEAVE,
                new String[]{"leave", "1234", "4"}));
        parkingProcessor.performOperation(parkingOperationList);
        Assert.assertEquals("Registration number 1234 with Slot Number 2 is free with Charge 30\n",
                outContent.toString());

    }

    @Test
    public void performParkingLotStatusOperationTest() {
        parkingOperationList = new ArrayList<>();
        parkingOperationList.add(0, new ParkingOperation(ParkingCommand.STATUS,
                null));
        parkingProcessor.performOperation(parkingOperationList);
        Assert.assertEquals("Slot No. Registration No.\n2    1234\n", outContent.toString());
    }

    @Test
    public void parseInputTest() throws IOException {
        List<ParkingOperation> operationList = parkingProcessor.
                parseInputFile("src/test/resources/file_inputs_test.txt",
                        ApplicationConstants.TXT_EXTENSION);
        Assert.assertEquals(ParkingCommand.CREATE_PARKING_LOT, operationList.get(0).getCommand());
        Assert.assertEquals("6", operationList.get(0).getOperationString()[1]);

    }
}
