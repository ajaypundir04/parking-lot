package com.dkatalis.parkinglot.service;

/**
 * @author Ajay Singh Pundir
 *
 * It will calculate the bill for the parking time.
 */
public interface BillCalculator {

    int calculateBill(float timeInHours);

}
