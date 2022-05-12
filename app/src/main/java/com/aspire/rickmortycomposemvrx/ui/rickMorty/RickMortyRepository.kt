package com.aspire.rickmortycomposemvrx.ui.rickMorty

import com.aspire.rickmortycomposemvrx.network.ApiService
import javax.inject.Inject

class RickMortyRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getCharacter() =
        apiService.fetchCharacters("1")
}