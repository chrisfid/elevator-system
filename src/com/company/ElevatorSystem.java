package com.company;

import java.util.ArrayList;
import java.util.Objects;

public class ElevatorSystem
{
    // private field that refers to the object
    private static ElevatorSystem elevatorSystem;
    private final ArrayList<Floor> floors;
    private Scheduler scheduler;
    private static ArrayList<ElevatorCar> elevators;
    private static Floor minFloor;
    private static Floor maxFloor;
                                              
   private ElevatorSystem() {
      this.floors = new ArrayList<>();
      elevators = new ArrayList<>();
   }

   public static ElevatorSystem getInstance() {
      // it allows us to create only one object
      // access the object as per our need
      if (elevatorSystem == null) {
          elevatorSystem = new ElevatorSystem();
      }
      return elevatorSystem;
   }
   
   public void getConnection() {
       System.out.println("\n### You are now connected to the system. ###\n");
   }
   
   // Set floors (above ground floor)
   public void setFloors(int maxLevel) {
       this.floors.clear();
       for (int i = 0; i < maxLevel + 1; i++) {
           if (i == 0) {
               Floor floor = new Floor(i);
               floor.setSpecialName("Ground floor");
               minFloor = floor;
               floors.add(floor);
           } else if (i == maxLevel) {
               Floor floor = new Floor(i);
               floor.setSpecialName("Highest floor");
               maxFloor = floor;
               floors.add(floor);
           } else {
           floors.add(new Floor(i));
        }
       }
       System.out.println("Number of floors has been set to " + maxFloor.getLevel() + " + a ground floor.");
   }
  
   public ArrayList<Floor> getFloors() {
       return this.floors;
   }
   
   public static void setElevatorCars(int elevatorCount) throws Exception {
       if (elevatorCount > 16) {
           throw new Exception("This system supports a maximum of 16 elevators");
       }
       ElevatorSystem.elevators.clear();
       for (int i = 0; i < elevatorCount; i++) {
           ElevatorSystem.elevators.add(new ElevatorCar(i + 1));
       }
   }
   
   public static ArrayList<ElevatorCar> getElevatorCars() {
       return ElevatorSystem.elevators;
   }
   
   public void setScheduler(String mode) {
       // First-Come, First-Serve
       if (Objects.equals(mode, "FCFS")) {
           this.scheduler = new Scheduler();
       }
   }
   
   public Scheduler getScheduler() {
       return this.scheduler;
   }
   
   public static Floor getMinFloor() {
       return ElevatorSystem.minFloor;
   }
   
   public  static Floor getMaxFloor() {
       return ElevatorSystem.maxFloor;
   }
   
   public void openAllDoors() {
       System.out.println("\nEmergency! Opening all doors...");
       for (ElevatorCar elevator : elevators) {
           elevator.getDoor().open();
       }
   }
   
   // Shows status of all working ElevatorCars
   public void status() {
       System.out.println("\nPrinting elevators' state...");
       for (ElevatorCar elevator : elevators) {
           System.out.println(elevator);
       }
       System.out.println("\n");
   }
   
   // Updates ElevatorCar of given id
   public void updateElevatorCar(
       int id,
       int newId,
       int currentFloor,
       int targetFloor
    ) throws Exception {
        ElevatorCar targetElevator = null;
        for (ElevatorCar elevator : elevators) {
            if (elevator.getId() == id) {
                targetElevator = elevator;
            }
        }
        if (targetElevator == null) {
            throw new Exception("Elevator of id: " + id + " is not found");
        }
        
        targetElevator.setId(newId);
        targetElevator.setCurrentFloor(currentFloor);
        targetElevator.setTargetFloor(targetFloor);
        targetElevator.setState(ElevatorCarState.IDLE);
    }
       
   public void assignFloorsToElevators() {
       System.out.println("\nAssigning floors to elevators...");
       
       // Not counting ground floors
       int floors = this.floors.size() - 1;
       int elevators = ElevatorSystem.elevators.size();
       
       // Floors per elevators should be assigned evenly
       // The last elevator is responsible for the rest upper floors
       int k = Math.floorDiv(floors, elevators);
       int z = floors % elevators;
       int i = 0;
       
       // Each elevator has an array of [min floor, max floor] that it serves
       for (ElevatorCar elevator : ElevatorSystem.elevators) {
           if (i == 0) {
               elevator.assignedFloors = new int[]{0, (i + 1) * k};
           }
           else if (i == ElevatorSystem.elevators.size() - 1) {
               elevator.assignedFloors = new int[]{i * k + 1, (i + 1) * k + z};
            } else {
                elevator.assignedFloors = new int[]{i * k + 1, (i + 1) * k};
            }
            elevator.setCurrentFloor(elevator.assignedFloors[0]);
            i++;
        }
    }
    
    public void step() {
        System.out.println("\nPrinting one step...\n");
        this.scheduler.fulfillRequest();
    }
   
   public void printCurrentRequests() {
       System.out.println(this.scheduler);
   }
}
