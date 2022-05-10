package com.aspire.rickmortycomposemvrx

import android.app.Application
import com.airbnb.mvrx.Mavericks
import com.aspire.rickmortycomposemvrx.network.ApiClient
import com.aspire.rickmortycomposemvrx.ui.rickMorty.RickMortyRepository

class BaseApplication : Application() {
    val rickMortyRepository = RickMortyRepository(ApiClient.apiService)

    override fun onCreate() {
        super.onCreate()
        Mavericks.initialize(this)
    }
}