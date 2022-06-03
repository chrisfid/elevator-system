package com.company;

import java.util.LinkedList;
import java.util.Queue;

public class Scheduler
{
    private Queue<Request> requests;
    
    public Scheduler()
    {
        this.requests = new LinkedList<>();
    }
    
    public String toString() {
        StringBuilder res = new StringBuilder("DUE: ");
        for (Request request : this.requests) res.append("(").append(request.toString()).append(") ");
        return res.toString();
    }
    
    public Queue<Request> getRequests() {
        return this.requests;
    }
    
    public void setRequests(Queue<Request> requests) {
        this.requests = requests;
    }
    
    public void addRequest(Request request) {
        this.requests.add(request);
    }
    
    public boolean fulfillRequest() {
        Request request = this.requests.poll();
        if (request == null) {
            System.out.println("There's no requests to fulfill.");
            return false;
        }
        System.out.println("@@@ Fulfilling the request: (" + request + ")");
        if (request instanceof ExternalRequest er) {
            Direction directionToGo = er.getDirectionToGo();
            int targetFloor = er.getSourceFloor();
            for (ElevatorCar elevator : ElevatorSystem.getElevatorCars()) {
                if (elevator.assignedFloors.length != 2) {
                    return false;
                }
                
                if (targetFloor >= elevator.assignedFloors[0] && targetFloor <= elevator.assignedFloors[1]) {
                    elevator.goToTargetFloor(directionToGo, targetFloor);
                }
            }
            
        } else if (request instanceof InternalRequest ir) {
            int targetFloor = ir.getDestinationFloor();
            int elevatorId = ir.getElevatorId();
            ElevatorCar elevatorTarget = null;
            
            for (ElevatorCar elevator : ElevatorSystem.getElevatorCars()) {
                if (elevator.getId() == elevatorId) {
                    elevatorTarget = elevator;
                }
            }
            
            if (elevatorTarget == null) {
                return false;
            }
            
            if (elevatorTarget.assignedFloors.length != 2) {
                return false;
            }
                
            elevatorTarget.goToTargetFloor(targetFloor);
            return true;
            
        } else {
            System.out.println("Wrong request instance type");
            return false;
        }
        return true;
    }
}
