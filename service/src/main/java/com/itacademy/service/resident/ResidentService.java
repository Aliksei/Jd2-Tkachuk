package com.itacademy.service.resident;

import com.itacademy.dto.ResidentDto;
import com.itacademy.dto.ResidentFilterDto;
import java.util.List;

public interface ResidentService {

    List<ResidentDto> getByFilter(ResidentFilterDto filter);

    Long getCount();
}
