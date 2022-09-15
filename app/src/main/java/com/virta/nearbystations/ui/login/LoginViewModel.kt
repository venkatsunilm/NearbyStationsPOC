package com.virta.nearbystations.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.virta.nearbyservices.data.NetworkResult
import com.virta.nearbyservices.data.RepositoryManager
import com.virta.nearbyservices.data.model.UserCredentials
import com.virta.nearbystations.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repositoryManager: RepositoryManager
) : BaseViewModel() {

    private val _spinner = MutableLiveData(false)
    val spinner: LiveData<Boolean>
        get() = _spinner

    private var _loggedUserStatus = MutableLiveData<LoginResultStatus>()
    val loggedUser: LiveData<LoginResultStatus>
        get() = _loggedUserStatus

    // TODO: Instead of Network result we can return UI custom data model from Repository
    fun login(username: String, password: String) {
        viewModelScope.launch {
            // TODO: Remove the below line, its for mock testing
            _spinner.value = true
            val userCred = UserCredentials()
            safeCall {
                when (val result = repositoryManager.login(userCred.userName, userCred.password)) {
                    is NetworkResult.Success -> {
                        _loggedUserStatus.value = LoginResultStatus(result.data)
                    }
                    is NetworkResult.Error -> {
                        _loggedUserStatus.value =
                            LoginResultStatus(errorMessage = result.exception.toString())
                    }
                }
            }
            _spinner.value = false
        }
    }

    fun isAuthenticated() {
        repositoryManager.isUserAuthenticated()
    }

//    fun loginDataChanged(username: String, password: String) {
//        if (!isUserNameValid(username)) {
//            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
//        } else if (!isPasswordValid(password)) {
//            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
//        } else {
//            _loginForm.value = LoginFormState(isDataValid = true)
//        }
//    }
//
//    // A placeholder username validation check
//    private fun isUserNameValid(username: String): Boolean {
//        return if (username.contains('@')) {
//            Patterns.EMAIL_ADDRESS.matcher(username).matches()
//        } else {
//            username.isNotBlank()
//        }
//    }
//
//    // A placeholder password validation check
//    private fun isPasswordValid(password: String): Boolean {
//        return password.length > 5
//    }
}