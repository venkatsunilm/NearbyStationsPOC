package com.virta.nearbystations.ui.stations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.virta.nearbystations.databinding.StationListFragmentBinding
import com.virta.nearbystations.ui.adapters.StationsAdapter
import dagger.hilt.android.AndroidEntryPoint


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
        repeat(10) {
            val params = hashMapOf<String, Double>()
            params["latMin"] = 4.398458
            params["longMin"] = 14.3984656
            params["latMax"] = 61.44560
            params["longMax"] = 23.84568
            stationListViewModel.cancelRoutineJob()
            stationListViewModel.getStationList(params)
        }
        stationListViewModel.stationList.observe(viewLifecycleOwner)
        {
//            lifecycleScope.launch {
            if (it.success) {
                binding.stationList.adapter = StationsAdapter(it.stationList)
                Toast.makeText(context, "List displayed", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, it.errorMessage, Toast.LENGTH_LONG).show()
            }
//            }
        }

        return binding.root
    }

}