package by.alex.kasperovich.entity;

public class Address {
    private long id;
    private String country;
    private String city;
    private String street;
    private int house;
    private int apartment;
    private int personIdFk;

    public Address() {
    }

    public Address(String country, String city, String street, int house, int apartment) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.house = house;
        this.apartment = apartment;
    }

    public Address(long id, String country, String city, String street, int house, int apartment, int personIdFk) {
        this.id = id;
        this.country = country;
        this.city = city;
        this.street = street;
        this.house = house;
        this.apartment = apartment;
        this.personIdFk = personIdFk;
    }

    public Address(String country, String city, String street, int house, int apartment, int personIdFk) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.house = house;
        this.apartment = apartment;
        this.personIdFk = personIdFk;
    }

    public Address(long id, String country, String city, String street, int house, int apartment) {
        this.id = id;
        this.country = country;
        this.city = city;
        this.street = street;
        this.house = house;
        this.apartment = apartment;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHouse() {
        return house;
    }

    public void setHouse(int house) {
        this.house = house;
    }

    public int getApartment() {
        return apartment;
    }

    public void setApartment(int apartment) {
        this.apartment = apartment;
    }

    public int getPersonIdFk() {
        return personIdFk;
    }

    public void setPersonIdFk(int personIdFk) {
        this.personIdFk = personIdFk;
    }
}
