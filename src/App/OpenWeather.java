package App;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class OpenWeather {
    private double temperature;
    private double pressure;
    private double humidity;
    private double temp_min;
    private double temp_max;
    private String city_name;
    private static final String CITYLIST = "city.list.min.json";
    private static ArrayList<City> cityList = new ArrayList<>();


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

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
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

    public static ArrayList<City> getCityList() {
        return cityList;
    }


    public static void loadCities(){
        Object ar;
        try(FileReader in = new FileReader(CITYLIST)){
            ar = new JSONParser().parse(in);
            JSONArray jar = (JSONArray) ar;
            for(int i = 0; i< jar.size(); i++){
                JSONObject job = (JSONObject) jar.get(i);
                JSONObject jcoord = (JSONObject) job.get("coord");
                double lon = 0, lat = 0;
                if(jcoord.get("lon") instanceof Long){
                    lon = Double.valueOf((long)jcoord.get("lon"));
                }else if(jcoord.get("lat") instanceof Long){
                    lat = Double.valueOf((long)jcoord.get("lat"));
                }else{
                    lon = (double) jcoord.get("lon");
                    lat = (double) jcoord.get("lat");
                }
                cityList.add(new City((long)job.get("id"), (String) job.get("name"), (String) job.get("country"), lon, lat));
                System.out.println(i+"/"+jar.size());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String toString(){
        String s = getCity_name()+"\nCurrent temperature: "+getTemperature()+"*C\nCurrent humidity: "+getHumidity()+"%\nCurrent pressure: "+getPressure()+"hPa\nTemperatures may vary from "+getTemp_min()+"*C to "+getTemp_max()+"*C";
        return s;
    }



}
