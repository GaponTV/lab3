package sample.Model;

import sample.Controller;

import java.util.concurrent.TimeUnit;

public class Wheel extends Detail {
    private int condition;
    public Wheel(int massWheel){
        super(massWheel);
        condition = 100;
    }
    public void rotate(int fuelBuffer){
        try {
            condition -= 1;
            Thread.sleep(1000*fuelBuffer);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            //e.printStackTrace();
        }
    }

    public void setCondition(int condition) {
        this.condition = condition;
    }
    public int getCondition(){ return condition; }
}
