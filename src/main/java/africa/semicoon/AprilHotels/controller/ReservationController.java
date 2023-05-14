package africa.semicoon.AprilHotels.controller;

import africa.semicoon.AprilHotels.dto.requests.ReservationRequest;
import africa.semicoon.AprilHotels.dto.response.ReservationResponse;
import africa.semicoon.AprilHotels.services.IReservationServ;
import africa.semicoon.AprilHotels.services.ReservationService;

public class ReservationController {
    private final IReservationServ reservationService = new ReservationService();
    public ReservationResponse reserveARoom(ReservationRequest reservationRequest) {
        return reservationService.findAndReserveARoom(reservationRequest);
    }
}
