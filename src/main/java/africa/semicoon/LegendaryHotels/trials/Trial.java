package africa.semicoon.LegendaryHotels.trials;

import java.util.ArrayList;
import java.util.List;

public class Trial {
    public static void main(String[] args) {

//        bookRoom();
//        bookRoom();
//        bookRoom();
//        //bookSpecificRoom(6);
//        //bookSpecificRoom(2);
//        System.out.println(findBookedRooms());
//        System.out.println(showNumbers());
//        System.out.println(Arrays.toString(showAllRoom()));
//        printFreeRooms();
//        System.out.println();
//        System.out.println();
//        checkOut();
//        checkOut();
//        System.out.println(findBookedRooms());
//        System.out.println(showNumbers());
//        System.out.println(Arrays.toString(showAllRoom()));
//        printFreeRooms();



    }

    private static int[] rooms = new int[10];
    private static int roomNumber;
    private static List<Integer> bookedNumbers = new ArrayList<>();

    public static void bookRoom(){
        for (int i = 0; i < rooms.length ; i++) {
            if(rooms[i] == 0){
                rooms[i] = 1;
                roomNumber = i + 1;
                bookedNumbers.add(roomNumber);
                break;
            }
        }
    }

    public static void checkOut(){
        for (int i = 0; i < rooms.length; i++) {
            if(rooms[i] == 1){
                rooms[i] = 0;
                //roomNumber = i + 1;
                //bookedNumbers.remove(i-1);
                break;
            }
        }
    }

    public static void bookSpecificRoom(int choice){
        for (int i = 0; i < rooms.length - 1 ; i++) {
            if(rooms[choice - i] == 0){
                rooms[choice - i] = 1;
                roomNumber = i+choice;
                bookedNumbers.add(roomNumber);
                break;
            }
        }
    }

    public static int findBookedRooms(){
        int bookedRoom = 0;
        for (int i = 0; i < rooms.length; i++) {
            if(rooms[i] == 1) bookedRoom++;
        }
        return bookedRoom;
    }

    public static List<Integer> showNumbers(){
        return bookedNumbers;
    }
    public static int[] showAllRoom(){
        return rooms;
    }
    public static void printFreeRooms(){
        System.out.print("The following rooms are free; Rooms ");
        for (int i = 0; i < rooms.length; i++) {
            if(rooms[i] == 1){
                continue;
            }
            else {
                System.out.print((i+1)+", ");
            }
        }
    }
}
