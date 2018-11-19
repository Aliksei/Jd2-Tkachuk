package com.itacademy.database.entity.hotel;

import com.itacademy.database.entity.BaseEntity;
import com.itacademy.database.entity.role.Resident;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString(exclude = "residents")
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@Entity
@Table(schema = "hotel_schema", name = "spa_request")
public class SpaRequest extends BaseEntity<Long> {

    @Column(name = "procedure_name", nullable = false)
    private String procedureName;

    @Column(name = "duration", nullable = false)
    private Integer duration;

    @Column(name = "spa_price", nullable = false)
    private Integer spaPrice;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "spa_procedure_resident", schema = "hotel_schema",
            joinColumns = @JoinColumn(name = "spa_request_id"),
            inverseJoinColumns = @JoinColumn(name = "resident_id"))
    private List<Resident> residents = new ArrayList<>();

    @Builder
    public SpaRequest(Long id, String procedureName, Integer duration, Integer spaPrice, List<Resident> residents) {
        super(id);
        this.procedureName = procedureName;
        this.duration = duration;
        this.spaPrice = spaPrice;
        this.residents = residents;
    }
}
