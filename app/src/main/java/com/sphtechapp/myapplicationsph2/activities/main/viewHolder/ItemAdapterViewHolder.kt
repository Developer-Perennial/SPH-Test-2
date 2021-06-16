package com.sphtechapp.myapplicationsph2.activities.main.viewHolder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sphtechapp.myapplicationsph2.R
import com.sphtechapp.myapplicationsph2.activities.main.data.RecordsData
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.layout_item.view.*

class ItemAdapterViewHolder constructor(
    override val containerView: View
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(recordsData: RecordsData) {
        containerView.item_year.text = "${recordsData.year}"
        containerView.item_vol_decrease.visibility = when (recordsData.decreased) {
            true -> View.VISIBLE
            else -> View.GONE
        }
    }

    companion object {
        fun create(
            parent: ViewGroup
        ): ItemAdapterViewHolder {
            return ItemAdapterViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.layout_item,
                    parent,
                    false
                )
            )
        }
    }
}
