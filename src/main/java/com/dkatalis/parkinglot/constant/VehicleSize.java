package com.dkatalis.parkinglot.constant;

/**
 * @author Ajay Singh Pundir
 *  It will capture the size of the class
 */
public enum VehicleSize {

    SMALL(1),MEDIUM(2),LARGE(3);

    private int space;

    VehicleSize(int space) {
        this.space = space;
    }
}
