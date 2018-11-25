package com.itacademy.dto;

import com.itacademy.database.entity.enum_.Gender;
import com.itacademy.database.entity.role.QResident;
import com.querydsl.core.types.Predicate;
import java.util.ArrayList;
import java.util.List;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ResidentFilterDto {

    private QResident resident = QResident.resident;
    private String firstName;
    private String secondName;
    private String gender;
    private Integer offset;
    private Integer limit;

    public void setLimit(Integer limit) {
        if (limit != null && limit > 0){
            this.limit = limit;
        }else {
            this.limit = 100;
        }
    }

    public void setOffset(Integer offset) {
        if (offset != null && offset > 0){
            this.offset = offset;
        }else {
            this.offset = 0;
        }
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Predicate[] getPredicates() {
        List<Predicate> pr = new ArrayList<>();
        if (firstName != null && !firstName.isEmpty()) {
            pr.add(resident.firstName.eq(firstName));
        }
        if (secondName != null && !secondName.isEmpty()) {
            pr.add(resident.secondName.eq(secondName));
        }
        if (gender != null && !gender.isEmpty()) {
            pr.add(resident.gender.eq(Gender.valueOf(gender)));
        }
        return pr.toArray(new Predicate[]{});
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getGender() {
        return gender;
    }

    public Integer getOffset() {
        return offset;
    }

    public Integer getLimit() {
        return limit;
    }

}
