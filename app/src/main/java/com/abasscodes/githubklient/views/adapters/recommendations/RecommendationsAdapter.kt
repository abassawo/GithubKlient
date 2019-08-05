package com.abasscodes.githubklient.views.adapters.recommendations

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abasscodes.githubklient.R
import com.abasscodes.githubklient.base.BaseViewHolder
import com.abasscodes.githubklient.models.RecommendedCompany
import com.abasscodes.githubklient.utils.inflateView
import com.abasscodes.githubklient.views.AdapterClickListener
import kotlinx.android.synthetic.main.recommendation_view_row.view.*

class RecommendationsAdapter(val listener: AdapterClickListener) :
    RecyclerView.Adapter<RecommendationsAdapter.RecommmendationViewHolder>() {

    var recommendations: List<RecommendedCompany> = mutableListOf()

    fun setData(recommendations: List<RecommendedCompany>) {
        this.recommendations = recommendations
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommmendationViewHolder {
        val view = inflateView(parent,
            RecommmendationViewHolder.layout
        )
        return RecommmendationViewHolder(
            view,
            listener
        )
    }

    override fun getItemCount(): Int = recommendations.size

    override fun onBindViewHolder(holder: RecommmendationViewHolder, position: Int) {
        holder.bind(recommendations[position])
    }

    class RecommmendationViewHolder(
        view: View,
        val listener: AdapterClickListener
    ) : BaseViewHolder<RecommendedCompany>(view) {

        lateinit var recommendedCompany: RecommendedCompany

        override fun bind(recommendedCompany: RecommendedCompany) {
            this.recommendedCompany = recommendedCompany

            with(itemView) {
                companyName.text = recommendedCompany.githubName
                setOnClickListener{ listener.onCompanyClicked(recommendedCompany.githubName) }
            }
        }

        companion object {
            val layout = R.layout.recommendation_view_row
        }
    }


}

