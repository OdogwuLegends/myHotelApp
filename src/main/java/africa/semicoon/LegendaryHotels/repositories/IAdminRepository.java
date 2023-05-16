package africa.semicoon.LegendaryHotels.repositories;

import africa.semicoon.LegendaryHotels.models.Admin;

import java.util.List;

public interface IAdminRepository {
    Admin saveAdmin(Admin newAdmin);
    Admin findAdminByEmail(String email);
    List<Admin> getAllAdmins();
    void deleteByEmail(String email);
}
