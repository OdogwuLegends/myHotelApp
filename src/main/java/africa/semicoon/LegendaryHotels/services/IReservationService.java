package africa.semicoon.LegendaryHotels.services;

import africa.semicoon.LegendaryHotels.dto.requests.RequestsForReservations;
import africa.semicoon.LegendaryHotels.dto.response.ResponseForReservation;
import africa.semicoon.LegendaryHotels.dto.response.ResponseForRoomBooking;
import africa.semicoon.LegendaryHotels.dto.response.RoomResponse;
import africa.semicoon.LegendaryHotels.exceptions.AmountIncorrectException;
import africa.semicoon.LegendaryHotels.exceptions.InvalidRoomNumberException;
import africa.semicoon.LegendaryHotels.exceptions.RoomUnavailableException;
import africa.semicoon.LegendaryHotels.models.Reservation;

import java.util.List;

public interface IReservationService {
    ResponseForRoomBooking findARoom(int roomType);
    ResponseForRoomBooking reserveARoom(RequestsForReservations customerReservation) throws RoomUnavailableException, AmountIncorrectException;
    RoomResponse getRoom(RequestsForReservations customerReservation);
    ResponseForReservation getCustomerReservation(RequestsForReservations customerReservation);
    ResponseForReservation findReservationByEmail(String email);
    ResponseForRoomBooking checkOut(RequestsForReservations customerReservation) throws InvalidRoomNumberException;
    List<Reservation> findAllReservations();
    List<Integer> showAllRooms();
}
