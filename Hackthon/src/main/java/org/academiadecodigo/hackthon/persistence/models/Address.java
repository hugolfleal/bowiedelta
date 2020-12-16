package org.academiadecodigo.hackthon.persistence.models;


import javax.persistence.*;

@Entity
@Table(name = "address")
public class Address extends AbstractModel{

    private String street;
    private String city;
    private String district;
    private String country;
    private String zipCode;

    @OneToOne(cascade = {CascadeType.ALL}, orphanRemoval = true, mappedBy = "user", fetch = FetchType.EAGER)
    private User user;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
