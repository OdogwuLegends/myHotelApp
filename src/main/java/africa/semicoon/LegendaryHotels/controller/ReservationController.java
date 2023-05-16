package africa.semicoon.LegendaryHotels.controller;

import africa.semicoon.LegendaryHotels.dto.requests.ReservationRequest;
import africa.semicoon.LegendaryHotels.dto.response.ReservationResponse;
import africa.semicoon.LegendaryHotels.services.IReservationService;
import africa.semicoon.LegendaryHotels.services.ServiceForReservation;

public class ReservationController {
    private final IReservationService reservationService = new ServiceForReservation();
    public ReservationResponse reserveARoom(ReservationRequest reservationRequest) {
        return reservationService.findAndReserveARoom(reservationRequest);
    }
}
