package africa.semicoon.LegendaryHotels.services;

import africa.semicoon.LegendaryHotels.dto.requests.ReservationRequest;
import africa.semicoon.LegendaryHotels.dto.response.ReservationResponse;
import africa.semicoon.LegendaryHotels.models.Reservation;

import java.util.List;

public interface IReservationService {
    String findARoom(ReservationRequest customerReservation);
    String reserveARoom(ReservationRequest customerReservation);
    Reservation getRoom(ReservationRequest customerReservation);
    Reservation getCustomerReservation(ReservationRequest customerReservation);
    Reservation checkOut(ReservationRequest customerReservation);
    List<Reservation> printReservations();
}
