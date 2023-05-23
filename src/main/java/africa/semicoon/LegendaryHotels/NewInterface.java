package africa.semicoon.LegendaryHotels;

import africa.semicoon.LegendaryHotels.controller.ControllerForAdmins;
import africa.semicoon.LegendaryHotels.controller.ControllerForCustomers;
import africa.semicoon.LegendaryHotels.controller.ControllerForReservations;
import africa.semicoon.LegendaryHotels.dto.requests.RequestsForCustomers;
import africa.semicoon.LegendaryHotels.dto.requests.RequestsForReservations;
import africa.semicoon.LegendaryHotels.dto.response.ResponseForReservation;
import africa.semicoon.LegendaryHotels.dto.response.ResponseToFindByEmail;
import africa.semicoon.LegendaryHotels.models.Customer;
import africa.semicoon.LegendaryHotels.models.Room;
import africa.semicoon.LegendaryHotels.models.RoomType;
import africa.semicoon.LegendaryHotels.utils.AppUtils;
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
        //ResponseToFindByEmail responseToFindByEmail = signIn();

        RequestsForReservations requestsForReservations = new RequestsForReservations();

        Display.message("""
                Room Type
                1. Single Room || 2. Double Room
                """);
        int roomType = Display.intInput("");

        Display.message(reservationController.findARoom(roomType).getMessage());

        String userResponse = Display.StringInput("\nDo you wish to proceed? Enter Yes/No: ");
        int amount = 0;
        int roomNumber = 0;
        RoomType type = null;


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
        }


        int checkInDate = Display.intInput("Check In date: ");
        int checkInMonth = Display.intInput("Check In month(in digits): ");
        int checkInYear = Display.intInput("Check In year: ");
        int checkOutDate = Display.intInput("Check out date: ");
        int checkOutMonth = Display.intInput("Check out month(in digits): ");
        int checkOutYear = Display.intInput("Check out year: ");
//
//        String firstName = responseToFindByEmail.getFirstName();
//        String lastName = responseToFindByEmail.getLastName();
//        String email = responseToFindByEmail.getEmail();


        String firstName = Display.StringInput("Enter First Name: ");
        String lastName = Display.StringInput("Enter Last name: ");
        String email = Display.StringInput("Enter email: ");
        String password = Display.StringInput("Enter password: ");


        Customer newCustomer = new Customer();
        newCustomer.setFirstName(firstName);
        newCustomer.setLastName(lastName);
        newCustomer.setEmail(email);
        newCustomer.setPassword(password);
        requestsForReservations.setCustomer(newCustomer);

        Room room = new Room();
        room.setRoomNumber(roomNumber);
        room.setPrice(amount);
        room.setRoomType(type);
        requestsForReservations.setRoom(room);


        requestsForReservations.setCheckInDate(checkInDate);
        requestsForReservations.setCheckInMonth(checkInMonth);
        requestsForReservations.setCheckInYear(checkInYear);
        requestsForReservations.setCheckOutDate(checkOutDate);
        requestsForReservations.setCheckOutMonth(checkOutMonth);
        requestsForReservations.setCheckOutYear(checkOutYear);


        Display.message("\n"+reservationController.reserveARoom(requestsForReservations).getMessage()+"\n");

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

    public static void printReservations() {
        RequestsForReservations requestsForReservations = new RequestsForReservations();
        Customer customer = new Customer();

        String email = Display.StringInput("Enter your email: ");
        String FirstName = Display.StringInput("Enter First Name: ");
        String lastName = Display.StringInput("Enter Last Name: ");
        customer.setEmail(email);
        customer.setFirstName(FirstName);
        customer.setLastName(lastName);
        requestsForReservations.setCustomer(customer);

        //Display.message(adminController.getCustomerReservation(requestsForReservations).getMessage());
        Display.message(adminController.findReservationByEmail(email).toString());
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
        String password = Display.StringInput("Set password: ");
        String reconfirmPassword = reconfirmPassword(password);
        String email = Display.StringInput("Email address: ");
        while(!AppUtils.emailIsCorrect(email)){
            Display.message("Wrong email address.");
            email= Display.StringInput("Enter correct Email address : ");
        }
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
