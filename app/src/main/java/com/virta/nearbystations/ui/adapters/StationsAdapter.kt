package com.virta.nearbystations.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.virta.nearbyservices.data.model.StationModel
import com.virta.nearbystations.databinding.StationFragmentBinding
import com.virta.nearbystations.viewmodel.stations.StationViewModel

internal class StationsAdapter(
    private var values: List<StationModel>
) : RecyclerView.Adapter<StationsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            StationFragmentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.updateBindValues(item)
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(
        private val binding: StationFragmentBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun updateBindValues(item: StationModel) {
            with(binding) {
                stationViewModel = StationViewModel(item)

                // update the child recycler view
                electricVehicleConnectors.apply {
                    adapter = KwAdapter(item.electricVehicleConnectors)
                }
                executePendingBindings()
            }
        }
    }
}
