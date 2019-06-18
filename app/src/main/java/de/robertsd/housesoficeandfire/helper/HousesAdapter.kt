package de.robertsd.housesoficeandfire.helper

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import de.robertsd.housesoficeandfire.databinding.HouseItemBinding
import de.robertsd.housesoficeandfire.models.House

class HousesAdapter(private val list: List<House>, private val action: (house: House) -> Unit) :
    RecyclerView.Adapter<HousesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = HouseItemBinding.inflate(layoutInflater)
        return ViewHolder(binding, action)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    class ViewHolder(
        private val binding: HouseItemBinding,
        private val action: (house: House) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(house: House) {
            binding.house = house
            binding.clFrame.setOnClickListener {
                action(house)
            }
            binding.executePendingBindings()
        }
    }
}