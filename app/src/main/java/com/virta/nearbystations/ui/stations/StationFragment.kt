package com.virta.nearbystations.ui.stations

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.virta.nearbystations.databinding.StationFragmentBinding

class StationFragment: Fragment() {

    private lateinit var binding: StationFragmentBinding
//    private val stationListViewModel: StationListViewModel by viewModels()
//    private lateinit var adapter: StationsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = StationFragmentBinding.inflate(inflater, container, false)
//        adapter = StationsAdapter(listOf())
//        binding.stationList.adapter = adapter
//
//            stationListViewModel.cancelRoutineJob()
//            stationListViewModel.getStations(params)
//        stationListViewModel.stations.observe(viewLifecycleOwner) {
//            lifecycleScope.launch {
//                binding.stationList.adapter = StationsAdapter(it)
//            }

//        }

        return binding.root
    }

    @BindingAdapter("entries", "layout")
    fun <T> setEntries(
        viewGroup: ViewGroup,
        entries: List<T>?, layoutId: Int
    ) {
        viewGroup.removeAllViews()
        if (entries != null) {
            val inflater = viewGroup.context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            for (i in entries.indices) {
                val entry = entries[i]
                val binding = DataBindingUtil
                    .inflate<ViewDataBinding>(inflater, layoutId, viewGroup, true)
                binding.setVariable(binding.root.id, entry)
            }
        }
    }

}