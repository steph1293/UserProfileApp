package com.example.UserProfileApp.data.repository

import com.example.UserProfileApp.domain.model.User

interface UserRepository {
    suspend fun getUserById(userId: Int): User
}