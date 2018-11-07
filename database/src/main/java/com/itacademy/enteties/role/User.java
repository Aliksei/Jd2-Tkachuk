package com.itacademy.enteties.role;

import com.itacademy.enteties.BaseEntity;
import com.itacademy.enteties.embedded.Credentials;
import com.itacademy.enteties.enum_.Gender;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(schema = "hotel_schema", name = "user")
public abstract class User extends BaseEntity<Long> {

    @Embedded
    private Credentials credentials;

    @Column(name = "first_name", nullable = false)
    private String first_name;

    @Column(name = "second_name", nullable = false)
    private String second_name;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private Gender gender;

    public User(Long id, Credentials credentials, String first_name, String second_name, Gender gender) {
        super(id);
        this.credentials = credentials;
        this.first_name = first_name;
        this.second_name = second_name;
        this.gender = gender;
    }
}
