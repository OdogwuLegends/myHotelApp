package africa.semicoon.LegendaryHotels.services;

import africa.semicoon.LegendaryHotels.dto.requests.AdminRequest;
import africa.semicoon.LegendaryHotels.dto.response.AdminRegistrationResponse;
import africa.semicoon.LegendaryHotels.dto.response.DeleteResponse;
import africa.semicoon.LegendaryHotels.dto.response.GetEmailResponse;
import africa.semicoon.LegendaryHotels.models.Admin;

import java.util.List;

public interface IAdminService {
    AdminRegistrationResponse registerAdmin(AdminRequest adminRequest);
    GetEmailResponse findAdminByEmail(String email);
    List<Admin> getAllAdmins();
    DeleteResponse deleteByEmail(String email);
}
