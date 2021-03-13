package suranovan.dao_with_hibernate.repository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import suranovan.dao_with_hibernate.model.Person;
import suranovan.dao_with_hibernate.model.Persons;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@Repository
@Transactional
public class MyRepository implements CommandLineRunner {

    @PersistenceContext
    EntityManager entityManager;

    private final List<String> names = List.of("Aleksey", "Andrey", "Dasha", "Masha");
    private final List<String> surnames = List.of("Kalinin", "Suranov", "Petrovich", "Ivanov");
    private final List<Integer> ages = List.of(21, 27, 33, 41);
    private final List<String> phones = List.of("985-315", "915-514", "903-145", "915-952");
    private final List<String> cities = List.of("moscow", "Yekaterinburg", "ST-Petersburg", "Krasnodar");

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
                            .city_of_living(cities.get(random.nextInt(cities.size())))
                            .build();
                    entityManager.persist(persons);
                });
    }

    public List<Persons> getPersonsByCity(String city) {
        StringBuilder resp = new StringBuilder();
//        entityManager.createQuery("select c from Persons c where c.city_of_living=" +
//                "'" + city + "'", Persons.class)
//                .getResultList().forEach(i -> resp.append("<p>").append(i.toString()).append("</p></b>"));
        Query query = entityManager.createQuery("select p from Persons p where p.city_of_living=:city_of_living", Persons.class);
        query.setParameter("city_of_living", city);
        var list = query.getResultList();
//        list.forEach(i -> resp.append("<p>").append(i.toString()).append("</p></b>"));
        return list;
    }

    @Override
    public void run(String... args) {
        initializeData();
    }
}
