package com.bus.helper;

import com.bus.dto.BookingDTO;
import com.bus.entity.Booking;
import com.bus.entity.Busdata;

public class ObjectCreationHelper {

	public static BookingDTO createBookingsDTO(Busdata busdata) {
		BookingDTO bks = new BookingDTO();

		bks.setBusName(busdata.getBusName());
		bks.setFilterDate(busdata.getFilterDate());
		bks.setFromDestination(busdata.getFromDestination());
		bks.setToDestination(busdata.getToDestination());
		bks.setPrice(busdata.getPrice());
		bks.setNoOfPersons(0);
		bks.setTime(busdata.getTime());
		bks.setTotalCalculated(0.0);

		return bks;

	}

	public static BookingDTO createBookingsDTO(Booking busdata) {
		BookingDTO bks = new BookingDTO();
		bks.setId(busdata.getId());

		bks.setBusName(busdata.getBusdata().getBusName().toString());
		bks.setFilterDate(busdata.getFilterDate());
		bks.setFromDestination(busdata.getFromDestination());
		bks.setToDestination(busdata.getToDestination());
		bks.setNoOfPersons(busdata.getNoOfPersons());
		bks.setTime(busdata.getTime());
		bks.setTotalCalculated(busdata.getTotalCalculated());
		if (busdata.isTripStatus() == true) {
			bks.setTripStatus("Active");
		} else {
			bks.setTripStatus("Canceled");
		}

		return bks;
	}

}
