package de.robertsd.housesoficeandfire.network

import de.robertsd.housesoficeandfire.models.House
import de.robertsd.housesoficeandfire.network.RetrofitFactory.BASE_URL
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Services {

    @GET("$BASE_URL/houses")
    fun getHousesAsync(@Query("page") page: String, @Query("pageSize") pageSize: String): Deferred<Response<List<House>>>

    @GET("$BASE_URL/houses/{number}")
    fun getHouseWithNumber(@Path("number") number: String): Deferred<Response<House>>
}