package africa.semicoon.LegendaryHotels.trials;

import africa.semicoon.LegendaryHotels.exceptions.InvalidRoomNumberException;
import africa.semicoon.LegendaryHotels.exceptions.RoomUnavailableException;

public class ServiceForSingleRooms implements IRoomServ{

    private final IRoomRep repositoryForSingleRooms = new RepositoryForSingleRooms();
    @Override
    public String bookRoom(int choice) {
        try{
            return repositoryForSingleRooms.bookRoom(choice);
        }
        catch (InvalidRoomNumberException | RoomUnavailableException ex){
            System.err.println(ex.getMessage());
        }
        return "";
    }

    @Override
    public String checkOut(int choice) {
        return repositoryForSingleRooms.checkOut(choice);
    }

    @Override
    public String printBookedRooms() {
        return repositoryForSingleRooms.printBookedRooms();
    }

    @Override
    public String printAvailableRooms() {
        return repositoryForSingleRooms.printAvailableRooms();
    }
}
