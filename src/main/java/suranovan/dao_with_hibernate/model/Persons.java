package suranovan.dao_with_hibernate.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Persons {
    @EmbeddedId()
    private Person person;
    @Column(nullable = false)
    private String phone_number;
    @Column(nullable = false)
    private String city_of_living;

    @Override
    public String toString() {
        return "Persons{" +
                person +
                ", phone_number='" + phone_number + '\'' +
                ", city_of_living='" + city_of_living + '\'' +
                '}';
    }
}
