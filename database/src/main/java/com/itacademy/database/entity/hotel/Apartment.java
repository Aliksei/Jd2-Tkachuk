package com.itacademy.database.entity.hotel;

import com.itacademy.database.entity.BaseEntity;
import com.itacademy.database.entity.role.Resident;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@Entity
@Table(name = "apartment", schema = "hotel_schema")
public class Apartment extends BaseEntity<Long> {

    @Column(name = "person_capacity", nullable = false)
    private Integer personCapacity;

    @Column(name = "number_of_rooms", nullable = false)
    private Integer numberOfRooms;

    @Column(name = "price_per_night", nullable = false)
    private Integer pricePerNight;

    @OneToOne(mappedBy = "apartment")
    private Resident resident;

    @OneToOne(mappedBy = "apartment")
    private RentRequest rentRequest;

    @Builder
    public Apartment(Long id, Integer personCapacity, Integer numberOfRooms, Integer pricePerNight, Resident resident, RentRequest rentRequest) {
        super(id);
        this.personCapacity = personCapacity;
        this.numberOfRooms = numberOfRooms;
        this.pricePerNight = pricePerNight;
        this.resident = resident;
        this.rentRequest = rentRequest;
    }
}
