package com.dkatalis.parkinglot.service;

import com.dkatalis.parkinglot.service.ParkingStrategy;
import com.dkatalis.parkinglot.service.impl.NearestSlotFirstStrategy;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParkingStrategyTest {

    private ParkingStrategy parkingStrategy;

    @Before
    public void setUp(){
        parkingStrategy = new NearestSlotFirstStrategy();
        parkingStrategy.createParkingLot(2);
    }

    @Test
    public void parkTest()
    {
        parkingStrategy.parkVehicle("1234");
        parkingStrategy.parkVehicle("4567");
        parkingStrategy.parkVehicle("7890");
        Assert.assertEquals(0,parkingStrategy.getSlotNumber("1234"));
        Assert.assertEquals(1,parkingStrategy.getSlotNumber("4567"));
        Assert.assertEquals(-1,parkingStrategy.getSlotNumber("7890"));

    }

    @Test
    public void unParkTest()
    {
        parkingStrategy.parkVehicle("1234");
        parkingStrategy.parkVehicle("4567");
        parkingStrategy.unParkVehicle(0);
        parkingStrategy.parkVehicle("7890");
        Assert.assertEquals(-1,parkingStrategy.getSlotNumber("1234"));
        Assert.assertEquals(1,parkingStrategy.getSlotNumber("4567"));
        Assert.assertEquals(0,parkingStrategy.getSlotNumber("7890"));

    }
}
