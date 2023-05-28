package africa.semicoon.LegendaryHotels.utils;

import africa.semicoon.LegendaryHotels.dto.requests.RequestsForAdmins;
import africa.semicoon.LegendaryHotels.dto.requests.RequestsForCustomers;
import africa.semicoon.LegendaryHotels.dto.requests.RequestsForReservations;
import africa.semicoon.LegendaryHotels.dto.response.*;
import africa.semicoon.LegendaryHotels.exceptions.InvalidEmailException;
import africa.semicoon.LegendaryHotels.models.*;
import africa.semicoon.LegendaryHotels.repositories.IReservationRepository;
import africa.semicoon.LegendaryHotels.repositories.RepositoryForReservations;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import static africa.semicoon.LegendaryHotels.utils.AppUtils.ONE;
import static africa.semicoon.LegendaryHotels.utils.AppUtils.emailIsCorrect;

public class Map {

    public static Reservation requestToReservation(RequestsForReservations requestsForReservations){

        Customer customer = customerDetails(requestsForReservations);
        Room room = setRoomDetail(requestsForReservations);

        //RoomType roomType = roomDetail(requestsForReservations);

        //Date checkIn = setDate(requestsForReservations.getCheckInDate(), requestsForReservations.getCheckInMonth(), requestsForReservations.getCheckInYear());
        //Date checkOut = setDate(requestsForReservations.getCheckOutDate(), requestsForReservations.getCheckOutMonth(), requestsForReservations.getCheckOutYear());

        LocalDate checkIn = getDateFromUser(requestsForReservations.getCheckIn());
        LocalDate checkOut = getDateFromUser(requestsForReservations.getCheckOut());

        Reservation customerReservation = new Reservation();

        customerReservation.setCustomer(customer);
        customerReservation.setRoom(room);
        customerReservation.setCheckIn(checkIn);
        customerReservation.setCheckOut(checkOut);

        return customerReservation;

    }
    public static Customer reservationRequestToCustomer(RequestsForReservations requestsForReservations){
        Customer customer = requestsForReservations.getCustomer();
        return customer;

    }
    public static Customer requestToCustomer(RequestsForCustomers requestsForCustomers) {
        Customer newCustomer = new Customer();
        newCustomer.setFirstName(requestsForCustomers.getFirstName());
        newCustomer.setLastName(requestsForCustomers.getLastName());
        if(!emailIsCorrect(requestsForCustomers.getEmail())){
            throw new InvalidEmailException("Invalid email.");
        }
        newCustomer.setEmail(requestsForCustomers.getEmail());
        newCustomer.setPassword(requestsForCustomers.getPassword());
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

//    public static RoomResponse requestToRoomResponse(RequestsForReservations requestsForReservations){
//        int roomNumberChoice = requestsForReservations.getRoom().getRoomNumber();
//        Room foundRoom = requestsForReservations.getRoom(roomNumberChoice);
//        RoomResponse roomResponse = new RoomResponse();
//        roomResponse.setRoomNumber(foundRoom.getRoomNumber());
//        roomResponse.setRoomPrice(foundRoom.getPrice());
//        roomResponse.setRoomType(foundRoom.getRoomType());
//
//        return roomResponse;
//    }

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
        responseForCustomerRegistration.setEmail(customer.getEmail());
        responseForCustomerRegistration.setPassword(customer.getPassword());
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
        return requestsForReservations.getRoom().getRoomType();

    }
    private static Room setRoomDetail(RequestsForReservations requestsForReservations){
        Room room = new Room();
        room.setRoomNumber(requestsForReservations.getRoom().getRoomNumber());
        room.setPrice(requestsForReservations.getRoom().getPrice());
        room.setRoomType(requestsForReservations.getRoom().getRoomType());

        return room;
    }

    private static Customer customerDetails(RequestsForReservations requestsForReservations) {
        Customer customer = new Customer();
        customer.setFirstName(requestsForReservations.getCustomer().getFirstName());
        customer.setLastName(requestsForReservations.getCustomer().getLastName());
        customer.setEmail(requestsForReservations.getCustomer().getEmail());
        return customer;
    }


    public static ResponseForReservation reservationToResponse(Reservation foundReservation){

        ResponseForReservation responseForReservation = new ResponseForReservation();

        if (foundReservation != null){
            Customer customer = new Customer();
            customer.setFirstName(foundReservation.getCustomer().getFirstName());
            customer.setLastName(foundReservation.getCustomer().getLastName());
            customer.setEmail(foundReservation.getCustomer().getEmail());

            Room room = new Room();
            room.setPrice(foundReservation.getRoom().getPrice());
            room.setRoomNumber(foundReservation.getRoom().getRoomNumber());
            room.setRoomType(foundReservation.getRoom().getRoomType());

            responseForReservation.setCustomer(customer);
            responseForReservation.setRoom(room);
            responseForReservation.setReservation(foundReservation);

        }

        return responseForReservation;
    }
    public static ResponseForReservation reservationToReservationResponse(Reservation reservation){
        Customer foundCustomer = reservation.getCustomer();
        Room room = reservation.getRoom();

        ResponseForReservation responseForReservation = new ResponseForReservation();
        responseForReservation.setCustomer(foundCustomer);
        responseForReservation.setRoom(room);
        responseForReservation.setReservation(reservation);

        return responseForReservation;
    }
    

    public static Date setDate(int year, int month, int date){
        Calendar newDate = Calendar.getInstance();
        newDate.set(year,month - ONE,date);

        return newDate.getTime();
    }

    public static LocalDate getDateFromUser(String userInput) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(userInput, dateFormatter);
    }


}
