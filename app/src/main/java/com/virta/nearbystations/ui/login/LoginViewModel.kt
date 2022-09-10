package com.virta.nearbystations.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.virta.nearbyservices.data.RepositoryManager
import com.virta.nearbyservices.data.ResponseError
import com.virta.nearbyservices.data.model.UserCredentials
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repositoryManager: RepositoryManager
) : ViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    private val _spinner = MutableLiveData<Boolean>(false)
    val spinner: LiveData<Boolean>
        get() = _spinner

    private var _loggedUser = MutableLiveData<Boolean>()
    val loggedUser: LiveData<Boolean>
        get() = _loggedUser

    fun login(username: String, password: String) {
        viewModelScope.launch {
            try {
                // TODO: Remove the below line, its for mock testing
                val userCred = UserCredentials()
                val result = repositoryManager.login(userCred.userName, userCred.password)
                _loggedUser.value = result
            } catch (e: ResponseError) {
                _loggedUser.value = false
            }
        }
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