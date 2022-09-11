package com.virta.nearbystations.ui.stations

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.virta.nearbyservices.data.RepositoryManager
import com.virta.nearbyservices.data.ResponseError
import com.virta.nearbyservices.data.model.StationListModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StationListViewModel @Inject constructor(
    private val repositoryManager: RepositoryManager
) : ViewModel() {

    private var stationsJob: Job? = null

    private var _stations = MutableLiveData<List<StationListModel>>()
    val stations: LiveData<List<StationListModel>>
        get() = _stations

    fun getStations(params: Map<String, Double>) {
        stationsJob = viewModelScope.launch {
            try {

                _stations.value = repositoryManager.getStations(params)

            } catch (error: ResponseError) {
                // TODO: Update the UI with the error message
                // TODO: For now  the service is not available sending mock data back
//                _stations.value = EventsMockList.getEventsMockList()
            }
        }
    }

    fun cancelRoutineJob() {
        stationsJob?.cancel()
    }
}