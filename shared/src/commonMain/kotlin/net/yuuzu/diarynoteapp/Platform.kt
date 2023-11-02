package net.yuuzu.diarynoteapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform