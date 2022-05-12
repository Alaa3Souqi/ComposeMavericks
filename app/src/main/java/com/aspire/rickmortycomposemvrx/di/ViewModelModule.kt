package com.aspire.rickmortycomposemvrx.di

import com.aspire.rickmortycomposemvrx.ui.rickMorty.RickMortyViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(RickMortyViewModel::class)
    fun viewModelFactory(factory: RickMortyViewModel.Factory): AssistedViewModelFactory<*, *>

}
