package com.fastcompaus.mycontact.controller;

import com.fastcompaus.mycontact.controller.dto.PersonDTO;
import com.fastcompaus.mycontact.domain.Person;
import com.fastcompaus.mycontact.domain.dto.Birthday;
import com.fastcompaus.mycontact.reporitory.PersonRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
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
import org.springframework.web.util.NestedServletException;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@SpringBootTest
@Transactional
class PersonControllerTest {
    @Autowired
    private PersonController personController;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private ObjectMapper objectMapper;

    private MockMvc mockMvc;

    @BeforeEach
    void beforeEach() {
        mockMvc = MockMvcBuilders.standaloneSetup(personController).build();
    }

    @Test
    void getPerson() throws Exception {
//        mockMvc = MockMvcBuilders.standaloneSetup(personController).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/api/person/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void postPerson() throws Exception {
//        mockMvc = MockMvcBuilders.standaloneSetup(personController).build();

        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/api/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"name\": \"martin2\",\n" +
                                "    \"age\": 20,\n" +
                                "    \"bloodType\": \"A\"\n" +
                                "}"))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    void modifyPerson() throws Exception {
//        mockMvc = MockMvcBuilders.standaloneSetup(personController).build();

        PersonDTO person = new PersonDTO("martin", "programming", "판교", LocalDate.now(), "programmer", "010-9494-9494");

        mockMvc.perform(
                MockMvcRequestBuilders.put("/api/person?id=1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJsonString(person)))
                .andDo(print())
                .andExpect(status().isOk());

        Person result = personRepository.findById(1L).get();

        assertAll(
                () -> assertThat(result.getName()).isEqualTo("martin"),
                () -> assertThat(result.getHobby()).isEqualTo("programming"),
                () -> assertThat(result.getAddress()).isEqualTo("판교"),
                () -> assertThat(result.getBirthDate()).isEqualTo(Birthday.of(LocalDate.now())),
                () -> assertThat(result.getJob()).isEqualTo("programmer"),
                () -> assertThat(result.getPhoneNumber()).isEqualTo("010-9494-9494")
        );
    }

    @Test
    void modifyPersonIfNameIsDifferent() throws Exception {
//        mockMvc = MockMvcBuilders.standaloneSetup(personController).build();

        PersonDTO person = new PersonDTO("James", "programming", "판교", LocalDate.now(), "programmer", "010-9494-9494");

        assertThrows(NestedServletException.class, () ->
            mockMvc.perform(
                    MockMvcRequestBuilders.put("/api/person?id=1")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(toJsonString(person)))
                    .andDo(print())
                    .andExpect(status().isOk())
        );
    }

    @Test
    void modifyName() throws Exception {
//        mockMvc = MockMvcBuilders.standaloneSetup(personController).build();

        mockMvc.perform(
                MockMvcRequestBuilders.patch("/api/person/1")
                        .param("name", "martinModified"))
                .andDo(print())
                .andExpect(status().isOk());

        assertThat(personRepository.findById(1L).get().getName()).isEqualTo("martinModified");
    }

    @Test
//    @Disabled
    void deletePerson() throws Exception {
//        mockMvc = MockMvcBuilders.standaloneSetup(personController).build();

        mockMvc.perform(
                MockMvcRequestBuilders.delete("/api/person/1"))
                .andDo(print())
                .andExpect(status().isOk());
//                .andExpect(content().string("true"));

//        log.info("people deleted : {}", personRepository.findPeopleDeleted());
        assertTrue(personRepository.findPeopleDeleted().stream().anyMatch(person -> person.getId().equals(1L)));
    }

    @Test
    void checkJsonString() throws JsonProcessingException {
        PersonDTO person = new PersonDTO();
        person.setName("martin");
        person.setBirthday(LocalDate.now());
        person.setAddress("판교");

        System.out.println(">>> " + toJsonString(person));
    }

    private String toJsonString(PersonDTO personDTO) throws JsonProcessingException {
        return objectMapper.writeValueAsString(personDTO);
    }

}