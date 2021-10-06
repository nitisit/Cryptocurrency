package com.example.cryptocurrency

import android.annotation.SuppressLint
import android.content.Context
import android.text.Html
import android.widget.ImageView
import androidx.core.text.HtmlCompat
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import coil.util.CoilUtils
import okhttp3.OkHttpClient

fun ImageView.loadImage(url: String?, imageLoader: ImageLoader) {
    val request = ImageRequest.Builder(this.context)
        .crossfade(true)
        .crossfade(500)
        .placeholder(R.drawable.ic_bitcoin_placeholder)
        .error(R.drawable.ic_bitcoin_placeholder)
        .data(url ?: "")
        .target(this)
        .build()

    imageLoader.enqueue(request)
}

fun imageLoader(context: Context) = ImageLoader.Builder(context)
    .componentRegistry { add(SvgDecoder(context)) }
    .okHttpClient {
        OkHttpClient.Builder()
            .cache(CoilUtils.createDefaultCache(context)).build()
    }
    .build()

@SuppressLint("NewApi")
fun String.convertHtmlToString(): String =
    Html.fromHtml(this, HtmlCompat.FROM_HTML_MODE_LEGACY).toString()