package com.example.wanandroid.ui.main.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.wanandroid.databinding.ItemProjectBinding
import com.example.wanandroid.model.ArticleBean


class ContentAdapter<T, VH : RecyclerView.ViewHolder> : PagedListAdapter<ArticleBean, ContentAdapter.ViewHolder>(DIFF_CALLBACK) {
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ArticleBean>() {
            override fun areItemsTheSame(oldItem: ArticleBean, newItem: ArticleBean): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ArticleBean, newItem: ArticleBean): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemProjectBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root, binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)!!
        holder.bindData(item)
    }

    class ViewHolder(itemView: View, var binding: ItemProjectBinding) : RecyclerView.ViewHolder(itemView) {
        fun bindData(item: ArticleBean?) {
            binding.data = item
        }
    }
}