package com.virta.nearbystations.ui.stations

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.virta.nearbyservices.data.RepositoryManager
import com.virta.nearbyservices.data.ResponseError
import com.virta.nearbyservices.data.model.StationListModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StationListViewModel @Inject constructor(
    private val repositoryManager: RepositoryManager
) : ViewModel() {

    private var _stations = MutableLiveData<List<StationListModel>>()
    val stations: LiveData<List<StationListModel>>
        get() = _stations

    fun getStations() {
        viewModelScope.launch {
            try {
                for (i in 1..10) {
//                    delay(0)
                    val params = hashMapOf<String, Double>()
                    params["latMin"] = 4.398458
                    params["longMin"] = 14.398458
                    params["latMax"] = 9.398458
                    params["longMax"] = 19.398458

                    _stations.value = repositoryManager.getStations(params)
                }
            } catch (error: ResponseError) {
                // TODO: Update the UI with the error message
                // TODO: For now  the service is not available sending mock data back
//                _stations.value = EventsMockList.getEventsMockList()
            }
        }
    }
}