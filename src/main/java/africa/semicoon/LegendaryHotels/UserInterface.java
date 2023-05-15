package africa.semicoon.LegendaryHotels;

import africa.semicoon.LegendaryHotels.controller.ReservationController;
import africa.semicoon.LegendaryHotels.dto.requests.ReservationRequest;
import africa.semicoon.LegendaryHotels.models.RoomType;
import africa.semicoon.LegendaryHotels.utils.Display;

public class UserInterface {
    private static ReservationController controller = new ReservationController();

    public static void main(String[] args) {
        String mainMenu = """
                1. Find and reserve a Room.
                2. See my reservations.
                3. Create an account.
                4. Admin.
                5. Exit
                """;
        int userInput = Display.intInput("");

        switch (userInput){
            case 1 -> findAndReserveARoom();
            //case 2 -> printReservations();
            //case 3 -> createAccount();
            //case 4 -> admin();
            case 5 -> System.exit(10);
        }
    }

    private static void findAndReserveARoom() {
        Display.message("1. Sign up || 2. Sign in");

    }
    private static void ReserveARoom(){
        String firstName = Display.StringInput("First Name: ");
        String lastName = Display.StringInput("Last Name: ");
        String email = Display.StringInput("Email address: ");
        Display.message("""
                Room Type
                1. Single Room || 2. Double Room
                """);
        int roomType = Display.intInput("");
        RoomType type;
        if(roomType == 1){ type = RoomType.SINGLE; } else { type = RoomType.DOUBLE; }
        int roomNumber = Display.intInput("Room Number: ");
        String checkInDate = Display.StringInput("Check In date: ");
        String checkInMonth = Display.StringInput("Check In month(in digits): ");
        String checkInYear = Display.StringInput("Check In year: ");
        String checkOutDate = Display.StringInput("Check out date: ");
        String checkOutMonth = Display.StringInput("Check out month: ");
        String checkOutYear = Display.StringInput("Check out year: ");

        ReservationRequest reservationRequest = new ReservationRequest();

        reservationRequest.setFirstName(firstName);
        reservationRequest.setLastName(lastName);
        reservationRequest.setEmail(email);
        reservationRequest.setRoomType(type);
        reservationRequest.setRoomChoice(roomNumber);
        reservationRequest.setCheckInDate(checkInDate);
        reservationRequest.setCheckInMonth(checkInMonth);
        reservationRequest.setCheckInYear(checkInYear);
        reservationRequest.setCheckOutDate(checkOutDate);
        reservationRequest.setCheckOutMonth(checkOutMonth);
        reservationRequest.setCheckOutYear(checkOutYear);

        Display.message(controller.reserveARoom(reservationRequest).toString());
    }
}
