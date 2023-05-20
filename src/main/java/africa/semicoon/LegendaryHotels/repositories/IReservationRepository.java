package africa.semicoon.LegendaryHotels.repositories;

import africa.semicoon.LegendaryHotels.models.Customer;
import africa.semicoon.LegendaryHotels.models.Reservation;
import africa.semicoon.LegendaryHotels.models.Room;
import africa.semicoon.LegendaryHotels.models.RoomType;

import java.util.Date;
import java.util.List;

public interface IReservationRepository {
    String findARoom(RoomType roomType);
    String reserveARoom(Reservation customerReservation);
    Room getRoom(int roomNumber);
    Reservation getCustomerReservation(Customer customer);
    String checkOut(Date checkInDate, Date checkOutDate);
    List<Reservation> getAllReservations();
    List<Integer> listOfBookedSingleRooms();
    List<Integer> listOfBookedDoubleRooms();
    List<Integer> showAllRooms();
    Reservation findReservationByEmail(String email);


}
