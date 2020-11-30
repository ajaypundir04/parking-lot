package com.dkatalis.parkinglot.model;

import java.util.Objects;

public class ParkingSpot implements Comparable<ParkingSpot> {

    private int slotNumber;

    private Vehicle vehicle;

    public ParkingSpot(int slotNumber, Vehicle vehicle) {
        this.slotNumber = slotNumber;
        this.vehicle = vehicle;
    }

    public int getSlotNumber() {
        return slotNumber;
    }

    public void setSlotNumber(int slotNumber) {
        this.slotNumber = slotNumber;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public String toString() {
        return "ParkingSpot{" +
                "slotNumber=" + slotNumber +
                ", vehicle=" + vehicle +
                '}';
    }

    @Override
    public int compareTo(ParkingSpot parkingSpot) {
        return Integer.compare(this.slotNumber, parkingSpot.getSlotNumber());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ParkingSpot)) return false;
        ParkingSpot spot = (ParkingSpot) o;
        return getSlotNumber() == spot.getSlotNumber() &&
                Objects.equals(getVehicle().getNumberPlate(), spot.getVehicle().getNumberPlate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSlotNumber(), getVehicle().getNumberPlate());
    }
}
