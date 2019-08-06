package com.abasscodes.githubklient.views

interface FragmentInteractionListener {
    fun onCompanyClicked(company: String)
}

interface AdapterClickListener : FragmentInteractionListener