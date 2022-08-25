package com.bus.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the busdata database table.
 * 
 */
@Entity
@NamedQuery(name="Busdata.findAll", query="SELECT b FROM Busdata b")
public class Busdata implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String busName;

	private String filterDate;

	private String fromDestination;

	private double price;

	private String time;

	private String toDestination;

	//bi-directional many-to-one association to Booking
	@OneToMany(mappedBy="busdata")
	private List<Booking> bookings;

	public Busdata() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBusName() {
		return this.busName;
	}

	public void setBusName(String busName) {
		this.busName = busName;
	}

	public String getFilterDate() {
		return this.filterDate;
	}

	public void setFilterDate(String filterDate) {
		this.filterDate = filterDate;
	}

	public String getFromDestination() {
		return this.fromDestination;
	}

	public void setFromDestination(String fromDestination) {
		this.fromDestination = fromDestination;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getTime() {
		return this.time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getToDestination() {
		return this.toDestination;
	}

	public void setToDestination(String toDestination) {
		this.toDestination = toDestination;
	}

	public List<Booking> getBookings() {
		return this.bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}

	public Booking addBooking(Booking booking) {
		getBookings().add(booking);
		booking.setBusdata(this);

		return booking;
	}

	public Booking removeBooking(Booking booking) {
		getBookings().remove(booking);
		booking.setBusdata(null);

		return booking;
	}

}