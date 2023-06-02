package com.bagusok.github.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bagusok.github.R
import com.bagusok.github.databinding.ItemListUserBinding
import com.bagusok.github.ui.data.model.User
import com.bumptech.glide.Glide

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private var onItemClickCallback: OnItemClickCallback? = null

    private var list = ArrayList<User>()

    inner class UserViewHolder(val binding: ItemListUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) {
            binding.root.setOnClickListener {
                onItemClickCallback?.onItemClicked(user)
            }
            binding.apply {
                Glide.with(itemView)
                    .load(user.avatarUrl)
                    .placeholder(R.drawable.dummy)
                    .centerCrop()
                    .into(imgUser)

                username.text = user.login
                listLink.text = user.htmlUrl
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = ItemListUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder((view))
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    fun setData(newList: ArrayList<User>) {
        val diffUtils = MainDiffUtils(list, newList)
        val diffResult = DiffUtil.calculateDiff(diffUtils)
        list = newList
        diffResult.dispatchUpdatesTo(this)
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: User)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
}