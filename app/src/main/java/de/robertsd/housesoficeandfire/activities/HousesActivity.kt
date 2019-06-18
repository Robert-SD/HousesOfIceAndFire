package de.robertsd.housesoficeandfire.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import de.robertsd.housesoficeandfire.R
import de.robertsd.housesoficeandfire.databinding.ActivityHousesBinding
import de.robertsd.housesoficeandfire.helper.BaseActivity
import de.robertsd.housesoficeandfire.helper.Constants.HOUSE
import de.robertsd.housesoficeandfire.helper.HousesAdapter
import de.robertsd.housesoficeandfire.models.House
import de.robertsd.housesoficeandfire.viewModels.HousesViewModel

class HousesActivity : BaseActivity() {

    private lateinit var binding: ActivityHousesBinding
    lateinit var viewModel: HousesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title = getString(R.string.houses_of_ice_and_fire)
        viewModel = ViewModelProviders.of(this).get(HousesViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_houses)
        binding.setLifecycleOwner { this.lifecycle }
        binding.viewModel = viewModel
        binding.rvHouses.layoutManager = LinearLayoutManager(this)
        binding.rvHouses.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        viewModel.houses.observe(this, Observer {
            binding.rvHouses.adapter = generateAdapter(it, ::openHouseDetails)
            binding.rvHouses.setHasFixedSize(true)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.refresh_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.refresh -> {
                viewModel.loadAllHouses()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun generateAdapter(list: List<House>, action: (house: House) -> Unit): RecyclerView.Adapter<*> {
        return HousesAdapter(list, action)
    }

    private fun openHouseDetails(house: House) {
        val intent = Intent(this, HouseDetails::class.java)
        intent.putExtra(HOUSE, house)
        startActivity(intent)
    }
}
