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
    private String cityOfLiving;

    @Override
    public String toString() {
        return "Persons{" +
                person +
                ", phone_number='" + phone_number + '\'' +
                ", city_of_living='" + cityOfLiving + '\'' +
                '}';
    }
}
