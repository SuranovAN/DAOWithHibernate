package suranovan.dao_with_hibernate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import suranovan.dao_with_hibernate.model.Person;
import suranovan.dao_with_hibernate.model.Persons;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonsRepository extends JpaRepository<Persons, Person> {

    List<Persons> findAllByCityOfLivingEquals(String city);

    List<Persons> findAllByPerson_AgeLessThanOrderByPerson_AgeAsc(int age);

    Optional<List<Persons>> findAllByPerson_NameAndPerson_Surname(String name, String surname);
}
