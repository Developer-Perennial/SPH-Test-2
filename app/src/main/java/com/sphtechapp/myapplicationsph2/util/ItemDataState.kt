package com.sphtechapp.myapplicationsph2.util

import com.sphtechapp.myapplicationsph2.activities.main.data.RecordsData

sealed class ItemDataState {
    object ShowProgress : ItemDataState()
    data class Error(val message: String?) : ItemDataState()
    data class Success(val body: List<RecordsData>? = null) : ItemDataState()
}