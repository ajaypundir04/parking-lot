package com.dkatalis.parkinglot.util;

import com.dkatalis.parkinglot.constant.ApplicationConstants;
import com.dkatalis.parkinglot.exception.ParkingException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Used to handle the file level operation.
 *
 * @author Ajay Singh Pundir
 */
public class InputFileReader {

    private InputFileReader() {
    }

    /**
     * Read the file and generates the list of string as output.
     *
     * @param filePath location of the file.
     * @param type     extension type of file
     * @return all the strings passed in the file
     * @throws FileNotFoundException if file path is invalid
     */
    public static List<String> parseFile(String filePath, String type) throws FileNotFoundException {
        List<String> commandList = null;
        switch (type) {
            case ApplicationConstants.TXT_EXTENSION:
                commandList = readTextFile(filePath);
                break;

            default:
                throw new ParkingException("Wrong Extension");
        }
        return commandList;
    }

    private static List<String> readTextFile(String filePath) throws FileNotFoundException {
        List<String> commandList = new ArrayList<>();
        File file = new File(filePath);
        Scanner sc = new Scanner(file);

        while (sc.hasNextLine())
            commandList.add(sc.nextLine());
        return commandList;
    }
}
