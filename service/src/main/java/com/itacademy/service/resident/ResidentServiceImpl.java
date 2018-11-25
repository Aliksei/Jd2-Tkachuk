package com.itacademy.service.resident;

import com.itacademy.database.dao.resident.ResidentRepository;
import com.itacademy.dto.ResidentDto;
import com.itacademy.dto.ResidentFilterDto;
import com.itacademy.database.entity.hotel.Apartment;
import com.itacademy.database.entity.role.Resident;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ResidentServiceImpl implements ResidentService{

    @Autowired
    private ResidentRepository residentRepository;

    @Override
    public List<ResidentDto> getByFilter(ResidentFilterDto filter){
        List<Resident> dao = residentRepository.getByFilter(filter.getLimit(),
                filter.getOffset(), filter.getPredicates());

        List<ResidentDto> dto = new ArrayList<>();

        for (Resident next : dao) {

            Apartment apartment = next.getApartment();

            ResidentDto residentDto = ResidentDto.builder()
                    .id(next.getId())
                    .firstName(next.getFirstName())
                    .secondName(next.getSecondName())
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
        return residentRepository.count();
    }

}
