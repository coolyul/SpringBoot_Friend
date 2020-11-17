package com.study.allinone.project.mycontact.repository;


import com.study.allinone.project.mycontact.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {

    List<Person> findByName(String name);

    // 차단되지 않은 경우의 person을 가져오는 메소드
    List<Person> findByBlockIsNull();

    List<Person> findByBloodType(String bloodType);

    // 이번 달 생일인 사람
    // nativeQuery = true 는 일반 native 쿼리로 쓸 수 있음
    @Query(value = "select person from Person person where person.birthday.monthOfBirthday =:monthOfBirthday")
    List<Person> findByMonthOfBirthday(@Param("monthOfBirthday") int monthOfBirthday);

    @Query(value = "select * from Person person where person.deleted = true", nativeQuery = true)
    List<Person> findPeopleDeleted();

}
