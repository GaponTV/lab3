package sample.Model;

import sample.Controller;

public class Engine extends Detail {

    public Engine(int mass) {
        super(mass);
    }

    public int work(Tank tank, Wheel wheel){
        int fuelBuffer = tank.transferFuel();
        if(fuelBuffer > 0) {
            return fuelBuffer;
        }
        return 0;
    }

    public boolean startEngine(Tank tank, Wheel wheel){
        if (tank.transferFuel() >0) {
            return true;
        }
        return false;
    }
}
