package de.robertsd.housesoficeandfire.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.test.espresso.idling.CountingIdlingResource
import de.robertsd.housesoficeandfire.models.House
import de.robertsd.housesoficeandfire.network.ServiceImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject


class HousesViewModel : ViewModel(), KoinComponent {

    val houses = MutableLiveData<List<House>>()
    val loading = MutableLiveData<Boolean>()
    var idlingResource = CountingIdlingResource("HOUSES_LOADING")

    init {
        loadAllHouses()
    }

    fun loadAllHouses() {
        loading.value = true
        idlingResource.increment()
        GlobalScope.launch(Dispatchers.IO) {
            val housesFromAPI = ArrayList<House>()
            val service: ServiceImpl by inject()
            for (i in 1..9) {
                val houses = service.getAllHouses(i)
                if (houses != null) {
                    housesFromAPI.addAll(houses)
                }
            }
            GlobalScope.launch(Dispatchers.Main) {
                houses.value = housesFromAPI
                loading.value = false
            }
        }
        idlingResource.decrement()
    }
}