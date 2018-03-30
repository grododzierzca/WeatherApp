package UI;

import App.City;
import App.ForecastLoader;
import App.OpenWeather;
import javafx.application.Application;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;


import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static org.controlsfx.control.textfield.TextFields.bindAutoCompletion;

public class UI extends Application implements Initializable {

    private String cityName = "";
    public TextField searchField, tempField, humidityField, pressureField, maxTempField, minTempField;
    private static OpenWeather forecast;

    public static void main(String [] args){
        launch(args);
    }

    public void buttonSearch(){
        cityName = searchField.getText();
        System.out.println(cityName);
        forecast = ForecastLoader.fetchForecast(cityName);
        tempField.setText(String.valueOf(forecast.getTemperature())+"*C");
        humidityField.setText(String.valueOf(forecast.getHumidity())+"%");
        pressureField.setText(String.valueOf(forecast.getPressure())+"hPa");
        maxTempField.setText(String.valueOf(forecast.getTemp_max())+"*C");
        minTempField.setText(String.valueOf(forecast.getTemp_min())+"*C");
    }

    public void updateText(){
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("UI.fxml"));
        Scene scene = new Scene(root, 600, 300);
        primaryStage.setTitle("WeatherApp");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CityLoader loader = new CityLoader();
        loader.start();
        ArrayList<String> cityNames = new ArrayList<>();
        for(City cit: forecast.getCityList()){ ///CZY MA SENS?
            cityNames.add(cit.getName()+", "+cit.getCountry());
        }
        System.out.println(cityNames);
        TextFields.bindAutoCompletion(searchField, cityNames);
    }


    private static class CityLoader implements Runnable{
        private Thread thread;
        public void start(){
            if(thread == null){
                thread = new Thread(this);
                thread.run();
            }
        }
        public void run() {
            forecast.loadCities();
        }
    }

}
