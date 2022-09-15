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

class StationFragment : Fragment() {

    private lateinit var binding: StationFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = StationFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

}