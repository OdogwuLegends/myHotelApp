package africa.semicoon.LegendaryHotels.services;

import africa.semicoon.LegendaryHotels.dto.requests.RequestsForReservations;
import africa.semicoon.LegendaryHotels.exceptions.AmountIncorrectException;
import africa.semicoon.LegendaryHotels.exceptions.RoomUnavailableException;
import africa.semicoon.LegendaryHotels.models.Customer;
import africa.semicoon.LegendaryHotels.models.Reservation;
import africa.semicoon.LegendaryHotels.models.Room;
import africa.semicoon.LegendaryHotels.utils.Map;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static africa.semicoon.LegendaryHotels.models.RoomType.DOUBLE;
import static africa.semicoon.LegendaryHotels.models.RoomType.SINGLE;
import static org.junit.jupiter.api.Assertions.*;

class ServiceForReservationsTest {
    private final IReservationService reservationService = new ServiceForReservations();

    @Test
    void testToFindRoom(){
        String expected = "Available Single Rooms are 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, ";
        String actual = reservationService.findARoom(1);
        assertEquals(expected,actual);

        expected = "Available Double Rooms are 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, ";
        actual = reservationService.findARoom(2);
        assertEquals(expected,actual);
    }

    @Test
    void testToReserveARoom(){
        String expected = "Room 4 Booked Successfully";
        String actual = null;
        try {
            actual = reservationService.reserveARoom(buildSingleRoomRequest());
        } catch(RoomUnavailableException | AmountIncorrectException ex){
            System.err.println(ex.getMessage());
        }
        assertEquals(expected,actual);

        expected = "Room 15 Booked Successfully";
        actual = null;
        try {
            actual = reservationService.reserveARoom(buildDoubleRoomRequest());
        } catch(RoomUnavailableException | AmountIncorrectException ex){
            System.err.println(ex.getMessage());
        }
        assertEquals(expected,actual);
    }

    @Test
    void testToGetRoom(){
        String reservedRoom = null;
        int reservedRoomNumber = 0;
        try {
            reservedRoom = reservationService.reserveARoom(buildSingleRoomRequest());
            reservedRoomNumber = buildSingleRoomRequest().getRoom().getRoomNumber();
        } catch(RoomUnavailableException | AmountIncorrectException ex){
            System.err.println(ex.getMessage());
        }

        Room foundRoom = reservationService.getRoom(buildSingleRoomRequest());
        assertSame(reservedRoomNumber,foundRoom.getRoomNumber());
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

        requestsForReservations.setCheckInDate(25);
        requestsForReservations.setCheckInMonth(5);
        requestsForReservations.setCheckInYear(2023);
        requestsForReservations.setCheckOutDate(30);
        requestsForReservations.setCheckOutMonth(5);
        requestsForReservations.setCheckOutYear(2023);


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

        requestsForReservations.setCheckInDate(25);
        requestsForReservations.setCheckInMonth(5);
        requestsForReservations.setCheckInYear(2023);
        requestsForReservations.setCheckOutDate(30);
        requestsForReservations.setCheckOutMonth(5);
        requestsForReservations.setCheckOutYear(2023);


        return requestsForReservations;
    }


}