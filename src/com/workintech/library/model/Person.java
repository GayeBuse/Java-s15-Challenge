package com.workintech.library.model;

import com.workintech.library.enums.Roles;
public abstract class Person {
    private String name;
    private String surname;
    private String email;
    private String address;
    private double idNumber;
    private  String phoneNumber;
    private Roles role;
    public Person(String name, String surname, String email, String address, double idNumber, String phoneNumber) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.address = address;
        this.idNumber = idNumber;
        this.phoneNumber = phoneNumber;
    }

    public Person(String name, String surname, String email, String phoneNumber) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public Person(String name, String surname, String email, String address, String phoneNumber) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public Person(String name, String surname, double idNumber, Roles role) {
        this.name = name;
        this.surname = surname;
        this.idNumber = idNumber;
        this.role = role;
    }

    public Person(String name, String surname, String email, double idNumber) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.idNumber = idNumber;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(double idNumber) {
        this.idNumber = idNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", idNumber='" + idNumber + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
