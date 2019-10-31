package base

import androidx.lifecycle.ViewModel
import injection.component.DaggerViewModelInjector
import injection.component.ViewModelInjector
import injection.module.NetworkModule
import ui.post.PostListViewModel
import ui.post.PostViewModel

abstract class BaseViewModel: ViewModel() {
    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is PostListViewModel -> injector.inject(this)
            is PostViewModel -> injector.inject(this)
        }
    }
}
