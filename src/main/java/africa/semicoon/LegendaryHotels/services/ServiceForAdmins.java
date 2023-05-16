package africa.semicoon.LegendaryHotels.services;

import africa.semicoon.LegendaryHotels.dto.requests.AdminRequest;
import africa.semicoon.LegendaryHotels.dto.response.AdminRegistrationResponse;
import africa.semicoon.LegendaryHotels.dto.response.DeleteResponse;
import africa.semicoon.LegendaryHotels.dto.response.GetEmailResponse;
import africa.semicoon.LegendaryHotels.models.Admin;
import africa.semicoon.LegendaryHotels.repositories.IAdminRepository;
import africa.semicoon.LegendaryHotels.repositories.RepositoryForAdmin;
import africa.semicoon.LegendaryHotels.utils.Map;

import java.util.List;

public class ServiceForAdmins implements IAdminService{

    IAdminRepository adminRepository = new RepositoryForAdmin();

    @Override
    public AdminRegistrationResponse registerAdmin(AdminRequest adminRequest) {
        Admin newAdmin = Map.map(adminRequest);
        adminRepository.saveAdmin(newAdmin);
        return new AdminRegistrationResponse();
    }

    @Override
    public GetEmailResponse findAdminByEmail(String email) {
        GetEmailResponse getEmailResponse = new GetEmailResponse();
        Admin foundAmin = adminRepository.findAdminByEmail(email);
        getEmailResponse.setFirstName(foundAmin.getFirstName());
        getEmailResponse.setLastName(foundAmin.getLastName());
        getEmailResponse.setEmail(foundAmin.getEmail());
        return getEmailResponse;
    }

    @Override
    public List<Admin> getAllAdmins() {
        return adminRepository.getAllAdmins();
    }

    @Override
    public DeleteResponse deleteByEmail(String email) {
        adminRepository.deleteByEmail(email);
        DeleteResponse deleteResponse = new DeleteResponse();
        deleteResponse.setMessage("Admin deleted successfully.");
        return deleteResponse;
    }
}
