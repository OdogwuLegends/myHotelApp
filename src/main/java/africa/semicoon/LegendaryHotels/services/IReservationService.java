package africa.semicoon.LegendaryHotels.services;

import africa.semicoon.LegendaryHotels.dto.requests.RequestsForReservations;
import africa.semicoon.LegendaryHotels.dto.response.ResponseForReservation;
import africa.semicoon.LegendaryHotels.exceptions.AmountIncorrectException;
import africa.semicoon.LegendaryHotels.exceptions.InvalidRoomNumberException;
import africa.semicoon.LegendaryHotels.exceptions.RoomUnavailableException;
import africa.semicoon.LegendaryHotels.models.Reservation;
import africa.semicoon.LegendaryHotels.models.Room;

import java.util.List;

public interface IReservationService {
    String findARoom(int roomType);
    String reserveARoom(RequestsForReservations customerReservation) throws RoomUnavailableException, AmountIncorrectException;
    Room getRoom(RequestsForReservations customerReservation);
    ResponseForReservation getCustomerReservation(RequestsForReservations customerReservation);
    String checkOut(RequestsForReservations customerReservation) throws InvalidRoomNumberException;
    List<Reservation> findAllReservations();
    List<Integer> showAllRooms();
}
