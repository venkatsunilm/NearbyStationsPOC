package com.virta.nearbystations.ui.stations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.virta.nearbystations.databinding.StationListFragmentBinding
import com.virta.nearbystations.ui.adapters.StationsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class StationListFragment : Fragment() {

    private lateinit var binding: StationListFragmentBinding
    private val stationListViewModel: StationListViewModel by viewModels()
    private lateinit var adapter: StationsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = StationListFragmentBinding.inflate(inflater, container, false)
        adapter = StationsAdapter(listOf())
        binding.stationList.adapter = adapter

        // Assuming multiple calls can trigger
        repeat(50) {
            val params = hashMapOf<String, Double>()
            params["latMin"] = 4.398458
            params["longMin"] = 14.3984656
            params["latMax"] = 61.44560
            params["longMax"] = 23.84568
            stationListViewModel.cancelRoutineJob()
            stationListViewModel.getStations(params)
        }
        stationListViewModel.stations.observe(viewLifecycleOwner)
        {
//            lifecycleScope.launch {
            binding.stationList.adapter = StationsAdapter(it)
//            }
        }

        return binding.root
    }

}