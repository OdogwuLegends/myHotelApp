package africa.semicoon.LegendaryHotels.repositories;

import africa.semicoon.LegendaryHotels.dto.requests.ReservationRequest;
import africa.semicoon.LegendaryHotels.exceptions.InvalidRoomNumberException;
import africa.semicoon.LegendaryHotels.exceptions.RoomUnavailableException;
import africa.semicoon.LegendaryHotels.models.Customer;
import africa.semicoon.LegendaryHotels.models.Reservation;
import africa.semicoon.LegendaryHotels.models.Room;
import africa.semicoon.LegendaryHotels.models.RoomType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static africa.semicoon.LegendaryHotels.utils.AppUtils.FIFTY_DOLLARS;
import static africa.semicoon.LegendaryHotels.utils.AppUtils.TWENTY_DOLLARS;

public class RepositoryForReservation implements IReservation {

    private List<Reservation> reservations = new ArrayList<>();
    private int[] rooms = new int[20];
    private int roomNumber;


    @Override
    public String findARoom(RoomType roomType) {
        if(roomType == RoomType.SINGLE){ printAvailableSingleRooms(); }
        else { printAvailableDoubleRooms(); }
        return "";
    }

    @Override
    public String reserveARoom(Reservation customerReservation, RoomType roomType, int roomNumberChoice) throws RoomUnavailableException {
        if(roomType == RoomType.SINGLE){ bookSingleRoom(roomNumberChoice); }
        else { bookDoubleRoom(roomNumberChoice); }
        reservations.add(customerReservation);
        return "";
    }

    @Override
    public Reservation getRoom(int roomNumber) {
        for (Reservation reservedRoom : reservations){
            if (Objects.equals(reservedRoom.getRoom().getRoomNumber(),roomNumber)) return reservedRoom;
        }
        return null;
    }

    @Override
    public Reservation getCustomerReservation(Customer customer) {
        for (Reservation customerReservation : reservations){
            if(Objects.equals(customerReservation.getCustomer(),customer)) return customerReservation;
        }
        return null;
    }

    @Override
    public Reservation checkOut(Date checkInDate, Date checkOutDate)throws InvalidRoomNumberException {
        RoomType roomType = null;
        int roomNumberChoice = 0;
        for (Reservation customerReservation : reservations){
            if(Objects.equals(customerReservation.getCheckIn(),checkInDate) && Objects.equals(customerReservation.getCheckOut(),checkOutDate)) {
                roomNumberChoice = customerReservation.getRoom().getRoomNumber();
                roomType = customerReservation.getRoomType();

            }
            if(roomType == RoomType.SINGLE){
                checkOutSingleRoom(roomNumberChoice);
            }
            else {
                checkOutDoubleRoom(roomNumberChoice);
            }
            reservations.remove(customerReservation);
        }
        return null;
    }

    @Override
    public List<Reservation> printReservations() {
        return reservations;
    }


    private String bookSingleRoom(int choice) throws RoomUnavailableException {
        if(choice < 1 || choice > 10){
            throw new InvalidRoomNumberException("Invalid Room Number Selected.");
        }
        if(rooms[choice -1] == 1){
            throw new RoomUnavailableException("Room " + choice + " is already booked.");
        }
        rooms[choice - 1] = 1;
        roomNumber = choice;
        Room room = new Room();
        room.setRoomNumber(roomNumber);
        room.setPrice(TWENTY_DOLLARS);
        return "Room " + choice + " Booked Successfully";
    }


    private String bookDoubleRoom(int choice) throws RoomUnavailableException {
        if(choice < 11 || choice > rooms.length){
            throw new InvalidRoomNumberException("Invalid Room Number Selected.");
        }
        if(rooms[choice -1] == 1){
            throw new RoomUnavailableException("Room " + choice + " is already booked.");
        }
        rooms[choice - 1] = 1;
        roomNumber = choice;
        Room room = new Room();
        room.setRoomNumber(roomNumber);
        room.setPrice(FIFTY_DOLLARS);
        return "Room " + choice + " Booked Successfully";
    }


    private String checkOutSingleRoom(int choice) throws InvalidRoomNumberException{
        if(choice < 1 || choice > 10){
            throw new InvalidRoomNumberException("Invalid Room Number Selected.");
        }
        if(rooms[choice -1] == 1){
            rooms[choice -1] = 0;
            roomNumber = 0;
            Room room = new Room();
            room.setRoomNumber(roomNumber);
            room.setPrice(0);
            return "Room " + choice + " checkout Successful. Thank you for your patronage.";
        }
        return "Room " + choice + " not booked previously.";
    }


    private String checkOutDoubleRoom(int choice) {
        if(choice < 11 || choice > rooms.length){
            throw new InvalidRoomNumberException("Invalid Room Number Selected.");
        }
        if(rooms[choice -1] == 1){
            rooms[choice -1] = 0;
            roomNumber = 0;
            Room room = new Room();
            room.setRoomNumber(roomNumber);
            room.setPrice(0);
            return "Room " + choice + " checkout Successful. Thank you for your patronage.";
        }
        return "Room " + choice + " not booked previously.";
    }


    private String printBookedSingleRooms() {
        StringBuilder bookedRooms = new StringBuilder();
        for (int i = 1; i <= 10; i++) {
            if(rooms[i - 1] == 1){
                bookedRooms.append(i);
                bookedRooms.append(", ");
            }
        }
        return "Booked Single Rooms are " + bookedRooms;
    }


    private String printBookedDoubleRooms() {
        StringBuilder bookedRooms = new StringBuilder();
        for (int i = 11; i <= rooms.length; i++) {
            if(rooms[i - 1] == 1){
                bookedRooms.append(i);
                bookedRooms.append(", ");
            }
        }
        return "Booked Double Rooms are " + bookedRooms;
    }


    private String printAvailableSingleRooms() {
        StringBuilder availableRooms = new StringBuilder();
        for (int i = 1; i <= 10; i++) {
            if(rooms[i - 1] == 0){
                availableRooms.append(i);
                availableRooms.append(", ");
            }
        }
        return "Available Single Rooms are " + availableRooms;
    }


    private String printAvailableDoubleRooms() {
        StringBuilder availableRooms = new StringBuilder();
        for (int i = 11; i <= rooms.length; i++) {
            if(rooms[i - 1] == 0){
                availableRooms.append(i);
                availableRooms.append(", ");
            }
        }
        return "Available Double Rooms are " + availableRooms;
    }

}
