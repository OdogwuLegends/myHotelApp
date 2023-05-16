package africa.semicoon.LegendaryHotels.utils;

import africa.semicoon.LegendaryHotels.dto.requests.AdminRequest;
import africa.semicoon.LegendaryHotels.dto.requests.CustomerRequest;
import africa.semicoon.LegendaryHotels.dto.requests.ReservationRequest;
import africa.semicoon.LegendaryHotels.exceptions.InvalidEmailException;
import africa.semicoon.LegendaryHotels.models.*;

import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static africa.semicoon.LegendaryHotels.utils.AppUtils.ONE;

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
    public static Customer map(CustomerRequest customerRequest) throws InvalidEmailException{
        Customer newCustomer = new Customer();
        newCustomer.setFirstName(customerRequest.getFirstName());
        newCustomer.setLastName(customerRequest.getLastName());
        if(!emailIsCorrect(customerRequest.getEmail())){
            throw new InvalidEmailException("Invalid email.");
        }
        newCustomer.setEmail(customerRequest.getEmail());
        newCustomer.setPassword(newCustomer.getPassword());
        return newCustomer;
    }
    public static Admin map(AdminRequest adminRequest){
        Admin newAdmin = new Admin();

        newAdmin.setFirstName(adminRequest.getFirstName());
        newAdmin.setLastName(adminRequest.getLastName());
        newAdmin.setEmail(adminRequest.getEmail());
        newAdmin.setPassword(adminRequest.getPassword());

        return newAdmin;
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

    public static Date setDate(String date, String month, String year){
        int monthValue = Integer.parseInt(month);
        int dateValue = Integer.parseInt(date);
        int yearValue = Integer.parseInt(year);

        Calendar newDate = Calendar.getInstance();
        newDate.set(yearValue,monthValue - ONE,dateValue);

        return newDate.getTime();
    }
    private static boolean emailIsCorrect(String email){
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
