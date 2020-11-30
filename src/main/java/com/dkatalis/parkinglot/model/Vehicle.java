package com.dkatalis.parkinglot.model;

import com.dkatalis.parkinglot.constant.VehicleSize;

/**
 * @author Ajay Singh Pundir
 *  It is used to capture vehicle level entries
 */
public abstract class Vehicle {

    private String numberPlate;
    private String color;
    private VehicleSize vehicleSize;

    public Vehicle(String numberPlate, String color, VehicleSize vehicleSize) {
        this.numberPlate = numberPlate;
        this.color = color;
        this.vehicleSize = vehicleSize;
    }

    public String getNumberPlate() {
        return numberPlate;
    }

    public void setNumberPlate(String numberPlate) {
        this.numberPlate = numberPlate;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public VehicleSize getVehicleSize() {
        return vehicleSize;
    }

    public void setVehicleSize(VehicleSize vehicleSize) {
        this.vehicleSize = vehicleSize;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "numberPlate='" + numberPlate + '\'' +
                ", color='" + color + '\'' +
                ", vehicleSize=" + vehicleSize +
                '}';
    }
}
