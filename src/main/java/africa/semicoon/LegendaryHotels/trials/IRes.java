package africa.semicoon.LegendaryHotels.trials;

import africa.semicoon.LegendaryHotels.exceptions.RoomUnavailableException;
import africa.semicoon.LegendaryHotels.models.Customer;
import africa.semicoon.LegendaryHotels.models.Reservation;

import java.util.List;

public interface IRes {
    String bookSingleRoom(int choice) throws RoomUnavailableException;
    String bookDoubleRoom(int choice) throws RoomUnavailableException;

    String checkOutOfSingleRoom(int choice);
    String checkOutOfDoubleRoom(int choice);
    Reservation findRooms(int roomNumber);
    Reservation reserveARoom(Reservation customerReservation);
    Reservation getCustomerReservation(Customer customer);
    List<Reservation> printReservations();
    String printBookedSingleRooms();
    String printBookedDoubleRooms();
    String printAvailableSingleRooms();
    String printAvailableDoubleRooms();

}
