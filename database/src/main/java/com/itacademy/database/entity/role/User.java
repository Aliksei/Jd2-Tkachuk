package com.itacademy.database.entity.role;

import com.itacademy.database.entity.BaseEntity;
import com.itacademy.database.entity.embedded.Credentials;
import com.itacademy.database.entity.enum_.Gender;
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

    @Column(name = "first_Name", nullable = false)
    private String firstName;

    @Column(name = "second_Name", nullable = false)
    private String secondName;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private Gender gender;

    public User(Long id, Credentials credentials, String firstName, String secondName, Gender gender) {
        super(id);
        this.credentials = credentials;
        this.firstName = firstName;
        this.secondName = secondName;
        this.gender = gender;
    }
}
