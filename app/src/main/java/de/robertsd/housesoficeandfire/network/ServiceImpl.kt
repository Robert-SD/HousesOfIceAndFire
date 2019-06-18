package de.robertsd.housesoficeandfire.network

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
            }
            return emptyList()
        }

        suspend fun getHouseWithNumber(number: Int): House? {
            val request = service.getHouseWithNumber(number.toString())
            try {
                val response = request.await()
                if (response.body() != null) {
                    response.body().let { return it!! }
                } else {
                    return null
                }
            } catch (exception: HttpException) {
            }
            return null
        }
    }
}