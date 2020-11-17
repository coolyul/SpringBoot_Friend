package com.study.allinone.project.mycontact.repository;

import com.study.allinone.project.mycontact.domain.Person;
import com.study.allinone.project.mycontact.domain.dto.Birthday;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    @Test
    void crud(){        // db 입출력에대한 테스트는 입출력 한번에 다함!
        Person person = new Person();
        person.setName("david");
        person.setBloodType("O");

        personRepository.save(person);

//        System.out.println(personRepository.findAll());

        List<Person> result = personRepository.findByName("david");

        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0).getName()).isEqualTo("david");
//        assertThat(result.get(0).getAge()).isEqualTo(9);
        assertThat(result.get(0).getBloodType()).isEqualTo("B");

    }

    @Test
    void findByBloodType(){
//        givenPerson("martin", 10, "A");
//        givenPerson("david", 9, "B");
//        givenPerson("jason", 8, "O");
//        givenPerson("Yuri", 7, "AB");
//        givenPerson("benny", 5, "A");
//        givenPerson("Rosy", 7, "A");

        List<Person> result = personRepository.findByBloodType("A");

        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0).getName()).isEqualTo("martin");
        assertThat(result.get(1).getName()).isEqualTo("benny");

//        System.out.println(result);
    }

    @Test
    void findByBirthdayBetween(){
//        givenPerson("martin", 10, "A", LocalDate.of(1991, 8, 15));
//        givenPerson("david", 9, "B", LocalDate.of(1992, 7, 11));
//        givenPerson("jason", 8, "O", LocalDate.of(1993, 1, 5));
//        givenPerson("Yuri", 7, "AB", LocalDate.of(1994, 6, 30));
//        givenPerson("benny", 5, "A", LocalDate.of(1995, 8, 30));

        List<Person> result = personRepository.findByMonthOfBirthday(8);

        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0).getName()).isEqualTo("martin");
        assertThat(result.get(1).getName()).isEqualTo("Yuri");

//        result.forEach(System.out::println);
    }

}