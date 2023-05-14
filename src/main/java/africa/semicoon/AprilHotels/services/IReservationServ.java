package africa.semicoon.AprilHotels.services;

import africa.semicoon.AprilHotels.dto.requests.ReservationRequest;
import africa.semicoon.AprilHotels.dto.response.ReservationResponse;

public interface IReservationServ {
    ReservationResponse findAndReserveARoom(ReservationRequest reservationRequest);
}
