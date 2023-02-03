package com.ruangguru.mvvmplaygroundarena.module

import com.ruangguru.mvvmplaygroundarena.data.FlowerRepository
import org.koin.dsl.module

val repositoryModule = module {
    single {
        FlowerRepository(get())
    }
}