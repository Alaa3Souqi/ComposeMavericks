package com.aspire.rickmortycomposemvrx.di
import com.airbnb.mvrx.MavericksViewModel
import com.aspire.rickmortycomposemvrx.di.AppModule
import com.aspire.rickmortycomposemvrx.di.AssistedViewModelFactory
import com.aspire.rickmortycomposemvrx.di.ViewModelModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ViewModelModule::class])
interface AppComponent {
    fun viewModelFactories(): Map<Class<out MavericksViewModel<*>>, AssistedViewModelFactory<*, *>>
}