package App;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


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

        try(BufferedReader in = new BufferedReader(new InputStreamReader(forecastURL.openStream()))) {
            ob = new JSONParser().parse(in.readLine());
            JSONObject jo = (JSONObject) ob;
            JSONObject main = (JSONObject) jo.get("main");
            result.setTemperature((double) main.get("temp"));
            if(main.get("humidity") instanceof Double) result.setHumidity((double) main.get("humidity"));
            else result.setHumidity(Double.valueOf((long)main.get("humidity")));
            if(main.get("pressure") instanceof Double) result.setPressure((double) main.get("pressure"));
            else result.setPressure(Double.valueOf((long)main.get("pressure")));
            result.setTemp_min((double) main.get("temp_min"));
            result.setTemp_max((double) main.get("temp_max"));
        }catch(FileNotFoundException e) {
            System.out.println("City name " + cityName + " is either invalid or is absent in the database.");
        }catch(ConnectException e){
            System.out.println("Connection error: connection timed out.");
        }catch(IOException|ParseException e){
            e.printStackTrace();
        }
        result.setCity_name(cityName);
        return result;
    }

}
