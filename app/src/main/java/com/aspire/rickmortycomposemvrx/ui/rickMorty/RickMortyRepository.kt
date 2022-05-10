package com.aspire.rickmortycomposemvrx.ui.rickMorty

import com.aspire.rickmortycomposemvrx.network.ApiService

class RickMortyRepository(private val apiService: ApiService) {

    suspend fun getCharacter() =
        apiService.fetchCharacters("1")
}