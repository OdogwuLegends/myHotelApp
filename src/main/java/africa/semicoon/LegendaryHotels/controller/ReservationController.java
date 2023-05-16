package africa.semicoon.LegendaryHotels.controller;

import africa.semicoon.LegendaryHotels.dto.requests.ReservationRequest;
import africa.semicoon.LegendaryHotels.exceptions.InvalidRoomNumberException;
import africa.semicoon.LegendaryHotels.exceptions.RoomUnavailableException;
import africa.semicoon.LegendaryHotels.models.Reservation;
import africa.semicoon.LegendaryHotels.models.Room;
import africa.semicoon.LegendaryHotels.services.IReservationService;
import africa.semicoon.LegendaryHotels.services.ServiceForReservation;

import java.util.List;

public class ReservationController {
    private final IReservationService reservationService = new ServiceForReservation();
    public String reserveARoom(ReservationRequest reservationRequest) {
        try{
            return reservationService.reserveARoom(reservationRequest);
        } catch (RoomUnavailableException ex){
            System.err.println(ex.getMessage());
        }
        return "";
    }
    public String findARoom(ReservationRequest reservationRequest){
        return reservationService.findARoom(reservationRequest);
    }
    public Room getRoom(ReservationRequest reservationRequest){
        return reservationService.getRoom(reservationRequest);
    }
    public String getCustomerReservation(ReservationRequest reservationRequest){
        return reservationService.getCustomerReservation(reservationRequest);
    }
    public Reservation checkOut(ReservationRequest reservationRequest){
        try{
            return reservationService.checkOut(reservationRequest);
        } catch (InvalidRoomNumberException ex){
            System.err.println(ex.getMessage());
        }
        return null;
    }
    public List<Reservation> printReservations(){
        return reservationService.printReservations();
    }
}
