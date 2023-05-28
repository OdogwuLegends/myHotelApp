package africa.semicoon.LegendaryHotels.controller;

import africa.semicoon.LegendaryHotels.dto.requests.RequestsForCustomers;
import africa.semicoon.LegendaryHotels.dto.requests.RequestsForReservations;
import africa.semicoon.LegendaryHotels.dto.response.ResponseForCustomerRegistration;
import africa.semicoon.LegendaryHotels.dto.response.ResponseForRoomBooking;
import africa.semicoon.LegendaryHotels.exceptions.AmountIncorrectException;
import africa.semicoon.LegendaryHotels.exceptions.RoomUnavailableException;
import africa.semicoon.LegendaryHotels.models.Customer;
import africa.semicoon.LegendaryHotels.models.Reservation;
import africa.semicoon.LegendaryHotels.models.Room;
import africa.semicoon.LegendaryHotels.services.ICustomerService;
import africa.semicoon.LegendaryHotels.services.IReservationService;
import africa.semicoon.LegendaryHotels.services.ServiceForCustomers;
import africa.semicoon.LegendaryHotels.services.ServiceForReservations;
import africa.semicoon.LegendaryHotels.trials.IRoomServ;
import africa.semicoon.LegendaryHotels.trials.ServiceForDoubleRooms;
import africa.semicoon.LegendaryHotels.utils.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Date;

import static africa.semicoon.LegendaryHotels.models.RoomType.SINGLE;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ControllerForReservationsTest {
    RequestsForReservations requestsForReservations;
    IReservationService reservationService;
    ICustomerService customerService;
    IRoomServ roomServ;
    IRoomServ roomServ1;

    ControllerForReservations controller;
    ResponseForRoomBooking responseForReservation;
    @BeforeEach void startAllTestWith(){
        requestsForReservations = new RequestsForReservations();
        customerService = new ServiceForCustomers();
        reservationService = new ServiceForReservations();
        controller = new ControllerForReservations();
        responseForReservation = new ResponseForRoomBooking();
        roomServ = new ServiceForDoubleRooms();
        roomServ1 = new ServiceForDoubleRooms();
    }

    @Test
    void findAvailableRooms(){
         responseForReservation.setMessage("Available Single Rooms are 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, ");
         ResponseForRoomBooking actual = controller.findARoom(1);
         assertEquals(responseForReservation,actual);
    }
//    @Test
//    void reserveARoom(){
//        ResponseForCustomerRegistration response = customerService.saveCustomer(RequestsForCustomers.builder()
//                .email("legend@gmail.com")
//                .firstName("abdulmalik")
//                .lastName("niyi")
//                .password("niyi@20")
//                .build());
//        ResponseForRoomBooking booking = null;
//        try {
//            booking = reservationService.reserveARoom(requestsForReservations);
//        } catch (RoomUnavailableException | AmountIncorrectException e) {
//            System.out.println(e.getMessage());
//        }
//        System.out.println(booking);
//        responseForReservation.setMessage("Room 4 Booked Successfully");
//        ResponseForRoomBooking actual = controller.reserveARoom(buildSingleRoomRequest());
//        assertEquals(responseForReservation,actual);
//    }
    @Test
    void getRoom(){
        String expected = "roomNumber = "+ buildSingleRoomRequest().getRoom().getRoomNumber() +
                ", roomPrice = "+buildSingleRoomRequest().getRoom().getPrice()
                + ", roomType = " + buildSingleRoomRequest().getRoom().getRoomType();
        String foundRoom = controller.getRoom(buildSingleRoomRequest());
        assertEquals(foundRoom,expected);

    }

    @Test
    void getCustomerReservation(){

    }

    private RequestsForReservations buildSingleRoomRequest(){
        Room room = new Room();
        room.setRoomNumber(4);
        room.setPrice(20);
        room.setRoomType(SINGLE);


        Customer newCustomer = new Customer();
        newCustomer.setFirstName("Legends");
        newCustomer.setLastName("Odogwu");
        newCustomer.setEmail("odogwu@gmail.com");
        newCustomer.setPassword("1234");

        RequestsForReservations requestsForReservations = new RequestsForReservations();
        requestsForReservations.setCustomer(newCustomer);
        requestsForReservations.setRoom(room);

//        requestsForReservations.setCheckInDate(25);
//        requestsForReservations.setCheckInMonth(5);
//        requestsForReservations.setCheckInYear(2023);
//        requestsForReservations.setCheckOutDate(30);
//        requestsForReservations.setCheckOutMonth(5);
//        requestsForReservations.setCheckOutYear(2023);
        //Date checkIn = Map.setDate(requestsForReservations.getCheckInYear(), requestsForReservations.getCheckInMonth(),requestsForReservations.getCheckInDate());
        //Date checkOut = Map.setDate(requestsForReservations.getCheckOutYear(),requestsForReservations.getCheckOutMonth(),requestsForReservations.getCheckOutDate());

        requestsForReservations.setCheckIn("25/05/2023");
        requestsForReservations.setCheckOut("30/05/2023");

        LocalDate checkIn = Map.getDateFromUser(requestsForReservations.getCheckIn());
        LocalDate checkOut = Map.getDateFromUser(requestsForReservations.getCheckOut());

        Reservation reservation = new Reservation();
        reservation.setCheckIn(checkIn);
        reservation.setCheckOut(checkOut);

        requestsForReservations.setReservation(reservation);



        return requestsForReservations;
    }

}