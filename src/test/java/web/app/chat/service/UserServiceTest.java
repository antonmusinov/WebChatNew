package web.app.chat.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import web.app.chat.entity.User;
import web.app.chat.repository.UsersRepository;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.MockitoAnnotations.initMocks;

public class UserServiceTest {

    @Mock
    private UsersRepository mockUsersRepository;

    @Mock
    private BCryptPasswordEncoder mockBCryptPasswordEncoder;

    private UserService userServiceUnderTest;

    private final String testName = "testName";

    private final String testPassword = "testPassword";

    @Before
    public void setUp() {
        initMocks(this);
        userServiceUnderTest = new UserService(mockUsersRepository);

        User user = new User();

        user.setName(testName);
        user.setPassword(testPassword);

        Mockito.when(mockUsersRepository.save(any())).thenReturn(user);

        Mockito.when(mockUsersRepository.findByName(anyString())).thenReturn(user);

    }

    @Test
    public void testFindUserByName() {

        final User userTest = userServiceUnderTest.findByName(testName);

        assertEquals(testName, userTest.getName());
    }

}
