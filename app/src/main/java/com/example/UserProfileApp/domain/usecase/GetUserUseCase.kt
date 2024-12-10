package com.example.UserProfileApp.domain.usecase

import com.example.UserProfileApp.domain.model.User

interface GetUserUseCase {
    suspend fun execute(userId: Int): User
}
