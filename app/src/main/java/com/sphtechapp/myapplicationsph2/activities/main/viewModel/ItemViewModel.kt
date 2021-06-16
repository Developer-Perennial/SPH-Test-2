package com.sphtechapp.myapplicationsph2.activities.main.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sphtechapp.myapplicationsph2.activities.main.data.RecordsData
import com.sphtechapp.myapplicationsph2.backend.ServiceUtil
import com.sphtechapp.myapplicationsph2.util.Constants
import com.sphtechapp.myapplicationsph2.util.ItemDataState
import kotlinx.coroutines.launch

class ItemViewModel(
    private val serviceUtil: ServiceUtil
) :
    ViewModel() {

    val uiState = MutableLiveData<ItemDataState>()

    fun retrieveItems() {
        viewModelScope.launch {
            runCatching {
                uiState.postValue(ItemDataState.ShowProgress)
                fetchItems()
            }.onSuccess {
                uiState.postValue(ItemDataState.Success(filterData(it)))
            }.onFailure {
                uiState.postValue(ItemDataState.Error(it.message))
            }
        }
    }

    private suspend fun fetchItems(): List<RecordsData> {
        return serviceUtil.getList(Constants.RESOURCE_ID).result.records.sortedBy { it.quarter }
    }

    fun getObserverState() = uiState

    fun filterData(records: List<RecordsData>?): List<RecordsData> {
        val filteredRecords = arrayListOf<RecordsData>()
        var lastQuarterRecord = 0.0
        records?.forEach { eachRecord ->
            val year = eachRecord.quarter.split("-")[0].toInt()
            if (year in 2008..2018) {
                eachRecord.year = year
                val indexRecord = filteredRecords.indexOfFirst { thisRecord -> thisRecord.year == year }
                if (indexRecord == -1) {
                    eachRecord.total_volume = eachRecord.volume_of_mobile_data.toDouble()
                    filteredRecords.add(eachRecord)
                } else {
                    val savedRecord = filteredRecords[indexRecord]
                    eachRecord.total_volume = savedRecord.total_volume + eachRecord.volume_of_mobile_data.toDouble()
                    eachRecord.decreased = (eachRecord.volume_of_mobile_data.toDouble() < lastQuarterRecord && !eachRecord.decreased || savedRecord.decreased)
                    filteredRecords[indexRecord] = eachRecord
                }
                lastQuarterRecord = eachRecord.volume_of_mobile_data.toDouble()
            }
        }
        return filteredRecords
    }
}