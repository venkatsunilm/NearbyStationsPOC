package com.virta.nearbystations.ui.stations

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.virta.nearbyservices.data.NetworkResult
import com.virta.nearbyservices.domain.RepositoryManager
import com.virta.nearbystations.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StationListViewModel @Inject constructor(
    private val repositoryManager: RepositoryManager
) : BaseViewModel() {

    private var stationsJob: Job? = null

    private var _stationList = MutableLiveData<StationListResultStatus>()
    val stationList: LiveData<StationListResultStatus>
        get() = _stationList

    fun getStationList(params: Map<String, Double>) {
        stationsJob = viewModelScope.launch {
            safeCall {
                when (val result = repositoryManager.getStationList(params)) {
                    is NetworkResult.Success -> {
                        _stationList.value = StationListResultStatus(result.data, true)
                    }
                    is NetworkResult.Error -> {
                        _stationList.value =
                            StationListResultStatus(errorMessage = result.exception.toString())
                    }
                }
            }
        }
    }

    fun cancelRoutineJob() {
        stationsJob?.cancel()
    }
}