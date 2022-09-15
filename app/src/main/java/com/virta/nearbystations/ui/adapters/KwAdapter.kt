package com.virta.nearbystations.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.virta.nearbyservices.data.model.StationDto
import com.virta.nearbystations.R
import com.virta.nearbystations.databinding.SingleUnitKwBinding

internal class KwAdapter(private var electricVehicleConnectors: List<StationDto.ElectricVehicleConnectors>) :
    RecyclerView.Adapter<KwAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            SingleUnitKwBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = electricVehicleConnectors[position]
        holder.updateBindValues(item)
    }

    override fun getItemCount(): Int = electricVehicleConnectors.size

    inner class ViewHolder(
        private val binding: SingleUnitKwBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun updateBindValues(item: StationDto.ElectricVehicleConnectors) {
            val chipName = binding.root.resources?.getString(R.string.kw)?.let {
                String.format(it, item.connectors[0].maxKw.toString())
            }
            with(binding) {
                tvKW.text = chipName
            }
        }
    }
}
