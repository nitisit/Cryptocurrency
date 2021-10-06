package com.example.cryptocurrency.presentation.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import com.example.cryptocurrency.R
import com.example.cryptocurrency.convertHtmlToString
import com.example.cryptocurrency.data.model.CoinX
import com.example.cryptocurrency.databinding.CoinItemBinding
import com.example.cryptocurrency.databinding.CoinSubItemBinding
import com.example.cryptocurrency.databinding.EmptyItemBinding
import com.example.cryptocurrency.loadImage

class CoinAdapter(private val imageLoader: ImageLoader) :
    PagedListAdapter<CoinX, RecyclerView.ViewHolder>(CoinDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            TYPE_HEADER -> {
                val coinSubItemBinding: CoinSubItemBinding =
                    DataBindingUtil.inflate(layoutInflater, R.layout.coin_sub_item, parent, false)
                HeaderViewHolder(coinSubItemBinding, imageLoader)
            }
            TYPE_BODY -> {
                val coinItemBinding: CoinItemBinding =
                    DataBindingUtil.inflate(layoutInflater, R.layout.coin_item, parent, false)
                BodyViewHolder(coinItemBinding, imageLoader)
            }
            else -> {
                val emptyItemBinding: EmptyItemBinding =
                    DataBindingUtil.inflate(layoutInflater, R.layout.empty_item, parent, false)
                EmptyViewHolder(emptyItemBinding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            TYPE_HEADER -> (holder as HeaderViewHolder).bind(getItem(position))
            TYPE_BODY -> (holder as BodyViewHolder).bind(getItem(position))
            else -> (holder as EmptyViewHolder)
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (getItem(position) == null) return TYPE_EMPTY
        return if ((position + 1) % 5 == 0) TYPE_HEADER else TYPE_BODY
    }

    class HeaderViewHolder(
        private val coinSubItemBinding: CoinSubItemBinding,
        private val imageLoader: ImageLoader
    ) : RecyclerView.ViewHolder(coinSubItemBinding.root) {
        fun bind(coinData: CoinX?) {
            coinSubItemBinding.apply {
                coin = coinData.apply {
                    this?.description = this?.description?.convertHtmlToString()
                }
                coinSubItemBinding.imgSubCoin.loadImage(coinData?.iconUrl, imageLoader)
            }
        }
    }

    class BodyViewHolder(
        private val coinItemBinding: CoinItemBinding,
        private val imageLoader: ImageLoader
    ) : RecyclerView.ViewHolder(coinItemBinding.root) {
        fun bind(coinData: CoinX?) {
            coinItemBinding.apply {
                coin = coinData.apply {
                    this?.description = this?.description?.convertHtmlToString()
                }
                coinItemBinding.imgCoin.loadImage(coinData?.iconUrl, imageLoader)
            }
        }
    }

    class EmptyViewHolder(
        private val emptyItemBinding: EmptyItemBinding
    ) : RecyclerView.ViewHolder(emptyItemBinding.root)

    companion object {
        private const val TYPE_HEADER = 0
        private const val TYPE_BODY = 1
        private const val TYPE_EMPTY = 2

        val CoinDiffCallback = object : DiffUtil.ItemCallback<CoinX>() {
            override fun areItemsTheSame(oldItem: CoinX, newItem: CoinX): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: CoinX, newItem: CoinX): Boolean {
                return oldItem == newItem
            }
        }
    }
}
