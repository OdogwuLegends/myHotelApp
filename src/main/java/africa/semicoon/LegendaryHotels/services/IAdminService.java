package africa.semicoon.LegendaryHotels.services;

import africa.semicoon.LegendaryHotels.dto.requests.RequestsForAdmins;
import africa.semicoon.LegendaryHotels.dto.response.ResponseForAdminRegistration;
import africa.semicoon.LegendaryHotels.dto.response.ResponseForDelete;
import africa.semicoon.LegendaryHotels.dto.response.ResponseToFindByEmail;
import africa.semicoon.LegendaryHotels.exceptions.EntityDoesNotExistException;
import africa.semicoon.LegendaryHotels.exceptions.InvalidEmailException;
import africa.semicoon.LegendaryHotels.models.Admin;

import java.util.List;

public interface IAdminService {
    ResponseForAdminRegistration registerAdmin(RequestsForAdmins requestsForAdmins);
    ResponseToFindByEmail findAdminByEmail(RequestsForAdmins requestsForAdmins) throws InvalidEmailException, EntityDoesNotExistException;
    List<Admin> getAllAdmins();
    ResponseForDelete deleteByEmail(RequestsForAdmins requestsForAdmins) throws InvalidEmailException, EntityDoesNotExistException;
}
