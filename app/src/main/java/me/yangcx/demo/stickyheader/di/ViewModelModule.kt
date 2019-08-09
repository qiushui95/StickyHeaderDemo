package me.yangcx.demo.stickyheader.di

import me.yangcx.demo.stickyheader.ui.main.MainViewModel
import org.koin.androidx.experimental.dsl.viewModel
import org.koin.dsl.module

object ViewModelModule {
    val instance = module {
        viewModel<MainViewModel>()
    }
}