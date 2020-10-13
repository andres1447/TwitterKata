package users.actions

import users.domain.User
import users.domain.UserAlreadyExistsException
import users.domain.Users

class RegisterUser(private val users: Users) {

    fun execute(registration: RegistrationData): UserCreated {
        if (users.exists(registration.nickname))
            throw UserAlreadyExistsException();

        users.add(User(registration.name, registration.nickname));

        return UserCreated(registration.name, registration.nickname);
    }
}
