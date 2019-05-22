package object_instanceof;

import parameter_polymorphism.Bus;
import parameter_polymorphism.Vehicle;

public class Driver {
    public void drive(Vehicle vehicle) {
        if(vehicle instanceof Bus) {
            Bus bus = (Bus) vehicle;
            bus.run();
        }
        vehicle.run();
    }
}