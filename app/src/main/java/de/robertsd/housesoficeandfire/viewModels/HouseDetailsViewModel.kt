package de.robertsd.housesoficeandfire.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import de.robertsd.housesoficeandfire.models.House
import de.robertsd.housesoficeandfire.network.ServiceImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HouseDetailsViewModel : ViewModel() {

    var id: String = ""
    var house: House? = null
    var titles = MutableLiveData<String>()
    var lord = MutableLiveData<String>()

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
            GlobalScope.launch(Dispatchers.IO) {
                val characterId = house!!.currentLord.substring(45)
                val character = ServiceImpl.getCharacterWithId(characterId)
                GlobalScope.launch(Dispatchers.Main) {
                    if (character != null) {
                        lord.value = character.name
                    }
                }
            }
        }
    }
}