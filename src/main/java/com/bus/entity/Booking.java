package com.bus.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the bookings database table.
 * 
 */
@Entity
@Table(name="bookings")
@NamedQuery(name="Booking.findAll", query="SELECT b FROM Booking b")
public class Booking implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String fileName;

	private String filterDate;

	private String fromDestination;

	private int noOfPersons;

	private String time;

	private String toDestination;

	private double totalCalculated;

	private byte tripStatus;
	
	private double price;

	//bi-directional many-to-one association to Busdata
	@ManyToOne
	@JoinColumn(name="BusName")
	private Busdata busdata;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;

	public Booking() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
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

	public int getNoOfPersons() {
		return this.noOfPersons;
	}

	public void setNoOfPersons(int noOfPersons) {
		this.noOfPersons = noOfPersons;
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

	public double getTotalCalculated() {
		return this.totalCalculated;
	}

	public void setTotalCalculated(double totalCalculated) {
		this.totalCalculated = totalCalculated;
	}

	public byte getTripStatus() {
		return this.tripStatus;
	}

	public void setTripStatus(byte tripStatus) {
		this.tripStatus = tripStatus;
	}

	public Busdata getBusdata() {
		return this.busdata;
	}

	public void setBusdata(Busdata busdata) {
		this.busdata = busdata;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}