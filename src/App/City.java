package App;


public class City {
    private String name;
    private String country;
    private long ID;
    private Coords coords;



    public City(long ID, String name, String country, double lon, double lat) {
        this.name = name;
        this.country = country;
        this.ID = ID;
        this.coords = new Coords(lon, lat);
    }
    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public long getID() {
        return ID;
    }

    public Coords getCoords() {
        return coords;
    }


    private class Coords{

        private double longitude;
        private double latitude;

        public Coords(double longitude, double latitude){
            this.latitude = latitude;
            this.longitude = longitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }
    }
    public String toString(){
        return "City of "+name+", "+country+"\nHas ID: "+ID+"\nCoords are: "+coords.getLongitude()+", "+coords.getLatitude();
    }

}
