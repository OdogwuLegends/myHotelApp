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
import africa.semicoon.LegendaryHotels.repositories.IReservationRepository;
import africa.semicoon.LegendaryHotels.repositories.RepositoryForReservations;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import static africa.semicoon.LegendaryHotels.utils.AppUtils.ONE;
import static africa.semicoon.LegendaryHotels.utils.AppUtils.emailIsCorrect;

public class Map {

    public static Reservation requestToReservation(RequestsForReservations requestsForReservations){

        Customer customer = customerDetails(requestsForReservations);
        Room room = setRoomDetail(requestsForReservations);

        //RoomType roomType = roomDetail(requestsForReservations);

        Date checkIn = setDate(requestsForReservations.getCheckInDate(), requestsForReservations.getCheckInMonth(), requestsForReservations.getCheckInYear());
        Date checkOut = setDate(requestsForReservations.getCheckOutDate(), requestsForReservations.getCheckOutMonth(), requestsForReservations.getCheckOutYear());

        Reservation customerReservation = new Reservation();

        customerReservation.setCustomer(customer);
        customerReservation.setRoom(room);
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


    public static ResponseForReservation olaMap(String email){
        System.out.println("Na here i dey ooooooooooooo");
        IReservationRepository reservationRepository = new RepositoryForReservations();

        Reservation foundReservation = reservationRepository.findReservationByEmail(email);

        ResponseForReservation responseForReservation = new ResponseForReservation();


        if (foundReservation != null){
        responseForReservation.setFirstName(foundReservation.getCustomer().getFirstName());
        responseForReservation.setLastName(foundReservation.getCustomer().getLastName());
        responseForReservation.setAmount(foundReservation.getRoom().getPrice());
        responseForReservation.setRoomChoice(foundReservation.getRoom().getRoomNumber());
        responseForReservation.setRoomType(foundReservation.getRoom().getRoomType());
        responseForReservation.setCheckInDate(foundReservation.getCheckIn());
        responseForReservation.setCheckOutDate(foundReservation.getCheckOut());
        }

        return responseForReservation;
    }
    public static ResponseForReservation reservationToReservationResponse(Reservation reservation){
        ResponseForReservation responseForReservation = new ResponseForReservation();


        Customer foundCustomer = reservation.getCustomer();
        Room room = reservation.getRoom();
        Date foundCheckIn = reservation.getCheckIn();
        Date foundCheckOut = reservation.getCheckOut();

        responseForReservation.setFirstName(foundCustomer.getFirstName());
        responseForReservation.setLastName(foundCustomer.getLastName());
        responseForReservation.setAmount(room.getPrice());
        responseForReservation.setRoomType(room.getRoomType());
        responseForReservation.setRoomChoice(room.getRoomNumber());
        responseForReservation.setCheckInDate(foundCheckIn);
        responseForReservation.setCheckOutDate(foundCheckOut);

        return responseForReservation;
    }
    

    public static Date setDate(int date, int month, int year){
        Calendar newDate = Calendar.getInstance();
        newDate.set(year,month - ONE,date);

        return newDate.getTime();
    }

}
