package africa.semicoon.LegendaryHotels.controller;

import africa.semicoon.LegendaryHotels.dto.requests.RequestsForAdmins;
import africa.semicoon.LegendaryHotels.dto.requests.RequestsForReservations;
import africa.semicoon.LegendaryHotels.dto.response.ResponseForAdminRegistration;
import africa.semicoon.LegendaryHotels.dto.response.ResponseForDelete;
import africa.semicoon.LegendaryHotels.dto.response.ResponseForReservation;
import africa.semicoon.LegendaryHotels.dto.response.ResponseToFindByEmail;
import africa.semicoon.LegendaryHotels.exceptions.EntityDoesNotExistException;
import africa.semicoon.LegendaryHotels.exceptions.InvalidEmailException;
import africa.semicoon.LegendaryHotels.models.Customer;
import africa.semicoon.LegendaryHotels.models.Reservation;
import africa.semicoon.LegendaryHotels.services.IAdminService;
import africa.semicoon.LegendaryHotels.services.ServiceForAdmins;

import java.util.List;

public class ControllerForAdmins {
    private final IAdminService adminService = new ServiceForAdmins();
    private final ControllerForReservations controllerForReservations = new ControllerForReservations();

    private final ControllerForCustomers controllerForCustomers = new ControllerForCustomers();

    public ResponseForAdminRegistration registerAdmin(RequestsForAdmins requestsForAdmins){
        try {
            return adminService.registerAdmin(requestsForAdmins);
        } catch (InvalidEmailException ex){
            System.err.println(ex.getMessage());
        }
        return new ResponseForAdminRegistration();
    }

    public ResponseToFindByEmail findAdminByEmail(RequestsForAdmins requestsForAdmins){
//        try {
//            return adminService.findAdminByEmail(requestsForAdmins);
//        } catch (InvalidEmailException | EntityDoesNotExistException ex){
//            System.err.println(ex.getMessage());
//        }
//        return new ResponseToFindByEmail();
        return adminService.findAdminByEmail(requestsForAdmins);
    }

    public ResponseForDelete deleteAdminByEmail(RequestsForAdmins requestsForAdmins){
        try {
            adminService.deleteByEmail(requestsForAdmins);
        } catch (InvalidEmailException | EntityDoesNotExistException ex){
            System.err.println(ex.getMessage());
        }
        return new ResponseForDelete();
    }

    public boolean verifyAdminCode(int code) {
        return adminService.verifyAdminCode(code);
    }

    public boolean verifyAdminPassword(String password){
        return adminService.verifyAdminPassword(password);
    }
    public boolean verifyAdminByEmail(String email){
        return adminService.verifyAdminByEmail(email);
    }

    public List<Customer> seeAllCustomers() {
        return controllerForCustomers.getAllCustomers();
    }
    public List<Reservation> seeAllReservations() {
        return controllerForReservations.printReservations();
    }
    public List<Integer> showAllRooms() {
        return controllerForReservations.showAllRooms();
    }
}
