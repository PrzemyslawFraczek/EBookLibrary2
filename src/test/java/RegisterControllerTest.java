import org.junit.jupiter.api.Test;

import org.mockito.BDDMockito;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.never;

class RegisterControllerTest {
    private static final String LOGIN = "LOGIN";
    private static final String CORRECT_PASSWORD = "PASSWORD";
    private static final String UNCORRECTED_PASSWORD = "PASWW";

    private UserStorage userStorage = mock(UserStorage.class);
    private RegisterController registerController = new RegisterController(userStorage);

    @Test
    void shouldNewUsers() {
        Response response = registerController.addNewUser(LOGIN, CORRECT_PASSWORD);

        assertTrue(response.isSuccess());
        assertEquals(response.getMassage(), "operation successful");
        verify(userStorage).add(LOGIN, CORRECT_PASSWORD);
    }


    @Test
    void shouldNotCreateNewUserWhenUserExist() {
        BDDMockito.given(userStorage.exist(LOGIN)).willReturn(true);

        Response response = registerController.addNewUser(LOGIN, CORRECT_PASSWORD);

        assertFalse(response.isSuccess());
        assertEquals(response.getMassage(), "Login busy , try other");
        verify(userStorage, never()).add(LOGIN ,CORRECT_PASSWORD);
    }

    @Test
    void shouldNotCreateNewUserWhenPasswordTooShort() {

        BDDMockito.given(userStorage.passwordTooShort(UNCORRECTED_PASSWORD)).willReturn(true);

        Response response = registerController.addNewUser(LOGIN, UNCORRECTED_PASSWORD);

        assertFalse(response.isSuccess());
        assertEquals(response.getMassage(), "Password too short");
        verify(userStorage, never()).add(LOGIN ,UNCORRECTED_PASSWORD);
    }
}
