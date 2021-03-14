package suranovan.dao_with_hibernate.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import suranovan.dao_with_hibernate.model.Person;
import suranovan.dao_with_hibernate.model.Persons;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.IntStream;

@Repository
@Transactional
public class MyRepository implements CommandLineRunner {

    @PersistenceContext
    EntityManager entityManager;

    final PersonsRepository personsRepository;

    private final List<String> names = List.of("Aleksey", "Andrey", "Dasha", "Masha", "Sasha");
    private final List<String> surnames = List.of("Kalinin", "Suranov", "Petrovich", "Ivanov");
    private final List<Integer> ages = List.of(21, 27, 33, 41, 55, 15);
    private final List<String> phones = List.of("985-315", "915-514", "903-145", "915-952");
    private final List<String> cities = List.of("Moscow", "Yekaterinburg", "ST-Petersburg", "Krasnodar");

    public MyRepository(PersonsRepository personsRepository) {
        this.personsRepository = personsRepository;
    }


    private void initializeData() {
        var random = new Random();
        IntStream.range(0, 4)
                .forEach(i -> {
                    var persons = Persons.builder()
                            .person(Person.builder().name(names.get(random.nextInt(names.size())))
                                    .surname(surnames.get(random.nextInt(surnames.size())))
                                    .age(ages.get(random.nextInt(ages.size())))
                                    .build())
                            .phone_number(phones.get(random.nextInt(phones.size())))
                            .cityOfLiving(cities.get(random.nextInt(cities.size())))
                            .build();
                    entityManager.persist(persons);
                });
    }

    public List<Persons> getPersonByCity(String city) {
        return personsRepository.findAllByCityOfLivingEquals(city);
    }

    public List<Persons> getPersonsByAge(int age) {
        return personsRepository.findAllByPerson_AgeLessThanOrderByPerson_AgeAsc(age);
    }

    public Optional<List<Persons>> getPersonsByNameAndSurname(String name, String surname) {
        return personsRepository.findAllByPerson_NameAndPerson_Surname(name, surname);
    }

    @Override
    public void run(String... args) {
        initializeData();
    }
}
