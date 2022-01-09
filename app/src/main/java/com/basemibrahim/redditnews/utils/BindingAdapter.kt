package com.basemibrahim.redditnews.utils

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.basemibrahim.redditnews.data.model.api.Children
import com.basemibrahim.redditnews.data.model.api.NewsResponse

import com.basemibrahim.redditnews.ui.NewsAdapter


@BindingAdapter("remoteData", "localData")
fun bindRecyclerView(recyclerView: RecyclerView, remoteData:NetworkResult<NewsResponse>?,
                     localData: NewsResponse? ) {
    val adapter = recyclerView.adapter as NewsAdapter
    var list: List<Children>
    if(Utils.isNetworkAvailable(recyclerView.context))
    {
      list = remoteData?.data?.data?.children ?: listOf()
    }
    else
    {
        list = localData?.data?.children ?: listOf()

    }
    adapter.submitList(list)
}

@BindingAdapter("title")
fun bindTitle(textView: TextView, title: String?) {
    title?.let {
        textView.text = title + "                                                   "
    }
}

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    if (!imgUrl.isNullOrEmpty()) {
        imgView.load(imgUrl)
        imgView.visibility = View.VISIBLE
    } else {
        imgView.visibility = View.GONE
    }
}