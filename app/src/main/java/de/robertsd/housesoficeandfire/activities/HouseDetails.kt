package de.robertsd.housesoficeandfire.activities

import android.os.Bundle
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import de.robertsd.housesoficeandfire.R
import de.robertsd.housesoficeandfire.databinding.ActivityHouseDetailsBinding
import de.robertsd.housesoficeandfire.helper.BaseActivity
import de.robertsd.housesoficeandfire.helper.Constants.HOUSE
import de.robertsd.housesoficeandfire.models.House
import de.robertsd.housesoficeandfire.viewModels.HouseDetailsViewModel

class HouseDetails : BaseActivity() {

    private lateinit var binding: ActivityHouseDetailsBinding
    private lateinit var viewModel: HouseDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val house = intent.extras?.get(HOUSE) as House
        supportActionBar?.title = getString(R.string.house_details)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        viewModel = ViewModelProviders.of(this).get(HouseDetailsViewModel::class.java)
        viewModel.house = house
        viewModel.setup()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_house_details)
        binding.setLifecycleOwner { this.lifecycle }
        binding.viewModel = viewModel
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
