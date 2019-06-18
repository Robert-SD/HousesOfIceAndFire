package de.robertsd.housesoficeandfire

import android.app.Application
import de.robertsd.housesoficeandfire.network.ServiceImpl
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

class ProjectApplication : Application() {

    val modules = module {
        single { ServiceImpl(applicationContext) }
    }

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@ProjectApplication)
            modules(modules)
        }
    }

}