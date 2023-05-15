package africa.semicoon.LegendaryHotels.trials;

import africa.semicoon.LegendaryHotels.exceptions.InvalidRoomNumberException;
import africa.semicoon.LegendaryHotels.exceptions.RoomUnavailableException;
import africa.semicoon.LegendaryHotels.models.Room;

import static africa.semicoon.LegendaryHotels.utils.AppUtils.FIFTY_DOLLARS;

public class RepositoryForDoubleRooms implements IRoomRep {

    private int[] doubleRooms = new int[10];
    private int roomNumber;

    @Override
    public String bookRoom(int choice) throws RoomUnavailableException {
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
    public String checkOut(int choice) {
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
    public String printBookedRooms() {
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
    public String printAvailableRooms() {
        StringBuilder availableRooms = new StringBuilder();
        for (int i = 11; i <= doubleRooms.length; i++) {
            if (doubleRooms[i - 1] == 0) {
                availableRooms.append(i);
                availableRooms.append(", ");
            }
        }
        return "Available Double Rooms are " + availableRooms;
    }

}

