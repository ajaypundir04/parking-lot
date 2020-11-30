package com.dkatalis.parkinglot.parser;

import com.dkatalis.parkinglot.model.ParkingOperation;

import java.util.List;

/**
 * @author Ajay Singh Pundir
 * It will parse the input file
 */
public interface InputParser {

    List<ParkingOperation> parseInput(List<String> inputList);

}
