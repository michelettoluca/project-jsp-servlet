package com.projectjspservlet.entity;

import com.projectjspservlet.type.UserRoles;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "vehicles")
public class Vehicle implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "brand")
    private String brand;

    @Column(name = "model")
    private String model;

    @Column(name = "date_of_registration")
    private LocalDate dateOfRegistration;

    @Column(name = "plate_number")
    private String plateNumber;

    @Column(name = "type")
    private String type;

    @OneToMany(mappedBy = "vehicle")
    private List<Reservation> reservations;


    public Vehicle(int id, String brand, String model, LocalDate dateOfRegistration, String plateNumber, String type) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.dateOfRegistration = dateOfRegistration;
        this.plateNumber = plateNumber;
        this.type = type;
    }

    public Vehicle(String brand, String model, LocalDate dateOfRegistration, String plateNumber, String type) {
        this.brand = brand;
        this.model = model;
        this.dateOfRegistration = dateOfRegistration;
        this.plateNumber = plateNumber;
        this.type = type;
    }

    public Vehicle(int id) {
        this.id = id;
    }

    public Vehicle() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public LocalDate getDateOfRegistration() {
        return dateOfRegistration;
    }

    public void setDateOfRegistration(LocalDate dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", dateOfRegistration=" + dateOfRegistration +
                ", plateNumber='" + plateNumber + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}

