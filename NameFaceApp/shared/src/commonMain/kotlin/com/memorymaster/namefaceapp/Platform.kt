package com.memorymaster.namefaceapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform