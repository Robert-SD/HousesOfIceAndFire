package de.robertsd.housesoficeandfire.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import de.robertsd.housesoficeandfire.R
import de.robertsd.housesoficeandfire.helper.HousesAdapter
import de.robertsd.housesoficeandfire.models.House
import de.robertsd.housesoficeandfire.viewModels.HousesViewModel

class HousesActivity : AppCompatActivity() {

    private lateinit var binding: de.robertsd.housesoficeandfire.databinding.ActivityHousesBinding
    private lateinit var viewModel: HousesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(HousesViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_houses)
        binding.setLifecycleOwner { this.lifecycle }
        binding.viewModel = viewModel
        viewModel.houses.observe(this, Observer {
            binding.rvHouses.layoutManager = LinearLayoutManager(this)
            binding.rvHouses.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
            binding.rvHouses.adapter = generateAdapter(it, ::openHouseDetails)
            binding.rvHouses.setHasFixedSize(true)
        })
    }

    private fun generateAdapter(list: List<House>, action: (house: House) -> Unit): RecyclerView.Adapter<*> {
        return HousesAdapter(list, action)
    }

    private fun openHouseDetails(house: House) {
    }
}
