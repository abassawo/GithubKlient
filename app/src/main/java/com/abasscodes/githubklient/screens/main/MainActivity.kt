package com.abasscodes.githubklient.screens.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import com.abasscodes.githubklient.GitKlientApp
import com.abasscodes.githubklient.R
import com.abasscodes.githubklient.base.BaseMvpActivity
import com.abasscodes.githubklient.utils.connectivity.ConnectivityUtil
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseMvpActivity<MainContract.Presenter>(), MainContract.View {
    @Inject
    lateinit var presenter: MainPresenter
    override fun getPresenter(): MainContract.Presenter = presenter

    override fun getLayoutResource() = R.layout.activity_main

    override fun onViewCreated(savedInstanceState: Bundle?) {
        super.onViewCreated(savedInstanceState)
        GitKlientApp.instance.appComponent?.inject(this)

        presenter.bindView(this)
    }

    override fun showNoInternetWarning() {
        Snackbar.make(viewPager, getString(R.string.internet_down_msg), Snackbar.LENGTH_INDEFINITE)
            .setAction(R.string.check_internet_settings) {
                startActivity(Intent(Settings.ACTION_WIRELESS_SETTINGS))
            }
            .show()
    }

    override fun showContent(index: Int) {
        viewPager.currentItem = index
    }

    override fun isNetworkAvailable(): Boolean {
        return ConnectivityUtil.isNetworkAvailable(this)
    }

}
