package sample.Model;

public class Tractor extends Car {
    private boolean snowMachines;
    public Tractor(String name) {
        super(name);
        this.snowMachines = false;
    }

    public void removeSnow(){
        snowMachines = !snowMachines;
    }
    public boolean getSnowMachines(){ return snowMachines; }
}
