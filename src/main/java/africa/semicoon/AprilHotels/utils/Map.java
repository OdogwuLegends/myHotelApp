package africa.semicoon.AprilHotels.utils;

import africa.semicoon.AprilHotels.dto.requests.ReservationRequest;
import africa.semicoon.AprilHotels.models.*;

import java.util.Calendar;
import java.util.Date;

import static africa.semicoon.AprilHotels.utils.AppUtils.ONE;

public class Map {

    public static Reservation map(ReservationRequest reservationRequest){

        Customer customer = customerDetails(reservationRequest);

        RoomType roomType = roomDetail(reservationRequest);

        Date checkIn = setDate(reservationRequest.getCheckInDate(), reservationRequest.getCheckInMonth(), reservationRequest.getCheckInYear());
        Date checkOut = setDate(reservationRequest.getCheckOutDate(), reservationRequest.getCheckOutMonth(), reservationRequest.getCheckOutYear());

        Reservation customerReservation = new Reservation();

        customerReservation.setCustomer(customer);
        customerReservation.setRoomType(roomType);
        customerReservation.setCheckIn(checkIn);
        customerReservation.setCheckOut(checkOut);

        return customerReservation;

    }

    private static RoomType roomDetail(ReservationRequest reservationRequest) {
        return reservationRequest.getRoomType();

    }

    private static Customer customerDetails(ReservationRequest reservationRequest) {
        Customer customer = new Customer();
        customer.setFirstName(reservationRequest.getFirstName());
        customer.setLastName(reservationRequest.getLastName());
        customer.setEmail(reservationRequest.getEmail());
        return customer;
    }

    private static Date setDate(String date, String month, String year){
        int monthValue = Integer.parseInt(month);
        int dateValue = Integer.parseInt(date);
        int yearValue = Integer.parseInt(year);

        Calendar newDate = Calendar.getInstance();
        newDate.set(yearValue,monthValue - ONE,dateValue);

        return newDate.getTime();
    }
}
