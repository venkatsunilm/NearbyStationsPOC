package com.virta.nearbystations.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.virta.nearbyservices.data.model.StationListModel
import com.virta.nearbystations.R
import com.virta.nearbystations.databinding.ItemKwBinding

class KwAdapter(private var electricVehicleconnectors: List<StationListModel.ElectricVehicleConnectors>) :
    RecyclerView.Adapter<KwAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemKwBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = electricVehicleconnectors[position]
        holder.updateBindValues(item)
    }

    override fun getItemCount(): Int = electricVehicleconnectors.size

    inner class ViewHolder(
        private val binding: ItemKwBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun updateBindValues(item: StationListModel.ElectricVehicleConnectors) {
            val chipName = binding.root.resources?.getString(R.string.kw)?.let {
                String.format(it, item.connectors[0].maxKw.toString())
            }
            with(binding) {
                tvKW.text = chipName
            }
        }

    }
}
