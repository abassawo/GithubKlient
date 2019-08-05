package com.abasscodes.githubklient.screens.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.webkit.WebViewClient
import com.abasscodes.githubklient.GitKlientApp
import com.abasscodes.githubklient.R
import com.abasscodes.githubklient.base.BaseMvpActivity
import com.abasscodes.githubklient.models.RepoModel
import kotlinx.android.synthetic.main.activity_details.*
import javax.inject.Inject

class DetailActivity : BaseMvpActivity<DetailContract.Presenter>(), DetailContract.View {
    @Inject lateinit var presenter: DetailPresenter

    override fun getPresenter(): DetailContract.Presenter = presenter

    override fun getLayoutResource(): Int = R.layout.activity_details

    override fun onViewCreated(savedInstanceState: Bundle?) {
        super.onViewCreated(savedInstanceState)
        GitKlientApp.instance.appComponent?.inject(this)
        setSupportActionBar(toolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.title = getString(R.string.app_name)
        }
        setupWebView()
        presenter.bindView(this)
        intent.extras[KEY_EXTRA_URL]?.let { presenter.onUrlDispatched(it.toString()) }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return true
    }

    private fun setupWebView() {
        webview.setWebViewClient(WebViewClient())
        webview.getSettings().setJavaScriptCanOpenWindowsAutomatically(false)
        webview.getSettings().setSupportMultipleWindows(false)
        webview.getSettings().setSupportZoom(false)
        webview.setVerticalScrollBarEnabled(false)
        webview.setHorizontalScrollBarEnabled(false)
    }

    override fun showWebView(url: String) = webview.loadUrl(url)

    companion object {

        private val KEY_EXTRA_URL = "key_extra"

        fun makeIntent(context: Context, repoModel: RepoModel) = Intent(context, DetailActivity::class.java)
            .putExtra(KEY_EXTRA_URL, repoModel.html_url)
    }

}