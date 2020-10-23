package ru.codemark.adminka.service.Impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.codemark.adminka.model.User;
import ru.codemark.adminka.respons.Respons;
import ru.codemark.adminka.service.UserService;
import java.util.Collections;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Autowired
    private ObjectMapper om;

    @Test
    @Transactional
    void whenOneUserDeleteThenDeletingInDb() throws JsonProcessingException {
        {
            Respons respons = userService.deleteUser(new User("oleg2000"));
            String jsonActual = om.writeValueAsString(respons);

            Respons expected = new Respons(true);
            String jsonExpected = om.writeValueAsString(expected);

            assertThat(jsonActual).isEqualTo(jsonExpected);
        }
        {
            int countUsers = userService.findAllUsers().size();
            int expected = 2;
            assertThat(countUsers).isEqualTo(expected);
        }
    }

    @Test
    @Transactional
    void whenOneUserDeleteNotFoundToBDThenNotDeletingInDb() throws JsonProcessingException {
        {
            String fakeUser = "fake";
            Respons respons = userService.deleteUser(new User(fakeUser));
            String jsonActual = om.writeValueAsString(respons);

            Respons expected = new Respons(false, Collections.singletonList("user not found"));
            String jsonExpected = om.writeValueAsString(expected);

            assertThat(jsonActual).isEqualTo(jsonExpected);
        }

        {
            int countUsers = userService.findAllUsers().size();
            int expected = 3;
            assertThat(countUsers).isEqualTo(expected);
        }
    }

    @Test
    void whenAddUserThenSavingUserToDB() throws JsonProcessingException {
        {
            User user = new User("dima111", "dima", "qwe2Wrty");
            Respons respons = userService.addUser(user);
            String jsonActual = om.writeValueAsString(respons);

            String jsonExpected = om.writeValueAsString(new Respons(true));

            assertThat(jsonActual).isEqualTo(jsonExpected);
        }
        {
            int countUsers = userService.findAllUsers().size();
            int expected = 4;
            assertThat(countUsers).isEqualTo(expected);
        }
    }

    @Test
    @Transactional
    void whenAddUserIsInDBThenNotSavingUserToDB() throws JsonProcessingException {
        {
            User user = new User("vasiliyROOT", "test1", "test2W");
            Respons respons = userService.addUser(user);
            String jsonActual = om.writeValueAsString(respons);

            String jsonExpected = om.writeValueAsString(new Respons(false, Collections.singletonList("there is such a user")));

            assertThat(jsonActual).isEqualTo(jsonExpected);
        }
    }
}