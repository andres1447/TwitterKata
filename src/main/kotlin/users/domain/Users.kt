package users.domain

interface Users {
    fun exists(nickname: String): Boolean;
    fun add(user: User)
}