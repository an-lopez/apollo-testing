package com.mishka.graphqltest.domain.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mishka.graphqltest.R
import com.mishka.graphqltest.databinding.ItemEpisodeBinding
import com.mishka.graphqltest.domain.model.EpisodeModel

class EpisodeAdapter :
    ListAdapter<EpisodeModel, EpisodeAdapter.EpisodeViewHolder>(characterDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        return EpisodeViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_episode,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class EpisodeViewHolder(private val binding: ItemEpisodeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(model: EpisodeModel) {
            binding.textEpisode.text = model.episode
            binding.textName.text = model.name
            binding.textAirDate.text = model.airDate
        }
    }

    companion object {

        fun characterDiffUtil(): DiffUtil.ItemCallback<EpisodeModel> {
            return object : DiffUtil.ItemCallback<EpisodeModel>() {

                override fun areItemsTheSame(
                    oldItem: EpisodeModel,
                    newItem: EpisodeModel
                ): Boolean {
                    return oldItem.name == newItem.name
                }

                override fun areContentsTheSame(
                    oldItem: EpisodeModel,
                    newItem: EpisodeModel
                ): Boolean {
                    return oldItem == newItem
                }
            }

        }

    }

}