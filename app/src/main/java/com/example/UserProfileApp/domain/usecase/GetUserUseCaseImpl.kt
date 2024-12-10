package com.example.UserProfileApp.domain.usecase

import com.example.UserProfileApp.data.repository.UserRepository
import com.example.UserProfileApp.domain.model.User

class GetUserUseCaseImpl(private val userRepository: UserRepository) : GetUserUseCase {
    override suspend fun execute(userId: Int): User {
        return userRepository.getUserById(userId)
    }
}