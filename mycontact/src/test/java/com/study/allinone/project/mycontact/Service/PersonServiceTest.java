package com.study.allinone.project.mycontact.Service;

import com.study.allinone.project.mycontact.domain.Block;
import com.study.allinone.project.mycontact.domain.Person;
import com.study.allinone.project.mycontact.repository.BlockRepository;
import com.study.allinone.project.mycontact.repository.PersonRepository;
import com.study.allinone.project.mycontact.service.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PersonServiceTest {

    @Autowired
    private PersonService personService;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private BlockRepository blockRepository;



    @Test
    void getPeopleExcludeBlocks(){      // 차단되지 않은 사람 목록

        List<Person> result = personService.getPeopleExcludeBlocks();

        personRepository.findAll().forEach(System.out::println);

        assertThat(result.size()).isEqualTo(3);
        assertThat(result.get(0).getName()).isEqualTo("martin");
        assertThat(result.get(1).getName()).isEqualTo("david");
        assertThat(result.get(2).getName()).isEqualTo("benny");


    }



    @Test
    void getPeopleByName(){

        List<Person> result = personService.getPeopleByName("martin");

        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getName()).isEqualTo("martin");
    }



    @Test
    void getPerson(){
        Person person = personService.getPerson(3L);

        assertThat(person.getName()).isEqualTo("jason");
    }
}