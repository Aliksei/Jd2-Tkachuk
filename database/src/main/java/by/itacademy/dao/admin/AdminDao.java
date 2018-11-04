package by.itacademy.dao.admin;

import by.itacademy.dao.BaseDao;
import by.itacademy.enteties.role.Admin;
import java.util.List;

public interface AdminDao extends BaseDao {
    List<Admin> getAllAdmins();

    Admin getAdminById(Long id);

    Long createAdmin(Admin admin);

    void deleteAdmin(Long id);

    void updateAdmin(Admin admin);
}
