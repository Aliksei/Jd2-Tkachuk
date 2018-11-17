package com.itacademy.dto;

import com.itacademy.enteties.enum_.Gender;
import com.itacademy.enteties.role.QResident;
import com.querydsl.core.types.Predicate;
import java.util.ArrayList;
import java.util.List;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ResidentFilterDto {

    private QResident resident = QResident.resident;
    private Predicate firstNamePredicate;
    private Predicate secondNamePredicate;
    private Predicate genderPredicate;
    private Integer offset;
    private Integer limit;
    private List<Predicate> predicates = new ArrayList<>();

    public Predicate getFirstNamePredicate() {
        return firstNamePredicate;
    }

    public Predicate getSecondNamePredicate() {
        return secondNamePredicate;
    }

    public Predicate getGenderPredicate() {
        return genderPredicate;
    }

    public void setFirstNamePredicate(String firstName){
        firstNamePredicate = resident.first_name.eq(firstName);
        predicates.add(firstNamePredicate);
    }

    public void setSecondNamePredicate(String secondName){
        secondNamePredicate = resident.second_name.eq(secondName);
        predicates.add(secondNamePredicate);
    }

    public void setGenderPredicate(Gender gender){
        genderPredicate = resident.gender.eq(gender);
        predicates.add(genderPredicate);
    }

    public Integer getOffset() {
        return offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Predicate[] getPredicates() {
        return predicates.toArray(new Predicate[]{});
    }
}
