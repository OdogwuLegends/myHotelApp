package africa.semicoon.AprilHotels.services;

import africa.semicoon.AprilHotels.dto.requests.ReservationRequest;
import africa.semicoon.AprilHotels.dto.response.ReservationResponse;
import africa.semicoon.AprilHotels.exceptions.InvalidRoomNumberException;
import africa.semicoon.AprilHotels.exceptions.RoomUnavailableException;
import africa.semicoon.AprilHotels.models.Reservation;
import africa.semicoon.AprilHotels.models.RoomType;
import africa.semicoon.AprilHotels.repositories.IReservationRep;
import africa.semicoon.AprilHotels.repositories.RepositoryForReservation;
import africa.semicoon.AprilHotels.utils.Map;

public class ReservationService implements IReservationServ {

    private final IReservationRep reservationRepository = new RepositoryForReservation();
    @Override
    public ReservationResponse findAndReserveARoom(ReservationRequest reservationRequest) {
        RoomType roomType = reservationRequest.getRoomType();
        int roomChoice = reservationRequest.getRoomChoice();
        Reservation customerReservation = Map.map(reservationRequest);

        try {
            reservationRepository.findAndReserveARoom(customerReservation,roomType,roomChoice);
        } catch (InvalidRoomNumberException | RoomUnavailableException ex){
            System.err.println(ex.getMessage());
        }

        ReservationResponse reservationResponse = new ReservationResponse();
        reservationResponse.setMessage("");
        return reservationResponse;
    }
}
