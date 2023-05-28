package africa.semicoon.LegendaryHotels.services;

import africa.semicoon.LegendaryHotels.dto.requests.RequestsForAdmins;
import africa.semicoon.LegendaryHotels.dto.response.ResponseForAdminRegistration;
import africa.semicoon.LegendaryHotels.dto.response.ResponseForDelete;
import africa.semicoon.LegendaryHotels.dto.response.ResponseToFindByEmail;
import africa.semicoon.LegendaryHotels.exceptions.EntityDoesNotExistException;
import africa.semicoon.LegendaryHotels.exceptions.InvalidEmailException;
import africa.semicoon.LegendaryHotels.models.Admin;
import africa.semicoon.LegendaryHotels.repositories.IAdminRepository;
import africa.semicoon.LegendaryHotels.repositories.RepositoryForAdmins;
import africa.semicoon.LegendaryHotels.utils.Map;

import java.util.List;

import static africa.semicoon.LegendaryHotels.utils.AppUtils.emailIsCorrect;

public class ServiceForAdmins implements IAdminService{

    IAdminRepository adminRepository = new RepositoryForAdmins();

    @Override
    public ResponseForAdminRegistration registerAdmin(RequestsForAdmins requestsForAdmins)  throws InvalidEmailException  {
        if(!emailIsCorrect(requestsForAdmins.getEmail())){
            throw new InvalidEmailException("Invalid email.");
        }
        Admin newAdmin = Map.requestToAdmin(requestsForAdmins);
        Admin savedAdmin = adminRepository.saveAdmin(newAdmin);
        return Map.adminToRegistrationResponse(savedAdmin);
    }

    @Override
    public ResponseToFindByEmail findAdminByEmail(RequestsForAdmins requestsForAdmins) {
        String email = requestsForAdmins.getEmail();
//        if(!emailIsCorrect(email)){
//            throw new InvalidEmailException("Invalid email.");
//        }

        ResponseToFindByEmail responseToFindByEmail;
        Admin foundAmin = adminRepository.findAdminByEmail(email);
//        if(foundAmin == null){
//            throw new EntityDoesNotExistException("Admin does not exist");
//        } else {
////            getEmailResponse.setFirstName(foundAmin.getFirstName());
////            getEmailResponse.setLastName(foundAmin.getLastName());
////            getEmailResponse.setEmail(foundAmin.getEmail());
//        }
        responseToFindByEmail = Map.adminToEmailResponse(foundAmin);
        return responseToFindByEmail;
    }

    @Override
    public boolean verifyAdminCode(int code) {
        return adminRepository.verifyAdminCode(code);
    }

    @Override
    public boolean verifyAdminPassword(String password) {
        return adminRepository.verifyAdminPassword(password);
    }

    @Override
    public boolean verifyAdminByEmail(String email) {
        return adminRepository.verifyAdminByEmail(email);
    }

    @Override
    public List<Admin> getAllAdmins() {
        return adminRepository.getAllAdmins();
    }

    @Override
    public ResponseForDelete deleteByEmail(RequestsForAdmins requestsForAdmins) throws InvalidEmailException, EntityDoesNotExistException {
        if(!emailIsCorrect(requestsForAdmins.getEmail())){
            throw new InvalidEmailException("Invalid email.");
        }
        String email = requestsForAdmins.getEmail();

        if(email == null){
            throw new EntityDoesNotExistException("Admin does not exist");
        } else{
            adminRepository.deleteByEmail(email);
        }
        ResponseForDelete responseForDelete = new ResponseForDelete();
        responseForDelete.setMessage("Admin deleted successfully.");
        return responseForDelete;
    }
}
