package com.sphtechapp.myapplicationsph2.activities.main.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import com.sphtechapp.myapplicationsph2.activities.main.viewModel.ItemViewModel

val itemModule = module {
    viewModel {
        ItemViewModel(
            get()
        )

    }

}