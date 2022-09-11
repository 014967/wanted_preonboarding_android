package com.example.wantedpreonboarding.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.wantedpreonboarding.R
import com.example.wantedpreonboarding.databinding.ItemTopNewsBinding
import com.example.wantedpreonboarding.presentation.model.TopNews

/**
 * @Created by 김현국 2022/09/07
 */
class TopNewsAdapter(
    private val topNewsClick: (TopNews) -> Unit
) : ListAdapter<TopNews, TopNewsAdapter.TopNewsViewHolder>(diffUtils) {

    companion object {
        private val diffUtils = object : DiffUtil.ItemCallback<TopNews>() {
            override fun areItemsTheSame(oldItem: TopNews, newItem: TopNews): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: TopNews, newItem: TopNews): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopNewsViewHolder {
        return TopNewsViewHolder(
            ItemTopNewsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TopNewsViewHolder, position: Int) {
        holder.bind(topNews = getItem(position))
        holder.itemView.setOnClickListener {
            topNewsClick.invoke(getItem(position))
        }
    }

    class TopNewsViewHolder(private val binding: ItemTopNewsBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(topNews: TopNews) = with(binding) {
            Glide.with(ivTopNews).load(topNews.imageUrl)
                .transform(CenterCrop(), RoundedCorners(10)).placeholder(R.drawable.img_placeholder).into(ivTopNews)
            tvTopNewsTitle.text = topNews.title
            tvTopNewsWriter.text = topNews.writer
            tvTopNewsWritedTime.text = tvTopNewsWritedTime.context.getString(R.string.news_writed_time, topNews.writedTime)
        }
    }
}
