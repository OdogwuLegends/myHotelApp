package africa.semicoon.AprilHotels.repositories;

import africa.semicoon.AprilHotels.exceptions.RoomUnavailableException;
import africa.semicoon.AprilHotels.models.Customer;
import africa.semicoon.AprilHotels.models.Reservation;
import africa.semicoon.AprilHotels.models.RoomType;

import java.util.Date;
import java.util.List;

public interface IReservationRep {
    Reservation findRooms(int roomNumber);
    Reservation findAndReserveARoom(Reservation customerReservation, RoomType roomType, int roomNumberChoice) throws RoomUnavailableException;
    String getCustomerReservation(Customer customer);
    Reservation checkOut(Date checkOutDate, int roomType);
    List<Reservation> printReservations();
}
