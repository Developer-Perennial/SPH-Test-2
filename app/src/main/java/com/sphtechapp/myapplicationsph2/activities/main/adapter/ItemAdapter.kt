package com.sphtechapp.myapplicationsph2.activities.main.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.sphtechapp.myapplicationsph2.activities.main.data.RecordsData
import com.sphtechapp.myapplicationsph2.activities.main.viewHolder.ItemAdapterViewHolder

class ItemAdapter() :
    ListAdapter<RecordsData, ItemAdapterViewHolder>(
        DIFF_CALLBACK
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ItemAdapterViewHolder.create(parent)

    override fun onBindViewHolder(holderAdapter: ItemAdapterViewHolder, position: Int) {
        holderAdapter.bind(getItem(position))
    }

    companion object {

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<RecordsData>() {

            override fun areItemsTheSame(
                oldItem: RecordsData,
                newItem: RecordsData
            ) = oldItem._id == newItem._id

            override fun areContentsTheSame(
                oldItem: RecordsData,
                newItem: RecordsData
            ) =
                oldItem == newItem
        }
    }
}