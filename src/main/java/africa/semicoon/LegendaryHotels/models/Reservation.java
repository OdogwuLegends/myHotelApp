package africa.semicoon.LegendaryHotels.models;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class Reservation {
    private Customer customer;
    private Room room;
//    private Date checkIn;
//    private Date checkOut;
    private LocalDate checkIn;
    private LocalDate checkOut;

}
