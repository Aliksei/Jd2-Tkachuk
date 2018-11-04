package by.itacademy.enteties.catalog;

import by.itacademy.enteties.BaseEntity;
import by.itacademy.enteties.role.Resident;
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
@ToString(exclude = "residents")
@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Entity
@Table(schema = "hotel_schema", name = "country")
public class Country extends BaseEntity<Long> {

    @Column(name = "country_name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "country")
    private List<Resident> residents = new ArrayList<>();

    @Builder
    public Country(Long id, String name) {
        super(id);
        this.name = name;
    }
}

