package com.example.wanandroid.ui.main.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.wanandroid.databinding.ItemHomeBinding
import com.example.wanandroid.model.ArticleBean


class HomeAdapter<T, VH : RecyclerView.ViewHolder> : PagedListAdapter<ArticleBean, HomeAdapter.ViewHolder>(DIFF_CALLBACK) {

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

    var ivCollectClick: ((item: ArticleBean) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root, binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)!!
        holder.bindData(item)
        holder.binding.ivCollect.setOnClickListener {
            ivCollectClick?.invoke(item)
        }
    }

    class ViewHolder(itemView: View, var binding: ItemHomeBinding) : RecyclerView.ViewHolder(itemView) {
        fun bindData(item: ArticleBean?) {
            binding.data = item
        }
    }

}