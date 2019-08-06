package com.abasscodes.githubklient.screens.history

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abasscodes.githubklient.GitKlientApp
import com.abasscodes.githubklient.base.BaseMvpFragment
import com.abasscodes.githubklient.views.AdapterClickListener
import com.abasscodes.githubklient.views.FragmentInteractionListener
import com.abasscodes.githubklient.views.adapters.history.HistoryAdapter
import kotlinx.android.synthetic.main.fragment_history.*
import javax.inject.Inject
import androidx.recyclerview.widget.ItemTouchHelper
import com.abasscodes.githubklient.R


class HistoryFragment : BaseMvpFragment<HistoryContract.Presenter>(), HistoryContract.View,
    AdapterClickListener{
    @Inject
    lateinit var presenter: HistoryPresenter

    override fun getLayoutResourceId(): Int = R.layout.fragment_history
    val adapter: HistoryAdapter = HistoryAdapter(this)

    override fun getPresenter(): HistoryContract.Presenter = presenter

    override fun onViewCreated(savedInstanceState: Bundle?) {
        super.onViewCreated(savedInstanceState)
        setupRecyclerView(recyclerView)
        GitKlientApp.instance.appComponent?.inject(this)
        presenter.bindView(this)
    }

    fun refresh() {
        presenter.onViewBound()
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = adapter

        val itemTouchHelper = ItemTouchHelper(
            object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.END) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    if (direction == ItemTouchHelper.END) {
                       presenter.onHistoryItemDeleted(viewHolder.adapterPosition)
                    }
                }
            }
        )
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    override fun showStoredQueries(queries: Set<String>) =
        adapter.setData(queries.toList())

    override fun onCompanyClicked(recommendedCompany: String) {
        val activity = activity as? FragmentInteractionListener
        activity?.onCompanyClicked(recommendedCompany)
    }
}