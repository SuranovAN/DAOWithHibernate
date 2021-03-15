package suranovan.dao_with_hibernate.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import suranovan.dao_with_hibernate.model.Persons;
import suranovan.dao_with_hibernate.repository.MyRepository;
import suranovan.dao_with_hibernate.repository.PersonsRepository;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/secure/person")
public class SecureController {
    final MyRepository myRepository;
    final PersonsRepository personsRepository;

    public SecureController(MyRepository myRepository, PersonsRepository personsRepository) {
        this.myRepository = myRepository;
        this.personsRepository = personsRepository;
    }

    @GetMapping("/by-city")
    @Secured("ROLE_READ")
    public List<Persons> getPersons(@RequestParam("city") String city) {
        return myRepository.getPersonByCity(city);
    }

    @GetMapping("/by-age")
    @RolesAllowed("ROLE_WRITE")
    public List<Persons> getPersons(@RequestParam("age") int age) {
        return myRepository.getPersonsByAge(age);
    }

    @GetMapping("/by-name_and_surname")
    @PreAuthorize("hasAnyRole('WRITE', 'DELETE')")
    public Optional<List<Persons>> getPersons(@RequestParam("name") String name, @RequestParam("surname") String surname) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
        System.out.println("User principal name =" + userPrincipal.getUsername());
        System.out.println("Is user enabled =" + userPrincipal.isEnabled());
        if (name.equals(userPrincipal.getUsername())) {
            return myRepository.getPersonsByNameAndSurname(name, surname);
        }
        return Optional.empty();
    }
}