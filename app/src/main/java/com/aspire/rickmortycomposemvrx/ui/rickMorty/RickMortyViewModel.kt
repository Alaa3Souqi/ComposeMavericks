package com.aspire.rickmortycomposemvrx.ui.rickMorty

import com.airbnb.mvrx.MavericksViewModel
import com.airbnb.mvrx.MavericksViewModelFactory
import com.aspire.rickmortycomposemvrx.di.AssistedViewModelFactory
import com.aspire.rickmortycomposemvrx.di.daggerMavericksViewModelFactory
import com.aspire.rickmortycomposemvrx.mavericks.CharacterState
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class RickMortyViewModel @AssistedInject constructor(
    @Assisted initialState: CharacterState,
    private val repository: RickMortyRepository
) :
    MavericksViewModel<CharacterState>(initialState) {

    @AssistedFactory
    interface Factory : AssistedViewModelFactory<RickMortyViewModel, CharacterState> {
        override fun create(state: CharacterState): RickMortyViewModel
    }

    companion object :
        MavericksViewModelFactory<RickMortyViewModel, CharacterState> by daggerMavericksViewModelFactory()


    fun getCharacters() {
        suspend {
            repository.getCharacter()
        }.execute {
            copy(state = it)
        }
    }
}