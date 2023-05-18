package africa.semicoon.LegendaryHotels.repositories;

import africa.semicoon.LegendaryHotels.models.Admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class RepositoryForAdmins implements IAdminRepository{
    List<Admin> adminRepository = new ArrayList<>();

    @Override
    public Admin saveAdmin(Admin newAdmin) {
        newAdmin.setAdminLoginCode(generateAdminCode());
        adminRepository.add(newAdmin);
        return newAdmin;
    }

    @Override
    public Admin findAdminByEmail(String email) {
        for(Admin foundAdmin : adminRepository){
            if(Objects.equals(foundAdmin.getEmail(),email)) return foundAdmin;
        }
        return null;
    }

    @Override
    public List<Admin> getAllAdmins() {
        return adminRepository;
    }

    @Override
    public void deleteByEmail(String email) {
        Admin foundAdmin = findAdminByEmail(email);
        if(foundAdmin != null) adminRepository.remove(foundAdmin);
    }

    private int generateAdminCode(){
        Random random = new Random();
        int code = 0;
        while(code < 1000){
            code = random.nextInt(9999);
        }
        return code;
    }
}
