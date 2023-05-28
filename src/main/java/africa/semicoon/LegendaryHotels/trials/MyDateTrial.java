package africa.semicoon.LegendaryHotels.trials;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class MyDateTrial {
    public static void main(String[] args) {
        LocalDate enteredDate = getDateFromUser();
        LocalDate newDate = enteredDate.plusDays(3);
        System.out.println("Entered date: " + enteredDate);
        System.out.println("newDate: " + newDate);
    }

    public static LocalDate getDateFromUser() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a date (DD/MM/YYYY): ");
        String userInput = scanner.nextLine();

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate parsedDate = LocalDate.parse(userInput, dateFormatter);

        return parsedDate;
    }
}
