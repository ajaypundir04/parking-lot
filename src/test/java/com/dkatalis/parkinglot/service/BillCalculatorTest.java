package com.dkatalis.parkinglot.service;

import com.dkatalis.parkinglot.service.BillCalculator;
import com.dkatalis.parkinglot.service.impl.BillCalculatorImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BillCalculatorTest {

    private BillCalculator billCalculator;

    @Before
    public void setUp() {
        billCalculator = new BillCalculatorImpl();
    }

    @Test
    public void calculateBillTest() {
        int bill = 0;
        bill = billCalculator.calculateBill(4);
        Assert.assertEquals(30, bill);
        bill = billCalculator.calculateBill(4.5f);
        Assert.assertEquals(40, bill);
    }
}
