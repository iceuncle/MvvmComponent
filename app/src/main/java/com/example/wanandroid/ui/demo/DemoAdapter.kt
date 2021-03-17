package com.example.wanandroid.ui.demo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.wanandroid.databinding.ItemArticleBinding
import com.example.wanandroid.model.Article


class DemoAdapter<T, VH : RecyclerView.ViewHolder> : PagedListAdapter<Article, DemoAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Article>() {
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem == newItem
            }

            override fun getChangePayload(oldItem: Article, newItem: Article): Any? {
                val payload = Bundle()
                if (oldItem.author != newItem.author) {
                    payload.putString(Article.KEY_AUTHOR, newItem.author)
                }
                if (oldItem.title != newItem.title) {
                    payload.putString(Article.KEY_TITLE, newItem.title)
                }
                return payload
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemArticleBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding.root, binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }

    class ViewHolder(itemView: View, var binding: ItemArticleBinding) : RecyclerView.ViewHolder(itemView) {
        fun bindData(item: Article?) {
            binding.article = item
        }
    }

}