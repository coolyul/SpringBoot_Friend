package com.study.allinone.project.mycontact.controller;

import com.study.allinone.project.mycontact.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@Transactional
class PersonControllerTest {

    @Autowired
    private PersonController personController;

    @Autowired
    private PersonRepository personRepository;

    private MockMvc mockMvc;

    @BeforeEach         // 이걸 달게 되면 모든 메소드마다 이 메소드가 한번씩 무조건 실행됨!
    void beforeEach(){
        mockMvc = MockMvcBuilders.standaloneSetup(personController).build();
    }

    @Test
    void getPerson() throws Exception{          //person 정보 가져오기
//        mockMvc = MockMvcBuilders.standaloneSetup(personController).build();

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/person/1"))
                .andDo(print())
                .andExpect(status().isOk());     // 200 응답!
    }

    @Test
    void postPerson() throws Exception{         // person 정보 저장하기
//        mockMvc = MockMvcBuilders.standaloneSetup(personController).build();

        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/person")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{\n" +
                        "    \"name\": \"martin\", " +
                        "\"age\": 20, " +
                        "\"bloodType\":\"A\"\n" +
                        "}"))
                .andDo(print())
                .andExpect(status().isCreated());
    }


    @Test
    void modifyPerson() throws Exception{        // person 정보 수정하기
//        mockMvc = MockMvcBuilders.standaloneSetup(personController).build();

        mockMvc.perform(
                MockMvcRequestBuilders.put("/api/person/1")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content("{\n" +
                            "\"name\": \"martin\", \n" +
                            "\"age\": 20, \n" +
                            "\"bloodType\":\"A\"\n" +
                            "}"))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    void modifyName() throws Exception{         // person의 이름만 변경하는 api
//        mockMvc = MockMvcBuilders.standaloneSetup(personController).build();

        mockMvc.perform(
                MockMvcRequestBuilders.patch("/api/person/1")
                .param("name", "martin22"))
                .andDo(print())
                .andExpect(status().isOk());
    }


    @Test
    @Disabled
    void deletePerson() throws Exception{       // db 삭제. 근데 이렇게 데이터자체를 삭제하면 복구할 방법이 없다.
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/api/person/1"))
                .andDo(print())
                .andExpect(status().isOk());

        System.out.println(personRepository.findPeopleDeleted());
    }
}