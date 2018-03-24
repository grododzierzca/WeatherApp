package App;



import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;


public class ForecastLoader {


    private static String APIKEY = "c530b3d0051b055ce3e8a0e3d562a9e5";
    private static URL forecastURL;


    public static OpenWeather fetchForecast(String cityName){

        final String forecastFormat = "http://api.openweathermap.org/data/2.5/weather?q="+cityName+"&appid="+APIKEY;

        try{
            forecastURL = new URL(forecastFormat);
        }catch(MalformedURLException e){
            e.printStackTrace();
        }
        OpenWeather result = new OpenWeather();
        Object ob;

        try(BufferedReader in = new BufferedReader(new InputStreamReader(forecastURL.openStream()))){
            ob = new JSONParser().parse(in.readLine());
            JSONObject jo = (JSONObject) ob;
            JSONObject main = (JSONObject) jo.get("main");
            result.setTemperature((double)main.get("temp"));
            result.setHumidity((long)main.get("humidity"));
            result.setPressure((long)main.get("pressure"));
            result.setTemp_min((double)main.get("temp_min"));
            result.setTemp_max((double)main.get("temp_max"));
        }catch(FileNotFoundException e){
            System.out.println("City name "+cityName+" is either invalid or absent in the database.");
        }catch(IOException|ParseException e){
            e.printStackTrace();
        }
        result.setCity_name(cityName);
        return result;
    }







}
