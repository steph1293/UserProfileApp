package com.example.UserProfileApp.data.repository

import com.example.UserProfileApp.domain.model.User
import com.example.UserProfileApp.data.datasource.UserDataSource

class UserRepositoryImpl(private val userDataSource: UserDataSource) : UserRepository {
    override suspend fun getUserById(userId: Int): User {
        return userDataSource.fetchUser(userId)
    }
}