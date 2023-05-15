package africa.semicoon.LegendaryHotels.trials;

import africa.semicoon.LegendaryHotels.exceptions.InvalidRoomNumberException;
import africa.semicoon.LegendaryHotels.exceptions.RoomUnavailableException;
import africa.semicoon.LegendaryHotels.models.Customer;
import africa.semicoon.LegendaryHotels.models.Reservation;
import africa.semicoon.LegendaryHotels.models.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static africa.semicoon.LegendaryHotels.utils.AppUtils.FIFTY_DOLLARS;
import static africa.semicoon.LegendaryHotels.utils.AppUtils.TWENTY_DOLLARS;

public class RepoForReservations implements IRes {

    private int[] singleRooms = new int[10];
    private int[] doubleRooms = new int[10];
    private int roomNumber;
    private List<Reservation> reservations = new ArrayList<>();

    @Override
    public String bookSingleRoom(int choice) throws RoomUnavailableException {
        if(choice < 1 || choice > singleRooms.length){
            throw new InvalidRoomNumberException("Invalid Room Number Selected.");
        }
        if(singleRooms[choice -1] == 1){
            throw new RoomUnavailableException("Room " + choice + " is already booked.");
        }
        singleRooms[choice - 1] = 1;
        roomNumber = choice;
        Room room = new Room();
        room.setRoomNumber(roomNumber);
        room.setPrice(TWENTY_DOLLARS);
        return "Room " + choice + " Booked Successfully";
    }


    public String bookDoubleRoom(int choice) throws RoomUnavailableException {
        if(choice < 1 || choice > doubleRooms.length){
            throw new InvalidRoomNumberException("Invalid Room Number Selected.");
        }
        if(doubleRooms[choice -1] == 1){
            throw new RoomUnavailableException("Room " + choice + " is already booked.");
        }
        doubleRooms[choice - 1] = 1;
        roomNumber = choice;
        Room room = new Room();
        room.setRoomNumber(roomNumber);
        room.setPrice(FIFTY_DOLLARS);
        return "Room " + choice + " Booked Successfully";
    }

    @Override
    public String checkOutOfSingleRoom(int choice) {
        if(choice < 1 || choice > singleRooms.length){
            throw new InvalidRoomNumberException("Invalid Room Number Selected.");
        }
        if(singleRooms[choice -1] == 1){
            singleRooms[choice -1] = 0;
            roomNumber = 0;
            Room room = new Room();
            room.setRoomNumber(roomNumber);
            room.setPrice(0);
            return "Room " + choice + " checkout Successful. Thank you for your patronage.";
        }
        return null;
    }


    public String checkOutOfDoubleRoom(int choice) {
        if(choice < 1 || choice > doubleRooms.length){
            throw new InvalidRoomNumberException("Invalid Room Number Selected.");
        }
        if(doubleRooms[choice -1] == 1){
            doubleRooms[choice -1] = 0;
            roomNumber = 0;
            Room room = new Room();
            room.setRoomNumber(roomNumber);
            room.setPrice(0);
            return "Room " + choice + " checkout Successful. Thank you for your patronage.";
        }
        return null;
    }
    @Override
    public String printBookedSingleRooms() {
        StringBuilder bookedRooms = new StringBuilder();
        for (int i = 1; i <= singleRooms.length; i++) {
            if(singleRooms[i - 1] == 1){
                bookedRooms.append(i);
                bookedRooms.append(", ");
            }
        }
        return "Booked Single Rooms are " + bookedRooms;
    }


    public String printBookedDoubleRooms() {
        StringBuilder bookedRooms = new StringBuilder();
        for (int i = 11; i <= doubleRooms.length; i++) {
            if(doubleRooms[i - 1] == 1){
                bookedRooms.append(i);
                bookedRooms.append(", ");
            }
        }
        return "Booked Double Rooms are " + bookedRooms;
    }

    @Override
    public String printAvailableSingleRooms() {
        StringBuilder availableRooms = new StringBuilder();
        for (int i = 1; i <= singleRooms.length; i++) {
            if(singleRooms[i - 1] == 0){
                availableRooms.append(i);
                availableRooms.append(", ");
            }
        }
        return "Available Single Rooms are " + availableRooms;
    }


    public String printAvailableDoubleRooms() {
        StringBuilder availableRooms = new StringBuilder();
        for (int i = 11; i <= singleRooms.length; i++) {
            if(singleRooms[i - 1] == 0){
                availableRooms.append(i);
                availableRooms.append(", ");
            }
        }
        return "Available Double Rooms are " + availableRooms;
    }

    @Override
    public Reservation findRooms(int roomNumber) {
        for (Reservation reservedRoom : reservations){
            if (Objects.equals(reservedRoom.getRoom().getRoomNumber(),roomNumber)) return reservedRoom;
        }
        return null;
    }

    @Override
    public Reservation reserveARoom(Reservation customerReservation) {
        reservations.add(customerReservation);
        return customerReservation;
    }

    @Override
    public Reservation getCustomerReservation(Customer customer) {
        for (Reservation customerReservation : reservations){
            if(Objects.equals(customerReservation.getCustomer(),customer)) return customerReservation;
        }
        return null;
    }

    @Override
    public List<Reservation> printReservations() {
        return reservations ;
    }

}
