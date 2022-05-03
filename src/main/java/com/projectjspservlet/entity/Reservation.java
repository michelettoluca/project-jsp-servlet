
package com.projectjspservlet.entity;

import com.projectjspservlet.type.ReservationStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "reservations")
public class Reservation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Vehicle vehicle;

    @Column(name = "fromDate")
    private Date fromDate;

    @Column(name = "toDate")
    private Date toDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ReservationStatus status;

    public Reservation(int id, User user, Vehicle vehicle, Date fromDate, Date toDate, ReservationStatus status) {
        this.id = id;
        this.user = user;
        this.vehicle = vehicle;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.status = status;
    }

    public Reservation(User user, Vehicle vehicle, Date fromDate, Date toDate, ReservationStatus status) {
        this.user = user;
        this.vehicle = vehicle;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.status = status;
    }

    public Reservation() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }
}
