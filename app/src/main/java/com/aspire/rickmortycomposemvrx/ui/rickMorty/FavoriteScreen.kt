package com.aspire.rickmortycomposemvrx.ui.rickMorty

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aspire.rickmortycomposemvrx.ui.rickMorty.components.CharacterView
import com.aspire.rickmortycomposemvrx.ui.rickMorty.components.Empty
import com.aspire.rickmortycomposemvrx.ui.rickMorty.components.Error
import com.aspire.rickmortycomposemvrx.ui.rickMorty.components.Loading

@ExperimentalFoundationApi
@Composable
fun FavoriteScreen(viewModel: RickMortyViewModel) {

    when (val state = viewModel.favoriteCharacters.collectAsState().value) {

        is RickMortyViewModel.ScreenState.Success -> {

            LazyVerticalGrid(
                modifier = Modifier.fillMaxSize(),
                cells = GridCells.Fixed(2),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(state.items) { item ->
                    CharacterView(item.name, item.image, item.isSaved) {
                        viewModel.onEvent(RickMortyEvents.FavoriteClick(item))
                    }
                }
            }
        }

        RickMortyViewModel.ScreenState.Empty -> {
            Empty()
        }

        is RickMortyViewModel.ScreenState.Error -> {

            Error {
                viewModel.onEvent(RickMortyEvents.TryAgainFavorite)
            }
        }
        RickMortyViewModel.ScreenState.Loading -> {
            Loading()
        }
    }

}
