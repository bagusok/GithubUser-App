package com.bagusok.github.ui.main

import androidx.recyclerview.widget.DiffUtil
import com.bagusok.github.ui.data.model.User

class MainDiffUtils(
    private val oldList: ArrayList<User>,
    private val newList: ArrayList<User>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            oldList[oldItemPosition].id !== newList[newItemPosition].id -> {
                false
            }
            else -> true
        }
    }
}