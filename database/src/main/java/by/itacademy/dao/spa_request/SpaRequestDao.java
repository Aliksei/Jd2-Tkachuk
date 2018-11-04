package by.itacademy.dao.spa_request;

import by.itacademy.dao.BaseDao;
import by.itacademy.enteties.hotel.SpaRequest;
import java.util.List;

public interface SpaRequestDao extends BaseDao {
    List<SpaRequest> getAllSpaRequests();

    SpaRequest getSpaRequestById(Long id);

    Long createSpaRequest(SpaRequest spaRequest);

    void deleteSpaReqeust(Long id);

    void updateSpaRequest(SpaRequest spaRequest);
}
