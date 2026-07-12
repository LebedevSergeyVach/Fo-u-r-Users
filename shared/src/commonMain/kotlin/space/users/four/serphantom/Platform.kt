package space.users.four.serphantom

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform