package de.robertsd.housesoficeandfire.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.widget.Toast
import de.robertsd.housesoficeandfire.R
import de.robertsd.housesoficeandfire.models.Character
import de.robertsd.housesoficeandfire.models.House
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class ServiceImpl(var context: Context) {

    private val amountOfResultsPerPage = "50"
    private val service = RetrofitFactory.makeRetrofitService()

    suspend fun getAllHouses(page: Int): List<House>? {
        if (isNetworkAvailable()) {
            val request = service.getHousesAsync(page.toString(), amountOfResultsPerPage)
            val response = request.await()
            if (response.isSuccessful && response.body() != null) {
                return response.body()!!
            }
        }
        showErrorToast()
        return null
    }

    suspend fun getCharacterWithId(id: String): Character? {
        if (isNetworkAvailable()) {
            val request = service.getCharacterWithId(id)
            val response = request.await()
            if (response.isSuccessful && response.body() != null) {
                response.body().let { return it }
            }
        }
        showErrorToast()
        return null
    }

    private fun showErrorToast() {
        GlobalScope.launch(Dispatchers.Main) {
            Toast.makeText(context, context.getString(R.string.error_message), Toast.LENGTH_SHORT).show()
        }
    }

    private fun isNetworkAvailable(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork
            val capabilities = connectivityManager.getNetworkCapabilities(network)
            capabilities != null && capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
        } else {
            connectivityManager.activeNetworkInfo.type == ConnectivityManager.TYPE_WIFI
        }
    }
}