package africa.semicoon.LegendaryHotels.services;

import africa.semicoon.LegendaryHotels.dto.requests.RequestsForReservations;
import africa.semicoon.LegendaryHotels.dto.response.ResponseForReservation;
import africa.semicoon.LegendaryHotels.dto.response.ResponseForRoomBooking;
import africa.semicoon.LegendaryHotels.dto.response.RoomResponse;
import africa.semicoon.LegendaryHotels.exceptions.AmountIncorrectException;
import africa.semicoon.LegendaryHotels.exceptions.RoomUnavailableException;
import africa.semicoon.LegendaryHotels.models.Customer;
import africa.semicoon.LegendaryHotels.models.Reservation;
import africa.semicoon.LegendaryHotels.models.Room;
import africa.semicoon.LegendaryHotels.utils.Map;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static africa.semicoon.LegendaryHotels.models.RoomType.DOUBLE;
import static africa.semicoon.LegendaryHotels.models.RoomType.SINGLE;
import static org.junit.jupiter.api.Assertions.*;

class ServiceForReservationsTest {
    private final IReservationService reservationService = new ServiceForReservations();
    private ResponseForRoomBooking responseForReservation = new ResponseForRoomBooking();

    @Test
    void testToFindRoom(){
        responseForReservation.setMessage("Available Single Rooms are 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, ");
        ResponseForRoomBooking actual = reservationService.findARoom(1);
        assertEquals(responseForReservation,actual);

        responseForReservation.setMessage("Available Double Rooms are 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, ");
        actual = reservationService.findARoom(2);
        assertEquals(responseForReservation,actual);
    }

    @Test
    void testToReserveARoom(){
        responseForReservation.setMessage("Room 4 Booked Successfully");
        ResponseForRoomBooking actual = null;
        try {
            actual = reservationService.reserveARoom(buildSingleRoomRequest());
        } catch(RoomUnavailableException | AmountIncorrectException ex){
            System.err.println(ex.getMessage());
        }
        assertEquals(responseForReservation,actual);

        responseForReservation.setMessage("Room 15 Booked Successfully");
        actual = null;
        try {
            actual = reservationService.reserveARoom(buildDoubleRoomRequest());
        } catch(RoomUnavailableException | AmountIncorrectException ex){
            System.err.println(ex.getMessage());
        }
        assertEquals(responseForReservation,actual);
    }

    @Test
    void testToGetRoom(){
        try {
            responseForReservation = reservationService.reserveARoom(buildSingleRoomRequest());

        } catch(RoomUnavailableException | AmountIncorrectException ex){
            System.err.println(ex.getMessage());
        }
        assertNotNull(responseForReservation);

        RoomResponse foundRoom = reservationService.getRoom(buildSingleRoomRequest());

    }

//    @Test
//    void testToGetCustomerReservation(){
//
//        try {
//            responseForReservation = reservationService.reserveARoom(buildSingleRoomRequest());
//            System.out.println(responseForReservation.getReservation());
//            responseForReservation.setReservation(buildSingleRoomRequest().getReservation());
//            //assertTrue(reservationService.findAllReservations().contains(responseForReservation.getReservation()));
//            assertNotNull(responseForReservation.getReservation());
//            System.out.println(responseForReservation.getReservation());
//        } catch(RoomUnavailableException | AmountIncorrectException ex){
//            System.err.println(ex.getMessage());
//        }
//
//        ResponseForReservation foundReservation = reservationService.getCustomerReservation(buildDoubleRoomRequest());
//        System.out.println(foundReservation.getReservation());
//        //assertSame(responseForReservation.getReservation(),foundReservation.getReservation());
//    }

    @Test
    void testToFindAllReservations(){
        ResponseForReservation firstReservation = null;
        ResponseForReservation secondReservation = null;
        try {
            reservationService.reserveARoom(buildSingleRoomRequest());
            reservationService.reserveARoom(buildDoubleRoomRequest());
        } catch(RoomUnavailableException | AmountIncorrectException ex){
            System.err.println(ex.getMessage());
        }
        List<Reservation> allReservations = reservationService.findAllReservations();
        assertEquals(2,allReservations.size());
    }

    @Test
    void testToShowAllRooms(){
        List<Integer> expected = buildAllRooms();
        assertEquals(expected,reservationService.showAllRooms());
    }

    private static List<Integer> buildAllRooms() {
        List<Integer> expected = new ArrayList<>();
        expected.add(1);
        expected.add(2);
        expected.add(3);
        expected.add(4);
        expected.add(5);
        expected.add(6);
        expected.add(7);
        expected.add(8);
        expected.add(9);
        expected.add(10);
        expected.add(11);
        expected.add(12);
        expected.add(13);
        expected.add(14);
        expected.add(15);
        expected.add(16);
        expected.add(17);
        expected.add(18);
        expected.add(19);
        expected.add(20);
        return expected;
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
//
//        Date checkIn = Map.setDate(requestsForReservations.getCheckInYear(), requestsForReservations.getCheckInMonth(),requestsForReservations.getCheckInDate());
//        Date checkOut = Map.setDate(requestsForReservations.getCheckOutYear(),requestsForReservations.getCheckOutMonth(),requestsForReservations.getCheckOutDate());
//

        LocalDate checkIn = Map.getDateFromUser("25/05/2023");
        LocalDate checkOut = Map.getDateFromUser("30/05/2023");


        Reservation reservation = new Reservation();
        reservation.setCheckIn(checkIn);
        reservation.setCheckOut(checkOut);

        requestsForReservations.setReservation(reservation);



        return requestsForReservations;
    }

    private RequestsForReservations buildDoubleRoomRequest(){

        Room room = new Room();
        room.setRoomNumber(15);
        room.setPrice(50);
        room.setRoomType(DOUBLE);


        Customer newCustomer = new Customer();
        newCustomer.setFirstName("Legends");
        newCustomer.setLastName("Odogwu");
        newCustomer.setEmail("odogwu@gmail.com");
        newCustomer.setPassword("1234");

        RequestsForReservations requestsForReservations = new RequestsForReservations();
        requestsForReservations.setCustomer(newCustomer);
        requestsForReservations.setRoom(room);

//        requestsForReservations.setCheckInDate(12);
//        requestsForReservations.setCheckInMonth(6);
//        requestsForReservations.setCheckInYear(2023);
//        requestsForReservations.setCheckOutDate(18);
//        requestsForReservations.setCheckOutMonth(6);
//        requestsForReservations.setCheckOutYear(2023);
//
//        Date checkIn = Map.setDate(requestsForReservations.getCheckInYear(), requestsForReservations.getCheckInMonth(),requestsForReservations.getCheckInDate());
//        Date checkOut = Map.setDate(requestsForReservations.getCheckOutYear(),requestsForReservations.getCheckOutMonth(),requestsForReservations.getCheckOutDate());
//

        LocalDate checkIn = Map.getDateFromUser("25/05/2023");
        LocalDate checkOut = Map.getDateFromUser("30/05/2023");


        Reservation reservation = new Reservation();
        reservation.setCheckIn(checkIn);
        reservation.setCheckOut(checkOut);

        requestsForReservations.setReservation(reservation);


        return requestsForReservations;
    }


}