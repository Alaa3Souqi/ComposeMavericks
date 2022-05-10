package com.aspire.rickmortycomposemvrx.ui.rickMorty

import com.airbnb.mvrx.MavericksViewModel
import com.airbnb.mvrx.MavericksViewModelFactory
import com.airbnb.mvrx.ViewModelContext
import com.aspire.rickmortycomposemvrx.BaseApplication
import com.aspire.rickmortycomposemvrx.mavericks.CharacterState

class RickMortyViewModel(initialState: CharacterState, private val repository: RickMortyRepository) :
    MavericksViewModel<CharacterState>(initialState) {

    companion object : MavericksViewModelFactory<RickMortyViewModel, CharacterState> {
        override fun create(
            viewModelContext: ViewModelContext,
            state: CharacterState
        ): RickMortyViewModel {
            val repository = viewModelContext.app<BaseApplication>().rickMortyRepository
            return RickMortyViewModel(state, repository)
        }
    }

    fun getCharacters() {
        suspend {
            repository.getCharacter()
        }.execute {
            copy(state = it)
        }
    }
}