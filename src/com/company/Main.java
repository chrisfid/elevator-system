package com.company;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ElevatorSystem es;

        // refers to the only object of ElevatorSystem
        es = ElevatorSystem.getInstance();
        es.getConnection();

        // Setting a number of floors in a building to 33 + ground floor
        es.setFloors(33);
        ArrayList<Floor> floors = es.getFloors();
        System.out.println("Floors: " + floors);

        // 16 is a maximum number of elevators, therefore catch otherwise
        try {
            ElevatorSystem.setElevatorCars(3);
        } catch (Exception e) {
            e.printStackTrace();
        }

        es.status();

        // Emergency!
        es.openAllDoors();

        // Each elevator has the same number of floors.
        // If it can't divide without remainders,
        // then the last elevator has the remaining upper floors
        es.assignFloorsToElevators();

        es.status();

        // Setting dispatcher's algorithm to FCFS
        es.setScheduler("FCFS");
        Scheduler scheduler = es.getScheduler();

        // It'll nothing because there's no requests at the moment
        System.out.println(scheduler);

        /* Requests
         START
         */
        // Someone presses a hall button to go down from the floor 33
        // in this example:
        // Elevator No. 3 is responsible for the third floor segment (floor: 23-33)
        scheduler.addRequest(new ExternalRequest(Direction.DOWN, 33));

        // There's a request from elevator's No. 3 button to go to floor 2
        scheduler.addRequest(new InternalRequest(3, 2));

        // Print the Queue
        System.out.println(scheduler);

        // Let's fulfill these two requests
        es.step();
        es.step();

        // Someone presses a hall button to go down from the floor 33 (again)
        // in this example:
        // Elevator No. 3 is responsible for the third floor segment (floor: 23-33)
        scheduler.addRequest(new ExternalRequest(Direction.DOWN, 33));

        es.step();

        // Someone presses a hall button to go up from the floor 16
        // in this example:
        // Elevator No. 2 is responsible for the second floor segment (floor: 12-22)
        scheduler.addRequest(new ExternalRequest(Direction.UP, 16));

        es.step();

        // The person in the elevator from the 33. floor is going to 6. floor
        // in this example:
        // Elevator No. 3 serves this segment (floor: 23-33)
        scheduler.addRequest(new InternalRequest(3, 6));

        // The person in the elevator from the 16. floor is going to 0. floor
        scheduler.addRequest(new InternalRequest(2, 0));

        es.step();
        es.step();

        // No more requests
        System.out.println("\n");
        // Printing the Queue
        System.out.println(scheduler);
        // Trying to fulfill requests
        es.step();
        es.step();

        /* Requests
         END
         */

        // Displaying elevators' status
        // Elevator No.1 was not used, therefore it stays at
        // its minimum segment floor (0)
        es.status();

        // Updating Car No. 1
        try {
            es.updateElevatorCar(1, 1, 6, 9);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("Printing the Elevator of ID = 1");
            for (ElevatorCar elevator : ElevatorSystem.getElevatorCars()) {
                if (elevator.getId() == 1) {
                    System.out.println(elevator);
                }
            }
        }
    }
}
