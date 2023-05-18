package africa.semicoon.LegendaryHotels.utils;

import africa.semicoon.LegendaryHotels.dto.requests.RequestsForAdmins;
import africa.semicoon.LegendaryHotels.dto.requests.RequestsForCustomers;
import africa.semicoon.LegendaryHotels.dto.requests.RequestsForReservations;
import africa.semicoon.LegendaryHotels.dto.response.ResponseForAdminRegistration;
import africa.semicoon.LegendaryHotels.dto.response.ResponseForCustomerRegistration;
import africa.semicoon.LegendaryHotels.dto.response.ResponseForReservation;
import africa.semicoon.LegendaryHotels.dto.response.ResponseToFindByEmail;
import africa.semicoon.LegendaryHotels.exceptions.InvalidEmailException;
import africa.semicoon.LegendaryHotels.models.*;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import static africa.semicoon.LegendaryHotels.utils.AppUtils.ONE;
import static africa.semicoon.LegendaryHotels.utils.AppUtils.emailIsCorrect;

public class Map {

    public static Reservation requestToReservation(RequestsForReservations requestsForReservations){

        Customer customer = customerDetails(requestsForReservations);

        RoomType roomType = roomDetail(requestsForReservations);

        Date checkIn = setDate(requestsForReservations.getCheckInDate(), requestsForReservations.getCheckInMonth(), requestsForReservations.getCheckInYear());
        Date checkOut = setDate(requestsForReservations.getCheckOutDate(), requestsForReservations.getCheckOutMonth(), requestsForReservations.getCheckOutYear());

        Reservation customerReservation = new Reservation();

        customerReservation.setCustomer(customer);
        customerReservation.setRoomType(roomType);
        customerReservation.setCheckIn(checkIn);
        customerReservation.setCheckOut(checkOut);

        return customerReservation;

    }
    public static Customer requestToCustomer(RequestsForCustomers requestsForCustomers) {
        Customer newCustomer = new Customer();
        newCustomer.setFirstName(requestsForCustomers.getFirstName());
        newCustomer.setLastName(requestsForCustomers.getLastName());
        if(!emailIsCorrect(requestsForCustomers.getEmail())){
            throw new InvalidEmailException("Invalid email.");
        }
        newCustomer.setEmail(requestsForCustomers.getEmail());
        newCustomer.setPassword(newCustomer.getPassword());
        return newCustomer;
    }
    public static Admin requestToAdmin(RequestsForAdmins requestsForAdmins){
        Admin newAdmin = new Admin();

        newAdmin.setFirstName(requestsForAdmins.getFirstName());
        newAdmin.setLastName(requestsForAdmins.getLastName());
        newAdmin.setEmail(requestsForAdmins.getEmail());
        newAdmin.setPassword(requestsForAdmins.getPassword());

        return newAdmin;
    }

    public static ResponseToFindByEmail customerToEmailResponse(Customer customer){
        ResponseToFindByEmail responseToFindByEmail = new ResponseToFindByEmail();

        responseToFindByEmail.setFirstName(customer.getFirstName());
        responseToFindByEmail.setLastName(customer.getLastName());
        responseToFindByEmail.setEmail(customer.getEmail());

        return responseToFindByEmail;
    }

    public static ResponseForCustomerRegistration customerToRegistrationResponse(Customer customer){
        ResponseForCustomerRegistration responseForCustomerRegistration = new ResponseForCustomerRegistration();
        responseForCustomerRegistration.setFirstName(customer.getFirstName());
        responseForCustomerRegistration.setLastName(customer.getLastName());
        return responseForCustomerRegistration;
    }

    public static ResponseToFindByEmail adminToEmailResponse(Admin admin){
        ResponseToFindByEmail responseToFindByEmail = new ResponseToFindByEmail();

        responseToFindByEmail.setFirstName(admin.getFirstName());
        responseToFindByEmail.setLastName(admin.getLastName());
        responseToFindByEmail.setEmail(admin.getEmail());

        return responseToFindByEmail;
    }

    public static ResponseForAdminRegistration adminToRegistrationResponse(Admin admin){
        ResponseForAdminRegistration responseForAdminRegistration = new ResponseForAdminRegistration();
        responseForAdminRegistration.setFirstName(admin.getFirstName());
        responseForAdminRegistration.setLastName(admin.getLastName());
        responseForAdminRegistration.setAdminLoginCode(admin.getAdminLoginCode());

        return responseForAdminRegistration;
    }
    
   

    private static RoomType roomDetail(RequestsForReservations requestsForReservations) {
        return requestsForReservations.getRoomType();

    }

    private static Customer customerDetails(RequestsForReservations requestsForReservations) {
        Customer customer = new Customer();
        customer.setFirstName(requestsForReservations.getFirstName());
        customer.setLastName(requestsForReservations.getLastName());
        customer.setEmail(requestsForReservations.getEmail());
        return customer;
    }

