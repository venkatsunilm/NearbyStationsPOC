package com.virta.nearbystations.viewmodel.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.virta.nearbyservices.data.NetworkResult
import com.virta.nearbyservices.domain.RepositoryManager
import com.virta.nearbystations.ui.login.LoginResultStatus
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
    fun login(userName: String, password: String) {
        viewModelScope.launch {
            _spinner.value = true
            safeCall {
                when (val result = repositoryManager.login(userName, password)) {
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
}