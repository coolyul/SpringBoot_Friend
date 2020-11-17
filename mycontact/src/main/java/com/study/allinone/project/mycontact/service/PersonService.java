package com.study.allinone.project.mycontact.service;

import com.study.allinone.project.mycontact.domain.Block;
import com.study.allinone.project.mycontact.domain.Person;
import com.study.allinone.project.mycontact.domain.dto.Birthday;
import com.study.allinone.project.mycontact.dto.PersonDto;
import com.study.allinone.project.mycontact.repository.BlockRepository;
import com.study.allinone.project.mycontact.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

//    @Autowired
//    private BlockRepository blockRepository;

    public List<Person> getPeopleExcludeBlocks(){
        List<Person> people = personRepository.findAll();
//        List<Block> blocks = blockRepository.findAll();
//        List<String> blockNames = blocks.stream()       // block에서 네임만 가져옴
//                .map(Block::getName).collect(Collectors.toList());

        // block 데이터가 null, 즉 block 데이터가 없는 차단되지 않은 사용자 정보만 가져옴
//        return people.stream().filter(person -> person.getBlock() == null)
//                .collect(Collectors.toList());

        // repository에 찾는 쿼리메소드 만들어서 한줄로 바로 리턴!
        return personRepository.findByBlockIsNull();

    }


    public List<Person> getPeopleByName(String name){
//        List<Person> people = personRepository.findAll();
//
//        // 내가 파라미터로 준 name과 똑같으면 콜렉트하겠다
//        return people.stream().filter(person -> person.getName().equals(name)).collect(Collectors.toList());

        return personRepository.findByName(name);
    }




    @Transactional(readOnly = true)
    public Person getPerson(Long id){
//        Person person = personRepository.findById(id).get();

        // Get을 했을 때 값이 없으면 null을 리턴하자
        Person person = personRepository.findById(id)
                .orElse(null);

        log.info("person : {}" , person);   // 배포됐을 때 로그 출력을 제한할 수 있음.

        return person;
    }

    @Transactional
    public void put(Person person){         // person 정보 저장하기
        personRepository.save(person);

    }


    @Transactional
    public void modify(Long id, PersonDto personDto){
        // 1. DB에서 읽어오는 값
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("아이디가 존재하지 않습니다"));

        // 2. 이름이 같은 사람을 변경하는건지 확인
        if(!person.getName().equals(personDto.getName())){
            throw new RuntimeException("이름이 다릅니다");
        }
        
        // 3. 새 정보 셋팅
        person.set(personDto);

        personRepository.save(person);
    }

    @Transactional
    public void modify(Long id, String name){       // 이름이 바뀌었을 때 이름바꾸는 메소드
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("아이디가 존재하지 않습니다."));

        person.setName(name);
        personRepository.save(person);
    }


    @Transactional
    public void delete(Long id){
//        personRepository.deleteById(id);       이게 쉬운 방법이긴 한데 아예 데이터 삭제로, 복구가 안됨.

        // 삭제하는 것이 아니라, 삭제 db에 넣어버림.
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("아이디가 존재하지 않습니다."));

        person.setDeleted(true);
        personRepository.save(person);
    }
}
