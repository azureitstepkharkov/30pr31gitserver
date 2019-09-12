
package code;

public class Addres {

    @Override
    public String toString() {
        return "INDEX " +INDEX+ " " + City + ", " + TypeStreet + ", " + Street;
    }

    public Addres(String INDEX,  String City,String TypeStreet, String Street) {
        this.TypeStreet = TypeStreet;
        this.Street = Street;
        this.INDEX = INDEX;
        this.City = City;
    }
    public String Street;        public String TypeStreet;
    public String INDEX;   
    public String City;

}
