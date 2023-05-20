package africa.semicoon.LegendaryHotels.repositories;

import africa.semicoon.LegendaryHotels.models.Customer;
import africa.semicoon.LegendaryHotels.models.Reservation;
import africa.semicoon.LegendaryHotels.models.Room;
import africa.semicoon.LegendaryHotels.models.RoomType;
import africa.semicoon.LegendaryHotels.utils.Map;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static africa.semicoon.LegendaryHotels.models.RoomType.DOUBLE;
import static africa.semicoon.LegendaryHotels.models.RoomType.SINGLE;
import static org.junit.jupiter.api.Assertions.*;

class RepositoryForReservationsTest {
    private final IReservationRepository reservationRepository = new RepositoryForReservations();

    @Test
    void testToFindRoom(){
        RoomType roomType = SINGLE;
       String expected = "Available Single Rooms are 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, ";
       String actual = reservationRepository.findARoom(roomType);
        assertEquals(expected,actual);


        roomType = RoomType.DOUBLE;
        expected = "Available Double Rooms are 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, ";
        actual = reservationRepository.findARoom(roomType);
        assertEquals(expected,actual);
    }
    @Test
    void testToReserveARoom(){
       Reservation singleRoomCustomer =  buildSingleRoomReservation();
       String expected = "Room 4 Booked Successfully";
       String actual = reservationRepository.reserveARoom(singleRoomCustomer);
       assertEquals(expected,actual);

       Reservation doubleRoomCustomer = buildDoubleRoomReservation();
       expected = "Room 15 Booked Successfully";
       actual = reservationRepository.reserveARoom(doubleRoomCustomer);
       assertEquals(expected,actual);

    }

    @Test
    void testToFindRoomByRoomNumber(){
        Reservation singleRoomCustomer =  buildSingleRoomReservation();
        reservationRepository.reserveARoom(singleRoomCustomer);
        Reservation doubleRoomCustomer = buildDoubleRoomReservation();
        reservationRepository.reserveARoom(doubleRoomCustomer);

        Room foundSingleRoom = reservationRepository.getRoom(4);
        Room foundDoubleRoom = reservationRepository.getRoom(15);

        assertSame(singleRoomCustomer.getRoom(),foundSingleRoom);
        assertSame(doubleRoomCustomer.getRoom(),foundDoubleRoom);
    }

    @Test
    void testToGetCustomerReservation(){
        Reservation singleRoomCustomer =  buildSingleRoomReservation();
        reservationRepository.reserveARoom(singleRoomCustomer);

        Customer firstCustomer = new Customer();
        firstCustomer.setFirstName("Legends");
        firstCustomer.setLastName("Odogwu");
        firstCustomer.setEmail("odogwu@gmail.com");
        firstCustomer.setPassword("1234");

        Reservation actual = reservationRepository.getCustomerReservation(firstCustomer);
        assertSame(singleRoomCustomer,actual);
        assertTrue(reservationRepository.getAllReservations().contains(singleRoomCustomer));
    }

    @Test
    void testToCheckOut(){
        Reservation singleRoomCustomer =  buildSingleRoomReservation();
        reservationRepository.reserveARoom(singleRoomCustomer);

        Date checkin = singleRoomCustomer.getCheckIn();
        Date checkOut = singleRoomCustomer.getCheckOut();

        String expected = "Room 4 checkout Successful. Thank you for your patronage.";
        String actual = reservationRepository.checkOut(checkin,checkOut);
        assertEquals(expected,actual);
        assertFalse(reservationRepository.getAllReservations().contains(singleRoomCustomer));
    }
    @Test
    void testToFindAllReservations(){
        Reservation singleRoomCustomer =  buildSingleRoomReservation();
        reservationRepository.reserveARoom(singleRoomCustomer);
        Reservation doubleRoomCustomer = buildDoubleRoomReservation();
        reservationRepository.reserveARoom(doubleRoomCustomer);

        List<Reservation> allReservations = reservationRepository.getAllReservations();
        assertEquals(2,allReservations.size());
    }
    @Test
    void testToShowBookedSingleRooms(){
        Reservation singleRoomCustomer =  buildSingleRoomReservation();
        reservationRepository.reserveARoom(singleRoomCustomer);

        List<Integer> expected = new ArrayList<>();
        expected.add(4);

        List<Integer> actual = reservationRepository.listOfBookedSingleRooms();

        assertEquals(expected,actual);
    }

    @Test
    void testToShowBookedDoubleRooms(){
        Reservation doubleRoomCustomer = buildDoubleRoomReservation();
        reservationRepository.reserveARoom(doubleRoomCustomer);

        List<Integer> expected = new ArrayList<>();
        expected.add(15);

        List<Integer> actual = reservationRepository.listOfBookedDoubleRooms();

        assertEquals(expected,actual);
    }

    private static Reservation buildSingleRoomReservation() {
        Customer newCustomer = new Customer();
        newCustomer.setFirstName("Legends");
        newCustomer.setLastName("Odogwu");
        newCustomer.setPassword("1234");
        newCustomer.setEmail("odogwu@gmail.com");

        Room room = new Room();
        room.setRoomNumber(4);
        room.setPrice(20);
        room.setRoomType(SINGLE);

        int checkInDate = 25;
        int checkInMonth = 5;
        int checkInYear = 2023;
        Date checkin = Map.setDate(checkInYear,checkInMonth,checkInDate);

        int checkOutDate = 30;
        int checkOutMonth = 5;
        int checkOutYear = 2023;
        Date checkOut = Map.setDate(checkOutYear,checkOutMonth,checkOutDate);


        Reservation customerReservation = new Reservation();
        customerReservation.setCustomer(newCustomer);
        customerReservation.setRoom(room);
        customerReservation.setCheckIn(checkin);
        customerReservation.setCheckOut(checkOut);

        return customerReservation;
    }

    private static Reservation buildDoubleRoomReservation() {
        Customer newCustomer = new Customer();
        newCustomer.setFirstName("Legends");
        newCustomer.setLastName("Odogwu");
        newCustomer.setPassword("1234");
        newCustomer.setEmail("odogwu@gmail.com");

        Room room = new Room();
        room.setRoomNumber(15);
        room.setPrice(50);
        room.setRoomType(DOUBLE);

        int checkInDate = 25;
        int checkInMonth = 5;
        int checkInYear = 2023;
        Date checkin = Map.setDate(checkInYear,checkInMonth,checkInDate);

        int checkOutDate = 30;
        int checkOutMonth = 5;
        int checkOutYear = 2023;
        Date checkOut = Map.setDate(checkOutYear,checkOutMonth,checkOutDate);


        Reservation customerReservation = new Reservation();
        customerReservation.setCustomer(newCustomer);
        customerReservation.setRoom(room);
        customerReservation.setCheckIn(checkin);
        customerReservation.setCheckOut(checkOut);

        return customerReservation;
    }

}