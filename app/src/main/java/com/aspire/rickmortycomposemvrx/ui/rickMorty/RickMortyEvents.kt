package com.aspire.rickmortycomposemvrx.ui.rickMorty

import com.aspire.rickmortycomposemvrx.domain.Character

sealed class RickMortyEvents {
    data class FavoriteClick(val character: Character) : RickMortyEvents()
    object TryAgainCharacter : RickMortyEvents()
    object TryAgainFavorite : RickMortyEvents()
}