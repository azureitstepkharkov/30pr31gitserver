/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code;

/**
 *
 * @author merkyr
 */
public class Adress {

    public Adress(String City, String StreetType, String StreetName, String Index) {
        this.City = City;
        this.StreetType = StreetType;
        this.StreetName = StreetName;
        this.Index = Index;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String City) {
        this.City = City;
    }

    public String getStreetType() {
        return StreetType;
    }

    public void setStreetType(String StreetType) {
        this.StreetType = StreetType;
    }

    public String getStreetName() {
        return StreetName;
    }

    public void setStreetName(String StreetName) {
        this.StreetName = StreetName;
    }

    public String getIndex() {
        return Index;
    }

    public void setIndex(String Index) {
        this.Index = Index;
    }

    @Override
    public String toString() {
        return "Adress{" + "City=" + City + ", StreetType=" + StreetType + ", StreetName=" + StreetName + ", Index=" + Index + '}';
    }
    String City;
    String StreetType;
    String StreetName;
    String Index;
}
