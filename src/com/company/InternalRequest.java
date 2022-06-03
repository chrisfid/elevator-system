package com.company;

public class InternalRequest implements Request
{
    int elevatorId;
    int destinationFloor;
    
    public InternalRequest(int elevatorId, int destinationFloor) {
        setElevatorId(elevatorId);
        setDestinationFloor(destinationFloor);
    }
    
    public String toString() {
        return "Request: to " + this.getDestinationFloor() + ". floor from Elevator No. " + this.getElevatorId();
    }
    
    public void setDestinationFloor(int destinationFloor) {
        this.destinationFloor = destinationFloor;
    }
    
    public int getDestinationFloor() {
        return this.destinationFloor;
    }
    
    public void setElevatorId(int elevatorId) {
        this.elevatorId = elevatorId;
    }
    
    public int getElevatorId() {
        return this.elevatorId;
    }
    
}
