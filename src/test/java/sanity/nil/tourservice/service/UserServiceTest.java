package sanity.nil.tourservice.service;

import static org.assertj.core.api.Assertions.assertThat;
import static sanity.nil.tourservice.util.ModelGenerator.generateRole;
import static sanity.nil.tourservice.util.ModelGenerator.generateUser;

import jakarta.transaction.Transactional;
import lombok.SneakyThrows;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import sanity.nil.tourservice.SpringProjectApplication;
import sanity.nil.tourservice.infrastructure.database.model.Role;
import sanity.nil.tourservice.infrastructure.database.model.User;

import java.util.*;


@SpringBootTest(classes = SpringProjectApplication.class)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
@ExtendWith({
        SpringExtension.class,
        MockitoExtension.class
})
public class UserServiceTest {

    @Autowired
    private UserService userService;
    private Role role1;
    private Role role2;
    @Captor
    private ArgumentCaptor<User> userArgumentCaptor;

    @BeforeEach
    public void initTest() {
        role1 = generateRole();
        role2 = generateRole();
    }

    @Test
    @DisplayName("tour saved if entity is valid")
    @Transactional
    public void successSaveValidTour() {
        User user = generateUser();
        userService.save(user);

        assertThat(userService.get(user.getUserId()).getUserId()).isEqualTo(user.getUserId());
        assertThat(userService.get(user.getUserId())).isEqualTo(user);
    }

    @Test
    @DisplayName("tours inserted are successfully extracted")
    @Transactional
    public void successGetAll() {
        User user1 = generateUser();
        User user2 = generateUser();
        userService.save(user1);
        userService.save(user2);
        List<User> list = new ArrayList<>(Arrays.asList(user1,user2));
        Set<User> set = new HashSet<>(list);
        assertThat(userService.getAll()).hasSize(2);
        assertThat(userService.getAll()).isEqualTo(list);
        assertThat(new HashSet<>(userService.getAll())).isEqualTo(set);
    }

    @Test
    @DisplayName("tour is updated successfully")
    @Transactional
    @SneakyThrows
    public void successUpdate() {
        User user1 = generateUser();
        User user2 = (User) user1.clone();
        assertThat(user1).isEqualTo(user2);
        userService.save(user1);
        user1.setRoles(Set.of(generateRole()));
        userService.update(user1);
        userService.getAll().forEach(System.out::println);
        assertThat(userService.get(user1.getUserId())).isNotEqualTo(user2);
    }

    @Test
    @DisplayName("tour deleted if entity with id exists")
    @Transactional
    public void successDeleteExistingTour(){
        User user = generateUser();
        userService.save(user);
        assertThat(userService.get(user.getUserId())).isEqualTo(user);
        userService.delete(user.getUserId());

        assertThat(userService.get(user.getUserId())).isNull();
    }
}
