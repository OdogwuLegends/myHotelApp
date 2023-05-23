package africa.semicoon.LegendaryHotels.repositories;

import africa.semicoon.LegendaryHotels.dto.response.ResponseForReservation;
import africa.semicoon.LegendaryHotels.dto.response.ResponseForRoomBooking;
import africa.semicoon.LegendaryHotels.models.Customer;
import africa.semicoon.LegendaryHotels.models.Reservation;
import africa.semicoon.LegendaryHotels.models.Room;
import africa.semicoon.LegendaryHotels.models.RoomType;

import java.util.Date;
import java.util.List;

public interface IReservationRepository {
    ResponseForRoomBooking findARoom(RoomType roomType);
    ResponseForRoomBooking reserveARoom(Reservation customerReservation);
    Room getRoom(int roomNumber);
    Reservation getCustomerReservation(Customer customer);
    ResponseForRoomBooking checkOut(Date checkInDate, Date checkOutDate);
    List<Reservation> getAllReservations();
    List<Integer> listOfBookedSingleRooms();
    List<Integer> listOfBookedDoubleRooms();
    List<Integer> showAllRooms();
    Reservation findReservationByEmail(String email);
}
