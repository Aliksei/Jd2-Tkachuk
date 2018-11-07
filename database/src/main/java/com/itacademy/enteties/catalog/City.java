package com.itacademy.enteties.catalog;

import com.itacademy.enteties.BaseEntity;
import com.itacademy.enteties.role.Resident;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;


@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "residents")
@Data
@Entity
@Table(schema = "hotel_schema", name = "city")
public class City extends BaseEntity<Long> {

    @Column(name = "city_name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "city")
    private List<Resident> residents = new ArrayList<>();

    @Builder
    public City(Long id, String name) {
        super(id);
        this.name = name;
    }
}
