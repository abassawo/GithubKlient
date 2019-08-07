package com.abasscodes.githubklient.screens.homescreen.history

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abasscodes.githubklient.GitKlientApp
import com.abasscodes.githubklient.R
import com.abasscodes.githubklient.base.BaseMvpFragment
import com.abasscodes.githubklient.views.AdapterClickListener
import com.abasscodes.githubklient.views.FragmentInteractionListener
import com.abasscodes.githubklient.views.adapters.history.HistoryAdapter
import kotlinx.android.synthetic.main.fragment_history.*
import javax.inject.Inject

class HistoryFragment : BaseMvpFragment<HistoryContract.Presenter>(), HistoryContract.View,
    AdapterClickListener {
    @Inject
    lateinit var presenter: HistoryPresenter

    override fun getLayoutResourceId(): Int = R.layout.fragment_history
    private val adapter: HistoryAdapter = HistoryAdapter(this)

    override fun getPresenter(): HistoryContract.Presenter = presenter

    override fun onViewCreated(savedInstanceState: Bundle?) {
        super.onViewCreated(savedInstanceState)
        setupRecyclerView(recyclerView)
        GitKlientApp.instance.appComponent?.inject(this)
        presenter.bindView(this)
    }

    override fun onResume() {
        super.onResume()
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
                ): Boolean = false


                override fun getMovementFlags(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder
                ): Int = ItemTouchHelper.Callback.makeMovementFlags(0, ItemTouchHelper.START)


                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) =
                    presenter.onHistoryItemDeleted(viewHolder.adapterPosition)

            }
        )
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    override fun showLoadingIndicator(isLoading: Boolean) {
        progressBar.visibility = if(isLoading) View.VISIBLE else View.GONE
    }

    override fun showEmptyContentMessage(visible: Boolean) {
        noHistoryTextView.visibility = if(visible) View.VISIBLE else View.GONE
    }

    override fun showStoredQueries(queries: Set<String>) =
        adapter.setData(queries.toList())

    override fun onCompanyClicked(company: String) {
        val activity = activity as? FragmentInteractionListener
        activity?.onCompanyClicked(company)
    }

    companion object {
        fun newInstance(): HistoryFragment = HistoryFragment()
    }
}