package com.dkatalis.parkinglot.integration;

import com.dkatalis.parkinglot.ApplicationLauncher;
import com.dkatalis.parkinglot.exception.ParkingException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ApplicationLauncherTest {

    private ByteArrayOutputStream outContent;
    @Before
    public void setUp(){
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(outContent));
    }
    @Test
    public void  launcherTest(){
        ApplicationLauncher.main(new String[]{"src/test/resources/file_inputs_test.txt"});
        Assert.assertEquals("Created parking lot with 6 slots\n",outContent.toString());
    }

    @Test(expected = ParkingException.class)
    public void launcherFakeFilePathTest(){
        ApplicationLauncher.main(new String[]{"src/test/fake_path/file_inputs_test.txt"});

    }
    @Test
    public void launcherFilePathNotProvidedTest(){
        ApplicationLauncher.main(null);
        Assert.assertEquals("No file path provided.\n",outContent.toString());


    }
}
