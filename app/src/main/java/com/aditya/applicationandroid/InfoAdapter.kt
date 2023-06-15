package com.aditya.applicationandroid

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aditya.applicationandroid.databinding.ListInfoBinding
import com.bumptech.glide.Glide

class InfoAdapter : ListAdapter<Posts, InfoAdapter.ViewHolder>(DIF_CALLBACK) {


    inner class ViewHolder(
        private val binding: ListInfoBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Posts) {
            binding.tvTitle.text = data.id.toString()
            binding.tvBody.text = data.title

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))

    }

    companion object {
        val DIF_CALLBACK: DiffUtil.ItemCallback<Posts> =
            object : DiffUtil.ItemCallback<Posts>() {
                override fun areItemsTheSame(oldItem: Posts, newItem: Posts): Boolean {
                    return oldItem.id == newItem.id
                }

                @SuppressLint("DiffUtilEquals")
                override fun areContentsTheSame(oldItem: Posts, newItem: Posts): Boolean {
                    return oldItem == newItem
                }

            }
    }
}