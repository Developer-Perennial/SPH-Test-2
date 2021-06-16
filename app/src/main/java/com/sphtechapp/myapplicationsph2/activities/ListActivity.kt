package com.sphtechapp.myapplicationsph2.activities

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.sphtechapp.myapplicationsph2.R
import org.koin.androidx.viewmodel.ext.android.getViewModel
import com.sphtechapp.myapplicationsph2.activities.main.adapter.ItemAdapter
import com.sphtechapp.myapplicationsph2.activities.main.viewModel.ItemViewModel
import com.sphtechapp.myapplicationsph2.util.ItemDataState
import kotlinx.android.synthetic.main.activity_list.*
class ListActivity : AppCompatActivity() {

    private lateinit var itemViewModel: ItemViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        itemViewModel = getViewModel()
        itemViewModel.retrieveItems()
        observeStates()

    }

    private fun observeStates() = observeListState()

    private fun observeListState() {
        itemViewModel.getObserverState().observe(this, itemObserver)
    }

    private val itemObserver = Observer<ItemDataState> { dataState ->
        when (dataState) {
            is ItemDataState.ShowProgress -> {
                progress_bar.visibility = View.VISIBLE
            }
            is ItemDataState.Success -> {
                val adapter = ItemAdapter()
                recycler_view.apply {
                    this.adapter = adapter
                }

                progress_bar.visibility = View.GONE
                adapter.submitList(dataState.body)
            }
            is ItemDataState.Error -> {
                progress_bar.visibility = View.GONE
                Toast.makeText(this, dataState.message, Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

}
