package com.dkatalis.parkinglot.util;

import com.dkatalis.parkinglot.constant.ApplicationConstants;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InputFileReaderTest {

    @Test
    public void parseFileTest() throws IOException {
        List<String> inputList = InputFileReader.parseFile("src/test/resources/file_inputs_test.txt",
                ApplicationConstants.TXT_EXTENSION);
        List<String> expectedList = new ArrayList<>();
        expectedList.add("create_parking_lot 6");
        Assert.assertEquals(expectedList, inputList);
    }
}
