package africa.semicoon.AprilHotels.repositories;

import africa.semicoon.AprilHotels.exceptions.RoomUnavailableException;
import africa.semicoon.AprilHotels.models.Room;

import java.util.List;

public interface IRoom {
    String bookRoom(int choice) throws RoomUnavailableException;
    String checkOut(int choice);
    String printBookedRooms();
    String printAvailableRooms();

}