    public static ResponseForReservation reservationToReservationResponse(Reservation reservation){
        ResponseForReservation responseForReservation = new ResponseForReservation();

        Customer foundCustomer = reservation.getCustomer();
        Room room = reservation.getRoom();
        Date foundCheckIn = reservation.getCheckIn();
        Date foundCheckOut = reservation.getCheckOut();
        RoomType roomType = reservation.getRoomType();

        responseForReservation.setFirstName(foundCustomer.getFirstName());
        responseForReservation.setLastName(foundCustomer.getLastName());
        responseForReservation.setAmount(room.getPrice());
        responseForReservation.setRoomType(roomType);
        responseForReservation.setRoomChoice(room.getRoomNumber());
        responseForReservation.setCheckInDate(foundCheckIn);
        responseForReservation.setCheckOutDate(foundCheckOut);

        return responseForReservation;
    }
    

    public static Date setDate(String date, String month, String year){
        Calendar newDate = Calendar.getInstance();
        int dateValue = Integer.parseInt(date);
        int yearValue = Integer.parseInt(year);

        if (Objects.equals(month, "1") || Objects.equals(month, "01")) {newDate.set(yearValue, Calendar.JANUARY,dateValue);}
        else if (Objects.equals(month, "2") || Objects.equals(month, "02")) {newDate.set(yearValue, Calendar.FEBRUARY,dateValue);}
        else if (Objects.equals(month, "3") || Objects.equals(month, "03")) {newDate.set(yearValue, Calendar.MARCH,dateValue);}
        else if (Objects.equals(month, "4") || Objects.equals(month, "04")) {newDate.set(yearValue, Calendar.APRIL,dateValue);}
        else if (Objects.equals(month, "5") || Objects.equals(month, "05")) {newDate.set(yearValue, Calendar.MAY,dateValue);}
        else if (Objects.equals(month, "6") || Objects.equals(month, "06")) {newDate.set(yearValue, Calendar.JUNE,dateValue);}
        else if (Objects.equals(month, "7") || Objects.equals(month, "07")) {newDate.set(yearValue, Calendar.JULY,dateValue);}
        else if (Objects.equals(month, "8") || Objects.equals(month, "08")) {newDate.set(yearValue, Calendar.AUGUST,dateValue);}
        else if (Objects.equals(month, "9") || Objects.equals(month, "09")) {newDate.set(yearValue, Calendar.SEPTEMBER,dateValue);}
        else if (Objects.equals(month, "10") || Objects.equals(month, "010")) {newDate.set(yearValue, Calendar.OCTOBER,dateValue);}
        else if (Objects.equals(month, "11") || Objects.equals(month, "011")) {newDate.set(yearValue, Calendar.NOVEMBER,dateValue);}
        else if (Objects.equals(month, "12") || Objects.equals(month, "012")) {newDate.set(yearValue, Calendar.DECEMBER,dateValue);}


//
//        int monthValue = Integer.parseInt(month);
//        int dateValue = Integer.parseInt(date);
//        int yearValue = Integer.parseInt(year);
//        newDate.set(yearValue,monthValue - ONE,dateValue);


//        if (Objects.equals(month, "1") || Objects.equals(month, "01")) {month = String.valueOf(Calendar.JANUARY);}
//        else if (Objects.equals(month, "2") || Objects.equals(month, "02")) {month = String.valueOf(Calendar.FEBRUARY);}
//        else if (Objects.equals(month, "3") || Objects.equals(month, "03")) {month = String.valueOf(Calendar.MARCH);}
//        else if (Objects.equals(month, "4") || Objects.equals(month, "04")) {month = String.valueOf(Calendar.APRIL);}
//        else if (Objects.equals(month, "5") || Objects.equals(month, "05")) {month = String.valueOf(Calendar.MAY);}
//        else if (Objects.equals(month, "6") || Objects.equals(month, "06")) {month = String.valueOf(Calendar.JUNE);}
//        else if (Objects.equals(month, "7") || Objects.equals(month, "07")) {month = String.valueOf(Calendar.JULY);}
//        else if (Objects.equals(month, "8") || Objects.equals(month, "08")) {month = String.valueOf(Calendar.AUGUST);}
//        else if (Objects.equals(month, "9") || Objects.equals(month, "09")) {month = String.valueOf(Calendar.SEPTEMBER);}
//        else if (Objects.equals(month, "10") || Objects.equals(month, "010")) {month = String.valueOf(Calendar.OCTOBER);}
//        else if (Objects.equals(month, "11") || Objects.equals(month, "011")) {month = String.valueOf(Calendar.NOVEMBER);}
//        else if (Objects.equals(month, "12") || Objects.equals(month, "012")) {month = String.valueOf(Calendar.DECEMBER);}
//


        return newDate.getTime();
    }

}
