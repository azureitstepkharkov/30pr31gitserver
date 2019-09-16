
package Model;

public class Adress {
    
    private String city;
    private String streetType;
    private String streetName;
    private String index;

    public Adress(String city, String streetType, String streetName, String index) {
        this.city = city;
        this.streetType = streetType;
        this.streetName = streetName;
        this.index = index;
    }

    @Override
    public String toString() {
        return city + ", " + streetType + " " + streetName + ", " + index;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStreetType(String streetType) {
        this.streetType = streetType;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getCity() {
        return city;
    }

    public String getStreetType() {
        return streetType;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getIndex() {
        return index;
    }
    
}
