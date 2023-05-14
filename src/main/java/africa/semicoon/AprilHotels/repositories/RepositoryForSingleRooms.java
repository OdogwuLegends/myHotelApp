package africa.semicoon.AprilHotels.repositories;

import africa.semicoon.AprilHotels.exceptions.InvalidRoomNumberException;
import africa.semicoon.AprilHotels.exceptions.RoomUnavailableException;
import africa.semicoon.AprilHotels.models.Room;

import static africa.semicoon.AprilHotels.utils.AppUtils.TWENTY_DOLLARS;

public class RepositoryForSingleRooms implements IRoom{

    private int[] singleRooms = new int[10];
    private int roomNumber;
    @Override
    public String bookRoom(int choice) throws RoomUnavailableException {
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

    @Override
    public String checkOut(int choice) {
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

    @Override
    public String printBookedRooms() {
        StringBuilder bookedRooms = new StringBuilder();
        for (int i = 1; i <= singleRooms.length; i++) {
            if(singleRooms[i - 1] == 1){
                bookedRooms.append(i);
                bookedRooms.append(", ");
            }
        }
        return "Booked Single Rooms are " + bookedRooms;
    }

    @Override
    public String printAvailableRooms() {
        StringBuilder availableRooms = new StringBuilder();
        for (int i = 1; i <= singleRooms.length; i++) {
            if(singleRooms[i - 1] == 0){
                availableRooms.append(i);
                availableRooms.append(", ");
            }
        }
        return "Available Single Rooms are " + availableRooms;
    }

}
