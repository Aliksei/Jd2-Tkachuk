package com.itacademy.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResidentDto {

    private Long id;

    private String firstName;

    private String secondName;

    private String gender;

    private String city;

    private String country;

    private String roomNumber;


}
