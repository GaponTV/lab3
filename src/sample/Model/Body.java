package sample.Model;

public class Body extends Detail {
    private final int doorCount;
    private boolean doorCondition;
    public Body(int doorCount, int mass){
        super(mass);
        this.doorCount = doorCount;
    }
    public void openDoor(){
        doorCondition = true;
    }
    public void closeDoor(){
        doorCondition = false;
    }
    public boolean getDoorCondition(){ return doorCondition; }
}
