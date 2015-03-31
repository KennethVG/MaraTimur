package be.intec.maravillos.model;

import java.io.Serializable;

/**
 * Created by Kenneth on 17/02/2015.
 */
public class User implements Serializable {

    private int id;
    private String lastName, firstName, city, country, churchName, quote, urlString;

    public User() {
        this(0, null, null, null, null, null);
    }

    public User(int id, String lastName, String firstName, String city, String country, String imgUrl) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.city = city;
        this.country = country;
        this.urlString = imgUrl;
    }

    public String getUrlString() {
        return urlString;
    }

    public void setUrlString(String urlString) {
        this.urlString = urlString;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getChurchName() {
        return churchName;
    }

    public void setChurchName(String churchName) {
        this.churchName = churchName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", churchName='" + churchName + '\'' +
                ", quote='" + quote + '\'' +
                '}';
    }
}
