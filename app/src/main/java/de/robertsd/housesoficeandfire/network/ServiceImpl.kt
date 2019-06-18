package de.robertsd.housesoficeandfire.network

import android.util.Log
import de.robertsd.housesoficeandfire.models.Character
import de.robertsd.housesoficeandfire.models.House
import retrofit2.HttpException

class ServiceImpl {

    companion object {
        private const val amountOfResultsPerPage = "50"
        private val service = RetrofitFactory.makeRetrofitService()

        suspend fun getAllHouses(page: Int): List<House> {
            val request = service.getHousesAsync(page.toString(), amountOfResultsPerPage)
            try {
                val response = request.await()
                if (response.body() != null) {
                    response.body().let { return it!! }
                } else {
                    return emptyList()
                }
            } catch (exception: HttpException) {
                Log.e("AllHouses", exception.message())
            }
            return emptyList()
        }

        suspend fun getCharacterWithId(id: String): Character? {
            val request = service.getCharacterWithId(id)
            try {
                val response = request.await()
                if (response.body() != null) {
                    response.body().let { return it }
                } else {
                    return null
                }
            } catch (exception: HttpException) {
                Log.e("CharacterWithId", exception.message())
            }
            return null
        }
    }
}