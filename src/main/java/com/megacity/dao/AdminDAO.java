package com.megacity.dao;

import com.megacity.model.Admin;
import java.util.List;

public interface AdminDAO {
    void addAdmin(Admin admin);
    List<Admin> getAllAdmins();
    Admin getAdminById(int adminId);
    void deleteAdmin(int adminId);
    void updateAdmin(Admin admin);
}
