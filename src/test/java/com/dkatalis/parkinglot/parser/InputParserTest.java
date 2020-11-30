package com.dkatalis.parkinglot.parser;

import com.dkatalis.parkinglot.constant.ApplicationConstants;
import com.dkatalis.parkinglot.constant.ParkingCommand;
import com.dkatalis.parkinglot.model.ParkingOperation;
import com.dkatalis.parkinglot.util.InputFileReader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InputParserTest {

    private InputParser parser;

    @Before
    public void setUp() {
        parser = new InputParserImpl();
    }

    @Test
    public void parseInputTest() throws IOException {
        List<String> inputList = InputFileReader.parseFile("src/test/resources/file_inputs_test.txt",
                ApplicationConstants.TXT_EXTENSION);
        List<String> expectedList = new ArrayList<>();
        expectedList.add("create_parking_lot 6");
        Assert.assertEquals(expectedList, inputList);
        List<ParkingOperation> operationList = parser.parseInput(inputList);
        Assert.assertNotNull(operationList);
        Assert.assertEquals(ParkingCommand.CREATE_PARKING_LOT, operationList.get(0).getCommand());
        Assert.assertEquals("6", operationList.get(0).getOperationString()[1]);

    }
}
