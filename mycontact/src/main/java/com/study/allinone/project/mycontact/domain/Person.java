package com.study.allinone.project.mycontact.domain;


import com.study.allinone.project.mycontact.domain.dto.Birthday;
import com.study.allinone.project.mycontact.dto.PersonDto;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Where;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@RequiredArgsConstructor
@Where(clause = "deleted = false")       // 글로벌하게 쓰일수있는 어노테이션. 조건을 하나 추가해주는 쿼리문. 안지워진 정보만 나오게!
public class Person {

    // @RequiredArgsConstructor 이거는 notNull인 것만 생성자로 선언할 수 있는 생성자 만들어주는것.
    // primary key로 쓸 아이디! 유니크한 값
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @NotEmpty
    @Column(nullable = false)
    private String name;

    @NonNull
    @Min(1)
    private int age;

    @NonNull
    @NotEmpty
    @Column(nullable = false)
    private String bloodType;

    private String address;

    @Embedded
    private Birthday birthday;

    private String job;

    private String hobby;

    @ToString.Exclude
    private String phoneNumber;


    @ColumnDefault("0")         // 0은 false
    private boolean deleted;            // 삭제 여부를 가지고 있는 필드! true가 되면 삭제됐다고 간주.






    // orphanRemoval은 내가 person에서 삭제하면 block에서도 같이 삭제
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)       // person, block의 관계를 정해주기 위해 해주기
    @ToString.Exclude       // 불필요한 쿼리 호출을 막음
    private Block block;




    // PersonDto에서 age값을 정하지 않으면 자동으로 0으로 셋팅됨.  personDto 세터들 만들어줌. 업데이트할떄 쓰려고!
    public void set(PersonDto personDto){

        if(!StringUtils.isEmpty(personDto.getHobby())){
            this.setHobby(personDto.getHobby());
        }

        if(!StringUtils.isEmpty(personDto.getBloodType())){
            this.setBloodType(personDto.getBloodType());
        }

        if(!StringUtils.isEmpty(personDto.getAddress())){
            this.setAddress(personDto.getAddress());
        }

        if(!StringUtils.isEmpty(personDto.getJob())){
            this.setJob(personDto.getJob());
        }

        if(!StringUtils.isEmpty(personDto.getPhoneNumber())){
            this.setPhoneNumber(personDto.getPhoneNumber());
        }

    }
    public Integer getAge(){
        if(this.birthday != null){
        return LocalDate.now().getYear() - this.birthday.getYearOfBirthday() + 1;

        } else {
            return null;
        }
    }

    public boolean isBirthdayToday(){
        return LocalDate.now().equals(LocalDate.of(
                this.birthday.getYearOfBirthday(), this.birthday.getMonthOfBirthday(), this.birthday.getDayOfBirthday()));
    }

}
