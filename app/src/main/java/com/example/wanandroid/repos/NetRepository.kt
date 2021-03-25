package com.example.wanandroid.repos

import com.example.wanandroid.net.NetService
import javax.inject.Inject


class NetRepository @Inject constructor(private var netService: NetService) {

    fun getArticles(page: Int) = netService.getArticles(page)

    fun getTopArticles() = netService.getTopArticles()

    fun getArticleList(page: Int) = netService.getArticleList(page)

    fun getProjectTree() = netService.getProjectTree()

    fun getProjectListByCid(page: Int, cid: Int) = netService.getProjectListByCid(page, cid)
}
