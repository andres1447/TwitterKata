package users.actions

import com.nhaarman.mockitokotlin2.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeAll
import users.actions.RegisterUser
import users.actions.RegistrationData
import users.domain.UserAlreadyExistsException
import users.actions.UserCreated
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import users.domain.User
import users.domain.Users

class RegisterUserShould {
    private val NAME: String = "IKKI";
    private val NICKNAME: String = "FENIX";
    private val REGISTRATION_DATA: RegistrationData = RegistrationData(NAME, NICKNAME);
    private val USER_CREATED: UserCreated = UserCreated(NAME, NICKNAME);

    @Test
    fun `throw an error if user already exist`() {
        // Given
        var users = mock<Users>(){
            on { exists(any())} doReturn true;
        }

        var registerUser = RegisterUser(users);

        assertThrows<UserAlreadyExistsException>
        {
            registerUser.execute(REGISTRATION_DATA)
        }
    }

    @Test
    fun `return a new user created`() {
        // Given
        var users = mock<Users>(){
            on { exists(any())} doReturn false;
        }
        var registerUser = RegisterUser(users);

        // When
        var created = registerUser.execute(REGISTRATION_DATA);

        // Then
        assertThat(created).isEqualTo(USER_CREATED);
    }

    @Test
    fun `add user to users`() {
        // Given
        var users = mock<Users>(){
            on { exists(any())} doReturn false;
        }
        var registerUser = RegisterUser(users);

        // When
        registerUser.execute(REGISTRATION_DATA);

        verify(users, times(1)).add(any());
    }
}