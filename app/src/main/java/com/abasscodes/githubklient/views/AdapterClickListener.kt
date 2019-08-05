package com.abasscodes.githubklient.views

import com.abasscodes.githubklient.models.RecommendedCompany

interface AdapterClickListener {
    fun onCompanyClicked(company: String)
}