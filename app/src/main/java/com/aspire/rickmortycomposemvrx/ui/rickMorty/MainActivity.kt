package com.aspire.rickmortycomposemvrx.ui.rickMorty

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.airbnb.mvrx.Fail
import com.airbnb.mvrx.Loading
import com.airbnb.mvrx.Success
import com.airbnb.mvrx.compose.collectAsState
import com.airbnb.mvrx.compose.mavericksViewModel
import com.aspire.rickmortycomposemvrx.ui.theme.RickMortyComposeMvRxTheme

@ExperimentalFoundationApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val viewModel: RickMortyViewModel = mavericksViewModel()
            val result by viewModel.collectAsState()

            RickMortyComposeMvRxTheme {
                when (val state = result.state) {
                    is Loading -> {
                        ProgressBar()
                    }
                    is Success -> {
                        LazyVerticalGrid(
                            modifier = Modifier.fillMaxSize(),
                            cells = GridCells.Fixed(2),
                            contentPadding = PaddingValues(16.dp)
                        ) {
                            items(state.invoke().CharactersList) { item ->
                                CharacterView(item.name, item.image)
                            }
                        }
                    }
                    is Fail -> {
                        Toast.makeText(this, state.error.message, Toast.LENGTH_LONG).show()
                    }
                    else -> {}
                }
            }
            viewModel.getCharacters()
        }
    }

    @Composable
    private fun ProgressBar() {
        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator()
        }
    }

    @Composable
    fun CharacterView(
        name: String,
        image: String
    ) {

        Card(
            modifier = Modifier
                .padding(6.dp)
                .fillMaxSize()
                .background(Color.White, shape = RoundedCornerShape(8.dp)),
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(image)
                        .crossfade(true)
                        .transformations(CircleCropTransformation())
                        .build(),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(96.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = name, maxLines = 1, overflow = TextOverflow.Ellipsis)
            }
        }
    }
}