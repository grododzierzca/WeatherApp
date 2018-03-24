package App;

import java.util.Scanner;

public class Main {

    public static void main(String [] args){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter city name...");
        OpenWeather forecast = ForecastLoader.fetchForecast(in.next());
        System.out.println(forecast);
    }
}
