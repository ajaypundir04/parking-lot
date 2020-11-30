package com.dkatalis.parkinglot.parser;

import com.dkatalis.parkinglot.constant.ParkingCommand;
import com.dkatalis.parkinglot.model.ParkingOperation;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ajay Singh Pundir
 * Used for parsing the input strings.
 */
public class InputParserImpl implements InputParser {
    /**
     * Used to parse the input text and convert to parking operation.
     *
     * @param inputList List of input text
     * @return List of @{@link ParkingOperation} needs to be performed
     */
    @Override
    public List<ParkingOperation> parseInput(List<String> inputList) {

        return inputList.stream()
                .map(inputLine -> {
                    String[] input = inputLine.split(" ");
                    return new ParkingOperation(ParkingCommand.setByMessage(input[0]), input);
                }).collect(Collectors.toList());
    }
}
