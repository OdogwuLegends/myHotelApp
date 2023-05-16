package africa.semicoon.LegendaryHotels.services;

import africa.semicoon.LegendaryHotels.dto.requests.ReservationRequest;
import africa.semicoon.LegendaryHotels.dto.response.ReservationResponse;
import africa.semicoon.LegendaryHotels.exceptions.InvalidRoomNumberException;
import africa.semicoon.LegendaryHotels.exceptions.RoomUnavailableException;
import africa.semicoon.LegendaryHotels.models.Reservation;
import africa.semicoon.LegendaryHotels.models.Room;

import java.util.List;

public interface IReservationService {
    String findARoom(ReservationRequest customerReservation);
    String reserveARoom(ReservationRequest customerReservation) throws RoomUnavailableException;
    Room getRoom(ReservationRequest customerReservation);
    String getCustomerReservation(ReservationRequest customerReservation);
    Reservation checkOut(ReservationRequest customerReservation) throws InvalidRoomNumberException;
    List<Reservation> printReservations();
}
