package africa.semicoon.LegendaryHotels.trials;

public class Trials2 {
    public static void main(String[] args) {
        bookRoom(3);
        bookRoom(7);
        printRoomStatus();
        unBookRoom(7);
        printRoomStatus();
    }
    private static int[] rooms = new int[10];

    public static void bookRoom(int roomNumber) {
        if (roomNumber < 1 || roomNumber > rooms.length) {
            System.out.println("Invalid room number");
            return;
        }
        if (rooms[roomNumber - 1] == 1) {
            System.out.println("Room " + roomNumber + " is already booked.");
        } else {
            rooms[roomNumber - 1] = 1;
            System.out.println("Room " + roomNumber + " booked successfully.");
        }
    }

    public static void unBookRoom(int roomNumber) {
        if (roomNumber < 1 || roomNumber > rooms.length) {
            System.out.println("Invalid room number");
            return;
        }
        if (rooms[roomNumber - 1] == 1) {
            rooms[roomNumber - 1] = 0;
            System.out.println("Room " + roomNumber + " unbooked successfully.");
        } else {
            System.out.println("Room " + roomNumber + " is already available.");
        }
    }
    public static void printRoomStatus() {
        System.out.println("Room Status:");
        for (int roomNumber = 1; roomNumber <= rooms.length; roomNumber++) {
            String status;
            if (rooms[roomNumber - 1] == 1) {
                status = "Booked";
            } else {
                status = "Available";
            }
            System.out.println("Room " + roomNumber + ": " + status);
        }
    }
}
