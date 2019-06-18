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

class HouseDetailsViewModel : ViewModel(), KoinComponent {

    var id: String = ""
    var house: House? = null
    var titles = MutableLiveData<String>()
    var lord = MutableLiveData<String>()
    var idlingResource = CountingIdlingResource("CHARACTERS_LOADING")

    fun setup() {
        determineTitles()
        determineId()
        determineLord()
    }

    private fun determineTitles() {
        var title = ""
        house!!.titles.forEach {
            title += it
            title += "; "
        }
        titles.value = title
    }

    private fun determineId() {
        id = house!!.url.substring(41)
    }

    private fun determineLord() {
        if (house!!.currentLord.length > 0) {
            idlingResource.increment()
            GlobalScope.launch(Dispatchers.IO) {
                val characterId = house!!.currentLord.substring(45)
                val service: ServiceImpl by inject()
                val character = service.getCharacterWithId(characterId)
                GlobalScope.launch(Dispatchers.Main) {
                    if (character != null) {
                        lord.value = character.name
                    }
                }
            }
            idlingResource.decrement()
        }
    }
}