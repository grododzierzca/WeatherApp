package App;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class OpenWeather {
    private double temperature;
    private long pressure;
    private long humidity;
    private double temp_min;
    private double temp_max;
    private String city_name;


    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public double getTemperature() {
        temperature-=273.15;
        temperature = new BigDecimal(temperature).setScale(2, RoundingMode.HALF_UP).doubleValue();
        return temperature;
    }

    public long getPressure() {
        return pressure;
    }

    public void setPressure(long pressure) {
        this.pressure = pressure;
    }

    public long getHumidity() {
        return humidity;
    }

    public void setHumidity(long humidity) {
        this.humidity = humidity;
    }

    public double getTemp_min() {
        temp_min-=273.15;
        temp_min = new BigDecimal(temp_min).setScale(2, RoundingMode.HALF_UP).doubleValue();
        return temp_min;
    }

    public void setTemp_min(double temp_min) {
        this.temp_min = temp_min;
    }

    public double getTemp_max() {
        temp_max-=273.15;
        temp_max = new BigDecimal(temp_max).setScale(2, RoundingMode.HALF_UP).doubleValue();
        return temp_max;
    }

    public void setTemp_max(double temp_max) {
        this.temp_max = temp_max;
    }

    public void setTemperature(double temp){
        temperature = temp;
    }

    public String toString(){
        String s = getCity_name()+"\nCurrent temperature: "+getTemperature()+"\nCurrent humidity: "+getHumidity()+"\nCurrent pressure: "+getPressure()+"\nTemperatures may vary from "+getTemp_min()+" to "+getTemp_max();
        return s;
    }



}
