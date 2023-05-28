package africa.semicoon.LegendaryHotels;

import africa.semicoon.LegendaryHotels.controller.ControllerForAdmins;
import africa.semicoon.LegendaryHotels.controller.ControllerForCustomers;
import africa.semicoon.LegendaryHotels.controller.ControllerForReservations;
import africa.semicoon.LegendaryHotels.dto.requests.RequestsForAdmins;
import africa.semicoon.LegendaryHotels.dto.requests.RequestsForCustomers;
import africa.semicoon.LegendaryHotels.dto.requests.RequestsForReservations;
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


    public static void main(String[] args) {
        mainMenu();
    }

    private static void mainMenu() {
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
            case 3 -> createAccountForCustomerAndReserveRoom();
            case 4 -> adminLogin();
            case 5 -> System.exit(10);
        }
    }

    private static void findAndReserveARoom() {
        Display.message("\n1. Sign in || 2. Sign up");
        int userInput = Display.intInput("");
        switch (userInput){
            case 1 -> ReserveARoom();
            case 2 -> createAccountForCustomerAndReserveRoom();
        }
    }

    private static void printReservations() {
        String email = Display.StringInput("Enter your email: ");
        Display.message(reservationController.findReservationByEmail(email).toString());
        mainMenu();
    }

    private static void createAccountForCustomerAndReserveRoom(){
        createAccountForCustomer();
        ReserveARoom();
    }

    private static void adminLogin(){
        Display.message("\n1. Sign in || 2. Sign up");
        int userInput = Display.intInput("");
        switch (userInput){
            case 1 -> adminPage();
            case 2 -> createAccountForAdmin();
        }
    }
    private static void ReserveARoom(){
        Display.message("\nPlease sign in below to continue; \n");
        ResponseToFindByEmail responseToFindByEmail = signInForCustomer();

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
            } else {
                type = RoomType.DOUBLE;
                amount = Display.intInput("Enter amount($50): ");
            }

            String checkIn = Display.StringInput("Enter Check in date(DD/MM/YYYY): ");
            while (checkIn.length() != 10){
                Display.message("\nWrong input. Please use the correct format.");
                checkIn = Display.StringInput("Enter Check in date(DD/MM/YYYY): ");
            }
            String checkOut = Display.StringInput("Enter Check out date(DD/MM/YYYY): ");
            while (checkOut.length() != 10){
                Display.message("\nWrong input. Please use the correct format.");
                checkOut = Display.StringInput("Enter Check in date(DD/MM/YYYY): ");
            }

            String firstName = responseToFindByEmail.getFirstName();
            String lastName = responseToFindByEmail.getLastName();
            String email = responseToFindByEmail.getEmail();
            String password = responseToFindByEmail.getPassword();

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

            requestsForReservations.setCheckIn(checkIn);
            requestsForReservations.setCheckOut(checkOut);


            Display.message("\n"+reservationController.reserveARoom(requestsForReservations).getMessage()+"\n");

            mainMenu();
        } else {
            System.out.println();
            mainMenu();
        }
    }

    private static void adminPage() {
        Display.message("\nPlease sign in below to continue; \n");
        signInForAdmin();
        verifyAdminLoginCode();
        Display.message("""
                \n1. See all Customers.
                2. See all Rooms.
                3. See all Reservations.
                4. Back to Main Menu
                """);

        int userInput = Display.intInput("");
        switch (userInput){
            case 1 -> Display.message(adminController.seeAllCustomers().toString());
            case 2 -> Display.message(adminController.showAllRooms().toString());
            case 3 -> Display.message(adminController.seeAllReservations().toString());
            case 4 -> mainMenu();
        }
        System.out.println();
        mainMenu();
    }

    private static ResponseToFindByEmail signInForCustomer(){
        String email = validEmail();
        if(!customerController.verifyCustomerByEmail(email)){
            System.out.println("Customer does not exist.\n");
            mainMenu();
        }
        verifyCustomerPassword();

        RequestsForCustomers requestsForCustomers = new RequestsForCustomers();
        requestsForCustomers.setEmail(email);

        return customerController.findCustomerByEmail(requestsForCustomers);
    }

    private static void createAccountForCustomer(){
        String firstName = Display.StringInput("First Name: ");
        String lastName = Display.StringInput("Last Name: ");
        String email = validEmail();
        String password = Display.StringInput("Set password: ");
        String reconfirmPassword = reconfirmPassword(password);

        RequestsForCustomers requestsForCustomers = new RequestsForCustomers();
        requestsForCustomers.setFirstName(firstName);
        requestsForCustomers.setLastName(lastName);
        requestsForCustomers.setEmail(email);
        requestsForCustomers.setPassword(reconfirmPassword);

        Display.message(customerController.saveCustomer(requestsForCustomers).toString());
    }

    private static void createAccountForAdmin(){
        String firstName = Display.StringInput("First Name: ");
        String lastName = Display.StringInput("Last Name: ");
        String email = validEmail();
        String password = Display.StringInput("Set password: ");
        String reconfirmPassword = reconfirmPassword(password);

        RequestsForAdmins requestsForAdmins = new RequestsForAdmins();
        requestsForAdmins.setFirstName(firstName);
        requestsForAdmins.setLastName(lastName);
        requestsForAdmins.setEmail(email);
        requestsForAdmins.setPassword(reconfirmPassword);

        Display.message(adminController.registerAdmin(requestsForAdmins).toString());
        adminPage();
    }

    private static ResponseToFindByEmail signInForAdmin(){
        String email = validEmail();
        if(!adminController.verifyAdminByEmail(email)){
            System.out.println("Admin does not exist.\n");
            mainMenu();
        }
        verifyAdminPassword();

        RequestsForAdmins requestsForAdmins = new RequestsForAdmins();
        requestsForAdmins.setEmail(email);

        return adminController.findAdminByEmail(requestsForAdmins);

    }

    private static String validEmail() {
        String email = Display.StringInput("Enter email address: ");
        int trial = 2;
        while(!AppUtils.emailIsCorrect(email)){
            if(trial == 0){
                Display.message("\nLimited exceeded. Try again later.");
                System.exit(16);
            }if(trial == 1){
                Display.errorMessage("Incorrect email format. You have "+trial+" trial left.");
                email = Display.StringInput("\nEnter email: ");
            } else {
                Display.errorMessage("Incorrect email format. You have "+trial+" trials left.");
                email = Display.StringInput("\nEnter email: ");
            }

            trial--;
        }
        return email;
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
    private static String verifyCustomerPassword(){
        String password = Display.StringInput("Enter password: ");

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

    private static String verifyAdminPassword(){
        String password = Display.StringInput("Enter password: ");

        int trial = 2;
        while (!adminController.verifyAdminPassword(password)){
            if(trial == 0){
                Display.message("\nLimited exceeded. Try again later.");
                System.exit(17);
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
    private static void verifyAdminLoginCode() {
        int code  = Display.intInput("Enter your Admin Login Code: ");
        int trial = 2;
        while (!adminController.verifyAdminCode(code)){
            if(trial == 0){
                Display.message("\nLimited exceeded. Try again later.");
                System.exit(19);
            }if(trial == 1){
                Display.errorMessage("Wrong Code. You have "+trial+" trial left.");
            } else {
                Display.errorMessage("Wrong Code. You have "+trial+" trials left.");
            }
            code = Display.intInput("\nEnter code: ");
            trial--;

        }
    }

}
