package suranovan.dao_with_hibernate.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import suranovan.dao_with_hibernate.repository.MyRepository;

@RestController
@RequestMapping("/")
public class MyController {
    MyRepository myRepository;

    public MyController(MyRepository myRepository) {
        this.myRepository = myRepository;
    }

    @GetMapping("person/by-city")
    public String getPersons(@RequestParam("city") String city){
        return myRepository.getPersonsByCity(city);
    }
}
