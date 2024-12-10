package com.example.UserProfileApp.data.datasource

import com.example.UserProfileApp.domain.model.User
import kotlinx.coroutines.delay

class UserDataSourceImpl : UserDataSource {

    private val users = listOf(
        User(id = 1, name = "Stephanie", email = "steph@example.com"),
        User(id = 2, name = "Nahianie", email = "nanie@example.com"),
        User(id = 3, name = "Kenny", email = "ken@example.com")
    )

    override suspend fun fetchUser(userId: Int): User {
        delay(1000) // Simulate network delay
        return users.find { it.id == userId } ?: throw Exception("User not found")
    }
}