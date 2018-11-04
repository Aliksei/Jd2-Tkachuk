package by.itacademy.enteties.role;


import by.itacademy.enteties.embedded.Credentials;
import by.itacademy.enteties.enum_.Gender;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Data
@Entity
@Table(schema = "hotel_schema", name = "admin")
public class Admin extends User {

    @Column(name = "salary", nullable = false)
    private Integer salary;

    @Column(name = "full_time", nullable = false)
    private Double fullTime;

    @Builder

    public Admin(Long id, Credentials credentials, String first_name, String second_name, Gender gender, Integer salary, Double fullTime) {
        super(id, credentials, first_name, second_name, gender);
        this.salary = salary;
        this.fullTime = fullTime;
    }
}

