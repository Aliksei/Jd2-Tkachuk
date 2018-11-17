package com.itacademy.service.resident;

import com.itacademy.dao.resident.ResidentDao;
import com.itacademy.dao.resident.ResidentDaoImpl;
import com.itacademy.dto.ResidentDto;
import com.itacademy.dto.ResidentFilterDto;
import com.itacademy.enteties.hotel.Apartment;
import com.itacademy.enteties.role.Resident;
import java.util.ArrayList;
import java.util.List;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ResidentServiceImpl implements ResidentService{

    private static final ResidentService INSTANCE = new ResidentServiceImpl();

    private ResidentDao residentDao = ResidentDaoImpl.getInstance();

    @Override
    public List<ResidentDto> getByFilter(ResidentFilterDto filter){
        List<Resident> dao = residentDao.getByFilter(filter.getLimit(),
                filter.getOffset(), filter.getPredicates());

        List<ResidentDto> dto = new ArrayList<>();

        for (Resident next : dao) {

            Apartment apartment = next.getApartment();

            ResidentDto residentDto = ResidentDto.builder()
                    .id(next.getId())
                    .firstName(next.getFirst_name())
                    .secondName(next.getSecond_name())
                    .city(next.getCity().getName())
                    .country(next.getCountry().getName())
                    .gender(next.getGender().toString())
                    .build();

            if (apartment != null) {
                residentDto.setRoomNumber(String.valueOf(apartment.getId()));
            } else {
                residentDto.setRoomNumber("N/A");
            }

            dto.add(residentDto);
        }
        return dto;
    }

    @Override
    public Long getCount() {
        return residentDao.getCount();
    }

    public static ResidentService getInstance() {
        return INSTANCE;
    }
}
