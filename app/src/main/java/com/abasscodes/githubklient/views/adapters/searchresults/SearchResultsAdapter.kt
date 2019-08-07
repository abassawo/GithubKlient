package com.abasscodes.githubklient.views.adapters.searchresults

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abasscodes.githubklient.R
import com.abasscodes.githubklient.base.BaseViewHolder
import com.abasscodes.githubklient.base.inflateView
import com.abasscodes.githubklient.models.RepoModel
import com.abasscodes.githubklient.views.adapters.searchresults.SearchResultsAdapter.SearchResultsViewHolder.Companion.LAYOUT_RES
import kotlinx.android.synthetic.main.search_results_item.view.*

class SearchResultsAdapter(private val listener: OnSearchResultClickListener) : RecyclerView.Adapter<BaseViewHolder<RepoModel>>() {
    private var repoModels: List<RepoModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultsViewHolder {
        val view = inflateView(parent, LAYOUT_RES)
        return SearchResultsViewHolder(view, listener)
    }

    fun setData(repoModels: List<RepoModel>) {
        this.repoModels = repoModels
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: BaseViewHolder<RepoModel>, position: Int) {
        holder.bind(repoModels[position])
    }

    override fun getItemCount(): Int = repoModels.size

    class SearchResultsViewHolder(view: View, private val listener: OnSearchResultClickListener) : BaseViewHolder<RepoModel>(view) {

        override fun bind(item: RepoModel) {
            with(itemView) {
                repoName.text = item.full_name
                repoStars.text = context.getString(R.string.stars, item.stargazers_count)
                repoDescription.text = item.description
                setOnClickListener { listener.onSearchResultClick(item) }
            }
        }

        companion object {
            const val LAYOUT_RES = R.layout.search_results_item
        }
    }

    interface OnSearchResultClickListener {
        fun onSearchResultClick(model: RepoModel)
    }
}