package users.actions

class UserCreated(val name: String, val nickname: String) {
    override fun equals(other: Any?): Boolean {
        var o = other as UserCreated;
        return name == o.name && nickname == o.nickname;
    }
}
