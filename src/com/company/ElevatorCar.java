package com.company;

import java.util.*;

public class ElevatorCar
{
    private int id;
    private int currentFloor;
    private int targetFloor;
    private ElevatorCarState state = ElevatorCarState.IDLE;
    private Direction direction;
    private Door door;
    public int[] assignedFloors;
    
    public ElevatorCar(int id) 
    {
        setId(id);
        setDoor(new Door(id));
    }
    
    public String toString() {
        return ("____________________\n" +
        "Elevator No. " + this.id +
        "\nCurrent floor: " + this.currentFloor +
        "\nDestination floor: " + this.targetFloor + 
        "\nDirection: " + this.direction +
        "\nAssigned floors: " + Arrays.toString(this.assignedFloors) +
        "\n____________________"
        );
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getId() {
        return this.id;
    }
    
    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }
    
    public int getCurrentFloor() {
        return this.currentFloor;
    }
    
    public void setTargetFloor(int targetFloor) {
        if (targetFloor >= ElevatorSystem.getMinFloor().getLevel() && 
        (targetFloor <= ElevatorSystem.getMaxFloor().getLevel())) {
            this.targetFloor = targetFloor;
        }
    }
    
    public int getTargetFloor() {
        return this.targetFloor;
    }
    
    public void setState(ElevatorCarState state) {
        this.state = state;
    }
    
    public ElevatorCarState getState() {
        return this.state;
    }
    
    public void setDirection(Direction direction) {
        this.direction = direction;
    }
    
    public Direction getDirection() {
        return this.direction;
    }
    
    private void setDoor(Door door) {
        this.door = door;    
    }
    
    public Door getDoor() {
        return this.door;
    }

    private void elevatorBehavior(int targetFloor) {
        System.out.println("Elevator No. " + this.id + " is moving from a floor "
                + this.currentFloor + " to "
                + targetFloor);
        door.close();
        // Moving
        this.state = ElevatorCarState.MOVING;
        this.targetFloor = targetFloor;
        this.currentFloor = targetFloor;
        this.state = ElevatorCarState.STOPPED;
        System.out.println("> We are on the " + this.currentFloor + ". floor.");
        door.open();
        this.state = ElevatorCarState.IDLE;
        // Wait a few seconds
    }
    
    // For ExternalRequest
    public boolean goToTargetFloor(
        Direction directionToGo,
        int targetFloor
    ) {
        this.direction = directionToGo;
        elevatorBehavior(targetFloor);
        return true;
    }
    
    // For InternalRequest
    public boolean goToTargetFloor(
        int targetFloor
    ) {
        if (this.currentFloor < targetFloor) {
            this.direction = Direction.UP;
        } else {
            this.direction = Direction.DOWN;
        }
        elevatorBehavior(targetFloor);
        return true;
    }
}
