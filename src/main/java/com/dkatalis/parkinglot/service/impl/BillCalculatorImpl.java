package com.dkatalis.parkinglot.service.impl;

import com.dkatalis.parkinglot.constant.ApplicationConstants;
import com.dkatalis.parkinglot.service.BillCalculator;

/**
 * @author Ajay Singh Pundir
 * Implementation for Billalculator used for parking charge calculations.
 */
public class BillCalculatorImpl implements BillCalculator {

    /**
     * This method calculates the bill charged to the vehicle.
     *
     * @param timeInHours we need to pass the parking time.
     * @return it will return the charges occurred.
     */
    @Override
    public int calculateBill(float timeInHours) {
        int billAmount = ApplicationConstants.BASIC_CHARGE;
        timeInHours -= ApplicationConstants.MINIMUM_PARKING_TIME_CHARGEABLE;
        while (timeInHours > 0) {
            timeInHours--;
            billAmount += ApplicationConstants.BASIC_CHARGE;
        }
        return billAmount;
    }
}
