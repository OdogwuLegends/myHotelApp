package africa.semicoon.LegendaryHotels.trials;

import africa.semicoon.LegendaryHotels.exceptions.RoomUnavailableException;

public interface IRoomRep {
    String bookRoom(int choice) throws RoomUnavailableException;
    String checkOut(int choice);
    String printBookedRooms();
    String printAvailableRooms();

}
