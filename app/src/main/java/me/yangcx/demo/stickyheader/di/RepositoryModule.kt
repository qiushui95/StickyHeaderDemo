package me.yangcx.demo.stickyheader.di

import me.yangcx.demo.stickyheader.repository.AreaRepository
import org.koin.dsl.module
import org.koin.experimental.builder.single

object RepositoryModule {
    val instance = module {
        single<AreaRepository>()
    }
}