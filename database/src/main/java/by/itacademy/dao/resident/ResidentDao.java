package by.itacademy.dao.resident;

import by.itacademy.dao.BaseDao;
import by.itacademy.enteties.role.Resident;
import java.util.List;

public interface ResidentDao extends BaseDao {
    List<Resident> getAllResidents();

    Resident getResidentById(Long id);

    Long createResident(Resident resident);

    void deleteResident(Long id);

    void updateResident(Resident resident);
}
