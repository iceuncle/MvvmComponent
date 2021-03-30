package com.example.module_base.model

import androidx.core.util.ObjectsCompat.hash


data class Article(var id: Int, var author: String, var title: String) {

    companion object {
        const val KEY_AUTHOR = "author"
        const val KEY_TITLE = "title"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        other as Article
        return id == other.id && author == other.author && title == other.title
    }

    override fun hashCode(): Int {
        return hash(id, author, title)
    }
}
