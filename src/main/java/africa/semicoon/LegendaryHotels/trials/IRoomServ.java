package africa.semicoon.LegendaryHotels.trials;

public interface IRoomServ {
    String bookRoom(int choice);
    String checkOut(int choice);
    String printBookedRooms();
    String printAvailableRooms();
}
