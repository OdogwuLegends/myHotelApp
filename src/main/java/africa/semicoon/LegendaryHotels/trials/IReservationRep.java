package africa.semicoon.LegendaryHotels.trials;

import africa.semicoon.LegendaryHotels.exceptions.RoomUnavailableException;
import africa.semicoon.LegendaryHotels.models.Customer;
import africa.semicoon.LegendaryHotels.models.Reservation;
import africa.semicoon.LegendaryHotels.models.RoomType;

import java.util.Date;
import java.util.List;

public interface IReservationRep {
    Reservation findRooms(int roomNumber);
    Reservation findAndReserveARoom(Reservation customerReservation, RoomType roomType, int roomNumberChoice) throws RoomUnavailableException;
    String getCustomerReservation(Customer customer);
    Reservation checkOut(Date checkOutDate, int roomType);
    List<Reservation> printReservations();
}
