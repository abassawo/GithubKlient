package com.abasscodes.githubklient.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T>(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bind(item: T)
}

fun inflateView(viewGroup: ViewGroup, @LayoutRes layoutRes: Int): View =
    LayoutInflater.from(viewGroup.context).inflate(layoutRes, viewGroup, false)