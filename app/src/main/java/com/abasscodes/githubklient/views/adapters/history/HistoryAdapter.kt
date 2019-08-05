package com.abasscodes.githubklient.views.adapters.history

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abasscodes.githubklient.R
import com.abasscodes.githubklient.base.BaseViewHolder
import com.abasscodes.githubklient.utils.inflateView
import com.abasscodes.githubklient.views.AdapterClickListener
import com.abasscodes.githubklient.views.adapters.history.HistoryAdapter.HistoryViewHolder.Companion.LAYOUT_RES
import kotlinx.android.synthetic.main.recommendation_view_row.view.*

class HistoryAdapter(val listener: AdapterClickListener) : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    private var items: List<String> = mutableListOf()

    fun setData(items: List<String>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view = inflateView(parent, LAYOUT_RES)
        return HistoryViewHolder(view, listener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) = holder.bind(items[position])


    class HistoryViewHolder(view: View, val listener: AdapterClickListener) : BaseViewHolder<String>(view) {

        override fun bind(item: String) {
            itemView.companyName.text = item
            itemView.setOnClickListener{ listener.onCompanyClicked(item)}
        }

        companion object {
            val LAYOUT_RES = R.layout.recommendation_view_row
        }
    }

}