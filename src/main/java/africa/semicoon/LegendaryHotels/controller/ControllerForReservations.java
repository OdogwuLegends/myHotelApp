package africa.semicoon.LegendaryHotels.controller;

import africa.semicoon.LegendaryHotels.dto.requests.RequestsForReservations;
import africa.semicoon.LegendaryHotels.dto.response.ResponseForReservation;
import africa.semicoon.LegendaryHotels.exceptions.AmountIncorrectException;
import africa.semicoon.LegendaryHotels.exceptions.InvalidRoomNumberException;
import africa.semicoon.LegendaryHotels.exceptions.RoomUnavailableException;
import africa.semicoon.LegendaryHotels.models.Reservation;
import africa.semicoon.LegendaryHotels.models.Room;
import africa.semicoon.LegendaryHotels.services.IReservationService;
import africa.semicoon.LegendaryHotels.services.ServiceForReservations;

import java.util.List;

public class ControllerForReservations {
    private final IReservationService reservationService = new ServiceForReservations();
    public String reserveARoom(RequestsForReservations requestsForReservations) {
        try{
            return reservationService.reserveARoom(requestsForReservations);
        } catch (RoomUnavailableException | AmountIncorrectException ex){
            System.err.println(ex.getMessage());
        }
        return "";
    }
    public String findARoom(int roomType){
        return reservationService.findARoom(roomType);
    }
    public Room getRoom(RequestsForReservations requestsForReservations){
        return reservationService.getRoom(requestsForReservations);
    }
    public ResponseForReservation getCustomerReservation(RequestsForReservations requestsForReservations){
        return reservationService.getCustomerReservation(requestsForReservations);
    }
    public Reservation checkOut(RequestsForReservations requestsForReservations){
        try{
            return reservationService.checkOut(requestsForReservations);
        } catch (InvalidRoomNumberException ex){
            System.err.println(ex.getMessage());
        }
        return null;
    }
    public List<Reservation> printReservations(){
        return reservationService.printReservations();
    }

    public List<Integer> showAllRooms(){ return reservationService.showAllRooms(); }
}
