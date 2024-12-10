package com.example.UserProfileApp.data.datasource

import com.example.UserProfileApp.domain.model.User

interface UserDataSource {
    suspend fun fetchUser(userId: Int): User
}