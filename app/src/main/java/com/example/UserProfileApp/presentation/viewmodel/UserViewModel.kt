package com.example.UserProfileApp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.UserProfileApp.domain.model.User
import com.example.UserProfileApp.domain.usecase.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase
) : ViewModel() {

    private val _user = MutableLiveData<User?>()
    val user: LiveData<User?> get() = _user

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    fun loadUser(userId: Int) {
        viewModelScope.launch {
            _isLoading.postValue(true)
            try {
                val fetchedUser = getUserUseCase.execute(userId)
                _user.postValue(fetchedUser)
            } catch (e: Exception) {
                _user.postValue(null)
                // Handle error (e.g., post a different LiveData for errors)
            } finally {
                _isLoading.postValue(false)
            }
        }
    }
}