package com.example.UserProfileApp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.UserProfileApp.domain.usecase.GetUserUseCase

class UserViewModelFactory(
    private val getUserUseCase: GetUserUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UserViewModel(getUserUseCase) as T
    }
}