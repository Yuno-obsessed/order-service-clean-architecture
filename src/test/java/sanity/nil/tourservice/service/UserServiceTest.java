package sanity.nil.tourservice.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.testcontainers.junit.jupiter.Testcontainers;
import sanity.nil.tourservice.dao.UserRepository;
import sanity.nil.tourservice.entity.User;
import sanity.nil.tourservice.service.impl.UserServiceImpl;
import sanity.nil.tourservice.util.EntityGenerator;

import java.util.Optional;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Testcontainers
@ExtendWith(
        MockitoExtension.class
)
class UserServiceTest {

    private static User user1;
    private static User user2;
    @Captor
    private ArgumentCaptor<Integer> argumentCaptor;
    private ArgumentCaptor<User> userCaptor;
    @Mock
    private UserRepository repository;
    @InjectMocks
    private UserServiceImpl service;

    @BeforeEach
    public void init(){
//        this.repository = Mockito.mock(UserRepository.class);
//        this.service = new UserServiceImpl(repository);
        user1 = EntityGenerator.generateUser("newIdentifier1", "example@gmail.com");
        user2 = EntityGenerator.generateUser("newIdentifier2", "example2@gmail.com");
    }

    @Test
    @DisplayName("user will be created successfully because the object is valid")
//    @CsvFileSource
    public void successCreateUserTest(){
        service.save(user1);
        service.save(user2);

        System.out.println(service.getAll().size());
        verify(repository).save(userCaptor.capture());
        User capturedUser = userCaptor.getValue();
        System.out.println(capturedUser);
        service.getAll().forEach(u -> System.out.println(u));
        assertThat(service.getAll().stream()
                .filter(user -> user.getUserId().equals(user1.getUserId()))
                .findFirst())
                .isNotNull();
    }

    @Test
    public void successGetUserTest() {
       service.save(user1);

       doReturn(Optional.of(user1)).when(repository).getByUserId(user1.getUserId());

       assertThat(service.get(user1.getUserId())).isEqualTo(user1);

       verify(repository, times(1)).getByUserId(user1.getUserId());
    }

    @Test
    public void successDeleteUserTest() {
        service.save(user2);

        service.delete(user1.getUserId());

        assertThat(service.getAll().stream()
                .filter(user -> user.getUserId().equals(user1.getUserId()))
                .findAny())
                .isNotPresent();
    }
}
