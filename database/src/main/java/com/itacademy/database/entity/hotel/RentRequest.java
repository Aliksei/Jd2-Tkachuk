package com.itacademy.database.entity.hotel;


import com.itacademy.database.entity.BaseEntity;
import com.itacademy.database.entity.enum_.RequestStatus;
import com.itacademy.database.entity.role.Resident;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Entity
@Table(schema = "hotel_schema", name = "rent_request")
public class RentRequest extends BaseEntity<Long> {

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private RequestStatus status;

    @OneToOne
    @JoinColumn(name = "apartment_id")
    private Apartment apartment;

    @OneToOne
    @JoinColumn(name = "resident_id")
    private Resident resident;

    @Builder
    public RentRequest(Long id, LocalDate startDate, LocalDate endDate, RequestStatus status, Apartment apartment, Resident resident) {
        super(id);
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.apartment = apartment;
        this.resident = resident;
    }
}
