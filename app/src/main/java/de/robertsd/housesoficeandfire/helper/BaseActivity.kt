package de.robertsd.housesoficeandfire.helper

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {

    fun displayErrorMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}