package suranovan.dao_with_hibernate.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import suranovan.dao_with_hibernate.model.Persons;
import suranovan.dao_with_hibernate.repository.MyRepository;
import suranovan.dao_with_hibernate.repository.PersonsRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class MyController {
    final MyRepository myRepository;
    final PersonsRepository personsRepository;

    public MyController(MyRepository myRepository, PersonsRepository personsRepository) {
        this.myRepository = myRepository;
        this.personsRepository = personsRepository;
    }

    @GetMapping("person/by-city")
    public List<Persons> getPersons(@RequestParam("city") String city) {
        return myRepository.getPersonByCity(city);
    }

    @GetMapping("person/by-age")
    public List<Persons> getPersons(@RequestParam("age") int age) {
        return myRepository.getPersonsByAge(age);
    }

    @GetMapping("person/by-name_and_surname")
    public Optional<List<Persons>> getPersons(@RequestParam("name") String name, @RequestParam("surname") String surname) {
        return myRepository.getPersonsByNameAndSurname(name, surname);
    }
}
