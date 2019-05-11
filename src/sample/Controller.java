package sample;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import sample.Model.Car;
import sample.Model.Driver;
import sample.Model.Tractor;

import java.io.IOException;
import java.net.MalformedURLException;

public class Controller {
    private Tractor car = new Tractor("Priora");
    private TaskBak tbak;
    private TaskCar tcar;
    private Driver driver = new Driver();
    @FXML
    private Label CarLabel;
    @FXML
    private Label WheelLabel;
    @FXML
    private Label BakLabel;
    @FXML
    private Label DvigatLabel;
    @FXML
    private Label DoorLabel;
    @FXML
    private Button CarButton;
    private Main main;

    public Controller(){
    }

    @FXML
    private void initialize() {

    }

    public void snowRemove(){
        car.removeSnow();
    }
    public void fill(){

        driver.fillup(car);
        BakLabel.textProperty().unbind();
        setBakLabel(car.getTank().getFuel() + " L");
        //BakLabel.textProperty().bind(tbak.messageProperty());
    }
    private void stopCar(ActionEvent actionEvent) {
        tcar.cancel();
        tbak.cancel();
        CarLabel.textProperty().unbind();
        setCarLabel("стоит");
        setDvigatLabel("не работает");
        setWheelLabel(car.getWheel().getCondition() + "%");
        CarButton.setText("Ехать!");
        CarButton.setOnAction(this::startCar);
    }
    public void startCar(ActionEvent actionEvent){
        CarButton.setText("Остановиться");
        CarButton.setOnAction(this::stopCar);
        tbak = new TaskBak();
        tcar = new TaskCar(tbak);
        CarLabel.textProperty().bind(tcar.messageProperty());
        BakLabel.textProperty().bind(tbak.messageProperty());
        tbak.setOnRunning(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent t) {
                setDvigatLabel("работает");
            }
        });
        tbak.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent t) {
                CarLabel.textProperty().unbind();
                setCarLabel("стоит");
                setDvigatLabel("не работает");
                setWheelLabel(car.getWheel().getCondition() + "%");
                CarButton.setOnAction(actionEvent1 -> startCar(actionEvent1));
                CarButton.setText("Ехать!");
            }
        });
        new Thread(tcar).start();
    }



    public void openDoor(){
        car.getBody().openDoor();
        DoorLabel.setText("открыта");
    }
    public void closeDoor(){
        car.getBody().closeDoor();
        DoorLabel.setText("закрыта");
    }
    public void inflate(){
        driver.inflate(car);
        setWheelLabel(car.getWheel().getCondition() + "%");
    }
    public void removeSnow(){
        car.removeSnow();
    }
    public void setMainApp(Main main) {
        this.main = main;
    }

    public void setCarLabel(String text){ CarLabel.setText(text);}
    public void setWheelLabel(String text){ WheelLabel.setText(text);}
    public void setBakLabel(String text){ BakLabel.setText(text);}
    public void setDvigatLabel(String text){ DvigatLabel.setText(text);}
    public Car getCar (){ return car;}

    class TaskCar extends Task<Void> {
        private TaskBak tbak;
        public TaskCar(TaskBak tbak){
            this.tbak = tbak;
        }
        @Override public Void call() {
            if(car.go()){
                if(car.getEngine().startEngine(car.getTank(), car.getWheel())){
                    if(car.getSnowMachines()){
                        updateMessage("убирает снег");
                    }
                    else{
                        updateMessage("едет");
                    }
                    new Thread(tbak).start();
                }
            }
            return null;
        }

    }

    class TaskBak extends Task<Void> {
        @Override public Void call() {
            int fuelBuffer = 0;
            do{
                fuelBuffer = car.getEngine().work(car.getTank(), car.getWheel());
                car.getWheel().rotate(fuelBuffer);
                updateMessage(car.getTank().getFuel() + " L");
            }while (fuelBuffer > 0 && !isCancelled());

            return null;
        }
    }
}





