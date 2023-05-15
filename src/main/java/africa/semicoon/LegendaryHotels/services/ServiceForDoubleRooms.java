package africa.semicoon.LegendaryHotels.services;

import africa.semicoon.LegendaryHotels.exceptions.InvalidRoomNumberException;
import africa.semicoon.LegendaryHotels.exceptions.RoomUnavailableException;
import africa.semicoon.LegendaryHotels.trials.IRoomRep;
import africa.semicoon.LegendaryHotels.trials.RepositoryForDoubleRooms;

public class ServiceForDoubleRooms implements IRoomServ{
    private final IRoomRep repositoryForDoubleRooms = new RepositoryForDoubleRooms();

    @Override
    public String bookRoom(int choice) {
        try{
            return repositoryForDoubleRooms.bookRoom(choice);
        }
        catch (InvalidRoomNumberException | RoomUnavailableException ex){
            System.err.println(ex.getMessage());
        }
        return "";
    }

    @Override
    public String checkOut(int choice) {
        return repositoryForDoubleRooms.checkOut(choice);
    }

    @Override
    public String printBookedRooms() {
        return repositoryForDoubleRooms.printBookedRooms();
    }

    @Override
    public String printAvailableRooms() {
        return repositoryForDoubleRooms.printAvailableRooms();
    }
}
