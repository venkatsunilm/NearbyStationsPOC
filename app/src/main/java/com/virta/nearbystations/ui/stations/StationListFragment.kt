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
import kotlinx.coroutines.Dispatchers
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

        stationListViewModel.getStations()
        stationListViewModel.stations.observe(viewLifecycleOwner){
            lifecycleScope.launch{
                binding.stationList.adapter = StationsAdapter(it)
            }

        }

        return binding.root
    }
}