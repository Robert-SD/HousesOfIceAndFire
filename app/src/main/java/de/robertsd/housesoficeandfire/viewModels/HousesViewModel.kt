package de.robertsd.housesoficeandfire.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import de.robertsd.housesoficeandfire.models.House
import de.robertsd.housesoficeandfire.network.ServiceImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HousesViewModel : ViewModel() {

    val houses = MutableLiveData<List<House>>()
    val loading = MutableLiveData<Boolean>()

    init {
        loadAllHouses()
    }

    fun loadAllHouses() {
        loading.value = true
        GlobalScope.launch(Dispatchers.IO) {
            val housesFromAPI = ArrayList<House>()
            for (i in 1..1) {
                val houses = ServiceImpl.getAllHouses(i)
                housesFromAPI.addAll(houses)
            }
            GlobalScope.launch(Dispatchers.Main) {
                houses.value = housesFromAPI
                loading.value = false
            }
        }
    }
}