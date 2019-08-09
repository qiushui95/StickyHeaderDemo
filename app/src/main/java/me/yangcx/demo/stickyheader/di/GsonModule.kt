package me.yangcx.demo.stickyheader.di

import com.google.gson.Gson
import org.koin.dsl.module

object GsonModule {
    val instance = module {
        single {
            Gson()
        }
    }
}