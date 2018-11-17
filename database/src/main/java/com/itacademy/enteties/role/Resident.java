package com.itacademy.enteties.role;


import com.itacademy.enteties.catalog.City;
import com.itacademy.enteties.catalog.Country;
import com.itacademy.enteties.embedded.Credentials;
import com.itacademy.enteties.enum_.Gender;
import com.itacademy.enteties.hotel.Apartment;
import com.itacademy.enteties.hotel.RentRequest;
import com.itacademy.enteties.hotel.SpaRequest;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@ToString(exclude = "spaRequests")
@Data
@Entity
@Table(schema = "hotel_schema", name = "resident")
public class Resident extends User {

    @OneToOne
    @JoinColumn(name = "apartment_id")
    private Apartment apartment;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "spa_procedure_resident", schema = "hotel_schema",
            joinColumns = @JoinColumn(name = "resident_id"),
            inverseJoinColumns = @JoinColumn(name = "spa_request_id"))
    private List<SpaRequest> spaRequests;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    private City city;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    private Country country;

    @OneToOne(mappedBy = "resident")
    private RentRequest rentRequest;

    @Builder
    public Resident(Long id, Credentials credentials, String first_name, String second_name, Gender gender, Apartment apartment, List<SpaRequest> spaRequests, City city, Country country, RentRequest rentRequest) {
        super(id, credentials, first_name, second_name, gender);
        this.apartment = apartment;
        this.spaRequests = spaRequests;
        this.city = city;
        this.country = country;
        this.rentRequest = rentRequest;
    }
}
