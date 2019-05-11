package sample.Model;


import sample.Controller;

public class Tank extends Detail {
    private static final int BUFFERFUEL = 1;
    private final int volume;
    private int fuel;

    public Tank(int massTank, int vol, int fue){
        volume = vol;
        fuel = fue;
    }
    public int transferFuel(){
        if(fuel>0) {
            fuel -= BUFFERFUEL;
            return BUFFERFUEL;
        }
        else return 0;
    }
    public int getVolume() { return volume; }
    public int getFuel(){
        return fuel;
    }

    public void setFuel(int fuel){
        this.fuel = fuel;
    }

}
