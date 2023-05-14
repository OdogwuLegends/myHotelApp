package africa.semicoon.AprilHotels.exceptions;

public class InvalidRoomNumberException extends IllegalArgumentException{

    public InvalidRoomNumberException(String message){
        super(message);
    }
}
