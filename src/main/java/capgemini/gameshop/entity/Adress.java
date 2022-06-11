package capgemini.gameshop.entity;

public class Adress {
    private String country;
    private String address;
    private String state;
    private String city;
    private String zip;

    public Adress(String country, String address, String state, String city, String zip) {
        this.country = country;
        this.address = address;
        this.state = state;
        this.city = city;
        this.zip = zip;
    }

    public Adress() {
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
}
