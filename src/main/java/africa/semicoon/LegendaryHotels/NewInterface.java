package africa.semicoon.LegendaryHotels;

import africa.semicoon.LegendaryHotels.controller.ControllerForAdmins;
import africa.semicoon.LegendaryHotels.controller.ControllerForCustomers;
import africa.semicoon.LegendaryHotels.controller.ControllerForReservations;
import africa.semicoon.LegendaryHotels.dto.requests.RequestsForCustomers;
import africa.semicoon.LegendaryHotels.dto.requests.RequestsForReservations;
import africa.semicoon.LegendaryHotels.dto.response.ResponseForReservation;
import africa.semicoon.LegendaryHotels.dto.response.ResponseToFindByEmail;
import africa.semicoon.LegendaryHotels.models.RoomType;
import africa.semicoon.LegendaryHotels.utils.Display;

import java.util.Objects;

public class NewInterface {
    private static final ControllerForReservations reservationController = new ControllerForReservations();
    private static final ControllerForCustomers customerController = new ControllerForCustomers();

    private static final ControllerForAdmins adminController = new ControllerForAdmins();

    public static void mainMenu() {
        Display.message("""
                1. Find and reserve a Room.
                2. See my reservations.
                3. Create an account.
                4. Admin.
                5. Exit
                """);
        int userInput = Display.intInput("");

        switch (userInput){
            case 1 -> findAndReserveARoom();
            case 2 -> printReservations();
            case 3 -> createAccountAndReserveRoom();
            case 4 -> admin();
            case 5 -> System.exit(10);
        }
    }


    public static void main(String[] args) {
        mainMenu();
    }

    public static void findAndReserveARoom() {
        Display.message("\n1. Sign in || 2. Sign up");
        int userInput = Display.intInput("");
        switch (userInput){
            case 1 -> ReserveARoom();
            case 2 -> createAccountAndReserveRoom();
        }
    }
    public static void createAccountAndReserveRoom(){
        createAccount();
        ReserveARoom();
    }
    public static void ReserveARoom(){
        ResponseToFindByEmail responseToFindByEmail = signIn();

        RequestsForReservations requestsForReservations = new RequestsForReservations();

        Display.message("""
                Room Type
                1. Single Room || 2. Double Room
                """);
        int roomType = Display.intInput("");

        Display.message(reservationController.findARoom(roomType));

        String userResponse = Display.StringInput("\nDo you wish to proceed? ");
        int amount = 0;
        int roomNumber = 0;
        RoomType type = null;

        String checkInDate = null;
        String checkInMonth = null;
        String checkInYear = null;
        String checkOutDate = null;
        String checkOutMonth = null;
        String checkOutYear = null;


        if(userResponse.equalsIgnoreCase("Yes")){
            Display.message("Please fill the following fields.\n");
            roomNumber = Display.intInput("Enter Room Number: ");
            if(roomType == 1){
                type = RoomType.SINGLE;
                amount =  Display.intInput("Enter amount($20): ");
                //Display.message(reservationController.reserveARoom(requestsForReservations));
            } else {
                type = RoomType.DOUBLE;
                amount = Display.intInput("Enter amount($50): ");
                //Display.message(reservationController.reserveARoom(requestsForReservations));
            }
            checkInDate = Display.StringInput("Check In date: ");
            checkInMonth = Display.StringInput("Check In month(in digits): ");
            checkInYear = Display.StringInput("Check In year: ");
            checkOutDate = Display.StringInput("Check out date: ");
            checkOutMonth = Display.StringInput("Check out month(in digits): ");
            checkOutYear = Display.StringInput("Check out year: \n");
        }

        String firstName = responseToFindByEmail.getFirstName();
        String lastName = responseToFindByEmail.getLastName();
        String email = responseToFindByEmail.getEmail();



        requestsForReservations.setFirstName(firstName);
        requestsForReservations.setLastName(lastName);
        requestsForReservations.setEmail(email);
        requestsForReservations.setRoomType(type);
        requestsForReservations.setRoomChoice(roomNumber);
        requestsForReservations.setAmount(amount);
        requestsForReservations.setCheckInDate(checkInDate);
        requestsForReservations.setCheckInMonth(checkInMonth);
        requestsForReservations.setCheckInYear(checkInYear);
        requestsForReservations.setCheckOutDate(checkOutDate);
        requestsForReservations.setCheckOutMonth(checkOutMonth);
        requestsForReservations.setCheckOutYear(checkOutYear);

        Display.message(reservationController.reserveARoom(requestsForReservations)+"\n");

        mainMenu();
    }

    private static void admin() {
        Display.message("""
                1. See all Customers.
                2. See all Rooms.
                3. See all Reservations.
                4. Back to Main Menu
                """);

        int userInput = Display.intInput("");
        switch (userInput){
            case 1 -> adminController.seeAllCustomers();
            case 2 -> adminController.showAllRooms();
            case 3 -> adminController.seeAllReservations();
            case 4 -> mainMenu();
        }
    }

    public static ResponseForReservation printReservations() {
        RequestsForReservations requestsForReservations = new RequestsForReservations();

        String email = Display.StringInput("Enter your email: ");
        requestsForReservations.setEmail(email);

        return adminController.getCustomerReservation(requestsForReservations);
    }

    private static ResponseToFindByEmail signIn(){
        String email = Display.StringInput("Enter email to sign in: ");
        String password = Display.StringInput("Enter password to sign in: ");
        customerController.verifyPassword(password);
        //passwordIsCorrect(password);

        RequestsForCustomers requestsForCustomers = new RequestsForCustomers();
        requestsForCustomers.setEmail(email);

        return customerController.findCustomerByEmail(requestsForCustomers);
    }

    private static void createAccount(){
        String firstName = Display.StringInput("First Name: ");
        String lastName = Display.StringInput("Last Name: ");
        String email = Display.StringInput("Email address: ");
        String password = Display.StringInput("Set password: ");
        String reconfirmPassword = reconfirmPassword(password);

        RequestsForCustomers requestsForCustomers = new RequestsForCustomers();
        requestsForCustomers.setFirstName(firstName);
        requestsForCustomers.setLastName(lastName);
        requestsForCustomers.setEmail(email);
        requestsForCustomers.setPassword(reconfirmPassword);

        Display.message(customerController.saveCustomer(requestsForCustomers).toString());
    }

    private static String reconfirmPassword(String password){
        String reconfirmPassword = Display.StringInput("Reconfirm your password: ");

        int trial = 2;
        while (!Objects.equals(password,reconfirmPassword)){
            if(trial == 0){
                Display.message("\nLimited exceeded. Try again later.");
                System.exit(14);
            }

            if(trial == 1){
                Display.errorMessage("Password does not match. You have "+trial+" trial left.");
            } else {
                Display.errorMessage("Password does not match. You have "+trial+" trials left.");
            }
            reconfirmPassword = Display.StringInput("\nReconfirm your password: ");
            trial--;
        }

        return reconfirmPassword;
    }
    private static String passwordIsCorrect(String password){

        int trial = 2;
        while (!customerController.verifyPassword(password)){
            if(trial == 0){
                Display.message("\nLimited exceeded. Try again later.");
                System.exit(16);
            }if(trial == 1){
                Display.errorMessage("Wrong Password. You have "+trial+" trial left.");
            } else {
                Display.errorMessage("Wrong Password. You have "+trial+" trials left.");
            }
            password = Display.StringInput("\nEnter password: ");
            trial--;
        }
        return password;
    }
}
