package sample.Model;

public class Driver {
    public boolean controlCar(Car car){
        return car.go();
    }
    public void inflate(Car car){
        if(car.getWheel().getCondition() < 100){
            car.getWheel().setCondition(car.getWheel().getCondition() + 1);
        }
    }
    public void fillup(Car car){
        if(car.getTank().getFuel() < car.getTank().getVolume()){
            car.getTank().setFuel(car.getTank().getFuel() + 1);
        }
    }
}
