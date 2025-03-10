package com.megacity.dao;

import com.megacity.model.Admin;
import java.util.List;

public interface AdminDAO {
    void addAdmin(Admin admin); // Add new admin
    List<Admin> getAllAdmins(); // Fetch all admins
    Admin getAdminById(int adminId); // Fetch admin by ID
    void deleteAdmin(int adminId); // Delete admin by ID
    void updateAdmin(Admin admin); // Update admin details
}
