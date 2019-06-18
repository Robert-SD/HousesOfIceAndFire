package de.robertsd.housesoficeandfire.helper

import androidx.test.espresso.idling.CountingIdlingResource

class IdlingResource {

    companion object {
        val name = "IDLING_RESOURCE"
        var idlingResource = CountingIdlingResource("HOUSES_LOADING")

        fun increment() {
            idlingResource.increment()
        }

        fun decrement() {
            idlingResource.decrement()
        }
    }
}