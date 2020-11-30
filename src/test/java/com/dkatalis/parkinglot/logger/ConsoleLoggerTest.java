package com.dkatalis.parkinglot.logger;

import com.dkatalis.parkinglot.constant.VehicleSize;
import com.dkatalis.parkinglot.model.Car;
import com.dkatalis.parkinglot.model.ParkingSpot;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Set;

public class ConsoleLoggerTest {

    private OutputLogger logger;
    private ByteArrayOutputStream outContent;

    @Before
    public void setUp(){
        logger = new ConsoleLogger();
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void printParkingLotCreationTest(){
        logger.printParkingLotCreation(6);
        Assert.assertEquals("Created parking lot with 6 slots\n",outContent.toString());
    }

    @Test
    public void printParkTest(){
        logger.printParkResult(6);
        Assert.assertEquals("Allocated slot number: 7\n",outContent.toString());
    }

    @Test
    public void printUnParkTest(){
        logger.printUnParkResult("1234",2,10);
        Assert.assertEquals("Registration number 1234 with Slot Number 3 is free with Charge 10\n",outContent.toString());
    }

    @Test
    public void parkingLotStatus(){
        Set<ParkingSpot> parkingSpotSet = new HashSet<>();
        parkingSpotSet.add(new ParkingSpot(1, new Car("1234", "Blue", VehicleSize.MEDIUM)));
        logger.printParkingLotStatus(parkingSpotSet);
        Assert.assertEquals("Slot No. Registration No.\n2    1234\n",outContent.toString());
    }
}
