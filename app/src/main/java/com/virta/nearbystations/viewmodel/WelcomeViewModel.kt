package com.virta.nearbystations.viewmodel

import androidx.lifecycle.ViewModel
import com.virta.nearbyservices.data.RepositoryManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    repositoryManager: RepositoryManager
) : ViewModel() {

    val isUserAuthenticated = repositoryManager.isUserAuthenticated()

}