package africa.semicoon.LegendaryHotels.repositories;

import africa.semicoon.LegendaryHotels.exceptions.InvalidRoomNumberException;
import africa.semicoon.LegendaryHotels.exceptions.RoomUnavailableException;
import africa.semicoon.LegendaryHotels.models.Customer;
import africa.semicoon.LegendaryHotels.models.Reservation;
import africa.semicoon.LegendaryHotels.models.RoomType;

import java.util.Date;
import java.util.List;

public interface IReservationRepository {
    String findARoom(RoomType roomType);
    String reserveARoom(Reservation customerReservation, RoomType roomType, int roomNumberChoice) throws RoomUnavailableException;
    Reservation getRoom(int roomNumber);
    Reservation getCustomerReservation(Customer customer);
    Reservation checkOut(Date checkInDate, Date checkOutDate) throws InvalidRoomNumberException;
    List<Reservation> printReservations();

}
